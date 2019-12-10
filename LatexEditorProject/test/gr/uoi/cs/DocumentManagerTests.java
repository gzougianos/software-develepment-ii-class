package gr.uoi.cs;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class DocumentManagerTests {
	private static final File testFile = new File(System.getProperty("java.io.tmpdir"), "test_document");
	private DocumentManager documentManager;

	@Test
	public void saveLoadDocument() throws Exception {
		Document document = new Document(DocumentType.ARTICLE);
		document.setContents("CONTENTS");
		document.setAuthor("authoris");
		document.setDate("a date");
		document.setCopyright("copyr");

		documentManager.saveDocument(document, testFile);
		assertTrue(testFile.exists());

		Document newDocument = documentManager.loadDocument(testFile);
		assertEquals(document, newDocument);
		assertEquals(testFile, newDocument.getPath());
	}

	@BeforeEach
	public void init() throws IOException {
		documentManager = new DocumentManager();
		if (testFile.exists())
			Files.delete(testFile.toPath());
	}
}
