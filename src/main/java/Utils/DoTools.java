package Utils;

import java.util.Stack;

public class DoTools {
    public static void undo() {
        if (undo_stack.empty())
        {
            System.out.println("C'est vide mon gars");
            return;
        }
        EditorEvent event = undo_stack.pop();
        if (event.action == EditorEvent.EventType.ADD_TILE) {
            System.out.println("On est bien ici undo");
            addRedoEvent(new EditorEvent(EditorEvent.EventType.ADD_TILE, event.map, event.x, event.y));
            event.map.setTile(event.x, event.y, event.old_path);
            //event.map.getGameTile(event.x, event.y).setTile_img_(event.old_path_img);
            event.map.getGameTile(event.x, event.y).setIs_Walkable(event.was_walkable);
        }
        else if (event.action == EditorEvent.EventType.ADD_OBJECT) {
            event.map.getGameObjects().add(event.object);
            event.action = EditorEvent.EventType.DEL_OBJECT;
            addRedoEvent(event);
        }
        else if (event.action == EditorEvent.EventType.DEL_OBJECT) {
            event.map.getGameObjects().add(event.object);
            event.action = EditorEvent.EventType.ADD_OBJECT;
            addRedoEvent(event);
        }
    }

    public static void redo() {
        if (redo_stack.empty())
        {
            System.out.println("C'est vide mon gars");
            return;
        }
        EditorEvent event = redo_stack.pop();
        if (event.action.equals(EditorEvent.EventType.ADD_TILE)) {
            System.out.println("On est bien ici redo");
            addUndoEvent(new EditorEvent(EditorEvent.EventType.ADD_TILE, event.map, event.x, event.y));
            event.map.setTile(event.x, event.y, event.old_path);
            //event.map.getGameTile(event.x, event.y).setTile_img_(event.old_path_img);
            event.map.getGameTile(event.x, event.y).setIs_Walkable(event.was_walkable);
        }
        else if (event.action.equals(EditorEvent.EventType.ADD_OBJECT) ) {
            event.map.getGameObjects().add(event.object);
            event.action = EditorEvent.EventType.DEL_OBJECT;
            addUndoEvent(event);
        }
        else if (event.action.equals(EditorEvent.EventType.DEL_OBJECT)) {
            event.map.getGameObjects().add(event.object);
            event.action = EditorEvent.EventType.ADD_OBJECT;
            addUndoEvent(event);
        }
    }

    public static void addUndoEvent(EditorEvent event) {
        undo_stack.push(event);
    }

    public static void addRedoEvent(EditorEvent event) {
        redo_stack.push(event);
    }

     static Stack<EditorEvent> undo_stack = new Stack<>();
     static Stack<EditorEvent> redo_stack = new Stack<>();
}