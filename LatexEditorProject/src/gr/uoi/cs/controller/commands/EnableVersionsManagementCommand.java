package gr.uoi.cs.controller.commands;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;

public class EnableVersionsManagementCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public EnableVersionsManagementCommand(VersionsManager versionsManager, MainView mainView) {
		super();
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		mainView.getEditorView().getDisableStrategyButton().setEnabled(true);
		String versionStrategyId = getVersionStrategyIdAccordingToWhichButtonIsSelected();
		versionsManager.enable();
		versionsManager.changeStrategy(versionStrategyId);
	}

	private String getVersionStrategyIdAccordingToWhichButtonIsSelected() {
		EditorView editorView = mainView.getEditorView();
		if (editorView.getVolatileStrategyButton().isSelected())
			return VersionsStrategy.VOLATILE;
		if (editorView.getStableStrategyButton().isSelected())
			return VersionsStrategy.STABLE;
		return null;
	}

}
