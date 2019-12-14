package gr.uoi.cs.model.encryption;

import gr.uoi.cs.model.Document;

public class Rot13EncryptionStrategy implements EncryptionStrategy {
	@Override
	public void encrypt(Document document) {
		document.setContents(rot13(document.getContents()));
	}

	@Override
	public void decrypt(Document document) {
		document.setContents(rot13(document.getContents()));
	}

	private String rot13(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c >= 'a' && c <= 'm')
				c += 13;
			else if (c >= 'A' && c <= 'M')
				c += 13;
			else if (c >= 'n' && c <= 'z')
				c -= 13;
			else if (c >= 'N' && c <= 'Z')
				c -= 13;
			sb.append(c);
		}
		return sb.toString();
	}
}
