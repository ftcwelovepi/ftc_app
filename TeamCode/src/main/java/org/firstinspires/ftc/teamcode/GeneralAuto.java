package org.firstinspires.ftc.roverruckus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "GeneralAuto",group = "SkyStone")
public class GeneralAuto extends BaseAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize the hardware using BaseAutonomous Function
        inithardware(false);

        telemetry.addData("program","initing");
        telemetry.update();


        //Wait for the start button to be pressed
        waitForStart();
        String location = getMineralLocation();
        telemetry.addData("location",location);
        telemetry.update();
        dropEncoder(1);
        encoderMecanumDrive(DRIVE_SPEED,15,5,1,0);

        dumbencoderMecanumDrive(DRIVE_SPEED,40,5,0,1);


        if (location.equals("Left")){
            encoderMecanumDrive(DRIVE_SPEED,55,10,-1,0);
        }else if (location.equals("Right")){
            encoderMecanumDrive(DRIVE_SPEED,30,10,1,0);
        }else{
            encoderMecanumDrive(DRIVE_SPEED,15,5,-1,0);
        }

        dumbencoderMecanumDrive(DRIVE_SPEED,65,5,0,1);
        if (location.equals("Left")){
            encoderMecanumDrive(DRIVE_SPEED,40,10,1,0);
        }else if (location.equals("Right")){
            encoderMecanumDrive(DRIVE_SPEED,45,10,-1,0);
        }

        dumbencoderMecanumDrive(DRIVE_SPEED,20,5,0,1);
        gyroTurn(TURBO_SPEED,135);
        encoderMecanumDrive(0.4,50,5,0,-1);
        encoderMecanumDrive(DRIVE_SPEED,20,5,0,1);
        sleep(1000);
        robot.markerDropper.setPosition(0);
        sleep(1000);
        encoderMecanumDrive(DRIVE_SPEED,40,5,0,1);
        encoderMecanumDrive(0.4,50,5,1,0);
        encoderMecanumDrive(0.4,5,5,-1,0);

        dumbencoderMecanumDrive(DRIVE_SPEED,120,10,0,1);
        parkInCrater();

    }
}
