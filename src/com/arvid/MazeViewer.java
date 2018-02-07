package com.arvid;

import com.arvid.commands.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeViewer implements MazeSolverObserver{

    private Scanner sc= new Scanner(System.in);


    private String output;

    private MazeSolver mazeSolver;

    private boolean running = false;

    private boolean newIteration = false;



    public MazeViewer(MazeSolver mazeSolver){
        this.mazeSolver = mazeSolver;
        Command.setMazeSolver(mazeSolver);
    }


    public void start(){
        mazeSolver.addObserver(this);
        while(true){
            handleNextCommand();
            displayOutput();
        }
    }


    private void handleNextCommand(){
        String command = getCommand();
        String[] commandSplit = command.split(" ");

        Command myCommand=null;

        switch (commandSplit [0]){
            case "delay":
                myCommand=new Delay(commandSplit);
                break;
            case "help":
                myCommand=new Help(commandSplit);
                break;
            case "list":
                myCommand=new MazeList(commandSplit);
                break;
            case "maze":
                myCommand=new MazeFile(commandSplit);
                break;
            case "start":
                handleMazeSolving();
                break;
        }

        if(myCommand!=null) {
            try {
                output = myCommand.getCommandResultText();
            } catch (FileNotFoundException e) {
                output = "File not found!";
            }
        }

    }

    private void handleMazeSolving() {
        Thread mazeThread = new Thread((Runnable) mazeSolver);
        mazeThread.start();
        System.out.println("Starting Thread");
        running=true;

        while(running){
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(newIteration&&running){
                newIteration=false;
                output=mazeSolver.getMaze()+"\n";
                displayOutput();
            }
        }

    }

    private String getCommand(){
        return sc.nextLine();
    }


    private void displayOutput(){
        if(output!=null) {
            System.out.print("\r\r"+output);
        }

        output=null;
    }

    @Override
    public void notifyIteration() {
        newIteration=true;
    }

    @Override
    public void notifyDone() {
        System.out.println("It works!");
        System.out.println(mazeSolver.getMaze());
        System.out.print("path=");
        for(int[] point : mazeSolver.getFinalPath())
            System.out.print ("("+point[0]+", "+point[1]+"),");
        System.out.println();

        running=false;

    }
}
