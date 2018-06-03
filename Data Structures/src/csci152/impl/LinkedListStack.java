/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Stack;

/**
 *
 * @author User
 */
public class LinkedListStack<T> implements Stack<T>{

    private Node<T> top;
    private int size;
    
    public LinkedListStack(){
        top = null;
        size = 0;
    }
    
    @Override
    public void push(T value) {
        Node<T> n = new Node(value);
        if(top==null){
            top=n;
        }
        else{
            n.setLink(top);
            top=n;
        }
        size++;
    }

    @Override
    public T pop() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        Node<T> result = top;
        top=(result.getLink());
        size--;
        return result.getValue();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        top = null;
        size=0;
    }
    
    public String toString(){
        String s="top [";
        if(size>0){
            Node<T> n = top;
           // s+=n.toString()+", ";
            while(n.getLink()!=null){
                s+=n.toString()+", ";
                n=n.getLink();
            }
            return s+n.toString()+"] bottom";
        }
        else
            return s+"] bottom";
    }
    
}
