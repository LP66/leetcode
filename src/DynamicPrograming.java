/**
 * @description: 利用动态规划解决的问题
 * @author: 吕立屏
 */
public class DynamicPrograming {

}


/**
 * @decription: 70.爬楼梯, 一次跨1/2步，到达n台阶有多少种方法
 * @solution: 利用最近重复子问题，假设我们从第二个台阶开始往上爬
 * @difficulty: 简单, 单循环到n，需要计算第n个斐波那契数, O(n)
 * @url: https://leetcode-cn.com/problems/climbing-stairs/
 * @date: 2020/5/5
 */
class Solutions70 {

    public int climbStairs(int n) {
        // 到第一个台阶只能走一步，即一种情况
        if (n == 1) return 1;
        // 初始我们在now即第2个台阶
        // 我们可以是从地面（0）跨两步上来，也可以是第一个台阶（1）跨一步上来，所以为now情况数为2
        // 而我们下一步台阶是第三个台阶，情况数便是第一个台阶的情况数 + 第二个台阶的情况数
        int prev = 1, now = 2, next;
        // 我们从now，即第2个台阶开始向上跨步
        for (int i = 2; i < n; i++) {
            // 我们从地面到达下一步有这么多种情况
            next = prev + now;
            // 我们到达下一个台阶
            prev = now;
            now = next;
        }
        return now;
    }

}