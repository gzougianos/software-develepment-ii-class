package gr.uoi.cs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.strategies.VersionNotFoundException;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.model.strategies.VersionsStrategyFactory;

public class VersionsManagerTests {

	@Test
	public void main() throws VersionNotFoundException {
		Document doc = new DocumentManager().createDocument(DocumentType.ARTICLE);
		VersionsManager manager = new VersionsManager(new VersionsStrategyFactory());
		manager.enable();
		manager.changeStrategy(VersionsStrategy.VOLATILE, doc);

		doc.setContents("hello");
		manager.commitVersion(doc);

		assertEquals(1, doc.getVersionId());

		doc.setContents("hi");
		manager.commitVersion(doc);
		manager.rollback(doc);
		assertEquals("hello", doc.getContents());
		assertThrows(VersionNotFoundException.class, () -> manager.rollback(doc));

	}

	@Test
	public void changeStrategy() throws VersionNotFoundException {
		Document doc = new DocumentManager().createDocument(DocumentType.ARTICLE);
		VersionsManager manager = new VersionsManager(new VersionsStrategyFactory());
		manager.enable();
		manager.changeStrategy(VersionsStrategy.VOLATILE, doc);

		doc.setContents("hello"); // version 1
		manager.commitVersion(doc);

		assertEquals(1, doc.getVersionId());

		manager.changeStrategy(VersionsStrategy.STABLE, doc);

		doc.setContents("hi");
		manager.commitVersion(doc);
		manager.rollback(doc);
		assertEquals("hello", doc.getContents());
		assertThrows(VersionNotFoundException.class, () -> manager.rollback(doc));
	}
}
