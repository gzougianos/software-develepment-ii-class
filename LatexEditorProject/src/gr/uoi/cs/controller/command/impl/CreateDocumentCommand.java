package gr.uoi.cs.controller.command.impl;

import java.util.List;
import java.util.Map;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.LatexCommandManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.LatexCommand;
import gr.uoi.cs.view.MainView;

public class CreateDocumentCommand implements Command {
	private DocumentManager documentManager;
	private MainView mainView;
	private LatexCommandManager latexCommandManager;

	public CreateDocumentCommand(DocumentManager documentManager, LatexCommandManager latexCommandManager,
			MainView mainView) {
		this.documentManager = documentManager;
		this.latexCommandManager = latexCommandManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		DocumentType documentType = mainView.getOpeningView().getSelectedDocumentType();
		Document document = documentManager.createDocument(documentType);
		mainView.getEditorView().setCurrentDocument(document);
		mainView.getEditorView().getEditorComponent().setText(document.getContents());
		mainView.showEditorView();

		Map<String, List<LatexCommand>> commandsForThisType = latexCommandManager
				.getCommandsForDocumentType(document.getType());
		mainView.getEditorView().setLatexCommands(commandsForThisType);
	}

}
