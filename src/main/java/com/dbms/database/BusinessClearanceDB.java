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
        BusinessClearanceTransaction businessClearanceTransaction = null;
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

            businessClearanceTransaction = new BusinessClearanceTransaction(TRANSACTION_ID, inspectionType, owner,
                    ownerAddress, businessAddress, businessName, businessType, contactNumber, propertyType,
                    registrationNumber, inspector, inspectionDate, amount, officialReceiptNumber);
        }
        conn.close();

        return businessClearanceTransaction;
    }

    public static void addBusinessClearanceTransaction(BusinessClearanceTransaction businessClearanceTransaction)
            throws SQLException {
        String inspectionType = businessClearanceTransaction.getInspectionType().toString();
        String owner = businessClearanceTransaction.getOwner();
        String ownerAddress = businessClearanceTransaction.getOwnerAddress();
        String businessName = businessClearanceTransaction.getBusinessName();
        String businessAddress = businessClearanceTransaction.getBusinessAddress();
        String businessType = businessClearanceTransaction.getBusinessType();
        String contactNumber = businessClearanceTransaction.getContactNumber();
        String propertyType = businessClearanceTransaction.getPropertyType().toString();
        String registrationNumber = businessClearanceTransaction.getRegistrationNumber();
        String inspector = businessClearanceTransaction.getInspector();
        String inspectionDate = businessClearanceTransaction.getInspectionDate().toString();
        String amount = businessClearanceTransaction.getAmount().toString();
        String officialReceiptNumber = businessClearanceTransaction.getOfficialReceiptNumber();

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