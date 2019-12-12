package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.strategies.VersionsStrategy;
import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;

public class EnableVersionStrategyCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;

	public EnableVersionStrategyCommand(VersionsManager versionsManager, MainView mainView) {
		super();
		this.versionsManager = versionsManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		Document document = mainView.getEditorView().getCurrentDocument();
		mainView.getEditorView().getDisableStrategyButton().setEnabled(true);
		mainView.getEditorView().getRollbackButton().setEnabled(true);
		String versionStrategyId = getVersionStrategyIdAccordingToWhichButtonIsSelected();
		boolean wasEnabled = versionsManager.isEnabled();
		versionsManager.enable();
		versionsManager.changeStrategy(versionStrategyId, document);
		if (!wasEnabled)
			versionsManager.commitVersion(document);
	}

	private String getVersionStrategyIdAccordingToWhichButtonIsSelected() {
		EditorView editorView = mainView.getEditorView();
		if (editorView.getVolatileStrategyButton().isSelected()) {
			editorView.getStableStrategyButton().setSelected(false);
			return VersionsStrategy.VOLATILE;
		}
		if (editorView.getStableStrategyButton().isSelected()) {
			editorView.getVolatileStrategyButton().setSelected(false);
			return VersionsStrategy.STABLE;
		}
		return null;
	}

}
