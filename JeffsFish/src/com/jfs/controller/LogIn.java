package com.jfs.controller;

import com.jfs.model.Admin;
import com.jfs.model.Customer;
import com.jfs.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class LogIn {

@FXML
private Label errLbl;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUpButton;

    @FXML
    private Label label;
    public static String user;

    private Customer customer;


    /**
     * send customer to signup screen
     */
     public void signUpAction(){

         Parent root = null;
         try {
             URL url = new File("src/com/jfs/ui/signup.fxml").toURL();
             root = FXMLLoader.load(url);
         } catch (IOException e) {
             e.printStackTrace();
         }

         Scene scene = new Scene(root);
         Stage stage = (Stage) signUpButton.getScene().getWindow();
         stage.setTitle("SignUp Screen");
         stage.setScene(scene);
         stage.show();
     }

    /**
     * Perform login method
     * @throws SQLException
     * @throws IOException
     */

    public void logInAction() throws SQLException, IOException {

         String categoryName="";

        DBConnection connectionClass=new DBConnection();
        Connection connection=connectionClass.getConnection();

        String userNameEntered = userName.getText();
        String passwordEntered = password.getText();

        PreparedStatement getCredentials = connection.prepareStatement("SELECT * FROM user_details where email=? and password=?");
        getCredentials.setString(1,userNameEntered);
        getCredentials.setString(2,passwordEntered);


        ResultSet resultSet = getCredentials.executeQuery();

        Map<String,String> userDetails = new HashMap<String,String>();


        if(resultSet.next()){
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String address = resultSet.getString("address");
            String category = resultSet.getString("category");
            Integer id = resultSet.getInt("id");

            userDetails.put("email",email);
            userDetails.put("firstName",firstName);
            userDetails.put("userId",id.toString());
            userDetails.put("lastName",lastName);
            userDetails.put("address",address);
            userDetails.put("category",category);
            userDetails.put("password",passwordEntered);

            if(category.equals("customer")){

                CustomerHome.userDetails=userDetails;

                Parent root = null;
                try {
                    URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = (Stage) password.getScene().getWindow();
                stage.setTitle("Customer Home Screen");
                stage.setScene(scene);
                stage.show();



            }else if (category.equals("staff")){

                Parent root = null;
                try {
                    URL url = new File("src/com/jfs/ui/staff_home.fxml").toURL();
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                   e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = (Stage) password.getScene().getWindow();
                stage.setTitle("Staff Home Screen");
                stage.setScene(scene);
                stage.show();
            }else{

                Admin admin=new Admin();
                admin.setFirstName("Jeff");
                AdminHome.admin=admin;
                Parent root = null;
                try {
                    URL url = new File("src/com/jfs/ui/admin_home.fxml").toURL();
                    root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) password.getScene().getWindow();
                    stage.setTitle("Admin Home Screen");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }

        }else{
            errLbl.setText("Invalid user name or password");
            System.out.println("Log invalid");
        }

    }

}
