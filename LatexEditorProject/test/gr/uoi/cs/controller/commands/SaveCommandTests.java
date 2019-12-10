package gr.uoi.cs.controller.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.JTextComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.strategies.VersionsStrategyFactory;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class SaveCommandTests {
	private static final File testFile = new File(System.getProperty("java.io.tmpdir"), "test_document.tex");
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainView mainView;

	@Test
	public void main() throws FileNotFoundException, ClassNotFoundException, IOException {
		Document doc = documentManager.createDocument(DocumentType.ARTICLE);
		mainView.getEditorView().setCurrentDocument(doc);
		JTextComponent textComponent = mainView.getEditorView().getEditorComponent();
		textComponent.setText("contents");

		SaveCommand command = new SaveCommand(versionsManager, documentManager, mainView, () -> testFile);
		command.execute();
		assertEquals("contents", doc.getContents());

		Document doc2 = documentManager.loadDocument(doc.getPath());
		assertEquals(doc, doc2);

		doc.setContents("It does not matter since it will take it from the view");
		textComponent.setText("hello");

		// With null file supplier NPE shouldn't take place since it does not rely on it
		command = new SaveCommand(versionsManager, documentManager, mainView, () -> null);
		command.execute();
		assertEquals(textComponent.getText(), doc.getContents());

		doc2 = documentManager.loadDocument(doc.getPath());
		assertEquals(doc, doc2);
		assertEquals("hello", doc2.getContents());

	}

	@BeforeEach
	public void init() {
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager(new VersionsStrategyFactory());
		mainView = new MainFrame();
	}
}
