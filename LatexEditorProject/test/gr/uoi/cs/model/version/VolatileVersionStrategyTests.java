package gr.uoi.cs.model.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class VolatileVersionStrategyTests {

	@Test
	public void main() throws VersionNotFoundException {
		Document doc = new Document(DocumentType.ARTICLE);
		VolatileVersionsStrategy volatilee = new VolatileVersionsStrategy();
		doc.setContents("hello");
		volatilee.putVersion(doc, 1);

		doc.setContents("aha");
		Document previousVersion = volatilee.getVersion(doc, 1);
		assertEquals("hello", previousVersion.getContents());

		assertThrows(VersionNotFoundException.class, () -> volatilee.getVersion(doc, 2));

	}

	@Test
	public void entireHistory() {
		Document doc = new Document(DocumentType.BOOK);
		doc.setCreatedTime(System.currentTimeMillis());
		doc.setContents("contents");
		doc.setVersionId(1);

		Document doc2 = new Document(DocumentType.ARTICLE);
		doc2.setCreatedTime(System.currentTimeMillis());
		doc2.setContents("contents2");
		doc2.setVersionId(2);

		VolatileVersionsStrategy stable = new VolatileVersionsStrategy();
		stable.setEntireHistory(doc, Arrays.asList(doc, doc2));

		assertEquals(stable.getEntireHistory(doc), Arrays.asList(doc, doc2));

	}
}
