package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;
import gr.uoi.cs.model.version.VersionsStrategyFactory;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class EnableVersionStrategyCommandTests {
	@Test
	public void enable() {
		VersionsManager manager = new VersionsManager(new VersionsStrategyFactory());
		assertFalse(manager.isEnabled());

		MainView view = new MainFrame();
		Document document = new DocumentManager().createDocument(DocumentType.ARTICLE);
		view.getEditorView().setCurrentDocument(document);

		view.getEditorView().getVolatileVersionStrategyButton().setSelected(true);
		EnableVersionStrategyCommand command = new EnableVersionStrategyCommand(manager, view);
		command.execute();
		assertTrue(manager.isEnabled());
	}
}
