package com.arvid.commands;

public class Delay extends Command {



    public Delay(String[] command) {
        super(command);
    }

    @Override
    public String getCommandResultText() {

        mazeSolver.setUpdateDelay(Integer.parseInt(command[1]));

        return new StringBuilder()
                .append("Added delay: ")
                .append(command[1])
                .append("\n")
                .toString();


    }
}
