package service;

import model.Server;
import model.Task;
import service.Strategy;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> serverList, Task task) {
        int minimumWaitingPeriod = Integer.MAX_VALUE;
        int minimumIndexQueue = 0;
        for(Server server : serverList){
            if(server.getWaitingPeriod().get() < minimumWaitingPeriod) {
                minimumWaitingPeriod = server.getWaitingPeriod().get();
                minimumIndexQueue = serverList.indexOf(server);
            }
        }
        serverList.get(minimumIndexQueue).addTask(task);
    }
}
