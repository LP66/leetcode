/**
 * @description: 暴力解法
 * @author: 吕立屏
 * @date: 2021/01/29
 */
public class BruteForce {

}


/**
 * @decription: 84.柱状图中最大的矩形
 * @solution: 暴力遍历，以当前柱子为高度，遍历每根柱子左右跨度，得到面积与MaxArea做比较
 * @difficulty: 困难
 * @url: https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @date: 2020/5/17
 */
class Solution84_bf {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int l = i, r = i;
            while (l > 0 && heights[l - 1] >= heights[i]) {
                l--;
            }
            while (r < heights.length - 1 && heights[r + 1] >= heights[i]) {
                r++;
            }
            res = Math.max(res, heights[i] * (r - l + 1));
        }
        return res;
    }
}


/**
 * @decription: 5.最长回文串
 * @solution: 暴力遍历所有子串，判定当前子串是否比记录的最长回文子串还长
 *            是则判断是否回文串，是回文穿则更新
 * @difficulty: 中等
 * @url:
 * @date: 2021/3/18
 */
class Solution5 {
    public String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) return s;
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                if (j - i + 1 < res.length()) continue;
                if (judge(s, i, j)) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    boolean judge(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}


/**
 * @decription: 724.寻找数组的中心下标
 * @solution: 暴力遍历，分别计算左右两侧的和, 会有大量重复计算导致慢
 *            建议看另一个Solution比对总结
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/find-pivot-index/
 * @date: 2021/3/18
 */
class Solution724_bf {

    public int pivotIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int left = sum(nums, 0, i - 1);
            int right = sum(nums, i + 1, nums.length - 1);
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    int sum(int[] nums, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += nums[i];
        }
        return sum;
    }
}