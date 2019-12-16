package gr.uoi.cs.view.impl;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.LatexCommand;
import gr.uoi.cs.view.EditorView;

public class EditorPanel extends JPanel implements EditorView {
	private static final long serialVersionUID = -1110668399927786993L;
	private Document currentDocument;
	private JTextArea editorArea;
	private AbstractButton newFileButton;
	private AbstractButton saveFileButton;
	private AbstractButton loadFileButton;
	private AbstractButton exitButton;
	private AbstractButton volateVersionStrategyButton;
	private AbstractButton stableVersionStrategyButton;
	private AbstractButton disableVersionStrategyButton;
	private AbstractButton rollbackButton;
	private JMenuBar menuBar;
	private ButtonGroup versionButtonGroup;
	private JMenu commandsMenu;
	private ButtonGroup encryptionButtonGroup;
	private JMenuItem atbashEncryptionMenuItem;
	private JMenuItem rot13EncryptionMenuItem;
	private JMenuItem disableEncryptionMenuItem;

	public EditorPanel() {
		super();
		setLayout(new BorderLayout());

		editorArea = new JTextArea();
		editorArea.setWrapStyleWord(true);
		editorArea.setLineWrap(true);

		JScrollPane editorScrollPane = new JScrollPane(editorArea);
		add(editorScrollPane, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(commandsMenu = new JMenu("Latex Commands"));
		menuBar.add(createStrategyMenu());
		menuBar.add(createEncryptionMenu());

		versionButtonGroup = new ButtonGroup();
		versionButtonGroup.add(volateVersionStrategyButton);
		versionButtonGroup.add(stableVersionStrategyButton);

	}

	private JMenu createEncryptionMenu() {
		JMenu encrytpionMenu = new JMenu("Encryption");

		JMenu enableEncryptionMenu = new JMenu("Enable");
		enableEncryptionMenu.add(enableEncryptionMenu);

		atbashEncryptionMenuItem = new JCheckBoxMenuItem("Atbash");
		enableEncryptionMenu.add(atbashEncryptionMenuItem);

		rot13EncryptionMenuItem = new JCheckBoxMenuItem("Rot-13");
		enableEncryptionMenu.add(rot13EncryptionMenuItem);
		encrytpionMenu.add(enableEncryptionMenu);

		encryptionButtonGroup = new ButtonGroup();
		encryptionButtonGroup.add(atbashEncryptionMenuItem);
		encryptionButtonGroup.add(rot13EncryptionMenuItem);

		disableEncryptionMenuItem = new JMenuItem("Disable");
		encrytpionMenu.add(disableEncryptionMenuItem);
		return encrytpionMenu;
	}

	private JMenu createStrategyMenu() {
		JMenu strategyMenu = new JMenu("Strategy");

		JMenu enableStrategyButton = new JMenu("Enable");
		strategyMenu.add(enableStrategyButton);

		volateVersionStrategyButton = new JCheckBoxMenuItem("Volatile");
		enableStrategyButton.add(volateVersionStrategyButton);

		stableVersionStrategyButton = new JCheckBoxMenuItem("Stable");
		enableStrategyButton.add(stableVersionStrategyButton);

		disableVersionStrategyButton = new JMenuItem("Disable");
		strategyMenu.add(disableVersionStrategyButton);

		rollbackButton = new JMenuItem("Rollback");
		strategyMenu.add(rollbackButton);
		return strategyMenu;
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		newFileButton = new JMenuItem("New file");
		menu.add(newFileButton);

		saveFileButton = new JMenuItem("Save");
		menu.add(saveFileButton);

		loadFileButton = new JMenuItem("Load");
		menu.add(loadFileButton);

		exitButton = new JMenuItem("Exit");
		menu.add(exitButton);
		return menu;
	}

	@Override
	public JPanel component() {
		return this;
	}

	@Override
	public JTextComponent getEditorComponent() {
		return editorArea;
	}

	public JTextArea getEditorArea() {
		return editorArea;
	}

	@Override
	public AbstractButton getNewFileButton() {
		return newFileButton;
	}

	@Override
	public AbstractButton getSaveFileButton() {
		return saveFileButton;
	}

	@Override
	public AbstractButton getLoadFileButton() {
		return loadFileButton;
	}

	@Override
	public AbstractButton getExitButton() {
		return exitButton;
	}

	@Override
	public AbstractButton getVolatileVersionStrategyButton() {
		return volateVersionStrategyButton;
	}

	@Override
	public AbstractButton getStableVersionStrategyButton() {
		return stableVersionStrategyButton;
	}

	@Override
	public AbstractButton getDisableVersionStrategyButton() {
		return disableVersionStrategyButton;
	}

	@Override
	public AbstractButton getRollbackButton() {
		return rollbackButton;
	}

	@Override
	public void setCurrentDocument(Document currentDocument) {
		Document oldDocument = getCurrentDocument();
		this.currentDocument = currentDocument;
		firePropertyChange(DOCUMENT_CHANGED_PROPERTY, oldDocument, getCurrentDocument());
	}

	@Override
	public Document getCurrentDocument() {
		return currentDocument;
	}

	@Override
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	@Override
	public void clearStrategySelection() {
		versionButtonGroup.clearSelection();
	}

	@Override
	public void setLatexCommands(Map<String, List<LatexCommand>> commands) {
		firePropertyChange(COMMANDS_CHANGED_PROPERTY, null, commands);
	}

	@Override
	public JMenu getLatexCommandsMenu() {
		return commandsMenu;
	}

	@Override
	public void clearEncryptionSelection() {
		encryptionButtonGroup.clearSelection();
	}

	@Override
	public AbstractButton getRot13EncryptionStrategyButton() {
		return rot13EncryptionMenuItem;
	}

	@Override
	public AbstractButton getAtbashEncryptionStrategyButton() {
		return atbashEncryptionMenuItem;
	}

	@Override
	public AbstractButton getDisableEncryptionStrategyButton() {
		return disableEncryptionMenuItem;
	}
}
