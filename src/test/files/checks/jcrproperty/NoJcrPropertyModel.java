package com.example;

import com.cognifide.slice.mapper.annotation.SliceResource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

@SliceResource
public class NoJcrPropertyModel {

	private final String label;

	public NoJcrPropertyModel(final String path) {
		this.label = createLabel(path);
	}

	private String createLabel(final String path) {
		String result = StringUtils.substringAfterLast(path, "/");
		result = StringUtils.substringBeforeLast(result, ".");
		return WordUtils.capitalize(result);
	}

	public String getLabel() {
		return label;
	}

}
