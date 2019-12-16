package gr.uoi.cs.controller.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.version.VersionsStrategyFactory;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.impl.MainFrame;

public class DisableVersionStrategyCommandTests {

	@Test
	public void disable() {
		VersionsManager manager = new VersionsManager(new VersionsStrategyFactory());
		manager.enable();
		assertTrue(manager.isEnabled());

		MainView view = new MainFrame();
		view.getEditorView().getVolatileVersionStrategyButton().setSelected(true);
		DisableVersionStrategyCommand command = new DisableVersionStrategyCommand(manager, view);
		command.execute();
		assertFalse(manager.isEnabled());
	}
}
