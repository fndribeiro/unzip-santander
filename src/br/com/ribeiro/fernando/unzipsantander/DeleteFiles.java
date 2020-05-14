package br.com.ribeiro.fernando.unzipsantander;

import java.util.List;

import java.util.Arrays;
import java.io.File;

public class DeleteFiles
{
    public static void deleteInput() {
        final File pathInput = new File(Path.getInput());
        final List<File> listOfIntputs = Arrays.asList(pathInput.listFiles());
        for (final File x : listOfIntputs) {
            x.delete();
            System.out.println(String.valueOf(x.getName()) + " - Deletado.");
        }
    }
    
    public static void deleteOutput() {
        final File pathOutput = new File(Path.getOutput());
        final List<File> listOfOutputs = Arrays.asList(pathOutput.listFiles());
        for (final File x : listOfOutputs) {
            x.delete();
            System.out.println(String.valueOf(x.getName()) + " - Deletado.");
        }
    }
}
