import java.util.Arrays;

/**
 * @description: 一些骚操作
 * @author: 吕立屏
 * @date: 2020/05/17
 */
public class Stunt {

}


/**
 * @decription: 242.有效的字母异位词
 * @solution: 排序，比较
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/valid-anagram/
 * @date: 2020/5/17
 */
class Solution242_sort {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ct);
        return Arrays.equals(cs, ct);
    }
}


/**
 * @decription: 169.多数元素，返回数组中出现次数大于长度一半的元素
 * @solution: 若出现次数大于一半，则让他减去其他所有元素出现次数后，这个出现次数还是大于0
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/majority-element/
 * @date: 2021/3/10
 */
class Solution169_balance {
    public int majorityElement(int[] nums) {
        int majI = 0, balance = 1;
        for (int i : nums) {
            if (i == majI) {
                balance++;
            } else if (--balance == 0) {
                majI = i;
                balance = 1;
            }
        }
        return majI;
    }
}
