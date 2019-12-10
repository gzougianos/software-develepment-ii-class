package controller.commands;

import gr.uoi.cs.VersionsManager;

public class SaveCommand implements Command {
	private VersionsManager versionsManager;
	
	public SaveCommand(VersionsManager versionsManager) {
		// TODO Auto-generated constructor stub
		this.versionsManager = versionsManager;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.saveToFile();
	}

}
