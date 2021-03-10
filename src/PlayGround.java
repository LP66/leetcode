import java.util.*;

/**
 * @description:
 * @author: 吕立屏
 * @date: 2021/03/08
 */
public class PlayGround {

    public static void main(String[] args) {
        System.out.println("have fun~");
    }
}


class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            if (count.containsKey(i)) {
                count.put(i, count.get(i) + 1);
            } else {
                count.put(i, 1);
            }
        }
        int bound = nums.length / 2;
        for (int i : nums) {
            if (count.get(i) >= bound) {
                return i;
            }
        }
        return -1;
    }
}