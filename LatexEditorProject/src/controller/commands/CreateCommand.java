package controller.commands;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import model.Document;
import model.DocumentType;
import view.MainView;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainView mainView;

	public CreateCommand(DocumentManager documentManager, VersionsManager versionsManager, MainView mainView) {
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		DocumentType documentType = mainView.getOpeningView().getSelectedDocumentType();
		Document document = documentManager.createDocument(documentType);
		mainView.showEditorView();
		mainView.getEditorView().getEditorComponent().setText(document.getContents());
	}

}
