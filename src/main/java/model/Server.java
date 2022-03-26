package model;

import model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
@SuppressWarnings("unused")
public class Server implements Runnable{
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private static int queueNumber = 0;
    private final int queueId;
    public Server() {
        queueNumber++;
        this.queueId = queueNumber;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        this.tasks.add(newTask);
        this.waitingPeriod.addAndGet(newTask.getServiceTime().get());
    }
    @Override
    public void run(){
        while(true) {
            if (!tasks.isEmpty()) {
                Task currentTask = this.tasks.peek();
                int processingTime = 0;
                while (waitingPeriod.get() != 0){
                    if (currentTask != null) {
                        processingTime = currentTask.getServiceTime().get();
                    }
                    try {
                        Thread.sleep(processingTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    waitingPeriod.getAndDecrement();
                }
            }
        }
    }

    public BlockingQueue<Task> getTasks(){
        return this.tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
}
