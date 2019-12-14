package gr.uoi.cs;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.model.encryption.EncryptionStrategyFactory;

public class EncryptionManager {

	public EncryptionManager() {
	}

	public void encrypt(Document document) {
		EncryptionStrategy strategy = EncryptionStrategyFactory
				.createEncryptionStrategy(document.getEncryptionAlgorithm());
		strategy.encrypt(document);
	}

	public void decrypt(Document document) {
		EncryptionStrategy strategy = EncryptionStrategyFactory
				.createEncryptionStrategy(document.getEncryptionAlgorithm());
		strategy.decrypt(document);
	}

}
