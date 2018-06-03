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
public class BSTSet<T extends Comparable> implements Set<T>{
    
    private TreeNode<T> root;
    private int size;
    
    public BSTSet(){
        root=null;
        size=0;
    }
    
    @Override
    public void add(T value) {
        if(!contains(value)){
            if(root == null)
                root = new TreeNode(value);
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
            T result = root.getValue();
            remove(root.getValue());
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
    
    private String toStringHelper(TreeNode<T> node){
        if(node==null)
            return "";
        return node.getValue()+" "+toStringHelper(node.getLeft())+
                toStringHelper(node.getRight());
    }
    
    private boolean containsHelper(TreeNode<T> node,T value){
        
        if (node != null)
        return value.equals(node.getValue()) || 
                containsHelper(node.getLeft(), value) ||
                containsHelper(node.getRight(), value);
        return false;
    }
    
    private void addHelper(TreeNode<T> node, T value){
        
        if(value.compareTo(node.getValue())<0){
            if(node.getLeft() == null){
                node.setLeft(new TreeNode(value)); 
                return;
            }
            addHelper(node.getLeft(), value);
        }else{
            if(node.getRight() == null){
                node.setRight(new TreeNode(value)); 
                return;
            }
            addHelper(node.getRight(), value);       
        }
    }
    private void findNode(TreeNode<T> node,T value){
        TreeNode<T> parent = null;
        TreeNode<T> child = node;
        while(child.getValue()!=value){
            parent=child;
            if(value.compareTo(child.getValue())<0){
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
            TreeNode<T> small = child.getRight();
            TreeNode<T> parentSmall = child;
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
        
        
    }
}
