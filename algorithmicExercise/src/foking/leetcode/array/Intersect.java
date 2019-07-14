package foking.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersect {
    public static int[] run(int[] sum1, int[] sum2) {

        int sum1Length = sum1.length;
        int sum2Length = sum2.length;

        Map<Integer, Integer> shortMap = new HashMap<>();
        Map<Integer, Integer> longMap = new HashMap<>();


        int[] longArray;
        int[] shortArray;
        List<Integer> resultList = new ArrayList<>();

        if (sum1Length <= sum2Length) {
            shortArray = sum1;
            longArray = sum2;
        } else {
            shortArray = sum2;
            longArray = sum1;
        }
        for (int one : shortArray) {
            if (shortMap.containsKey(one)) {
                shortMap.put(one, shortMap.get(one) + 1);
            } else {
                shortMap.put(one, 1);
            }
        }

        for (int two : longArray) {
            if (longMap.containsKey(two)) {
                longMap.put(two, longMap.get(two) + 1);
            } else {
                longMap.put(two, 1);
            }
        }

        for (Map.Entry<Integer, Integer> next : shortMap.entrySet()) {
            Integer key = next.getKey();
            if (longMap.containsKey(key)) {
                int min = Math.min(longMap.get(key), shortMap.get(key));
                for (int i = 0; i < min; i++) {
                    resultList.add(key);
                }
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
