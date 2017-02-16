package TrainModel;
import java.util.*;
import java.lang.*;

/**
 * Created by swaroopakkineni on 2/14/17.
 */
public class TrainModelMain {
    private ArrayList<Cart> listOfCarts;
    private Double authority;
    private static Double velocity;
    private static Double powerCommand;
    private static Double calculatedAccleration;
    private static Double mass;
    private static Double timeStep;
    private static Double grade;
    private static Double distance;

    public TrainModelMain(){
        powerCommand = 100.0;
        velocity = 0.5;
        //listOfCarts.add(new Cart());
        calculatedAccleration = 0.5;
        mass = 2.0;
        timeStep = 0.001;
        distance = 0.0;
        grade = 0.0;
        System.out.println("Initialized");
       //TimeCalc();
    }
    public TrainModelMain(int numberOfCarts){
        for(int i = 0; i < numberOfCarts; i++)
            listOfCarts.add(new Cart());
    }
    public double TimeCalc() {
        //System.out.println("IN method");
        double acceleration;

        double gradient = Math.atan(grade / 100);
        //int i = 100000;
        //while (i > 0) {
        //while(true){
            if (velocity == 0)
                acceleration = .5;//base case minimum
            else {
                if (powerCommand == 0)
                    acceleration = 0.0;
                else
                    acceleration = powerCommand / (mass * velocity);
            }
            acceleration += calculatedAccleration;
            velocity = velocity + (timeStep * acceleration * Math.cos(gradient));
            if (velocity <= 0.0)
                velocity = 0.0;
            distance = distance + velocity * timeStep;
            System.out.println("Acceleration: " + acceleration +"    Velocity: " + velocity + "    Distance: " + distance);
           // i--;
        //}
        return velocity;
    }

    public TrainModelMain(int numberOfCarts, Double newAuthority, Double newSpeed){
        authority = newAuthority;
        velocity = newSpeed;

        for(int i = 0; i < numberOfCarts; i++)
            listOfCarts.add(new Cart());
    }

    private boolean setTemp(Double temp){
        for(Cart cart : listOfCarts)
            cart.setTemp(temp);
        return true;
    }
    private boolean setAuthority(Double newAuthority){
        authority = newAuthority;
        return true;
    }
    private boolean setSpeed(Double newSpeed){
        velocity = newSpeed;
        return true;
    }


    private class Cart{
        private Double temp;
        private int passengers;
        private boolean lights;

        private Cart(){//initialization if no values are sent
            temp = 70.0; //70 degrees
            lights = true;
            passengers = 0;
        }

        private boolean setTemp(Double newTemp){
            temp = newTemp;
            return true;
        }
    }

}
