/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Set;
import csci152.adt.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 * @param <T>
 */
public class LLStackSet<T> implements Set<T>{
    
    private LinkedListStack<T> container;
    private int size;
    public LLStackSet() {
        container = new LinkedListStack();
        size = 0;
    }
    
    @Override
    public void add(T value) {
        if(!contains(value)){
            container.push(value);
            size++;
        }
    }

    @Override
    public boolean contains(T value) {
        boolean ok = false;
        Stack<T> temp = new LinkedListStack();
        for(int i=1;i<=size;i++){
            T result;
            try {
                
                result = container.pop();
                if(result.equals(value)){
                    ok = true;
                }
                temp.push(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        while(temp.getSize()!=0){
            try {
                container.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(ok)
            return true;
        return false;
    }

    @Override
    public boolean remove(T value) {
        if(!contains(value))
            return false;
        Stack<T> temp = new LinkedListStack();
        for(int i=1;i<=size;i++){
            T result;
            try {
                result = container.pop();
                if(!result.equals(value))
                    temp.push(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while(temp.getSize()!=0){
            try {
                container.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        size--;
        return true;
    }
        

    @Override
    public T removeAny() throws Exception {
        T result;
        if(size == 0) throw new Exception("No elements");
        else{
            result = container.pop();
            size--;
        }
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        container = new LinkedListStack();
        size = 0;
    }
    @Override
    public String toString() {
        Stack<T> temp = new LinkedListStack();
        String s="[";
        for(int i=1;i<=size;i++){
            T result;
            try {
                result = container.pop();
                s+=result;
                if(i!=size)
                    s+=", ";
                temp.push(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while(temp.getSize()!=0){
            try {
                container.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return s+"]";
    }   
    
}
