package view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import controller.MainViewController;
import controller.commands.CommandFactory;
import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import model.strategies.VersionsStrategyFactory;
import view.EditorView;
import view.MainView;
import view.OpeningView;

public class MainFrame extends JFrame implements MainView {
	private static final long serialVersionUID = 1320577016945996814L;
	private static final Dimension minimumSize = new Dimension(400, 400);
	private OpeningView openingView;
	private EditorView editorView;
	private JRootPane defaultRootPane;

	public MainFrame() {
		super("Latex Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		defaultRootPane = getRootPane();

		setLayout(new BorderLayout());

		openingView = new OpeningPanel();
		editorView = new EditorPane();

		showOpeningView();

		setMinimumSize(minimumSize);

		pack();
		setLocationByPlatform(true);
	}

	@Override
	public OpeningView getOpeningView() {
		return openingView;
	}

	@Override
	public EditorView getEditorView() {
		return editorView;
	}

	@Override
	public void showEditorView() {
		setRootPane(editorView.component());
		revalidate();
		repaint();
	}

	@Override
	public void showOpeningView() {
		setRootPane(defaultRootPane);
		getContentPane().removeAll();
		getContentPane().add(openingView.component());
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	@Override
	public JFrame component() {
		return this;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame view = new MainFrame();
			DocumentManager documentManager = new DocumentManager();
			VersionsManager versionsManager = new VersionsManager(new VersionsStrategyFactory());
			CommandFactory commandFactory = new CommandFactory(documentManager, versionsManager, view);
			new MainViewController(commandFactory, view);
			view.setVisible(true);
		});
	}

}
