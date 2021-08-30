package com.oxyzgen.androidlearning.algorithm;

import java.util.HashMap;
import java.util.Map;

/*
https://www.tuicool.com/articles/fyqQFnA
 */
public class AlgoString {

  /*
   * BF    暴力匹配
   *
   假设主串 s 的长度为 m，模式串 t 的长度为 n，先拿 t 和 s (0, n-1) 开始匹配，
   碰到不匹配的字符再拿 t 和 s (1, n) 匹配，直到匹配到 s (m-n-1, m-1) 为止
   *
这种算法在最坏的情况下需要匹配 n * (m-n) 次，也就是我们说的时间复杂度 O (n*m)。
它的劣势和优势都十分明显，劣势是时间复杂度太高了，当字符串很长的时候性能会很差，
优势是足够简单不容易出错，而实际场景中大部分时候字符串并不会很长，所以这种算法使用的地方蛮多的。
   */
  /**
   * BF算法
   * @param s 主串
   * @param t 模式串
   * @return 模式串在主串的位置，不存在返回-1
   */
  public static int bfMatch(String s, String t) {
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    int i = 0;
    int j = 0;
    while (i < sChars.length && j < tChars.length) {
      if (sChars[i] == tChars[j]) { // 如果字符匹配，往后移一位继续匹配
        i++;
        j++;
      } else { // 如果字符不匹配，则回到主串的下一个字符继续匹配
        i = i - j + 1;
        j = 0;
      }
    }
    if (j == tChars.length) {
      return i - j;
    } else {
      return -1;
    }
  }

  /*
  研究上面算法的整个过程，发掘可以优化的环节：
1. 当碰到一个不匹配的字符，如果它在模式串中根本就不存在，那模式串让后移动几次内，整个串也都匹配不上。所以，
我们可以跳过这几次匹配。
2. 主串的一个字符，能不能快速的在模式串中定位出来？如果两个对象的hash值相等，那可以认为二者相等，这就是快速匹配。
所以可以把模式串数组哈希化。借助它，来快速的判断一个字符在模式串中是否存在。
   */

  /*
  坏字符规则
  利用坏字符规则，BM 算法的最好时间复杂度可以做到 O (n/m)。例如主串是 aaabaaabaaabaaab，模式串是 aaaa。每次比对，模式串都可以直接后移四位，只需要匹配四次。
   */
  /**
   * BM匹配算法之坏字符规则
   *
   * @param s 主串
   * @param t 模式串
   * @return 模式串在主串的位置，不存在返回-1
   */
  public static int badCharMatch(String s, String t) {
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    // 拿到模式串字符和index的映射
    Map<Character, Integer> map = getCharIndexMap(tChars);
    int i = 0; // i表示主串与模式串对齐的第一个字符
    while (i <= sChars.length - tChars.length) {
      int j;
      for (j = tChars.length - 1; j >= 0; j--) { // 模式串从后往前匹配
        if (sChars[i + j] != tChars[j]) {
          break; // 坏字符对应模式串中的下标是j
        }
      }
      if (j < 0) {
        return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
      }

      // 获取模式串中坏字符的最大index，如果没有找到，则返回-1
      int badCharMaxIndex = map.getOrDefault(sChars[i + j], -1);
      // 将模式串往后移动j - badCharMaxIndex位
      i = i + (j - badCharMaxIndex);
    }
    return -1;
  }

  private static Map<Character, Integer> getCharIndexMap(char[] chars) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < chars.length; i++) {
      map.put(chars[i], i);
    }
    return map;
  }

  /*
  好后缀规则
  如果在模式串往后移的过程中连好后缀都没有匹配上的话，那整个模式串就更不可能匹配上了，所以我们可以把模式串移动到直至再次匹配上好后缀的位置。
  后移位数 = 好后缀的位置 - 模式串中的上一次出现这个好后缀的位置
    在模式串中，查找跟好后缀匹配的另一个子串；
    在好后缀的后缀子串中，查找最长的、能跟模式串前缀子串匹配的后缀子串；
   */
  /**
   * 初始化模式串的suffix数组和prefix数组
   *
   * @param chars 模式串
   */
  private static void initSuffixAndPrefix(char[] chars, int[] suffix, boolean[] prefix) {
    for (int i = 0; i < chars.length; i++) { // 初始化赋值
      suffix[i] = -1;
      prefix[i] = false;
    }

    // 从前往后遍历模式串中的所有子串
    for (int i = 0; i < chars.length - 1; i++) {
      int j = i; // j代表子串中和后缀子串对比的字符index
      int k = 1; // k代表后缀子串长度

      // 从后往前对比子串的字符和后缀子串的字符是否相等
      while (j >= 0 && chars[j] == chars[chars.length - k]) {
        suffix[k] = j; // 匹配成功，覆盖之前的suffix值
        if (j == 0) {
          prefix[k] = true; // 该子串为符合条件的前缀子串
        }
        j--; // 子串字符下标往前一位
        k++; // 后缀子串的长度加一位
      }
    }
  }

  /**
   * BM匹配算法
   *（Boyer-Moore）
   *
   * @param s 主串
   * @param t 模式串
   * @return 模式串在主串的位置，不存在返回-1
   */
  public static int bm(String s, String t) {
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    // 坏字符规则：拿到模式串字符和index的映射
    Map<Character, Integer> map = getCharIndexMap(tChars);
    // 好后缀规则：初始化suffix数组和prefix数组
    int[] suffix = new int[tChars.length];
    boolean[] prefix = new boolean[tChars.length];
    initSuffixAndPrefix(tChars, suffix, prefix);

    int i = 0; // i表示主串与模式串对齐的第一个字符
    while (i <= sChars.length - tChars.length) {
      int j; // j代表坏字符对应模式串中的下标
      for (j = tChars.length - 1; j >= 0; j--) { // 模式串从后往前匹配
        if (sChars[i + j] != tChars[j]) {
          break;
        }
      }
      if (j < 0) {
        return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
      }

      // 获取模式串中坏字符的最大index，如果没有找到，则返回-1
      int badCharMaxIndex = map.getOrDefault(sChars[i + j], -1);
      int x = j - badCharMaxIndex; // x代表坏字符规则移动的位数
      int y = 0;
      if (j < tChars.length - 1) { // 如果有好后缀的话
        y = moveByGS(j, tChars.length, suffix, prefix); // y代表好后缀规则移动的位数
      }
      i = i + Math.max(x, y);
    }
    return -1;
  }

  /**
   * 计算好后缀规则往后移动的位数
   *
   * @param j 坏字符对应的模式串中的字符下标
   * @param m 模式串的长度
   */
  private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
    int k = m - 1 - j; // k代表好后缀的长度
    if (suffix[k] != -1) { // 优先匹配好后缀
      return j - suffix[k] + 1; // 移动至前一个匹配到好后缀的位置
    }
    for (int r = j + 2; r <= m - 1; r++) {
      if (prefix[m - r] == true) { // 有可以匹配的前缀
        return r; // 移动至匹配成功的前缀的位置
      }
    }
    return m;
  }







}
