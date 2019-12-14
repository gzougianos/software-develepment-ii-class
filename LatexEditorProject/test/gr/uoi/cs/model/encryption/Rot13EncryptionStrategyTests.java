package gr.uoi.cs.model.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class Rot13EncryptionStrategyTests {
	@Test
	public void main() {
		Rot13EncryptionStrategy rot13 = new Rot13EncryptionStrategy();
		Document doc = new Document(DocumentType.ARTICLE);
		doc.setContents("Ahello");

		rot13.encrypt(doc);
		assertEquals("Nuryyb", doc.getContents());

		rot13.decrypt(doc);
		assertEquals("Ahello", doc.getContents());

	}
}
