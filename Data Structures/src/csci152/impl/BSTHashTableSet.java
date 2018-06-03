/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.HashTableSet;
import csci152.adt.HashTableStats;
import csci152.adt.Set;
import static java.lang.Math.abs;

/**
 *
 * @author User
 */
public class BSTHashTableSet<T extends Comparable> implements HashTableSet<T>{
    private BSTSet<T>[] buckets;
    private int size;
    public BSTHashTableSet(int k){
        buckets = new BSTSet[k];
        size=0;
    }
    @Override
    public void add(T value) {
        if(buckets[abs(value.hashCode())%buckets.length]==null)
            buckets[abs(value.hashCode())%buckets.length] = new BSTSet();
        int x=buckets[abs(value.hashCode())%buckets.length].getSize();
        buckets[abs(value.hashCode())%buckets.length].add(value);
        if(x<buckets[abs(value.hashCode())%buckets.length].getSize())
            size++;
        
    }

    @Override
    public boolean contains(T value) {
        int x = abs(value.hashCode())%buckets.length;
        if(buckets[x]!=null)
            return buckets[x].contains(value);
        return false;
    }

    @Override
    public boolean remove(T value) {
        int x = abs(value.hashCode())%buckets.length;
        if(buckets[x]!=null)
            if(buckets[x].remove(value)){
                size--;
                return true;
            }
        return false;
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
                        result = buckets[i].removeAny();
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
        buckets = new BSTSet[k];
        size=0;
    }

    @Override
    public String toString() {
        String s="";
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]!=null)
                if(buckets[i].getSize()!=0)
                    s+=buckets[i].toString()+"\n";
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
            if(buckets[i]!=null)
                mean+=buckets[i].getSize();
        mean/=buckets.length;
        for(int i=0;i<buckets.length;i++)
            if(buckets[i]!=null)
                std+=(mean-buckets[i].getSize())*(mean-buckets[i].getSize());
            else
                std+=(mean)*mean;
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
