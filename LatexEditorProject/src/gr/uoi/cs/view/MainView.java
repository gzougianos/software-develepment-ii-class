package gr.uoi.cs.view;

import javax.swing.JFrame;

import gr.uoi.cs.model.Document;

public interface MainView extends View<JFrame> {
	OpeningView getOpeningView();

	EditorView getEditorView();

	void showOpeningView();

	void showEditorView();

	Document getCurrentDocument();

	void setCurrentDocument(Document document);
}
