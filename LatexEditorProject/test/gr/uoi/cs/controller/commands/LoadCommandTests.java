package gr.uoi.cs.controller.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class LoadCommandTests {
	private static final File testFile = new File(System.getProperty("java.io.tmpdir"), "test_document.tex");
	private DocumentManager documentManager;
	private MainView mainView;

	@Test
	public void main() throws IOException {
		Document doc = new Document(DocumentType.BOOK);
		doc.setContents("hei");
		documentManager.saveDocument(doc, testFile);

		LoadCommand command = new LoadCommand(documentManager, mainView, () -> testFile);
		command.execute();
		assertEquals(doc, mainView.getCurrentDocument());
	}

	@BeforeEach
	public void init() throws IOException {
		documentManager = new DocumentManager();
		mainView = new MainFrame();
		if (testFile.exists())
			Files.delete(testFile.toPath());
	}
}
