package gr.uoi.cs.model.version;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class StableVersionStrategyTests {
	@Test
	public void main() throws VersionNotFoundException {
		Document doc = new Document(DocumentType.BOOK);
		doc.setCreatedTime(System.currentTimeMillis());

		StableVersionsStrategy stable = new StableVersionsStrategy();

		doc.setContents("black");

		stable.putVersion(doc, 1);

		doc.setContents("chnaged");

		Document previousVersion = stable.getVersion(doc, 1);
		assertEquals("black", previousVersion.getContents());
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

		StableVersionsStrategy stable = new StableVersionsStrategy();
		stable.setEntireHistory(doc, Arrays.asList(doc, doc2));

		assertEquals(stable.getEntireHistory(doc), Arrays.asList(doc, doc2));

	}
}
