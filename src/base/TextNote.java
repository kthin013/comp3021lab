package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.sun.java_cup.internal.runtime.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {
	protected String content;

	public TextNote(String title) {
		super(title);
		content = "";
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	private String getTextFromFile(String absolutePath) {
		String result = "";
		File file = new File(absolutePath);
		FileInputStream fis = null;
		BufferedReader reader = null;
	    try {
	            fis = new FileInputStream(file);
	            reader = new BufferedReader(new InputStreamReader(fis));
	            while ((result = reader.readLine()) != null) {
					System.out.println(result);
				}
				fis.close();
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		return result;
	}

	public void exportTextToFile(String pathFolder) {
		if (pathFolder.isEmpty())
			pathFolder = ".";
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
		FileWriter writer = null;
		BufferedWriter out = null;
		try {
			writer = new FileWriter(file.getAbsolutePath());
			out = new BufferedWriter(writer);
			out.write(this.content);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
