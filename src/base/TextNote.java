package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {
	
	public String content;
	private static final long serialVersionUID = 1L;
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getContent(){
		return this.content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		BufferedReader br = null;
		FileInputStream fis = null;
		InputStreamReader isr =null;
		
		try {
    		br = new BufferedReader(isr);
    		fis = new FileInputStream(absolutePath);
    		isr = new InputStreamReader(fis);
    		
    		while((result = br.readLine()) != null) {
    			System.out.println(result);
    		}
    		br.close();
    		fis.close();
    		isr.close();
    		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		BufferedWriter bw = null;
		FileWriter fw = null;   
		
		try {
    		String title = this.getTitle();
    		title = title.replaceAll(" ", "_");
    		File file = new File( pathFolder + File.separator + title + ".txt");

    		fw = new FileWriter(file.getAbsolutePath());
    		bw = new BufferedWriter(fw);
    		bw.write(content);
    		bw.close();
    		fw.close();
    		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return;
	}
}
