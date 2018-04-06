package com.example.collection.utils;

import static java.lang.System.out;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Function;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) {
		List<Item> list = asList( new Item("12", "mobile", 10)
								, new Item("13", "mobile", 20));
		out.println(
		ListUtils.groupByCount(list, Item::getCategroy ));
	}
}
