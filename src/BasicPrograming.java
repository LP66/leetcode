import java.util.Arrays;

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


/**
 * @decription: 215.数组中的第k个最大元素（不是最大的第k个不同）
 * @solution: 排序，返回倒数第k个
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @date: 2021/3/18
 */
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    void quickSort(int[] nums, int left, int right) {
        int l = left, r = right, mid = l + (r - l) / 2;
        int pivot = nums[mid], temp;
        while (l < r) {
            while (nums[l] < pivot) l++;
            while (nums[r] > pivot) r--;
            if (l >= r) break;
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            if (nums[l] == pivot) r--;
            if (nums[r] == pivot) l++;
        }
        if (l == r) {
            l++;
            r--;
        }
        if (l < right) {
            quickSort(nums, l, right);
        }
        if (left < r) {
            quickSort(nums, left, r);
        }
    }
}


/**
 * @decription: 66.加一，给定每位单数字的数组，最后一位加1
 * @solution: 注意一下进位
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/plus-one/
 * @date: 2021/3/18
 */
class Solution66 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            ++digits[i];
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}