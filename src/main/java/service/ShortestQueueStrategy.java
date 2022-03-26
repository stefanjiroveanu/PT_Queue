package service;

import model.Server;
import model.Task;
import service.Strategy;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> serverList, Task task) {
        int minQueueSize = Integer.MAX_VALUE;
        int minQueueIndex = 0;
        for (Server server : serverList){
            if(minQueueSize > server.getTasks().size()){
                minQueueSize = server.getTasks().size();
                minQueueIndex = serverList.indexOf(server);
            }
        }
        serverList.get(minQueueIndex).addTask(task);
    }
}
