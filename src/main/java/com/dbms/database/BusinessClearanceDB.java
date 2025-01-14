package com.dbms.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.dbms.models.BusinessClearanceTransaction;
import com.dbms.models.InspectionType;
import com.dbms.models.PropertyType;
import com.dbms.utils.PopulateTable;
import javafx.scene.control.TableView;

public class BusinessClearanceDB {

    // fetch business clearance transaction
    public static BusinessClearanceTransaction fetchBusinessClearanceTransaction(int transactionId)
            throws SQLException {
        BusinessClearanceTransaction transaction = null;
        String query = "SELECT * FROM `" + DBConnection.getDBName()
                + "`.`business_clearance_transaction` WHERE transaction_id = "
                + transactionId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int TRANSACTION_ID = rs.getInt("transaction_id");
            InspectionType inspectionType = InspectionType.fromString(rs.getString("inspection_type"));
            String owner = rs.getString("owner");
            String ownerAddress = rs.getString("owner_address");
            String businessName = rs.getString("business_name");
            String businessAddress = rs.getString("business_address");
            String businessType = rs.getString("business_type");
            String contactNumber = rs.getString("contact_number");
            PropertyType propertyType = PropertyType.fromString(rs.getString("property_type"));
            String registrationNumber = rs.getString("registration_number");
            String inspector = rs.getString("inspector");
            LocalDate inspectionDate = rs.getDate("inspection_date").toLocalDate();
            BigDecimal amount = rs.getBigDecimal("amount");
            String officialReceiptNumber = rs.getString("official_receipt_number");

            transaction = new BusinessClearanceTransaction(TRANSACTION_ID, inspectionType, owner,
                    ownerAddress, businessAddress, businessName, businessType, contactNumber,
                    propertyType,
                    registrationNumber, inspector, inspectionDate, amount, officialReceiptNumber);
        }
        conn.close();

        return transaction;
    }

    // add business clearance transaction
    public static void addBusinessClearanceTransaction(BusinessClearanceTransaction transaction)
            throws SQLException {
        String query = "INSERT INTO `" + DBConnection.getDBName()
                + "`.`business_clearance_transaction` ("
                + "`inspection_type`, `owner`, `owner_address`, `business_address`, `business_name`, `business_type`, `contact_number`, "
                + "`property_type`, `registration_number`, `inspector`, `inspection_date`, `amount`, `official_receipt_number`"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, transaction.getInspectionType().toString());
        stmt.setString(2, transaction.getOwner());
        stmt.setString(3, transaction.getOwnerAddress());
        stmt.setString(4, transaction.getBusinessAddress());
        stmt.setString(5, transaction.getBusinessName());
        stmt.setString(6, transaction.getBusinessType());
        stmt.setString(7, transaction.getContactNumber());
        stmt.setString(8, transaction.getPropertyType().toString());
        stmt.setString(9, transaction.getRegistrationNumber());
        stmt.setString(10, transaction.getInspector());
        stmt.setString(11, transaction.getInspectionDate().toString());
        stmt.setBigDecimal(12, transaction.getAmount());
        stmt.setString(13, transaction.getOfficialReceiptNumber());

        stmt.execute();
        conn.close();
    }

    // update business clearance transaction
    public static void updateBusinessClearanceTransaction(BusinessClearanceTransaction transaction)
            throws SQLException {
        String query = "UPDATE `" + DBConnection.getDBName()
                + "`.`business_clearance_transaction` SET "
                + "`inspection_type` = ?, `owner` = ?, `owner_address` = ?, `business_address` = ?, "
                + "`business_name` = ?, `business_type` = ?, `contact_number` = ?, `property_type` = ?, "
                + "`registration_number` = ?, `inspector` = ?, `inspection_date` = ?, `amount` = ?, "
                + "`official_receipt_number` = ? WHERE `transaction_id` = ?;";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, transaction.getInspectionType().toString());
        stmt.setString(2, transaction.getOwner());
        stmt.setString(3, transaction.getOwnerAddress());
        stmt.setString(4, transaction.getBusinessAddress());
        stmt.setString(5, transaction.getBusinessName());
        stmt.setString(6, transaction.getBusinessType());
        stmt.setString(7, transaction.getContactNumber());
        stmt.setString(8, transaction.getPropertyType().toString());
        stmt.setString(9, transaction.getRegistrationNumber());
        stmt.setString(10, transaction.getInspector());
        stmt.setString(11, transaction.getInspectionDate().toString());
        stmt.setBigDecimal(12, transaction.getAmount());
        stmt.setString(13, transaction.getOfficialReceiptNumber());
        stmt.setInt(14, transaction.getTransactionId());

        stmt.execute();
        conn.close();
    }

    // delete business clearance transaction
    public static void deleteBusinessClearanceTransaction(int transactionId) throws SQLException {
        // set status to 0
        String query = "UPDATE `" + DBConnection.getDBName()
                + "`.`business_clearance_transaction` SET `status` = 0 WHERE `transaction_id` = "
                + transactionId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }

    // populate TableView with full list
    public static void populateTable(TableView tableView) throws SQLException {
        String query = "SELECT transaction_id, inspection_type, owner, owner_address, business_name, business_address, business_type, contact_number, property_type, registration_number, inspector, inspection_date, amount, official_receipt_number FROM `"
                + DBConnection.getDBName()
                + "`.`business_clearance_transaction` WHERE status = 1;";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        PopulateTable.populateTable(tableView, rs);
        conn.close();
    }

    // populate TableView with search results
    public static void populateSearchResults(TableView tableView, String searchString, ArrayList<String> searchOptions)
            throws SQLException {
        ResultSet rs = searchQuery(searchString, searchOptions);
        PopulateTable.populateTable(tableView, rs);
    }

    // customized search query
    private static ResultSet searchQuery(String searchString, ArrayList<String> searchOptions) throws SQLException {
        String query = "SELECT transaction_id, inspection_type, owner, owner_address, business_name, business_address, business_type, contact_number, property_type, registration_number, inspector, inspection_date, amount, official_receipt_number FROM `"
                + DBConnection.getDBName()
                + "`.`business_clearance_transaction` WHERE status = 1 AND (";

        for (int i = 0; i < searchOptions.size(); i++) {
            query += searchOptions.get(i) + " LIKE ? ";
            if (i < searchOptions.size() - 1) {
                query += "OR ";
            }
        }

        query += ");";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < searchOptions.size(); i++) {
            stmt.setString(i + 1, "%" + searchString + "%");
        }
        ResultSet rs = stmt.executeQuery();

        return rs;
    }
}
