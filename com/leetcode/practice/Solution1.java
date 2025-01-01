package com.lc.practice;

class Solution1 {

    //https://leetcode.com/problems/construct-the-rectangle/description/
    //Rule: L >= W [L,W]
   /* 1.The area of the rectangular web page you designed must equal to the given target area.
      2.The width W should not be larger than the length L, which means L >= W.
      3.The difference between length L and width W should be as small as possible.*/

    /* Input: area = 4,output [2,2]
    [1,4],[4,1],[2,2]
    Input: area = 122122
    [1,122122],[122122,1],[61061,2],[2,61061],

     */

    public static int[] constructRectangle(int area) {
        int W = (int) Math.sqrt(area); // Start from the square root of the area
        while (area % W != 0) { // Find the largest divisor of the area less than sqrt(area)
            W--;
        }
        int L = area / W; // Calculate the corresponding length
        return new int[]{L, W};
    }

    public static void main(String[] args) {
        int[] ints = constructRectangle(122122);
        System.out.println("["+ints[0]+","+ ints[1] +"]");
    }
}