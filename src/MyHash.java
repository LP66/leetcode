import java.util.*;

/**
 * @description: 利用HashMap解决的问题
 * @author: 吕立屏
 */
public class MyHash {

}


/**
 * @decription: 1.两数之和
 * @solution: 利用HashMap存储数组每一个元素的值作为entry.key键，数组元素索引作为entry.value内容
 * 如：Entry<key, value> thisV = <nums[i], i>
 * 将target减去nums[i]得到mate（匹配值mate + 当前值nums[i] = 目标值target）
 * 同时利用HashMap快速索引key的特征查询是否存入过匹配值
 * 若有则返回两者索引
 * @difficulty: 简单, O(n), 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1)的时间。
 * @url: https://leetcode-cn.com/problems/two-sum/
 * @date: 2020/5/5
 */
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int mate;   // 匹配值
        for (int i = 0; i < nums.length; i++) {
            mate = target - nums[i];    // 匹配值 + 当前值 = 目标值
            if (map.containsKey(mate)) {
                return new int[]{map.get(mate), i};
            }
            map.put(nums[i], i);    // 有机会成为未来的mate
        }
        throw new IllegalArgumentException("no sum equals target");
    }
}


/**
 * @decription: 242.有效的字母异位词
 * @solution: 存储每个字母出现次数，s+，t-，存在非0则说明不是字母异位词
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/valid-anagram/
 * @date: 2020/5/17
 */
class Solution242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] cc = new int[26]; // 26个字母26个家
        for (int i = 0; i < s.length(); i++) {
            cc[s.charAt(i) - 'a']++;
            cc[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (cc[i] != 0) {
                return false;
            }
        }
        return true;
    }
}


/**
 * @decription: 49.字母异位词分组
 * @solution: 因为异位词字符串排序后生成string是相同的
 *            所以利用这个string作为key来存储到HashMap下的数组
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/group-anagrams/
 * @date: 2020/5/18
 */
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> res = new HashMap<>();
        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String key = String.valueOf(cs);
            if (!res.containsKey(key)) res.put(key, new ArrayList());
            res.get(key).add(s);
        }
        return new ArrayList(res.values());
    }
}


/**
 * @decription: 141.环形链表，判断传入的链表是否有环
 * @solution: 利用HashSet存入遍历过的节点，遍历时遇到重复则返回true，遍历结束返回false
 * @difficulty: 简单，O(n), 不推荐这种解法，可查看快慢指针的解法
 * @url: https://leetcode-cn.com/problems/linked-list-cycle/
 * @date: 2020/5/5
 */
class Solution141_set {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> hasSeen = new HashSet<>();
        while (head != null) {
            if (hasSeen.contains(head)) {
                return true;
            }
            hasSeen.add(head);
            head = head.next;
        }
        return false;
    }
}


/**
 * @decription: 136.只出现一次的数字，返回给定数组中只出现一次的一个数字
 * @solution: 利用两个访问时间O(1)的Set，先假定所有数字只出现一次，第一次遍历全部加到结果集中
 *            第二次遍历用另一个set逐个add，false说明set中已存在此元素，此元素是重复出现的，则可以将结果集中对应的数去除
 *            这里如果其他数只出现两次则只需要一个set，解法见Solution126_set2
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/single-number/
 * @date: 2021/3/12
 */
class Solution136_set {

    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Set<Integer> resSet = new HashSet<>();
        for (int i : nums) {
            resSet.add(i);
        }
        Set<Integer> temp = new HashSet<>();
        for (int i : nums) {
            if (!temp.add(i)) {
                resSet.remove(i);
            }
        }
        return (int) resSet.toArray()[0];
    }
}


/**
 * @decription: 136.只出现一次的数字，返回给定数组中只出现一次的一个数字
 * @solution: 利用两个访问时间O(1)的Set，先假定所有数字只出现一次，第一次遍历全部加到结果集中
 *            第二次遍历用另一个set逐个add，false说明set中已存在此元素，此元素是重复出现的，则可以将结果集中对应的数去除
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/single-number/
 * @date: 2021/3/12
 */
class Solution136_set2 {

    public int singleNumber(int[] nums) {
        Set<Integer> resSet = new HashSet<>();
        for (int i : nums) {
            if (!resSet.add(i)) {
                resSet.remove(i);
            }
        }
        return (int) resSet.toArray()[0];
    }
}


/**
 * @decription: 3.无重复字符的最长子串
 * @solution: 设置双指针为子串首尾，将当前子串用hashset保存每一个字符
 *            移动右指针并检查hashset，重复则右移作指针重新开始一个字串的遍历
 *            相当于暴力+hashset
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @date: 2021/3/15
 */
class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("abcd"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        char[] chars = s.toCharArray();
        Set<Character> currentSub = new HashSet<>();
        currentSub.add(chars[0]);
        int max = 1;
        for (int l = 0, r = 1; r < chars.length; ) {
            char c = chars[r];
            if (!currentSub.contains(c)) {
                currentSub.add(c);
                r++;
            } else {
                l++;
                currentSub = new HashSet<>();
                currentSub.add(chars[l]);
                r = l + 1;
            }
            max = Math.max(r - l, max);
        }
        return max;
    }
}


/**
 * @decription: 692.前k个高频单词，输出顺序按频次多，频次相同则按字母小
 * @solution: 遍历单词组，利用hashMap为每个单词计数
 *            将计数装载到List中，利用api排序，自定义排序规则
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/top-k-frequent-words/
 * @date: 2021/3/15
 */
class Solution692 {

    public static void main(String[] args) {
        System.out.println(new Solution692().topKFrequent(
                new String[] {
                        "i", "love", "leetcode", "i", "love", "coding"
                }, 2
        ));
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String current : words) {
            count.put(current, count.getOrDefault(current, 0) + 1);
        }
        List<String> candidates = new LinkedList<>(count.keySet());
        candidates.sort(new Comparator<String>() {
            @Override
            // 从小到大 o1 then o2
            // 从大到小 o2 then o1
            public int compare(String o1, String o2) {
                int count1 = count.get(o1);
                int count2 = count.get(o2);
                // 若计数相同则按字符串从小到大排序，否则按计数从大到小排序
                return count1 == count2 ? o1.compareTo(o2) : Integer.compare(count2, count1);
            }
        });
        return candidates.subList(0, k);
    }
}