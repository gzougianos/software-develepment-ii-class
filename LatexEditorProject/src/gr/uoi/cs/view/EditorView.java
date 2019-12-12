package gr.uoi.cs.view;

import javax.swing.AbstractButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import gr.uoi.cs.model.Document;

public interface EditorView extends View<JPanel> {

	JMenuBar getMenuBar();

	JTextComponent getEditorComponent();

	AbstractButton getNewFileButton();

	AbstractButton getSaveFileButton();

	AbstractButton getLoadFileButton();

	AbstractButton getExitButton();

	AbstractButton getVolatileStrategyButton();

	AbstractButton getStableStrategyButton();

	AbstractButton getDisableStrategyButton();

	AbstractButton getRollbackButton();

	Document getCurrentDocument();

	void setCurrentDocument(Document document);

	void clearStrategySelection();
}
