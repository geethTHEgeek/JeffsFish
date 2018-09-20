package com.jfs.controller;

import com.jfs.model.Customer;
import com.jfs.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class SignUp {

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private Label passLbl1;

    @FXML
    private Label passLbl2;

    @FXML
    private Label emailValidityLbl;

    private Customer customer;

    /**
     * Register the customer
     * @throws SQLException
     */

    public void signUpAction() throws SQLException {

        //getting the input values
        String firstName = fName.getText();
        String lastName = lName.getText();
        String emailEntered = email.getText();
        String passwordEntered = passwordField1.getText();
        String addressEntered = address.getText();

         customer=new Customer();

        //validating if all the input fields were filled
        if(!(firstName.equals("") || lastName.equals("") || emailEntered.equals("") || addressEntered.equals("") || passwordEntered.equals(""))) {

            if (customer.hasValidPassword(passwordEntered)) {

                DBConnection connectionClass = new DBConnection();
                Connection connection = connectionClass.getConnection();


                ArrayList<Integer> idList = new ArrayList<>();
                ArrayList<String> emailList = new ArrayList<>();
                //get the last customer id
                Statement stmt = null;
                ResultSet rst = null;
                try {
                    stmt = connection.createStatement();
                    String querry = "SELECT * FROM user_details WHERE category='customer' ";
                    rst = stmt.executeQuery(querry);
                    while (rst.next()) {
                        String emailTxt = rst.getString("email");
                        int id = rst.getInt("id");
                        emailList.add(emailTxt);
                        idList.add(id);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                boolean emailValid = true;
                //checking whether email entered is unique
                for (int i = 0; i < emailList.size(); i++) {
                    if ((emailList.get(i).equals(emailEntered))) {
                        emailValid = false;
                        break;
                    }
                }

                int lastListId = idList.get(idList.size() - 1);
                int insertedRecords = 0;
                if (emailValid) {

                    PreparedStatement registerUserStatement = connection.prepareStatement("INSERT INTO user_details VALUES(?,?,?,?,?,?,?)");

                    registerUserStatement.setInt(1, lastListId + 1);
                    registerUserStatement.setString(2, firstName);
                    registerUserStatement.setString(3, lastName);
                    registerUserStatement.setString(4, emailEntered);
                    registerUserStatement.setString(5, passwordEntered);
                    registerUserStatement.setString(6, addressEntered);
                    registerUserStatement.setString(7, "customer");


                    try {
                        insertedRecords = registerUserStatement.executeUpdate();

                        connection.close();
                    } catch (Exception e) {
                        //TO Do
                    }
                } else {
                    emailValidityLbl.setText("This email is already in use");
                }

                if (insertedRecords == 1) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sign up Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Thank You! For Signing up. Please enjoy our sevices");
                    alert.showAndWait();

                    //displaying log in screen
                    Parent root = null;
                    try {
                        URL url = new File("src/com/jfs/ui/Log_In.fxml").toURL();
                        root = FXMLLoader.load(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) passwordField1.getScene().getWindow();
                    stage.setTitle("Log In Screen");
                    stage.setScene(scene);
                    stage.show();


                } else {
                    // TO Do
                }

            } else {
                passLbl1.setText("Please Enter a password with password  eight characters or more");
                passLbl2.setText("and at least two non-alphabetic characters");
            }

        }else{

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign up Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill all the fields to Sign up");
            alert.showAndWait();
        }


    }

    /**
     * Go back to login
     */
    public void backToLogIn(){
        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/Log_In.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) passwordField1.getScene().getWindow();
        stage.setTitle("Log In Screen");
        stage.setScene(scene);
        stage.show();
    }


}
