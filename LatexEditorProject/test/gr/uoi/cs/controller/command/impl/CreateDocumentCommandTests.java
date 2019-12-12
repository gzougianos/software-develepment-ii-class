package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.impl.CreateDocumentCommand;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.strategies.VersionsStrategyFactory;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.OpeningView;
import gr.uoi.cs.view.impl.MainFrame;
import gr.uoi.cs.view.impl.OpeningPanel;

public class CreateDocumentCommandTests {

	private DocumentManager documentManager;
	private MainView mainView;
	private VersionsManager versionsManager;

	@Test
	public void main() {
		CreateDocumentCommand command = new CreateDocumentCommand(documentManager, versionsManager, mainView);
		command.execute();

		Document doc = mainView.getEditorView().getCurrentDocument();
		assertNotNull(doc);
		assertEquals(DocumentType.LETTER, doc.getType());
		assertEquals(doc.getContents(), mainView.getEditorView().getEditorComponent().getText());
	}

	@SuppressWarnings("serial")
	@BeforeEach
	public void init() throws IOException {
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager(new VersionsStrategyFactory());
		OpeningView openingView = new OpeningPanel() {
			@Override
			public DocumentType getSelectedDocumentType() {
				return DocumentType.LETTER;
			}
		};
		mainView = new MainFrame() {
			@Override
			public OpeningView getOpeningView() {
				return openingView;
			}
		};
	}
}
