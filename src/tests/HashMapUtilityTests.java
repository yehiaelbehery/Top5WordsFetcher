package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import utility.HashMapUtility;

class HashMapUtilityTests {

	@Test
	void testSort() {

		Map<String, Integer> unsortMap = new HashMap<String, Integer>();
        
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("z", 10);
		
		Map<String, Integer> sortedMap = HashMapUtility.sortByValueDesc(unsortMap);
		Set<String> sortedMapKeys = sortedMap.keySet();
		Iterator<String> it = sortedMapKeys.iterator();
		assertTrue(it.next() == "z");
		it.remove();
		assertTrue(it.next() == "a");
		it.remove();
		assertTrue(it.next() == "b");
	}
	

	@Test
	void testGetFirstN() {

		Map<String, Integer> map = new HashMap<String, Integer>();
        
		map.put("b", 5);
		map.put("a", 6);
		map.put("z", 10);
		map.put("z", 10);
		
		Map<String, Integer> filteredMap = HashMapUtility.getTheFirst(2, map);
		
		Set<String> filteredMapKeys = filteredMap.keySet();
		Iterator<String> it = filteredMapKeys.iterator();
		it.remove();
		it.remove();
		assertFalse(it.hasNext());

	}
}
