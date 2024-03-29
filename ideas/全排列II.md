# 题目

**类型：**回溯

**难度：**中等

![image-20210829224638562](https://gitee.com/janeroad/iamge-cloud/raw/master/NoteImage/image-20210829224638562.png)







# 解题思路

此题是`46. 全排列`的进阶，序列中包含了重复的数字，要求返回不重复的全排列，依然可以选择使用搜索回溯的方法来做。



将这个问题看作有 n 个排列成一行的空格，需要从左往右依次填入题目给定的 n 个数，每个数只能使用一次。



定义递归函数 `backtrack(idx, perm) ` 表示当前排列为 `perm`，下一个待填入的位置是第 `idx `个位置（下标从 0 开始）。那么整个递归函数分为两个情况：

如果 `idx==n`，说明已经填完了 `n `个位置，找到了一个可行的解，将 `perm `放入答案数组中，递归结束。
如果`idx<n`，要考虑第`idx `个位置填哪个数。根据题目要求肯定不能填已经填过的数，因此很容易想到的一个处理手段是定义一个标记数组`vis `来标记已经填过的数，那么在填第`idx `个数的时候我们遍历题目给定的 n 个数，如果这个数没有被标记过，就尝试填入，并将其标记，继续尝试填下一个位置，即调用函数 `backtrack(idx + 1, perm)`。搜索回溯的时候要撤销该个位置填的数以及标记，并继续尝试其他没被标记过的数。

但题目解到这里并没有满足「全排列不重复」 的要求，在上述的递归函数中会生成大量重复的排列，因为对于第 `idx `的位置，如果存在重复的数字 i，每次会将重复的数字都重新填上去并继续尝试导致最后答案的重复，因此需要处理这个情况。要解决重复问题，只要设定一个规则，保证在填第 `idx `个数的时候重复数字只会被填入一次即可。对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」，即如下的判断条件：

~~~java
if (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]) {
    continue;
}
~~~





# 代码

```java
class Solution {
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }
}
```



