package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.view.MainView;

public class DisableVersionStrategyCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public DisableVersionStrategyCommand(VersionsManager versionsManager, MainView mainView) {
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		mainView.getEditorView().clearStrategySelection();
		versionsManager.disable();
		mainView.getEditorView().getDisableStrategyButton().setEnabled(false);
		mainView.getEditorView().getRollbackButton().setEnabled(false);
	}

}
