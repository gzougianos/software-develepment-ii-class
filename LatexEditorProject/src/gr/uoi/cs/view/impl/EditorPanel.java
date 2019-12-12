package gr.uoi.cs.view.impl;

import java.awt.BorderLayout;

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
import gr.uoi.cs.view.EditorView;

public class EditorPanel extends JPanel implements EditorView {
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
	private JMenuBar menuBar;
	private ButtonGroup strategyButtonGroup;

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
		menuBar.add(createStrategyMenu());

		strategyButtonGroup = new ButtonGroup();
		strategyButtonGroup.add(volatileStrategyButton);
		strategyButtonGroup.add(stableStrategyButton);

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

	@Override
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	@Override
	public void clearStrategySelection() {
		strategyButtonGroup.clearSelection();
	}
}
