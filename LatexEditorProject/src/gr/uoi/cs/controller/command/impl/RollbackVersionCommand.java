package gr.uoi.cs.controller.command.impl;

import javax.swing.JOptionPane;

import gr.uoi.cs.EncryptionManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.version.VersionNotFoundException;
import gr.uoi.cs.view.MainView;

public class RollbackVersionCommand implements Command {
	private VersionsManager versionsManager;
	private MainView mainView;
	private EncryptionManager encryptionManager;

	public RollbackVersionCommand(VersionsManager versionsManager, EncryptionManager encryptionManager,
			MainView mainView) {
		this.versionsManager = versionsManager;
		this.encryptionManager = encryptionManager;
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		Document document = mainView.getEditorView().getCurrentDocument();
		try {
			versionsManager.rollback(document);
			if (document.isEncrypted()) {
				encryptionManager.decrypt(document);
			}
			mainView.getEditorView().getEditorComponent().setText(document.getContents());
			// Controller will disable it so we must enable it again
			mainView.getEditorView().getSaveFileButton().setEnabled(true);
		} catch (VersionNotFoundException e) {
			JOptionPane.showMessageDialog(mainView.component(), "There is no previous version.", "Rollback",
					JOptionPane.ERROR_MESSAGE);
			// e.printStackTrace();
		}

	}

}
