package gr.uoi.cs.controller;

import gr.uoi.cs.controller.commands.Command;
import gr.uoi.cs.controller.commands.CommandFactory;
import gr.uoi.cs.view.EditorView;

public class EditorViewController {
	private EditorView editorView;
	private CommandFactory commandFactory;

	public EditorViewController(EditorView editorView, CommandFactory commandFactory) {
		this.editorView = editorView;
		this.commandFactory = commandFactory;

		registerCommands();
	}

	private void registerCommands() {
		editorView.getSaveButton().addActionListener(e -> saveDocument());
	}

	public void saveDocument() {
		commandFactory.createCommand(Command.SAVE_DOCUMENT).execute();
	}
}
