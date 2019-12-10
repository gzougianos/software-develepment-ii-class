package gr.uoi.cs.controller.commands;

public interface Command {
	String CREATE_DOCUMENT = "CREATE_DOCUMENT";
	String SAVE_DOCUMENT = "SAVE_DOCUMENT";
	String LOAD_DOCUMENT = "LOAD_DOCUMENT";
	String SHOW_OPENING_VIEW = "SHOW_OPENING_VIEW";
	String EXIT = "EXIT";

	public void execute();
}
