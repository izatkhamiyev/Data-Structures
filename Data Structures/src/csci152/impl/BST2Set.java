/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Set;

/**
 *
 * @author User
 */
public class BST2Set<T extends Comparable> implements Set<T>{

    private OnOffTreeNode<T> root;
    private int size;

    public BST2Set() {
        root=null;
        size=0;
        
    }    
    
    @Override
    public void add(T value) {
        if(!contains(value)){
            if(root == null)
                root = new OnOffTreeNode(value);
            else
                addHelper(root, value);
            size++;
        }
    }

    @Override
    public boolean contains(T value) {
        return containsHelper(root, value);
    }

    @Override
    public boolean remove(T value) {
        if(!contains(value))
            return false;
        findNode(root, value);
        size--;
        return true;
    }

    @Override
    public T removeAny() throws Exception {
        if(size==0)
            throw new Exception("No elements");
        else{
            T result=removeAnyHelper(root);
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
        root=null;
        size=0;
    }
    @Override
    public String toString() {
        return toStringHelper(root); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String toStringHelper(OnOffTreeNode<T> node){
        if(node==null)
            return "";
        if(node.isActive())
            return node.getValue()+" "+toStringHelper(node.getLeft())+
                toStringHelper(node.getRight());
        else
            return toStringHelper(node.getLeft())+
                toStringHelper(node.getRight());
       
    }
    
    private boolean containsHelper(OnOffTreeNode<T> node,T value){
        
        if (node != null)
        return (value.equals(node.getValue())&&node.isActive())  || 
                containsHelper(node.getLeft(), value) ||
                containsHelper(node.getRight(), value);
        return false;
    }
    private void addHelper(OnOffTreeNode<T> node, T value){
        
        if(value.compareTo(node.getValue())<0){
            if(node.getLeft() == null){
                node.setLeft(new OnOffTreeNode(value)); 
                return;
            }
            addHelper(node.getLeft(), value);
        }else if(value.compareTo(node.getValue())>0){
                if(node.getRight() == null){
                    node.setRight(new OnOffTreeNode(value)); 
                    return;
                }
                addHelper(node.getRight(), value);  
        }else{
            node.setActive(true);
            return;
        }
        
    }
    private void findNode(OnOffTreeNode<T> node,T value){
        OnOffTreeNode<T> child = node;
        while(child.getValue()!=value){
            if(value.compareTo(child.getValue())<0){
                child=child.getLeft();
            }else
                child=child.getRight();
        }
       // System.out.println(child.getValue());
        child.setActive(false);
    }
    
    private T removeAnyHelper(OnOffTreeNode<T> node){
        if(node==null)
            return null;
        if(node.isActive()){
            node.setActive(false);
            return node.getValue();
        }
        T result = removeAnyHelper(node.getLeft());
        if(result!=null)
            return result;
        return (T) removeAnyHelper(node.getRight());
    }
}
