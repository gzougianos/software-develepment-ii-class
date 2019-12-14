package gr.uoi.cs.model.version;

public class VersionNotFoundException extends Exception {
	private static final long serialVersionUID = 4236967958601668479L;

	public VersionNotFoundException() {
		super();
	}

	public VersionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionNotFoundException(String message) {
		super(message);
	}

	public VersionNotFoundException(Throwable cause) {
		super(cause);
	}

}
