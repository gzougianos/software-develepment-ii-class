package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class EnableEncryptionStrategyTests {

	@Test
	public void main() {
		MainView view = new MainFrame();
		Document doc = new Document(DocumentType.ARTICLE);

		view.getEditorView().setCurrentDocument(doc);
		view.getEditorView().getAtbashEncryptionStrategyButton().setSelected(true);

		EnableEncryptionStrategyCommand command = new EnableEncryptionStrategyCommand(view);
		command.execute();

		assertEquals(EncryptionStrategy.ATBASH, doc.getEncryptionAlgorithm());
	}
}
