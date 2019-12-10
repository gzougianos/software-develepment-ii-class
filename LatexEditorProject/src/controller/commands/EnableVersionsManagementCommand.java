package controller.commands;

import gr.uoi.cs.VersionsManager;

public class EnableVersionsManagementCommand implements Command {
	private VersionsManager versionsManager;
	
	public EnableVersionsManagementCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.enableStrategy();
	}

}
