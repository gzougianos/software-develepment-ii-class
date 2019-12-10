package gr.uoi.cs.controller;

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
		String documentContent = editorView.getCurrentDocument().getContents();
		String contentInTextComponent = editorView.getEditorComponent().getText();
		boolean hasNeverBeenSavedBefore = editorView.getCurrentDocument().getPath() == null;
		editorView.getSaveFileButton()
				.setEnabled(hasNeverBeenSavedBefore || !documentContent.equals(contentInTextComponent));
	}
}
