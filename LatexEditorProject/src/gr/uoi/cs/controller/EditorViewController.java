package gr.uoi.cs.controller;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gr.uoi.cs.controller.commands.Command;
import gr.uoi.cs.controller.commands.CommandFactory;
import gr.uoi.cs.view.EditorView;

public class EditorViewController implements DocumentListener {
	private static final int ATTEMPT_TO_KEEP_VERSION_AFTER_EDIT_MS = 250;
	private EditorView editorView;
	private CommandFactory commandFactory;
	private Timer keepVersionTimer;

	public EditorViewController(EditorView editorView, CommandFactory commandFactory) {
		this.editorView = editorView;
		this.commandFactory = commandFactory;

		initKeepVersionTimer();
		editorView.getEditorComponent().getDocument().addDocumentListener(this);
		registerCommands();
		disableVersionStrategy(); // default is disabled
	}

	private void initKeepVersionTimer() {
		keepVersionTimer = new Timer(ATTEMPT_TO_KEEP_VERSION_AFTER_EDIT_MS, e -> {
			commandFactory.createCommand(Command.KEEP_VERSION).execute();
		});
		keepVersionTimer.setRepeats(false);
	}

	private void registerCommands() {
		registerFileRelatedCommands();
		registerVersionStrategyRelatedCommands();
	}

	private void registerVersionStrategyRelatedCommands() {
		editorView.getDisableStrategyButton().addActionListener(e -> disableVersionStrategy());
		ActionListener enableVersionStrategyListener = e -> enableVersionStrategy();
		editorView.getVolatileStrategyButton().addActionListener(enableVersionStrategyListener);
		editorView.getStableStrategyButton().addActionListener(enableVersionStrategyListener);
		editorView.getRollbackButton().addActionListener(e -> rollback());
	}

	private void rollback() {
		commandFactory.createCommand(Command.ROLLBACK_TO_PREVIOUS_VERSION).execute();
	}

	private void registerFileRelatedCommands() {
		editorView.getSaveFileButton().addActionListener(e -> saveDocument());
		editorView.getLoadFileButton().addActionListener(e -> loadDocument());
		editorView.getExitButton().addActionListener(e -> exit());
		editorView.getNewFileButton().addActionListener(e -> redirectToNewDocumentCreation());
	}

	private void enableVersionStrategy() {
		commandFactory.createCommand(Command.ENABLE_VERSION_STRATEGY).execute();
	}

	private void disableVersionStrategy() {
		commandFactory.createCommand(Command.DISABLE_VERSION_STRATEGY).execute();
	}

	private void redirectToNewDocumentCreation() {
		if (askToSaveUnsavedChanges())
			commandFactory.createCommand(Command.SHOW_OPENING_VIEW).execute();
	}

	private boolean askToSaveUnsavedChanges() {
		if (contentChanged()) {
			int answer = JOptionPane.showConfirmDialog(editorView.component(),
					"There are unsaved changes to the document. Would you like to save them before you exit the editor?",
					"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			if (answer == JOptionPane.YES_OPTION)
				saveDocument();
			else if (answer == JOptionPane.CANCEL_OPTION)
				return false;
		}
		return true;
	}

	private void exit() {
		commandFactory.createCommand(Command.EXIT).execute();
	}

	private void loadDocument() {
		commandFactory.createCommand(Command.LOAD_DOCUMENT).execute();
	}

	public void saveDocument() {
		commandFactory.createCommand(Command.SAVE_DOCUMENT).execute();
		updateSaveButtonAvailability();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateSaveButtonAvailability();
		if (e.getLength() != editorView.getEditorComponent().getText().length()) {
			keepVersionTimer.restart();
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateSaveButtonAvailability();
		if (e.getOffset() != 0 || editorView.getEditorComponent().getText().length() != 0) {
			keepVersionTimer.restart();
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateSaveButtonAvailability();
		if (e.getLength() != editorView.getEditorComponent().getText().length())
			keepVersionTimer.restart();
	}

	private void updateSaveButtonAvailability() {
		boolean hasNeverBeenSavedBefore = editorView.getCurrentDocument().getPath() == null;
		editorView.getSaveFileButton().setEnabled(hasNeverBeenSavedBefore || contentChanged());
	}

	private boolean contentChanged() {
		String documentContent = editorView.getCurrentDocument().getContents();
		String contentInTextComponent = editorView.getEditorComponent().getText();
		return !documentContent.equals(contentInTextComponent);
	}
}
