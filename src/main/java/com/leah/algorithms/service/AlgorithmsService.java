package com.leah.algorithms.service;

import com.leah.algorithms.datastructure.ListNode;
import com.leah.algorithms.datastructure.Node;
import com.leah.algorithms.datastructure.TreeNode;

import java.util.List;

public interface AlgorithmsService {

    //https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target);

    //https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2);

    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s);

    //https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s);

    //https://leetcode.com/problems/longest-palindromic-substring/solution/
    public String longestPalindrome(String s);

    //https://leetcode.com/problems/reverse-integer/
    public int reverse(int x);

    //https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String str);

    //https://leetcode.com/problems/regular-expression-matching/
    public boolean isMatch(String s, String p);

    //https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height);

    //https://leetcode.com/problems/roman-to-integer/
    public int romanToInt(String s);

    //https://leetcode.com/problems/longest-common-prefix/
    public String longestCommonPrefix(String[] strs);

    //https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums);

    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits);

    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n);

    //https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s);

    //https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2);

    //https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n);

    //https://leetcode.com/problems/merge-k-sorted-lists/solution/
    public ListNode mergeKLists(ListNode[] lists);

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicates(int[] nums);

    //https://leetcode.com/problems/implement-strstr/
    public int strStr(String haystack, String needle);

    //https://leetcode.com/problems/divide-two-integers/
    public int divide(int dividend, int divisor);

    //https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target);

    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public int[] searchRange(int[] nums, int target);

    //https://leetcode.com/problems/count-and-say/
    public String countAndSay(int n);

    //https://leetcode.com/problems/valid-sudoku/
    public boolean isValidSudoku(char[][] board);

    //https://leetcode.com/problems/first-missing-positive/
    public int firstMissingPositive(int[] nums);

    //https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height);

    //https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums);

    //https://leetcode.com/problems/rotate-image/
    public void rotate(int[][] matrix);

    //https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs);

    //https://leetcode.com/problems/powx-n/
    public double myPow(double x, int n);

    //https://leetcode.com/problems/maximum-subarray/submissions/
    public int maxSubArray(int[] nums);

    //https://leetcode.com/problems/find-all-duplicates-in-an-array/submissions/
    public List<Integer> findDuplicates(int[] nums);

    //https://leetcode.com/problems/spiral-matrix/
    public List<Integer> spiralOrder(int[][] matrix);

    //https://leetcode.com/problems/jump-game/
    public boolean canJump(int[] nums);

    //https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals);

    //https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n);

    //https://leetcode.com/problems/plus-one/
    public int[] plusOne(int[] digits);

    //https://leetcode.com/problems/sqrtx/
    public int mySqrt(int x);

    //https://leetcode.com/problems/climbing-stairs/
    public int climbStairs(int n);

    //https://leetcode.com/problems/set-matrix-zeroes/
    public void setZeroes(int[][] matrix);

    //https://leetcode.com/problems/sort-colors/
    public void sortColors(int[] nums);

    //https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t);

    //https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums);

    //https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word);

    //https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] heights);

    //https://leetcode.com/problems/merge-sorted-array/
    public void merge(int[] nums1, int m, int[] nums2, int n);

    //https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s);

    //https://leetcode.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root);

    public boolean isValidBST(TreeNode root);

    public boolean isSymmetric(TreeNode root);

    //102. https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root);

    //107. https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root);

    //103. https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root);

    //104. https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root);

    //105. https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    public TreeNode buildTree(int[] preorder, int[] inorder);

    //106. https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    public TreeNode buildTreeTwo(int[] inorder, int[] postorder);

    //108. https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode sortedArrayToBST(int[] nums);

    //110. https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root);

    //100. https://leetcode.com/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q);

    //124. https://leetcode.com/problems/binary-tree-maximum-path-sum/
    public int maxPathSum(TreeNode root);

    //116. https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect(Node root);

    //118. https://leetcode.com/problems/pascals-triangle/
    public List<List<Integer>> generate(int numRows);

    //121. https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices);

    //122. https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfitTwo(int[] prices);

    //127. https://leetcode.com/problems/word-ladder/
    public int ladderLength(String beginWord, String endWord, List<String> wordList);

    //128. https://leetcode.com/problems/longest-consecutive-sequence/
    public int longestConsecutive(int[] nums);

    //200. https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid);

    //130. https://leetcode.com/problems/surrounded-regions/
    public void solve(char[][] board);

    //131. https://leetcode.com/problems/palindrome-partitioning/
    public List<List<String>> partition(String s);

    //136. https://leetcode.com/problems/single-number/
    public int singleNumber(int[] nums);

    //134. https://leetcode.com/problems/gas-station/
    public int canCompleteCircuit(int[] gas, int[] cost);

    //139. https://leetcode.com/problems/word-break/
    public boolean wordBreak(String s, List<String> wordDict);

    //141. https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head);

    //148. https://leetcode.com/problems/sort-list/
    public ListNode sortList(ListNode head);

    //149. https://leetcode.com/problems/max-points-on-a-line/
    public int maxPoints(int[][] points);
}
