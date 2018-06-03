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
public class ArrayQueue<T> implements Queue<T>{
    private T[] values;
    private int size;
    private int front;
    private int back;
    
    public ArrayQueue(){
        values = (T[])new Object[10];
        size = 0;
        front = 0;
        back = -1;
        
    }
    
    @Override
    public void enqueue(T value) {
        if(size == values.length){
            T[] newArray= (T[])new Object[2*values.length];
            for(int i=0, j=front;i<size;i++,j=(j+1)%values.length){
                newArray[i]=values[j];
            }
            values = newArray; 
            front = 0;
            back = size-1;
        }
        back = (back+1)%values.length;
        values[back] = value;
        size++;
      
       
    }
    @Override
    public T dequeue() throws Exception {
        if(size>0){
            T result = values[front];
            values[front]=null;
            front=(front+1)%values.length;
            size--;
         
            return result;
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
        values = (T[])new Object[10];
        size = 0;
        front = 0;
        back = -1;
       
    }
    public String toString(){
        String s = "front[";

        for(int i=0, j=front;i<size;i++,j=(j+1)%values.length){
            if(i==size-1)
                s+=values[j];
            else
                s+=values[j]+", ";
            
        }
        return s+"]back";
    }
}
