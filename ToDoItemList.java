package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tisha868 on 4/26/16.
 */

public class ToDoItemList {
    public ArrayList<ToDoItem> todoItems = new ArrayList<ToDoItem>(); /// delcared in GLobal scoop

    public ToDoItemList(List<ToDoItem> incomingList) { //
        todoItems = new ArrayList<ToDoItem>(incomingList); //  intitionlized inside of a method
    }

    public ToDoItemList() {

    }
}

