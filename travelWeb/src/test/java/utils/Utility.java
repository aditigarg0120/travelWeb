package utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Utility {

	// Get The Current Day
		public static LocalDate getDeptDay(int no_of_weeks) {
			LocalDate today = LocalDate.now();
			// add 2 week to the current date
			LocalDate deptDate = today.plus(no_of_weeks, ChronoUnit.WEEKS);
			return deptDate;
		}

		// Get The Current Day
		public static LocalDate getReturnDay(int no_of_weeks,LocalDate deptDate) {
			LocalDate today = LocalDate.now();
			// add 2 week to the dept date
			LocalDate returnDate = deptDate.plus(no_of_weeks, ChronoUnit.WEEKS);
			return returnDate;
		}
		
		
		public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<Integer, Integer>> list =
	                new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
	            public int compare(Map.Entry<Integer, Integer> o1,
	                               Map.Entry<Integer, Integer> o2) {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
	        for (Map.Entry<Integer, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }



	        return sortedMap;
	    }	
	
	
}
