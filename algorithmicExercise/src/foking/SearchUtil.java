package foking;

public class SearchUtil {

    /**
     * 简单的二分查找 顺序结构 没有重复数据
     *
     * @param sourceArray 数据
     * @param value       要查找的值
     * @param startIdex   区间开始的索引
     * @param endIndex    区间结束的索引
     * @return 目标值的索引，没有目标值则返回-1
     */
    public static int binarySerach(int[] sourceArray, int value, int startIdex, int endIndex) {
        int mid = startIdex + ((endIndex - startIdex) >> 1);
        if (startIdex <= endIndex)
            if (sourceArray[mid] == value) {
                return mid;
            } else if (sourceArray[mid] < value) {
                return binarySerach(sourceArray, value, mid + 1, endIndex);
            } else if (sourceArray[mid] > value) {
                return binarySerach(sourceArray, value, startIdex, mid - 1);
            }

        return -1;
    }


}
