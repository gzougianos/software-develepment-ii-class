package gr.uoi.cs.model.version;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import gr.uoi.cs.model.Document;

public class StableVersionsStrategy implements VersionsStrategy {
	private static File versionsDirectory;

	public StableVersionsStrategy() {
		createVersionsDirectoryBasedOnOperatinSystem();
	}

	private void createVersionsDirectoryBasedOnOperatinSystem() {
		String operatingSystem = (System.getProperty("os.name")).toLowerCase();
		if (operatingSystem.contains("win"))
			versionsDirectory = new File(System.getenv("AppData"), "LatexEditorSoftDevII");
		else
			// TODO: confirm that on linux/mac?
			versionsDirectory = new File(System.getProperty("user.home"), "LatexEditorSoftDevII");

		if (!versionsDirectory.exists())
			versionsDirectory.mkdirs();
	}

	@Override
	public void putVersion(Document document, final int versionId) {
		String fileName = nameDocumentVersion(document, versionId);
		File file = new File(versionsDirectory, fileName);
		try {
			saveDocument(file, document);
		} catch (IOException exception) {
			System.err.println("There was an error while keeping version of document.");
			exception.printStackTrace();
		}
	}

	@Override
	public Document getVersion(Document document, final int versionId) throws VersionNotFoundException {
		String fileName = nameDocumentVersion(document, versionId);
		File file = new File(versionsDirectory, fileName);
		if (!file.exists())
			throw new VersionNotFoundException(
					String.format("Version %s not found for the document. File %s does not exist", versionId,
							file.getAbsolutePath()));

		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file))) {
			Document doc = (Document) oos.readObject();
			return doc;
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("There was an error while loading version " + versionId + " of document.");
			e.printStackTrace();
		}
		return null;
	}

	private void saveDocument(File file, Document document) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			outputStream.writeObject(document);
		}
	}

	private Document loadDocument(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file))) {
			Document doc = (Document) oos.readObject();
			return doc;
		}
	}

	private String nameDocumentVersion(Document document, int versionId) {
		return document.getCreatedTime() + "-" + versionId;
	}

	@Override
	public void setEntireHistory(Document document, List<Document> documents) {
		try {
			clearAllVersions(document);
		} catch (IOException e) {
			System.err.println("There was an error while clearing version history of the document.");
			e.printStackTrace();
		}
		documents.forEach(d -> putVersion(d, d.getVersionId()));
	}

	private void clearAllVersions(Document document) throws IOException {
		for (File f : versionFiles(document)) {
			Files.delete(f.toPath());
		}
	}

	@Override
	public List<Document> getEntireHistory(Document document) {
		List<Document> documents = new ArrayList<Document>();
		File[] versionFiles = versionFiles(document);
		for (File f : versionFiles) {
			try {
				documents.add(loadDocument(f));
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		return documents;
	}

	private File[] versionFiles(Document document) {
		return versionsDirectory.listFiles((FileFilter) f -> {
			return f.getName().startsWith(String.valueOf(document.getCreatedTime()));
		});
	}

}
