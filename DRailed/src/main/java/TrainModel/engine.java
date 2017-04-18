package TrainModel;

/**
 * Created by swaroopakkineni on 4/2/17.
 */
public class engine {
    private final double timeStep = .1;
    private static boolean emergencyBrake = false;
    private static boolean serviceBrake = false;

    private static double standardAcceleration = 3.0;
    private static double serviceBrakeAcceleration = -0.9; //https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwivxIiCqa7TAhUs_IMKHZNzBIIQFggjMAA&url=http%3A%2F%2Fwww.m-a.org.uk%2Fwhat_use%2Ftractiveeffortaccelerationandbraking.doc&usg=AFQjCNH-BKLjGwZNeijx5KCWW6SvE8xP8w&sig2=zRvcK25HtDxHsm2VHH9HXg
    private static double emergencyBrakeAcceleration = -2.5;
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
            //double theta = Math.atan(grade);///100); //this is probably why grade is off
            if (powerCommand <= 0) {
                newAcceleration = 0;
            } else if (powerCommand > 0 && currentSpeed == 0) {
                newAcceleration = standardAcceleration;
            } else if (powerCommand > 0 && currentSpeed > 0) {
                newAcceleration = powerCommand / (mass * currentSpeed);
            }

            gravityForce = mass*gravity*Math.sin(grade);
            gravityAcceleration= gravityForce/mass;
            fricationForce = gravityForce*friction;
            frictionAcceleration = Math.abs(fricationForce/mass);


            double totalAcceleration = (newAcceleration + gravityAcceleration + frictionAcceleration);
            //System.out.println(totalAcceleration + " " + newAcceleration + " " + gravityAcceleration + " " + frictionAcceleration);
            currentSpeed += (timeStep * totalAcceleration);// * Math.cos(theta));

            if (currentSpeed < 0)
                currentSpeed = 0;
            if(currentSpeed > 70)
                currentSpeed = 70;
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
