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