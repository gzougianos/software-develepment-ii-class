package gr.uoi.cs.controller.commands;

import java.io.File;
import java.util.function.Supplier;

import javax.swing.JFileChooser;

import gr.uoi.cs.DocumentManager;
import gr.uoi.cs.view.MainView;

public class LoadCommand implements Command {
	private static final File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
	private DocumentManager documentManager;
	private MainView mainView;
	private Supplier<File> documentFileSupplier;

	public LoadCommand(DocumentManager documentManager, MainView mainView, Supplier<File> fileSupplier) {
		this.documentManager = documentManager;
		this.mainView = mainView;
		this.documentFileSupplier = fileSupplier;
	}

	public LoadCommand(DocumentManager documentManager, MainView mainView) {
		this.documentManager = documentManager;
		this.mainView = mainView;
		this.documentFileSupplier = this::selectFileWithFileChooser;
	}

	@Override
	public void execute() {
		File documentFile = documentFileSupplier.get();
	}

	private File selectFileWithFileChooser() {
		JFileChooser chooser = new JFileChooser(desktopDirectory);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(mainView.component()) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

}
