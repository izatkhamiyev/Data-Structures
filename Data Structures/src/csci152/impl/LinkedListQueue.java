/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import com.sun.glass.ui.View;

import csci152.adt.Queue;

/**
 *
 * @author User
 */
public class LinkedListQueue<T> implements Queue<T>{
    private int size;
    private Node<T> front;
    private Node<T> back;
    public LinkedListQueue(){
        size=0;
        front =  null;
        back = null;
    }
    
    @Override
    public void enqueue(T value) {
        Node<T> n = new Node(value);
        if(back == null){
            back = n;
            front = n;
        }
        else{
            back.setLink(n);
            back = n;
        }
        size++;
    }
    @Override
    public T dequeue() throws Exception {
        if(size>0){
           Node<T> result = front;
           if(size == 1){
              front = null;
              back = null;
           }
           else{   
              front = front.getLink();
           }           
           size--;
           
           return result.getValue();
        }
        else
            throw new Exception("No elements");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        size=0;
        front = null;
        back = null;
    }
    public String toString(){
        String s = "front[";
        Node<T> n = front;
        if(size>0){
            while(n.getLink()!=null){
                s+=n.toString()+", ";
                n=n.getLink();
            }
            s+=n.toString();
        }
        return s+"]back";
    }
}
