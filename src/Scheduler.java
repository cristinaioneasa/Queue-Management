import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTaskPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.maxNoServers=maxNoServers;
        this.maxTaskPerServer=maxTasksPerServer;
        this.servers = new ArrayList<Server>(maxNoServers);
        for(int i = 0; i< maxNoServers; i++){
            servers.add(new Server());
            Thread thread = new Thread(servers.get(i)) ;
            thread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteSTrategyQueue();
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteSTrategyTime();
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server>getServers(){
        return servers;
    }

    public String toString() {
        String rezultat="";
        int index = 1;
        for (Server i: servers) {
            rezultat += "Queue " + index + ": " + i.toString() + "\n";
            index++;
        }
        return rezultat;
    }
}
