package com.jfs.controller;

import com.jfs.model.Customer;
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
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerHome implements Initializable{


   public static Map<String,String> userDetails;

   private Customer customer;

    public void setUserDetails(Map<String,String> userDetais){
        this.userDetails = userDetais;
    }

    public void setCustomer(Customer customer){
        this.customer=customer;
    }

    @FXML
    private Label label;

    @FXML
    private Label lbl1;

    /**
     * Send customer to product catalogue screen
     */
    public void goToProductCatalogue() {
        ProductCatalogue.userDetails=userDetails;

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/product_catalogue.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Product Catalogue");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Send the customer to modify account screen
     */
    public void goToModifyAccount(){

        ModifyAccount modifyAccount =new ModifyAccount();
        modifyAccount.setUserDetais(userDetails);
        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/modify_account.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Modify Account");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * send customer to email inquiry screen
     */
    public void goToEmailInquiry(){

        //set the map of userdetails for email inquiry
        EmailInquiry.userDetails=userDetails;
        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/email_inquiry.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setTitle("Email Inquiry");
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl1.setText("Hello! "+userDetails.get("firstName")+ ", Welcome to JFS");
    }
}
