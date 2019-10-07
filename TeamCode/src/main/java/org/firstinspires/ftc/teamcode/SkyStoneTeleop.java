/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import java.io.File;

import static org.firstinspires.ftc.teamcode.HardwareSkyStone.TeleOpRunMode;


/**
 * This file provides basic Telop driving for a RoverRuckus robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common RoverRuckus hardware class to define the devices on the robot.
 * All device access is managed through the HardwareRoverRuckus class.
 *
 */

@TeleOp(name="RoverRuckus: TeleOp Tank", group="RoverRuckus")
public class SkyStoneTeleop extends OpMode{


    // declaring variables

    double liftPower = 0;

    float slidemultiplier = 0.95f;

    float bucketLimiter = 1f;

    boolean pastStateX;
    boolean pastStateY;

    boolean pastNani;
    boolean pastOof;
    boolean pastBruh;
    boolean pastXp;

    boolean spinX;
    boolean spinY;

    boolean startTheDrop;
    int raiserAngle;

    Integer gyroAngle;



    MecanumDriveTrain vroom;


    private ElapsedTime runtime = new ElapsedTime();

    private String soundPath = "/FIRST/blocks";
    private File naniFile   = new File( "/sdcard" + soundPath + "/nanigood.mp3");
    private File oofFile   = new File(  "/sdcard" + soundPath + "/oof.mp3");
    private File bruhFile   = new File(  "/sdcard" + soundPath + "/bruh.mp3");
    private File xpFile   = new File(  "/sdcard" + soundPath + "/xp.mp3");
    boolean naniFound;
    boolean bruhfound;
    boolean ooffound;
    boolean xpfound;


    /* Declare OpMode members. */
    HardwareSkyStone robot       = new HardwareSkyStone(true); // use the class created to define a RoverRuckus's hardware


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */


        robot.init(hardwareMap);
        robot.setMode(TeleOpRunMode);

        // initializing the variables


        pastStateX = false;
        pastStateY = false;

        pastBruh = false;
        pastNani = false;
        pastOof = false;
        pastXp = false;

        gyroAngle = null;

        spinX = false;
        spinY = false;
        startTheDrop = false;
        naniFound = naniFile.exists();
        ooffound = oofFile.exists();
        bruhfound = bruhFile.exists();
        xpfound = xpFile.exists();

        vroom = new MecanumDriveTrain(robot, gamepad1,telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Haddi", "Haddi");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {



        //Mecanum Drivetrain function to set powers
        vroom.loop();


        // Press gamepad 1 x to initiate water game
        if ((gamepad1.dpad_left  || gamepad2.dpad_left) && !pastNani){
            if (naniFound){
                SoundPlayer.getInstance().stopPlayingAll();
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, naniFile);
                telemetry.addData("sound playing","yess");
                telemetry.update();
            }else{
                telemetry.addData("sound not found","yoo");
                telemetry.update();
            }
        }
        pastNani = gamepad1.dpad_left || gamepad2.dpad_left;

        if ((gamepad1.dpad_down  || gamepad2.dpad_down) && !pastOof ){
            if (ooffound){
                SoundPlayer.getInstance().stopPlayingAll();
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, oofFile);
            }

        }
        pastOof = gamepad1.dpad_down || gamepad2.dpad_down;

        if ((gamepad2.dpad_right || gamepad1.dpad_right) && !pastBruh){
            if (bruhfound){
                SoundPlayer.getInstance().stopPlayingAll();
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, bruhFile);
            }
        }
        pastBruh = gamepad1.dpad_right || gamepad2.dpad_right;

        if ((gamepad2.dpad_up || gamepad1.dpad_up) && !pastXp){
            if (xpfound){
                SoundPlayer.getInstance().stopPlayingAll();
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, xpFile);
            }
        }
        pastXp = gamepad1.dpad_up || gamepad2.dpad_up;





        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}