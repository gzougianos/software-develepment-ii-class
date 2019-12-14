package gr.uoi.cs;

import gr.uoi.cs.model.Document;
import gr.uoi.cs.model.encryption.EncryptionStrategy;
import gr.uoi.cs.model.encryption.EncryptionStrategyFactory;

public class EncryptionManager {
	private EncryptionStrategyFactory encryptionStrategyFactory;
	private EncryptionStrategy strategy;

	public EncryptionManager(EncryptionStrategyFactory encryptionStrategyFactory) {
		this.encryptionStrategyFactory = encryptionStrategyFactory;
	}

	public void encrypt(Document document) {
		strategy.encrypt(document);
	}

	public void decrypt(Document document) {
		strategy.decrypt(document);
	}

}
