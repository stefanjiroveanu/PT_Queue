package service;

import model.Task;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable {
    public Gui gui;
    public JTextArea textArea;
    public int timeLimit = 60;
    public int maxProcessingTime = 4;
    public int minProcessingTime = 2;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 30;
    public int numberOfServers = 2;
    public int numberOfClients = 4;
    public List<Task> taskList = new ArrayList<>();
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;

    public SimulationManager() {
        gui = new Gui();
        gui.addStartButtonActionListener(e -> {
            timeLimit = gui.getSimulationField();
            maxProcessingTime = gui.getMaxServiceField();
            minProcessingTime = gui.getMinServiceField();
            maxArrivalTime = gui.getMaximumArrivalField();
            minArrivalTime = gui.getMinimumArrivalField();
            numberOfClients = gui.getClientField();
            numberOfServers = gui.getQueueField();
            selectionPolicy = SelectionPolicy.valueOf(gui.getComboBox());
            textArea = gui.getTextArea();
            scheduler = new Scheduler(numberOfServers, numberOfClients);
            scheduler.changeStrategy(selectionPolicy);
            Thread t = new Thread(this);
            t.start();
        });
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
    }

    public void generateNRandomTasks(int n) {
        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            int randomArrivalTime = rand.nextInt((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime;
            AtomicInteger randomProcessingTime = new AtomicInteger(
                    rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            Task task = new Task(i, randomArrivalTime, randomProcessingTime);
            taskList.add(task);
        }
        taskList.sort(Comparator.comparingInt(Task::getArrivalTime));
    }

    @Override
    public void run() {
        try {
            BufferedWriter logger = new BufferedWriter(new FileWriter("src/main/resources/logged_events"));
            generateNRandomTasks(numberOfClients);
            double averageTime = avgWaitingTime();
            List<Task> clientsToBeRemoved = new ArrayList<>();
            int currentTime = 0;
            while (currentTime < timeLimit) {
                System.out.println("Time: " + currentTime);
                logger.write("Time: " + currentTime + "\n");
                textArea.append("Time: " + currentTime + "\n");
                System.out.println("Waiting Clients:");
                logger.write("Waiting Clients\n");
                textArea.append("Waiting Clients\n");
                for (Task client : taskList) {
                    if (client.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(client);
                        clientsToBeRemoved.add(client);
                    }
                }
                clientsToBeRemoved.forEach(taskList::remove);
                taskList.forEach(System.out::println);
                for (Task task : taskList) {
                    logger.write(task.toString());
                    logger.write("\n");
                    textArea.append(task.toString());
                    textArea.append("\n");
                }
                if (!taskList.isEmpty()) {
                    System.out.println();
                    logger.write("\n");
                    textArea.append("\n");
                }
                String appended = scheduler.listClients();
                System.out.println();
                logger.write(appended + "\n");
                textArea.append(appended + "\n");
                currentTime++;
                try {
                    sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Average waiting time is :" + averageTime);
            logger.write("Average waiting time is :" + averageTime);
            textArea.append("Average waiting time is :" + averageTime);
            logger.close();
        } catch (IOException exception){
            System.out.println("Could not write in file");
        }

    }

    public double avgWaitingTime(){
        double waitingTime = 0;
        int meanIndex = 0;
        for (Task task : taskList) {
            waitingTime += task.getServiceTime().get();
            meanIndex++;
        }
        return waitingTime/meanIndex;
    }
}
