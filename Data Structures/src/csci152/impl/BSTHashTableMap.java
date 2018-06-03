/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.HashTableMap;
import static java.lang.Math.abs;

/**
 *
 * @author User
 */
public class BSTHashTableMap<K extends Comparable,V> implements HashTableMap<K, V>{
    private TreeNode<KeyValuePair<K,V>>[] buckets;
    private int size;
    private int [] sizes;
    public BSTHashTableMap(int k){
        buckets = new TreeNode[k];
        sizes=new int[k];
        size=0;
    }

    @Override
    public void define(K key, V value) {
        int x = abs(key.hashCode())%buckets.length;
        if(!containsHelper(buckets[x], key)){
            if(buckets[x] == null) {
                buckets[x] = new TreeNode(new KeyValuePair(key, value));
            }
            else
                defineHelper(buckets[x], key, value);
            size++;
            sizes[x]++;
        }
        else
            defineHelper(buckets[x], key, value);
    }

    @Override
    public V getValue(K key) {
        int x = abs(key.hashCode())%buckets.length;
        if(containsHelper(buckets[x], key))
            return findKeyValue(buckets[x], key);
        return null;
    }

    @Override
    public V remove(K key) {
        int x = abs(key.hashCode())%buckets.length;
        if(containsHelper(buckets[x], key)){
            size--;
            sizes[x]--;
            return removeHelper(buckets[x], key);
        }
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        else{
            KeyValuePair<K, V> result = null;
            for(int i=0;i<buckets.length;i++){
                if(buckets[i]!=null){
                    result = buckets[i].getValue();
                    remove(buckets[i].getValue().getKey());
                    break;
                }
            }
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
        buckets = new TreeNode[k];
        sizes = new int[k];
        size=0;
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
            return sizes[index];
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
                mean+=sizes[i];
        mean/=buckets.length;
        for(int i=0;i<buckets.length;i++)
            if(buckets[i]!=null)
                std+=(mean-sizes[i])*(mean-sizes[i]);
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

    @Override
    public String toString() {
        String s="[";
        for(int i=0;i<buckets.length;i++)
            s+=toStringHelper(buckets[i]);
        return s+"]";
    }

    
    
    private void defineHelper(TreeNode<KeyValuePair<K,V>> node,K key, V value){
        if(key.compareTo(node.getValue().getKey())==0){
            node.getValue().setValue(value);
            return;
        }
        if(key.compareTo(node.getValue().getKey())<0){
            if(node.getLeft() == null){
                node.setLeft(new TreeNode(new KeyValuePair(key,value))); 
                return;
            }
            defineHelper(node.getLeft(),key,value);
        }else{
            if(node.getRight() == null){
                node.setRight(new TreeNode(new KeyValuePair(key,value))); 
                return;
            }
            defineHelper(node.getRight(), key, value);       
        }
    }
    private boolean containsHelper(TreeNode<KeyValuePair<K,V>> node,K key){  
        if (node != null)
             return key.equals(node.getValue().getKey()) || 
                containsHelper(node.getLeft(),key) ||
                containsHelper(node.getRight(), key);
        return false;
    }
    private V findKeyValue(TreeNode<KeyValuePair<K,V>> node,K key){
        TreeNode<KeyValuePair<K,V>> child = node;
        while(child.getValue().getKey()!=key){
            if(key.compareTo(child.getValue().getKey())<0){
                child=child.getLeft();
            }else
                child=child.getRight();
        }
        return child.getValue().getValue();
    }
    private V removeHelper(TreeNode<KeyValuePair<K,V>> node,K key){
        int x=abs(key.hashCode())%buckets.length;
       // TreeNode<KeyValuePair<K,V>> root = buckets[abs(key.hashCode())%buckets.length];
        TreeNode<KeyValuePair<K,V>> parent = null;
        TreeNode<KeyValuePair<K,V>> child = node;
        while(child.getValue().getKey()!=key){
            parent=child;
            if(key.compareTo(child.getValue().getKey())<0){
                child=child.getLeft();
            }else
                child=child.getRight();
        }
        if(child.getLeft()==null && child.getRight()==null){
            if(parent!=null){
                if(parent.getLeft()==child)
                    parent.setLeft(null);
                else
                    parent.setRight(null);
            }else
                buckets[x] = null;
        }else if(child.getLeft()==null || child.getRight()==null){
            if(parent!=null){
                if(parent.getLeft()==child)
                    parent.setLeft(child.getLeft()!=null?child.getLeft():child.getRight());
                else
                    parent.setRight(child.getLeft()!=null?child.getLeft():child.getRight());
            }else{
                buckets[x] = child.getLeft()!=null?child.getLeft():child.getRight();
            }
        }else{
            TreeNode<KeyValuePair<K,V>> small = child.getRight();
            TreeNode<KeyValuePair<K,V>> parentSmall = child;
            while(small.getLeft()!=null){
                parentSmall = small;
                small = small.getLeft();
            }
            
            child.setValue(small.getValue());
            if(small.getRight()!=null)
                parentSmall.setLeft(small.getRight());
            else if(parentSmall==child)
                    parentSmall.setRight(null);
                 else
                    parentSmall.setLeft(null);
        }
        
        return child.getValue().getValue();
    }
     
    private String toStringHelper(TreeNode<KeyValuePair<K,V>> node){
        if(node==null)
            return "";
        return node.getValue().toString()+" "+toStringHelper(node.getLeft())+
                toStringHelper(node.getRight());
    }
    
}

