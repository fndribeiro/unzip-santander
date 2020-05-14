package br.com.ribeiro.fernando.unzipsantander;

import java.io.IOException;
import javax.swing.JTextArea;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream
{
    private JTextArea textArea;
    
    public CustomOutputStream(final JTextArea textArea) {
        this.textArea = textArea;
    }
    
    @Override
    public void write(final int b) throws IOException {
        this.textArea.append(String.valueOf((char)b));
        this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
    }
}
