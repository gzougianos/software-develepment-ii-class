package gr.uoi.cs.controller.commands;

import gr.uoi.cs.view.MainView;

public class ShowOpeningViewCommand implements Command {
	private MainView mainView;

	public ShowOpeningViewCommand(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void execute() {
		mainView.showOpeningView();
	}

}
