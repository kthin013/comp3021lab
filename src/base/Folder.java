package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note newnotes) {
		notes.add(newnotes);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void sortNotes() {
		Collections.sort(this.notes);
	}

	/*public List<Note> searchNotes(String keywords) {
		List<Note> resultNote = new ArrayList<Note>();
		List<String> stringList = new ArrayList<String>();
		List<List<String>> orList = new ArrayList<List<String>>();
		List<Boolean> boolList = new ArrayList<Boolean>();
		String splitkeywords[] = keywords.toLowerCase().trim().split("or");

		for(int i = 0; i < splitkeywords.length; i++) {
			splitkeywords[i] = splitkeywords[i].trim();
			if(splitkeywords[i].contains(" ")) {
				String tempString[] = splitkeywords[i].split(" ");
				List<String> tempList = new ArrayList<String>();
				for(int j = 0; j < tempString.length; j++)
					tempList.add(tempString[j]);
				orList.add(tempList);
			} else {
				List tempList = new ArrayList<String>();
				tempList.add(splitkeywords[i]);
				orList.add(tempList);
			}
		}

		for(Note n: notes) {
			for(int i = 0; i < orList.size(); i++) {
				if(orList.get(i).size() == 1) { //or
					if(n instanceof TextNote) {
						if(n.getTitle().toLowerCase().contains(orList.get(i).get(0)) || ((TextNote)n).content.toLowerCase().contains(orList.get(i).get(0)))
							boolList.add(true);

					} else if(n instanceof ImageNote){
						if(n.getTitle().toLowerCase().contains(orList.get(i).get(0)))
							boolList.add(true);
					}
				} else if(orList.get(i).size() > 1) { //and
					boolean flag = true;
					for(int j = 0; j < orList.get(i).size(); j++) {
						if(n instanceof TextNote) {
							if(!n.getTitle().toLowerCase().contains(orList.get(i).get(j)) && !((TextNote)n).content.toLowerCase().contains(orList.get(i).get(j)))
								flag = false;
						} else if(n instanceof ImageNote){
							if(!n.getTitle().toLowerCase().contains(orList.get(i).get(j)))
								flag = false;
						}
					}
					boolList.add(flag);
				}
			}
			if(boolList.contains(true))
				resultNote.add(n);
		}
		return resultNote;
	}*/

	public List<Note> searchNotes(String keywords) {
		List<Note> resultNote = new ArrayList<Note>();
		ArrayList<ArrayList<String>> andList = new ArrayList<ArrayList<String>>();

		String[] splitKeywords = keywords.toLowerCase().split(" ");
		int  i = 0;

		while(i<splitKeywords.length) {
			if(splitKeywords[i].equals("or")) {
				i++;
				andList.get(andList.size() - 1).add(splitKeywords[i]);
			} else {
				ArrayList<String> orList = new ArrayList<String>();
				orList.add(splitKeywords[i].toLowerCase());
				andList.add(orList);
			}
			i++;
		}

		for(Note n:notes) {
			String noteTitle = n.getTitle().toLowerCase();
			if(n instanceof TextNote)
				noteTitle +=  ((TextNote)n).content.toLowerCase();

			boolean andFlag = true;
			for(ArrayList<String> pattern : andList) {
				boolean orFlag = false;
				for(String oneKey : pattern) {
					if(noteTitle.contains(oneKey)) {
						orFlag  = true;
						break;
					}
				}
				if(!orFlag) {
					andFlag = false;
					break;
				}
			}
			if(andFlag)
				resultNote.add(n);
		}

		return resultNote;
	}

	/*public boolean equals(Folder f) {
		if(name.equals(f.name))
			return true;
		return false;
	}*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;

		for(Note n:notes) {
			if(n instanceof TextNote)
				nText++;
			else
				nImage++;
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.getName());
	}
}

