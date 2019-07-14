package foking.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArrayDemoContainer {

    public static void ratate(int[] array, int step) {
        if (step <= 0) return;
        if (step > array.length) {
            step = (step % array.length);
        }

        int[] temp = new int[step];

        //把需要旋转值先储存起来
        for (int i = 0; i < step; i++) {
            temp[i] = array[array.length - step + i];
        }
        //原来数组剩下的数据先依次向后转
        for (int i = array.length - step - 1; i >= 0; i--) {
            array[i + step] = array[i];
        }

        //将原来的数据放进新数组中
        for (int i = 0; i < step; i++) {
            array[i] = temp[i];
        }

    }

    public static int getDuplication(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], 2);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
