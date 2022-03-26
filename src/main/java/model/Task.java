package model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private final int id;
    private final int arrivalTime;
    private final AtomicInteger serviceTime;

    public Task(int id, int arrivalTime, AtomicInteger serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public AtomicInteger getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "(" + this.getId() + "," +
                this.getArrivalTime() + "," +
                this.getServiceTime().get() + "),";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && arrivalTime == task.arrivalTime && Objects.equals(serviceTime, task.serviceTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrivalTime, serviceTime);
    }
}
