package gr.uoi.cs.controller;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gr.uoi.cs.controller.commands.Command;
import gr.uoi.cs.controller.commands.CommandFactory;
import gr.uoi.cs.view.EditorView;

public class EditorViewController implements DocumentListener {
	private EditorView editorView;
	private CommandFactory commandFactory;

	public EditorViewController(EditorView editorView, CommandFactory commandFactory) {
		this.editorView = editorView;
		this.commandFactory = commandFactory;

		editorView.getEditorComponent().getDocument().addDocumentListener(this);
		registerCommands();
	}

	private void registerCommands() {
		editorView.getSaveFileButton().addActionListener(e -> saveDocument());
		editorView.getLoadFileButton().addActionListener(e -> loadDocument());
		editorView.getExitButton().addActionListener(e -> exit());
		editorView.getNewFileButton().addActionListener(e -> redirectToNewDocumentCreation());
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
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateSaveButtonAvailability();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateSaveButtonAvailability();
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
