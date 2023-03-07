import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
   private int totalWaitingPeriod = 0;
    boolean finish = false;

    public Server(){
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
        totalWaitingPeriod += newTask.getServiceTime();
    }

    public void finish(){
        finish = true;
    }

    public void run() {
        while (true) {
            if (tasks.peek() != null) {
                try{
                    Task t = new Task(0, 0);
                    t = tasks.peek();
                    int time = t.getServiceTime();
                    while(!finish && time > 0) {
                        Thread.sleep(1000);
                        time--;
                        t.setServiceTime(time);
                    }
                    waitingPeriod.addAndGet(t.getServiceTime() - 1);
                    if(finish)
                        break;
                    tasks.remove();
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
            }
            else if(finish)
                break;
        }
    }
    public Task[] getTasks() {
        Task[] tasksList = new Task[tasks.size()];
        int i = 0;
        for(Task t: tasks) {
            tasksList[i] = t;
        }
        return tasksList;
    }

    public int getNrTasks(){
        return tasks.size();
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }


    public int getTotalWaitingPeriod() {
        return totalWaitingPeriod;
    }

    public void setTotalWaitingPeriod(int totalWaitingPeriod) {
        this.totalWaitingPeriod = totalWaitingPeriod;
    }



    @Override
    public String toString() {
        if(tasks.size() == 0)
            return "close";
        return "tasks=" + tasks;
    }


}
