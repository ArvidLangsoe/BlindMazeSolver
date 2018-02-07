package com.arvid;

import java.io.File;
import java.util.Stack;

public interface MazeSolver {


    String getMaze();

    void setUpdateDelay(int miliseconds);

    void setMaze(char[][] maze);

    void addObserver(MazeSolverObserver obs);

    Stack<int[]> getFinalPath();






}
