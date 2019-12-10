package gr.uoi.cs.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.view.EditorView;
import gr.uoi.cs.view.MainView;
import gr.uoi.cs.view.OpeningView;

public class MainFrame extends JFrame implements MainView {
	private static final long serialVersionUID = 1320577016945996814L;
	private static final Dimension minimumSize = new Dimension(400, 400);
	private OpeningView openingView;
	private EditorView editorView;
	private JRootPane defaultRootPane;
	private Document document;

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

	@Override
	public Document getCurrentDocument() {
		return document;
	}

	@Override
	public void setCurrentDocument(Document document) {
		this.document = document;
	}
}
