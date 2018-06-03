/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Map;

/**
 *
 * @author User
 */
public class BSTMap<K extends Comparable,V> implements Map<K,V>{

    
    private TreeNode<KeyValuePair<K,V>> root;
    private int size;
    public BSTMap() {
        root = null;
        size = 0;
    }
    @Override
    public void define(K key, V value) {
        if(!containsHelper(root, key)){
            if(root == null) {
                root = new TreeNode(new KeyValuePair(key, value));
            }
            else
                defineHelper(root, key, value);
            size++;
        }
        else
            defineHelper(root, key, value);
    }

    @Override
    public V getValue(K key) {
        if(containsHelper(root, key))
            return findKeyValue(root, key);
        return null;
    }

    @Override
    public V remove(K key) {
        if(containsHelper(root, key)){
            size--;
            return removeHelper(root, key);
        }
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        else{
            KeyValuePair<K, V> result = root.getValue();
            remove(root.getValue().getKey());
            return result;
        }
            
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {
        return "["+toStringHelper(root)+"]";
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
                root = null;
        }else if(child.getLeft()==null || child.getRight()==null){
            if(parent!=null){
                if(parent.getLeft()==child)
                    parent.setLeft(child.getLeft()!=null?child.getLeft():child.getRight());
                else
                    parent.setRight(child.getLeft()!=null?child.getLeft():child.getRight());
            }else{
                root = child.getLeft()!=null?child.getLeft():child.getRight();
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
