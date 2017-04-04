package TrainModel;

/**
 * Created by swaroopakkineni on 4/2/17.
 */
public class engine {
    private final double timeStep = .1;
    private boolean emergencyBrake = false;
    private final double standardAcceleration = 1.0;
    private final double emergencyBrakeAcceleration = -1.0;
    private double acceleration = 0;


    /*
    * This method calculates speed.
    * Send it mass, powerCommand, current Train speed, and current trade grade
    * Returns back new speed
    */
    protected double calculateSpeed(double mass, double powerCommand, double currentSpeed, double grade){
        if(emergencyBrake)
            return emergencyBrakeSpeedCalculation(mass, powerCommand, currentSpeed, grade);
        double newAcceleration = 0;
        if(grade == 0.0)
            grade = 0.1;
        double theta = Math.atan(grade/100);
        if(powerCommand <= 0 ){
            newAcceleration = 0;
        }
        else if(powerCommand > 0 && currentSpeed == 0){
            newAcceleration = standardAcceleration;
        }
        else if(powerCommand > 0 && currentSpeed > 0){
            newAcceleration  = powerCommand / (mass * currentSpeed);
        }
        newAcceleration += acceleration;
        currentSpeed += (timeStep * newAcceleration * Math.cos(theta));
        if(currentSpeed < 0)
            currentSpeed = 0;
        return currentSpeed;
    }

    /*
    * This method calculates speed if emergency brake is active.
    * Send it mass, powerCommand, current Train speed, and current trade grade
    * Returns back new speed
    */
    private double emergencyBrakeSpeedCalculation(double mass, double powerCommand, double currentSpeed, double grade){
        acceleration += emergencyBrakeAcceleration;
        double theta = Math.atan(grade/100);
        double newAcceleration = 0;
        newAcceleration += acceleration;
        currentSpeed += (timeStep * newAcceleration * Math.cos(theta));
        if(currentSpeed < 0)
            currentSpeed = 0;
        return currentSpeed;
    }

    /*
   * This method activates the emergency brake
   * Turns emergencyBrake true
   * Returns back boolean
   */
    private boolean emergencyBrakeActivated(){
        emergencyBrake = true;
        return true;
    }
}
