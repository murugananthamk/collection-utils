package com.example.collection.utils;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

	public CollectionUtils() {

	}

	/**
	 * Returns true if list is null or empty. Otherwise false.
	 * <pre>
	 * CollectionUtils.isEmpty(null) = true
	 * CollectionUtils.isEmpty([]) = true
	 * CollectionUtils.isEmpty([1,2]) = false
	 * </pre>
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty( Collection<?> collection ) {
		return collection == null || collection.isEmpty() ? true : false;
	}
	
	/**
	 * Returns false if list is empty or null. Otherwise true.
	 * <pre>
	 * CollectionUtils.isEmpty(null) = true
	 * CollectionUtils.isEmpty([]) = true
	 * CollectionUtils.isEmpty([1,2]) = false
	 * </pre>
	 * @param collection
	 * @return true of false
	 */
	public static boolean isNotEmpty( Collection<?> collection ) {
		return !isEmpty( collection );
	}
	
	/**
	 * Partitions the collection based on the predicate.
	 * <pre>
	 * CollectionUtils.partition(null) = null
	 * CollectionUtils.partition([]) = new HashMap<>()
	 * CollectionUtils.partition( [1,2,3,4], val -> val > 2 ) = Map{ true=[1,2], false=[3,4] }
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @param asSet 
	 * @param parallel - if true partitions parallely, else sequentially
	 * @return Map[Boolean, List] if asSet is false, else Map[Boolean, Set]
	 */
	static <T,R> Map<Boolean, ? extends Collection<T>> partition( Collection<T> collection, Predicate<T> predicate
			, boolean asSet, boolean parallel ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			new HashMap<>();
		Stream<T> stream =  parallel ? collection.parallelStream() : collection.stream();
		return asSet ? stream.collect( partitioningBy( predicate, toSet() ) ) : stream.collect( partitioningBy( predicate ) );
	}
	
	/**
	 * Filters the collection based on the predicate.
	 * <pre>
	 * CollectionUtils.filter(null) = null
	 * CollectionUtils.filter([]) = null
	 * CollectionUtils.filter( [1,2,3,4], val -> val > 2 ) = [3,4]
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @param asSet - return type is Set if true. Else, List
	 * @param parallel - if true partitions parallely, else sequentially
	 * @return Collection
	 */
	static <T> Collection<T> filter( Collection<T> collection, Predicate<T> predicate
			, boolean asSet, boolean parallel ) {
		if( isEmpty( collection ) ) 
			return collection;
		Stream<T> stream =  parallel ? collection.parallelStream() : collection.stream();
		stream = stream.filter( predicate );
		return asSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Map<R, ? extends Collection<T>> groupBy( Collection<T> collection, Function<T, R> func
			, boolean distinct, boolean parallel ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			new HashMap<>();
		
		Stream<T> stream =  parallel ? collection.parallelStream() : collection.stream();
		return distinct ? stream.collect( groupingBy(func, toSet() ) ) : stream.collect( groupingBy( func ) );
	}
	
	/**
	 * 
	 * @param collection
	 * @param func
	 * @param distinct
	 * @return
	 */
	static <T,R> Collection<R> map( Collection<T> collection, Function<T,R> func, boolean distinct, 
			boolean parallel ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return new ArrayList<>();
		
		Stream<R> stream = mapCollectionAsStream( collection, func, parallel );
		return distinct ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Stream<R> mapCollectionAsStream( Collection<T> collection, Function<T,R> func, 
			boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.map( func );
	}

	static <T> Collection<T> distinct( Collection<T> collection, boolean returnAsSet, boolean parallel ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return returnAsSet ? new HashSet<>() : new ArrayList<>();
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		stream = stream.distinct();
		return returnAsSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Collection<R> distinctField( Collection<T> collection, Function<T,R> func
			, boolean returnAsSet, boolean parallel ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return returnAsSet ? new HashSet<>() : new ArrayList<>();
	
		Stream<R> stream = mapCollectionAsStream( collection, func, parallel ).distinct();
		return returnAsSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T> Integer sum( Collection<T> collection, ToIntFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.collect( Collectors.summingInt( func ) );
	}
	
	static <T> Long sum( Collection<T> collection, ToLongFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.collect( Collectors.summingLong( func ) );
	}
	
	static <T> Double sum( Collection<T> collection, ToDoubleFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.collect( Collectors.summingDouble( func ) );
	}
	
	static Integer summingInt( Collection<Integer> collection, boolean parallel ) {
		Stream<Integer> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.reduce( 0, Integer::sum ) ;
	}
	
	static Long summingLong( Collection<Long> collection, boolean parallel ) {
		Stream<Long> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.reduce( 0l, Long::sum ) ;
	}
	
	static Double summingDouble( Collection<Double> collection, boolean parallel ) {
		Stream<Double> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.reduce( 0d, Double::sum ) ;
	}
	
	
	/*static <T> Float sum( Collection<T> collection, ToFloatFunction<T> func ) {
		return mapCollectionAsStream( collection, func )
				  	.reduce( 0f, Float::sum );
	}*/

	static <T,U> T min( Collection<T> collection, ToIntFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.min( comparingInt( func ) )
					 .get();
	}
	
	static <T,U> T min( Collection<T> collection, ToLongFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.min( comparingLong( func ) )
					 .get();
	}
	
	static <T,U> T min( Collection<T> collection, ToDoubleFunction<T> func, boolean parallel ) {
		Stream<T> stream = parallel ? collection.parallelStream() : collection.stream();
		return stream.min( comparingDouble( func ) )
					 .get();
	}
	
	static <T,R> R apply( Collection<T> collection, Function<T,R> func ) {
		T t = collection.iterator().next();
		R r = func.apply(t);
		return r;
	}
	
	/**
	 * Returns first n elements from the collection;
	 * <pre>
	 * CollectionUtils.take( null, 2 ) = null
	 * CollectionUtils.take( [], 2 ) = []
	 * CollectionUtils.take( [1,2], 3 ) = [1,2]
	 * CollectionUtils.take( [1,2,3,4,5] , 3 ) = [1,2,3]
	 * </pre>
	 * @param set
	 * @param n
	 * @return
	 */
	static <T> Collection<T> limit( Collection<T> collection, int n, boolean asSet ) {
		if( collection == null || collection.size() < n ) {
			return collection;
		}
		Stream<T> stream = collection.stream()
				  					.limit( n );
		return asSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}

	@SuppressWarnings({ "rawtypes" })
	static <T,R,K extends Number> Map<R, K> groupBySum( Collection<T> collection, Function<T,R> groupByFunc
			, Function<T,K> aggFunc, boolean parallel, Class<K> clazz ) {
		
		Collector collector = null;
		if( clazz.equals( Integer.class ) )
			collector = Collectors.<Integer>summingInt( val -> val );
		else if( clazz.equals( Long.class ))
			collector = Collectors.<Long>summingLong( val -> val );
		else
			collector = Collectors.<Double>summingDouble( val -> val );
		
		Stream<T> stream = getStream( collection, parallel );
		return groupBy( stream, groupByFunc, aggFunc, collector );
	}
	
	@SuppressWarnings({ "unchecked"})
	static <T,R> Map<R, Long> groupByCount( Collection<T> collection, Function<T,R> groupByFunc
			, boolean parallel ) {
		Stream<T> stream = getStream( collection, parallel );
		return ( Map<R, Long> ) groupBy( stream, groupByFunc, Function.identity(), Collectors.counting() );
	}
	
	private static <T> Stream<T> getStream( Collection<T> collection, boolean parallel ) {
		return parallel ? collection.parallelStream() : collection.stream();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T,R,V> Map<R,V> groupBy( Stream<T> stream, Function<T, R> groupByFunc, 
			Function<T, V> aggFunc, Collector collector ) {
		return stream.collect( groupingBy( groupByFunc
  							, mapping( aggFunc
  									, collector ) ) );
	}
}
