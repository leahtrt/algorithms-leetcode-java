package com.leah.algorithms.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleArray {
    int[] copy;
    List<Integer> list;

    public ShuffleArray(int[] nums) {
        copy = new int[nums.length];
        list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            copy[i] = nums[i];
            list.add(nums[i]);
        }
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return copy;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Collections.shuffle(list);
        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}
