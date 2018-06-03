
package robot_world;

import csci152.adt.Stack;
import csci152.impl.LinkedListStack;
import java.io.IOException;

public class SuperRobotWorld extends RobotWorld {

	// TODO: Add some fields to keep track of commands
    private Stack<Character> undo;
    private Stack<Character> redo; 
    
    public SuperRobotWorld(String mapFile) throws IOException {
        super(mapFile);
        undo= new LinkedListStack();
        redo = new LinkedListStack();
    }
    
    
    /**
     * Undo the last move or rotation command that put the robot
     * in its current state.  If no commands have been issued yet,
     * do nothing.
     */
    
    @Override
    public void rotateClockwise() {
        super.rotateClockwise(); //To change body of generated methods, choose Tools | Templates.
        // TODO: Fix me
        undo.push('c');
        redo = new LinkedListStack();
    }

    @Override
    public void rotateCounterClockwise() {
        super.rotateCounterClockwise(); //To change body of generated methods, choose Tools | Templates.
        undo.push('r');
        redo = new LinkedListStack();
    }

    @Override
    public void moveForward() throws Exception {
        super.moveForward(); //To change body of generated methods, choose Tools | Templates.
        undo.push('m');
        redo = new LinkedListStack();
    }
    

    public void undoCommand() {
        // TODO: Fix me
        try {
            char c=undo.pop();
            redo.push(c);
            if(c=='c'){
                super.rotateCounterClockwise();
            }
            if(c=='r'){
                super.rotateClockwise();
            }
            if(c=='m'){
                super.rotateClockwise();
                super.rotateClockwise();
                super.moveForward();
                super.rotateCounterClockwise();
                super.rotateCounterClockwise();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Undo the last n commands.  Do nothing if n is zero or negative.
     * 
     * @param n the number of commands to undo
     */
    public void undoCommands(int n) {
    	// TODO:  Three lines of code, if you use undoCommand
            for(int i=0;i<n;i++){
                undoCommand();
            }
    }
    
    /**
     * For previously undone commands, redo the last command that was
     * undone
     */
    public void redoUndoneCommand() {
        // TODO: Fix me!!!
        try {
            char c=redo.pop();
            //redo.push(c);
            if(c=='c'){
                super.rotateClockwise();
            }
            if(c=='r'){
                super.rotateCounterClockwise();
            }
            if(c=='m'){
                super.moveForward();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Redo the last n undone commands.  Do nothing if n is zero or negative.
     * 
     * @param n the number of commands to redo
     */
    public void redoUndoneCommands(int n) {
    	// TODO:  Also three lines of code
            for(int i=0;i<n;i++)
                redoUndoneCommand();
    }
}
