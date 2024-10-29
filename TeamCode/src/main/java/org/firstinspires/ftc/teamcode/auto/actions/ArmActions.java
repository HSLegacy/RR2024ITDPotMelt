package org.firstinspires.ftc.teamcode.auto.actions;
import android.content.pm.LauncherApps;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class ArmActions {
    private DcMotorEx leftSlide;
    private DcMotorEx rightSlide;
    private CRServo intake;
    private Servo intakePivot;
    private Servo bucketPivot;

    public ArmActions(HardwareMap hardwareMap) {
        leftSlide = hardwareMap.get(DcMotorEx.class, "left_slide");
        rightSlide = hardwareMap.get(DcMotorEx.class, "right_slide");
        intake = hardwareMap.get(CRServo.class, "intake");
        intakePivot = hardwareMap.get(Servo.class, "intake_pivot");
        bucketPivot = hardwareMap.get(Servo.class, "bucket_pivot");
    }

    public Action extendArm(){
            return new Action() {
                private boolean initialized = false;

            @Override
            public boolean run (@NonNull TelemetryPacket packet){
            if (!initialized) {
                leftSlide.setPower(0.8);
                initialized = true;
            }

            double vel = leftSlide.getVelocity();
            packet.put("shooterVelocity", vel);
            return vel < 10_000.0;
            }
        };
    };
    public Action runIntake(boolean slow){
        return new Action() {
            boolean initalized = false;
            @Override
            public boolean run (@NonNull TelemetryPacket packet){
                if (!slow) {
                    intake.setPower(0.8);
                }else{
                    intake.setPower(0.2);
                }
                intakePivot.setPosition(10);
                return initalized;
            }
        };
    };
    public Action runOutake(){
        return new Action() {
            private boolean initialized;
            @Override
            public boolean run (@NonNull TelemetryPacket packet){
                if (!initialized) {
                    bucketPivot.setPosition(1);
                    intakePivot.setPosition(1);
                    intake.setPower(-0.8);
                    while(leftSlide.getCurrentPosition() < 500){
                        leftSlide.setPower(1);
                    }
                    leftSlide.setPower(0);
                    bucketPivot.setPosition(0);
                    while(leftSlide.getCurrentPosition() > 500){
                        leftSlide.setPower(-0.2);
                    }
                    bucketPivot.setPosition(1);

                }else{
                    intake.setPower(0.2);
                }

                return initialized;
            }
        };
    };
}
