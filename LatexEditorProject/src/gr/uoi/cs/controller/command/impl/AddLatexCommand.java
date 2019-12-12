package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.controller.command.Command;

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
