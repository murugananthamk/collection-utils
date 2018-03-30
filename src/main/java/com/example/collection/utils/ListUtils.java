/**
 * 
 */
package com.example.collection.utils;

import java.lang.reflect.ParameterizedType;
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
	
	public static <T,R> List<R> map( List<T> list, Function<T,R> func ) {
		return ( List<R> ) CollectionUtils.map( list, func, false );
	}
	
	public static <T> Set<T> mapToSet( List<T> list, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( list, func, true );
	}
	
	public static <T> Set<T> distinctAsSet( List<T> list ) {
		return ( Set<T> ) CollectionUtils.distinct( list, true );
	}
	
	public static <T> List<T> distinctAsList( List<T> list ) {
		return ( List<T> ) CollectionUtils.distinct( list, false ) ;
	}
	
	public static <T,R> List<R> distinctFieldAsList( List<T> list, Function<T,R> func ) {
		return (List<R>) CollectionUtils.distinctField( list, func, false );
	}
	
	public static <T,R> Set<R> distinctFieldAsSet( List<T> list, Function<T,R> func ) {
		return (Set<R>) CollectionUtils.distinctField( list, func, true );
	}
	
	public static <T> Integer summingInt( List<T> list, Function<T, Integer> func ) {
		return CollectionUtils.summingInt( list, func );
	}
	
	public static <T> Long summingLong( List<T> list, Function<T, Long> func ) {
		return CollectionUtils.summingLong( list, func );
	}
	
	public static <T> Float summingFloat( List<T> list, Function<T, Float> func ) {
		return CollectionUtils.summingFloat( list, func );
	}
	
	public static <T> Double summingDouble( List<T> list, Function<T, Double> func ) {
		return CollectionUtils.summingDouble( list, func );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R > R min( List<T> list, Function<T,R> func ) {
		T t = list.iterator().next();
		R r = func.apply(t);
		System.out.println(r.getClass());
		return null;
	}
	
	/*@SuppressWarnings("unchecked")
	public static <T,R> R min( List<T> list, Function<T, Long> func ) {
		return null;
	}*/
}
