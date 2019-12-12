package gr.uoi.cs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.model.DocumentType;

public class LatexCommandManagerTests {
	@Test
	public void main() {
		LatexCommandManager manager = new LatexCommandManager();
		assertTrue(manager.getCommandsForDocumentType(DocumentType.LETTER).isEmpty());
	}
}
