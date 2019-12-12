package gr.uoi.cs.controller.commands;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;

public class KeepVersionCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public KeepVersionCommand(VersionsManager versionsManager, MainView mainView) {
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
			versionsManager.keepVersion(document);
		document.setContents(previousContents);
	}

}
