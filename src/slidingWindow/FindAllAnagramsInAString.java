package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * @author janeroad
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String t) {

        List<Integer> res = new ArrayList<>();

        // 记录要求的字符串所有字符count 以及 滑动窗口window中 字符与个数的映射关系
        HashMap<Character, Integer> windowMap = new HashMap<>();
        HashMap<Character, Integer> needMap = new HashMap<>();
        //把要求的字符串所有字符记下来
        for (int i = 0; i < t.length(); i++) {
            char c1 = t.charAt(i);
            needMap.put(c1, needMap.getOrDefault(c1, 0) + 1);
        }
        // 双指针
        int left = 0;
        int right = 0;
        int count = 0;

        // 用于记录window串的起始位置， 返回 s[start, len]
        int start = 0;

        while (right < s.length()) {
            //curChar 进入窗口的当前字符
            char curChar = s.charAt(right);
            //扩大窗口
            right++;
            // 如果进入窗口的字符是所需要的字符，进行窗口内的字符更新
            if (needMap.containsKey(curChar)) {
                windowMap.put(curChar, windowMap.getOrDefault(curChar, 0) + 1);
                if (windowMap.get(curChar).equals(needMap.get(curChar))) {
                    count++;
                }
            }

            // 判断左侧窗口是否要收缩 收缩window的长度
            while (count == needMap.size()) {
                // 记录result
                if (right - left == t.length()) {
                    res.add(left);
                }
                // removeChar 是将移出窗口的字符
                char removeChar = s.charAt(left);
                // 缩小窗口
                left++;

                // 如果移出窗口的字符是所需要的字符，进行窗口内的字符更新
                if (needMap.containsKey(removeChar)) {
                    if (windowMap.get(removeChar).equals(needMap.get(removeChar))) {
                        count--;
                    }
                    windowMap.put(removeChar, windowMap.get(removeChar) - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindAllAnagramsInAString f = new FindAllAnagramsInAString();
        List<Integer> anagrams = f.findAnagrams("abab", "ab");
        for (Integer anagram : anagrams) {
            System.out.println(anagram);
        }

    }
}
