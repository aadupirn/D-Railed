package MBO.java;

import javafx.beans.property.SimpleStringProperty;

public class WorkerRow {
    private final SimpleStringProperty workerId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty schedule;

    public WorkerRow(String workerId, String name, String schedule) {
        this.workerId = new SimpleStringProperty(workerId);
        this.name = new SimpleStringProperty(name);
        this.schedule= new SimpleStringProperty(schedule);
    }

    public String getWorkerId() {
        return workerId.get();
    }

    public void setWorkerId(String workerId) {
        this.workerId.set(workerId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSchedule() {
        return schedule.get();
    }

    public void setSchedule(String schedule) {
        this.schedule.set(schedule);
    }
}
