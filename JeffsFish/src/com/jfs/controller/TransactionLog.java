package com.jfs.controller;


import com.jfs.model.Transaction;
import com.jfs.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TransactionLog implements Initializable{


    @FXML
    private TableView<Transaction> table;

    @FXML
    private TableColumn<Transaction,Integer> receiptId;

    @FXML
    private  TableColumn<Transaction,Integer> userId;

    @FXML
    private TableColumn<Transaction,Double> totalPaid;

    @FXML
    private  TableColumn<Transaction,String> date;

    public ObservableList<Transaction> transactions;

    private Transaction transaction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transactions= FXCollections.observableArrayList();

        //creating connection to data
        DBConnection connectionClass=new DBConnection();
        Connection connection=connectionClass.getConnection();

        long millis=System.currentTimeMillis();
        Date todayDate=new Date(millis);

        //excicuting querry fo transactions
        Statement stmt= null;
        ResultSet rst= null;
        try {
            stmt = connection.createStatement();
            String querry="SELECT * FROM transaction_log WHERE date="+"'"+todayDate+"'";
            rst = stmt.executeQuery(querry);
            while(rst.next()) {

                int rcptId=rst.getInt("receiptId");
                int usrId=rst.getInt("userId");
                double totAmount=rst.getDouble("totalPaid");
                Date theDate=rst.getDate("date");

                transaction =new Transaction(rcptId,usrId,totAmount,theDate);
                transactions.add(transaction);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        receiptId.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("receiptId"));
        userId.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("userId"));
        totalPaid.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("totalAmount"));
        date.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

        table.setItems(transactions);


    }

    /**
     * go to admin home screen
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/admin_home.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {

        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) table.getScene().getWindow();

        stage.setTitle("Customer Home");
        stage.setScene(scene);
        stage.show();

    }
}
