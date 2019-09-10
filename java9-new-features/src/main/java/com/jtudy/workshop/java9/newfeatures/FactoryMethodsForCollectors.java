package com.jtudy.workshop.java9.newfeatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jtudy.workshop.java9.newfeatures.samplemodel.Point;

/*
 * JEP 269: Convenience Factory Methods for Collections
 * 
 */
public class FactoryMethodsForCollectors {
	
	
	public void beforeJEP269() {
		List<Point> myList = new ArrayList<>();
		myList.add(new Point(1, 1));
		myList.add(new Point(2, 2));
		myList.add(new Point(3, 3));
		myList = Collections.unmodifiableList(myList);
	}
	
	@Test
	public void withJEP269() {
		
		List<Point> list = List.of(
				new Point(1, 1), new Point(2, 2), new Point(3, 3));
		
		Map<String, Integer> map = Map.of("One", 1, "Two", 2, "Three", 3);
	}
}
