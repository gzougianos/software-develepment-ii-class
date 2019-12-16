package gr.uoi.cs.controller.command;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.EncryptionManager;
import gr.uoi.cs.LatexCommandManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.impl.CommitVersionCommand;
import gr.uoi.cs.controller.command.impl.CreateDocumentCommand;
import gr.uoi.cs.controller.command.impl.DisableEncryptionStrategyCommand;
import gr.uoi.cs.controller.command.impl.DisableVersionStrategyCommand;
import gr.uoi.cs.controller.command.impl.EnableEncryptionStrategyCommand;
import gr.uoi.cs.controller.command.impl.EnableVersionStrategyCommand;
import gr.uoi.cs.controller.command.impl.ExitCommand;
import gr.uoi.cs.controller.command.impl.LoadExistingDocumentCommand;
import gr.uoi.cs.controller.command.impl.RollbackVersionCommand;
import gr.uoi.cs.controller.command.impl.SaveDocumentCommand;
import gr.uoi.cs.controller.command.impl.ShowOpeningViewCommand;
import gr.uoi.cs.view.MainView;

public class CommandFactory {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private MainView mainView;
	private LatexCommandManager latexCommandManager;
	private EncryptionManager encryptionManager;

	public CommandFactory(DocumentManager documentManager, VersionsManager versionsManager,
			LatexCommandManager latexCommandManager, EncryptionManager encryptionManager, MainView mainView) {
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.latexCommandManager = latexCommandManager;
		this.encryptionManager = encryptionManager;
		this.mainView = mainView;
	}

	public Command createCommand(String commandId) {
		switch (commandId) {
			case Command.ENABLE_ENCRYPTION_STRATEGY:
				return new EnableEncryptionStrategyCommand(mainView);
			case Command.DISABLE_ENCRYPTION_STRATEGY:
				return new DisableEncryptionStrategyCommand(mainView);
			case Command.ROLLBACK_TO_PREVIOUS_VERSION:
				return new RollbackVersionCommand(versionsManager, encryptionManager, mainView);
			case Command.COMMIT_VERSION:
				return new CommitVersionCommand(versionsManager, encryptionManager, mainView);
			case Command.ENABLE_VERSION_STRATEGY:
				return new EnableVersionStrategyCommand(versionsManager, mainView);
			case Command.DISABLE_VERSION_STRATEGY:
				return new DisableVersionStrategyCommand(versionsManager, mainView);
			case Command.SAVE_DOCUMENT:
				return new SaveDocumentCommand(documentManager, encryptionManager, mainView);
			case Command.CREATE_DOCUMENT:
				return new CreateDocumentCommand(documentManager, latexCommandManager, mainView);
			case Command.LOAD_DOCUMENT:
				return new LoadExistingDocumentCommand(documentManager, latexCommandManager, encryptionManager,
						mainView);
			case Command.SHOW_OPENING_VIEW:
				return new ShowOpeningViewCommand(mainView);
			case Command.EXIT:
				return new ExitCommand();
		}
		return null;
	}
}
