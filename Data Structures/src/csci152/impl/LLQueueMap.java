/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.impl.KeyValuePair;
import csci152.adt.Map;
import csci152.impl.LinkedListQueue;

/**
 *
 * @author User
 */
public class LLQueueMap<K,V> implements Map<K,V>{
    
    private LinkedListQueue<KeyValuePair<K,V>> pairs;
    
    public LLQueueMap(){
        pairs = new LinkedListQueue();
    }

    @Override
    public void define(K key, V value) {
        boolean isThere=false;
        for(int i=1;i<=pairs.getSize();i++){
            try {
                KeyValuePair<K,V> it = pairs.dequeue();
                if(it.getKey().equals(key)){
                    it.setValue(value);
                    pairs.enqueue(it);
                    isThere = true;
                    break;
                }else
                    pairs.enqueue(it);
            } catch (Exception e) {
                System.out.println("No elements");
            }
        }
        if(!isThere){
            pairs.enqueue(new KeyValuePair(key,value));
        }
    }

    @Override
    public V getValue(K key) {
        boolean isThere=false;
        V result = null;
        for(int i=1;i<=pairs.getSize();i++){
            try {
                KeyValuePair<K,V> it = pairs.dequeue();
                if(it.getKey().equals(key)){
                    result = it.getValue();
                    pairs.enqueue(it);
                    isThere = true;
                    break;
                }else
                    pairs.enqueue(it);
            } catch (Exception e) {
                System.out.println("No elements in the Queue");
            }
        }
        return result;
    }

    @Override
    public V remove(K key) {
        boolean isThere=false;
        KeyValuePair<K,V> result = null;
        for(int i=1;i<=pairs.getSize();i++){
            try {
                KeyValuePair<K,V> it = pairs.dequeue();
                if(it.getKey().equals(key)){
                    result = it;
                   // pairs.enqueue(it);
                    isThere = true;
                    break;
                }else
                    pairs.enqueue(it);
            } catch (Exception e) {
                System.out.println("No elements in the Queue");
            }
        }
        if(result!=null)
            return result.getValue();
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        return pairs.dequeue();
    }

    @Override
    public int getSize() {
        return pairs.getSize();
    }

    @Override
    public void clear() {
        pairs = new LinkedListQueue();
    }

    @Override
    public String toString() {
        String s="[";
        for(int i=1;i<=pairs.getSize();i++){
            try {
                KeyValuePair<K,V> it = pairs.dequeue();
                s+=it.toString();
                pairs.enqueue(it);
            } catch (Exception e) {
                System.out.println("No elements in the Queue");
            }
        }
        return s+"]";
    }
    
      
}
