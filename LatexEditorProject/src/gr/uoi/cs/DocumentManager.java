package gr.uoi.cs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.Document;
import model.DocumentType;

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
