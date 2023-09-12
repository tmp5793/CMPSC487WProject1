package com.example.cmpsc487wproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlconnection {

    public static Connection connectDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Password!");
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ObservableList<users> getData() {
        Connection connection = connectDatabase();
        ObservableList<users> userlist = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet userResultSet = statement.executeQuery("SELECT * FROM usertable");
            while (userResultSet.next()) {
                userlist.add(new users(Integer.parseInt(userResultSet.getString("id")), userResultSet.getString("name"), userResultSet.getString("title"), userResultSet.getBoolean("access")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return userlist;
    }

    public static ObservableList<userrecord> getHistoryData() {
        Connection connection = connectDatabase();
        ObservableList<userrecord> historylist = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet historyResultSet = statement.executeQuery("SELECT * FROM userhistory");
            while (historyResultSet.next()) {
                historylist.add(new userrecord(Integer.parseInt(historyResultSet.getString("id")), historyResultSet.getDate("date"), historyResultSet.getTime("time")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return historylist;
    }
}
