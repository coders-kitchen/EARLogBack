package com.coderskitchen.earlogback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Model;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@Model
public class Dummy {

	String hello;
	Logger logger = LoggerFactory.getLogger(Dummy.class);
	private List<String> classContent;

	public String getHello() {
		logger.info("Said Hello!");

		getClass().getResource("logback.xml");

		return "hi!";
	}

	public void setHello(final String hello) {
		this.hello = hello;
	}

	public List<String> getClasspaths() throws URISyntaxException {
		classContent = new ArrayList<String>();
		URL resource = getClass().getClassLoader().getResource("");
		File[] files = getFilesFromURL(resource);
		extractFiles(files);
		classContent.add("==========");
		resource = Thread.currentThread().getContextClassLoader().getResource("");
		files = getFilesFromURL(resource);
		extractFiles(files);
		classContent.add("==========");
		resource = getClass().getResource("");
		files = getFilesFromURL(resource);
		extractFiles(files);
		return classContent;
	}

	private File[] getFilesFromURL(final URL resource) throws URISyntaxException {
		File f = new File(resource.toURI());
		return f.listFiles();
	}

	private void extractFiles(final File[] files) {
		for (File file : files) {
			classContent.add(file.getAbsolutePath());
			if(file.isDirectory())
				extractFiles(file.listFiles());
		}

	}

}
