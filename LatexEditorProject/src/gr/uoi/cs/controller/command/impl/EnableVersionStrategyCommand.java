package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.version.VersionsStrategy;
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
		EditorView editorView = mainView.getEditorView();
		Document document = editorView.getCurrentDocument();
		editorView.getDisableVersionStrategyButton().setEnabled(true);
		editorView.getRollbackButton().setEnabled(true);
		String versionStrategyId = getVersionStrategyIdAccordingToWhichButtonIsSelected();
		boolean wasEnabled = versionsManager.isEnabled();
		versionsManager.enable();
		versionsManager.changeStrategy(versionStrategyId, document);
		if (!wasEnabled) {
			String previousContents = document.getContents();
			document.setContents(editorView.getEditorComponent().getText());
			if (versionsManager.isEnabled())
				versionsManager.commitVersion(document);
			document.setContents(previousContents);
		}
	}

	private String getVersionStrategyIdAccordingToWhichButtonIsSelected() {
		EditorView editorView = mainView.getEditorView();
		if (editorView.getVolatileVersionStrategyButton().isSelected()) {
			editorView.getStableVersionStrategyButton().setSelected(false);
			return VersionsStrategy.VOLATILE;
		}
		if (editorView.getStableVersionStrategyButton().isSelected()) {
			editorView.getVolatileVersionStrategyButton().setSelected(false);
			return VersionsStrategy.STABLE;
		}
		return null;
	}

}
