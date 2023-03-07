import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private SimulationFrame view;

    public Controller(SimulationFrame view) {
        this.view = view;
    };

    public void actionPerformed(ActionEvent e){
        int timeLimit = Integer.parseInt(view.timeLimTxt.getText());
        int nrServers = Integer.parseInt(view.nrServersTF.getText());
        int nrTasks = Integer.parseInt(view.nrTasksTF.getText());
        int minArrivalTime = Integer.parseInt(view.minArrTF.getText());
        int maxArrivalTime = Integer.parseInt(view.maxArrTF.getText());
        int minProcessingTime = Integer.parseInt(view.minProTF.getText());
        int maxProcessingTime = Integer.parseInt(view.maxProTF.getText());

        SimulationManager sim = new SimulationManager(timeLimit, maxProcessingTime, minProcessingTime, nrServers, nrTasks, minArrivalTime, maxArrivalTime);
        Thread t = new Thread(sim);
        t.start();
    }


}
