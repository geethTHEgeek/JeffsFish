package com.jfs.controller;

import com.jfs.model.Transaction;
import com.jfs.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class CustomerReport {

    @FXML
    private TableView<Transaction> table;

    @FXML
    private TableColumn<Transaction,Integer> receiptId;

    @FXML
    private TableColumn<Transaction,Double> totalPaid;

    @FXML
    private  TableColumn<Transaction,String> date;

    @FXML
    private TextField customerId;

    private ObservableList<Transaction> transactions;

    public void runReport(){

        int givenId=Integer.parseInt(customerId.getText());

        if(hasValidId(givenId)){

            Transaction transaction =null;
            transactions= FXCollections.observableArrayList();

            DBConnection connectionClass=new DBConnection();
            Connection connection=connectionClass.getConnection();

            long millis=System.currentTimeMillis();
            Date todaysDate=new Date(millis);

            Statement stmt= null;
            ResultSet rst= null;
            try {
                stmt = connection.createStatement();
                String querry="SELECT * FROM transaction_log WHERE userId="+"'"+givenId+"'";
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

            //populating the table view
            receiptId.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("receiptId"));
            totalPaid.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("totalAmount"));
            date.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

            table.setItems(transactions);


        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Customer ID");
            alert.setHeaderText(null);
            alert.setContentText("This customer has not purchased or is not registered");
            alert.showAndWait();
        }



    }

    /**
     * Check for validity of customer id
     * @param givenId
     * @return
     */
    public boolean hasValidId(int givenId){

        DBConnection connectionClass=new DBConnection();
        Connection connection=connectionClass.getConnection();

        ArrayList<Integer> idList= new ArrayList<>();

        Statement stmt= null;
        ResultSet rst= null;
        try {
            stmt = connection.createStatement();
            String querry="SELECT userId FROM transaction_log";
            rst = stmt.executeQuery(querry);
            while(rst.next()) {
                int usrId=rst.getInt("userId");
                idList.add(usrId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int[] arr =new int[idList.size()];

        for(int i=0;i<idList.size();i++){
            arr[i]=idList.get(i);
        }
        sort(arr);
        int searchValue=search(arr,0, arr.length-1,givenId);

        if(searchValue>=0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Search a given list
     * @param arr
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int search(int[] arr,int low,int high,int value){

        if(low<=high){
            int mid=(low+high)/2;

            if(arr[mid]==value){
                return mid;
            }
            else if(arr[mid]<value){
                return search(arr,mid+1,high,value);
            }
            else{
                return search(arr,low,mid-1,value);
            }
        }
        else {
            return -1;
        }
    }

    /**
     * Sort a list given
     * @param arr
     */
    public static void sort(int[] arr){

        for(int i=0;i<arr.length-1;i++){
            int minPos=minimumPosition(arr,i);
            swap(arr,minPos,i);
        }

    }

    /**
     * Get the minimum position
     * @param arr
     * @param from
     * @return
     */
    public static int minimumPosition(int[] arr, int from){

        int minPos=from;
        for(int i = from+1; i< arr.length; i++){
            if(arr[i]< arr[minPos]){minPos=i;}
        }
        return minPos;
    }

    /**
     * Swap a list
     * @param arr
     * @param i
     * @param j
     */
    public  static void swap(int[] arr, int i, int j){
        int temp= arr[i];
        arr[i]= arr[j];
        arr[j]=temp;
    }


    /**
     * Send customer back to admin home
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

        stage.setTitle("Admin Home");
        stage.setScene(scene);
        stage.show();

    }

}
