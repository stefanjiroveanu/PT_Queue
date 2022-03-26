package service;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> serverList;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        serverList = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for (int i = 0; i < maxNoServers; i++) {
            Server s = new Server();
            Thread t = new Thread(s);
            serverList.add(s);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task t) {
        strategy.addTask(serverList, t);
    }

    public List<Server> getServerList() {
        return serverList;
    }

    public String listClients() {
        StringBuilder returnedString = new StringBuilder();
        int queueIndex = 0;
        for (Server server : serverList) {
            System.out.println("Queue " + queueIndex + ":");
            returnedString.append("Queue ").append(queueIndex).append(":\n");
            queueIndex++;
            if(server.getTasks().isEmpty()){
                System.out.println("closed");
                returnedString.append("closed\n");
            } else {
                server.getTasks().forEach(client -> {
                    if(client.getServiceTime().get() > 0){
                        System.out.println(client);
                        System.out.println();
                        returnedString.append(client).append("\n");
                        if (server.getTasks().peek() != null &&
                                server.getTasks().peek().equals(client)) {
                            client.getServiceTime().decrementAndGet();
                            server.getWaitingPeriod().decrementAndGet();
                        }
                    } else if(client.getServiceTime().get() == 0){
                        server.getTasks().remove(client);
                    }
                });
            }
        }
        return returnedString.toString();
    }
}
