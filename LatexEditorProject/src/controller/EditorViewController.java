package controller;

import controller.commands.CommandFactory;
import view.EditorView;

public class EditorViewController {
	private EditorView editorView;

	public EditorViewController(EditorView editorView, CommandFactory commandFactory) {
		this.editorView = editorView;
	}
}
