// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutonomousCommand extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    Drivetrain m_subsystem;
    private Encoder encoderL;
    private Encoder encoderR;
    private String state;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutonomousCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_subsystem = new Drivetrain();
        encoderL = m_subsystem.getEncoders(0);
        encoderR = m_subsystem.getEncoders(1);
        //addRequirements(m_subsystem);    

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        String state = "forward";

    }
  
    // Drives forward
    public void forward(){
        m_subsystem.Drive(0, .5);
        m_subsystem.Drive(1, .5);
    }
    // Turns
    public void turn(){
        m_subsystem.Drive(0, .5);
        m_subsystem.Drive(1, -.5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        
        if (state.equals("forward"))
        {
            if (encoderL.getDistance()>=5){
                state="turning";
                encoderL.reset();
            } else {

                this.forward();
            }
        }
        else if ( state.equals("turning"))
        {
            if (encoderL.getDistance()>=(.5*3.14159*Constants.wheelRadius)){
                state="backwards";
                encoderL.reset();
            } else {

                this.turn();
            }
            // CHECK IF TURNED DEGREES IS >= 180
        }
        else if ( state.equals("backwards"))
        {
            if (encoderL.getDistance()>=5) { //
                state="stopped";
                encoderL.reset();
            } else {

                this.forward();
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}