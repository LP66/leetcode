import org.junit.Test;

import java.util.*;

/**
 * @description: 递归、回溯、分治
 * @author: 吕立屏
 */
public class MyRecursion {

    @Test
    public void test1() {
        // 典型的递归程序结构

        // 递归退出条件

        // 核心处理逻辑

        // 调用自己继续递归
    }

}


/**
 * @decription: 20.括号生成，根据给定的n，生成所有可能n对括号
 * @solution: 利用递归思想，最小子问题便是能不能加左括号，能不能加右括号
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/generate-parentheses/
 * @date: 2021/3/4
 */
class Solution20 {

    private List<String> res = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        generate(0, 0, n, "");
        return res;
    }

    private void generate(int left, int right, int max, String current) {
        if (right == max) {
            res.add(current);
            return;
        }

        if (left < max) {
            generate(left + 1, right, max, current + "(");
        }
        if (right < left) {
            generate(left, right + 1, max, current + ")");
        }

    }

}


/**
 * @decription: 46.全排列，返回给定数组的所有排列情况
 * @solution: 回溯法，深度遍历过程中进行决策与回溯
 *
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/permutations/
 * @date: 2021/3/9
 */
class Solution46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        helper(nums, 0, nums.length, new boolean[nums.length], new LinkedList<>(), ans);
        return ans;
    }

    private void helper(int[] nums, int depth, int length, boolean[] used, Deque<Integer> current, List<List<Integer>> ans) {
        if (depth == length) {
            ans.add(new LinkedList<>(current));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                current.addLast(nums[i]);
                used[i] = true;

                helper(nums, depth + 1, length, used, current, ans);

                // 回溯
                current.removeLast();
                used[i] = false;
            }
        }
    }
}


/**
 * @decription: 47.全排列2，返回给定数组的所有不重复排列情况
 * @solution: 回溯法，深度遍历过程中进行决策与回溯
 *
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/permutations-ii/
 * @date: 2021/3/9
 */
class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> current = new ArrayDeque<>();
        Arrays.sort(nums);
        helper(nums, 0, nums.length, new boolean[nums.length], current, ans);
        return ans;
    }

    private void helper(int[] nums, int depth, int length, boolean[] used, Deque<Integer> current, List<List<Integer>> ans) {
        if (depth == length) {
            ans.add(new LinkedList<>(current));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])) {
                continue;
            }
            current.addLast(nums[i]);
            used[i] = true;

            helper(nums, depth + 1, length, used, current, ans);

            current.removeLast();
            used[i] = false;
        }
    }
}


/**
 * @decription: 77.组合，返回1到n个数选k个的所有组合情况
 * @solution: 回溯
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/combinations/
 * @date: 2021/3/10
 */
class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        if (k <= 0 || k > n) {
            return ans;
        }
        helper(n, k, 1, ans, new ArrayDeque<>());
        return ans;
    }

    private void helper(int n, int k, int begin, List<List<Integer>> ans, Deque<Integer> current) {
        if (current.size() == k) {
            ans.add(new LinkedList<>(current));
            return;
        }

        for (int i = begin; i <= n; i++) {
            current.addLast(i);

            helper(n, k, i + 1, ans, current);

            current.removeLast();
        }
    }
}


/**
 * @decription: 169.多数元素，返回数组中出现次数大于长度一半的元素
 * @solution: 分治，比较左右局部出现最多，递归下去
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/majority-element/
 * @date: 2021/3/10
 */
class Solution169_rec {
    public int majorityElement(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        int mid = low + (high - low) / 2;
        int leftMaj = helper(nums, low, mid);
        int rightMaj = helper(nums, mid + 1, high);

        if (leftMaj == rightMaj) {
            return leftMaj;
        }

        int leftCount = count(nums, low, mid, leftMaj);
        int rightCount = count(nums, mid + 1, high, rightMaj);

        return leftCount > rightCount ? leftMaj : rightMaj;
    }

    public int count(int[] nums, int begin, int end, int target) {
        int count = 0;
        for (int i = begin; i <= end; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }
}


/**
 * @decription: 78.子集，给定数组的所有子集
 * @solution: 回溯，撤销前步操作
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/subsets/
 * @date: 2021/3/10
 */
class Solution78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(0, nums, res, new LinkedList<>());
        return res;
    }

    public void backtrack(int begin, int[] nums, List<List<Integer>> res, Deque<Integer> current) {
        res.add(new LinkedList<>(current));
        for (int i = begin; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(i + 1, nums, res, current);
            current.removeLast();
        }
    }

}


/**
 * @decription: 17.电话号码的字母组合，需要回链接看题, https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @solution: 回溯
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @date: 2021/3/10
 */
class Solution17 {

    public List<String> letterCombinations(String digits) {

        List<String> ans = new LinkedList<>();
        if (digits.length() == 0) {
            return ans;
        }

        // 构建号码字母表
        char[][] map = new char[10][4];
        char c = 'a';
        for (int i = 2; i < 10; i++) {
            map[i][0] = c++;
            map[i][1] = c++;
            map[i][2] = c++;
            if (i == 7 || i == 9) {
                map[i][3] = c++;
            }
        }

        // 构建用户输入号码串
        int[] input = new int[digits.length()];
        char[] chars = digits.toCharArray();
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(String.valueOf(chars[i]));
        }

        // process
        helper(map, input, 0, new StringBuilder(), ans);

        return ans;
    }

    private void helper(char[][] map, int[] input, int idx, StringBuilder sb, List<String> ans) {
        if (idx == input.length) {
            ans.add(sb.toString());
        } else {
            int num = input[idx];
            if (num == 7 || num == 9) {
                // 输入7与9有四种字符可能
                for (int i = 0; i < 4; i++) {
                    sb.append(map[num][i]);
                    helper(map, input, idx + 1, sb, ans);
                    // 撤销
                    sb.deleteCharAt(idx);
                }
            } else {
                // 同理
                for (int i = 0; i < 3; i++) {
                    sb.append(map[num][i]);
                    helper(map, input, idx + 1, sb, ans);
                    sb.deleteCharAt(idx);
                }
            }
        }
    }
}


/**
 * @decription: 51.N皇后，根据给定的n，在n x n棋盘中放n个皇后，皇后间不能在同一行、列、对角线
 * @solution: 回溯，利用set存储同列、同对角线的使用情况以进行排除不符合情况的状态，像棋盘下一行递归后回溯撤销上一步操作
 * @difficulty: 复杂
 * @url: https://leetcode-cn.com/problems/n-queens/
 * @date: 2021/3/10
 */
class Solution51 {

    public static void main(String[] args) {
        System.out.println(new Solution51().solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();

        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        helper(0, n, queens, new HashSet<>(), new HashSet<>(), new HashSet<>(), res);
        return res;
    }


    public void helper(int row, int n, int[] queens, HashSet<Integer> colSet,
                       HashSet<Integer> diaSet1,  HashSet<Integer> diaset2, List<List<String>> res) {
        if (row == n) {

            res.add(buildBoard(queens));
        } else {

            for (int col = 0; col < n; col++) {

                // 同列不可放
                if (colSet.contains(col)) continue;
                int dia1 = row - col;
                // 同对角线不可放
                if (diaSet1.contains(dia1)) continue;
                int dia2 = row + col;
                if (diaset2.contains(dia2)) continue;

                queens[row] = col;
                colSet.add(col);
                diaSet1.add(dia1);
                diaset2.add(dia2);

                helper(row + 1, n, queens, colSet, diaSet1, diaset2, res);

                // 回溯，撤销操作，回到"正确"状态
                queens[row] = -1;
                colSet.remove(col);
                diaSet1.remove(dia1);
                diaset2.remove(dia2);
            }
        }
    }


    public List<String> buildBoard(int[] queens) {
        List<String> res = new LinkedList<>();
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            int col = queens[i];
            if (col != -1) {
                row[col] = 'Q';
            }
            res.add(new String(row));
        }
        return res;
    }

}