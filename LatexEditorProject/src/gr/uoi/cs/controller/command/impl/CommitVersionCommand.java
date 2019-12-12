package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;

public class CommitVersionCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public CommitVersionCommand(VersionsManager versionsManager, MainView mainView) {
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		EditorView editorView = mainView.getEditorView();
		Document document = editorView.getCurrentDocument();
		String previousContents = document.getContents();
		document.setContents(editorView.getEditorComponent().getText());
		if (versionsManager.isEnabled())
			versionsManager.commitVersion(document);
		document.setContents(previousContents);
	}

}
