// 
// Decompiled by Procyon v0.5.36
// 

package br.com.ribeiro.fernando.unzipsantander;

public class Path
{
    private static String input;
    private static String output;
    
    static {
        Path.input = "C:\\arquivo_santander_input\\";
        Path.output = "C:\\arquivo_santander_output\\";
    }
    
    public static String getInput() {
        return Path.input;
    }
    
    public static String getOutput() {
        return Path.output;
    }
}
