package bookstore;

import javax.swing.*;

public class Customer {


    private String first;
    private String last;
    private String email;

    public static DefaultListModel<Book> customerRentedModel = new DefaultListModel<Book>();
    public static JList customerRentedJList = new JList(customerRentedModel);



    public Customer(String last, String first, String email){

       this.first = first;
       this.last = last;
       this.email = email;

    }

    public String getFirst(){
        return this.first;
    }

    public String getLast(){
        return this.last;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString(){

        return this.last + ", "+ this.first +" ("+this.email+")";
    }


}
