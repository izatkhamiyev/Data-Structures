/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

/**
 *
 * @author User
 */
public class OnOffTreeNode<T> {
    private T value;
    
    private boolean active;
    private OnOffTreeNode<T> left;
    private OnOffTreeNode<T> right;
    
    /**
     * Construct a tree node with the given value, 
     * and null children links
     * 
     * @param val to set on the node
     */
    public OnOffTreeNode(T val) {
        value = val;
        left = null;
        right = null;
        active=true;
    }
    
    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return the left
     */
    public OnOffTreeNode<T> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(OnOffTreeNode left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public OnOffTreeNode getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(OnOffTreeNode right) {
        this.right = right;
    }
        
    /**
     * @return the string representation of the value 
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * @return the on
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param on the on to set
     */
    public void setActive(boolean on) {
        this.active = on;
    }
}
