package gr.uoi.cs.controller.commands;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
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
			case Command.SAVE_DOCUMENT:
				return new SaveCommand(versionsManager, documentManager, mainView);
			case Command.CREATE_DOCUMENT:
				return new CreateCommand(documentManager, versionsManager, mainView);
			case Command.LOAD_DOCUMENT:
				return new LoadCommand(documentManager, mainView);
			case Command.EXIT:
				return new ExitCommand();
		}
		return null;
	}
}
