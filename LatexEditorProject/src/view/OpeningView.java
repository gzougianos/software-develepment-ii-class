package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.DocumentType;

public interface OpeningView extends View<JPanel> {

	JPanel getDocumentTypeSelectionPanel();

	JPanel getCreateOrLoadPanel();

	JButton getShowDocumentTypeSelectionButton();

	JButton getOpenExistingDocumentButton();

	JButton getExitButton();

	JButton getCreateDocumentButton();

	JButton getBackToOpeningScreenButton();

	DocumentType getSelectedDocumentType();
}
