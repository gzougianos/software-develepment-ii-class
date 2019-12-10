package gr.uoi.cs.view.impl;

import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.view.EditorView;

public class EditorPane extends JRootPane implements EditorView {
	private static final long serialVersionUID = -1110668399927786993L;
	private Document currentDocument;
	private JTextArea editorArea;
	private AbstractButton newFileButton;
	private AbstractButton saveFileButton;
	private AbstractButton loadFileButton;
	private AbstractButton exitButton;
	private AbstractButton volatileStrategyButton;
	private AbstractButton stableStrategyButton;
	private AbstractButton disableStrategyButton;
	private AbstractButton rollbackButton;

	public EditorPane() {
		super();
		getContentPane().setLayout(new BorderLayout());

		editorArea = new JTextArea();
		editorArea.setWrapStyleWord(true);
		editorArea.setLineWrap(true);

		JScrollPane editorScrollPane = new JScrollPane(editorArea);
		getContentPane().add(editorScrollPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createStrategyMenu());

		setJMenuBar(menuBar);
	}

	private JMenu createStrategyMenu() {
		JMenu strategyMenu = new JMenu("Strategy");

		JMenu enableStrategyButton = new JMenu("Enable");
		strategyMenu.add(enableStrategyButton);

		volatileStrategyButton = new JCheckBoxMenuItem("Volatile");
		enableStrategyButton.add(volatileStrategyButton);

		stableStrategyButton = new JCheckBoxMenuItem("Stable");
		enableStrategyButton.add(stableStrategyButton);

		disableStrategyButton = new JMenuItem("Disable");
		strategyMenu.add(disableStrategyButton);

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
	public JRootPane component() {
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
	public AbstractButton getVolatileStrategyButton() {
		return volatileStrategyButton;
	}

	@Override
	public AbstractButton getStableStrategyButton() {
		return stableStrategyButton;
	}

	@Override
	public AbstractButton getDisableStrategyButton() {
		return disableStrategyButton;
	}

	@Override
	public AbstractButton getRollbackButton() {
		return rollbackButton;
	}

	@Override
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}

	@Override
	public Document getCurrentDocument() {
		return currentDocument;
	}
}
