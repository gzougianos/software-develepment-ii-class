package gr.uoi.cs.view;

import javax.swing.JFrame;

public interface MainView extends View<JFrame> {
	OpeningView getOpeningView();

	EditorView getEditorView();

	void showOpeningView();

	void showEditorView();
}
