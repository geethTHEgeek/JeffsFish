package com.jfs.controller;

import com.jfs.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class ModifyAccount{

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private PasswordField password;

    public static Map<String,String> userDetails;

    public void setUserDetais(Map<String,String> userDetails) {
        this.userDetails = userDetails;
    }



    public void clickUpdate() throws SQLException {

        String firstNameEntered = firstName.getText();
        String lastNameEntered = lastName.getText();
        String emailEntered = email.getText();
        String passwordEntered = password.getText();
        String addressEntered = address.getText();
        DBConnection connectionClass=new DBConnection();
        Connection connection=connectionClass.getConnection();


            PreparedStatement registerUserStatement= connection.prepareStatement("UPDATE user_details SET " +
                    "firstName=?, lastName=?, email=?, password=?, address=?" +
                    "WHERE email=? AND category='customer'");



        if(!firstNameEntered.equals("")) {
            registerUserStatement.setString(1, firstNameEntered);
        }else{
            registerUserStatement.setString(1, userDetails.get("firstName"));
        }
        if(!lastNameEntered.equals("")) {
            registerUserStatement.setString(2, lastNameEntered);
        }else{
            registerUserStatement.setString(2, userDetails.get("lastName"));
        }

        if(!emailEntered.equals("")) {
            registerUserStatement.setString(3, emailEntered);
        }else{
            registerUserStatement.setString(3, userDetails.get("email"));
        }

        if(!passwordEntered.equals("")) {
            registerUserStatement.setString(4, passwordEntered);
        } else{
            registerUserStatement.setString(4, userDetails.get("password"));
        }

        if(!addressEntered.equals("")) {
            registerUserStatement.setString(5, addressEntered);
        }else{
            registerUserStatement.setString(5, userDetails.get("address"));
        }
        registerUserStatement.setString(6,userDetails.get("email"));

        registerUserStatement.executeUpdate();

    }

    /**
     * Go back to home screen
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {

        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) email.getScene().getWindow();

        stage.setTitle("Customer Home");
        stage.setScene(scene);
        stage.show();

    }
}
