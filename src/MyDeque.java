import java.util.Stack;

/**
 * @description: 利用栈及队列的特性解决的问题
 * @author: 吕立屏
 */
public class MyDeque {

}


/**
 * @decription: 20.有效的括号
 * @solution: 左括号时入栈相反括号，右括号时直接 == 匹配
 *            右括号时栈空时必false，不匹配也false
 * @difficulty: 简单, o(n)
 * @url: https://leetcode-cn.com/problems/valid-parentheses/
 * @date: 2020/5/7
 */
class Solution20_stack {

    public boolean isValid(String s) {
        Stack<Character> cs = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') cs.push(')');
            else if (c == '{') cs.push('}');
            else if (c == '[') cs.push(']');
            else if (cs.isEmpty() || cs.pop() != c) return false;
        }
        return cs.isEmpty();
    }
}