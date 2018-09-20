package linkedlist;

import javafx.collections.ObservableList;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class TransactionList {

    private Transaction2 firstLink;

    public TransactionList(){
        firstLink =null;
    }

    public boolean isEmpty(){
        return (firstLink ==null);
    }

    public void insertFLink(int receiptId,int userId,double totalAmount,Date date){

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy:MM:dd");
        String currentDate = dateformat.format(date);
        Transaction2 newLink =new Transaction2(receiptId,userId,totalAmount,currentDate);
        newLink.setNext(firstLink);
        firstLink = newLink;
    }

    public ObservableList<Transaction2> getTransactions(ObservableList<Transaction2> transactionsList){

        Transaction2 theLink = firstLink;
        while(theLink !=null){

            System.out.println("firstLink"+theLink);
            System.out.println("nextLink"+theLink.getNext());

            transactionsList.add(theLink.getNext());
            theLink = theLink.getNext();
        }
        return transactionsList;
    }


    public Transaction2 findLink(int userId){
        Transaction2 theLink = firstLink;
        if(!isEmpty()){
            while(theLink.getUserId()!=userId){

                if(theLink.getNext()==null){
                    return null;
                }else{
                    theLink = theLink.getNext();
                }


            }

        }else {
            System.out.println("Empty ProductList");
        }

        return theLink;
    }


}
