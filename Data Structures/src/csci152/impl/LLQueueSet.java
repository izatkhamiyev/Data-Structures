/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class LLQueueSet<T> implements Set<T>{
    
    private LinkedListQueue<T> container;
    private int size;
    public LLQueueSet() {
        container = new LinkedListQueue();
        size = 0;
    }
    

    @Override
    public void add(T value) {
        if(!contains(value)){
            container.enqueue(value);
            size++;
        }
    }
    

    @Override
    public boolean contains(T value) {
        boolean ok = false;
        for(int i=1;i<=size;i++){
            T result;
            try {
                result = container.dequeue();
                if(result.equals(value)){
                    ok = true;
                }
                container.enqueue(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
        for(int i=1;i<=size;i++){
            T result;
            try {
                result = container.dequeue();
                if(!result.equals(value)){
                    container.enqueue(result);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
            result = container.dequeue();
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
        container = new LinkedListQueue();
        size = 0;
   }

    @Override
    public String toString() {
        String s="[";
        for(int i=1;i<=size;i++){
            T result;
            try {
                result = container.dequeue();
                s+=result;
                if(i!=size)
                    s+=", ";
                container.enqueue(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return s+"]";
    }   
    
}
