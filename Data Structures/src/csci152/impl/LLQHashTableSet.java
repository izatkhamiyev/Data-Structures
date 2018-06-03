/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.HashTableSet;
import csci152.adt.HashTableStats;
import csci152.adt.Queue;
import csci152.adt.Set;
import static java.lang.Math.abs;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author User
 */
public class LLQHashTableSet<T> implements HashTableSet<T>{
    private LinkedListQueue<T>[] buckets;
    private int size;
    
    public LLQHashTableSet(int k){
        buckets = new LinkedListQueue[k];
        size=0;
    }
    @Override
    public void add(T value) {
        if(buckets[abs(value.hashCode())%buckets.length]==null)
        buckets[abs(value.hashCode())%buckets.length]=new LinkedListQueue();
        if(!contains(value)){
            buckets[abs(value.hashCode())%buckets.length].enqueue(value);
            size++;
        }
    }

    @Override
    public boolean contains(T value) {
        int x = abs(value.hashCode())%buckets.length;
        boolean ok=false;
        if(buckets[x]!=null){
            for(int j=1;j<=buckets[x].getSize();j++){
                try {
                    T it=buckets[x].dequeue();
                    if(it.equals(value))
                        ok=true;
                    buckets[x].enqueue(it);
                } catch (Exception e) { 
                    System.out.println(e.getMessage());
                }
            }     
        }
        return ok;
    }

    @Override
    public boolean remove(T value) {
        int x = abs(value.hashCode())%buckets.length;
        boolean ok=false;
        if(buckets[x]!=null){
            Queue temp = new LinkedListQueue();
            while(buckets[x].getSize()!=0){
                try {
                    T it = buckets[x].dequeue();
                    if(it.equals(value)){
                        ok=true;
                        size--;
                    }
                    else
                        temp.enqueue(it);
                } catch (Exception e) {
                    System.out.println("No elements");
                }       
            }
            while(temp.getSize()!=0){
                try {
                    buckets[x].enqueue((T) temp.dequeue());
                } catch (Exception e) {
                    System.out.println("No elements");
                }
            }
        }
        return ok;
    }

    @Override
    public T removeAny() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        else{
            T result = null;
            for(int i=0;i<buckets.length;i++){
                if(buckets[i]!=null)
                    if(buckets[i].getSize()!=0){
                        result = buckets[i].dequeue();
                        break;
                    }
            }
            size--;
            return result;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        int k=buckets.length;
        buckets = new LinkedListQueue[k];
        size=0;
    }

    @Override
    public String toString() {
        String s="";
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]!=null){
                for(int j=1;j<=buckets[i].getSize();j++){
                    try {
                        T it=buckets[i].dequeue();
                        s+=it;
                        buckets[i].enqueue(it);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }                    
                }
              //  if(buckets[i].getSize()!=0)
                    //s+="\n";
            }
        }
        return s;
    }

    @Override
    public int getNumberOfBuckets() {
        return buckets.length;
    }

    @Override
    public int getBucketSize(int index) throws Exception {
        if(index<0 || index>=buckets.length)
            throw new Exception("Out of bounds");
        else{
            return buckets[index].getSize();
        }
    }

    @Override
    public double getLoadFactor() {
        return (double)size/buckets.length;
    }

    @Override
    public double getBucketSizeStandardDev() {
        double mean=0,std=0;
        for(int i=0;i<buckets.length;i++)
            mean+=buckets[i].getSize();
        mean/=buckets.length;
        for(int i=0;i<buckets.length;i++)
            std+=(mean-buckets[i].getSize())*(mean-buckets[i].getSize());
        
        return Math.sqrt(std/buckets.length);
    }

    @Override
    public String bucketsToString() {
        String s="";
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]==null)
                s+="bucket " + i + ": \n";
            else
            s+="bucket " + i+buckets[i].toString()+"\n";
        }
        return s;
    }
    
    
}
