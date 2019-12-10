package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.commands.CreateCommand;
import gr.uoi.cs.controller.commands.DisableVersionsManagementCommand;
import gr.uoi.cs.controller.commands.EditCommand;
import gr.uoi.cs.model.strategies.StableVersionsStrategy;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.model.strategies.VolatileVersionsStrategy;
import gr.uoi.cs.view.LatexEditorView;

class DisableVersionsManagementCommandTest {
	private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager(null, latexEditorView);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	private DisableVersionsManagementCommand disableCommand = new DisableVersionsManagementCommand(versionsManager);

	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorView.setType("articleTemplate");
		latexEditorView.setVersionsManager(versionsManager);
		createCommand.execute();
		latexEditorView.setStrategy("volatile");
		disableCommand.execute();
		latexEditorView.setText("test edit contents\n");
		editCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
		assertEquals(strategy.getEntireHistory().size(), 0);
	}
}
