package com.lc.practice;

public class Solution3152 {


    /*

    An array is considered special if every pair of its adjacent elements contains two numbers with different parity.

    You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that subarray nums[fromi..toi] is special or not.

    Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

    Example 1:
    Input: nums = [3,4,1,2,6], queries = [[0,4]]

    Output: [false]

    Explanation:

    The subarray is [3,4,1,2,6]. 2 and 6 are both even.

    Example 2:

    Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]

    Output: [false,true]

    Explanation:

    The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
    The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
    * */
    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {

        boolean[] res = new boolean[queries.length];
        for(int i = 0; i < queries.length;i++){
            int to = queries[i][0];
            int from = queries[i][1];
            boolean isDiffParity = true;
            for (int k = to+1; k <= from; k++){
                if(nums[k] % 2 == nums[k-1] % 2 ){
                    isDiffParity = false;
                }
            }
            res[i] = isDiffParity;
        }
        return res;
    }

    public static boolean[] isArraySpecial1(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] maxReach = new int[n];
        int end = 0;

        // Step 1: Compute the maximum reachable index for each starting index
        for (int start = 0; start < n; start++) {
            // Ensure 'end' always starts from the current index or beyond
            end = Math.max(end, start);

            // Expand 'end' as long as adjacent elements have different parity
            while (end < n - 1 && nums[end] % 2 != nums[end + 1] % 2) {
                end++;
            }

            // Store the farthest index reachable from 'start'
            maxReach[start] = end;
        }

        boolean[] ans = new boolean[queries.length];

        // Step 2: Answer each query based on precomputed 'maxReach'
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int endQuery = queries[i][1];

            // Check if the query range [start, end] lies within the max reachable range
            ans[i] = endQuery <= maxReach[start];
        }

        return ans;
    }

    public static boolean[] isArraySpecial2(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        int[] prefix = new int[nums.length];
        prefix[0] = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                // new violative index found
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1];
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];

            ans[i] = prefix[end] - prefix[start] == 0;
        }

        return ans;
    }
    // 1,2,3,4,5


    public static void main(String[] args) {
        int [] nums = {3,2,1,4,3};//{4,3,1,6};//
        int[][] queries = {{0,4},{1,4}};//{{0,2},{2,3}};//
        boolean[] res = isArraySpecial2(nums,queries);
        System.out.print("[ ");
        for (boolean b:res){
            System.out.print(b+", ");
        }
        System.out.print(" ]");
    }
}
