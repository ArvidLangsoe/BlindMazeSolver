package com.arvid.commands;

import java.io.File;
import java.io.FileNotFoundException;

public class MazeList extends Command{

    public MazeList(String[] command) {
        super(command);
    }


    @Override
    public String getCommandResultText() throws FileNotFoundException {
        File directory = new File("mazes");
        StringBuilder builder = new StringBuilder().append("Available mazes: \n");
        for(File file : directory.listFiles()) {
            String[] filePath = file.getAbsolutePath().split("\\\\");

            //TODO: Error here, it is not split properly.
            String filename =filePath[filePath.length-1].split("\\.")[0];
            builder.append(filename+"\n");
        }

        return builder.append("\n").toString();
    }
}
