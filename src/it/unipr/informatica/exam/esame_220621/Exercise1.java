package it.unipr.informatica.exam.esame_220621;

import java.util.List;

public class Exercise1 {
	private String s;

	public Exercise1(String s) {
		if (s == null || s.length() == 0) throw new IllegalArgumentException("s == null");
		this.s = s;
	}

	public Boolean search(List<String> l) {
		if (l == null) throw new IllegalArgumentException("l == null");
		for(String e : l) 
			if (s.equals(e)) 
				return true;

		return false;		
	}
} 

