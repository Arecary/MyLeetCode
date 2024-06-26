package strings;


/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 */
public class RepeatedSubstringPattern_459 {

  public static void main(String[] args) {

    String input = "babbabbabbabbab";
    System.out.println(repeatedSubstringPattern(input));

  }


  static public boolean repeatedSubstringPattern(String s) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      sb.append(s.charAt(i));
      if (s.length() % (i + 1) == 0) {
        int count = s.length() / (i + 1);
        System.out.println(count);
        String temp = sb.toString();
        if (s.equals(temp.repeat(count)) && count != 1) {
          System.out.println("count is :" + count);
          return true;
        }
      }
    }
    System.out.println("11111111111111");
    return false;
  }



  // KMP算法
  // next[len]是一个字符串里的最大公共前后缀，比如abcabcabc里，next[len]就是abcabc = 6
  // len - next[len]就是abc = 3， 如果字符串能被3整除，就说明是重复的。
  // 如果整个字符串是由重复的子串构成的，那么这部分（由 next[len] 表示的部分）加上至少一个重复子串的长度应该等于整个字符串的长度。
  public boolean repeatedSubstringPattern1(String s) {
    if (s.equals("")) return false;

    int len = s.length();
    // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
    s = " " + s;
    char[] chars = s.toCharArray();
    int[] next = new int[len + 1];

    // 构造 next 数组过程，j从0开始(空格)，i从2开始
    for (int i = 2, j = 0; i <= len; i++) {
      // 匹配不成功，j回到前一位置 next 数组所对应的值
      while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
      // 匹配成功，j往后移
      if (chars[i] == chars[j + 1]) j++;
      // 更新 next 数组的值
      next[i] = j;
    }

    // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
    if (next[len] > 0 && len % (len - next[len]) == 0) {
      return true;
    }
    return false;
  }


}
