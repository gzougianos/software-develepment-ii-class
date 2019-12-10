package gr.uoi.cs.controller;

import gr.uoi.cs.controller.commands.CommandFactory;
import gr.uoi.cs.view.EditorView;

public class EditorViewController {
	private EditorView editorView;

	public EditorViewController(EditorView editorView, CommandFactory commandFactory) {
		this.editorView = editorView;
	}
}
