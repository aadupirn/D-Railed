package TrainModel;

/**
 * Created by swaroopakkineni on 4/2/17.
 */
public class engine {
    private final double timeStep = .1;
    private static boolean emergencyBrake = false;
    private static boolean serviceBrake = false;

    private final double standardAcceleration = 3.0;
    private final double serviceBrakeAcceleration = -1.0;
    private final double emergencyBrakeAcceleration = -5.0;
    private double acceleration = 0;
    private final double friction = 0.2;
    private final double gravity = -9.81;

    /*
    * This method calculates speed.
    * Send it mass, powerCommand, current Train speed, and current trade grade
    * Returns back new speed
    */
    protected double calculateSpeed(double mass, double powerCommand, double currentSpeed, double grade){
        if(emergencyBrake)
            return emergencyBrakeSpeedCalculation(mass, powerCommand, currentSpeed, grade);
        else if(serviceBrake)
            return serviceBrakeSpeedCalculation(mass, powerCommand, currentSpeed, grade);
        else {
            double newAcceleration = 0;
            double gravityForce = 0;
            double gravityAcceleration = 0;
            double fricationForce = 0;
            double frictionAcceleration = 0;

            if (grade == 0.0)
                grade = 0.1;
            double theta = Math.atan(grade);///100); //this is probably why grade is off
            if (powerCommand <= 0) {
                newAcceleration = 0;
            } else if (powerCommand > 0 && currentSpeed == 0) {
                newAcceleration = .5;
            } else if (powerCommand > 0 && currentSpeed > 0) {
                newAcceleration = powerCommand / (mass * currentSpeed);
            }

            theta = Math.atan(grade/100);

            gravityForce = mass*gravity*Math.sin(theta);
            gravityAcceleration= gravityForce/mass;
            fricationForce = gravityForce*friction*Math.sin(theta);
            frictionAcceleration = Math.abs(fricationForce/mass);


            double totalAcceleration = (newAcceleration + gravityAcceleration - frictionAcceleration);
            //System.out.println(totalAcceleration + " " + newAcceleration + " " + gravityAcceleration + " " + frictionAcceleration);
            currentSpeed += (timeStep * totalAcceleration);// * Math.cos(theta));
            //System.out.println("total Accel " + totalAcceleration + " " + "Speed " + currentSpeed);

            if (currentSpeed < 0)
                currentSpeed = 0;
            return currentSpeed;
        }
    }

    protected static boolean setEbrake(Boolean bool){
        emergencyBrake = bool;
        return true;
    }

    protected static boolean setSbrake(Boolean bool){
        serviceBrake = bool;
        return true;
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
        if(currentSpeed < 0) {
            currentSpeed = 0;
            emergencyBrake = false;
        }

        return currentSpeed;
    }

    private double serviceBrakeSpeedCalculation(double mass, double powerCommand, double currentSpeed, double grade){
        acceleration += serviceBrakeAcceleration;
        double theta = Math.atan(grade/100);
        double newAcceleration = 0;
        newAcceleration += acceleration;
        currentSpeed += (timeStep * newAcceleration * Math.cos(theta));
        if(currentSpeed < 0) {
            currentSpeed = 0;
            //serviceBrake = false;
        }
        return currentSpeed;
    }
    //// testing
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