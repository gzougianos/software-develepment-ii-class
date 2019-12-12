package gr.uoi.cs;

import javax.swing.SwingUtilities;

import gr.uoi.cs.controller.MainViewController;
import gr.uoi.cs.controller.command.CommandFactory;
import gr.uoi.cs.model.strategies.VersionsStrategyFactory;
import gr.uoi.cs.view.impl.MainFrame;

public class Launcher implements Runnable {
	private Launcher() {
	}

	@Override
	public void run() {
		MainFrame view = new MainFrame();
		DocumentManager documentManager = new DocumentManager();
		VersionsManager versionsManager = new VersionsManager(new VersionsStrategyFactory());
		LatexCommandManager latexCommandManager = new LatexCommandManager();
		CommandFactory commandFactory = new CommandFactory(documentManager, versionsManager, latexCommandManager, view);
		new MainViewController(commandFactory, view);
		view.setVisible(true);
	}

	public static void main(String[] args) {
		// All swing applications must run in event dispatch thread
		SwingUtilities.invokeLater(new Launcher());
	}
}
