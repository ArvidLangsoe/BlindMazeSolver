package com.arvid.commands;

public class Help extends Command {

    private final  String[] commands= {
            "delay miliseconds, sets the number of miliseconds delay between every iteration of pathfinding.",
            "help, outputs this list. ",
            "maze name, sets the current maze to the named maze.",
            "start, starts the current maze finding algorithm.",
            "list, lists all the mazes available"
    };

    public Help(String[] command) {
        super(command);
    }

    @Override
    public String getCommandResultText() {
        StringBuilder builder = new StringBuilder().append("The following are the supported commands:\n");

        for(String s: commands)
            builder.append(s+"\n");

        return builder.toString();
    }
}
