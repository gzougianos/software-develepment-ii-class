package controller;

import controller.commands.CommandFactory;
import view.MainView;

public class MainViewController {
	private OpeningViewController openingViewController;
	private EditorViewController editorViewController;
	private MainView mainView;
	private CommandFactory commandFactory;

	public MainViewController(CommandFactory commandFactory, MainView mainView) {
		this.mainView = mainView;
		this.commandFactory = commandFactory;
		initChildControllers();
	}

	private void initChildControllers() {
		openingViewController = new OpeningViewController(mainView.getOpeningView(), commandFactory);
		editorViewController = new EditorViewController(mainView.getEditorView(), commandFactory);
	}

}