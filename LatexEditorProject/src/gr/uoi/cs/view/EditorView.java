package gr.uoi.cs.view;

import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

public interface EditorView extends View<JRootPane> {
	JTextComponent getEditorComponent();
}
