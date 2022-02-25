package base;

import java.util.Date;

public class Note {
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

	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}
}