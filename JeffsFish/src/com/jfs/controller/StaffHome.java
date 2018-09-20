package com.jfs.controller;


import com.jfs.model.Transaction;
import com.jfs.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.ArrayList;

public class StaffHome {

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
    private ObservableList<Transaction> currentTransaction;

    /**
     * Send Staff to view Current Transaction
     */
    public void viewCurrentTransaction(){

        int givenId=Integer.parseInt(customerId.getText());

        if(hasValidId(givenId)){

            Transaction transaction =null;
            transactions= FXCollections.observableArrayList();

            DBConnection connectionClass=new DBConnection();
            Connection connection=connectionClass.getConnection();

            int transactionCount=0;

            long millis=System.currentTimeMillis();
            Date todaysDate=new Date(millis);

            Statement stmt= null;
            ResultSet rst= null;
            try {
                stmt = connection.createStatement();
                String querry="SELECT * FROM transaction_log WHERE userId="+givenId+" AND date="+"'"+todaysDate+"'";
                rst = stmt.executeQuery(querry);
                while(rst.next()) {
                    int rcptId=rst.getInt("receiptId");
                    int usrId=rst.getInt("userId");
                    double totAmount=rst.getDouble("totalPaid");
                    Date theDate=rst.getDate("date");

                    transaction =new Transaction(rcptId,usrId,totAmount,theDate);
                    transactions.add(transaction);
                    transactionCount++;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            receiptId.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("receiptId"));
            totalPaid.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("totalAmount"));
            date.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

            Transaction tr=transactions.get(transactionCount-1);
            currentTransaction= FXCollections.observableArrayList();
            currentTransaction.add(tr);
            table.setItems(currentTransaction);

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Customer ID");
            alert.setHeaderText(null);
            alert.setContentText("This customer has not purchased or is not registered");
            alert.showAndWait();
        }

    }

    /**
     * Validate Staff ID
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
        int[] a=new int[idList.size()];

        for(int i=0;i<idList.size();i++){
            a[i]=idList.get(i);
        }

        sort(a);

        int searchValue=search(a,0,a.length-1,givenId);

        if(searchValue>=0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Search a list
     * @param a
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int search(int[] a,int low,int high,int value){

        if(low<=high){
            int mid=(low+high)/2;

            if(a[mid]==value){
                return mid;
            }
            else if(a[mid]<value){
                return search(a,mid+1,high,value);
            }
            else{
                return search(a,low,mid-1,value);
            }
        }
        else {
            return -1;
        }
    }

    /**
     * sort a list
     * @param a
     */
    public static void sort(int[] a){

        for(int i=0;i<a.length-1;i++){
            int minPos=minimumPosition(a,i);
            swap(a,minPos,i);
        }


    }

    /**
     * get the mimimum position
     * @param a
     * @param from
     * @return
     */
    public static int minimumPosition(int[] a,int from){

        int minPos=from;
        for(int i=from+1;i<a.length;i++){
            if(a[i]<a[minPos]){minPos=i;}
        }
        return minPos;
    }

    /**
     * Swap a list
     * @param a
     * @param i
     * @param j
     */
    public  static void swap(int[] a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }




}