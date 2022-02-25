package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note add) {
		notes.add(add);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/*@Override
	public int hashCode() {
		return Objects.hash(name);
	}*/

	@Override
	public boolean equals(Object obj) {
		/*if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;*/
		Folder other = (Folder) obj;
		return other.getName().equals(this.name);
	}

	public String toString() {
		int nText = 0;
		int nImage = 0;
		for(Note note : this.notes){
			if(note instanceof TextNote){
				nText ++;
			}
			else {
				nImage ++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
}
