package com.muruga.collection.utils;

import static java.lang.System.out;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollectionUtilsTest {
	
	List<Employee> employees;
	List<Employee> employees1;
	Set<Employee> employeesSet;
	Set<Employee> employees1Set;
	
	@BeforeEach
	void setup() {
		//out.println( "started" );
		employees1 = asList( new Employee( "muruga", "k", "IT", 100 ) );
		employees1Set = new HashSet<>( employees1);
		employees = Arrays.asList( new Employee( "muruga", "k", "IT", 100 )
								, new Employee( "ananth", "k", "IT", 110 )
								, new Employee( "iniyan", "k", "HR", 130 )
								, new Employee( "ila", "k", "HR", 105)
								);
		employeesSet = new HashSet<>( employees );
	}

	/*@Test
	void testMapping() {
		List<String> fnames = CollectionUtils.mappingList( employees1, Employee::getFname );
		assertEquals( size(employees1), size(fnames) );
		assertEquals( fnames.get(0), "muruga" );
		fnames = CollectionUtils.mappingList( null,  Employee::getFname );
		assertNull( fnames );
		fnames = CollectionUtils.mappingList( new ArrayList<>(),  Employee::getFname );
		assertTrue( fnames.isEmpty() );
	}
	
	@Test
	void testExists() {
		Predicate<Employee> predicate = name -> name.getFname().equals( "muruga" );
		boolean exists = CollectionUtils.exists( null, predicate );
		assertFalse( exists );
		exists = CollectionUtils.exists( employees1, predicate );
		assertTrue( exists );
	}*/
	
	/*@Test
	void testOrderBy() {
		CollectionUtils.orderByDesc( employees, Employee::getFname )
					   .forEach( out::println );
	}*/
	
	@Test
	void testGroupBy() {
		CollectionUtils.groupBy( employees, Employee::getDept )
					   .forEach( (k,v) -> out.println( k + ":" + v ) );
		CollectionUtils.groupBy(employeesSet, Employee::getDept)
		   			   .forEach( (k,v) -> out.println( k + ":" + v ) );
	}
	
	@Test
	void testGroupByMapping() {
		CollectionUtils.groupByMapping( employees, Employee::getDept, Employee::getFname )
		   			   .forEach( (k,v) -> out.println( k + ":" + v ) );
		CollectionUtils.groupByMapping( employeesSet, Employee::getDept, emp -> emp.getLname() + "," + emp.getFname() )
					   .forEach( (k,v) -> out.println( k + ":" + v ) );
	}

	<T> int size( Collection<T> collection ) {
		return collection == null ? 0 : collection.size();
	}
}
