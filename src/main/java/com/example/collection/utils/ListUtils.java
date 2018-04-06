/**
 * 
 */
package com.example.collection.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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

	/**
	 * Returns first n elements from the list;
	 * <pre>
	 * ListUtils.take( null, 2 ) = null
	 * ListUtils.take( [], 2 ) = []
	 * ListUtils.take( [1,2], 3 ) = [1,2]
	 * ListUtils.take( [1,2,3,4,5] , 3 ) = [1,2,3]
	 * </pre>
	 * @param set
	 * @param n
	 * @return
	 */
	public static <T> List<T> take( List<T> list, int n ) {
		return ( List<T> ) CollectionUtils.limit( list, n, false );
	}
	
	/**
	 * Groups the list based on the value return by func and returns as a {@link Map}
	 * <pre>
	 * ListUtils.groupBy(null) = null
	 * ListUtils.groupBy([]) = new HashMap<>();
	 * </pre>
	 * @param list
	 * @param func - mapper function
	 * @return Map<R, List<T>>
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, List<T>> groupBy( List<T> list, Function<T, R> func ) {
		return ( Map<R, List<T>> ) CollectionUtils.groupBy( list, func, false, false );
	}
	
	/**
	 * Groups the list parallely based on the value return by func and returns as a {@link Map}
	 * <pre>
	 * ListUtils.groupBy(null) = null
	 * ListUtils.groupBy([]) = new HashMap<>();
	 * </pre>
	 * @param list
	 * @param func - mapper function
	 * @return Map<R, List<T>>
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, List<T>> groupByParallely( List<T> list, Function<T, R> func ) {
		return ( Map<R, List<T>> ) CollectionUtils.groupBy( list, func, false, true );
	}
	
	/**
	 * Groups the list based on the value return by func
	 * <pre>
	 * ListUtils.groupBy(null) = null
	 * ListUtils.groupBy([]) = new HashMap<>();
	 * </pre>
	 * @param list
	 * @param func - mapper function
	 * @return Map<R, Set<T>>
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, Set<T>> groupByAsSet( List<T> list, Function<T, R> func ) {
		return ( Map<R, Set<T>> ) CollectionUtils.groupBy( list, func, true, false );
	}
	
	/**
	 * Groups the list based on the value return by func
	 * <pre>
	 * ListUtils.groupBy(null) = null
	 * ListUtils.groupBy([]) = new HashMap<>();
	 * </pre>
	 * @param list
	 * @param func - mapper function
	 * @return Map<R, Set<T>>
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, Set<T>> groupByAsSetParallely( List<T> list, Function<T, R> func ) {
		return ( Map<R, Set<T>> ) CollectionUtils.groupBy( list, func, true, true );
	}
	
	/**
	 * 
	 * @param list
	 * @param predicate
	 * @return
	 */
	public static <T,R> Map<R, Integer> groupBySummingInt( List<T> list, Function<T,R> groupByFunc
			, Function<T,Integer> aggFunc ) {
		return CollectionUtils.groupBySum( list, groupByFunc, aggFunc, false, Integer.class );
	}
	
	/**
	 * 
	 * @param list
	 * @param predicate
	 * @return
	 */
	public static <T,R> Map<R, Long> groupByCount( List<T> list, Function<T, R> groupByFunc ) {
		return CollectionUtils.groupByCount( list, groupByFunc, false );
	}
	
	public static <T> List<T> filter( List<T> list, Predicate<T> predicate ) {
		return ( List<T> ) CollectionUtils.filter( list, predicate, false, false );
	}
	
	public static <T> List<T> filterParallely( List<T> list, Predicate<T> predicate ) {
		return ( List<T> ) CollectionUtils.filter( list, predicate, false, true );
	}
	
	public static <T> Set<T> filterAsSet( List<T> list, Predicate<T> predicate ) {
		return ( Set<T> ) CollectionUtils.filter( list, predicate, true, false );
	}
	
	public static <T> Set<T> filterAsSetParallely( List<T> list, Predicate<T> predicate ) {
		return ( Set<T> ) CollectionUtils.filter( list, predicate, true, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, List<T>> partition( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, List<T>> ) CollectionUtils.partition( list, predicate, false, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, List<T>> partitionParallely( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, List<T>> ) CollectionUtils.partition( list, predicate, false, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, Set<T>> partitionAsSet( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, Set<T>> ) CollectionUtils.partition( list, predicate, false, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, Set<T>> partitionAsSetParallely( List<T> list, Predicate<T> predicate ) {
		return ( Map<Boolean, Set<T>> ) CollectionUtils.partition( list, predicate, false, true );
	}
	
	public static <T,R> List<R> map( List<T> list, Function<T,R> func ) {
		return ( List<R> ) CollectionUtils.map( list, func, false, false );
	}
	
	public static <T,R> List<R> mapParallely( List<T> list, Function<T,R> func ) {
		return ( List<R> ) CollectionUtils.map( list, func, false, true );
	}
	
	public static <T> Set<T> mapToSet( List<T> list, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( list, func, true, false );
	}
	
	public static <T> Set<T> mapToSetParallely( List<T> list, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( list, func, true, true );
	}
	
	public static <T> Set<T> distinctAsSet( List<T> list ) {
		return ( Set<T> ) CollectionUtils.distinct( list, true, false );
	}
	
	public static <T> List<T> distinctAsList( List<T> list ) {
		return ( List<T> ) CollectionUtils.distinct( list, false, false ) ;
	}
	
	public static <T,R> List<R> distinctFieldAsList( List<T> list, Function<T,R> func ) {
		return (List<R>) CollectionUtils.distinctField( list, func, false, false );
	}
	
	public static <T,R> Set<R> distinctFieldAsSet( List<T> list, Function<T,R> func ) {
		return (Set<R>) CollectionUtils.distinctField( list, func, true, false );
	}
	
	public static <T> Integer sum( List<T> list, ToIntFunction<T> func ) {
		return CollectionUtils.sum( list, func, false );
	}
	
	public static <T> Long sum( List<T> list, ToLongFunction<T> func ) {
		return CollectionUtils.sum( list, func, false );
	}
	
	
	public static <T> Double sum( List<T> list, ToDoubleFunction<T> func ) {
		return CollectionUtils.sum( list, func, false );
	}
	
	public static <T> T min( List<T> list, ToIntFunction<T> func ) {
		return CollectionUtils.min( list, func, false );
	}
	
	public static <T> T min( List<T> list, ToLongFunction<T> func ) {
		return CollectionUtils.min( list, func, false );
	}
	
	public static <T> T min( List<T> list, ToDoubleFunction<T> func ) {
		return CollectionUtils.min( list, func, false );
	}
	
	public static Long summingLong( List<Long> list ) {
		return CollectionUtils.summingLong( list, false );
	}
	
	public static Integer summingInt( List<Integer> list ) {
		return CollectionUtils.summingInt( list, false );
	}
	
	public static Double summingDouble( List<Double> list ) {
		return CollectionUtils.summingDouble( list, false );
	}
	
	/*@SuppressWarnings("unchecked")
	public static <T,R> R min( List<T> list, Function<T, Long> func ) {
		return null;
	}*/
}
