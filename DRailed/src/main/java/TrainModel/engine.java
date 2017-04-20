package TrainModel;

/**
 * Created by swaroopakkineni on 4/2/17.
 */
public class engine {
    private final double timeStep = .1;
    private static boolean emergencyBrake = false;
    private static boolean serviceBrake = false;

    private static double standardAcceleration = 10.0;
    private static double serviceBrakeAcceleration = -1.2; //https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwivxIiCqa7TAhUs_IMKHZNzBIIQFggjMAA&url=http%3A%2F%2Fwww.m-a.org.uk%2Fwhat_use%2Ftractiveeffortaccelerationandbraking.doc&usg=AFQjCNH-BKLjGwZNeijx5KCWW6SvE8xP8w&sig2=zRvcK25HtDxHsm2VHH9HXg
    private static double emergencyBrakeAcceleration = -1.7;
    private double acceleration = 0;
    private final double friction = 0.1;
    private final double gravity = 9.81;
    private final double pi = 3.14;


    /*
    * This method calculates speed.
    * Send it mass, powerCommand, current Train speed, and current trade grade
    * Returns back new speed
    */
    protected double calculateSpeed(double mass, double power, double speed, double grade){
        double accelerationTotal = 0;

        if(emergencyBrake)
            return emergencyBrakeSpeedCalculation(mass, power, speed, grade);
        else if(serviceBrake)
            return serviceBrakeSpeedCalculation(mass, power, speed, grade);
        else {
            if (power == 0) {
                return 0;
            } else if (power == 0 && speed == 0)
                accelerationTotal = .1;
            else {
                accelerationTotal = power / (mass * speed);

                double forceFriction = mass * gravity * friction;
                double accelFriction = forceFriction / mass;

                accelerationTotal -= accelFriction;
            }
            if (accelerationTotal > .5)
                accelerationTotal = .5;
            if(accelerationTotal < 0)
                accelerationTotal = 0;
            speed = speed + (accelerationTotal * 1);
            if (speed < 0)
                speed = 0;
            return speed;
        }

                /*double angle = (Math.atan(grade / 100));
                double theta = Math.sin(angle);
                System.out.println("Angle   " + angle + " "  + theta);

                double forceFriction = mass * gravity * friction;
                double forceTrain = 0.0;
                if (speed == 0)
                    forceTrain = power / mass;
                else {
                    forceTrain = power / (mass * speed);
                }
                double forceTotal = forceTrain;// - forceFriction;
                double accelerationTotal = forceTotal / mass;
               // System.out.println(forceTotal + " " + forceTrain + " " + forceFriction);
                if (accelerationTotal > .5)
                    accelerationTotal = .5;



                speed = speed;
//                System.out.println("Power " + power + " " + grade + " " + accelerationTotal + " " + forceFriction / mass + " " + forceTrain / mass + " " + speed);
                */



        /*if(powerCommand == 0)
            return 0;
        else {
            double totalAcceleration = 0.0;

            double angle = (Math.atan(grade/ 100));

            double normalForce = (mass * gravity * Math.sin(angle));
            double frictionForce = mass * gravity * friction * Math.cos(angle);

            double actingForce = normalForce + frictionForce;
            double actingAcceleration = actingForce / mass;

            double trainAcceleration = 0;
            if (currentSpeed == 0)
                trainAcceleration = powerCommand / mass;
            else
                trainAcceleration = powerCommand / (mass * currentSpeed);
            trainAcceleration -= actingAcceleration;

            currentSpeed += (trainAcceleration * 1);
            if(currentSpeed > 0 && currentSpeed < 1)
                currentSpeed = 1;
            if (currentSpeed < 0)
                currentSpeed = 0;
            System.out.println("Power " + powerCommand + " "  + grade  + " " + trainAcceleration + " " + normalForce + " " + frictionForce + " " + currentSpeed);

            return currentSpeed;
        }*/







        /*if(emergencyBrake)
            return emergencyBrakeSpeedCalculation(mass, powerCommand, currentSpeed, grade);
        else if(serviceBrake)
            return serviceBrakeSpeedCalculation(mass, powerCommand, currentSpeed, grade);
        else {

            if (powerCommand <= 0) {
                totalAcceleration = 0.0;
            } /*else if (powerCommand > 0 && currentSpeed == 0) {
                System.out.print("b ");
                totalAcceleration = .1;

*               else { //if (powerCommand > 0 && currentSpeed > 0) {
                System.out.print("c ");
                double acceleration = powerCommand / (mass * currentSpeed);

                forceGravity = mass * gravity * Math.sin(grade * 3.14 / 180);
                accelGravity = forceGravity / mass;

                forceFriction = mass * gravity * friction;
                accelFriction = forceFriction / mass;


                totalAcceleration = acceleration - accelGravity - accelFriction;
            }
            //gravity

            //if(totalAcceleration < 0)
            // totalAcceleration = .004;
            currentSpeed += (timeStep * totalAcceleration);
            System.out.println("Power : " + powerCommand + " speed : " + currentSpeed + " totalAcc: " + totalAcceleration + " pwrAccel: " + acceleration
                    + " accelGravity: " + accelGravity + " accelFriction: " + accelFriction);
            if (currentSpeed < 0)
                currentSpeed = 0;
            return currentSpeed;
        }
*/

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

           /* double newAcceleration = 0;
            double gravForce =  mass * gravity * Math.sin(grade * pi /180);
            double frictionForce = (mass * gravity * friction);

            double gravAccel = gravForce/mass;
            double frictionAccel = frictionForce/mass;
            if(frictionAccel < 0 )
                frictionAccel = frictionAccel * -1;
            if(grade < 0)
                gravAccel = gravAccel * -1;*/
////////////////////////////////////////////////////////////////////////////////////
//double theta = Math.sin(grade);

     /* double totalAccelertaion = newAcceleration + ((gravForce - frictionForce)/mass);
            System.out.println(" forces " + grade + " " + totalAccelertaion + " " +  newAcceleration + " " + gravAccel + " " + frictionAccel
            );
            currentSpeed += (timeStep * totalAccelertaion);
            if(currentSpeed < 0)
                currentSpeed = 0;
            return currentSpeed;
            */

//double angle = Math.atan(grade/100);
//angle = -1 * angle;
            /*double normalForce = mass*gravity ;
            gravityForce = normalForce * theta;
            //fricationForce = normalForce*friction;

            gravityAcceleration= gravityForce/mass;
            //frictionAcceleration =  Math.abs(fricationForce/mass);

            double acclerationAndGravity = (newAcceleration + gravityAcceleration);// - frictionAcceleration);
            double acclerationfriction = gravityAcceleration *friction;
            double totalAcceleration = acclerationAndGravity - acclerationfriction;

            if(totalAcceleration > 5.0)
                totalAcceleration = 5.0;
            //System.out.println(totalAcceleration + " " + newAcceleration + " " + gravityAcceleration + " " + frictionAcceleration);
            currentSpeed += (timeStep * totalAcceleration);// * Math.cos(theta));
            System.out.println("Accelerataion " + totalAcceleration + " " + newAcceleration + " " + gravityAcceleration + " " +  frictionAcceleration);
            System.out.println(grade + " " + theta + " " + currentSpeed + " " + mass);

            if (currentSpeed < 0)
                currentSpeed = 0;
            if(currentSpeed > 70)
                currentSpeed = 70;
            return currentSpeed;*/