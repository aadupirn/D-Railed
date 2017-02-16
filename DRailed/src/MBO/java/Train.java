package MBO.java;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by joero on 2/3/2017.
 */
public class Train {
    public int id;
    public double speed;
    public int auth;
    public double safeSpeed;
    public ArrayList<Station> stations = new ArrayList<Station>();

    private class Station {
        int id = -1;
        String name = "test";
        Time time = new Time(0,0,0);

        public Station(int id, String name, Time time){
            this.id = id;
            this.name = name;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Time getTime() {
            return time;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTime(Time time) {
            this.time = time;
        }
    }

    public Train(int id, double speed, int auth, double safeSpeed){
        this.id = id;
        this.speed = speed;
        this.auth = auth;
        this.safeSpeed = safeSpeed;
    }

    public int getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public int getAuthority() {
        return auth;
    }

    public double getSafeSpeed() {
        return safeSpeed;
    }

    public Boolean isSpeedSafe() {
        return speed < safeSpeed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAuthority (int auth) {
        this.auth = auth;
    }

    public void setSafeSpeed(double safeSpeed) {
        this.safeSpeed = safeSpeed;
    }

    public void addStation(int id, String name, Date date) {
        stations.add(new Station(id, name, new Time(date.getHours(), date.getMinutes(), date.getSeconds())));
    }

    public void clearStations() {
        stations.clear();
    }
}
