package MBO.java;

import javafx.beans.property.SimpleStringProperty;

public class TrainRow {
    private final SimpleStringProperty trainId;
    private final SimpleStringProperty station1;
    private final SimpleStringProperty station2;
    private final SimpleStringProperty station3;
    private final SimpleStringProperty station4;
    private final SimpleStringProperty station5;
    private final SimpleStringProperty station6;
    private final SimpleStringProperty station7;
    private final SimpleStringProperty station8;

    public TrainRow(String id, String station1, String station2, String station3, String station4, String station5, String station6, String station7, String station8) {
        this.trainId = new SimpleStringProperty(id);
        this.station1 = new SimpleStringProperty(station1);
        this.station2 = new SimpleStringProperty(station2);
        this.station3 = new SimpleStringProperty(station3);
        this.station4 = new SimpleStringProperty(station4);
        this.station5 = new SimpleStringProperty(station5);
        this.station6 = new SimpleStringProperty(station6);
        this.station7 = new SimpleStringProperty(station7);
        this.station8 = new SimpleStringProperty(station8);
    }

    public String getTrainId() {
        return trainId.get();
    }

    public void setTrainId(String trainId) {
        this.trainId.set(trainId);
    }

    public String getStation1() {
        return station1.get();
    }

    public void setStation1(String station1) {
        this.station1.set(station1);
    }

    public String getStation2() {
        return station2.get();
    }

    public void setStation2(String station2) {
        this.station2.set(station2);
    }

    public String getStation3() {
        return station3.get();
    }

    public void setStation3(String station3) {
        this.station3.set(station3);
    }

    public String getStation4() {
        return station4.get();
    }

    public void setStation4(String station4) {
        this.station4.set(station4);
    }

    public String getStation5() {
        return station5.get();
    }

    public void setStation5(String station5) {
        this.station5.set(station5);
    }

    public String getStation6() {
        return station6.get();
    }

    public void setStation6(String station6) {
        this.station6.set(station6);
    }

    public String getStation7() {
        return station7.get();
    }

    public void setStation7(String station7) {
        this.station7.set(station7);
    }

    public String getStation8() {
        return station8.get();
    }

    public void setStation8(String station8) {
        this.station8.set(station8);
    }
}
