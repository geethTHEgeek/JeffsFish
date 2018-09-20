package linkedlist;



public class LinkedList1 {

    private Link1 firstLink1;

    public LinkedList1(){
        firstLink1 =null;
    }

    public boolean isEmpty(){
        return (firstLink1 ==null);
    }

    public void insertFLink(String bookName,int millionsSold){
        Link1 newLink1 =new Link1(bookName, millionsSold);
        newLink1.setNext(firstLink1);
        firstLink1 = newLink1;

    }



    public Link1 removeFirst(){

        Link1 link1Reference = firstLink1;

        if(!(isEmpty())){

            firstLink1 = firstLink1.getNext();

        }else{
            System.out.println("Empty Linked List");
        }

        return link1Reference;

    }

    public void display(){

        Link1 theLink1 = firstLink1;

        while(theLink1 !=null){
            theLink1.display();
            System.out.println("Next Link1 : "+ theLink1.getNext());
            theLink1 = theLink1.getNext();
            System.out.println();
        }
    }

    public Link1 findLink(String bookName){
        Link1 theLink1 = firstLink1;
        if(!isEmpty()){
            while(theLink1.getBookName()!=bookName){

                if(theLink1.getNext()==null){
                    return null;
                }else{
                    theLink1 = theLink1.getNext();
                }


            }

        }else {
            System.out.println("Empty LinkedList1");
        }

        return theLink1;
    }

public Link1 removeLink(String bookName){

        Link1 currentLink1 = firstLink1;
        Link1 previousLink1 = firstLink1;

        while(currentLink1.getBookName()!=bookName){

            if(currentLink1.getNext()==null){
                return null;
            }else{
                previousLink1 = currentLink1;
                currentLink1 = currentLink1.getNext();
            }

        }

        if(currentLink1 == firstLink1){
           firstLink1 = firstLink1.getNext();
        }else{
            previousLink1.setNext(currentLink1.getNext());
        }

        return currentLink1;
}


}
