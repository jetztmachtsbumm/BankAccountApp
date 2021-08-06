package de.kellner.bankaccountapp.database;

import de.kellner.bankaccountapp.DatabaseRepository;
import de.kellner.bankaccountapp.Receipt;
import de.kellner.bankaccountapp.Revenue;
import de.kellner.bankaccountapp.Usage;
import de.kellner.bankaccountapp.util.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLLite implements DatabaseRepository {

    public Connection con = null;

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Successfully connected to database");
    }

    public void createTable(){
        try {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS transactions (t_id TEXT PRIMARY KEY, transaction_type TEXT, value REAL, sender_recipient TEXT, day INTEGER, month INTEGER, year INTEGER, usage TEXT )";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void CreateRevenue(Revenue revenue) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO transactions (t_id, transaction_type, value, sender_recipient, day, month, year, usage) VALUES ('" + revenue.id + "', 'revenue', '" + revenue.value + "', '" + revenue.sender_recipient + "', '" + revenue.date.day + "', '" + revenue.date.month + "', '" + revenue.date.year + "', '" + revenue.usage.toString() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void CreateReceipt(Receipt receipt) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO transactions (t_id, transaction_type, value, sender_recipient, day, month, year, usage) VALUES ('" + receipt.id + "', 'receipt', '" + receipt.value + "', '" + receipt.sender_recipient + "', '" + receipt.date.day + "', '" + receipt.date.month + "', '" + receipt.date.year + "', '" + receipt.usage.toString() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public List<Revenue> GetAllRevenues() {
        List<Revenue> revenues = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE transaction_type = 'revenue'");
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("t_id"));
                float value = rs.getFloat("value");
                String recipient = rs.getString("sender_recipient");
                Date date = new Date(rs.getInt("day"), rs.getInt("month"), rs.getInt("year"));
                Usage usage = Usage.valueOf(rs.getString("usage"));

                revenues.add(new Revenue(id, value, recipient, date, usage));
            }
        } catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return revenues;
    }

    @Override
    public List<Receipt> GetAllReceipts() {
        List<Receipt> receipts = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE transaction_type = 'receipt'");
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("t_id"));
                float value = rs.getFloat("value");
                String sender = rs.getString("sender_recipient");
                Date date = new Date(rs.getInt("day"), rs.getInt("month"), rs.getInt("year"));
                Usage usage = Usage.valueOf(rs.getString("usage"));

                receipts.add(new Receipt(id, value, sender, date, usage));
            }
        } catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return receipts;
    }
    public void deleteTransaction(String uuid){
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM transactions WHERE t_id = '" + uuid + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
