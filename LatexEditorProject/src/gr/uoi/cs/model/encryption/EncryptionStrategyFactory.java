package gr.uoi.cs.model.encryption;

public class EncryptionStrategyFactory {
	private EncryptionStrategyFactory() {
	}

	public static EncryptionStrategy createEncryptionStrategy(String encryptionStrategyId) {
		switch (encryptionStrategyId) {
			case EncryptionStrategy.ATBASH:
				return new AtbashEncryptionStrategy();
			case EncryptionStrategy.ROT_13:
				return new Rot13EncryptionStrategy();
			default:
				return new DefaultEncryptionStrategy();
		}
	}
}
