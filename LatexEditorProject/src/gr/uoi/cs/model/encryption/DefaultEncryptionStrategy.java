package gr.uoi.cs.model.encryption;

import gr.uoi.cs.model.Document;

public class DefaultEncryptionStrategy implements EncryptionStrategy {

	@Override
	public void encrypt(Document document) {
		document.setEncryptionAlgorithm(null);
		// do nothing
	}

	@Override
	public void decrypt(Document document) {
		// do nothing
	}

}
