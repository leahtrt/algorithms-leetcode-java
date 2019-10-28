package com.leah.algorithms.service;

import com.leah.algorithms.datastructure.ListNode;
import com.leah.algorithms.datastructure.TreeNode;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlgorithmsServiceTest {

    @Autowired
    private AlgorithmsService algorithmsService;

    @Ignore
    @Test
    public void testTwoSum(){
        assertArrayEquals(new int[]{1,2}, algorithmsService.twoSum(new int[]{3,2,4},6));
    }

    @Ignore
    @Test
    public void testAddTwoNumbers(){
        ListNode l = new ListNode(7);
        l.next = new ListNode(0);
        l.next.next = new ListNode(8);
        ListNode p1 = new ListNode(2);
        p1.next = new ListNode(4);
        p1.next.next = new ListNode(3);
        ListNode p2 = new ListNode(5);
        p2.next = new ListNode(6);
        p2.next.next = new ListNode(4);
        assertEquals(l, algorithmsService.addTwoNumbers(p1, p2));
    }

    @Ignore
    @Test
    public void testLengthOfLongestSubstring(){
        assertEquals(3, algorithmsService.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, algorithmsService.lengthOfLongestSubstring("bbbbbb"));
        assertEquals(3, algorithmsService.lengthOfLongestSubstring("pwwkew"));
    }


    @Test
    public void testIsPalindrome(){
        assertEquals(true, algorithmsService.isPalindrome("A man, a plan, a canal: Panama"));
        assertEquals(false, algorithmsService.isPalindrome("race a car"));
    }


    @Test
    public void testLongestPalindrome(){
        assertEquals("babbabbab", algorithmsService.longestPalindrome("babadbabbabbab"));
    }

    @Ignore
    @Test
    public void testReverse(){
        assertEquals(0, algorithmsService.reverse(-2147483648));
    }

    @Ignore
    @Test
    public void testMyAtoi(){
        assertEquals( 2147483647, algorithmsService.myAtoi("9223372036854775808"));
    }

    @Ignore
    @Test
    public void testIsMatch(){
        assertEquals(true,algorithmsService.isMatch("ab",".*"));
    }

    @Ignore
    @Test
    public void testMaxArea(){
        assertEquals(49, algorithmsService.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    @Ignore
    @Test
    public void testRomanToInt(){
        assertEquals(1994, algorithmsService.romanToInt("MCMXCIV"));
    }

    @Ignore
    @Test
    public void testLongestCommonPrefix(){
        assertEquals("fl", algorithmsService.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    @Ignore
    @Test
    public void test3Sum(){
        List<List<Integer>> e = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(-1);
        a.add(0);
        a.add(1);
        e.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(-1);
        b.add(-1);
        b.add(2);
        e.add(b);
        assertEquals(e, algorithmsService.threeSum(new int[]{-1,0,1,2,-1,-4,0,1,2,6,7,-3,-4}));

    }

    @Ignore
    @Test
    public void testLetterCombination(){
        List<String> e = Arrays.asList(new String[]{"ad","ae","af","bd","be","bf","cd","ce","cf"});
        assertEquals(e,algorithmsService.letterCombinations("23"));
    }

    @Ignore
    @Test
    public void testRemoveDuplicates(){
        int[] nums = new int[]{1,1,2};
        assertEquals(2, algorithmsService.removeDuplicates(nums));
    }

    @Ignore
    @Test
    public void testStrStr(){
        assertEquals(-1, algorithmsService.strStr("hel0lo","ll"));
    }

    @Ignore
    @Test
    public void testDivide(){
        assertEquals(-1073741824, algorithmsService.divide(-2147483648,2));
    }

    @Ignore
    @Test
    public void testSearch() {
        assertEquals(1, algorithmsService.search(new int[]{3,1}, 1));

    }

    @Ignore
    @Test
    public void testSearchRange(){
        assertArrayEquals(new int[]{5,5}, algorithmsService.searchRange(new int[]{5,7,7,7,7,8},8));
    }

    @Ignore
    @Test
    public void testCountAndSay(){
        assertEquals("13112221", algorithmsService.countAndSay(7));
    }

    @Ignore
    @Test
    public void testFirstMissingPositive(){
        assertEquals(2, algorithmsService.firstMissingPositive(new int[]{1,1,1,1}));
    }

    @Ignore
    @Test
    public void testTrap(){
        assertEquals(6, algorithmsService.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    @Ignore
    @Test
    public void testMyPow(){
        assertEquals(0.25000, algorithmsService.myPow(2.00000, -2), .0);
    }

    @Ignore
    @Test
    public void testMaxSubArray(){
        assertEquals(6, algorithmsService.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    @Ignore
    @Test
    public void testCanJump(){
        assertEquals(true, algorithmsService.canJump(new int[]{3,6,1,0,4}));
    }

    @Ignore
    @Test
    public void testMerge(){
        int[][] exp = new int[][]{
                {1,6},
                {8,10},
                {15,18}
        };
        int[][] interval = new int[][]{
                {1,2},
                {3,6},
                {2,3},
                {8,10},
                {15,18}
        };
        assertArrayEquals(exp, algorithmsService.merge(interval));
    }

    @Ignore
    @Test
    public void testUniquePaths(){
        assertEquals(28, algorithmsService.uniquePaths(7,3));
    }

    @Ignore
    @Test
    public void testPlusOne(){
        assertArrayEquals(new int[]{1}, algorithmsService.plusOne(new int[]{0}));
    }

    @Ignore
    @Test
    public void testMySqrt(){
        assertEquals(46339, algorithmsService.mySqrt(2147395599));
    }

    @Ignore
    @Test
    public void testClimbStairs(){
        assertEquals(5, algorithmsService.climbStairs(4));
    }

    @Ignore
    @Test
    public void testSortColor(){
        int[] nums = new int[]{1};
        algorithmsService.sortColors(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Ignore
    @Test
    public void testMinWindow(){
        assertEquals("BANC", algorithmsService.minWindow("ADOBECODEBANC", "ABC"));
    }


    @Ignore
    @Test
    public void testEvalRPN(){
        assertEquals(6, algorithmsService.evalRPN(new String[]{"4","13","5","/","+"}));
    }

    @Ignore
    @Test
    public void testTrailingZeroes(){
        assertEquals(7, algorithmsService.trailingZeroes(30));
    }

    @Ignore
    @Test
    public void testIsPrime(){
        assertEquals(false, algorithmsService.isPrime(87));
        assertEquals(true, algorithmsService.isPrime(73));
    }

    @Test
    public void testIsPalidrome(){
        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        assertEquals(true, algorithmsService.isPalindrome(head));
    }








}
