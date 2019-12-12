package gr.uoi.cs.model.strategies;

import java.util.List;

import gr.uoi.cs.model.Document;

public interface VersionsStrategy {
	String VOLATILE = "VOLATILE";
	String STABLE = "STABLE";

	public void putVersion(Document document, int versionId);

	public Document getVersion(Document document, int versionId) throws VersionNotFoundException;

	public void setEntireHistory(Document document, List<Document> documents);

	public List<Document> getEntireHistory(Document document);

}
