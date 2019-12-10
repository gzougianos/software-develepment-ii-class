package gr.uoi.cs.model.strategies;

import java.util.List;

import gr.uoi.cs.model.Document;

public interface VersionsStrategy {
	String VOLATILE = "VOLATILE";
	String STABLE = "STABLE";

	public void putVersion(Document document);

	public Document getVersion();

	public void setEntireHistory(List<Document> documents);

	public List<Document> getEntireHistory();

	public void removeVersion();
}
