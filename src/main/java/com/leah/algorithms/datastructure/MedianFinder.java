package com.leah.algorithms.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFinder {
    private List<Integer> nums;
    private int size;

    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new ArrayList<>();
        size = 0;
    }

    public void addNum(int num) {
        nums.add(num);
        size = size + 1;
        Collections.sort(nums);
    }

    public double findMedian() {
        double median = 0;
        int index = size / 2;
        if(size > 0 && size % 2 == 1){
            median = (double)nums.get(index);
        }else{
            median = (double)(nums.get(index) + nums.get(index-1)) / 2;
        }
        return median;
    }
}
