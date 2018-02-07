package com.arvid.commands;

import com.arvid.MazeSolver;

import java.io.FileNotFoundException;


public abstract class Command {
    protected String[] command;

    public static  MazeSolver mazeSolver=null;


    public Command(String[] command){

        this.command = command;
    }

    public abstract String getCommandResultText() throws FileNotFoundException;

    public static void  setMazeSolver(MazeSolver mazeSolver){
        Command.mazeSolver=mazeSolver;
    }
}
