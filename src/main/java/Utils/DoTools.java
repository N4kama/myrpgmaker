package Utils;

import java.util.Stack;

public class DoTools {


    public static void addUndoEvent(EditorEvent event) {
        undo_stack.push(event);
    }

    public static void addRedoEvent(EditorEvent event) {
        redo_stack.push(event);
    }

     static Stack<EditorEvent> undo_stack = new Stack<>();
     static Stack<EditorEvent> redo_stack = new Stack<>();
}