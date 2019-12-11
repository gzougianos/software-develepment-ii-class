package gr.uoi.cs.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.OpeningView;

public class MainFrame extends JFrame implements MainView {
	private static final long serialVersionUID = 1320577016945996814L;
	private static final Dimension minimumSize = new Dimension(400, 400);
	private OpeningView openingView;
	private EditorView editorView;

	public MainFrame() {
		super("Latex Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		openingView = new OpeningPanel();
		editorView = new EditorPanel();

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
		setJMenuBar(getEditorView().getMenuBar());
		removeAllAndAdd(getEditorView().component());
	}

	@Override
	public void showOpeningView() {
		setJMenuBar(null);
		removeAllAndAdd(getOpeningView().component());
	}

	private void removeAllAndAdd(Component c) {
		getContentPane().removeAll();
		getContentPane().add(c);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	@Override
	public JFrame component() {
		return this;
	}

}
