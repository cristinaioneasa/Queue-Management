import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients ;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    private Scheduler scheduler;
    private SimulationFrame frame = new SimulationFrame();
    private List<Task> generatedTasks;
    private int minArrivalTime;
    private int maxArrivalTime;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime){
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        generateNRandomTasks();
    }

    private void generateNRandomTasks(){
        generatedTasks = new ArrayList<Task>(numberOfClients);
        for(int i = 0; i< numberOfClients; i++){
            int pT = minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime));
            int aT = minArrivalTime + (int) (Math.random() * (maxArrivalTime  - minArrivalTime ));
            Task t = new Task (aT, pT);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getArrivalTime() - o2.getArrivalTime();
            }
        });
    }

    @Override
    public void run() {
        int currentTime = 0, peak = 0, maxTasksperServer = 0;
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        //frame.getTextArea().append("verificare");
        ArrayList<Integer> time = new ArrayList<>();
        int sum = 0;
        for(Task t: generatedTasks)
            sum += t.getServiceTime();
        float averageServiceTime = sum / generatedTasks.size();

        try {
            FileWriter myWriter = new FileWriter("fisier.txt");
            frame.getTextArea().append("verificare");
        while (currentTime < timeLimit) {
            if (!generatedTasks.isEmpty()) {
                for (int i = 0; i < numberOfServers; i++) {
                    if(generatedTasks.size() > 0)
                    if (currentTime >= generatedTasks.get(0).getArrivalTime()) {
                        scheduler.changeStrategy(selectionPolicy);
                        scheduler.dispatchTask(generatedTasks.get(0));
                        generatedTasks.remove(0);
                    }
                }
            }
            String rez = getResult(currentTime);
            System.out.println(rez);
            //frame.getTextArea().append("verifica");
            frame.getTextArea().append(rez);
            frame.setTextArea(rez);
            //frame.setResult(rez);
            myWriter.write(rez);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Fir intrerupt");
            }

            if (generatedTasks.isEmpty()) {
                int cnt = 0;
                for (Server s : scheduler.getServers()) {
                    if (s.getTasks().length == 0)
                        cnt++;
                }
                if (cnt == numberOfServers)
                    currentTime = timeLimit;
            }
            int suma_cozi = 0;
            for(int j = 0; j< numberOfServers; j++)
                suma_cozi += (scheduler.getServers().get(j).getNrTasks());
            time.add(suma_cozi);
            currentTime++;
        }
        float suma = 0;
        for(Server s: scheduler.getServers())
            suma += s.getTotalWaitingPeriod();
        float avrageWaitingTime = suma / (numberOfServers * currentTime);
        int max = 0;
        for(int k = 0; k < time.size(); k++) {
            if (max < time.get(k))
                max = time.get(k);
        }
        System.out.println("\nAverege waiting time: " + avrageWaitingTime + "\nAverage service time: " + averageServiceTime + "\nPeak hour: " + max);
        myWriter.write("\nAverege waiting time: " + avrageWaitingTime + "\nAverage service time: " + averageServiceTime + "\nPeak hour: " + max);
        myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (Server s : scheduler.getServers()) {
            s.finish();
        }
    }

    private String getResult(int currentTime)
    {

        String result = "\nTime: " +currentTime + "\n" + "Waiting: ";
        for (Task i: generatedTasks)
        {
            result = result + i.toString();
        }
        result = result+ "\n" + scheduler.toString();
        return result;
    }

    public static void main(String[] args){
      // SimulationManager gen = new SimulationManager(15, 4, 3,2,6,2,5);
       //SimulationManager gen = new SimulationManager(60, 4, 2,2,4,2,30);
       SimulationManager gen = new SimulationManager(60, 7, 1,5,50,2,40);
       //SimulationManager gen = new SimulationManager(200, 9, 3,20,1000,10,100);
       Thread t = new Thread(gen);
        t.start();

/*
        try {
            SimulationFrame window = new SimulationFrame();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

}
