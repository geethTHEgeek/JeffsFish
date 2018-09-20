package com.jfs.model;

import com.jfs.linkedlist.ProductList;

import java.util.Map;

public class Invoice {
    private Map<String,String> userDetails;
    private ProductList purchaseList;
    private Receipt receipt;

    public Invoice(Map<String, String> userDetails, ProductList purchaseList, Receipt receipt) {
        this.userDetails = userDetails;
        this.purchaseList = purchaseList;
        this.receipt = receipt;
    }

    /**
     * Print Invoide Method
     * @return invoice
     */
    public String printInvoice(){
            String invoice="<h2 style=\"text-align:center\">Jeff's Fishing Shack</h2>\n" +
                    "      <h3 style=\"text-align:center\">Tax Invoice</h3>\n" +
                    "      <p>\n" +
                    "         Jeff’s Fishing Shack<br>\n" +
                    "         Trading as: Octopus Pty Ltd<br>\n" +
                    "         Shop 4, Hillarys Boat Harbour<br>\n" +
                    "         Hillarys, WA, 6025<br>\n" +
                    "         T: 08 9402 2222<br>\n" +
                    "         Email:sadeshkasm@gmail.com<br><br>\n" +
                    "      </p>\n" +
                    "      <span>Receipt#:"+receipt.getReceiptId()+"</span>\n" +
                    "      <span style=\"float:right;\">Date:"+receipt.getDate()+"</span>\n" +
                    "      <p>\n" +
                    "         Customer:\t"+userDetails.get("firstName")+" "+userDetails.get("lastName")+"\t<br>\t\n" +
                    "         "+userDetails.get("address")+"\t<br><br>\t\t\t\t\n" +
                    "         Customer#:\t"+userDetails.get("userId")+"\t<br>\t\t\n" +
                    "         Customer email:"+userDetails.get("email")+"<br>\t\n" +
                    "      </p>\n" +
                    "      <h3>Purchases</h3>\n" +
                    "      <table>\n" +
                    "         <tr>\n" +
                    "            <th>No.</th>\n" +
                    "            <th>Description</th>\n" +
                    "            <th>Code</th>\n" +
                    "            <th>Size</th>\n" +
                    "            <th>Cost</th>\n" +
                    "            <th>Quantity</th>\n" +
                    "            <th>Amount</th>\n" +
                    "         </tr>\n";

                    invoice+=purchaseList.printInvoiceTable();
                    invoice+="      </table>\n" +
                    "      <p style=\"text-align:center;\">Total Paid: $"+receipt.getTotalAmount()+"<br><br>\n" +
                    "         Thank you for your business.<br>\n" +
                    "         Jeff’s - where the real fishermen shop.\n" +
                    "      </p>";
            return invoice;
        }





}
