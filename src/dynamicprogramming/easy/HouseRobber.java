package dynamicprogramming.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/10/7 19:30
 *
 * 198. House Robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {

    public int rob_review20200208(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // dp(i): max amount of money given first i nums
        // dp(i) = max(dp(i-2)+nums[i], dp(i-1))
        int len = nums.length;
        int[] dp = new int[len+2];
        for(int i = 2; i < len+2; i++) {
            int n = nums[i-2];
            dp[i] = Math.max(dp[i-2]+n, dp[i-1]);
        }
        return dp[len+1];
    }

    /**
     * f(n): max of rob n;
     * g(n): max of not rob n;
     * f(n) = g(x)+nums[i]
     * g(n) = max(f(n), g(n))
     */
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int robN = nums[0];
        int notRobN = 0;
        for (int i = 1; i < nums.length; i++) {
            int tmpRobN = notRobN + nums[i];
            int tmpNotRobN = Math.max(robN, notRobN);
            robN = tmpRobN;
            notRobN = tmpNotRobN;
        }
        return Math.max(robN, notRobN);
    }

    @Test
    public void case1() {
        assertEquals(4, rob(new int[]{1,2,3,1}));
    }

    @Test
    public void case2() {
        assertEquals(12, rob(new int[]{2,7,9,3,1}));
    }

}
