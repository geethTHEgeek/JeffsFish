package com.jfs.linkedlist;

import com.jfs.model.Purchase;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ProductList {

    private Product firstProduct;

    public ProductList(){
        firstProduct =null;
    }

    public boolean isEmpty(){
        return (firstProduct ==null);
    }

    /**
     * Inserting the first link of the linked list productlist
     * @param productCode
     * @param description
     * @param size
     * @param prize
     * @param quantity
     */
    public void insertFLink(String productCode, String description, String size, double prize,int quantity){
        Product newProduct =new Product(productCode,description,size,prize,quantity);
        newProduct.setNext(firstProduct);
        firstProduct = newProduct;
    }



   public void display(){

        Product theProduct = firstProduct;
        while(theProduct !=null){
            theProduct.display();
            System.out.println("Next Link1 : "+ theProduct.getNext());
            theProduct = theProduct.getNext();
            System.out.println();
        }
    }

    /**
     * Adding purchases to observable list
     * @param list
     * @return
     */
    public ObservableList<Purchase> getValues(ObservableList<Purchase> list){

        Product theProduct = firstProduct;
        Purchase newPurchase;
        while(theProduct !=null){
            newPurchase=new Purchase(theProduct.getDescription(), theProduct.getSize(), theProduct.getQuantity(), theProduct.getPrize());
            list.add(newPurchase);
            theProduct = theProduct.getNext();
        }
   return list;
   }

    public ArrayList<Product> getValuesAsList(){

        Product theProduct = firstProduct;
        ArrayList<Product> products = new ArrayList<>();

        while(theProduct !=null){
            products.add(theProduct);
            theProduct = theProduct.getNext();
        }

        return products;
    }



    public Product findLink(String description, String size){
        Product theProduct = firstProduct;
        if(!isEmpty()){
            while(theProduct.getDescription()!=description && theProduct.getSize()!=size){

                if(theProduct.getNext()==null){
                    return null;
                }else{
                    theProduct = theProduct.getNext();
                }

            }
        }else {
            System.out.println("Empty LinkedList1");
        }

        return theProduct;
    }

    public Product removeLink(String description, String size){

        Product currentProduct = firstProduct;
        Product previousProduct = firstProduct;

        while(currentProduct.getDescription()!=description && currentProduct.getSize()!=size){

            if(currentProduct.getNext()==null){
                return null;
            }else{
                previousProduct = currentProduct;
                currentProduct = currentProduct.getNext();
            }

        }

        if(currentProduct == firstProduct){
            firstProduct = firstProduct.getNext();
        }else{
            previousProduct.setNext(currentProduct.getNext());
        }

        return currentProduct;
    }

    /**
     * finding the price a product
     * @param description
     * @param size
     * @return
     */
    public double findPrice(String description,String size){
        Product newProduct =findLink(description,size);
        return newProduct.getPrize();
    }

    /**
     * Printing the invoice
     * @return
     */
    public String printInvoiceTable(){
        Product theProduct = firstProduct;
        String invoiceTable="";
        int numb=0;
        while(theProduct !=null){
            invoiceTable+="         <tr>\n" +
                    "            <td>"+ ++numb +"</td>\n" +
                    "            <td>"+ theProduct.getDescription()+"</td>\n" +
                    "            <td>"+ theProduct.getProductCode()+"</td>\n" +
                    "            <td>"+ theProduct.getSize()+"</td>\n" +
                    "            <td>"+ theProduct.getPrize()+"</td>\n" +
                    "            <td>"+ theProduct.getQuantity()+"</td>\n" +
                    "            <td>"+ theProduct.itemAmount()+"</td>\n" +
                    "         </tr>\n";

            theProduct = theProduct.getNext();
        }
    return invoiceTable;
    }

public String toString(){
        return "The linked list";
}
}
