import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 利用数组解决的问题
 *               经典解法：双指针：快慢指针停滞做操作，首尾指针夹逼做操作
 * @author: 吕立屏
 */
public class MyArrayList {

}


/**
 * @decription: 283.移动0，0全到后面，其他保持原序
 * @solution: 利用快慢指针，非0元素两者同步递增，当遇0停滞慢指针直到快指针找到非0数相交换
 * @difficulty: 简单, 一次遍历，O(n)
 * @url: https://leetcode-cn.com/problems/move-zeroes/
 * @date: 2020/5/5
 */
class Solution283 {

    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        // fast 快指针，遍历数组所有元素
        // slow 慢指针，遇到0便停滞, 方便 fast 遇到非0元素时双方交换
        for (int fast = 0, slow = 0, temp; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
        }
    }
}



/**
 * @decription: 11.盛最多水的容器，求两柱之间面积最大
 * @solution: 首尾指针夹逼，每次保留高柱
 * @difficulty: 中等, 每个元素都是访问一次，O(n)
 * @url: https://leetcode-cn.com/problems/container-with-most-water/
 * @date: 2020/5/5
 */
class Solution11 {

    public int maxArea(int[] heights) {
        int lowBar;
        int maxArea = 0;
        // 首尾双指针
        for (int l = 0, r = heights.length - 1; l < r; ) {
            // 选择低的一方做高度，同时将低的一方往另一方逼近（留下高的柱子）
            lowBar = heights[l] < heights[r] ? heights[l++] : heights[r--];
            // 因为指针已经往里靠近，所以下面要+1获得原来的距离
            maxArea = Math.max(maxArea, lowBar * (r - l + 1));
        }
        return maxArea;
    }
}


/**
 * @decription: 15.三数之和，返回三元组的集合，每个三元组的元素来自数组且相加得到0
 * @solution: 先使数组有序，定位一个元素，后置首尾指针，夹逼取数
 * @difficulty: 中等, O(n^2)
 * @url: https://leetcode-cn.com/problems/3sum/
 * @date: 2020/5/5
 */
class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        // 有序的数组，才使得后续操作有意义
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) break;
            if ( i > 0 && nums[i-1] == nums[i] ) continue;  // 去重
            for (int l = i + 1, r = len - 1; l < r; ) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;  // 去重
                    while (l < r && nums[r] == nums[r-1]) r--;  // 去重
                    l++;
                    r--;
                }
                else if (sum < 0) l++;  // sum < 0 说明加的数还不够大，往后取更大的数
                else r--;
            }
        }
        return ans;
    }
}


/**
 * @decription: 16.最接近的三数之和
 * @solution: 三数之和改一改
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/3sum-closest/
 * @date: 2021/3/12
 */
class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                res = Math.abs(sum - target) < Math.abs(res - target) ? sum : res;
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    return res;
                }
            }
        }
        return res;
    }
}


/**
 * @decription: 26.删除排序数组中的重复项，返回有效长度
 * @solution: 快慢指针，快慢指针数据不同时使慢指针后移，并将快指针内容覆盖慢指针内容
 *            总的来看就是，快指针未发现重复数据时总是在覆盖慢指针，当快指针遇到重复数据时，慢指针便停滞了
 *            所以当快指针再次遇到新值便可以将新值赋给慢指针（此时指向重复数据的位置）
 *            当然这一切都是建立在有序数组的基础上
 * @difficulty: 简单，O(n)
 * @url: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * @date: 2020/5/6
 */
class Solution26 {

    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int quick = 1; quick < nums.length; quick++) {
            if (nums[slow] != nums[quick]) {
                nums[++slow] = nums[quick]; // 给++slow位赋值是因为要保留slow，++slow的数字才是视作重复的数，用来被覆盖
            }
        }
        return slow + 1;
    }
}


/**
 * @decription: 189.旋转数组，将i位的数移动k位
 * @solution: 解法1，创建新数组存储，不推荐的解法, 不能使用常数空间
 * @difficulty: 简单, O(n)
 * @url: https://leetcode-cn.com/problems/rotate-array/
 * @date: 2020/5/6
 */
class Solution189 {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] rot = new int[len];
        k %= len;
        for (int i = 0; i < nums.length; i++) {
            int idx = (i + k) % len;
            rot[idx] = nums[i];
        }
        // 注意这里需要一个一个赋值
        // 因为我们没法改变外部引用变量的指向对象
        // （我们不能企图使用 nums = rot 去改变外部调用方法的引用变量的引用对象）
        // 我们只能通过引用去改变外部引用变量指向对象的值
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rot[i];
        }
    }
}


/**
 * @decription: 189.旋转数组，将i位的数移动k位
 * @solution: 解法2，三次反转，观察旋转后的数组，满足这样的性质：
 *            k%n 个尾部元素会被移动到头部, 剩下的就是往后移动 k%n 位.
 *            所以我们可以手动三次反转数组，一次全反转，一次前k位反转，一次后k位反转
 *            变得到旋转数组
 * @difficulty: 简单, O(n)
 * @url: https://leetcode-cn.com/problems/rotate-array/
 * @date: 2020/5/6
 */
class Solution189_rotate {

    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    // 反转数组的函数
    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}


/**
 * @decription: 136.只出现一次的数字，返回给定数组中只出现一次的一个数字
 * @solution: 利用排序+双指针，可以不使用额外空间复杂度
 *            利用快慢指针比较，重复则同步递增下去
 *            不重复时看是不是边界，边界直接返回
 *            不是边界则要看再下一个数是否重复，不重复说明当前是只出现一次的
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/single-number/
 * @date: 2021/3/12
 */
class Solution136 {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int fast = 1, slow = 0; fast < n; fast++) {
            if (nums[fast] == nums[slow++]) continue;
            if (fast == 1) {
                return nums[0];
            }
            if (fast == n - 1) {
                return nums[fast];
            }
            if (nums[fast] != nums[fast + 1]) {
                return nums[fast];
            }
        }
        return -1;
    }
}


/**
 * @decription: 747.至少是其他数字两倍的最大数
 * @solution: 一次遍历找最大，一次遍历比对2倍约束
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
 * @date: 2021/3/18
 */
class Solution747 {
    public int dominantIndex(int[] nums) {
        int idx = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= nums[idx]) {
                idx = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (2 * nums[i] > nums[idx]) {
                if (i == idx) {
                    continue;
                }
                return -1;
            }
        }
        return idx;
    }
}


/**
 * @decription: 724.寻找数组的中心下标
 * @solution: 一次遍历求和，一次遍历比对当前左边累计和是否满足与sum的关系
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/find-pivot-index/
 * @date: 2021/3/18
 */
class Solution724 {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                leftSum += nums[i - 1];
            }
            if (leftSum * 2 == sum - nums[i]) {
                return i;
            }
        }
        return -1;
    }
}