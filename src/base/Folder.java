package base;

import java.util.ArrayList;
import java.util.*;

public class Folder implements Comparable<Folder> {
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
	
	@Override
	public int compareTo(Folder o){
		if(this.name.compareTo(o.name) > 0) {
			return 1;
		}
		if(this.name.compareTo(o.name) < 0) {
			return -1;
		}
		else return 0;
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
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
/*	public boolean checkKeywords(String keywords, String content) {
        String[] keywordsList = keywords.toLowerCase().split(" ");
        String lowerCaseContent = content.toLowerCase();
        boolean temp = true;
        for (int a = 0; a < keywordsList.length - 2; a++) {
            if (keywordsList[a + 1].equals("or")) {
                temp = temp && (lowerCaseContent.contains(keywordsList[a]) || lowerCaseContent.contains(keywordsList[a + 2]));
                a = a + 2;
            } else {
                temp = temp && lowerCaseContent.contains(keywordsList[a]);
            }
        }
        return temp;
    }
	
	public List<Note> searchNotes(String keywords) {
		List<Note> ListNote = new ArrayList<Note>();
		
		for(Note note: this.notes) {
			if (note instanceof TextNote) {
				if (this.checkKeywords(lowerCaseKeywords, content) || this.checkKeywords(lowerCaseKeywords, title)) {
					
				}
			}
		}
	}*/
	
	public List<Note> searchNotes(String keywords) {
		keywords = keywords.toLowerCase();
		String[] StrArr = keywords.split(" ");
		List<Note> ListNote = new ArrayList<Note>();
		
		for(Note note: this.notes) {
			boolean search = true;
			int y = 0;
			if((y < StrArr.length) && (search == true)) {
				search = false;
				if (note instanceof TextNote) {
					
					do{
						if(StrArr[y].equals("or"))
						y++;
							//considering the title and content
							if ((note.getTitle().indexOf(StrArr[y])>=0)||(((TextNote) note).content.indexOf(StrArr[y])>=0))
								search = true;
								y++;
			} while ((y < StrArr.length) && (StrArr[y].equals("or")));
				}
					else {
						do {
							if(StrArr[y].equals("or")) {
								y++;
							}
							//Only considering the title
								if((note.getTitle().indexOf(StrArr[y])>=0))
									search = true;
									y++;
						}  while ((y < StrArr.length) && (StrArr[y].equals("or")));
				}
			}
			if (search)
				ListNote.add(note);
		}
		return ListNote;
	}

}
