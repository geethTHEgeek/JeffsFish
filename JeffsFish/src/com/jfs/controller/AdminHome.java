package com.jfs.controller;

import com.jfs.model.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminHome implements Initializable{


    public static Admin admin;

    @FXML
    private Label label;

    @FXML
    private Label lbl1;



    /**
     * Go to staff registration screen
     */
    public void registerStaff(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/staff_registration.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Staff registration Screen");
        stage.setScene(scene);
        stage.show();


    }

    /**
     * Go to Transaction log window
     */
    public void goToTransactionsLog(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/transaction_log.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Transaction Log");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Go to Customer Report Window
     */
    public void goToCustomerReport(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/customer_report.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
           e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Customer Report");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl1.setText("Hi! "+admin.getFirstName()+" Welcome Back");
    }
}
