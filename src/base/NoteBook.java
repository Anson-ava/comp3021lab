package base;

import java.util.*;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	public ArrayList<Folder> getFolders() {
	return folders;
	}

	public boolean insertNote(String folderName, Note note) {
	
	Folder f = null;
	for (Folder f1 : folders) {
		if(f1.getName().equals(folderName)) {
			f = f1;
		}
	} if(f == null) {
		f = new Folder(folderName);
		folders.add(f);
	}
	for(Note a: f.getNotes()) {
		if(note.equals(a)) {
			System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
			return false;
		}
	}
		f.addNote(note);
		return true;
	}

	public void sortFolders() {
	//sort the notes for the folders first
	for (Folder f1: folders) {
		f1.sortNotes();
	}
		Collections.sort(folders); 
	}

	public List<Note> searchNotes(String keywords) {
	List<Note> ListNote = new ArrayList<Note>();
	for(Folder f1:folders){
		ListNote.addAll(f1.searchNotes(keywords));
	}
	return ListNote;
	}

	public boolean createTextNote(String folderName, String title, String content) {
	TextNote note = new TextNote(title, content);
	return insertNote(folderName, note);
	}

	public boolean save(String file) {
	FileOutputStream fos = null;
	ObjectOutputStream out = null;
	
	try{
		fos = new FileOutputStream(file);
		out = new ObjectOutputStream(fos);
		out.writeObject(this);
		out.close();
		
	}catch (Exception e){
		e.printStackTrace();
		return false;
		}
	return true;
	}

	public NoteBook(String file) {
	FileInputStream fis = null;
	ObjectInputStream in = null;
	
	try{
		fis = new FileInputStream(file);
		in = new ObjectInputStream (fis);
		NoteBook n = (NoteBook) in.readObject();
		folders = n.folders;
		in.close();
		
	}catch (Exception e){
		e.printStackTrace();
		}
	}
}
