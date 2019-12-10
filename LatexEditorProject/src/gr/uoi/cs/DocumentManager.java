package gr.uoi.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.DocumentType;

public class DocumentManager {
	private static final String templatesPackage = "/gr/uoi/cs/resource/templates/";
	private HashMap<DocumentType, Document> templates;

	public DocumentManager() {
		templates = new HashMap<DocumentType, Document>();
		Arrays.asList(DocumentType.values()).forEach(this::createPrototype);
	}

	public Document createDocument(DocumentType documentType) {
		return templates.get(documentType).clone();
	}

	public void saveDocument(Document document, File path) throws IOException {
		if (path.isDirectory())
			path = new File(path, "document.tex");

		document.setPath(path);
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
			outputStream.writeObject(document);
		}
	}

	public Document loadDocument(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file))) {
			Document doc = (Document) oos.readObject();
			doc.setPath(file);
			return doc;
		}
	}

	private Document createPrototype(DocumentType type) {
		Document document = new Document(type);
		try {
			String templateContent = loadTemplateForType(type);
			document.setContents(templateContent);
		} catch (IOException e) {
			System.err.println("There was an error while loading contents of template:" + type);
			e.printStackTrace();
		}
		templates.put(type, document);
		return document;
	}

	private String loadTemplateForType(DocumentType type) throws IOException {
		String fileName = type.toString().toLowerCase();
		String filePathInClassPath = DocumentManager.class.getResource(templatesPackage + fileName).getFile();
		File file = new File(filePathInClassPath);
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		StringBuilder sb = new StringBuilder();
		lines.forEach(line -> {
			sb.append(line);
			sb.append(System.lineSeparator());
		});
		return sb.toString();
	}

}
