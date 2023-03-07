import java.util.List;

public class ConcreteSTrategyTime implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
        int minTime = Integer.MAX_VALUE;
        int index = 0;
        for(Server s: servers){
           if(s.getWaitingPeriod() < minTime){
                minTime = s.getWaitingPeriod();
                index = servers.indexOf(s);
            }
        }
        for(Server s: servers){
            if(servers.indexOf(s) == index)
            s.addTask(t);
        }
    }
}
