package gr.uoi.cs.controller.command.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.LatexCommandManager;
import gr.uoi.cs.controller.command.Command;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.LatexCommand;
import gr.uoi.cs.support.TexFileFilter;
import gr.uoi.cs.view.MainView;

public class LoadExistingDocumentCommand implements Command {
	private static final File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
	private DocumentManager documentManager;
	private MainView mainView;
	private Supplier<File> documentFileSupplier;
	private LatexCommandManager latexCommandManager;

	public LoadExistingDocumentCommand(DocumentManager documentManager, LatexCommandManager latexCommandManager,
			MainView mainView, Supplier<File> documentFileSupplier) {
		this.documentManager = documentManager;
		this.latexCommandManager = latexCommandManager;
		this.mainView = mainView;
		this.documentFileSupplier = documentFileSupplier;
	}

	public LoadExistingDocumentCommand(DocumentManager documentManager, LatexCommandManager latexCommandManager,
			MainView mainView) {
		this.documentManager = documentManager;
		this.latexCommandManager = latexCommandManager;
		this.mainView = mainView;
		this.documentFileSupplier = this::selectFileWithFileChooser;
	}

	@Override
	public void execute() {
		File documentFile = documentFileSupplier.get();
		if (documentFile == null) // mostly when file chooser dialog closed
			return;

		try {
			Document document = documentManager.loadDocument(documentFile);
			mainView.getEditorView().setCurrentDocument(document);
			mainView.getEditorView().getEditorComponent().setText(document.getContents());
			mainView.showEditorView();

			Map<String, List<LatexCommand>> commandsForThisType = latexCommandManager
					.getCommandsForDocumentType(document.getType());
			mainView.getEditorView().setLatexCommands(commandsForThisType);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mainView.component(), "There was an error while loading document.",
					"Load Document", JOptionPane.ERROR_MESSAGE);
		}
	}

	private File selectFileWithFileChooser() {
		JFileChooser chooser = new JFileChooser(desktopDirectory);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new TexFileFilter());

		if (chooser.showOpenDialog(mainView.component()) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

}
