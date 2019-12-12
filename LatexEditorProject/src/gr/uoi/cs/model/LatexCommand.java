package gr.uoi.cs.model;

public class LatexCommand {
	private String name;
	private String content;
	private String allowedTypes;
	private String disallowedTypes;

	public LatexCommand(String name, String content, String allowedTypes, String disallowedTypes, String category) {
		super();
		this.name = name;
		this.content = content;
		this.allowedTypes = allowedTypes;
		this.disallowedTypes = disallowedTypes;
		this.category = category;
	}

	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAllowedTypes() {
		return allowedTypes;
	}

	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}

	public String getDisallowedTypes() {
		return disallowedTypes;
	}

	public void setDisallowedTypes(String disallowedTypes) {
		this.disallowedTypes = disallowedTypes;
	}

	public boolean allowsType(DocumentType type) {
		if (getDisallowedTypes().trim().toLowerCase().contains(type.toString().toLowerCase()))
			return false;
		return getAllowedTypes().trim().equalsIgnoreCase("all")
				|| getAllowedTypes().trim().toLowerCase().contains(type.toString().toLowerCase());
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "LatexCommand [name=" + name + ", content=" + content + ", allowedTypes=" + allowedTypes
				+ ", disallowedTypes=" + disallowedTypes + ", category=" + category + "]";
	}

}
