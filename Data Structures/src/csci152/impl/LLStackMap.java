/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Map;
import csci152.adt.Stack;


public class LLStackMap<K,V> implements Map<K,V> {

    private LinkedListStack<KeyValuePair<K,V>> pairs;
    public LLStackMap(){
        pairs = new LinkedListStack();
    }
    @Override
    public void define(K key, V value) {
        boolean isThere = false;
        Stack<KeyValuePair<K,V>> temp = new LinkedListStack();
        int sz=pairs.getSize();
        for(int i=1;i<=sz;i++){
            try {
                KeyValuePair<K,V> it = pairs.pop();
                if(it.getKey().equals(key)){
                    it.setValue(value);
                    temp.push(it);
                    isThere = true;
                    break;
                }else
                    temp.push(it);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while(temp.getSize()!=0){
            try {
               
                pairs.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(!isThere)
            pairs.push(new KeyValuePair(key, value));
       
    }

    @Override
    public V getValue(K key) {
        V result = null;
        Stack<KeyValuePair<K,V>> temp = new LinkedListStack();
        int sz=pairs.getSize();
        for(int i=1;i<=sz;i++){
            try {
                KeyValuePair<K,V> it = pairs.pop();
                if(it.getKey().equals(key)){
                    result = it.getValue();
                    temp.push(it);
                    break;
                }else
                    temp.push(it);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        while(temp.getSize()!=0){
            try {
                pairs.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public V remove(K key) {
        boolean isThere = false;
        KeyValuePair<K,V> result = null;
        Stack<KeyValuePair<K,V>> temp = new LinkedListStack();
        int sz=pairs.getSize();
        for(int i=1;i<=sz;i++){
            try {
                KeyValuePair<K,V> it = pairs.pop();
                if(it.getKey().equals(key)){
                    result = it;
                   // temp.push(it);
                    isThere = true;
                    break;
                }else
                    temp.push(it);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        while(temp.getSize()!=0){
            try {
                pairs.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(result!=null)
            return result.getValue();
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        return pairs.pop();
    }

    @Override
    public int getSize() {
        return pairs.getSize();
    }

    @Override
    public void clear() {
        pairs = new LinkedListStack();
    }

    @Override
    public String toString() {
        Stack<KeyValuePair<K,V>> temp = new LinkedListStack();
        String s="[";
        int sz=pairs.getSize();
        for(int i=1;i<=sz;i++){
            try {
                KeyValuePair<K,V> result = pairs.pop();
                s+=result.toString();
                temp.push(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while(temp.getSize()!=0){
            try {
                pairs.push(temp.pop());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return s+"]";
    }
    
    
}
