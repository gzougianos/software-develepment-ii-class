package gr.uoi.cs.view;

import javax.swing.AbstractButton;
import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

public interface EditorView extends View<JRootPane> {
	JTextComponent getEditorComponent();

	AbstractButton getNewFileButton();

	AbstractButton getSaveFileButton();

	AbstractButton getLoadFileButton();

	AbstractButton getExitButton();

	AbstractButton getVolatileStrategyButton();

	AbstractButton getStableStrategyButton();

	AbstractButton getDisableStrategyButton();

	AbstractButton getRollbackButton();
}
