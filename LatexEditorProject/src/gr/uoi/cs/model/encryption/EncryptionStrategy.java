package gr.uoi.cs.model.encryption;

import gr.uoi.cs.model.Document;

public interface EncryptionStrategy {
	String ATBASH = "ATBASH";
	String ROT_13 = "ROT-13";
	String DEFAULT = "DEFAULT";
	String DISABLED = DEFAULT;

	void encrypt(Document document);

	void decrypt(Document document);
}
