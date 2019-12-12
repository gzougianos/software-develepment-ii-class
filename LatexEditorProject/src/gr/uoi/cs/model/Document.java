package gr.uoi.cs.model;

import java.io.File;
import java.io.Serializable;

public class Document implements Serializable {
	private static final long serialVersionUID = 2226256781646208244L;
	private String author;
	private String date;
	private String copyright;
	private int versionId;
	private String contents;
	private DocumentType type;
	private long createdTime;
	private transient File path;

	private Document(DocumentType type, String author, String date, String copyright, int versionId, String contents) {
		this.author = author;
		this.date = date;
		this.copyright = copyright;
		this.versionId = versionId;
		this.contents = contents;
		this.type = type;
	}

	public Document(DocumentType type) {
		this(type, "", "", "", 0, "");
	}

	public DocumentType getType() {
		return type;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getVersionId() {
		return versionId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}

	@Override
	public Document clone() {
		Document clone = new Document(type, author, date, copyright, versionId, contents);
		clone.setCreatedTime(getCreatedTime());
		return clone;
	}

	/*
	 * Eclipse IDE auto-generated equals() method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (copyright == null) {
			if (other.copyright != null)
				return false;
		} else if (!copyright.equals(other.copyright))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (type != other.type)
			return false;
		if (versionId != other.versionId)
			return false;
		if (createdTime != other.createdTime)
			return false;
		return true;
	}

}
