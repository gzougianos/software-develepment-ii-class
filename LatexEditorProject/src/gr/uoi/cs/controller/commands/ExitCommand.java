package gr.uoi.cs.controller.commands;

public class ExitCommand implements Command {
	public ExitCommand() {
	}

	@Override
	public void execute() {
		System.exit(0);
	}
}
