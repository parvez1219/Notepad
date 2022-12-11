import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class notepad implements ActionListener {
    static JFrame jframe;
    static JTextArea jtextArea;
    static JButton jButton;
    static JMenuBar jmenuBar;
    static JMenu Files;
    static JMenuItem New,Open,Save,Print,Close;
    static JMenu Edit;
    static JMenuItem Cut,Copy,Paste;

    notepad(){
        jframe=new JFrame("Notepad");
        jframe.setBounds(0,0,500,500);
        jtextArea=new JTextArea("Type Here\n");
        jmenuBar=new JMenuBar();

        Files=new JMenu("Files");
        New=new JMenuItem("New");
        New.addActionListener(this);
        Open=new JMenuItem("Open");
        Open.addActionListener(this);
        Save=new JMenuItem("Save");
        Save.addActionListener(this);
        Print=new JMenuItem("Print");
        Print.addActionListener(this);
        Close=new JMenuItem("Close");
        Close.addActionListener(this);
        Files.add(New);
        Files.add(Open);
        Files.add(Save);
        Files.add(Print);
        Files.add(Close);

        Edit=new JMenu("Edit");
        Cut=new JMenuItem("Cut");
        Cut.addActionListener(this);
        Copy=new JMenuItem("Copy");
        Copy.addActionListener(this);
        Paste=new JMenuItem("Paste");
        Paste.addActionListener(this);
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        jmenuBar.add(Files);
        jmenuBar.add(Edit);
        jmenuBar.setVisible(true);


        jframe.setJMenuBar(jmenuBar);
        jframe.add(jtextArea);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
    public static void main(String[] args) {
        notepad n=new notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String a= e.getActionCommand();
        if(a.equals("New")){
            jtextArea.setText("");
        } else if (a.equals("Open")) {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans== JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="\n";
                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2=s2+s1+"\n";
                    }
                    jtextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (a.equals("Save")) {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showSaveDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,false));
                    bufferedWriter.write(jtextArea.getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }else if (a.equals("Print")) {
            try {
                jtextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if (a.equals("Cut")) {
            jtextArea.cut();
        }else if (a.equals("Copy")) {
            jtextArea.copy();
        }else if (a.equals("Paste")) {
            jtextArea.paste();
        }
        else{
            System.exit(0);
        }
    }
}