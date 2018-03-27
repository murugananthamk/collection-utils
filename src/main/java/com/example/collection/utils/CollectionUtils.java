package com.example.collection.utils;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
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
	
}
