package gr.uoi.cs.model.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class AtbashEncryptionStrategyTests {

	@Test
	public void main() {
		AtbashEncryptionStrategy atbash = new AtbashEncryptionStrategy();
		Document document = new Document(DocumentType.BOOK);
		document.setContents("contents");

		atbash.encrypt(document);
		assertEquals("stnetnoc", document.getContents());

		atbash.decrypt(document);
		assertEquals("contents", document.getContents());

	}
}
