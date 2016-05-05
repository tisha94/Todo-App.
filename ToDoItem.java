package sample;

/**
 * Created by Tisha868 on 4/21/16.
 */
public class ToDoItem {
      public   String text;
      public   boolean isDone;

        public ToDoItem() { // default constructor

        }

        public ToDoItem(String text) {
            this.text = text;
            this.isDone = false;
        }

        @Override // = annotation, a comment for the compiler & other java classes. Ment to override a method ONLY the base class.
        // a way to make sure
        public String toString() {
            if (isDone) {
                return text + " (done)";
            } else {
                return text;
            }
            // A one-line version of the logic above:
            // return text + (isDone ? " (done)" : ""); [a shorter lazier If/Else statement]

        }

}
