package foking;

public class OtherUtil {

    public static int cellCount(int n) {

        return 0;
    }

    private static boolean Huiwen() {
        // write your code here
        String huiwen = "level";
        char[] chars = huiwen.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                System.out.println("该数据不是回文数");
                return true;
            }
        }
        System.out.println("该数据是回文数");
        return false;
    }

    //如何编程实现“求一个数的平方根”？要求精确到小数点后 6 位
    public static float getSquaerRoot(int value, float low, float high) {
        float mid = low + ((high - low) / 2);
        if (Math.abs(value - mid * mid) < 0.00001F) {
            return mid;
        } else if (mid * mid < value) {
            return getSquaerRoot(value, mid, high);
        } else {
            return getSquaerRoot(value, low, mid);
        }
    }
}
