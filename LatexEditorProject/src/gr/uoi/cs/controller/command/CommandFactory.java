package gr.uoi.cs.controller.command;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.impl.CreateCommand;
import gr.uoi.cs.controller.command.impl.DisableVersionsManagementCommand;
import gr.uoi.cs.controller.command.impl.EnableVersionsManagementCommand;
import gr.uoi.cs.controller.command.impl.ExitCommand;
import gr.uoi.cs.controller.command.impl.KeepVersionCommand;
import gr.uoi.cs.controller.command.impl.LoadCommand;
import gr.uoi.cs.controller.command.impl.RollbackToPreviousVersionCommand;
import gr.uoi.cs.controller.command.impl.SaveCommand;
import gr.uoi.cs.controller.command.impl.ShowOpeningViewCommand;
import gr.uoi.cs.view.MainView;

public class CommandFactory {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainView mainView;

	public CommandFactory(DocumentManager documentManager, VersionsManager versionsManager, MainView mainView) {
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	public Command createCommand(String commandId) {
		switch (commandId) {
			case Command.ROLLBACK_TO_PREVIOUS_VERSION:
				return new RollbackToPreviousVersionCommand(versionsManager, mainView);
			case Command.KEEP_VERSION:
				return new KeepVersionCommand(versionsManager, mainView);
			case Command.ENABLE_VERSION_STRATEGY:
				return new EnableVersionsManagementCommand(versionsManager, mainView);
			case Command.DISABLE_VERSION_STRATEGY:
				return new DisableVersionsManagementCommand(versionsManager, mainView);
			case Command.SAVE_DOCUMENT:
				return new SaveCommand(versionsManager, documentManager, mainView);
			case Command.CREATE_DOCUMENT:
				return new CreateCommand(documentManager, versionsManager, mainView);
			case Command.LOAD_DOCUMENT:
				return new LoadCommand(documentManager, mainView);
			case Command.SHOW_OPENING_VIEW:
				return new ShowOpeningViewCommand(mainView);
			case Command.EXIT:
				return new ExitCommand();
		}
		return null;
	}
}