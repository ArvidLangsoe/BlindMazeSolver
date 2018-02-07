package com.arvid;


import java.util.ArrayList;
import java.util.Stack;

public class BlindMazeSolver implements MazeSolver, Runnable
{

    ArrayList<MazeSolverObserver> observers = new ArrayList<MazeSolverObserver>();

    char[][] maze = null;
    int iterationDelay = 0;


    char[][] mazeSolve = null;
    Stack<int[]> path = null;


    @Override
    public String getMaze() {
        StringBuilder builder = new StringBuilder();
        for(char[] row : maze) {
            for (char col : row)
                builder.append(col);
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public void setUpdateDelay(int miliseconds) {
        iterationDelay=miliseconds;
    }

    @Override
    public void setMaze(char[][] maze) {
        this.maze=maze;

    }

    @Override
    public void addObserver(MazeSolverObserver obs) {
        observers.add(obs);
    }

    @Override
    public Stack<int[]> getFinalPath() {
        return path;
    }


    @Override
    public void run() {
        System.out.println("Thread Running");
        int[] start = findSinglePoint('S');
        int[] goal = findSinglePoint('G');

        Stack<int[]> path=new Stack<>();
        path.add(start);

        solveMaze(maze.clone(),start,goal,path);


    }

    private int[] findSinglePoint(char search){

        for(int i =0; i< maze.length;i++)
            for (int j =0;j<maze[0].length;j++)
                if(maze[i][j]==search)
                    return new int[]{i,j};

        return null;
    }

    private boolean solveMaze(char[][] maze, int[] start, int[] goal, Stack<int[]> path){
        try {
            Thread.sleep(iterationDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(MazeSolverObserver obs : observers)
            obs.notifyIteration();

        if(start[0]==goal[0] && start[1]==goal[1]){
            this.path=path;
            this.maze=maze;

            for(MazeSolverObserver obs : observers)
                obs.notifyDone();
            return true;
        }



        int[] newStart =new int[]{(start[0]-1),(start[1])};
        if(checkPath(maze,newStart,goal,path)){
            return true;
        }


        newStart =new int[]{(start[0]+1),(start[1])};
        if(checkPath(maze,newStart,goal,path)){
            return true;
        }

        newStart =new int[]{(start[0]),(start[1]-1)};
        if(checkPath(maze,newStart,goal,path)){
            return true;
        }


        newStart =new int[]{(start[0]),(start[1]+1)};

        if(checkPath(maze,newStart,goal,path)){
            return true;
        }

        return false;
    }

    private boolean checkPath(char[][] maze, int[] newStart, int[] goal, Stack<int[]> path){
        try{
            if(maze[newStart[0]][newStart[1]]==' '||maze[newStart[0]][newStart[1]]=='G'){
                path.push(newStart);
                maze[newStart[0]][newStart[1]] = '#';

                if(solveMaze(maze,newStart,goal,path)){
                    return true;
                }
                else{
                    maze[newStart[0]][newStart[1]]=' ';
                    path.pop();
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

}
