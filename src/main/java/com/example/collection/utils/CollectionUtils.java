package com.example.collection.utils;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

public class CollectionUtils {

	public CollectionUtils() {

	}

	public static boolean isEmpty( Collection<?> collection ) {
		return collection == null || collection.isEmpty() ? true : false;
	}
	
	public static boolean isNotEmpty( Collection<?> collection ) {
		return !isEmpty( collection );
	}
	
	static <T,R> Map<Boolean, ? extends Collection<T>> partition( Collection<T> collection, Predicate<T> predicate, boolean distinct ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			new HashMap<>();
		Stream<T> stream =  collection.stream();
		stream.collect( partitioningBy( predicate ) );
		return null;
	}
	
	static <T> Collection<T> filter( Collection<T> collection, Predicate<T> predicate, boolean distinct ) {
		if( isEmpty( collection ) ) 
			return collection;
		Stream<T> stream =  collection.stream()
							    		 .filter( predicate );
		return distinct ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Map<R, ? extends Collection<T>> groupBy( Collection<T> collection, Function<T, R> func, boolean distinct ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			new HashMap<>();
		
		Stream<T> stream =  collection.stream();
		return distinct ? stream.collect( groupingBy(func, toSet() ) ) : stream.collect( groupingBy( func ) );
	}
	static <T,R> Collection<R> map( Collection<T> collection, Function<T,R> func, boolean distinct ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return new ArrayList<>();
		
		Stream<R> stream = mapCollectionAsStream( collection, func );
		return distinct ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Stream<R> mapCollectionAsStream( Collection<T> collection, Function<T,R> func) {
		return collection.stream()
						 .map( func );
	}

	static <T> Collection<T> distinct( Collection<T> collection, boolean returnAsSet ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return returnAsSet ? new HashSet<>() : new ArrayList<>();
		Stream<T> stream = collection.stream()
				   			   		.distinct();
		return returnAsSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T,R> Collection<R> distinctField( Collection<T> collection, Function<T,R> func, boolean returnAsSet ) {
		if( collection == null ) 
			return null;
		if( collection.isEmpty() )
			return returnAsSet ? new HashSet<>() : new ArrayList<>();
	
		Stream<R> stream = mapCollectionAsStream( collection, func ).distinct();
		return returnAsSet ? stream.collect( toSet() ) : stream.collect( toList() );
	}
	
	static <T> Integer summingInt( Collection<T> collection, Function<T,Integer> func ) {
		return mapCollectionAsStream( collection, func )
				  	.reduce( 0, Integer::sum );
	}
	
	static <T> Long summingLong( Collection<T> collection, Function<T,Long> func ) {
		return mapCollectionAsStream( collection, func )
				  	.reduce( 0l, Long::sum );
	}
	
	static <T> Double summingDouble( Collection<T> collection, Function<T,Double> func ) {
		return mapCollectionAsStream( collection, func )
				  	.reduce( 0d, Double::sum );
	}
	
	static <T> Float summingFloat( Collection<T> collection, Function<T,Float> func ) {
		return mapCollectionAsStream( collection, func )
				  	.reduce( 0f, Float::sum );
	}

	@SuppressWarnings("unchecked")
	static <T,R> T min( List<T> list, Function<T, R> func, Class<? extends Object> class1 ) {
		Stream<T> stream =  list.stream();
		if( class1.equals(Integer.class) )
			return stream.min( comparingInt( ( ToIntFunction<? super T> )  func ) )
						 .get();
		if ( class1.equals(Long.class) )
			return stream.min( comparingLong( ( ToLongFunction<? super T> )  func ) )
					 .get();
		if( class1.equals(Integer.class) )
			return stream.min( comparingDouble( ( ToDoubleFunction<? super T> )  func ) )
						 .get();
		return null;
	}
	
}
