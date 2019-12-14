package gr.uoi.cs.model.encryption;

import gr.uoi.cs.model.Document;

public class AtbashEncryptionStrategy implements EncryptionStrategy {

	@Override
	public void encrypt(Document document) {
		System.out.println("ATBASH");
	}

	@Override
	public void decrypt(Document document) {
		System.out.println("DEATBASH");
	}

}
