package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.view.MainView;

public class CreateDocumentCommand implements Command {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainView mainView;

	public CreateDocumentCommand(DocumentManager documentManager, VersionsManager versionsManager, MainView mainView) {
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		DocumentType documentType = mainView.getOpeningView().getSelectedDocumentType();
		Document document = documentManager.createDocument(documentType);
		mainView.getEditorView().setCurrentDocument(document);
		mainView.getEditorView().getEditorComponent().setText(document.getContents());
		mainView.showEditorView();
	}

}
