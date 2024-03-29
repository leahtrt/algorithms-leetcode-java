package com.leah.algorithms.service.impl;

import com.leah.algorithms.datastructure.ListNode;
import com.leah.algorithms.datastructure.Node;
import com.leah.algorithms.datastructure.Position;
import com.leah.algorithms.datastructure.TreeNode;
import com.leah.algorithms.service.AlgorithmsService;
import javafx.util.Pair;
import org.springframework.stereotype.Component;
import sun.nio.cs.ext.MacArabic;

import java.util.*;

@Component
public class AlgorithmsServiceImpl implements AlgorithmsService {

    @Override
    public int[] twoSum(int[] nums, int target){
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(target - nums[i], i);
        }
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i]) ){
                if(i != map.get(nums[i])){
                    ans[0] = i;
                    ans[1] = map.get(nums[i]);
                    break;
                }
            }
        }
        return ans;
    }

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode listNode = new ListNode(0);
        ListNode p1 = l1, p2 = l2, cur = listNode;
        int carry = 0;
        while (p1 != null || p2 != null){
            int sum = 0;
            if(p1 != null){
                sum = sum + p1.val;
            }
            if(p2 != null){
                sum = sum + p2.val;
            }
            sum = sum + carry;
            if(sum >= 10 ){
                carry = 1;
                cur.next = new ListNode(sum - 10);
            }else{
                carry = 0;
                cur.next = new ListNode(sum);
            }
            cur = cur.next;
            if(p1 != null){p1 = p1.next;}
            if(p2 != null){p2 = p2.next;}
        }
        if(carry > 0){
            cur.next = new ListNode(carry);
        }
        return listNode.next;
    }

    @Override
    public int lengthOfLongestSubstring(String s){
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
/*        for(int i = 0; i < s.length(); i++){
            set.clear();
            for(int j = i; j < s.length(); j++){
                if(set.contains(s.charAt(j))){
                    set.clear();
                }else{
                    set.add(s.charAt(j));
                }
                ans = Math.max(ans, set.size());
            }
        }*/
        int n = s.length();
        int i = 0, j = 0;
        while(i < n && j < n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, set.size());
            }else{
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }


    @Override
    public boolean isPalindrome(String s){
        int left = 0, right = s.length() - 1;
        while (left < right){
            if(Character.isLetter(s.charAt(left)) || Character.isDigit(s.charAt(left))){
                if(Character.isLetter(s.charAt(right)) || Character.isDigit(s.charAt(right))){
                    if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                        return false;
                    }else{
                        left++;
                        right--;
                    }
                }else{
                    right--;
                }
            }else{
                left++;
            }
        }
        return true;
    }

    @Override
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0){
            return "";
        }
        int start = 0, end = 0;
        int maxLength = end - start + 1;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for(int j = 1; j < s.length(); j++){
            for(int i = 0; i < j; i++){
                boolean isInnerPalindrome = dp[i+1][j-1] || (j - i) <= 2;
                dp[i][j] = isInnerPalindrome && (s.charAt(i) == s.charAt(j));
                if(dp[i][j] == true && (j - i + 1) > maxLength){
                    start = i;
                    end = j;
                    maxLength = end - start + 1;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    @Override
    public int reverse(int x){
        /*long res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        return (int)res == res ? (int)res : 0;*/

        long ans = 0, y = x;
        boolean isPositive = y > 0;
        List<Integer> digits = new ArrayList<>();
        if(!isPositive){
            y = y * -1;
        }
        while (y >= 10){
            int num = (int)(y % 10);
            if(digits.size() > 0 || num > 0){
                digits.add(num);
            }
            y = y / 10;
        }
        digits.add((int)y);
        for(int i = 0; i < digits.size(); i++){
            ans = ans + digits.get(i) * (long)Math.pow(10,digits.size() - i - 1);
        }
        if(ans > Math.pow(2,31) - 1){
            ans = 0;
        }
        if(!isPositive){
            ans = ans * -1;
        }
        if(ans < Integer.MIN_VALUE){
            ans = 0;
        }

        return (int)ans;
    }

    @Override
    public int myAtoi(String str){
        if(str.isEmpty()){
            return 0;
        }
        int sign = 1, i = 0;
        long base = 0;
        while (i < str.length()){
            if(str.charAt(i) == ' '){
                i++;
            }else if(str.charAt(i) == '+'){
                sign = 1;
                i++;
                break;
            }else if(str.charAt(i) == '-'){
                sign = -1;
                i++;
                break;
            }else{
                break;
            }
        }
        while (i < str.length()){
            if(!Character.isDigit(str.charAt(i))){
                break;
            }else{
                if(base * sign> Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }
                if(base * sign < Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }

                base = base * 10 + str.charAt(i) - '0';
            }
            i++;
        }

        return (int)(base * sign);
    }


    @Override
    public boolean isMatch(String s, String p){
        if(p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if(p.length() >1 && p.charAt(1) == '*'){
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1),p));
        }else{
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }

    }


    @Override
    public int maxArea(int[] height){
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right){
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, h * (right - left));
            if(height[left] > height[right]){
                right--;
            }else{
                left++;
            }
        }

        return maxArea;
    }

    public HashMap<Character, Integer> romanMap(){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        return map;
    }

    @Override
    public int romanToInt(String s){
        HashMap<Character, Integer> map = romanMap();
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            if(i == s.length() - 1 || (map.get(s.charAt(i)) >= map.get(s.charAt(i+1)))){
                ans = ans + map.get(s.charAt(i));
            }else{
                ans = ans - map.get(s.charAt(i));
            }
        }

        /*for(int i = 0; i < s.length();){
            if(s.charAt(i) == 'I'){
                if(i < s.length() - 1 && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')){
                    ans = ans + map.get(s.substring(i, i+2));
                    i = i + 2;
                }else{
                    ans = ans + map.get(s.charAt(i++)+"");
                }
            }else if(s.charAt(i) == 'X'){
                if(i < s.length() - 1 && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')){
                    ans = ans + map.get(s.substring(i, i+2));
                    i = i + 2;
                }else{
                    ans = ans + map.get(s.charAt(i++)+"");
                }
            }else if(s.charAt(i) == 'C'){
                if(i < s.length() - 1 && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')){
                    ans = ans + map.get(s.substring(i, i+2));
                    i = i + 2;
                }else{
                    ans = ans + map.get(s.charAt(i++)+"");
                }
            }else{
                ans = ans + map.get(s.charAt(i++)+"");
            }
        }*/

        return ans;
    }


    @Override
    public String longestCommonPrefix(String[] strs){

        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;


        /*if(strs == null || strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        String ans = findCommonPrefix(strs[0],strs[1]);
        for(int i = 2; i < strs.length; i++){
            ans = findCommonPrefix(ans, strs[i]);
        }
        return ans;*/
    }

    public String findCommonPrefix(String s, String t){
        String ans = "";
        for(int i = 0; i < s.length() && i < t.length();i++){
            if(s.charAt(i) == t.charAt(i)){
                ans = ans + s.charAt(i);
            }else{
                return ans;
            }
        }
        return ans;
    }

    @Override
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new LinkedList<>();
        if(nums == null || nums.length == 0){
            return ans;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(nums[i] > 0){
                break;
            }
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])){
                int target = 0 - nums[i];
                int left = i + 1, right = nums.length - 1;
                while (left < right){
                    if(nums[left] + nums[right] == target){
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[left++]);
                        item.add(nums[right--]);
                        ans.add(item);
                        while (left < right && nums[left] == nums[left - 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]){
                            right--;
                        }
                    }else if(nums[left] + nums[right] > target){
                        right--;
                    }else{
                        left++;
                    }


                }
            }

        }

        return ans;
    }

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> ansPhone = new ArrayList<>();
    public void constructCombination(String item, String digits){
        if(digits.length() == 0){
            ansPhone.add(item);
        }else{
            String digit = digits.substring(0,1);
            String letters = phone.get(digit+"");
            for(int j = 0; j < letters.length(); j++){
                constructCombination(item + letters.charAt(j), digits.substring(1));
            }
        }
    }

    @Override
    public List<String> letterCombinations(String digits){

        constructCombination("",digits);
        return ansPhone;
    }

    @Override
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        n = n + 1;
        while (n > 0){
            first = first.next;
            n--;
        }
        while (first!= null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
        /*int length = 0;
        ListNode first = head;
        while (first != null){
            first = first.next;
            length++;
        }
        length = length - n;
        first = dummy;
        while (length > 0){
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;*/
    }


    @Override
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(!stack.empty() && (validPair(stack.peek(),s.charAt(i)))){
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }
        return stack.empty();
    }

    public boolean validPair(Character s, Character t){
        if(s == '(' && t == ')'){
            return true;
        }
        if(s == '[' && t == ']'){
            return true;
        }
        if(s == '{' && t == '}'){
            return true;
        }
        return false;
    }

    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if(l1.val >= l2.val){
                cur.next = l2;
                l2 = l2.next;
            }else{
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if(l1 != null){
            cur.next = l1;
        }else{
            cur.next = l2;
        }
        return dummy.next;
    }

    @Override
    public List<String> generateParenthesis(int n){
        List<String> ans = new ArrayList<>();
        generateParenthesisDfs(ans, 0, 0, "", n);

        return ans;
    }

    public void generateParenthesisDfs(List<String> ans, int left, int right, String item, int n){
        if(item.length() == n * 2){
            ans.add(item);
            return;
        }
        if(left < n){
            generateParenthesisDfs(ans, left+1, right, item + "(", n);
        }
        if(right < n){
            generateParenthesisDfs(ans, left, right+1, item + ")", n);
        }

    }


    @Override
    public ListNode mergeKLists(ListNode[] lists){
        ListNode dummy = new ListNode(0);
        List<Integer> listNum = new ArrayList<>();
        for(int i = 0; i < lists.length; i++){
            ListNode cur = lists[i];
            while (cur != null){
                listNum.add(cur.val);
                cur = cur.next;
            }
        }
        Collections.sort(listNum);
        ListNode cur = dummy;
        for(int i = 0; i < listNum.size(); i++){
            cur.next = new ListNode(listNum.get(i));
            cur = cur.next;
        }
        return dummy.next;
    }


    @Override
    public int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int index = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                nums[index++] = nums[i];
            }
        }

        return index;
    }

    @Override
    public int strStr(String haystack, String needle){
        if(needle.isEmpty()) return 0;
        if(needle.length() > haystack.length()) return -1;

        for(int i = 0; i < haystack.length();i++){
            for(int j = 0; j < needle.length();j++){
                if(i + j < haystack.length()){
                    if(haystack.charAt(i + j) != needle.charAt(j)){
                        break;
                    }else{
                        if(j == needle.length() - 1) return i;
                    }

                }else{
                    break;
                }
            }
        }


        return -1;
    }


    @Override
    public int divide(int dividend, int divisor){
        if(divisor == 1) return dividend;
        if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
        if(divisor == -1) return -1 * dividend;
        boolean dividNeg = dividend > 0 ? false : true;
        boolean divisNeg = divisor > 0 ? false : true;
        int ans = 0;
        while (Math.abs((long)dividend) >= Math.abs((long)divisor)){
            dividend = Math.abs(dividend) - Math.abs(divisor);
            ans++;
        }
        if(dividNeg == divisNeg){
            return ans;
        }

        return ans * -1;
    }


    @Override
    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]){
                //left part is in order
                if(target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                //right part is in order
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    @Override
    public int[] searchRange(int[] nums, int target){
        int[] ans = new int[]{-1,-1};
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                int i = mid, j = mid;
                while (i >= 0){
                    if(nums[i] == target){
                        i--;
                    }else{
                        break;
                    }
                }
                while (j < nums.length){
                    if(nums[j] == target){
                        j++;
                    }else{
                        break;
                    }
                }
                ans[0] = i + 1;
                ans[1] = j - 1;
                return ans;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return ans;
    }


    @Override
    public String countAndSay(int n){
        if(n <= 0) return "";
        String ans = "1";
        while (n > 1){
            String current = "";
            for(int i = 0; i < ans.length();){
                int count = 1;
                while (i+1 < ans.length() && ans.charAt(i) == ans.charAt(i+1)){
                    count++;
                    i++;
                }
                current = current + count + ans.charAt(i);
                i++;
            }
            ans = current;
            n--;
        }
        return ans;
    }

    @Override
    public boolean isValidSudoku(char[][] board){
        for(int i = 0; i < board.length; i++){
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> col = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.' && !row.add(board[i][j])){
                    return false;
                }
                if(board[j][i] != '.' && !col.add(board[j][i])){
                    return false;
                }

                int indexRow = (i / 3) * 3 + j / 3;
                int indexCol = (i % 3) * 3 + j % 3;

                if(board[indexRow][indexCol] != '.' && !cube.add(board[indexRow][indexCol])){
                    return false;
                }
            }
        }
        return true;
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    @Override
    public int firstMissingPositive(int[] nums){
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length;){
            if(nums[i] > 0 && nums[i] < nums.length){
                if(nums[i] == i + 1){
                    i++;
                    continue;
                }else{
                    if(nums[i] != nums[nums[i] - 1]){
                        swap(nums, i, nums[i] - 1);
                        i = 0;
                    }
                    else{
                        i++;
                    }
                }
            }else{
                i++;
            }

        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums[nums.length - 1] + 1;

        /*int ans = 1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                if(nums[i] == ans){
                    ans = ans + 1;
                }
                else if(nums[i] > ans){
                    return ans;
                }
            }
        }
        return ans;*/
    }

    @Override
    public int trap(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < height.length; ){
            if(stack.isEmpty() || height[stack.peek()] >= height[i]){
                stack.add(i++);
            }else{
                int t = stack.pop();
                if(stack.isEmpty()) continue;
                ans = ans + (Math.min(height[i], height[stack.peek()]) - height[t]) * (i - stack.peek() - 1);
            }
        }
        return ans;
    }

    @Override

    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        int[] visited = new int[nums.length];
        List<Integer> item = new ArrayList<>();
        permuteDfs(ans, nums, visited, item, 0);
        return ans;
    }

    public void permuteDfs(List<List<Integer>> ans, int[] nums, int[] visited, List<Integer> item, int level){
        if(level == nums.length){
            ans.add(new ArrayList<>(item));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                item.add(nums[i]);
                permuteDfs(ans, nums, visited, item, level + 1);
                item.remove(item.size() - 1);
                visited[i] = 0;
            }
        }
    }

    public void swap(int[][] matrix, int i, int j, int newI, int newJ){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[newI][newJ];
        matrix[newI][newJ] = temp;
    }

    @Override
    public void rotate(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n - i; j++){
                swap(matrix, i, j, n - j - 1, n - i - 1);
            }
        }

        for(int i = 0; i < m / 2; i++){
            for(int j = 0; j < n; j++){
                swap(matrix, i, j, m - i - 1, j);
            }
        }
    }

    @Override
    public List<List<String>> groupAnagrams(String[] strs){
        if(strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String key = new String(charArr);
            if(map.containsKey(key)){
                List<String> value = map.get(key);
                value.add(strs[i]);
            }else{
                List<String> value = new ArrayList<>();
                value.add(strs[i]);
                map.put(key, value);
            }
        }
        return new ArrayList(map.values());
    }

    @Override
    public double myPow(double x, int n){
        if(n == 0) return 1.0;
        double half = myPow(x, n / 2);
        if(n % 2 == 0) return half * half;
        if(n > 0) return half * half * x;
        return half * half / x;
    }

    @Override
    public int maxSubArray(int[] nums){
        //DP
        if(nums == null || nums.length == 0) return 0;
        int ans = nums[0];
        int temp = nums[0];
        for(int i = 1; i < nums.length; i++){
            temp = Math.max(nums[i], temp + nums[i]);
            ans = Math.max(temp, ans);
        }

        return ans;
    }

    @Override
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList();
        for(int i = 0; i < nums.length; i++){
            nums[Math.abs(nums[i]) - 1] = nums[Math.abs(nums[i]) - 1] * -1;
            if(nums[Math.abs(nums[i]) - 1] > 0){
                ans.add(Math.abs(nums[i]));
            }
        }

        return ans;
    }

    @Override
    public List<Integer> spiralOrder(int[][] matrix){
        List<Integer> ans = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        while (true){
            for(int i = left; i < right + 1; i++){
                ans.add(matrix[up][i]);
            }
            if(++up > down) break;
            for(int i = up; i < down + 1; i++){
                ans.add(matrix[i][right]);
            }
            if(left > --right) break;
            for(int i = right; i >= left; i--){
                ans.add(matrix[down][i]);
            }
            if(up > --down) break;
            for(int i = down; i >= up; i--){
                ans.add(matrix[i][left]);
            }
            if(++left > right) break;
        }
        return ans;
    }

    @Override
    public boolean canJump(int[] nums){
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int reach = 0;
        for(int i = 0; i < n; i++){
            if(reach >= nums.length || i > reach) break;
            else{
                reach = Math.max(reach, nums[i] + i);
            }
        }
        return reach >= n - 1;
    }

    @Override
    public int[][] merge(int[][] intervals){
        if (intervals == null || intervals.length == 0) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            if(list.isEmpty() || list.get(list.size() - 1).get(1) < intervals[i][0]){
                List<Integer> item = new ArrayList<>();
                item.add(intervals[i][0]);
                item.add(intervals[i][1]);
                list.add(item);
            }
            else{
                List<Integer> item = new ArrayList<>();
                item.add(list.get(list.size() - 1).get(0));
                item.add(Math.max(intervals[i][1], list.get(list.size() - 1).get(1)));
                list.remove(list.size() - 1);
                list.add(item);
            }
        }

        int[][] ans = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            ans[i][0] = list.get(i).get(0);
            ans[i][1] = list.get(i).get(1);
        }
        return ans;
    }


    @Override
    public int uniquePaths(int m, int n){
        if(m <= 0 || n <= 0) return 0;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0 && j > 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }else if(i > 0 && j == 0){
                    dp[i][j] = dp[i-1][j];
                }else if(i == 0 && j > 0){
                    dp[i][j] = dp[i][j-1];
                }else if(i == 0 && j == 0){
                    dp[i][j] = 1;
                }
            }
        }
        return dp[m-1][n-1];
    }

    @Override
    public int[] plusOne(int[] digits){
        if(digits == null || digits.length == 0) return digits;
        int carry = 1;
        List<Integer> list = new ArrayList<>();
        for(int i = digits.length - 1; i >= 0; i--){
            int sum = digits[i] + carry;
            if(sum >= 10){
                carry = 1;
                list.add(sum - 10);
            }else{
                list.add(sum);
                carry = 0;
            }
        }
        if(carry == 1){
            list.add(1);
        }

        int[] ans = new int[list.size()];
        for(int i = list.size() - 1, j = 0; i >= 0; i--, j++){
            ans[j] = list.get(i);
        }
        return ans;
    }

    @Override
    public int mySqrt(int x){
        if(x == 1) return 1;
        int left = 1, right = x;
        while (left <= right){
            int mid = left + (right - left) / 2;
            long midX = (long) mid * mid;
            if(midX == x){
                return mid;
            }else if(midX > x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }

    @Override
    public int climbStairs(int n){
        if(n <= 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        if(n > 1){
            dp[1] = 2;
        }
        for(int i = 2; i < n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n - 1];
    }

    @Override
    public void setZeroes(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int MARKER = 10000011;
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    for(int p = 0; p < m; p++){
                        if(matrix[p][j] != 0){
                            matrix[p][j] = MARKER;
                        }
                    }
                    for(int q = 0; q < n; q++){
                        if(matrix[i][q] != 0){
                            matrix[i][q] = MARKER;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == MARKER){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void sortColors(int[] nums){
        int blue = nums.length - 1, red = 0;
        //one pass
        for(int i = 0; i <= blue; i++){
            if(nums[i] == 2){
                swap(nums, i--, blue--);
            }
            else if(nums[i] == 0){
                swap(nums, i, red++);
            }
        }
/*
        //two pass
        for(int i = 0; i < nums.length; i++){
            while (blue > -1 && nums[blue] == 2){
                blue--;
            }
            if(i < blue && nums[i] == 2){
                swap(nums, i, blue--);
            }else{
                continue;
            }
        }
        for(int i = 0; i < nums.length; i++){
            while ( red < nums.length - 1 && nums[red] == 0){
                red++;
            }
            if(i > red && nums[i] == 0){
                swap(nums, i, red++);
            }else{
                continue;
            }
        }*/
    }

    @Override
    public String minWindow(String s, String t){
        if(s.isEmpty() || t.isEmpty()) return "";
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c:s.toCharArray())map.put(c,0);
        for(int i = 0; i < t.length(); i++){
            if(map.containsKey(t.charAt(i)))
                map.put(t.charAt(i),map.get(t.charAt(i))+1);
            else
                return "";
        }
        int start  = 0, end = 0, counter = t.length(), minLen = Integer.MAX_VALUE;
        int minStart = 0;
        String res = "";
        while (end < s.length()){
            char cur  = s.charAt(end);
            if(map.get(cur) > 0) counter--;
            map.put(cur, map.get(cur) - 1);

            while (counter == 0){
                if(minLen > end - start  + 1){
                    minLen = end - start  + 1;
                    minStart = start;
                }
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if(map.get(c2) > 0) counter++;
                start ++;
            }
            end++;
        }

        return minLen==Integer.MAX_VALUE?"":s.substring(minStart, minStart+minLen);

    }

    public List<List<Integer>> ans = new ArrayList<>();

    @Override
    public List<List<Integer>> subsets(int[] nums){
   /*     Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++){
            int size = ans.size();
            for(int j = 0; j < size; j++){
                List<Integer> item = new ArrayList<>(ans.get(j));
                item.add(nums[i]);
                ans.add(item);
            }
        }

        return ans;*/
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, temp);
        return ans;
    }


    public void  dfs(int[] nums, int i, List<Integer> temp){
        if(i == nums.length){
            ans.add(new ArrayList<>(temp));
        }else{
            temp.add(nums[i]);
            dfs(nums, i+1, temp);
            temp.remove(temp.size() - 1);
            dfs(nums, i+1, temp);
        }
    }

    @Override
    public boolean exist(char[][] board, String word){
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        if(word == "") return false;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(existBfs(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    public boolean existBfs(char[][] board, String word, int count, int i, int j){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(count)){
            return false;
        }
        if(count == word.length() - 1){
            return true;
        }
        char c = board[i][j];
        board[i][j] = '#';
        boolean res = existBfs(board, word, count + 1, i+1, j) || existBfs(board, word, count + 1, i-1, j)
                || existBfs(board, word, count + 1, i, j+1) || existBfs(board, word, count + 1, i, j-1);
        board[i][j] = c;

        return res;
    }

    @Override
    public int largestRectangleArea(int[] heights){
        if(heights == null || heights.length == 0) return 0;
        int res = 0;
        //每找到一个局部峰值（只要当前的数字大于后面的一个数字，那么当前数字就看作一个局部峰值，跟前面的数字大小无关），然后向前遍历所有的值，算出共同的矩形面积，每次对比保留最大值
        for(int i = 0; i < heights.length; i++){
            if(i + 1 < heights.length && heights[i] <= heights[i+1]){
                continue;
            }
            int maxH = heights[i];
            for(int j = i; j >= 0; j--){
                maxH = Math.min(maxH, heights[j]);
                int width = i - j + 1;
                res = Math.max(res, maxH * width);
            }
        }
        return res;
    }

    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int i = m - 1, j = n - 1;
        int len = m + n;
        while(i >= 0 && j >= 0){
            if(nums1[i] >= nums2[j]){
                nums1[len - 1] = nums1[i--];
            }else{
                nums1[len - 1] = nums2[j--];
            }
            len--;
        }
        while(j >= 0){
            nums1[len - 1] = nums2[j--];
            len--;
        }
    }

    @Override
    public int numDecodings(String s){
        if(s.isEmpty()) return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        if(s.charAt(0) == '0'){
            dp[0] = 0;
        }else{
            dp[0] = 1;
        }
        for(int i = 1; i <= len; i++){
            dp[i] = (s.charAt(i - 1) == '0') ? 0 : dp[i - 1];
            if(i >= 2 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7'))){
                dp[i] = dp[i] + dp[i-2];
            }
        }
        return dp[len];
    }

    @Override
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList();
        inorderHelper(root, ans);
        return ans;
    }
    public void inorderHelper(TreeNode root, List<Integer> ans){
        if(root != null){
            if(root.left != null){
                inorderHelper(root.left, ans);
            }
            ans.add(root.val);
            if(root.right != null){
                inorderHelper(root.right, ans);
            }
        }

    }

    @Override
    public boolean isValidBST(TreeNode root){
        Long lower = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= lower){
                return false;
            }
            lower = (long)root.val;
            root = root.right;
        }
        return true;
    }

    @Override
    public boolean isSymmetric(TreeNode root){
 /*       //recursive
        return isMirror(root, root);*/
        //iterative
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null){
                continue;
            }
            if(t1 == null || t2 == null){
                return false;
            }
            if(t1.val != t2.val){
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }

        return true;
    }
    public boolean isMirror(TreeNode left, TreeNode right){
        if(left == null && right != null){
            return false;
        }
        if(left != null && right == null){
            return false;
        }
        if(left == null && right == null){
            return true;
        }
        if(left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left)){
            return true;
        }
        return false;
    }

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
        /*//iterative
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> item = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode t = q.poll();
                item.add(t.val);
                if(t.left != null){
                    q.add(t.left);
                }
                if(t.right != null){
                    q.add(t.right);
                }
            }
            ans.add(item);
        }
        return ans;*/

        //recursive
        List<List<Integer>> ans = new ArrayList<>();
        levelOrderDfs(root, 0, ans);
        return ans;
    }

    public void levelOrderDfs(TreeNode t, int level, List<List<Integer>> ans){
        if(t == null) return;
        if(level >= ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(t.val);
        levelOrderDfs(t.left, level+1, ans);
        levelOrderDfs(t.right, level+1, ans);
    }

    @Override
    public List<List<Integer>> levelOrderBottom(TreeNode root){
        //recursive
        List<List<Integer>> ans = new ArrayList<>();
        levelOrderDfs(root, 0, ans);
        Collections.reverse(ans);
        return ans;

      /*  //iterative
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> item = new ArrayList();
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode t = q.poll();
                item.add(t.val);
                if(t.left != null){
                    q.add(t.left);
                }
                if(t.right != null){
                    q.add(t.right);
                }
            }
            stack.add(item);
        }

        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }
        return ans;*/
    }

    @Override
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
  /*      //iterative
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 1;
        while(!q.isEmpty()){
            int size = q.size();
            Stack<Integer> s = new Stack<>();
            List<Integer> l = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode t = q.poll();
                if(count % 2 == 0){
                    s.push(t.val);
                }else{
                    l.add(t.val);
                }
                if(t.left != null){
                    q.add(t.left);
                }
                if(t.right != null){
                    q.add(t.right);
                }

            }
            if(count % 2 == 0){
                while(!s.isEmpty()){
                    l.add(s.peek());
                    s.pop();
                }
                ans.add(l);
            }else{
                ans.add(l);
            }
            count++;
        }
        return ans;*/

        //recursive
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        leverOrderZigzagDfs(root, 0, ans);
        return ans;
    }


    public void leverOrderZigzagDfs(TreeNode t, int level, List<List<Integer>> ans){
        if(t == null) return;
        if(level >= ans.size()){
            ans.add(new ArrayList<>());
        }
        if(level % 2 == 0){
            ans.get(level).add(t.val);
        }else{
            ans.get(level).add(0, t.val);//add to the beginning of the list
        }
        leverOrderZigzagDfs(t.left, level+1, ans);
        leverOrderZigzagDfs(t.right, level+1, ans);
    }

    @Override
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);

/*        int ans = 0;
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            ans++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode t = q.poll();
                if(t.left != null){
                    q.add(t.left);
                }
                if(t.right != null){
                    q.add(t.right);
                }
            }
        }
        return ans;*/
    }

    @Override
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight){
        if(pLeft > pRight || iLeft > iRight){
            return null;
        }

        int i = 0;
        for(i = iLeft;i <= iRight; i++){
            if(preorder[pLeft] == inorder[i]){
                break;
            }
        }
        TreeNode t = new TreeNode(preorder[pLeft]);
        t.left = buildTreeHelper(preorder, pLeft + 1, pLeft + i - iLeft, inorder, iLeft, i - 1);
        t.right = buildTreeHelper(preorder, pLeft + i - iLeft + 1, pRight, inorder, i+1, iRight);
        return t;
    }
    @Override
    public TreeNode buildTreeTwo(int[] inorder, int[] postorder) {
        return buildTreeHelperTwo(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    public TreeNode buildTreeHelperTwo(int[] inorder, int iLeft, int iRight, int[] postorder, int pLeft, int pRight){
        if(iLeft > iRight || pLeft > pRight){
            return null;
        }
        TreeNode t = new TreeNode(postorder[pRight]);
        int i = 0;
        for(i = iLeft; i <= iRight; i++){
            if(inorder[i] == postorder[pRight]){
                break;
            }
        }
        t.left = buildTreeHelperTwo(inorder, iLeft, i-1, postorder, pLeft, i-iLeft+pLeft - 1);
        t.right = buildTreeHelperTwo(inorder, i + 1, iRight, postorder,i-iLeft+pLeft, pRight - 1 );
        return t;
    }

    @Override
    public TreeNode sortedArrayToBST(int[] nums) {
        return helperBST(nums);
    }

    public TreeNode helperBST(int[] nums){
        if(nums.length == 0){
            return null;
        }
        int mid = nums.length / 2;
        TreeNode t = new TreeNode(nums[mid]);
        t.left = helperBST(Arrays.copyOfRange(nums, 0, mid));
        t.right = helperBST(Arrays.copyOfRange(nums, mid+1, nums.length));
        return t;
    }

    @Override
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int maxLeft = depth(root.left);
        int maxRight = depth(root.right);
        if(Math.abs(maxLeft - maxRight) > 1){
            return false;
        }
        else{
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int depth(TreeNode t){
        if(t == null){
            return 0;
        }
        return Math.max(depth(t.left), depth(t.right)) + 1;
    }

    @Override
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return sameTreeHelper(p, q);
    }

    public boolean sameTreeHelper(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) return sameTreeHelper(p.left, q.left) && sameTreeHelper(p.right, q.right);
        else return false;
    }


    public int res = Integer.MIN_VALUE;
    @Override
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return res;
    }

    public int maxPathSumHelper(TreeNode t){
        if(t == null) return 0;
        int left = Math.max(maxPathSumHelper(t.left), 0);
        int right = Math.max(maxPathSumHelper(t.right), 0);
        res = Math.max(res, left + right + t.val);
        return Math.max(left, right) + t.val;
    }

    @Override
    public Node connect(Node root) {
      /*  //recursive
        if(root == null) return null;
        if(root.right != null){
            root.right.next = root.next == null ? null : root.next.left;
        }
        if(root.left != null){
            root.left.next = root.right;
        }

        connect(root.left);
        connect(root.right);
        return root;*/
      //iterative
        if(root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Node n = q.poll();
                if(i < size - 1){
                    n.next = q.peek();
                }
                if(n.left != null){
                    q.add(n.left);
                }
                if(n.right != null){
                    q.add(n.right);
                }
            }
        }
        return root;
    }

    @Override
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            ans.add(new ArrayList<>());
        }
        if(numRows >= 1){
            ans.get(0).add(1);
        }
        if(numRows >= 2){
            ans.get(1).add(1);
            ans.get(1).add(1);
        }
        if(numRows >= 3){
            for(int i = 2; i < numRows; i++){
                ans.get(i).add(1);
                for(int j = 0; j < ans.get(i-1).size() - 1; j++){
                    ans.get(i).add(ans.get(i-1).get(j) + ans.get(i-1).get(j+1));
                }
                ans.get(i).add(1);
            }
        }
        return ans;
    }

    @Override
    public int maxProfit(int[] prices) {
        /*//brute force
        int max = 0;
        for(int i = 0; i < prices.length - 1; i++){
            for(int j = i + 1; j < prices.length; j++){
                if(prices[j] - prices[i] > max){
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;*/
        //one pass
        int minPrice = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }else if(prices[i] - minPrice > max){
                max = prices[i] - minPrice;
            }
        }
        return max;

    }

    @Override
    public int maxProfitTwo(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                profit = profit + prices[i] - prices[i-1];
            }
        }
        return profit;
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();
        int len = beginWord.length();
        for(int i = 0; i < wordList.size(); i++){
            for(int j = 0; j < len; j++){
                String word = wordList.get(i);
                String key = word.substring(0, j) + "*" + word.substring(j+1);
                if(map.containsKey(key)){
                    List<String> value = map.get(key);
                    value.add(word);
                    map.put(key, value);
                }else{
                    List<String> value = new ArrayList<>();
                    value.add(word);
                    map.put(key, value);
                }
            }
        }

        //Queue for BFS
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        HashMap<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while(!q.isEmpty()){
            Pair<String, Integer> p = q.poll();
            String key = p.getKey();
            int level = p.getValue();

            for(int i = 0; i < len; i++){
                String transformed = key.substring(0, i) + "*" + key.substring(i+1);
                List<String> dicList = map.getOrDefault(transformed, new ArrayList<>());
                for(int j = 0; j < dicList.size(); j++){
                    //get to the endWord
                    if(dicList.get(j).equals(endWord)){
                        return level + 1;
                    }
                    //add to queue and marked as visited
                    if(!visited.containsKey(dicList.get(j))){
                        visited.put(dicList.get(j), true);
                        q.add(new Pair(dicList.get(j), level + 1));
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int longest = 1;
        int current = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i-1]){
                if(nums[i] - nums[i-1] == 1){
                    current = current + 1;
                }else{
                    longest = Math.max(longest, current);
                    current = 1;
                }
            }

        }
        return Math.max(longest, current);
    }

    @Override
    public int numIslands(char[][] grid) {
        //DFS
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '0' || visited[i][j]){
                    continue;
                }else{
                    helperIsland(grid, i, j, visited);
                    res++;
                }
            }
        }
        return res;
    }

    public void helperIsland(char[][] grid, int i, int j, boolean[][] visited){
        int m = grid.length, n = grid[0].length;
        if(i < 0 || j < 0 || i > m - 1 || j > n - 1 || visited[i][j] || grid[i][j] == '0'){
            return;
        }
        visited[i][j] = true;
        helperIsland(grid, i + 1, j, visited);
        helperIsland(grid, i - 1, j, visited);
        helperIsland(grid, i, j - 1, visited);
        helperIsland(grid, i, j + 1, visited);
    }

    @Override
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            if(board[i][0] != 'X' && !visited[i][0]){
                helperRegion(board, i, 0, visited);
            }

            if(board[i][n-1] != 'X' && !visited[i][n-1]){
                helperRegion(board, i, n-1, visited);
            }
        }

        for(int j = 0; j < n; j++){
            if(board[0][j] != 'X' && !visited[0][j]){
                helperRegion(board, 0, j, visited);
            }

            if(board[m-1][j] != 'X' && !visited[m-1][j]){
                helperRegion(board, m-1, j, visited);
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == '$'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void helperRegion(char[][] board, int i, int j, boolean[][] visited){
        int m = board.length, n = board[0].length;
        if(i < 0 || j < 0 || i > m - 1 || j > n - 1 || visited[i][j] || board[i][j] == 'X'){
            return;
        }
        board[i][j] = '$';
        visited[i][j] = true;
        helperRegion(board, i - 1, j, visited);
        helperRegion(board, i + 1, j, visited);
        helperRegion(board, i, j - 1, visited);
        helperRegion(board, i, j + 1, visited);
    }

    @Override
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if(s.isEmpty()){
            return ans;
        }
        List<String> item = new ArrayList<>();
        helper(s, 0, ans, item);

        return ans;
    }

    public void helper(String s, int start, List<List<String>> ans, List<String> item){
        if(start == s.length()){
            ans.add(new ArrayList(item));
            return;
        }
        for(int i = start; i < s.length(); i++){
            if(!isPal(s.substring(start, i+1))){
                continue;
            }else{
                item.add(s.substring(start, i+1));
                helper(s, i+1, ans, item);
                item.remove(item.size() - 1);
            }
        }
    }

    public boolean isPal(String s){
        int left = 0, right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }

    @Override
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

       /* //Sort
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i+1]){
                i++;
            }else{
                return nums[i];
            }
        }
        return nums[nums.length - 1];*/

       /*//HashMap
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        for(int i = 0; i < nums.length; i++){
            if(count.get(nums[i]) == 1){
                return nums[i];
            }
        }
        return 0;*/

       //Bit manipulation
        int a = 0;
        for(int i = 0; i < nums.length; i++){
            a = a ^ nums[i];
        }
        return a;
    }

    @Override
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int len = gas.length;
        int currentGas = 0;
        int total = 0;
        for(int i = 0; i < len; i++){
            total = total + gas[i] - cost[i];
            currentGas = currentGas + gas[i] - cost[i];
            if(currentGas < 0){
                start = i + 1;
                currentGas = 0;
            }
        }
        if(total < 0) return -1;
        return start;
    }

    @Override
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //dp[i]表示前i个字符能否被分割
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < i; j++){
                String temp = s.substring(j, i);
                //[0,i) => [0,j) + [j,i)
                if(dp[j] && wordDict.contains(temp)){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    public boolean hasCycle(ListNode head) {
        //Hash Table
        if(head == null || head.next == null){
            return false;
        }
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while(cur != null){
            if(set.contains(cur)){
                return true;
            }else{
                set.add(cur);
                cur = cur.next;
            }
        }
        return false;

        /*//Two pointers
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || slow == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;*/
    }

    @Override
    public ListNode sortList(ListNode head) {
/*        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i = 0; i < list.size(); i++){
            cur.next = new ListNode(list.get(i));
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;*/

        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return mergeTwoLists(sortList(head), sortList(slow));
    }

    @Override
    public int maxPoints(int[][] points) {
        if(points == null || points.length == 0 || points[0].length == 0) return 0;
        int ans = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<Double,Integer> map = new HashMap<>();
            int duplicate = 1;
            for(int j = i+1; j < points.length; j++){
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                if(dx == 0 && dy == 0){
                    duplicate++;
                }else if(dy == 0){
                    map.put((double)Integer.MAX_VALUE, map.getOrDefault((double)Integer.MAX_VALUE, 0) + 1);
                }else{
                    double k = (double)dx / dy;
                    if(k == -0.0) k = 0.0;
                    map.put(k, map.getOrDefault(k, 0) + 1);
                }
            }
            ans = Math.max(ans, duplicate);
            for (Map.Entry mapElement : map.entrySet()) {
                int value = (int)mapElement.getValue() + duplicate;
                if(value > ans){
                    ans = value;
                }
            }
        }
        return ans;
    }



    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals("+")){
                int a = s.pop();
                int b = s.pop();
                int c = a + b;
                s.push(c);
            }else if(tokens[i].equals("-")){
                int a = s.pop();
                int b = s.pop();
                int c = b - a;
                s.push(c);

            }else if(tokens[i].equals("*")){
                int a = s.pop();
                int b = s.pop();
                int c = a * b;
                s.push(c);
            }else if(tokens[i].equals("/")){
                int a = s.pop();
                int b = s.pop();
                int c = b / a;
                s.push(c);
            }else{
                s.push(Integer.parseInt(tokens[i]));
            }
        }
        return s.pop();
    }

    @Override
    public int maxProduct(int[] nums) {
       /* //brute force
        if(nums == null || nums.length == 0) return 0;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            int product = 1;
            for(int j = i; j < nums.length; j++){
                product = product * nums[j];
                ans = Math.max(ans, product);
            }
        }
        return ans;*/
        //two dp array
        if(nums == null || nums.length == 0) return 0;
        int ans = nums[0];
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMin[i-1] * nums[i], dpMax[i-1] * nums[i]));
            ans = Math.max(ans, dpMax[i]);
        }
        return ans;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*//brute force
        if(headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        while(curA != null){
            curB = headB;
            while(curB != null){
                if(curA == curB){
                    return curA;
                }else{
                    curB = curB.next;
                }
            }
            curA = curA.next;
        }
        return null;*/

        //HashSet
        if(headA == null || headB == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode curA = headA;
        while(curA != null){
            set.add(curA);
            curA = curA.next;
        }
        ListNode curB = headB;
        while(curB != null){
            if(set.contains(curB)){
                return curB;
            }else{
                curB = curB.next;
            }
        }
        return null;
    }

    @Override
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return 0;
        if(nums.length == 2){
            if(nums[0] > nums[1]){
                return 0;
            }else{
                return 1;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                if(nums[i] > nums[i+1]){
                    return i;
                }
            }else if(i < nums.length - 1){
                if(nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                    return i;
                }
            }else{
                if(nums[i] > nums[i-1]){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int majorityElement(int[] nums) {
        //HashMap
        if(nums == null || nums.length == 0) return 0;
        int m = (int)Math.floor((double)(nums.length/2));
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > m){
                return entry.getKey();
            }
        }
        return 0;
    }

    @Override
    public int titleToNumber(String s) {
        int ans = 0;
        int n = s.length() - 1;
        for(int i = n; i >= 0; i--){
            ans = ans + (s.charAt(i) - 'A' + 1) * (int)Math.pow(26, n-i);
        }
        return ans;
    }

    @Override
    public int trailingZeroes(int n) {
        int ans = 0;
        while(n >= 1){
            ans = ans + n / 5;
            n = n / 5;
        }
        return ans;
    }

    @Override
    public void rotate(int[] nums, int k) {
        /*k = k % nums.length;
        int[] temp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            int index = i + k;
            if(index < nums.length){
                nums[index] = temp[i];
            }else{
                index = i + k - nums.length;
                nums[index] = temp[i];
            }
        }*/

        //reverse
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end){
        while(start <= end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Override
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                dp[i] = nums[i];
            }else if(i == 1){
                dp[i] = Math.max(nums[0], nums[1]);
            }else{
                dp[i] = Math.max(dp[i - 1], dp[i-2] + nums[i]);
            }
        }
        return dp[nums.length - 1];
    }

    @Override
    public boolean isHappy(int n) {
        if(n == 1) return true;
        HashSet<Integer> set = new HashSet<>();
        while(n != 1){
            if(set.contains(n)){
                return false;
            }else{
                set.add(n);
                int sum = 0;
                int temp = n;
                while(temp > 9){
                    sum = sum + (temp%10) * (temp%10);
                    temp = temp / 10;
                }
                sum = sum + temp * temp;
                n = sum;
            }
        }
        return true;
    }

    public boolean isPrime(int n){
        if(n == 0) return false;
        if(n == 1) return false;
        if(n == 2) return true;
        //we only need to consider factors up to √n because, if n is divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
    @Override
    public int countPrimes(int n) {
        /*int count = 0;
        for(int i = n-1; i >= 0; i--){
            if(isPrime(i)){
                count++;
            }
        }
        return count;*/

        //Sieve of Eratosthenes
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; i++){
            if(notPrime[i] == false){
                count++;
            }
            for(int j = 2; i * j < n; j++){
                notPrime[i * j] = true;
            }
        }
        return count;
    }

    @Override
    public ListNode reverseList(ListNode head) {
  /*      if(head == null) return head;
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode curDummy = dummy;
        for(int i = list.size() - 1; i >= 0; i--){
            curDummy.next = new ListNode(list.get(i));
            curDummy = curDummy.next;
        }
        curDummy.next = null;
        return dummy.next;*/

       /* //iterative
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;*/

       //recursive
        if(head == null || head.next == null) return head;
        ListNode n = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return n;
    }

    @Override
    public boolean containsDuplicate(int[] nums) {
        if(nums == null) return false;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }else{
                set.add(nums[i]);
            }
        }
        return false;
    }

    @Override
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        int num = 0, ans = 0;
        char op = '+';
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }

            if((!Character.isDigit(c) && c != ' ') || i == len - 1){
                if(op == '+'){
                    stack.push(num);
                }else if(op == '-'){
                    stack.push(-num);
                }else if(op == '*'){
                    int temp = stack.pop() * num;
                    stack.push(temp);
                }else if(op == '/'){
                    int temp = stack.pop() / num;
                    stack.push(temp);
                }
                num = 0;
                op = c;
            }
        }

        while(!stack.isEmpty()){
            ans = ans + stack.peek();
            stack.pop();
        }

        return ans;
    }

    @Override
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        List<Integer> list = new ArrayList<>();
        //inorder
        formList(list, root);
        return list.get(k-1);
    }

    //inOrder
    public void formList(List<Integer> list, TreeNode t){
        if(t == null) return;
        if(t.left != null){
            formList(list, t.left);
        }
        list.add(t.val);
        if(t.right != null){
            formList(list, t.right);
        }

    }

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        int left = 0, right = list.size() - 1;
        while(left <= right){
            if(list.get(left).equals(list.get(right))){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*//recursive
        if(root == null) return null;
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }*/

        //iterative
        TreeNode t = root;
        while(t != null){
            if(t.val > p.val && t.val > q.val){
                t = t.left;
            }else if(t.val < p.val && t.val < q.val){
                t = t.right;
            }else{
                return t;
            }
        }
        return null;
    }

    @Override
    public TreeNode lowestCommonAncestorTwo(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val){
            return root;
        }
        TreeNode left = lowestCommonAncestorTwo(root.left, p, q);
        TreeNode right = lowestCommonAncestorTwo(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }

    @Override
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    @Override
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur != null){
            ListNode next = cur.next;
            while(next != null && next.val == val){
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }

    @Override
    public int[] productExceptSelf(int[] nums) {
        /*int len = nums.length;
        int[] ans = new int[len];
        int[] left = new int[len+1];
        int[] right = new int[len+1];
        left[0] = 1;
        right[0] = 1;
        for(int i = 1; i < len + 1; i++){
            left[i] = left[i-1] * nums[i-1];
            right[i] = right[i-1] * nums[len - i];
        }

        for(int i = 0; i < len; i++){
            ans[i] = left[i] * right[len - i - 1];
        }
        return ans;*/

        //O(1) space
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;
        for(int i = 1; i < len; i++){
            ans[i] = ans[i-1] * nums[i-1];
        }
        int right = 1;
        for(int i = len - 1; i >= 0; i--){
            ans[i] = ans[i] * right;
            right = right * nums[i];
        }
        return ans;
    }

    @Override
    public int[] maxSlidingWindow(int[] nums, int k) {
 /*       if(nums == null || nums.length == 0) return nums;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= nums.length - k; i++){
            int max = Integer.MIN_VALUE;
            int count = 0;
            for(int j = i; count < k; count++){
                if(nums[j] > max){
                    max = nums[j];
                }
                j++;
            }
            list.add(max);
        }

        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;*/

        // Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
        if(k==0) return new int[0];

        LinkedList<Integer> q = new LinkedList<Integer>();

        int[] res = new int[nums.length-k+1];

        for(int i=0; i<nums.length; i++) {
            while(!q.isEmpty() && nums[i]>=nums[q.getLast()]){
                q.removeLast();
            }
            q.addLast(i);

            if(i-q.getFirst()+1 > k) {
                q.removeFirst();
            }
            if(i+1>=k) res[i-k+1] = nums[q.getFirst()];
        }

        return res;
    }

    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
/*        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            int left = 0, right = n - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(target == matrix[i][mid]){
                    return true;
                }else if(target > matrix[i][mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return false;*/

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while(x < m && y >= 0){
            if(matrix[x][y] > target){
                y--;
            }else if(matrix[x][y] < target){
                x++;
            }else if(matrix[x][y] == target){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isAnagram(String s, String t) {
/*        if(t.length() != s.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) < 0){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;*/

       /* //Sort
        if(t.length() != s.length()) return false;
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return Arrays.equals(ch1, ch2);*/

        //HashMap
        if(t.length() != s.length()) return false;
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(map[i] != 0){
                return false;
            }
        }
        return true;
    }

    public int missingNumber(int[] nums) {
        int ans = 0;
        for(int i = 0; i <= nums.length; i++){
            ans = ans + i;
        }
        for(int i = 0; i < nums.length; i++){
            ans = ans - nums[i];
        }
        return ans;
    }

    @Override
    public void moveZeroes(int[] nums) {
/*        int index = 0;
        int len = nums.length;
        while(index < len){
            if(nums[index] != 0){
                index++;
            }else{
                break;
            }
        }

        while(index < len - 1){
            for(int i = index + 1; i < len; i++){
                if(nums[i] != 0){
                    nums[index] = nums[i];
                    nums[i] = 0;
                    break;
                }
            }
            index++;
        }*/

        for(int i = 0, j = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }

    }

    @Override
    public int findDuplicate(int[] nums) {
        //HashSet
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                return nums[i];
            }else{
                set.add(nums[i]);
            }
        }
        return 0;
    }

    @Override
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] temp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int c = count(board, i+1, j) + count(board, i-1, j) + count(board, i, j+1) + count(board, i, j-1)
                        + count(board, i-1, j-1) + count(board,i-1,j+1) + count(board,i+1,j+1) + count(board,i+1,j-1);
                if(board[i][j] == 0){
                    if(c == 3){
                        temp[i][j] = 1;
                    }else{
                        temp[i][j] = 0;
                    }
                }else if(board[i][j] == 1){
                    if(c >= 2 && c <= 3){
                        temp[i][j] = 1;
                    }else{
                        temp[i][j] = 0;
                    }
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = temp[i][j];
            }
        }

    }

    public int count(int[][] board, int i, int j){
        int m = board.length, n = board[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n){
            return 0;
        }
        if(board[i][j] == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Integer> countSmaller(int[] nums) {
        //brute force
        List<Integer> counts = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return counts;
        }
        for(int i = 0; i < nums.length; i++){
            int count = 0;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] < nums[i]){
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
    }

    @Override
    public void wiggleSort(int[] nums) {
        int[] copy = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int mid = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        for(int i = 0, j = mid; i < nums.length; i++){
            nums[i] = copy[j];
            if(i + 1 < nums.length){
                nums[i + 1] = copy[j + nums.length/2];
            }
            j--;
            i++;
        }
    }

    @Override
    public boolean isPowerOfThree(int n) {
        while(n > 3){
            if(n % 3 != 0) return false;
            n = n / 3;
        }
        if(n == 3) return true;
        if(n == 1) return true;
        return false;

    }

    @Override
    public ListNode oddEvenList(ListNode head) {
/*        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode dummyCopy = dummy;
        ListNode cur = head;
        while(cur != null){
            dummyCopy.next = new ListNode(cur.val);
            dummyCopy = dummyCopy.next;
            if(cur.next != null){
                cur = cur.next.next;
            }else{
                break;
            }
        }
        cur = head.next;
        while(cur != null){
            dummyCopy.next = new ListNode(cur.val);
            dummyCopy = dummyCopy.next;
            if(cur.next != null){
                cur = cur.next.next;
            }else{
                break;
            }
        }
        return dummy.next;*/

        if(head == null) return head;
        ListNode odd = head, even = head.next, evenHead = head.next;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    @Override
    public void reverseString(char[] s) {
        int left = 0, right = s.length-1;
        while(left <= right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    @Override
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ans = new ArrayList<Integer>();
        if(nums == null || nums.length == 0 || k < 1){
            return ans;
        }
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        HashMap<Integer, List<Integer>> countR = new HashMap<>();
        int[] countArr = new int[count.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            List<Integer> list = countR.getOrDefault(value, new ArrayList<>());
            if(list.size() == 0){
                countArr[i++] = value;
            }
            list.add(key);
            countR.put(value, list);
        }

        Arrays.sort(countArr);
        for(int j = 0; j < k; j++){
            if(ans.size() == k){
                break;
            }else{
                int key = countArr[countArr.length - 1 - j];
                List<Integer> list = countR.get(key);
                for(int p = 0; p < list.size(); p++){
                    if(ans.size() == k){
                        break;
                    }else{
                        ans.add(list.get(p));
                    }
                }
            }


        }
        return ans;
    }

    @Override
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            count.put(nums1[i], count.getOrDefault(nums1[i], 0) + 1);
        }
        List<Integer> ansList = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            if(count.containsKey(nums2[i])){
                int value = count.get(nums2[i]);
                if(value > 0){
                    ansList.add(nums2[i]);
                    count.put(nums2[i], value-1);
                }
            }
        }

        int[] ans = new int[ansList.size()];
        for(int i = 0; i < ansList.size(); i++){
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    @Override
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            count[c - 'a'] = count[c - 'a'] + 1;
        }
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(count[c - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        while(n > 0){
            if(n % 3 == 0 && n % 5 == 0){
                ans.add("FizzBuzz");
            }else if(n % 3 == 0){
                ans.add("Fizz");
            }else if(n % 5 == 0){
                ans.add("Buzz");
            }else{
                ans.add("" + n);
            }
            n--;
        }
        Collections.reverse(ans);
        return ans;
    }

    @Override
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A == null || B == null || C == null || D == null){
            return 0;
        }
        int n = A.length;
        if(n == 0){
            return 0;
        }

/*        HashMap<Integer, List<List<Integer>>> mapAB = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sum = A[i] + B[j];
                List<Integer> item = new ArrayList<>();
                item.add(i);
                item.add(j);
                if(mapAB.containsKey(sum)){
                    List<List<Integer>> value = mapAB.get(sum);
                    value.add(item);
                    mapAB.put(sum, value);
                }else{
                    List<List<Integer>> value = new ArrayList<>();
                    value.add(item);
                    mapAB.put(sum, value);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sumCD = C[i] + D[j];
                if(mapAB.containsKey(-sumCD)){
                    ans = ans + mapAB.get(-sumCD).size();
                }
            }
        }*/

        //只是记录个数
        HashMap<Integer, Integer> mapAB = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sum = A[i] + B[j];
                mapAB.put(sum, mapAB.getOrDefault(sum, 0) + 1);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sumCD = C[i] + D[j];
                ans = ans + mapAB.getOrDefault(-sumCD,0);
            }
        }
        return ans;
    }

    public int longestSubstring(String s, int k) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i + k - 1; j < s.length(); j++){
                String sub = s.substring(i, j + 1);
                HashMap<Character, Integer> count = new HashMap<>();
                for(int p = 0; p < sub.length(); p++){
                    char c = sub.charAt(p);
                    count.put(c, count.getOrDefault(c, 0) + 1);
                }
                int flag = 1;
                for(Integer value : count.values()){
                    if(value < k){
                        flag = 0;
                        break;
                    }
                }
                if(flag == 1){
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    @Override
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        //max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(k+1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                pq.offer(matrix[i][j]);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return pq.peek();

    }

    @Override
    public boolean increasingTriplet(int[] nums) {
/*        if(nums == null || nums.length == 0){
            return false;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if(dp[i] >= 3){
                    return true;
                }
            }
        }
        return false;*/

        //O(n) time, O(1) space
        if(nums == null || nums.length == 0){
            return false;
        }
        int m = Integer.MAX_VALUE, n = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= m){
                m = nums[i];
            }else if(nums[i] <= n){
                n = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public int lengthOfLIS(int[] nums) {
        //O(n2)
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(dp[i], ans);
            }
        }
        return ans;
    }

    public int resCoins = Integer.MAX_VALUE;
    @Override
    public int coinChange(int[] coins, int amount) {
/*        Arrays.sort(coins);
        helperCoin(coins, coins.length - 1, amount, 0);
        return resCoins == Integer.MAX_VALUE ? -1 : resCoins;*/

        if(amount < 1){
            return 0;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        for(int i = 1; i < amount + 1; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }


    public void helperCoin(int[] coins, int start, int amount, int cur){
        if(amount < 0) return;
        if(amount == 0){
            resCoins = Math.min(resCoins, cur);
            return;
        }
        for(int i = start; i >= 0; i--){
            helperCoin(coins, i, amount - coins[i], cur + 1);
        }
    }

    @Override
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 1; i + j * j <= n; j++){
                dp[i+j*j] = Math.min(dp[i] + 1, dp[i+j*j]);
            }
        }
        return dp[n];
    }
    public int[][] direction = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    @Override
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length ==0){
            return 0;
        }
        int res = 1;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                res = Math.max(res, dfsHelper(matrix, dp, i, j));
            }
        }
        return res;
    }

    public int dfsHelper(int[][] matrix, int[][]dp, int x, int y){
        if(dp[x][y] > 0) return dp[x][y];
        int ans = 1, m = matrix.length, n = matrix[0].length;
        for(int d = 0; d < 4; d++){
            int i = x + direction[d][0];
            int j = y + direction[d][1];
            if(i < 0 || j < 0 || i >= m || j >= n || matrix[x][y] >= matrix[i][j]){
                continue;
            }
            int len = 1 + dfsHelper(matrix, dp, i, j);
            ans = Math.max(ans, len);
        }
        dp[x][y] = ans;
        return ans;
    }

    @Override
    public int longestValidParentheses(String s) {
        //stack
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.empty()){
                    stack.push(i);
                }else{
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    @Override
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null && slow != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        if(fast == null || slow == null || fast.next == null) return null;
        slow = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    @Override
    public int maximalSquare(char[][] matrix) {
        /*//brute force
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int maxLen = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    int maxLenTemp = 1;
                    boolean flag = true;
                    while(flag && i + maxLenTemp < m && j + maxLenTemp < n){
                        for(int k = i; k <= i + maxLenTemp; k++){
                            if(matrix[k][j + maxLenTemp] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        for(int k = j; k <= j + maxLenTemp; k++){
                            if(matrix[i + maxLenTemp][k] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        if(flag){
                            maxLenTemp++;
                        }
                    }

                    maxLen = Math.max(maxLen, maxLenTemp);
                }
            }
        }

        return maxLen * maxLen;*/

        //dp[][]
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int maxLen = 0;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }

    @Override
    public int countSubstrings(String s) {
      /*  //brute force
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(isPalindromic(s,i,j)){
                    ans++;
                }
            }
        }
        return ans;*/

        //expand from center
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            ans = countHelper(s, i, i, ans);
            ans = countHelper(s, i, i+1, ans);
        }
        return ans;
    }

    public int countHelper(String s, int i, int j, int ans){
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
            ans++;
        }
        return ans;
    }

    public boolean isPalindromic(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
                continue;
            }else{
                return false;
            }

        }
        return true;
    }

    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int fresh = 0, rotten = 0;
        Queue<Position> q = new ArrayDeque<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    q.add(new Position(i, j));
                    rotten++;
                }
                if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }
        if(fresh == 0) return 0;
        if(rotten == 0) return -1;
        if(rotten == m * n) return 0;

        int minutes = 1;
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            for(int i = q.size(); i > 0; i--){
                Position p = q.poll();
                for(int[] dir: direction){
                    int curX = p.x + dir[0];
                    int curY = p.y + dir[1];
                    if(isNotRottenAndNotEmpty(grid, curX, curY)){
                        fresh--;
                        rotten++;
                        grid[curX][curY] = 2;
                        q.add(new Position(curX, curY));
                    }
                    if(fresh == 0) return minutes;
                }
            }
            minutes++;
        }
        if(fresh > 0) return -1;
        return minutes;
    }

    public boolean isNotRottenAndNotEmpty(int[][] grid, int i, int j){
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            return true;
        }
        return false;
    }

    @Override
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) return logs;
        List<String> letterLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();
        for(int i = 0; i < logs.length; i++){
            int index = logs[i].indexOf(" ");
            if(logs[i].charAt(index + 1) >= '0' && logs[i].charAt(index + 1) <= '9'){
                digitLogs.add(logs[i]);
            }else{
                letterLogs.add(logs[i]);
            }
        }

        Collections.sort(letterLogs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                int index1 = s1.indexOf(" ");
                String id1 = s1.substring(0, index1);
                String content1 = s1.substring(index1 + 1);

                int index2 = s2.indexOf(" ");
                String id2 = s2.substring(0, index2);
                String content2 = s2.substring(index2 + 1);

                int v1 = content1.compareTo(content2);
                if(v1 != 0) return v1;
                int v2 = id1.compareTo(id2);
                return v2;
            }
        });

        String[] newLogs = new String[logs.length];
        for(int i = 0; i < letterLogs.size(); i++){
            newLogs[i] = letterLogs.get(i);
        }

        for(int i = 0; i < digitLogs.size(); i++){
            newLogs[i + letterLogs.size()] = digitLogs.get(i);
        }
        return newLogs;


    }

    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0){
            for(int i = 0; i < numCourses; i++){
                order[i] = i;
            }
            return order;
        }

        HashMap<Integer, HashSet<Integer>> adj = new HashMap<>();
        int[] degree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            adj.put(i, new HashSet<>());
        }
        for(int i = 0; i < prerequisites.length; i++){
            int in = prerequisites[i][0];
            int out = prerequisites[i][1];
            HashSet<Integer> set = adj.get(out);
            set.add(in);
            adj.put(out, set);
            degree[in] = degree[in] + 1;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }
        int index = 0;
        while(!q.isEmpty()){
            int n = q.poll();
            order[index++] = n;
            HashSet<Integer> edges = adj.get(n);
            for(int e : edges){
                degree[e] = degree[e] - 1;
                if(degree[e] == 0){
                    q.offer(e);
                }
            }
        }

        if(index == numCourses) return order;
        return new int[0];
    }

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return false;
        HashMap<Integer, HashSet<Integer>> adj = new HashMap<>();
        int[] degree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            adj.put(i, new HashSet<>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            int n = prerequisites[i][1];
            HashSet<Integer> edges = adj.get(n);
            edges.add(prerequisites[i][0]);
            adj.put(n, edges);
            degree[prerequisites[i][0]] = degree[prerequisites[i][0]] + 1;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int node = q.poll();
            HashSet<Integer> edges = adj.get(node);
            for(int e : edges){
                degree[e] = degree[e] - 1;
                if(degree[e] == 0){
                    q.offer(e);
                }
            }
        }
        for(int i = 0; i < degree.length; i++){
            if(degree[i] > 0){
                return false;
            }
        }

        return true;
    }

    @Override
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for(int i = 0; i < nums.length; i++){
            pq.add(nums[i]);
        }
        for(int i = 0; i < k - 1; i++){
            pq.poll();
        }
        return pq.peek();
    }

    @Override
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, HashSet<String>> adj = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        HashSet<String> nodes = new HashSet<>();
        for(int i = 0; i < accounts.size(); i++){
            List<String> account = accounts.get(i);
            String name = account.get(0);
            HashSet<String> edges = new HashSet<>();
            for(int j = 1; j < account.size(); j++){
                edges.add(account.get(j));
                nodes.add(account.get(j));
                map.put(account.get(j), name);
            }
            for(String n : edges){
                HashSet<String> temp = adj.getOrDefault(n, new HashSet<>());
                temp.addAll(edges);
                adj.put(n, temp);
            }
        }

        HashMap<String, Boolean> visited = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for(String n : nodes){
            if(visited.getOrDefault(n, false) == false){
                visited.put(n, true);

                List<String> item = new ArrayList<>();

                Stack<String> stack = new Stack<>();
                stack.push(n);
                while(!stack.empty()){
                    String s = stack.pop();
                    item.add(s);
                    HashSet<String> edges = adj.get(s);
                    for(String e : edges){
                        if(visited.getOrDefault(e, false) == false){
                            visited.put(e, true);
                            stack.push(e);
                        }
                    }
                }

                Collections.sort(item);
                item.add(0, map.get(n));//name
                ans.add(item);
            }
        }
        return ans;
    }

    @Override
    public int[] asteroidCollision(int[] asteroids) {
        if(asteroids == null || asteroids.length == 0) return asteroids;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < asteroids.length; i++){
            int flag = 0;
            while(!stack.empty() && asteroids[i] < 0 && stack.peek() > 0){
                int pre = stack.peek();
                if(Math.abs(pre) == Math.abs(asteroids[i])){
                    stack.pop();
                    flag = 1;
                    break;
                }else if(Math.abs(pre) < Math.abs(asteroids[i])){
                    stack.pop();
                    continue;
                }else{
                    flag = 1;
                    break;
                }
                //break;
            }
            if(flag == 1) continue;
            stack.push(asteroids[i]);
        }

        int[] ans = new int[stack.size()];
        int index = stack.size() - 1;
        while(!stack.empty()){
            int n = stack.peek();
            ans[index--] = n;
            stack.pop();
        }
        return ans;
    }

    @Override
    public int[][] kClosest(int[][] points, int K) {
        if(points == null || points.length == 0 || points[0].length == 0) return points;
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue()-b.getValue());
        int[][] ans = new int[K][2];
        HashMap<Integer, Integer> items = new HashMap<>();
        for(int i = 0; i < points.length; i++){
            HashMap<Integer, Integer> item = new HashMap<>();
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            items.put(i, distance);

        }
        for(Map.Entry<Integer, Integer> entry : items.entrySet()){
            pq.offer(entry);
        }
        for(int i = 0; i < K; i++){
            int index = pq.poll().getKey();
            ans[i][0] = points[index][0];
            ans[i][1] = points[index][1];
        }
        return ans;
    }

    @Override
    public List<Integer> findAnagrams(String s, String p) {
       /* if(s.isEmpty() || p.isEmpty()) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int len = p.length();
        int[] count = new int[26];
        for(int i = 0; i < len; i++){
            count[p.charAt(i) - 'a'] =  count[p.charAt(i) - 'a'] + 1;
        }
        for(int i = 0; i <= s.length() - len; i++){
            int[] temp = Arrays.copyOf(count, 26);
            int success = 1;
            for(int j = i; j < i + len; j++){
                temp[s.charAt(j) - 'a'] = temp[s.charAt(j) - 'a'] - 1;
                if(temp[s.charAt(j) - 'a'] < 0){
                    success = 0;
                    break;
                }
            }
            if(success == 1){
                ans.add(i);
            }
        }
        return ans;*/

       //sliding window
        if(s.isEmpty() || p.isEmpty()) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int len = p.length();
        int[] count = new int[26];
        for(int i = 0; i < len; i++){
            count[p.charAt(i) - 'a'] =  count[p.charAt(i) - 'a'] + 1;
        }
        int left = 0, right = 0, cur = 0;
        while(right < s.length()){
            if(--count[s.charAt(right++) - 'a'] >= 0) cur++;
            if(cur == len) ans.add(left);
            if(right - left == len && count[s.charAt(left++) - 'a']++ >= 0) cur--;
        }
        return ans;
    }

    @Override
    public int maxSum(int[] nums){

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int key = computeDigitsSum(nums[i]);
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(nums[i]);
            Collections.sort(list);
            map.put(key, list);
        }

        int max = Integer.MIN_VALUE;
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            List<Integer> list = entry.getValue();
            int lastIndex = list.size() - 1;
            max = Math.max(max, list.get(lastIndex) + list.get(lastIndex-1));
        }

        return max;
    }

    public int computeDigitsSum(int a){
        a = Math.abs(a);
        int sum = 0;
        while(a > 10){
            sum = sum + a % 10;
            a = a / 10;
        }
        sum = sum + a;
        return sum;
    }

    public boolean IsBalancedString(String s) {
        if(s.isEmpty()) return true;
        HashMap<Character, Integer> count = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        }
        int countAC = count.getOrDefault('a', 0) + count.getOrDefault('c', 0);
        int countBD = count.getOrDefault('b', 0) + count.getOrDefault('d', 0);
        if((countAC % 2 == 0) && (countBD % 2 == 0)) return true;
        return false;
    }

    public HashMap<Integer, HashSet<Integer>> findEmployees(List<List<Integer>> data){
        HashMap<Integer,HashSet<Integer>> adj = new HashMap<>();
        HashSet<Integer> nodes = new HashSet<>();
        for(int i = 0; i < data.size(); i++){
            nodes.add(data.get(i).get(0));
            nodes.add(data.get(i).get(1));
            HashSet<Integer> edges = adj.getOrDefault(data.get(i).get(1), new HashSet<>());
            edges.add(data.get(i).get(0));
            adj.put(data.get(i).get(1), edges);
        }
        HashMap<Integer, HashSet<Integer>> ans = new HashMap<>();
        for(int node : nodes){
            HashSet<Integer> employees = adj.getOrDefault(node, new HashSet<>());
            if(employees.size() > 0){
                HashMap<Integer, Boolean> visited = new HashMap<>();
                Queue<Integer> q = new ArrayDeque<>();
                for(int e : employees){
                    q.offer(e);
                }
                while(!q.isEmpty()){
                    int e = q.poll();
                    if(!visited.getOrDefault(e, false)){
                        employees.add(e);
                        visited.put(e, true);
                        HashSet<Integer> subE = adj.getOrDefault(e, new HashSet<>());
                        if(subE.size() > 0){
                            for(int e2 : subE){
                                q.offer(e2);
                            }
                        }
                    }
                }
            }
            ans.put(node, employees);
        }
        
        return ans;
    }
}
