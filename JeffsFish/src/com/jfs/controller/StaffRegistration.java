package com.jfs.controller;



import com.jfs.model.Staff;
import com.jfs.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class StaffRegistration {

    @FXML
private TextField firstName;
    @FXML
private TextField lastName;
    @FXML
private TextField email;
    @FXML
private PasswordField password;

    private Staff staff;

    /**
     * Register the staff
     * @throws SQLException
     */
    public void registerStaff() throws SQLException {

        String firstNameEntered = firstName.getText();
        String lastNameEntered = lastName.getText();
        String emailEntered = email.getText();
        String passwordEntered = password.getText();

        staff=new Staff();
        if(!(firstNameEntered.equals("") || lastNameEntered.equals("") || emailEntered.equals("") || passwordEntered.equals(""))) {

            if (staff.hasValidPassword(passwordEntered)) {

                DBConnection connectionClass = new DBConnection();
                Connection connection = connectionClass.getConnection();


                ArrayList<Integer> idList = new ArrayList<>();
                ArrayList<String> emailList = new ArrayList<>();
                //get the last staff id
                Statement stmt = null;
                ResultSet rst = null;
                try {
                    stmt = connection.createStatement();
                    String querry = "SELECT * FROM user_details WHERE category='staff' ";
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
                if (emailValid) {

                    PreparedStatement registerUserStatement = connection.prepareStatement("INSERT INTO user_details VALUES(?,?,?,?,?,?,?)");

                    registerUserStatement.setInt(1, lastListId + 1);
                    registerUserStatement.setString(2, firstNameEntered);
                    registerUserStatement.setString(3, lastNameEntered);
                    registerUserStatement.setString(4, emailEntered);
                    registerUserStatement.setString(5, passwordEntered);
                    registerUserStatement.setString(6,null);
                    registerUserStatement.setString(7, "staff");


                    try {
                        registerUserStatement.executeUpdate();
                        connection.close();
                        System.out.println("registration successful");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Registration Successful");
                        alert.setHeaderText(null);
                        alert.setContentText("A new Staff member was Registered");
                        alert.showAndWait();


                        firstName.setText("");
                        lastName.setText("");
                        email.setText("");
                        password.setText("");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sign up Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Email already in use");
                    alert.showAndWait();
                }



            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign up Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid password");
                alert.showAndWait();
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
     * Go back to admin home screen
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/admin_home.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {

        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) firstName.getScene().getWindow();

        stage.setTitle("Customer Home");
        stage.setScene(scene);
        stage.show();

    }
}
