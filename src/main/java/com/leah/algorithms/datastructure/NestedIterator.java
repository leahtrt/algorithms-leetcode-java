package com.leah.algorithms.datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {

    private Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        getNested(nestedList);
    }

    private void getNested(List<NestedInteger> nestedList){
        for(int i = 0; i < nestedList.size(); i++){
            if(nestedList.get(i).isInteger()){
                q.add(nestedList.get(i).getInteger());
            }else{
                getNested(nestedList.get(i).getList());
            }
        }
    }

    @Override
    public Integer next() {
        int next = q.peek();
        q.poll();
        return next;
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
