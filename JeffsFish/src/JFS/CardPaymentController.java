package JFS;


public class CardPaymentController {
   /* public static Map<String,String> userDetails;

    public static ProductList theProductList;




    public void purchase() {
        // To Do : Email generate and Send

        // TO Do : persist in to DataBase


        DBConnection connectionClass = new DBConnection();
        Connection connection = connectionClass.getConnection();

        try {

            theProductList = ShoppingCart.theProductList;
           PreparedStatement listData = connection.prepareStatement("INSERT INTO list_data (FL100m,FL150m,FL200m,SI50g,SI100g,SI150g,SWS,SWM,SWL,FH4,FH5,FH6,RO1m,RO2m,RO3m,FRS,FRM,FRL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           //PreparedStatement listData = connection.prepareStatement("INSERT INTO list_data (FL100m,FL150m,FL200m,SI50g,SI100g,SI150g,SWS,SWM,SWL,FH#4) VALUES (?,?,?,?,?,?,?,?,?,?)");

            for(int i=1;i<=18;i++){
                listData.setInt(i,0);
            }

            for (Product item: theProductList.getValuesAsList()) {
                if (item.getProductCode().equals("FL")) {
                    if (item.getSize().equals("100m")) {
                        listData.setInt(1, item.getQuantity());
                    } else if (item.getSize().equals("150m")) {
                        listData.setInt(2, item.getQuantity());
                    } else {
                        listData.setInt(3, item.getQuantity());
                    }
                } else if (item.getProductCode().equals("SI")) {
                    if (item.getSize().equals("50g")) {
                        listData.setInt(4, item.getQuantity());
                    } else if (item.getSize().equals("100g")) {
                        listData.setInt(5, item.getQuantity());
                    } else {
                        listData.setInt(6, item.getQuantity());
                    }
                } if (item.getProductCode().equals("SW")) {
                    if (item.getSize().equals("S")) {
                        listData.setInt(7,item.getQuantity());
                    } else if (item.getSize().equals("M")) {
                        listData.setInt(8,item.getQuantity());
                    } else {
                        listData.setInt(9,item.getQuantity());
                    }
                }else if (item.getProductCode().equals("FH")) {
                    if (item.getSize().equals("#4")) {
                        listData.setInt(10,item.getQuantity());
                    } else if (item.getSize().equals("#5")) {
                        listData.setInt(11,item.getQuantity());
                    } else {
                        listData.setInt(12,item.getQuantity());
                    }
                }else if (item.getProductCode().equals("RO")) {
                    if (item.getSize().equals("1m")) {
                        listData.setInt(13,item.getQuantity());
                    } else if (item.getSize().equals("2m")) {
                        listData.setInt(14,item.getQuantity());
                    } else {
                        listData.setInt(15,item.getQuantity());
                    }
                } else {
                    if (item.getSize().equals("S")) {
                        listData.setInt(16,item.getQuantity());
                    } else if (item.getSize().equals("M")) {
                        listData.setInt(17,item.getQuantity());
                    } else {
                        listData.setInt(18,item.getQuantity());
                    }
                }

            }
            listData.executeUpdate();


            //getting the last list id
            PreparedStatement getLastListId=connection.prepareStatement("SELECT listId FROM list_data ");
            ResultSet resultSet=getLastListId.executeQuery();

            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            while(resultSet.next()) {
                arrayList.add(resultSet.getInt("listId"));
            }

            System.out.println(arrayList);
            int lastListId=arrayList.get(arrayList.size()-1);
            System.out.println(lastListId);


            //inserting values for receipts table
            PreparedStatement receiptData = connection.prepareStatement("" +
                    "INSERT INTO receipts(date,total,listId) " +
                    "VALUES(?,?,?)");

            long millis=System.currentTimeMillis();
            Date date=new Date(millis);
            receiptData.setDate(1,date);
            receiptData.setDouble(2,ShoppingCart.totalPrice);
            receiptData.setInt(3,lastListId);

            //updating the receipts table
            receiptData.executeUpdate();

            //getting the last receipt id
            PreparedStatement getLastReceiptId=connection.prepareStatement("SELECT receiptId FROM receipts");
            ResultSet resultSet2=getLastReceiptId.executeQuery();
            ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
            while(resultSet2.next()) {
                arrayList2.add(resultSet2.getInt("receiptId"));
            }

            System.out.println(arrayList2);
            int lastReceiptId=arrayList2.get(arrayList2.size()-1);

            //inserting values for purchases table
            PreparedStatement purchasesData = connection.prepareStatement("" +
                    "INSERT INTO purchase(userId,receiptId) " +
                    "VALUES(?,?)");
            purchasesData.setInt(1, Integer.parseInt(ShoppingCart.userDetails.get("userId")));
            purchasesData.setInt(2,lastReceiptId);

           int updateResult =  purchasesData.executeUpdate();

           if(updateResult == 1){
               Receipt receipt=new Receipt(lastReceiptId,date,ShoppingCart.totalPrice,lastListId);

               EmailService emailService = new EmailService();
               //String invoice = "<h1>Hello,"+userDetails.get("firstName")+"</h1> \n <p> Your Card Number is"+cardNumber.getText()+"</p>";

              Invoice invoice=new Invoice(userDetails,theProductList,receipt);

               String printedInvoice=invoice.printInvoice();
               emailService.sendEmail(printedInvoice,"gvkodikara1@gmail.com");

               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Purchase Success");
               alert.setHeaderText(null);
               alert.setContentText(receipt.printReceipt());

               Optional<ButtonType> result = alert.showAndWait();

               if (result.get() == ButtonType.OK) {

                   Parent root = null;
                   try {
                       URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
                       root = FXMLLoader.load(url);
                   } catch (IOException e) {

                   }

                   Scene scene = new Scene(root);
                   Stage stage = new Stage();

                   stage.setTitle("Card Payment Screen");
                   stage.setScene(scene);
                   stage.show();

               }

           }

        } catch (SQLException e) {
            e.printStackTrace();
            //Log
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                //Log
            }
        }





    }

    public String getInvoice(){
        String invoice="<h2 style=\"text-align:center\">Jeff's Fishing Shack</h2>\n" +
                "      <h3 style=\"text-align:center\">Tax Invoice</h3>\n" +
                "      <p>\n" +
                "         Jeff’s Fishing Shack<br>\n" +
                "         Trading as: Octopus Pty Ltd<br>\n" +
                "         Shop 4, Hillarys Boat Harbour<br>\n" +
                "         Hillarys, WA, 6025<br>\n" +
                "         T: 08 9402 2222<br>\n" +
                "         Email:gvkodikara1@gmail.com<br><br>\n" +
                "      </p>\n" +
                "      <span>Receipt#:10412</span>\n" +
                "      <span style=\"float:right;\">Date:02/08/2018</span>\n" +
                "      <p>\n" +
                "         Customer:\tA Fisherman\t<br>\t\n" +
                "         1, Shark Loop\t<br>\t\t\n" +
                "         Mindarie\t<br>\t\t\t\n" +
                "         W.A. 6027\t<br><br>\t\t\t\t\n" +
                "         Customer#:\t0367\t<br>\t\t\n" +
                "         Customer email: AFish@gmail.com<br>\t\n" +
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
                "         </tr>\n" +
                "         <tr>\n" +
                "            <td>1</td>\n" +
                "            <td>Fish Hooks</td>\n" +
                "            <td>FH</td>\n" +
                "            <td>#6</td>\n" +
                "            <td>1</td>\n" +
                "            <td>10</td>\n" +
                "            <td>10</td>\n" +
                "         </tr>\n" +
                "         <tr>\n" +
                "            <td>2</td>\n" +
                "            <td>Fish Reel</td>\n" +
                "            <td>FR</td>\n" +
                "            <td>100m</td>\n" +
                "            <td>2.5</td>\n" +
                "            <td>2</td>\n" +
                "            <td>5</td>\n" +
                "         </tr>\n" +
                "      </table>\n" +
                "      <p style=\"text-align:center;\">Total Paid: $160<br><br>\n" +
                "         Thank you for your business.<br>\n" +
                "         Jeff’s - where the real fishermen shop.\n" +
                "      </p>";
        return invoice;
    }
*/
}
