package com.leah.algorithms.service.impl;

import com.leah.algorithms.datastructure.ListNode;
import com.leah.algorithms.service.AlgorithmsService;
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







}
