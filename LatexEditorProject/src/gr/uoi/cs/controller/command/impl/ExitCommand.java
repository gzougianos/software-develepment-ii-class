package gr.uoi.cs.controller.command.impl;

import gr.uoi.cs.controller.command.Command;

public class ExitCommand implements Command {
	public ExitCommand() {
	}

	@Override
	public void execute() {
		System.exit(0);
	}
}
