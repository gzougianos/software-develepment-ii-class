package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class DisableEncryptionStrategyCommandTests {

	@Test
	public void main() {
		Document document = new Document(DocumentType.ARTICLE);
		document.setEncryptionAlgorithm(EncryptionStrategy.ATBASH);

		MainView mainView = new MainFrame();
		DisableEncryptionStrategyCommand command = new DisableEncryptionStrategyCommand(mainView);
		mainView.getEditorView().setCurrentDocument(document);

		command.execute();

		assertNull(document.getEncryptionAlgorithm());
	}
}
