package controller.commands;

public interface Command {
	String CREATE_DOCUMENT = "CREATE_DOCUMENT";
	String LOAD_DOCUMENT = "LOAD_DOCUMENT";
	String EXIT = "EXIT";

	public void execute();
}
