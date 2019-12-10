package view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import model.DocumentType;
import view.OpeningView;

public class OpeningPanel extends JPanel implements OpeningView {
	private static final long serialVersionUID = -1489924684008935564L;
	private static final Border contentPaneBorder = BorderFactory.createEmptyBorder(15, 30, 30, 30);
	private static final int componentGapHorizontally = 50;
	private static final int componentGapVertically = 50;
	private JPanel createOrLoadPanel;
	private JButton showDocumentTypeSelectionButton;
	private JButton openExistingDocumentButton;
	private JButton exitButton;
	private JPanel documentTypeSelectionPanel;
	private DocumentType selectedDocumentType = DocumentType.EMPTY;
	private JButton backToOpeningScreenButton;
	private JButton createDocumentButton;

	public OpeningPanel() {
		super(new BorderLayout());
		setBorder(contentPaneBorder);

		createOrLoadPanel = createCreateOrLoadPanel();
		documentTypeSelectionPanel = createDocumentTypeSelectionPanel();

	}

	@Override
	public JPanel getCreateOrLoadPanel() {
		return createOrLoadPanel;
	}

	@Override
	public JPanel getDocumentTypeSelectionPanel() {
		return documentTypeSelectionPanel;
	}

	private JPanel createDocumentTypeSelectionPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));

		JLabel hintMessage = new JLabel("Choose template. (Leave empty for blank document)");
		hintMessage.setHorizontalTextPosition(JLabel.CENTER);
		hintMessage.setHorizontalAlignment(JLabel.CENTER);
		panel.add(hintMessage, BorderLayout.PAGE_START);

		JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
		ButtonGroup buttonGroup = createDocumentTypeRadioButtonGroup();
		for (AbstractButton button : Collections.list(buttonGroup.getElements())) {
			buttonPanel.add(button);
		}
		panel.add(buttonPanel, BorderLayout.CENTER);

		JPanel actionsPanel = new JPanel(new GridLayout(0, 2, componentGapHorizontally, componentGapVertically));
		backToOpeningScreenButton = new JButton("Back");
		actionsPanel.add(backToOpeningScreenButton);

		createDocumentButton = new JButton("Create");
		actionsPanel.add(createDocumentButton);

		panel.add(actionsPanel, BorderLayout.PAGE_END);
		return panel;
	}

	private ButtonGroup createDocumentTypeRadioButtonGroup() {
		ButtonGroup group = new ButtonGroup();
		for (DocumentType type : DocumentType.values()) {
			if (type == DocumentType.EMPTY)
				continue;
			JRadioButton button = new JRadioButton(type.toBeautifulString());
			button.addActionListener(e -> {
				// When the button is double selected, clear the selection for EMPTY doc type
				if (selectedDocumentType == type) {
					group.clearSelection();
					selectedDocumentType = DocumentType.EMPTY;
				} else {
					selectedDocumentType = type;
				}
			});
			group.add(button);
		}
		return group;
	}

	private JPanel createCreateOrLoadPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, componentGapHorizontally, componentGapVertically));

		showDocumentTypeSelectionButton = new JButton("Create new document");
		panel.add(showDocumentTypeSelectionButton);

		openExistingDocumentButton = new JButton("Open existing document");
		panel.add(openExistingDocumentButton);

		panel.add(Box.createRigidArea(new Dimension(1, 1))); // irrelevant, layout will ignore it

		exitButton = new JButton("Exit");
		panel.add(exitButton);
		return panel;
	}

	@Override
	public JPanel component() {
		return this;
	}

	@Override
	public JButton getShowDocumentTypeSelectionButton() {
		return showDocumentTypeSelectionButton;
	}

	@Override
	public JButton getOpenExistingDocumentButton() {
		return openExistingDocumentButton;
	}

	@Override
	public JButton getExitButton() {
		return exitButton;
	}

	@Override
	public DocumentType getSelectedDocumentType() {
		return selectedDocumentType;
	}

	@Override
	public JButton getCreateDocumentButton() {
		return createDocumentButton;
	}

	@Override
	public JButton getBackToOpeningScreenButton() {
		return backToOpeningScreenButton;
	}

}
