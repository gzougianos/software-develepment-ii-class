package gr.uoi.cs.controller.commands;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.view.MainView;

public class DisableVersionsManagementCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public DisableVersionsManagementCommand(VersionsManager versionsManager, MainView mainView) {
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		mainView.getEditorView().getDisableStrategyButton().setSelected(false);
		mainView.getEditorView().getVolatileStrategyButton().setSelected(false);
		versionsManager.disable();
		mainView.getEditorView().getDisableStrategyButton().setEnabled(false);
		mainView.getEditorView().getRollbackButton().setEnabled(false);
	}

}
