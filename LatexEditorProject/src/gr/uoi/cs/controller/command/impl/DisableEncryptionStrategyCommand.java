package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.view.MainView;

public class DisableEncryptionStrategyCommand implements Command {
	private MainView mainView;

	public DisableEncryptionStrategyCommand(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		mainView.getEditorView().clearEncryptionSelection();
		mainView.getEditorView().getDisableEncryptionStrategyButton().setEnabled(false);
		mainView.getEditorView().getCurrentDocument().setEncryptionAlgorithm(null);
	}

}
