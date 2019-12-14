package gr.uoi.cs.model.encryption;

import gr.uoi.cs.model.Document;

public class AtbashEncryptionStrategy implements EncryptionStrategy {

	@Override
	public void encrypt(Document document) {
		document.setContents(reverse(document.getContents()));
	}

	@Override
	public void decrypt(Document document) {
		document.setContents(reverse(document.getContents()));
	}

	private String reverse(String string) {
		StringBuilder sb = new StringBuilder();
		sb.append(string);

		return sb.reverse().toString();
	}

}
