package gr.uoi.cs.controller.commands;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.VersionsManager;
import gr.uoi.cs.model.Document;
import gr.uoi.cs.support.TexFileFilter;
import gr.uoi.cs.view.MainView;

public class SaveCommand implements Command {
	private static final File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
	private VersionsManager versionsManager;
	private DocumentManager documentManager;
	private MainView mainView;
	private Supplier<File> documentFileSupplier;

	public SaveCommand(VersionsManager versionsManager, DocumentManager documentManager, MainView mainView) {
		this.versionsManager = versionsManager;
		this.documentManager = documentManager;
		this.mainView = mainView;
		this.documentFileSupplier = this::selectFileWithFileChooser;
	}

	public SaveCommand(VersionsManager versionsManager, DocumentManager documentManager, MainView mainView,
			Supplier<File> documentFileSupplier) {
		this(versionsManager, documentManager, mainView);
		this.documentFileSupplier = documentFileSupplier;
	}

	@Override
	public void execute() {
		Document document = mainView.getEditorView().getCurrentDocument();
		document.setContents(mainView.getEditorView().getEditorComponent().getText());

		File file = document.getPath() != null ? document.getPath() : documentFileSupplier.get();

		if (file == null)
			return; // mostly when file chooser dialog closed

		if (!file.getName().toLowerCase().endsWith(".tex"))
			file = new File(file.getAbsolutePath() + ".tex");

		try {
			documentManager.saveDocument(document, file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainView.component(), "There was an error while trying to save document.",
					"Save document", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private File selectFileWithFileChooser() {
		JFileChooser chooser = new JFileChooser(desktopDirectory);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new TexFileFilter());
		if (chooser.showSaveDialog(mainView.component()) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

}
