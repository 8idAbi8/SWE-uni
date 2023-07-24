package it.unipr.informatica.exam.esame_220621;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Exercise1Tests {

@Test
public void testConstructor() {
	try {
		new Exercise1(null);
		fail();
	} catch (IllegalArgumentException e) {
		assertEquals("s == null", e.getMessage());
	}
	try {
		new Exercise1("");
		fail();
	} catch (IllegalArgumentException e) {
		assertEquals("s == null", e.getMessage());
	}
	new Exercise1("valid string");
}

@Test
public void testSearch() {
	Exercise1 e1 = new Exercise1("abc");
	List<String> list1 = Arrays.asList("def", "ghi");
	assertFalse(e1.search(list1));
	List<String> list2 = Arrays.asList("abc", "ghi");
	assertTrue(e1.search(list2));
	
	try {
		e1.search(null);
		fail();
	} catch (IllegalArgumentException e) {
		assertEquals("l == null", e.getMessage());
	}
}

}