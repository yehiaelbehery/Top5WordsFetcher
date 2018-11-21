package utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapUtility {
	/**
	 * Turn hashMap into map and sort it descending
	 */
	public static Map<String, Integer> sortByValueDesc(Map<String, Integer> unsortMap) {

        //Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sort list with Collections.sort(), provide a custom Comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

	/**
	 * Get the first n elements from a map
	 */
	public static Map<String, Integer> getTheFirst(int count, Map<String, Integer> map) {
		Map<String, Integer> filteredMap = new LinkedHashMap<String, Integer>();
		Set<String> mapKeys = map.keySet();
		Iterator<String> it = mapKeys.iterator();
		for (int i=0;i < count;i++) {
			if (it.hasNext()) {
				String key = it.next().toString();
				filteredMap.put(key, map.get(key));
				it.remove();
			}else {
				break;
			}
		}
		return filteredMap;
		
	}
}
