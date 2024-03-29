package com.leah.algorithms.datastructure;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public String name;
    public String email;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public Node(String name, String email){
        this.name = name;
        this.email = email;
    }
}
