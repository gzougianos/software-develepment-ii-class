package gr.uoi.cs.view;

import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.LatexCommand;

public interface EditorView extends View<JPanel> {
	String COMMANDS_CHANGED_PROPERTY = "COMMANDS_CHANGED";
	String DOCUMENT_CHANGED_PROPERTY = "DOCUMENT_CHANGED";

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

	void setLatexCommands(Map<String, List<LatexCommand>> commands);

	JMenu getLatexCommandsMenu();

	void clearEncryptionSelection();

	AbstractButton getRot13EncryptionStrategyButton();

	AbstractButton getAtbashEncryptionStrategyButton();

	AbstractButton getDisableEncryptionStrategyButton();

}
