package com.arvid.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeFile extends Command {

    public MazeFile(String[] command) {
        super(command);
    }

    @Override
    public String getCommandResultText() throws FileNotFoundException {

        String mazeName= command[1];

        String mazeFile= "mazes/"+mazeName+".maze";


        Scanner fileReader = new Scanner(new FileInputStream(mazeFile));

        String[] sizes =fileReader.nextLine().split(" ");

        char[][] maze = new char [Integer.parseInt(sizes[0])] [Integer.parseInt(sizes[1])];

        for(int i=0;i<maze.length;i++) {
            char[] line = fileReader.nextLine().toCharArray();
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = line[j];
            }
        }


        Command.mazeSolver.setMaze(maze);
        return "Current imported maze: \n"+Command.mazeSolver.getMaze();
    }
}
