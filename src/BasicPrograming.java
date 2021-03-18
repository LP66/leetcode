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
