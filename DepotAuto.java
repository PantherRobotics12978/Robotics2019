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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@Autonomous

public class DepotAuto extends LinearOpMode {

    private DcMotor motorOne;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    private DcMotor motorFour;
    private DcMotor motorLow;
    private DcMotor motorTop;
    private Servo servoOne;
    private double speed = .9;

    @Override
    public void runOpMode() {
        motorOne  = hardwareMap.get(DcMotor.class, "one");
        motorOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorTwo  = hardwareMap.get(DcMotor.class, "two");
        motorTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorThree  = hardwareMap.get(DcMotor.class, "three");
        motorThree.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFour  = hardwareMap.get(DcMotor.class, "four");
        motorFour.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        motorLow  = hardwareMap.get(DcMotor.class, "cosmo");
        motorTop  = hardwareMap.get(DcMotor.class, "wanda");
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
        
        leftTurn(1000);
        rightTurn(1000);
    }
    
    public void forward(long millis) {
        motorOne.setPower(-speed);
        motorThree.setPower(speed);
        sleep(millis);
        brake();
    }
    
    public void reverse(long millis) {
        motorOne.setPower(speed);
        motorThree.setPower(-speed);
        sleep(millis);
        brake();
    }
    
    public void left(long millis) {
        motorTwo.setPower(-speed);
        motorFour.setPower(speed);
        sleep(millis);
        brake();
    }
    
    public void right(long millis) {
        motorTwo.setPower(speed);
        motorFour.setPower(-speed);
        sleep(millis);
        brake();
    }
    
    public void rightTurn(long millis) {
        motorOne.setPower(-speed);
        motorThree.setPower(-speed);
        motorTwo.setPower(-speed);
        motorFour.setPower(-speed);
        sleep(millis);
        brake();
    }
    
    public void leftTurn(long millis) {
        motorOne.setPower(speed);
        motorThree.setPower(speed);
        motorTwo.setPower(speed);
        motorFour.setPower(speed);
        sleep(millis);
        brake();
    }
    
    public void armUp(double revs) {
        motorLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int revolution = 1440;
            
        motorLow.setTargetPosition((int)(-revolution * revs));
        motorLow.setPower(.60);
        motorLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        while(motorLow.isBusy()) {}   
        
        motorLow.setPower(0);
        
    }
    
    public void armDown(double revs) {
        motorLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int revolution = 1440;
            
        motorLow.setTargetPosition((int)(revolution * revs));
        motorLow.setPower(.60);
        motorLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        while(motorLow.isBusy()) {}   
        
        motorLow.setPower(0);
    
    }
    public void brake() {
        motorOne.setPower(0);
        motorTwo.setPower(0);
        motorThree.setPower(0);
        motorFour.setPower(0);
    }
}
