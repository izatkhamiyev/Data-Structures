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
public class ArrayStack<T> implements Stack<T>{
    private T[] values;
    private int size;
   
    
    public ArrayStack(){
        values = (T[])new Object[10];
        size = 0;
    }
    public void push(T value) {
        if(size>0){
            T[] newArray= (T[])new Object[2*size]; 
            for(int i=0;i<size;i++)
            newArray[i]=values[i];
            values = newArray; 
            
        }
        values[size] = value;
        size++;
    }

    @Override
    public T pop() throws Exception {
        if(size>0){
            T result = values[size-1];
            values[size-1] = null;
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
    }
    
    public String toString(){
        
        String s="bottom[";
        if(size>0)
            s+=values[0];
        for(int i=1;i<getSize();i++)
            s+=", "+values[i];
        s+="]top";
        return s;
    }
    
}
