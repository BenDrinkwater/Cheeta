package util;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

public class NoNullLikedListTest {

	@Test
	public void AddTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.add("Lets");
		list.add("add");
		list.add("some");
		list.add("words");
		list.add("to");
		list.add("this");
		list.add("list");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());
	}
	
	@Test
	public void AddWithNullTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.add("Lets");
		list.add("add");
		list.add(null);
		list.add("some");
		list.add("words");
		list.add(null);
		list.add("to");
		list.add("this");
		list.add("list");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());
	}
	
	@Test
	public void AddFirstTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.addFirst("list");
		list.addFirst("this");
		list.addFirst("to");
		list.addFirst("words");
		list.addFirst("some");
		list.addFirst("add");
		list.addFirst("Lets");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());		
	}
	
	@Test
	public void AddFirstWithNullTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.addFirst("list");
		list.addFirst("this");
		list.addFirst("to");
		list.addFirst("words");
		list.addFirst("some");
		list.addFirst("add");
		list.addFirst(null);
		list.addFirst("Lets");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());		
	}
	
	@Test
	public void AddLastTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.addLast("Lets");
		list.addLast("add");
		list.addLast("some");
		list.addLast("words");
		list.addLast("to");
		list.addLast("this");
		list.addLast("list");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());				
	}
	
	@Test
	public void AddLastWithNullTest() {
		LinkedList<String> list = new NoNullLinkedList<String>();
		list.addLast(null);
		list.addLast(null);
		list.addLast(null);
		list.addLast("Lets");
		list.addLast("add");
		list.addLast("some");
		list.addLast("words");
		list.addLast("to");
		list.addLast("this");
		list.addLast("list");
		assertEquals(7, list.size());
		StringBuilder builder = new StringBuilder();
		for (String str : list) {
			builder.append(str);
			builder.append(" ");
		}
		assertEquals("Lets add some words to this list ", builder.toString());				
	}
	
}
