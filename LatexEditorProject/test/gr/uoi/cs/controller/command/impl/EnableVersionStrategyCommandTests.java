package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.strategies.VersionsStrategyFactory;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class EnableVersionStrategyCommandTests {
	@Test
	public void enable() {
		VersionsManager manager = new VersionsManager(new VersionsStrategyFactory());
		assertFalse(manager.isEnabled());

		MainView view = new MainFrame();
		view.getEditorView().getVolatileStrategyButton().setSelected(true);
		EnableVersionStrategyCommand command = new EnableVersionStrategyCommand(manager, view);
		command.execute();
		assertTrue(manager.isEnabled());
	}
}
