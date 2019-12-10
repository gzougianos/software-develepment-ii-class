package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.commands.ChangeVersionsStrategyCommand;
import gr.uoi.cs.model.strategies.StableVersionsStrategy;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.model.strategies.VolatileVersionsStrategy;
import gr.uoi.cs.view.LatexEditorView;

class ChangeVersionsStrategyCommandTest {
	private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager(null, latexEditorView);
	private ChangeVersionsStrategyCommand changeCommand = new ChangeVersionsStrategyCommand(versionsManager);
	
	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorView.setType("articleTemplate");
		latexEditorView.setVersionsManager(versionsManager);
		latexEditorView.setStrategy("stable");
		changeCommand.execute();
		
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof VolatileVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorView.setType("articleTemplate");
		latexEditorView.setVersionsManager(versionsManager);
		latexEditorView.setStrategy("volatile");
		changeCommand.execute();
		
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof StableVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
		
}
