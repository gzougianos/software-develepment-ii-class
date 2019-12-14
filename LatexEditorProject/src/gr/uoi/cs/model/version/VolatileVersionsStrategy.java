package gr.uoi.cs.model.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gr.uoi.cs.model.Document;

public class VolatileVersionsStrategy implements VersionsStrategy {
	private static Map<String, Document> documents = new HashMap<>();

	@Override
	public void putVersion(Document document, int versionId) {
		String key = nameDocumentVersion(document, versionId);
		documents.put(key, document.clone());
	}

	private String nameDocumentVersion(Document document, int versionId) {
		return document.getCreatedTime() + "-" + versionId;
	}

	@Override
	public Document getVersion(Document document, int versionId) throws VersionNotFoundException {
		String key = nameDocumentVersion(document, versionId);
		Document doc = documents.get(key);
		if (doc == null)
			throw new VersionNotFoundException(String.format("Version %s not found for the document.", versionId));

		return doc;
	}

	@Override
	public void setEntireHistory(Document document, List<Document> documents) {
		Map<String, Document> docs = documents.stream()
				.collect(Collectors.toMap(d -> nameDocumentVersion(d, d.getVersionId()), d -> d));
		VolatileVersionsStrategy.documents.putAll(docs);
	}

	@Override
	public List<Document> getEntireHistory(Document document) {
		//@formatter:off
		return documents.keySet().stream()
				.filter(k -> k.startsWith(String.valueOf(document.getCreatedTime())))
				.map(documents::get)
				.collect(Collectors.toList());
		//@formatter:on
	}

}
