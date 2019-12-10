package gr.uoi.cs.support;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TexFileFilter extends FileFilter {
	private static final String texFilesSuffix = ".tex";
	private static final String description = "Tex files";

	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().toLowerCase().endsWith(texFilesSuffix);
	}

	@Override
	public String getDescription() {
		return description;
	}

}
