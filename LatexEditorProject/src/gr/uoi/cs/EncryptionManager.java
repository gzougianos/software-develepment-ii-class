package gr.uoi.cs;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.model.encryption.EncryptionStrategyFactory;

public class EncryptionManager {
	private EncryptionStrategy strategy;

	public EncryptionManager() {
	}

	public void changeStrategy(String encryptionStrategyId) {
		strategy = EncryptionStrategyFactory.createEncryptionStrategy(encryptionStrategyId);
	}

	public void encrypt(Document document) {
		strategy.encrypt(document);
	}

	public void decrypt(Document document) {
		strategy.decrypt(document);
	}

}
