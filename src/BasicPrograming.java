/**
 * @description: 基本操作
 * @author: 吕立屏
 * @date: 2021/03/18
 */
public class BasicPrograming {
    public static void main(String[] args) {

    }
}


/**
 * @decription: 415.字符串相加
 * @solution: 看就懂
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/add-strings/
 * @date: 2021/3/18
 */
class Solution415 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i > -1 || j > -1; i--, j--) {
            int n1 = i > -1 ? num1.charAt(i) - '0' : 0;
            int n2 = j > -1 ? num2.charAt(j) - '0' : 0;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            sb.append(temp % 10);
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}


/**
 * @decription: 4.寻找两个正序数组的中位数
 * @solution: 有序合并两个数组的前部分，使得新数组有两个加起来一半长就行
 *            奇数长度：返回正中间 n / 2
 *            偶数长度：返回中间两个和的一半 n / 2 - 1, n / 2
 * @difficulty:
 * @url:
 * @date: 2021/3/18
 */
class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length, half = n / 2;
        int[] merge = new int[n];
        int i = 0, j = 0, k = 0;
        while (k <= half && i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merge[k++] = nums1[i++];
            } else {
                merge[k++] = nums2[j++];
            }
        }
        if (k <= half) {
            while (k <= half && i < nums1.length) merge[k++] = nums1[i++];
            while (k <= half && j < nums2.length) merge[k++] = nums2[j++];
        }
        return n % 2 == 1 ? merge[half] : (double) (merge[half] + merge[half - 1]) / 2;
    }
}