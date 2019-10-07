package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.Scanner;

@Autonomous(name = "GoldAuto",group = "RoverRuckus")
public class Testing extends BaseAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        inithardware(true);

        telemetry.addData("program","initing");
        telemetry.update();


        //Wait for the start button to be pressed
        waitForStart();


    }
}
