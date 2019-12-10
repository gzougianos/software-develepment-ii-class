package controller.commands;

import gr.uoi.cs.VersionsManager;

public class DisableVersionsManagementCommand implements Command {

	private VersionsManager versionsManager;
	
	public DisableVersionsManagementCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.disable();
	}

}
