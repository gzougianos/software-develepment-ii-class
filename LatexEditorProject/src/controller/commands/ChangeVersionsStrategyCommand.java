package controller.commands;

import gr.uoi.cs.VersionsManager;

public class ChangeVersionsStrategyCommand implements Command {
	private VersionsManager versionsManager;

	public ChangeVersionsStrategyCommand(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
//		versionsManager.changeStrategy();
	}

}
