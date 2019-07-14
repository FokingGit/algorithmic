package foking.sort;

import java.util.Arrays;

public class SortUtil {


    /**
     * 归并排序
     */
    public static void merge_sort(int[] sourceArray) {
        long startTime = System.currentTimeMillis();

        //数据进行分解到最小单位，然后进行合并排序
        merge_sort_a(sourceArray, 0, sourceArray.length - 1);
        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
        System.out.println(Arrays.toString(sourceArray));
    }

    private static void merge_sort_a(int[] sourceArray, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;

        int pivot = (endIndex + startIndex) / 2;

        merge_sort_a(sourceArray, startIndex, pivot);
        merge_sort_a(sourceArray, pivot + 1, endIndex);

        mergeArray(sourceArray, startIndex, endIndex, pivot);
    }

    public static void mergeArray(int[] sourceArray, int startSourceIndex, int endSourceIndex, int pivotIndex) {
        //申请一个新的数组
        int[] tempArray = new int[endSourceIndex - startSourceIndex + 1];

        int tempArrayindex = 0;

        int leftStartIndex = startSourceIndex;
        int rightStartIndex = pivotIndex + 1;

        while (leftStartIndex <= pivotIndex && rightStartIndex <= endSourceIndex) {
            if (sourceArray[leftStartIndex] < sourceArray[rightStartIndex]) {
                tempArray[tempArrayindex++] = sourceArray[leftStartIndex++];
            } else {
                tempArray[tempArrayindex++] = sourceArray[rightStartIndex++];
            }
        }

//        //判断那个数组到终点了
//        if (startIndex == pivotIndex + 1) {
//            while (rightStartIndex <= endIndex) {
//                tempArray[tempArrayindex++] = sourceArray[rightStartIndex++];
//            }
//        } else if (rightStartIndex == endIndex + 1) {
//            while (startIndex <= pivotIndex) {
//                tempArray[tempArrayindex++] = sourceArray[startIndex++];
//            }
//        }

        int start = leftStartIndex;
        int end = pivotIndex;

        //todo 临界点的判断,会出现rightStartIndx == endIndex情况，这个时候对应的endIndex中的值还没有存到临时数组中，所以判断条件要添加
        if (rightStartIndex <= endSourceIndex) {
            start = rightStartIndex;
            end = endSourceIndex;
        }

        while (start <= end) {
            tempArray[tempArrayindex++] = sourceArray[start++];
        }

        for (int i = 0; i < tempArray.length; i++) {
            sourceArray[startSourceIndex + i] = tempArray[i];
        }

    }


    /**
     * 快速排序
     */
    public static void quickSort(int[] sourceArray) {
        long startTime = System.currentTimeMillis();

        //选取一个分界点(Q)，将这个数据对这个节点进行排序
        //Q点左侧小于Q的点，右侧是大于Q的点

        sort(sourceArray, 0, sourceArray.length - 1);
        System.out.println("用时:" + (System.currentTimeMillis() - startTime));

    }

    private static void sort(int[] sourceArray, int startIndex, int endIndex) {
        //递归的出口条件
        if (startIndex >= endIndex) return;

        //获取区分点
        int index = getPivot(sourceArray, startIndex, endIndex);

        sort(sourceArray, startIndex, index - 1);
        sort(sourceArray, index + 1, endIndex);

    }

    private static int getPivot(int[] sourceArray, int startIndex, int endIndex) {
        int i = startIndex;
        int pivot = sourceArray[endIndex];

        for (int j = startIndex; j < endIndex; j++) {
            if (sourceArray[j] < pivot) {
                int temp = sourceArray[j];
                sourceArray[j] = sourceArray[i];
                sourceArray[i] = temp;
                i++;
            }
        }
        //将分界点 设置到正确位置
        int temp = sourceArray[i];
        sourceArray[i] = pivot;
        sourceArray[endIndex] = temp;
        return i;
    }


    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] sourceArray) {
        long startTime = System.currentTimeMillis();
        int length = sourceArray.length;
        int tempValue;
        for (int i = 0; i < length; i++) {
            boolean isChange = false;
            for (int j = i + 1; j < length; j++) {
                if (sourceArray[i] > sourceArray[j]) {
                    isChange = true;
                    tempValue = sourceArray[j];
                    sourceArray[j] = sourceArray[i];
                    sourceArray[i] = tempValue;
                }
            }
            if (!isChange) {
                break;
            }
        }
        System.out.println(Arrays.toString(sourceArray));
        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] sourceArray) {
        long startTime = System.currentTimeMillis();
        int length = sourceArray.length;

        for (int i = 1; i < length; i++) {
            int value = sourceArray[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (sourceArray[j] > value) {
                    sourceArray[j + 1] = sourceArray[j];
                } else {
                    break;
                }
            }
            sourceArray[j + 1] = value;
        }

        System.out.println(Arrays.toString(sourceArray));
        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 选择排序
     */
    public static void selectionSort(int[] sourceArray) {
        long startTime = System.currentTimeMillis();
        int length = sourceArray.length;

        for (int i = 0; i < length; i++) {
            //找出最小元素
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;
            int tempValue;

            for (int j = i; j < length; j++) {
                if (sourceArray[j] < minValue) {
                    minValue = sourceArray[j];
                    minIndex = j;
                }
            }

            if (minIndex != -1) {
                tempValue = sourceArray[minIndex];
                sourceArray[minIndex] = sourceArray[i];
                sourceArray[i] = tempValue;
            }
        }

        System.out.println(Arrays.toString(sourceArray));
        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
    }
}
