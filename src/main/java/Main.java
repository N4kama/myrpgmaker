import Editor.EditorController;
import Editor.EditorModel;
import Editor.EditorView;

public class Main {
    public static void main(String[] args) {
        EditorModel model = new EditorModel();
        EditorView view = new EditorView(model);
        EditorController controller = new EditorController(model, view);
    }
}
