package controller;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import controller.commands.Command;
import controller.commands.CommandFactory;
import view.OpeningView;

public class OpeningViewController {
	private OpeningView openingView;
	private CommandFactory commandFactory;

	public OpeningViewController(OpeningView openingView, CommandFactory commandFactory) {
		this.openingView = openingView;
		this.commandFactory = commandFactory;

		showCreateOrLoadPanel();// The initial state of the view
		registerListenersToViewComponents();
	}

	private void registerListenersToViewComponents() {
		openingView.getExitButton().addActionListener(e -> exit());
		openingView.getShowDocumentTypeSelectionButton().addActionListener(e -> showDocumentTypeSelection());
		openingView.getOpenExistingDocumentButton().addActionListener(e -> openExistingDocument());
		openingView.getBackToOpeningScreenButton().addActionListener(e -> showCreateOrLoadPanel());
		openingView.getCreateDocumentButton().addActionListener(e -> createDocument());
	}

	private void createDocument() {
		commandFactory.createCommand(Command.CREATE_DOCUMENT).execute();
	}

	private void openExistingDocument() {
		commandFactory.createCommand(Command.LOAD_DOCUMENT).execute();
	}

	private void showDocumentTypeSelection() {
		removeAllAndAdd(openingView.getDocumentTypeSelectionPanel());
	}

	private void showCreateOrLoadPanel() {
		removeAllAndAdd(openingView.getCreateOrLoadPanel());
	}

	private void removeAllAndAdd(Component component) {
		JPanel panel = openingView.component();
		panel.removeAll();
		panel.add(component, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
	}

	public void exit() {
		commandFactory.createCommand(Command.EXIT).execute();
	}

}
