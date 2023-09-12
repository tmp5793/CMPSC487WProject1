package com.example.cmpsc487wproject1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.*;

public class HelloController {

    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    private Button clearButton;

    @FXML
    private Button filterButton;

    @FXML
    private TextField dateFilter;

    @FXML
    private TextField idFilter;

    @FXML
    private TextField timeFilter;

    @FXML
    private TableView<userrecord> historyTable;

    @FXML
    private TableColumn<userrecord, Date> historyDate;

    @FXML
    private TableColumn<userrecord, Integer> historyID;

    @FXML
    private TableColumn<userrecord, Time> historyTime;

    @FXML
    private TableView<users> userTable;

    @FXML
    private TableColumn<users, Boolean> usersAccess;

    @FXML
    private TableColumn<users, Integer> usersID;

    @FXML
    private TableColumn<users, String> usersName;

    @FXML
    private TableColumn<users, String> usersTitle;

    @FXML
    private TextField swipeBox;

    @FXML
    private Text swipeText;

    @FXML
    protected void onSwipeButtonClick() {
        swipeText.setVisible(true);
        connection = sqlconnection.connectDatabase();
        int swipeID = Integer.parseInt(swipeBox.getText());
        String length = String.valueOf(swipeBox.getText().length());
        Time time = new Time(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        if (length.equals(String.valueOf(9))) {
            for (com.example.cmpsc487wproject1.users users : userlist) {
                if (users.id == swipeID && users.access.equals(true)) {
                    try {
                        PreparedStatement pst = connection.prepareStatement("INSERT INTO userhistory (id,date,time)VALUES(?,?,?)");
                        pst.setInt(1,swipeID);
                        pst.setDate(2,date);
                        pst.setTime(3,time);
                        pst.execute();
                        swipeText.setText("Swipe recorded");
                        break;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    swipeText.setText("Access denied!");
                }
            }
        } else {
            swipeText.setText("Please enter a valid 9 digit ID number!");
        }
    }

    private void filterHistoryTable() {
        ObservableList<userrecord> filteredList = historylist.filtered(record -> (idFilter.getText().isEmpty() || record.getId() == Integer.parseInt(idFilter.getText())) && (dateFilter.getText().isEmpty() || record.getDate().toString().equals(dateFilter.getText())) && (timeFilter.getText().isEmpty() || record.getTime().toString().equals(timeFilter.getText())));

        historyTable.setItems(filteredList);
    }

    @FXML
    protected void onClearFilterButtonClick() {
        idFilter.clear();
        dateFilter.clear();
        timeFilter.clear();
        historyTable.setItems(historylist);
    }

    @FXML
    protected void onFilterButtonClick() {
        filterHistoryTable();
    }

    @FXML
    private TextField adminIdBox;

    @FXML
    protected void onAdminButtonClick() {
        int adminID = Integer.parseInt(adminIdBox.getText());
        String length = String.valueOf(adminIdBox.getText().length());
        adminIdBox.setText("");
        if (length.equals(String.valueOf(9))) {
            for (com.example.cmpsc487wproject1.users users : userlist) {
                if (users.id == adminID && users.title.equals("Admin")) {
                    userTable.setVisible(true);
                    historyTable.setVisible(true);
                    dateFilter.setVisible(true);
                    idFilter.setVisible(true);
                    timeFilter.setVisible(true);
                    clearButton.setVisible(true);
                    filterButton.setVisible(true);
                    adminIdBox.setPromptText("Accepted, welcome!");
                    break;
                } else {
                    adminIdBox.setPromptText("Access Denied!");
                }
            }
        } else {
            adminIdBox.setPromptText("Please enter a valid 9 digit ID number!");
        }
    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    ObservableList<users> userlist;
    ObservableList<userrecord> historylist;

    public void initialize() {
        usersID.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        usersName.setCellValueFactory(new PropertyValueFactory<users,String>("name"));
        usersTitle.setCellValueFactory(new PropertyValueFactory<users,String>("title"));
        usersAccess.setCellValueFactory(new PropertyValueFactory<users,Boolean>("access"));

        userlist = sqlconnection.getData();
        userTable.setItems(userlist);

        historyID.setCellValueFactory(new PropertyValueFactory<userrecord,Integer>("id"));
        historyTime.setCellValueFactory(new PropertyValueFactory<userrecord,Time>("time"));
        historyDate.setCellValueFactory(new PropertyValueFactory<userrecord,Date>("date"));

        historylist = sqlconnection.getHistoryData();
        historyTable.setItems(historylist);

    }
}


