/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.SortedQueue;

/**
 *
 * @author User
 */
public class LinkedListSortedQueue<T extends Comparable> implements SortedQueue<T>{
    private int size;
    private Node<T> front;
    private Node<T> back;
    public LinkedListSortedQueue(){
        size=0;
        front =  null;
    }
    @Override
    public void insert(T value) {
        Node<T> newNode = new Node(value);
        if(size==0){
            front = newNode;
        }
        else{
            if(value.compareTo(front.getValue())<=0){
                newNode.setLink(front);
                front=newNode;    
            }
            else{
                Node<T> n = front;
                boolean ok=false;
                while(n.getLink()!=null){
                    if(value.compareTo(n.getLink().getValue())<=0){
                        ok=true;
                        newNode.setLink(n.getLink());
                        n.setLink(newNode);
                        break;
                    }
                    n=n.getLink();
                }
                if(!ok)
                    n.setLink(newNode);
            }                
        }
        size++;
    }

    @Override
    public T dequeue() throws Exception {
    if(size>0){
            Node<T> result = front;
            if(size == 1){
               front = null;
         
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
