/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTTest;

import csci152.impl.TowerOfHanoi;

/**
 *
 * @author User
 */
public class TowerOfHonoi {
     public static void main(String[] args)  {
        TowerOfHanoi solve = new TowerOfHanoi(10);
        Honoi(solve, 10, 0, 2, 1);
        System.out.println(solve.getNumMoves());
        System.out.println(solve.toString());
    }
    public static void Honoi(TowerOfHanoi solve, int n,int from, int to,int auxilary){
            if(n==1){
                try {
                    solve.moveDisc(from, to);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
            }else{
                Honoi(solve, n-1, from, auxilary, to);
                try {
                    solve.moveDisc(from, to);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Honoi(solve, n-1, auxilary, to, from);
            }
    }
   
}
