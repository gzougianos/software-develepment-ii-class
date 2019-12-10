package gr.uoi.cs.controller.commands;

import gr.uoi.cs.VersionsManager;

public class AddLatexCommand implements Command  {
	private VersionsManager versionsManager;
	
	
	public AddLatexCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.saveContents();
	}

}