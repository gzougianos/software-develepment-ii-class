package gr.uoi.cs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.encryption.EncryptionStrategy;

public class EncryptionManagerTests {
	@Test
	public void main() {
		Document document = new Document(DocumentType.ARTICLE);
		EncryptionManager manager = new EncryptionManager();

		document.setContents("content");
		manager.encrypt(document);// null algorithm will have no encryption
		assertEquals("content", document.getContents());

		document.setEncryptionAlgorithm(EncryptionStrategy.ATBASH);
		manager.encrypt(document);
		assertEquals("tnetnoc", document.getContents());

		manager.decrypt(document);
		assertEquals("content", document.getContents());

	}
}
