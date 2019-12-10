package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.commands.CreateCommand;
import gr.uoi.cs.controller.commands.EditCommand;
import gr.uoi.cs.controller.commands.EnableVersionsManagementCommand;
import gr.uoi.cs.controller.commands.RollbackToPreviousVersionCommand;
import gr.uoi.cs.model.strategies.StableVersionsStrategy;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.model.strategies.VolatileVersionsStrategy;
import gr.uoi.cs.view.LatexEditorView;

class RollbackToPreviousVersionCommandTest {
	private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager(null, latexEditorView);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	private EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand(versionsManager);
	private RollbackToPreviousVersionCommand rollback = new RollbackToPreviousVersionCommand(versionsManager);
	
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorView.setType("articleTemplate");
		latexEditorView.setVersionsManager(versionsManager);
		createCommand.execute();
		String actualContents = latexEditorView.getCurrentDocument().getContents();
		latexEditorView.setStrategy("stable");
		enableCommand.execute();
		latexEditorView.setText("test edit contents\n");
		editCommand.execute();
		rollback.execute();
		String contents = latexEditorView.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
}
