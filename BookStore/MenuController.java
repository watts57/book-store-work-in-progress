package bookstore;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuController {
    // THE LIST MODELS CANNOT BE SWAPPED INTERCHANGABLY BETWEEEN CUSTOMER AND BOOK LISTS.
    // NEED 2 SEPERATE AREAS TO VIEW CUSTOMERS OR BOOKS (rented/available)

    //Helpful guide I referenced a few times:
    //https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/

    public static int yearInt = -1;
    public static int pageInt = -1;
    public static Customer selectedCustomer = null;
    public static Customer resultCustomer = null;
    public static Book selectedBook = null;
    public static Book returnedBook = null;
    public static Book highlightedBook = null;

    public static JList customerJListAlias = null;
    public static JList availableJListAlias = null;
    public static JList rentedJListAlias = null;

    //depending on user input, one of these 3 will be shown in jsplit pane components
    public static DefaultListModel<Book> availableModel = new DefaultListModel<Book>();
    public static DefaultListModel<Book> rentedModel = new DefaultListModel<Book>();
    public static DefaultListModel<Customer> customerModel = new DefaultListModel<Customer>();
    ;
    public static JList customerJList = new JList<>(customerModel);
    public static JList  availableJList = new JList<>(availableModel);
    public static JList rentedJList = new JList<>(rentedModel);
    //set JList models





    ///THIS FUNCTION RENTS A BOOK
    public static void rentSelectedBookForSelectedCustomer(Customer selectedCustomer, Book selectedBook){

    selectedCustomer.customerRentedModel.addElement(selectedBook);
    rentedModel.addElement(selectedBook);
    availableModel.removeElement(selectedBook);

    }

    // THIS FUNCTION RETURNS A BOOK
    public static void returnSelectedBookForSelectedCustomer(Customer selectedCustomer, Book returnedBook){

        selectedCustomer.customerRentedModel.removeElement(returnedBook);
        availableModel.addElement(returnedBook);
        rentedModel.removeElement(returnedBook);



    }

    public static void addNewCustomer(){
        JFrame addCustomerFrame = new JFrame();
        addCustomerFrame.setVisible(true);
        addCustomerFrame.setSize(800,600);
        JPanel addCustomerPanel = new JPanel();
        addCustomerPanel.setLayout(new BoxLayout(addCustomerPanel, BoxLayout.PAGE_AXIS));
        addCustomerPanel.setVisible(true);


        JTextField newFirstJTextField = new JTextField("Input First Name");
        JTextField newLastJTextField = new JTextField("Input Last Name");
        JTextField newEmailJTextField = new JTextField("Input Email");
        JButton createNewCustomer = new JButton( "Create New Customer");
        JButton closeNewCustomerWindow = new JButton("Done(go back)");

        String newFirst = newFirstJTextField.getText();
        String newLast = newLastJTextField.getText();
        String newEmail = newEmailJTextField.getText();


        createNewCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer newCustomer = new Customer(newLast,newFirst,newEmail);

                customerModel.addElement(newCustomer);
                addCustomerFrame.dispose();

            }
        });

        closeNewCustomerWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomerFrame.dispose();

            }
        });

        addCustomerPanel.add(newFirstJTextField);
        addCustomerPanel.add(newLastJTextField);
        addCustomerPanel.add(newEmailJTextField);
        addCustomerFrame.add(addCustomerPanel);
        addCustomerPanel.add(createNewCustomer);
        addCustomerPanel.add(closeNewCustomerWindow);

    }

    //This Function shows the books rented by currently selected customer, and gives the option to return books
    public static void showBooksRentedByCustomer(Customer selectedCustomer) {

        JFrame customerAccountInfoFrame = new JFrame();
        String customerInfoString = selectedCustomer.getFirst() + selectedCustomer.getLast() + "\n" + selectedCustomer.getEmail();
        JList customerRentedBooks = new JList<>(selectedCustomer.customerRentedModel);

        JPanel customerAccountInfoPanel = new JPanel();// shows name, email

        customerAccountInfoFrame.add(customerAccountInfoPanel);
        customerAccountInfoFrame.setVisible(true);
        customerAccountInfoFrame.setSize(800, 600);
        JPanel customerCurrentRentalPanel = new JPanel(); // holds JList
        JLabel customerJLabel = new JLabel(customerInfoString);   
        customerAccountInfoPanel.add(customerJLabel); // shows name, email, rented books
        JLabel booksRentedByIndividual = new JLabel("\nBooks Rented by this Customer:\n");
        customerAccountInfoPanel.add(booksRentedByIndividual);
        customerAccountInfoPanel.add(customerRentedBooks);

        customerAccountInfoPanel.setLayout(new BoxLayout(customerAccountInfoPanel, BoxLayout.PAGE_AXIS));

        JPanel contextualButtonPanel = new JPanel(); // buttons for this task








        customerAccountInfoPanel.add(customerCurrentRentalPanel); //nested in account info panel
        customerAccountInfoPanel.add(contextualButtonPanel); // holds buttons relevant to this task

        customerAccountInfoPanel.setVisible(true);
        customerCurrentRentalPanel.setVisible(true);
        contextualButtonPanel.setVisible(true);

        customerRentedBooks.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent pickReturn) {

                ///
                if (customerRentedBooks.getValueIsAdjusting() == false) {
                    int chosenReturnIndex = customerRentedBooks.getSelectedIndex();
                    Object chosenReturn = customerRentedBooks.getSelectedValue();


                    returnedBook = (Book) chosenReturn;


                }

            }

            ;
        });

        ///
        JButton returnSelectedJButton = new JButton("Return Selected Book");
        JButton closeThisCustomerJButton = new JButton("Cancel & go back (Close this window)");
        contextualButtonPanel.add(returnSelectedJButton);
        contextualButtonPanel.add(closeThisCustomerJButton);

        closeThisCustomerJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerAccountInfoFrame.dispose();

            }
        });

        returnSelectedJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 returnSelectedBookForSelectedCustomer(selectedCustomer, returnedBook);

            }
        });
   

    }


    public static void addNewBook(){



        //JTEXTFIELD FOR TEXT ENTRY --
        // see https://www.javatpoint.com/java-jtextfield and oracle docs

        JTextField titleField = new JTextField("Input title");
        JTextField authorFirstField = new JTextField("Input Author First Name");
        JTextField authorLastField = new JTextField("Input Author Last Name");
        JTextField publisherField = new JTextField("Input Publisher Name");
        JTextField yearField = new JTextField("Input Year Published");
        JTextField pageField = new JTextField("Input number of Pages");

        String insertTitle = titleField.getText();
        String insertAuthorLast = authorLastField.getText();
        String insertAuthorFirst = authorFirstField.getText();
        String insertPublisher = publisherField.getText();
        String insertYearString = yearField.getText();
        String insertPageString = pageField.getText();





        JButton addThisBookJButton = new JButton("Add New Book to Inventory & Go Back (close this window)");

        JFrame addNewBookFrame = new JFrame();
        JPanel addNewBookPanel = new JPanel();

        addNewBookFrame.add(addNewBookPanel);

        addNewBookPanel.add(titleField);
        addNewBookPanel.add(authorFirstField);
        addNewBookPanel.add(authorLastField);
        addNewBookPanel.add(publisherField);
        addNewBookPanel.add(yearField);
        addNewBookPanel.add(pageField);
        addNewBookPanel.add(addThisBookJButton);




        addNewBookPanel.setLayout(new BoxLayout(addNewBookPanel, BoxLayout.PAGE_AXIS));
        addNewBookFrame.setVisible(true);
        addNewBookPanel.setVisible(true);
        addNewBookFrame.setSize(800, 600);

        addThisBookJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book newBook = new Book(insertTitle, insertAuthorLast, insertAuthorFirst, insertPublisher, insertYearString, insertPageString);
                availableModel.addElement(newBook);

                addNewBookFrame.dispose();
            }
        });


    }




    public static void showInventoryEditor(){
        JFrame inventoryFrame = new JFrame("View/ Edit Inventory");


        inventoryFrame.setVisible(true);
        inventoryFrame.setSize(800, 600);


        JButton closeInventory = new JButton("Done (close this window)");
        closeInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryFrame.dispose();
            }
        });

        JButton newBook = new JButton("Add a Book to Inventory");



        newBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewBook();



            }
        });


        JButton removeBook = new JButton("Remove a book from Inventory");
        removeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int highlightedIndex = availableJList.getSelectedIndex();
                 highlightedBook = availableModel.getElementAt(highlightedIndex);

                 availableModel.removeElement(highlightedBook);
            }
        });


        JLabel availableInventoryJLabel = new JLabel("Available Books");
        JLabel rentedInventoryJLabel = new JLabel("Books Currently Rented");

        JPanel mainInventoryPanel = new JPanel();
        JPanel inventoryButtonPanel = new JPanel();
        JPanel inventoryAvailablePanel = new JPanel();
        JPanel inventoryRentedPanel = new JPanel();

        inventoryAvailablePanel.add(availableInventoryJLabel);
        mainInventoryPanel.add(inventoryAvailablePanel);

        inventoryButtonPanel.add(newBook);
        inventoryButtonPanel.add(removeBook);
        inventoryButtonPanel.add(closeInventory);

        inventoryAvailablePanel.add(availableJList);
        inventoryAvailablePanel.add(inventoryButtonPanel);
        inventoryAvailablePanel.setLayout(new BoxLayout(inventoryAvailablePanel, BoxLayout.PAGE_AXIS));


        inventoryFrame.add(mainInventoryPanel);






    }


    public static void showFirstMenu(){



        //JFrame
        JFrame rentCustomerFrame = new JFrame("Select the Customer and the Book they'd like to Rent"); //FRAME
        rentCustomerFrame.setVisible(true); //MAKE VISIBLE (FRAME)

        //JPanel

        //LEFT HALF OF SCREEN (Customers)
        JPanel leftHalfPanel = new JPanel();//JList goes here
        JPanel listPanelLeft = new JPanel();
        JPanel buttonPanelLeft = new JPanel();
        JScrollBar customerScrollBar = new JScrollBar();
        listPanelLeft.setVisible(true);
        listPanelLeft.add(customerJListAlias);
        listPanelLeft.add(customerScrollBar);
        JLabel customerPanelJLabel = new JLabel("Step 1: Select a Customer"); // LABEL AT TOP
        leftHalfPanel.add(customerPanelJLabel);
        leftHalfPanel.add(listPanelLeft);

        //JList of Customers goes below this
        leftHalfPanel.add(listPanelLeft); // I don't know why, but it only works when I use this variable as a stand in for the customerJList. Still learning how scoping works in  Java
        //JButtons that go below

        //see customer account info
        JButton viewCustomerInfoJButton = new JButton("Books Currently Rented by Selected Customer");
        viewCustomerInfoJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent showCustomerRented) {
                showBooksRentedByCustomer(selectedCustomer);


            }
        });




        JButton newCustomerJButton = new JButton("New Customer"); // add a new customer -- call function

        newCustomerJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewCustomer();
            }
        });




        //
        buttonPanelLeft.add(viewCustomerInfoJButton);
        buttonPanelLeft.add(newCustomerJButton);



        buttonPanelLeft.setLayout(new BoxLayout(buttonPanelLeft, BoxLayout.PAGE_AXIS)); // these buttons are arranged vertically
        //buttonPanelLeft.setAlignmentY(buttonPanelLeft.TOP_ALIGNMENT);
        //add the buttons to leftHalfPanel
        leftHalfPanel.add(buttonPanelLeft); //



        //set layout
        leftHalfPanel.setLayout(new BoxLayout(leftHalfPanel, BoxLayout.PAGE_AXIS));



/////////////////////////////////
        //RIGHT HALF OF SCREEN
        JPanel rightHalfPanel = new JPanel();// BUTTONS TO PROCEED
        rightHalfPanel.setLayout(new BoxLayout(rightHalfPanel, BoxLayout.PAGE_AXIS));
        rightHalfPanel.setVisible(true);

        //JButtons
        JButton rentThisBookButton = new JButton("Rent Selected Book For Selected Customer");

        rentThisBookButton.addActionListener(new ActionListener() //THE FOLLOWING EXECUTES WHEN "BEGIN TRANSACTION" IS PRESSED
        {
            public void actionPerformed(ActionEvent rent) {

                if ((selectedCustomer != null) && (selectedBook != null)) {

                    JButton closeSuccessWindow = new JButton("OK");

                    rentSelectedBookForSelectedCustomer(selectedCustomer, selectedBook);

                    String customerString = selectedCustomer.toString();

                    //String bookString = selectedBook.toString();

                    JFrame rentalSuccessfulFrame = new JFrame("Rental Successful");
                    JPanel rentalSuccessfulPanel = new JPanel();
                    JLabel successTextLabel = new JLabel("Rental Successful!");
                    JLabel customerTextLabel = new JLabel(customerString);


                    rentalSuccessfulPanel.add(successTextLabel);
                    rentalSuccessfulPanel.add(customerTextLabel);
                    rentalSuccessfulPanel.add(closeSuccessWindow);

                    closeSuccessWindow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rentalSuccessfulFrame.dispose();

                        }
                    });


                    rentalSuccessfulPanel.setLayout(new BoxLayout(rentalSuccessfulPanel, BoxLayout.PAGE_AXIS));

                    rentalSuccessfulPanel.setVisible(true);
                    rentalSuccessfulFrame.setSize(500, 200);
                    rentalSuccessfulFrame.setVisible(true);
                    rentalSuccessfulFrame.add(rentalSuccessfulPanel);
                }
            }
        });








        JScrollBar availableScrollBar = new JScrollBar();

        JPanel listPanelTopRight = new JPanel();
        listPanelTopRight.add(availableJListAlias);
        listPanelTopRight.add(availableScrollBar);




        rightHalfPanel.add(listPanelTopRight);
        rightHalfPanel.add(rentThisBookButton); //

        JSplitPane transactionSplitPane = new JSplitPane();
        transactionSplitPane.setLeftComponent(leftHalfPanel);
        transactionSplitPane.setRightComponent(rightHalfPanel);
        transactionSplitPane.setLayout(new BoxLayout(transactionSplitPane, BoxLayout.X_AXIS));


        rentCustomerFrame.setSize(1080, 720);
        rentCustomerFrame.add(transactionSplitPane);
    }

    public static void main(String[] args) {


        //Existing Customers and Books (For tests)
        Book book1 = new Book("The Greatest Alien Moments of the Civil War", "Theorist", "Conspiracy", "Your Crazy Uncle's Basement Printing Press", "1990", "7000");
        Book book2 = new Book("Cow Says Moo", "Hawking", "Steven", "Oxford Press", "1997", "14");
        Book book3 = new Book("Earth is Round", "Einstein", "Albert", "Harvard Press", "1935", "154");
        Book book4 = new Book("How to Open a Pickle Jar", "Musk", "Elon", "Tesla Publishing", "2016", "321");
        Book book5 = new Book("The Catcher in the Rye 2: More Rye, More Problems", "Salinger", "J.D", "Little, Brown and Company","1952", "700");
        Book book6 = new Book("Guide to Time Travel", "Smith", "Robert", "Dyson Sphere Books", "3894", "615");



        Customer customer1 = new Customer("Johnson", "Bob", "bobjohnson@geocities.biz");
        Customer customer2 = new Customer("Cage", "Nicholas", "notthebees@aol.com");
        Customer customer3 = new Customer("Jackson", "Samuel L.", "tiredofthesesnakes@yahoo.com");
        Customer customer4 = new Customer("Christmas", "Lloyd", "biggulpshuh@hotmail.com");
        Customer customer5 = new Customer("Ackbar", "Admiral", "itsatrap@geocities.biz");
        // the actual lists of available books, rented books, customers

        ////////////////////////////////
        ///////////////////////////////
        //// JLISTS, LISTMODELS, MENU STUFF WITH BOOK STORE DATA




        /// SETTING UP JLISTS








        //adding pre-existing books to available listmodel
        availableModel.addElement(book1);
        availableModel.addElement(book2);
        availableModel.addElement(book3);
        availableModel.addElement(book4);
        availableModel.addElement(book5);
        availableModel.addElement(book6);

////////////////
        customerModel.addElement(customer1);
        customerModel.addElement(customer2);
        customerModel.addElement(customer3);
        customerModel.addElement(customer4);
        customerModel.addElement(customer5);

        // add pre-existing customers a to listmodel
        customerJList.setModel(customerModel); //will display the relevant list

///////////////////////////
        //LISTENER FOR JList GUI Items (currentModel)

        customerJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {



            }
        });

        availableJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        rentedJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });




/////////////////////////
            //MAIN METHOD VARIABLES, ETC,

            JFrame frame = new JFrame("Mom & Pop Books, LLC; Point of Sale");
            JPanel panel = new JPanel(); // holds other panels? CHECK TO SEE WHAT THIS DOES

            //First Menu (Begin Transaction, View Inventory, New Customer)
            JButton rentButton = new JButton("Begin Transaction");

            JButton inventoryButton = new JButton("View/Edit Inventory");
            inventoryButton.addActionListener(new ActionListener() {
             @Override
              public void actionPerformed(ActionEvent e) {

                 showInventoryEditor();

               }
              });
            JButton showAllBooksRentedAvailable = new JButton("View all Books (rented and in stock)");
            showAllBooksRentedAvailable.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int totalSize = availableModel.getSize() + rentedModel.getSize();
                    DefaultListModel<Book> allBooksRentedAndAvailable = new DefaultListModel<Book>();
                    JList JListAllBooksAvailableAndRented = new JList<>(allBooksRentedAndAvailable);
                    for (int i = 0; i < availableModel.getSize(); i++) {
                        Book availAdd = availableModel.getElementAt(i);
                        allBooksRentedAndAvailable.addElement(availAdd);
                    }

                    for (int i = 0; i < rentedModel.getSize(); i++) {
                        Book availAdd2 = rentedModel.getElementAt(i);
                        allBooksRentedAndAvailable.addElement(availAdd2);
                }

                    JFrame showAllBooks = new JFrame("Search Books");
                    JPanel allBooksPanel = new JPanel();



                    allBooksPanel.add(JListAllBooksAvailableAndRented);
                    allBooksPanel.setVisible(true);
                    showAllBooks.setVisible(true);
                    showAllBooks.setSize(800,600);
                    allBooksPanel.setLayout(new BoxLayout(allBooksPanel, BoxLayout.PAGE_AXIS));

                    showAllBooks.add(allBooksPanel);


                }
            });


                    //////////// second menu (select a customer who wants to rent a book)
            JPanel transactionPanel = new JPanel();
            JPanel customerPanel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS )); //SETS ROWS OF BUTTONS APPROPRIATELY (left pane)


        // ADD PANELS TO LEFT SIDE OF SPLIT PANE

        panel.add(transactionPanel);


            rentButton.addActionListener(new ActionListener() //THE FOLLOWING EXECUTES WHEN "BEGIN TRANSACTION" IS PRESSED
        {
            public void actionPerformed(ActionEvent e) {
                //DISPLAY CUSTOMER LIST --WHO IS RENTING A BOOK?

                customerJListAlias = customerJList;
                availableJListAlias = availableJList;
                rentedJListAlias = rentedJList;
                showFirstMenu();
            }
        }); ///END OF CODE FOR SELECT A CUSTOMER





        customerJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (customerJList.getValueIsAdjusting() == false) {
                    int chosenBookIndex = customerJList.getSelectedIndex();
                    Object chosenCustomer = customerJList.getSelectedValue();


                    selectedCustomer = (Customer) chosenCustomer;


                }

            }
        });



            availableJList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent f) {
                    if (availableJList.getValueIsAdjusting() == false) {
                        int chosenBookIndex = availableJList.getSelectedIndex();
                        Object chosenBook = availableJList.getSelectedValue();

                        selectedBook = (Book) chosenBook;
                    }

                }
            });




///////////////Select Action (1st menu BEFORE SELECTING CUSTOMER))

        //MAKE EACH PANEL VISIBLE

        panel.setVisible(true);
        transactionPanel.setVisible(true);
        customerPanel.setVisible(true);

        //transactionPanel gets buttons to rent or return books
        transactionPanel.add(rentButton);
        transactionPanel.add(inventoryButton);
        transactionPanel.add(showAllBooksRentedAvailable);

        frame.add(panel);
////////////FIRST FRAME INFO
            frame.setSize(256, 128);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }



