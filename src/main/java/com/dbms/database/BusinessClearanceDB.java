package com.dbms.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.dbms.models.BusinessClearanceTransaction;
import com.dbms.models.InspectionType;
import com.dbms.models.PropertyType;

public class BusinessClearanceDB {

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
                    ownerAddress, businessAddress, businessName, businessType, contactNumber, propertyType,
                    registrationNumber, inspector, inspectionDate, amount, officialReceiptNumber);
        }
        conn.close();

        return transaction;
    }

    public static void addBusinessClearanceTransaction(BusinessClearanceTransaction transaction)
            throws SQLException {
        String inspectionType = transaction.getInspectionType().toString();
        String owner = transaction.getOwner();
        String ownerAddress = transaction.getOwnerAddress();
        String businessName = transaction.getBusinessName();
        String businessAddress = transaction.getBusinessAddress();
        String businessType = transaction.getBusinessType();
        String contactNumber = transaction.getContactNumber();
        String propertyType = transaction.getPropertyType().toString();
        String registrationNumber = transaction.getRegistrationNumber();
        String inspector = transaction.getInspector();
        String inspectionDate = transaction.getInspectionDate().toString();
        String amount = transaction.getAmount().toString();
        String officialReceiptNumber = transaction.getOfficialReceiptNumber();

        String query = "INSERT INTO `" + DBConnection.getDBName()
                + "`.`business_clearance_transaction` ("
                + "`inspection_type`, `owner`, `owner_address`, `business_address`, `business_name`, `business_type`, `contact_number`, "
                + "`property_type`, `registration_number`, `inspector`, `inspection_date`, `amount`, `official_receipt_number`"
                + ") VALUES ("
                + "'" + inspectionType + "', '" + owner + "', '" + ownerAddress + "', '" + businessAddress + "', '"
                + businessName + "', '" + businessType + "', '" + contactNumber + "', '" + propertyType + "', '"
                + registrationNumber + "', '" + inspector + "', '" + inspectionDate + "', '" + amount + "', '"
                + officialReceiptNumber + "');";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }
}