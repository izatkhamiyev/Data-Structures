/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Deque;

/**
 *
 * @author User
 */
public class LinkedListDeque<T> implements Deque<T>{
    
    private DoublyLinkedNode<T> front;
    private DoublyLinkedNode<T> back;
    private int size;

    public LinkedListDeque() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public void pushToFront(T value) {
        DoublyLinkedNode<T> n = new DoublyLinkedNode(value);
        if(size == 0){
            front = n;
            back = n;
        }
        else{
            n.setNext(front);
            front.setPrevious(n);
            front = n;
        }
        size++;
    }

    @Override
    public void pushToBack(T value) {
        DoublyLinkedNode<T> n = new DoublyLinkedNode(value);
        if(size == 0){
            front = n;
            back = n;
        }
        else{
            n.setPrevious(back);
            back.setNext(n);
            back = n;
        }
        size++;
    }

    @Override
    public T popFromFront() throws Exception {
        DoublyLinkedNode<T> result;
        if(size > 1){
           result = front;
           front.getNext().setPrevious(null);
           front = front.getNext();
           size--;
           return result.getValue();
        }
        if(size == 1){
            result = front;
            front = null;
            back = null;
            size--;
            return result.getValue();
        }
        throw new Exception("No elements");
        
    }

    @Override
    public T popFromBack() throws Exception {
        DoublyLinkedNode<T> result;
        if(size > 1){
           result = back;
           back.getPrevious().setNext(null);
           back = back.getPrevious();
           size--;
           return result.getValue();
        }
        if(size == 1){
            result = front;
            front = null;
            back = null;
            size--;
            return result.getValue();
        }
        throw new Exception("No elements");    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }
    
    public String toString(){
        String s = "front[";
        DoublyLinkedNode<T> n = front;
        if(size>0){
            while(n.getNext()!=null){
                s+=n.getValue()+", ";
                n = n.getNext();
            }
                s+=n.getValue();
        }
        s+="]back";
        return s;
    }
}
