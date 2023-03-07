import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame {
    public JFrame frame;
    public JTextField timeLimTxt;
    public JTextField nrServersTF;
    public JTextField nrTasksTF;
    public JTextField minArrTF;
    public JTextField maxArrTF;
    public JTextField minProTF;
    public JTextField maxProTF;
    private JComboBox comboBox;
    public JButton buton;

    private JTextArea textArea;
    //private JTextField result;

    Controller c = new Controller(this);

    public SimulationFrame() {
        initialize();
    }

    public void setTextArea1(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void setTextArea(String simulationContent) {
        this.textArea.setText(simulationContent);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    /*
    public JTextField getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result.setText(result);
    }
*/
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel timeLimit = new JLabel("Time limit: ");
        timeLimit.setFont(new Font("Tahoma", Font.BOLD, 12));
        timeLimit.setHorizontalAlignment(SwingConstants.TRAILING);
        timeLimit.setBounds(540, 80, 68, 22);
        frame.getContentPane().add(timeLimit);

        timeLimTxt = new JTextField();
        timeLimTxt.setBounds(615, 80, 135, 20);
        frame.getContentPane().add(timeLimTxt);
        timeLimTxt.setColumns(10);

        JLabel nrServers = new JLabel("Number of servers:");
        nrServers.setFont(new Font("Tahoma", Font.BOLD, 12));
        nrServers.setBounds(540, 120, 120, 20);
        frame.getContentPane().add(nrServers);

        JLabel maxArr = new JLabel("Maximum:");
        maxArr.setFont(new Font("Tahoma", Font.BOLD, 12));
        maxArr.setBounds(258, 120, 65, 20);
        frame.getContentPane().add(maxArr);

        nrServersTF = new JTextField();
        nrServersTF.setBounds(660, 120, 90, 20);
        frame.getContentPane().add(nrServersTF);
        nrServersTF.setColumns(10);

        nrTasksTF = new JTextField();
        nrTasksTF.setBounds(650, 160, 100, 20);
        frame.getContentPane().add(nrTasksTF);
        nrTasksTF.setColumns(10);

        JLabel arr = new JLabel("Arrival time:");
        arr.setFont(new Font("Tahoma", Font.BOLD, 12));
        arr.setBounds(20, 120, 85, 20);
        frame.getContentPane().add(arr);

        minArrTF = new JTextField();
        minArrTF.setBounds(185, 120, 50, 19);
        frame.getContentPane().add(minArrTF);
        minArrTF.setColumns(10);

        maxArrTF = new JTextField();
        maxArrTF.setBounds(320, 120, 50, 19);
        frame.getContentPane().add(maxArrTF);
        maxArrTF.setColumns(10);

        JLabel minArr = new JLabel("Minimum:");
        minArr.setFont(new Font("Tahoma", Font.BOLD, 12));
        minArr.setBounds(125, 120, 60, 20);
        frame.getContentPane().add(minArr);

        JLabel nrTasks = new JLabel("Number of tasks:");
        nrTasks.setFont(new Font("Tahoma", Font.BOLD, 12));
        nrTasks.setBounds(540, 160, 120, 20);
        frame.getContentPane().add(nrTasks);

        JLabel pr = new JLabel("Processing time:");
        pr.setFont(new Font("Tahoma", Font.BOLD, 12));
        pr.setBounds(20, 160, 100, 20);
        frame.getContentPane().add(pr);

        minProTF = new JTextField();
        minProTF.setBounds(185, 160, 50, 19);
        frame.getContentPane().add(minProTF);
        minProTF.setColumns(10);

        maxProTF = new JTextField();
        maxProTF.setBounds(320, 160, 50, 19);
        frame.getContentPane().add(maxProTF);
        maxProTF.setColumns(10);

        JLabel minP = new JLabel("Minimum:");
        minP.setFont(new Font("Tahoma", Font.BOLD, 12));
        minP.setBounds(125, 160, 60, 20);
        frame.getContentPane().add(minP);

        JLabel maxP = new JLabel("Maximum:");
        maxP.setFont(new Font("Tahoma", Font.BOLD, 12));
        maxP.setBounds(258, 160, 65, 20);
        frame.getContentPane().add(maxP);

        JLabel lblNewLabel_8 = new JLabel("Choose strategy:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_8.setBounds(20, 80, 110, 20);
        frame.getContentPane().add(lblNewLabel_8);

        SelectionPolicy[] sel = new SelectionPolicy[]{SelectionPolicy.SHORTEST_QUEUE, SelectionPolicy.SHORTEST_TIME};
        comboBox = new JComboBox<SelectionPolicy>(sel);
        comboBox.setBounds(130, 80, 170, 21);
        frame.getContentPane().add(comboBox);

        JLabel titlu = new JLabel("QUEUE   SIMULATION");
        titlu.setFont(new Font("Tahoma", Font.BOLD, 20));
        titlu.setBounds(300, 20, 220, 35);
        frame.getContentPane().add(titlu);

        textArea = new JTextArea();
        textArea.setBounds(10, 200, 966, 353);
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        buton = new JButton("START");
        buton.addActionListener(c);
        buton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        buton.setBounds(825, 120, 88, 20);
        frame.getContentPane().add(buton);
/*
        result = new JTextField();
        result.setBounds(10, 200, 950, 350);
        frame.getContentPane().add(result);
        result.setColumns(10);
        */

    }
}

