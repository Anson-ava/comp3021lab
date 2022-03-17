package base;

import java.util.Date;
import java.io.Serializable;

public class Note implements Comparable<Note>, Serializable {
	/*@Override
	public int hashCode() {
		return Objects.hash(title);
	}*/

	@Override
	public boolean equals(Object obj) {
	/*	if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false; */
		Note other = (Note) obj; 
		return other.getTitle().equals(this.title);
	}
	
	@Override
	public int compareTo(Note o) {
		if(this.date.compareTo(o.date) < 0) {
			return 1;
		}
		if(this.date.compareTo(o.date) > 0) {
			return -1; 
		}
		else return 0; 	
	}

	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}