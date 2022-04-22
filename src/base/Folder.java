package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class Folder implements Comparable<Folder>,Serializable {
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note add) {
		notes.add(add);
	}
	
	public boolean removeNotes(String title){
 		
 		for(Note n:this.notes){
 			if(n.getTitle().equals(title)){
 				
 				this.notes.remove(this.notes.indexOf(n));
 				return true;
 			}     
 		}
 		
 		return false;
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
	
//public boolean checkKeywords(String keywords, String content) {
//        String[] keywordsList = keywords.toLowerCase().split(" ");
//        String lowerCaseContent = content.toLowerCase();
//        boolean temp = true;
//        for (int a = 0; a < keywordsList.length - 2; a++) {
//            if (keywordsList[a + 1].equals("or")) {
//                temp = temp && (lowerCaseContent.contains(keywordsList[a]) || lowerCaseContent.contains(keywordsList[a + 2]));
//                a = a + 2;
//            } else {
//                temp = temp && lowerCaseContent.contains(keywordsList[a]);
//            }
//        }
//        return temp;
//    }
//	
//	public List<Note> searchNotes(String keywords) {
//		List<Note> ListNote = new ArrayList<Note>();
//		
//		for(Note note: this.notes) {
//			if (note instanceof TextNote) {
//				//if (this.checkKeywords(lowerCaseKeywords, content) || this.checkKeywords(lowerCaseKeywords, title)) {
//					
//				}
//			}
//		}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> res = new ArrayList<Note>();

		ArrayList<ArrayList<String>> patterns = new ArrayList<ArrayList<String>>();

		//process keywords
		String[] keyList = keywords.split(" ");
		int i=0;
		while(i<keyList.length) {
			if(keyList[i].toLowerCase().equals("or")) {
				i++;
				patterns.get(patterns.size() - 1).add(keyList[i].toLowerCase());
			} else {
				ArrayList<String> newArr = new ArrayList<String>();
				newArr.add(keyList[i].toLowerCase());
				patterns.add(newArr);
			}
			i++;
		}

		for(Note n : notes) {
			String toBeSearched = n.getTitle();
			if(n instanceof TextNote) {
				toBeSearched +=  ((TextNote)n).getContent();
			}
			toBeSearched = toBeSearched.toLowerCase();

			boolean flag = true;
			for(ArrayList<String> pattern : patterns) {
				boolean flag2 = false;
				for(String oneKey : pattern) {
					if(toBeSearched.contains(oneKey)) {
						flag2 = true;
						break;
					}
				}
				if(!flag2) {
					flag = false;
					break;
				}
			}
			if(flag) res.add(n);
		}

		return res;
	}
	
}