package gr.uoi.cs;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.version.VersionNotFoundException;
import gr.uoi.cs.model.version.VersionsStrategy;
import gr.uoi.cs.model.version.VersionsStrategyFactory;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private VersionsStrategyFactory strategyFactory;

	public VersionsManager(VersionsStrategyFactory strategyFactory) {
		this.strategyFactory = strategyFactory;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public void changeStrategy(String strategyId, Document document) {
		VersionsStrategy newStrategy = strategyFactory.createStrategy(strategyId);

		if (!strategyChanged(newStrategy))
			return; // Strategy was not changed

		if (strategy != null)
			newStrategy.setEntireHistory(document, strategy.getEntireHistory(document));
		strategy = newStrategy;
	}

	public void commitVersion(Document document) {
		if (!isEnabled())
			throw new RuntimeException("Cannot keep version of document. Version Manager is currently disabled.");

		document.setVersionId(document.getVersionId() + 1);
		strategy.putVersion(document, document.getVersionId());
	}

	public void rollback(Document document) throws VersionNotFoundException {
		if (!isEnabled())
			throw new RuntimeException(
					"Cannot rollback to previous version of the document. Version Manager is currently disabled.");

		int previousVersion = document.getVersionId() - 1;
		Document previousVersionDocument = strategy.getVersion(document, previousVersion);

		document.setAuthor(previousVersionDocument.getAuthor());
		document.setContents(previousVersionDocument.getContents());
		document.setDate(previousVersionDocument.getDate());
		document.setCopyright(previousVersionDocument.getCopyright());
		document.setVersionId(previousVersion);
	}

	private boolean strategyChanged(VersionsStrategy newStrategy) {
		if (strategy == null)
			return true;

		return strategy.getClass() != newStrategy.getClass();
	}
}
