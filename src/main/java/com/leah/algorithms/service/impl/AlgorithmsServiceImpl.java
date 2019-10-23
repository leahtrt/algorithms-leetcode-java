package com.leah.algorithms.service.impl;

import com.leah.algorithms.datastructure.ListNode;
import com.leah.algorithms.datastructure.Node;
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







}
