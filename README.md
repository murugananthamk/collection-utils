#Collection Utils
Collection utils provides utility methods for java 8 collection framework. The utility methods are groupBy, map, filter, sum, minimum, maximum, average, etc. 

###Examples
1. To get the list of employee first names from each department:
```java
Map<String, List<String> empNames = CollectionUtils.groupByMapping( employees
													   			, Employee::getDepartment
													   			, Employee::getFirstName() );
```
2. To get the average salary from each department:
```java
Map<String,Double> salaryAvg = CollectionUtils.groupByAvg( employees
														, Employee::getDepartment()
														, Employee::getSalary() );
```

###Build
This project is built using maven. Build Command: `mvn clean package`

###Test
JUnit is used for unit testing. Test Command: `mvn clean test`

##License
This code is under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).