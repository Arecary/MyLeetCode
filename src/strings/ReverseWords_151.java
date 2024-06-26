package strings;

public class ReverseWords_151 {



  public String reverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    StringBuilder word = new StringBuilder();
    String newS = s.trim(); // 去除首尾空格

    for (int i = 0; i < newS.length(); i++) {
      char cur = newS.charAt(i);
      char pre = (i == 0) ? ' ' : newS.charAt(i - 1);

      if (pre == ' ' && cur == ' ') {
        continue;
      }

      if (!word.isEmpty() && cur == ' ') {
        sb.insert(0, word.toString());
        sb.insert(0, ' '); // 在单词前面加上空格
        word = new StringBuilder();
        continue;
      }

      if (cur != ' ') {
        word.append(cur);
      }

      if (i == newS.length()- 1) {
        sb.insert(0, word.toString());
      }

    }

    return  sb.toString();
  }




  /**
   * 不使用Java内置方法实现
   * <p>
   * 1.去除首尾以及中间多余空格
   * 2.反转整个字符串
   * 3.反转各个单词
   */
  public String reverseWords1(String s) {
    // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
    // 1.去除首尾以及中间多余空格
    StringBuilder sb = removeSpace(s);
    // 2.反转整个字符串
    reverseString(sb, 0, sb.length() - 1);
    // 3.反转各个单词
    reverseEachWord(sb);
    return sb.toString();
  }

  private StringBuilder removeSpace(String s) {
    // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
    int start = 0;
    int end = s.length() - 1;
    while (s.charAt(start) == ' ') start++;
    while (s.charAt(end) == ' ') end--;
    StringBuilder sb = new StringBuilder();
    while (start <= end) {
      char c = s.charAt(start);
      if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
        sb.append(c);
      }
      start++;
    }
    // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
    return sb;
  }

  /**
   * 反转字符串指定区间[start, end]的字符
   */
  public void reverseString(StringBuilder sb, int start, int end) {
    // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
    while (start < end) {
      char temp = sb.charAt(start);
      sb.setCharAt(start, sb.charAt(end));
      sb.setCharAt(end, temp);
      start++;
      end--;
    }
    // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
  }

  private void reverseEachWord(StringBuilder sb) {
    int start = 0;
    int end = 1;
    int n = sb.length();
    while (start < n) {
      while (end < n && sb.charAt(end) != ' ') {
        end++;
      }
      reverseString(sb, start, end - 1);
      start = end + 1;
      end = start + 1;
    }
  }




}
