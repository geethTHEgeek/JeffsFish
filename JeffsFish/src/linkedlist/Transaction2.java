package linkedlist;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



public class Transaction2 {

    private SimpleIntegerProperty receiptId;
    private SimpleIntegerProperty userId;
    private SimpleDoubleProperty totalAmount;
    private SimpleStringProperty date;
    private Transaction2 next;

    public int getReceiptId() {
        return receiptId.get();
    }

    public SimpleIntegerProperty receiptIdProperty() {
        return receiptId;
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public SimpleDoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public Transaction2(int receiptId, int userId, double totalAmount, String date) {
        this.receiptId = new SimpleIntegerProperty(receiptId);
        this.userId =  new SimpleIntegerProperty(userId);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
        this.date = new SimpleStringProperty(date);
    }



    public Transaction2 getNext() {
        return next;
    }

    public void setNext(Transaction2 next) {
        this.next = next;
    }



    public String toString(){

        return ""+getReceiptId();
    }


}
