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
        int indexOfMaj = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[indexOfMaj]) {
                count++;
            } else if (--count == 0){
                indexOfMaj = i;
                count = 1;
            }
        }
        return nums[indexOfMaj];
    }
}


/**
 * @decription: 169.多数元素，返回数组中出现次数大于长度一半的元素
 * @solution: 将数组排序，若出现次数大于一半，则此数一定也出现在数组中间位置
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/majority-element/
 * @date: 2021/3/12
 */
class Solution169_sort {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}


/**
 * @decription: 200.岛屿数量，看有多数个被0围起来的1区域
 * @solution: 利用dfs的思想，首先遍历，遇到1，dfs进去将当前陆地1四散开来dfs，走过的设为2不再进入
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/number-of-islands/
 * @date: 2021/3/16
 */
class Solution200 {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == '1') {
                    ++count;
                    dfsAndRecord(grid, r, c);
                }
            }
        }
        return count;
    }

    void dfsAndRecord(char[][] grid, int r, int c) {
        // 出界，再见
        if (r == -1 || r == grid.length || c == -1 || c == grid[r].length) {
            return;
        }
        // 水或者走过，再见
        if (grid[r][c] != '1') {
            return;
        }
        // 设为走过
        grid[r][c] = '2';
        // 四散开来dfs掉当前这一区域的陆地块
        dfsAndRecord(grid, r - 1, c);
        dfsAndRecord(grid, r + 1, c);
        dfsAndRecord(grid, r, c - 1);
        dfsAndRecord(grid, r, c + 1);
    }

}