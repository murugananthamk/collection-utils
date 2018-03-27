/**
 * 
 */
package com.example.collection.utils;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Collection utils class
 * @author muruga
 *
 */
public class ListUtils {

	/**
	 * Default Constructor
	 */
	public ListUtils() {

	}

	public static <T> List<T> take( List<T> list, int n ) {
		return list == null || list.size() < n ? list : list.subList( 0, n - 1 );
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, List<T>> groupBy( List<T> list, Function<T, R> func ) {
		return ( Map<R, List<T>> ) CollectionUtils.groupBy( list, func, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, Set<T>> groupByDistinct( List<T> list, Function<T, R> func ) {
		return ( Map<R, Set<T>> ) CollectionUtils.groupBy( list, func, true );
	}
	
	public static <T> List<T> filter( List<T> list, Predicate<T> predicate ) {
		return ( List<T> ) CollectionUtils.filter( list, predicate, false );
	}
	
	public static <T> Set<T> filterDistinct( List<T> list, Predicate<T> predicate ) {
		return ( Set<T> ) CollectionUtils.filter( list, predicate, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, List<T>> partition( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, List<T>> ) CollectionUtils.partition( list, predicate, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, Set<T>> partitionDistinct( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, Set<T>> ) CollectionUtils.partition( list, predicate, false );
	}
	
	public static <T> List<T> map( List<T> list, Function<T,T> func ) {
		return ( List<T> ) CollectionUtils.map( list, func, false );
	}
	
	public static <T> Set<T> mapDistinct( List<T> list, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( list, func, true );
	}
	
	public static <T> Set<T> distinctAsSet( List<T> list ) {
		return ( Set<T> ) CollectionUtils.distinct( list, true );
	}
	
	public static <T> List<T> distinctAsList( List<T> list ) {
		return ( List<T> ) CollectionUtils.distinct( list, false ) ;
	}
	
	//public static <T,R> List<R> distinctFieldAsList( List<T> list, Function<T,R> func ) {
		
	//}
}
