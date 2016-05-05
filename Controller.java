package sample;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser; // File + project structure + Library + GREEN Plus sign + org.jodd:jodd-json:3.6.7 = to
// use Jodd.
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;



public class Controller implements Initializable {


    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList();

    ToDoItemList myList= new ToDoItemList(); // cre8ing a instance class of ToDoItemList, & storing it in variable
    // myList.

    public String saveItems; // just a variable




    public  void name(){
        System.out.println("Welcome: What is your Name? ");
        Scanner uiScanner = new Scanner(System.in);
        String userInput = uiScanner.nextLine();
        saveItems = userInput + ".json"; // storing userInput into saveItems ....json file
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name();
        System.out.println("PULLING UP APPLICATION~~~~~~~");
        //System.out.println("ATTEMPTING TO RETRIEVE FILE~~~~~~~");
        ToDoItemList retrieveItems = retrieveItem();  // storing my retrieveItem() method  into a variable of type
        // ToDoItem named retrieveItems.
        System.out.println("Please add Items to your APPLICATION :) ");


        if (retrieveItems != null) { // if its true that retrieveItems is not null,
            // then i want to put retrieveItems into my observable list.
            todoItems.addAll(retrieveItems.todoItems); // Add array here
        }
        todoList.setItems(todoItems);
    }


   @FXML  //added two variables for each field we want to track.
   ListView todoList;  // <-- list view

   @FXML
    TextField todoText;


    public void addItem() {  // adding a method for each button to our Controller class.
        System.out.println("Adding item ...");
        String textInTheBox = todoText.getText();
        String letMeDo = "Let me do this instead ;-)";
        System.out.println(textInTheBox);
        todoItems.add(new ToDoItem(todoText.getText())); // adding the Items to the List.
        todoText.setText("");
        System.out.println("Storing item");
    }

    public void removeItem() {
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone; //
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
    }

    public void saveItem()  {
        System.out.println("now im first");
        // ToDoItem todoItems = (ToDoItem) todoList.getSelectionModel().getSelectedItems();
        if (todoItems.isEmpty()){
            System.out.println("Empty List");
        } else {
            try {
                System.out.println("Saving item. ..");
                //File testFile = new File("File.json"); // cre8ing a new file named FN, & is being stored into
                // testFile.
                File testFile = new File(saveItems);


                JsonSerializer jsonserializer = new JsonSerializer().deep(true); //  cre8ing a new JsonSerializer & storing
                // it in jsonserializer variable.

                // ToDoItem todoItem = (ToDoItem) todoList.getItems().get(0); // getting the 1st item from the todoList
                // list view & storing it into todoItem.

                myList.todoItems.addAll(todoItems); // adding all observable list inside array list whch is inside
                // todoiteslist class

                String jsonString = jsonserializer.serialize(myList); // serializing todoItem & storing it as a jsonString.

                FileWriter testWriter = new FileWriter(testFile); //  cre8ing a new FileWriter while taking in parameter of
                // my file variable = testFile, & storing it into testWriter.
                testWriter.write(jsonString); // writing whatever is in -> () <- to file .
                System.out.println("file saving");
                testWriter.close(); // closing
                System.out.println("file closed");

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public ToDoItemList retrieveItem() { // named ToDoItem to Return the Item.
        // retrieve something of type Hospital use fie scanner to cre8 a file and
        // pass it. tell file scanner to pass \\z, i want to
        try{
            Scanner fileScanner = new Scanner(new File(saveItems));
            fileScanner.useDelimiter("\\Z"); // read the input until the 'end of the input'.
            String fileContents = fileScanner.next();
            JsonParser ControllerParser = new JsonParser();

            ToDoItemList returnItems = ControllerParser.parse(fileContents, ToDoItemList.class); // todoitems list class
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("            Restored previous Version");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            return returnItems;

        } catch (IOException ioexception) { // if we can't find the file or run into an issue restoring the object
            // from the file, just return null, so the callrt knows to create an object from
            return null;
        }
    }
}

