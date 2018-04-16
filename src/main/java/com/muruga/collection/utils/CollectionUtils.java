/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.muruga.collection.utils;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for {@link List}, {@link Set}.
 * @author muruga
 *
 */
public class CollectionUtils 
{
	
	/**
	 * Returns list after applying the mapper function sequentially.
	 * <pre>
	 * CollectionUtils.mappingList( null, Employee::getFname ) = null
	 * CollectionUtils.mappingList( [], Employee::getFname ) = []
	 * CollectionUtils.mappingList( [{fname="m", lname="k", dept="IT", salary=100.0}], Employee::getFname ) = List["m"]
	 * </pre>
	 * @param collection
	 * @param mapper
	 * @return {@link List}
	 */
	public static <T,R> List<R> mappingList( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( mapper, toList() ), new ArrayList<>(), false );
	}

	/**
	 * Returns list after applying the mapper function parallely.
	 * <pre>
	 * CollectionUtils.mappingList( null, Employee::getFname ) = null
	 * CollectionUtils.mappingList( [], Employee::getFname ) = []
	 * CollectionUtils.mappingList( [{fname="m", lname="k", dept="IT", salary=100.0}], Employee::getFname ) = List["m"]
	 * </pre>
	 * @param collection
	 * @param mapper
	 * @return {@link List}
	 */
	public static <T,R> List<R> mappingListParallely( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( mapper, toList() ), new ArrayList<>(), true );
	}
	
	/**
	 * Returns set after applying the mapper function sequentially.
	 * <pre>
	 * CollectionUtils.mappingList( null, Employee::getFname ) = null
	 * CollectionUtils.mappingList( [], Employee::getFname ) = []
	 * CollectionUtils.mappingList( [{fname="m", lname="k", dept="IT", salary=100.0}], Employee::getFname ) = Set["m"]
	 * </pre>
	 * @param collection
	 * @param mapper
	 * @return {@link Set}
	 */
	public static <T,R> Set<R> mappingSet( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( mapper, toSet() ), new HashSet<>(), false );
	}

	/**
	 * Returns set after applying the mapper function parallely.
	 * <pre>
	 * CollectionUtils.mappingList( null, Employee::getFname ) = null
	 * CollectionUtils.mappingList( [], Employee::getFname ) = []
	 * CollectionUtils.mappingList( [{fname="m", lname="k", dept="IT", salary=100.0}], Employee::getFname ) = Set["m"]
	 * </pre>
	 * @param collection
	 * @param mapper
	 * @return {@link Set}
	 */
	public static <T,R> Set<R> mappingSetParallely( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( mapper, toSet() ), new HashSet<>(), true );
	}

	/**
	 * Returns filter {@link List} after applying predicate sequentially.
	 * <pre>
	 * {@code Predicate<Employee> predicate = employee.getFname().equals("m") }
	 * CollectionUtils.filteringList( null, predicate ) = null
	 * CollectionUtils.filteringList( [], predicate ) = List[]
	 * {@code List<Employee> employees = List[{fname="m", lname="k", dept="IT", salary=100.0}, {fname="a", lname="k", dept="IT", salary=100.0}]
	 * CollectionUtils.filteringList( null, predicate ) = List[{fname="m", lname="k", dept="IT", salary=100.0}]
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @return {@link List}
	 */
	public static <T,R> List<T> filteringList(  Collection<T> collection, Predicate<T> predicate ) {
		return collect( collection, filtering( predicate, toList() ), new ArrayList<>(), false );
	}
	
	/**
	 * Returns filter {@link List} after applying predicate parallely.
	 * See examples: {@link #filteringList(Collection, Predicate)}
	 * @param collection
	 * @param predicate
	 * @return {@link List}
	 */
	public static <T,R> List<T> filteringListParallely(  Collection<T> collection, Predicate<T> predicate ) {
		return collect( collection, filtering( predicate, toList() ), new ArrayList<>(), true );
	}
	
	/**
	 * Returns filter {@link Set} after applying predicate sequentially.
	 * <pre>
	 * {@code Predicate<Employee> predicate = employee.getFname().equals("m") }
	 * CollectionUtils.filteringSet( null, predicate ) = null
	 * CollectionUtils.filteringSet( [], predicate ) = List[]
	 * {@code List<Employee> employees = List[{fname="m", lname="k", dept="IT", salary=100.0}, {fname="a", lname="k", dept="IT", salary=100.0}]
	 * CollectionUtils.filteringSet( null, predicate ) = Set[{fname="m", lname="k", dept="IT", salary=100.0}]
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @return {@link Set}
	 */
	public static <T,R> Set<T> filteringSet(  Collection<T> collection, Predicate<T> predicate ) {
		return collect( collection, filtering( predicate, toSet() ), new HashSet<>(), false );
	}
	
	/**
	 * Returns filter {@link Set} after applying predicate parallely.
	 * See examples: {@link #filteringSet(Collection, Predicate)}
	 * @param collection
	 * @param predicate
	 * @return {@link Set}
	 */
	public static <T,R> Set<T> filteringSetParallely(  Collection<T> collection, Predicate<T> predicate ) {
		return collect( collection, filtering( predicate, toSet() ), new HashSet<>(), true );
	}
	
	/**
	 * Checks whether the value exists or not in the collection sequentially. Returns true if value exists. Otherwise, false.
	 * <pre>
	 * {@code Predicate<Employee> predicate = employee.getFname().equals("m") }
	 * CollectionUtils.exists( null, predicate ) = false
	 * CollectionUtils.exists( [], predicate ) = false
	 * CollectionUtils.exists( [{fname="a", lname="k", dept="IT", salary=100.0}], predicate ) = false
	 * CollectionUtils.exists( [{fname="m", lname="k", dept="IT", salary=100.0}], predicate ) = true
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @return boolean
	 */
	public static <T,R> boolean exists(  Collection<T> collection, Predicate<T> predicate ) {
		Optional<T> optional = findFirst( collection, predicate, false );
		return optional.isPresent();
	}
	
	/**
	 * Checks whether the value exists or not in the collection parallely. Returns true if value exists. Otherwise, false.
	 * See examples : {@link #exists(Collection, Predicate)}
	 * @param collection
	 * @param predicate
	 * @return
	 */
	public static <T,R> boolean existsParallely(  Collection<T> collection, Predicate<T> predicate ) {
		Optional<T> optional = findFirst( collection, predicate, true );
		return optional.isPresent();
	}
	
	/**
	 * Returns first match of the collection.
	 * <pre>
	 * {@code Predicate<Employee> predicate = employee.getFname().equals("m") }
	 * CollectionUtils.findFirst( null, predicate, false ) = Optional[null]
	 * CollectionUtils.findFirst( [], predicate, false ) = Optional[null]
	 * CollectionUtils.findFirst( [{fname="m", lname="k", dept="IT", salary=100.0}], predicate, false ) = Optional[{fname="m", lname="k", dept="IT", salary=100.0}]
	 * CollectionUtils.findFirst( [{fname="a", lname="k", dept="IT", salary=100.0}], predicate, false ) = Optional[empty]
	 * </pre>
	 * @param collection
	 * @param predicate
	 * @param parallel - if true finds first parallely. else, sequentially.
	 * @return
	 */
	private static <T> Optional<T> findFirst( Collection<T> collection, Predicate<T> predicate
			, boolean parallel ) {
		if( isEmpty( collection ) )
			return Optional.ofNullable( null );
		return getStream( collection, parallel )
 						.filter( predicate )
 						.findFirst();
	}
	
	/**
	 * Returns first match of the collection sequentially.
	 * See examples : {@link #findFirst(Collection, Predicate, boolean)}
	 * @param collection
	 * @param predicate
	 * @return
	 */
	public static <T> Optional<T> findFirst( Collection<T> collection, Predicate<T> predicate ) {
		return findFirst( collection, predicate, false );
	}
	
	/**
	 * Returns first match of the collection parallely.
	 * See examples : {@link #findFirst(Collection, Predicate, boolean)}
	 * @param collection
	 * @param predicate
	 * @return
	 */
	public static <T> Optional<T> findFirstParallely( Collection<T> collection, Predicate<T> predicate ) {
		return findFirst( collection, predicate, true );
	}
	
	static <T,I> I collect( Collection<T> collection, Collector<T,?,I> collector, I defaultVal, boolean parallel ) {
		if( collection == null )
			return null;
		if( collection.isEmpty() )
			return defaultVal;
		return getStream( collection, parallel ).collect( collector );
	}

	/**
	 * Returns parallel stream if parallel is true. Otherwise, sequential stream
	 * @param collection
	 * @param parallel
	 * @return {@link Stream}
	 */
	private static <T> Stream<T> getStream( Collection<T> collection, boolean parallel ) {
		return parallel ? collection.parallelStream() : collection.stream();
	}
	
	/**
	 * Returns true if collection is empty or null. Otherwise, false.
	 * <pre>
	 * CollectionUtils.isEmpty( null ) = true
	 * CollectionUtils.isEmpty( [] ) = true
	 * CollectionUtils.isEmpty( [1,2] ) = false
	 * </pre>
	 * @param collection
	 * @return boolean
	 */
	public static <T> boolean isEmpty( Collection<T> collection ) {
		return collection == null || collection.isEmpty() ? true : false;
	}
	
	/**
	 * Returns false if collection is empty or null. Otherwise, true.
	 * <pre>
	 * CollectionUtils.isEmpty( null ) = false
	 * CollectionUtils.isEmpty( [] ) = false
	 * CollectionUtils.isEmpty( [1,2] ) = true
	 * </pre>
	 * @param collection
	 * @return boolean
	 */
	public static <T> boolean isNotEmpty( Collection<T> collection ) {
		return !isEmpty( collection );
	}
	
	public static <T,R> Map<R,Long> groupByCount( Collection<T> collection, Function<T,R> groupByFunc ) {
		//return collect( collection, groupingBy( groupByFunc, counting() ), new HashMap<>(), false );
		return groupBy( collection, groupByFunc, counting(), false );
	}
	
	public static <T,R> Map<R,Long> groupByCountParallely( Collection<T> collection, Function<T,R> groupByFunc ) {
		//return collect( collection, groupingBy( groupByFunc, counting() ), new HashMap<>(), true );
		return groupBy( collection, groupByFunc, counting(), true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R,E extends Collection<T>> Map<R, E> groupBy( E collection, Function<T,R> groupByFunc ) {
		//return collect( collection, groupingBy( groupByFunc ), new HashMap<>(), true );
		return ( Map<R,E> ) groupBy( collection, groupByFunc, false, null );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R,E extends Collection<T>> Map<R, E> groupByParallely( E collection, Function<T,R> groupByFunc ) {
		//return collect( collection, groupingBy( groupByFunc ), new HashMap<>(), true );
		return ( Map<R, E> ) groupBy( collection, groupByFunc, true, null );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R,E extends Collection<T>,V> Map<R, ? extends Collection<V>> groupByMapping( E collection
			, Function<T,R> groupByFunc, Function<T,V> mapFunc ) {
		return ( Map<R, ? extends Collection<V>> ) groupBy( collection, groupByFunc, false, mapFunc );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R,E extends Collection<T>,V> Map<R, ? extends Collection<V>> groupByMappingParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> mapFunc ) {
		return ( Map<R, ? extends Collection<V>> ) groupBy( collection, groupByFunc, true, mapFunc );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<V>> groupByMin( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> minFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, minBy( comparing( val -> (V) val ) ) ) )
					 , new HashMap<>(), false );*/
		return groupBy( collection
					  , groupByFunc
					  , mapping( minFunc, minBy( comparing( val -> val ) ) )
					  , false );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<V>> groupByMinParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> minFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, minBy( comparing( identity() ) ) ) )
					 , new HashMap<>(), true );*/
		return groupBy( collection
					 , groupByFunc
					 , mapping( minFunc, minBy( comparing( val -> val ) ) )
					 , true );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<T>> groupByMinObject( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> minFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, minBy( comparing( minFunc ) ) )
					 , new HashMap<>(), false );*/
		return groupBy( collection
				 	 , groupByFunc
				 	 , minBy( comparing( minFunc ) ) 
				 	 , false );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<T>> groupByMinObjectParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> minFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, minBy( comparing( minFunc ) ) ) 
					 , new HashMap<>(), true );*/
		return groupBy( collection
			 	 	  , groupByFunc
			 	 	  , minBy( comparing( minFunc ) ) 
			 	 	  , true );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<V>> groupByMax( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> maxFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, maxBy( comparing(  val -> (V) val  ) ) ) )
					 , new HashMap<>(), false );*/
		return groupBy( collection
				 	 , groupByFunc
				 	 , mapping( maxFunc, maxBy( comparing( val -> val ) ) )
				 	 , false );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<V>> groupByMaxParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> maxFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, maxBy( comparing(  val -> (V) val  ) ) ) )
					 , new HashMap<>(), true );*/
		return groupBy( collection
			 	 	 , groupByFunc
			 	 	 , mapping( maxFunc, maxBy( comparing( val -> val ) ) )
			 	 	 , true );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<T>> groupByMaxObject( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> maxFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, maxBy( comparing(  val -> (V) val  ) ) ) )
					 , new HashMap<>(), false );*/
		return groupBy( collection
			 	 	 , groupByFunc
			 	 	 , maxBy( comparing( maxFunc ) ) 
			 	 	 , false );
	}
	
	public static <T,R,V extends Comparable<? super V>> Map<R, Optional<T>> groupByMaxObjectParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,V> maxFunc ) {
		/*return collect( collection, groupingBy( groupByFunc, mapping( minFunc, maxBy( comparing(  val -> (V) val  ) ) ) )
					 , new HashMap<>(), true );*/
		return groupBy( collection
			 	 	 , groupByFunc
			 	 	 , maxBy( comparing( maxFunc ) ) 
			 	 	 , false );
	}
	
	public static <T,R,V> Map<R,Double> groupByAvg( Collection<T> collection
			, Function<T,R> groupByFunc, ToIntFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingInt( avgFunc ), false );
	}
	public static <T,R,V> Map<R,Double> groupByAvgParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToIntFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingInt( avgFunc ), true );
	}
	public static <T,R,V> Map<R,Double> groupByAvg( Collection<T> collection
			, Function<T,R> groupByFunc, ToLongFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingLong( avgFunc ), false );
	}
	public static <T,R,V> Map<R,Double> groupByAvgParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToLongFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingLong( avgFunc ), true );
	}
	public static <T,R,V> Map<R,Double> groupByAvg( Collection<T> collection
			, Function<T,R> groupByFunc, ToDoubleFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingDouble( avgFunc ), false );
	}
	public static <T,R,V> Map<R,Double> groupByAvgParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToDoubleFunction<T> avgFunc ) {
		return groupBy( collection, groupByFunc, averagingDouble( avgFunc ), true );
	}
	
	public static <T,R,V> Map<R, Integer> groupBySum( Collection<T> collection
			, Function<T,R> groupByFunc, ToIntFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingInt( sumFunc ), false );
	}
	public static <T,R,V> Map<R, Integer> groupBySumParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToIntFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingInt( sumFunc ), true );
	}
	public static <T,R,V> Map<R, Long> groupBySum( Collection<T> collection
			, Function<T,R> groupByFunc, ToLongFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingLong( sumFunc ), false );
	}
	public static <T,R,V> Map<R, Long> groupBySumParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToLongFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingLong( sumFunc ), true );
	}
	public static <T,R,V> Map<R,Double> groupBySum( Collection<T> collection
			, Function<T,R> groupByFunc, ToDoubleFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingDouble( sumFunc ), false );
	}
	public static <T,R,V> Map<R,Double> groupBySumParallely( Collection<T> collection
			, Function<T,R> groupByFunc, ToDoubleFunction<T> sumFunc ) {
		return groupBy( collection, groupByFunc, Collectors.summingDouble( sumFunc ), true );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAgg( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,String> aggFunc ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining() ), false );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAggParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,String> aggFunc ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining() ), true );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAgg( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,String> aggFunc, String delimiter ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining( delimiter ) ), false );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAggParallely( Collection<T> collection
			, Function<T,R> groupByFunc, Function<T,String> aggFunc, String delimiter ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining( delimiter ) ), true );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAgg( Collection<T> collection, Function<T,R> groupByFunc
			, Function<T,String> aggFunc, String delimiter, String prefix, String suffix ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining( delimiter, prefix, suffix) ), false );
	}
	
	public static <T,R,V> Map<R,String> groupByStringAggParallely( Collection<T> collection, Function<T,R> groupByFunc
			, Function<T,String> aggFunc, String delimiter, String prefix, String suffix ) {
		return groupBy( collection, groupByFunc, mapping( aggFunc, joining( delimiter, prefix, suffix) ), true );
	}
	
	public static <T> Set<T> distint( Collection<T> collection ) {
		return distintValue( collection, val -> val );
	}
	
	public static <T> Set<T> distintParallely( Collection<T> collection ) {
		return distintValueParallely( collection, val -> val );
	}
	
	public static <T,R extends Comparable<? super R>> Optional<T> min( Collection<T> collection
			, Function<T,R> mapper ) {
		return collect( collection, minBy( comparing( mapper ) ), ofNullable( null ), false );
	}
	
	public static <T,R extends Comparable<? super R>> Optional<T> minParallely( Collection<T> collection
			, Function<T,R> mapper ) {
		return collect( collection, minBy( comparing( mapper ) ), ofNullable( null ), false );
	}
	
	public static <T extends Comparable<? super T>> Optional<T> min( Collection<T> collection ) {
		return collect( collection, minBy( comparing( val -> val ) ), ofNullable( null ), false );
	}
	
	public static <T extends Comparable<? super T>> Optional<T> minParallely( Collection<T> collection ) {
		return collect( collection, minBy( comparing( val -> val ) ), ofNullable( null ), false );
	}
	
	public static <T,R extends Comparable<? super R>> Optional<T> max( Collection<T> collection
			, Function<T,R> mapper ) {
		return collect( collection, maxBy( comparing( mapper ) ), ofNullable( null ), false );
	}
	
	public static <T,R extends Comparable<? super R>> Optional<T> maxParallely( Collection<T> collection
			, Function<T,R> mapper ) {
		return collect( collection, maxBy( comparing( mapper ) ), ofNullable( null ), false );
	}
	
	public static <T> Integer summingInt( Collection<Integer> collection ) {
		return summingInt( collection, val -> val );
	}
	
	public static <T> Integer summingInt( Collection<T> collection, ToIntFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingInt( sumFunc ), 0, false );
	}
	
	public static <T> Integer summingIntParallely( Collection<Integer> collection ) {
		return summingIntParallely( collection, val -> val );
	}
	
	public static <T> Integer summingIntParallely( Collection<T> collection, ToIntFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingInt( sumFunc ), 0, true );
	}
	
	public static <T> Long summingLong( Collection<Long> collection ) {
		return summingLong( collection, val -> val );
	}
	
	public static <T> Long summingLong( Collection<T> collection, ToLongFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingLong( sumFunc ), 0l, false );
	}
	
	public static <T> Long summingLongParallely( Collection<Long> collection ) {
		return summingLongParallely( collection, val -> val );
	}
	
	public static <T> Long summingLongParallely( Collection<T> collection, ToLongFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingLong( sumFunc ), 0l, true );
	}
	
	public static <T> Double summingDouble( Collection<Double> collection ) {
		return summingDouble( collection, val -> val );
	}
	
	public static <T> Double summingDouble( Collection<T> collection, ToDoubleFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingDouble( sumFunc ), 0d, false );
	}
	
	public static <T> Double summingDoubleParallely( Collection<Double> collection ) {
		return summingDoubleParallely( collection, val -> val );
	}
	
	public static <T> Double summingDoubleParallely( Collection<T> collection, ToDoubleFunction<T> sumFunc ) {
		return collect( collection, Collectors.summingDouble( sumFunc ), 0d, true );
	}
	
	public static <T,R> Set<R> distintValue( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( mapper, toSet() ), new HashSet<>(), false );
	}
	
	public static <T,R> Set<T> distintValueParallely( Collection<T> collection, Function<T,R> mapper ) {
		return collect( collection, mapping( val -> val, toSet() ), new HashSet<>(), true );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends Comparable<? super R>, E extends Collection<T>> E orderBy( E collection
			, Function<T,R> sortFunc ) {
		return ( E ) sort( collection, false, comparing( sortFunc ) );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends Comparable<? super R>, E extends Collection<T>> E orderByDesc( E collection
			, Function<T,R> sortFunc ) {
		return ( E ) sort( collection, false, comparing( sortFunc, reverseOrder() ) );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends Comparable<? super R>, E extends Collection<T>> E orderByParallely( E collection
			, Function<T,R> sortFunc ) {
		return ( E ) sort( collection, false, comparing( sortFunc ) );
	}
	
	@SuppressWarnings("unchecked")
	public static <T,R extends Comparable<? super R>, E extends Collection<T>> E orderByDescParallely( E collection
			, Function<T,R> sortFunc ) {
		return ( E ) sort( collection, false, comparing( sortFunc, reverseOrder() ) );
	}
	
	public static <T,R extends Comparable<? super R>, E extends Collection<T>> Collection<T> sort( E collection
			, boolean parallel, Comparator<T> comparator ) {
		if( isEmpty( collection ) )
			return collection;
		return collection.stream()
						 .sorted( comparator )
						 .collect( getCollector( collection ) );
	}
	
	private static <T,R,E extends Collection<T>,V> Map<R,?> groupBy( E collection, Function<T, R> groupByFunc
			, boolean parallel, Function<T,V> mapFunc ) {
		Collector<T,?,? extends Collection<?>> collector = 
								mapFunc != null ? mapping( mapFunc, getCollector( collection )) : getCollector( collection );
		return collect( collection, groupingBy( groupByFunc, collector )
									, new HashMap<>(), parallel );
	}
	
	private static <T,R,V> Map<R,V> groupBy( Collection<T> collection, Function<T, R> groupByFunc
			, Collector<T, ?, V> collector, boolean parallel ) {
		return collect( collection, groupingBy( groupByFunc, collector )
					 , new HashMap<>(), parallel );
	}
	
	private static <T,E extends Collection<?>> Collector<T,?,? extends Collection<?>> getCollector( E collection ) {
		return collection instanceof List ? toList() : toSet();
	}
	
}
