package com.leah.algorithms.datastructure;

import java.util.*;

public class RandomizedSet {
    private List<Integer> nums;
    private HashMap<Integer, Integer> map;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList();
        map = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else{
            nums.add(val);
            map.put(val, nums.size() - 1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int index = map.get(val);
            int lastIndex = nums.size()- 1;
            int valLast = nums.get(lastIndex);
            map.replace(valLast, index);
            nums.set(index, valLast);
            nums.remove(lastIndex);
            map.remove(val);
            return true;
        }else{
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int n = nums.size();
        Random random = new Random();
        int index = random.nextInt(n);
        return nums.get(index);
    }
}
