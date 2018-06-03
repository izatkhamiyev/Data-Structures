/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.HashTableMap;
import csci152.adt.Map;
import static java.lang.Math.abs;

/**
 *
 * @author User
 */
public class LLQHashTableMap<K,V> implements HashTableMap<K,V>{
    private LinkedListQueue<KeyValuePair<K,V>>[] buckets;
    private int size;
    
    public LLQHashTableMap(int k){
        buckets = new LinkedListQueue[k];
        size=0;
    }

    @Override
    public void define(K key, V value) {
        int x = abs(key.hashCode())%buckets.length;
        if(buckets[x]==null)
            buckets[x]= new LinkedListQueue();
        boolean ok=false;
        for(int j=1;j<=buckets[x].getSize();j++){
            try {
                KeyValuePair<K,V> it=buckets[x].dequeue();
                if(it.getKey().equals(key)){
                    it.setValue(value);
                    ok=true;
                    buckets[x].enqueue(it);
                    break;
                }else
                    buckets[x].enqueue(it);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }     
        if(!ok){
            buckets[x].enqueue(new KeyValuePair(key,value));
            size++;
        }
        
    }

    @Override
    public V getValue(K key) {
        int x = abs(key.hashCode())%buckets.length;
        if(buckets[x]==null){
            return null;
        }else{
            V result = null;
            boolean ok=false;
            for(int j=1;j<=buckets[x].getSize();j++){
                try {
                    KeyValuePair<K,V> it=buckets[x].dequeue();
                    if(it.getKey().equals(key)){
                        result=it.getValue();
                        ok=true;
                        buckets[x].enqueue(it);
                        break;
                    }
                    else
                        buckets[x].enqueue(it);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }     
            return result;
        }
    }

    @Override
    public V remove(K key) {
        int x = abs(key.hashCode())%buckets.length;
        if(buckets[x]==null){
            return null;
        }else{
            V result = null;
            boolean ok=false;
            for(int j=1;j<=buckets[x].getSize();j++){
                try {
                    KeyValuePair<K,V> it=buckets[x].dequeue();
                    if(it.getKey().equals(key)){
                        result=it.getValue();
                        size--;
                        ok=true;
                        break;
                    }
                    else
                        buckets[x].enqueue(it);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }     
            return result;
        }
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        else{
            KeyValuePair<K,V> result = null;
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
        int k = buckets.length;
        buckets = new LinkedListQueue[k];
        size=0;
    }

    @Override
    public String toString() {
        String s="[";
        for(int i=0;i<buckets.length;i++){
            if(buckets[i]!=null){
                for(int j=1;j<=buckets[i].getSize();j++){
                    try {
                        KeyValuePair<K,V> it=buckets[i].dequeue();
                        s+=it.toString()+" ";
                        buckets[i].enqueue(it);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }                    
                }
              //  if(buckets[i].getSize()!=0)
                    //s+="\n";
            }
        }
        return s+"]";
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
