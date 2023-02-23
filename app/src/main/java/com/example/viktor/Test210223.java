package com.example.viktor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test210223 {
    public static void main(String[] args) throws Exception {
        Queue<String> queue=new LinkedList<>();
        queue.offer("Первый");
        queue.offer("Второй");
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        Stack<String> stack=new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        hehe();
}
public static void hehe()throws  Exception{
        throw  new Exception("fdkd");
}
}
