import java.util.List;

public class ConcreteSTrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int minQueue = Integer.MAX_VALUE;
        int index = 0;
        for(Server s: servers){
            if(s.getTasks().length < minQueue){
                minQueue = s.getTasks().length;
                index = servers.indexOf(s);
            }
        }
        for(Server s : servers)
            if(servers.indexOf(s) == index)
                servers.get(index).addTask(t);
    }
}
