package com.lc.practice;

class Solution2 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150

    /*
    You are given an array prices where prices[i] is the price of a given stock on the ith day.
    You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0
    * */

    /*Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.*/
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for(int i =  0; i < prices.length; i++){
            int b = prices[i];
            for (int j = i+1; j < prices.length; j++ ){
                if(prices[j] > b ){
                    profit = profit > prices[j] - b ? profit :  prices[j] - b;
                }
            }
        }
        return profit;
    }

    public  static int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // Initialize to a very large value
        int maxProfit = 0; // Initialize maxProfit to 0

        for (int price : prices) {
            // Update the minimum price seen so far
            minPrice = Math.min(minPrice, price);
            // Calculate the potential profit if selling at the current price
            int profit = price - minPrice;
            // Update the maximum profit
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
    public static int maxProfitII(int[] prices) {
        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // If there is an upward slope, add the profit
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
                System.out.println("total "+ totalProfit);
            }
        }
        return totalProfit;
    }


    public static void main(String[] args) {
        System.out.println(maxProfitII(new int[]{7,1,5,3,6,4}));
    }
}
