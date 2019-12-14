package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.EncryptionManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.view.MainView;

public class EnableEncryptionStrategyCommand implements Command {
	private EncryptionManager encryptionManager;
	private MainView mainView;

	public EnableEncryptionStrategyCommand(EncryptionManager encryptionManager, MainView mainView) {
		this.encryptionManager = encryptionManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		String encryptionStrategyId = findEncryptionStrategyFromSelectionInView();
		encryptionManager.changeStrategy(encryptionStrategyId);
		mainView.getEditorView().getCurrentDocument().setEncryptionAlgorithm(encryptionStrategyId);
		mainView.getEditorView().getDisableEncryptionStrategyButton().setEnabled(true);
	}

	private String findEncryptionStrategyFromSelectionInView() {
		if (mainView.getEditorView().getAtbashEncryptionStrategyButton().isSelected())
			return EncryptionStrategy.ATBASH;
		else if (mainView.getEditorView().getRot13EncryptionStrategyButton().isSelected())
			return EncryptionStrategy.ROT_13;
		return null;
	}

}
