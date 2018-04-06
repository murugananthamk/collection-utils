package com.example.collection.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SetUtils {

	/**
	 * Default Constructor
	 */
	public SetUtils() {

	}

	/**
	 * Returns first n elements from the set;
	 * <pre>
	 * SetUtils.take( null, 2 ) = null
	 * SetUtils.take( [], 2 ) = []
	 * SetUtils.take( [1,2], 3 ) = [1,2]
	 * SetUtils.take( [1,2,3,4,5] , 3 ) = [1,2,3]
	 * </pre>
	 * @param set
	 * @param n
	 * @return
	 */
	public static <T> Set<T> take( Set<T> set, int n ) {
		return ( Set<T> ) CollectionUtils.limit( set, n, true );
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, List<T>> groupBy( Set<T> set, Function<T, R> func ) {
		return ( Map<R, List<T>> ) CollectionUtils.groupBy( set, func, false, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, List<T>> groupByParallely( Set<T> set, Function<T, R> func ) {
		return ( Map<R, List<T>> ) CollectionUtils.groupBy( set, func, false, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, Set<T>> groupByDistinct( Set<T> set, Function<T, R> func ) {
		return ( Map<R, Set<T>> ) CollectionUtils.groupBy( set, func, true, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<R, Set<T>> groupByDistinctParallely( Set<T> set, Function<T, R> func ) {
		return ( Map<R, Set<T>> ) CollectionUtils.groupBy( set, func, true, true );
	}
	
	public static <T> List<T> filter( Set<T> set, Predicate<T> predicate ) {
		return ( List<T> ) CollectionUtils.filter( set, predicate, false, false );
	}
	
	public static <T> List<T> filterParallely( Set<T> set, Predicate<T> predicate ) {
		return ( List<T> ) CollectionUtils.filter( set, predicate, false, true );
	}
	
	public static <T> Set<T> filterDistinct( Set<T> set, Predicate<T> predicate ) {
		return ( Set<T> ) CollectionUtils.filter( set, predicate, true, false );
	}
	
	public static <T> Set<T> filterDistinctParallely( Set<T> set, Predicate<T> predicate ) {
		return ( Set<T> ) CollectionUtils.filter( set, predicate, true, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, List<T>> partition( Set<T> set, Predicate<T> predicate ) {
		return ( Map<Boolean, List<T>> ) CollectionUtils.partition( set, predicate, false, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, List<T>> partitionParallely( Set<T> set, Predicate<T> predicate ) {
		return ( Map<Boolean, List<T>> ) CollectionUtils.partition( set, predicate, false, true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, Set<T>> partitionDistinct( Set<T> set, Predicate<T> predicate ) {
		return ( Map<Boolean, Set<T>> ) CollectionUtils.partition( set, predicate, false, false );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R> Map<Boolean, Set<T>> partitionDistinctParallely( Set<T> set, Predicate<T> predicate ) {
		return ( Map<Boolean, Set<T>> ) CollectionUtils.partition( set, predicate, false, true );
	}
	
	public static <T,R> List<R> map( Set<T> set, Function<T,R> func ) {
		return ( List<R> ) CollectionUtils.map( set, func, false, false );
	}
	
	public static <T,R> List<R> mapParallely( Set<T> set, Function<T,R> func ) {
		return ( List<R> ) CollectionUtils.map( set, func, false, true );
	}
	
	public static <T> Set<T> mapToSet( Set<T> set, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( set, func, true, false );
	}
	
	public static <T> Set<T> mapToSetParallely( Set<T> set, Function<T,T> func ) {
		return ( Set<T> ) CollectionUtils.map( set, func, true, true );
	}
	
	public static <T> Set<T> distinctAsSet( Set<T> set ) {
		return ( Set<T> ) CollectionUtils.distinct( set, true, false );
	}
	
	public static <T> List<T> distinctAsList( Set<T> set ) {
		return ( List<T> ) CollectionUtils.distinct( set, false, false ) ;
	}
	
	public static <T,R> List<R> distinctFieldAsList( Set<T> set, Function<T,R> func ) {
		return (List<R>) CollectionUtils.distinctField( set, func, false, false );
	}
	
	public static <T,R> Set<R> distinctFieldAsSet( Set<T> set, Function<T,R> func ) {
		return (Set<R>) CollectionUtils.distinctField( set, func, true, false );
	}
	
	public static <T> Integer sum( Set<T> set, ToIntFunction<T> func ) {
		return CollectionUtils.sum( set, func, false );
	}
	
	public static <T> Long sum( Set<T> set, ToLongFunction<T> func ) {
		return CollectionUtils.sum( set, func, false );
	}
	
	
	public static <T> Double sum( Set<T> set, ToDoubleFunction<T> func ) {
		return CollectionUtils.sum( set, func, false );
	}
	
	public static <T> T min( Set<T> set, ToIntFunction<T> func ) {
		return CollectionUtils.min( set, func, false );
	}
	
	public static <T> T min( Set<T> set, ToLongFunction<T> func ) {
		return CollectionUtils.min( set, func, false );
	}
	
	public static <T> T min( Set<T> set, ToDoubleFunction<T> func ) {
		return CollectionUtils.min( set, func, false );
	}
	
	public static Long summingLong( Set<Long> set ) {
		return CollectionUtils.summingLong( set, false );
	}
	
	public static Integer summingInt( Set<Integer> set ) {
		return CollectionUtils.summingInt( set, false );
	}
	
	public static Double summingDouble( Set<Double> set ) {
		return CollectionUtils.summingDouble( set, false );
	}
	
}
