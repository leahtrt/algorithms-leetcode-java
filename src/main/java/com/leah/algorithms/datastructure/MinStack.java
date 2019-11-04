package com.leah.algorithms.datastructure;

import java.util.HashMap;
import java.util.Stack;

public class MinStack {
    Stack<Integer> s;
    int min;
    HashMap<Integer, Integer> map;

    /** initialize your data structure here. */
    public MinStack() {
        s = new Stack<>();
        min = Integer.MAX_VALUE;
        map = new HashMap<>();
    }

    public void push(int x) {
        min = Math.min(min, x);
        s.push(x);
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    public void pop() {
        int removed = s.pop();
        if(map.get(removed) == 1){
            map.remove(removed);
        }else{
            map.put(removed, map.get(removed) - 1);
        }
        if(removed == min){
            int minTemp = Integer.MAX_VALUE;
            for (Integer key : map.keySet()) {
                minTemp = Math.min(minTemp, key);
            }
            min = minTemp;
        }
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return min;
    }
}