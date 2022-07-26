package dynamicProgramming;

/**
 * 123. 买卖股票的最佳时机 III
 */
public class BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        //最多交易2次
        int num = 2, length = prices.length;
        int[][][] dp = new int[length][num + 1][2];
        //遍历天数
        for (int i = 0; i < length; i++) {
            //遍历交易次数
            for (int k = num; k >= 1; k--) {
                // 处理 base case
                if (i - 1 == -1) {
                    //第一天 手上没有股票
                    dp[i][k][0] = 0;
                    //第一天 手上有股票
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                //今天手上没股票 = 昨天没股票       or     昨天有股票今天卖出
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                //今天手上有股票 = 昨天有股票       or     昨天没股票今天买入(交易次数k-1)
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        // 穷举了 length × num × 2 个状态，正确。
        return dp[length - 1][2][0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,3,5,0,0,3,1,4};
        BestTimeToBuyAndSellStockIII b = new BestTimeToBuyAndSellStockIII();
        System.out.println(b.maxProfit(arr));
    }
}
