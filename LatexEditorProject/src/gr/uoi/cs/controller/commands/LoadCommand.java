package gr.uoi.cs.controller.commands;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.support.TexFileFilter;
import gr.uoi.cs.view.MainView;

public class LoadCommand implements Command {
	private static final File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
	private DocumentManager documentManager;
	private MainView mainView;
	private Supplier<File> documentFileSupplier;

	public LoadCommand(DocumentManager documentManager, MainView mainView, Supplier<File> documentFileSupplier) {
		this.documentManager = documentManager;
		this.mainView = mainView;
		this.documentFileSupplier = documentFileSupplier;
	}

	public LoadCommand(DocumentManager documentManager, MainView mainView) {
		this.documentManager = documentManager;
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
			mainView.setCurrentDocument(document);
			mainView.showEditorView();
			mainView.getEditorView().getEditorComponent().setText(document.getContents());
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
