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

    //150. https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public int evalRPN(String[] tokens);

    //152. https://leetcode.com/problems/maximum-product-subarray/
    public int maxProduct(int[] nums);

    //155. https://leetcode.com/problems/min-stack/

    //160. https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB);

    //162.https://leetcode.com/problems/find-peak-element/
    public int findPeakElement(int[] nums);

    //169. https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums);

    //171. https://leetcode.com/problems/excel-sheet-column-number/
    public int titleToNumber(String s);

    //172. https://leetcode.com/problems/factorial-trailing-zeroes/
    public int trailingZeroes(int n);

    //189. https://leetcode.com/problems/rotate-array/
    public void rotate(int[] nums, int k);

    //198. https://leetcode.com/problems/house-robber/
    public int rob(int[] nums);

    //202. https://leetcode.com/problems/happy-number/
    public boolean isHappy(int n);

    //204. https://leetcode.com/problems/count-primes/
    public boolean isPrime(int n);
    public int countPrimes(int n);

    //206. https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head);

    //217. https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate(int[] nums);

    //227. https://leetcode.com/problems/basic-calculator-ii/
    public int calculate(String s);

    //230. https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(TreeNode root, int k);

    //234. https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head);

    //235. https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);

    //236. https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public TreeNode lowestCommonAncestorTwo(TreeNode root, TreeNode p, TreeNode q);

    //237.https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node);

    //203. https://leetcode.com/problems/remove-linked-list-elements/
    public ListNode removeElements(ListNode head, int val);

    //238. https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums);

    //239. https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k);

    //240. https://leetcode.com/problems/search-a-2d-matrix-ii/
    public boolean searchMatrix(int[][] matrix, int target);

    //242. https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t);

    //268. https://leetcode.com/problems/missing-number/
    public int missingNumber(int[] nums);

    //283. https://leetcode.com/problems/move-zeroes/
    public void moveZeroes(int[] nums);

    //287. https://leetcode.com/problems/find-the-duplicate-number/
    public int findDuplicate(int[] nums);

    //289. https://leetcode.com/problems/game-of-life/
    public void gameOfLife(int[][] board);

    //295. https://leetcode.com/problems/find-median-from-data-stream/

    //315. https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public List<Integer> countSmaller(int[] nums);

    //324. https://leetcode.com/problems/wiggle-sort-ii/
    public void wiggleSort(int[] nums);

    //326. https://leetcode.com/problems/power-of-three/
    public boolean isPowerOfThree(int n);

    //328. https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head);

    //344. https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s);

    //347. https://leetcode.com/problems/top-k-frequent-elements/
    public List<Integer> topKFrequent(int[] nums, int k);

    //350. https://leetcode.com/problems/intersection-of-two-arrays-ii/
    public int[] intersect(int[] nums1, int[] nums2);

    //387. https://leetcode.com/problems/first-unique-character-in-a-string/
    public int firstUniqChar(String s);

    //412. https://leetcode.com/problems/fizz-buzz/
    public List<String> fizzBuzz(int n);

    //454. https://leetcode.com/problems/4sum-ii/
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D);

    //395. https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
    public int longestSubstring(String s, int k);

    //378. https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    public int kthSmallest(int[][] matrix, int k);

    //334. https://leetcode.com/problems/increasing-triplet-subsequence/
    public boolean increasingTriplet(int[] nums);

    //300. https://leetcode.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums);

    //322. https://leetcode.com/problems/coin-change/
    public int coinChange(int[] coins, int amount);

    //279. https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n);

    //329. https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    public int longestIncreasingPath(int[][] matrix);

    //341. https://leetcode.com/problems/flatten-nested-list-iterator/

    //380. https://leetcode.com/problems/insert-delete-getrandom-o1/

    //384. https://leetcode.com/problems/shuffle-an-array/

    //32. https://leetcode.com/problems/longest-valid-parentheses/
    public int longestValidParentheses(String s);

    //142. https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head);

    //221. https://leetcode.com/problems/maximal-square/
    public int maximalSquare(char[][] matrix);

    //647. https://leetcode.com/problems/palindromic-substrings/
    public int countSubstrings(String s);

    //994. https://leetcode.com/problems/rotting-oranges/
    public int orangesRotting(int[][] grid);

    //937. https://leetcode.com/problems/reorder-data-in-log-files/
    public String[] reorderLogFiles(String[] logs);

    //210. https://leetcode.com/problems/course-schedule-ii/
    public int[] findOrder(int numCourses, int[][] prerequisites);

    //207. https://leetcode.com/problems/course-schedule/
    public boolean canFinish(int numCourses, int[][] prerequisites);

    //215. https://leetcode.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k);

    //721. https://leetcode.com/problems/accounts-merge/
    public List<List<String>> accountsMerge(List<List<String>> accounts);

    //735. https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids);
}
