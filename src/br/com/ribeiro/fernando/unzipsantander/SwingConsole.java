// 
// Decompiled by Procyon v0.5.36
// 

package br.com.ribeiro.fernando.unzipsantander;

import javax.swing.text.BadLocationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.io.OutputStream;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class SwingConsole extends JFrame
{
    private JTextArea textArea;
    private JButton buttonStart;
    private JButton buttonClear;
    private JButton buttonStop;
    private PrintStream standardOut;
    
    public SwingConsole() {
        this.buttonStart = new JButton("Start");
        this.buttonClear = new JButton("Clear");
        this.buttonStop = new JButton("Stop");
    }
    
    void createPanel() {
        (this.textArea = new JTextArea(15, 30)).setEditable(false);
        final JScrollPane scroll = new JScrollPane(this.textArea);
        scroll.setVerticalScrollBarPolicy(22);
        final PrintStream printStream = new PrintStream(new CustomOutputStream(this.textArea));
        this.standardOut = System.out;
        System.setOut(printStream);
        System.setErr(printStream);
        final JPanel textPanel = new JPanel();
        textPanel.add(scroll);
        final JPanel mainPanel = new JPanel();
        mainPanel.add(this.buttonStart);
        mainPanel.add(this.buttonStop);
        mainPanel.add(this.buttonClear);
        final JFrame frame = new JFrame("Envio Autom\u00e1tico dos Emails Santander");
        frame.add(mainPanel);
        frame.add(textPanel, "South");
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        final ActionListener StartListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    StartButtonMethod.Start();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
        final ActionListener StopListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    throw new InterruptedException();
                }
                catch (InterruptedException e2) {
                    System.out.println("Programa interrompido.");
                }
            }
        };
        final ActionListener ClearListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    SwingConsole.this.textArea.getDocument().remove(0, SwingConsole.this.textArea.getDocument().getLength());
                }
                catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        };
        this.buttonStart.addActionListener(StartListener);
        this.buttonStop.addActionListener(StopListener);
        this.buttonClear.addActionListener(ClearListener);
    }
    
    public static void main(final String[] args) {
        new SwingConsole().createPanel();
        for (int i = 0; i < 20; ++i) {
            System.out.println(i);
        }
    }
}
