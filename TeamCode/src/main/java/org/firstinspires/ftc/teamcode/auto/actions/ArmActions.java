package org.firstinspires.ftc.teamcode.auto.actions;
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
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ArmActions {
    private DcMotorEx motor;

    public ArmActions(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
    }

    public Action extendArm(){
            return new Action() {
                private boolean initialized = false;

            @Override
            public boolean run (@NonNull TelemetryPacket packet){
            if (!initialized) {
                motor.setPower(0.8);
                initialized = true;
            }

            double vel = motor.getVelocity();
            packet.put("shooterVelocity", vel);
            return vel < 10_000.0;
            }
        };
    };
    public Action intake(){
        return new Action() {
            private boolean lowPower = false;

            @Override
            public boolean run (@NonNull TelemetryPacket packet){
                if (!lowPower) {
                    motor.setPower(0.8);
                }else{
                    motor.setPower(0.4);
                }

                double vel = motor.getVelocity();
                packet.put("shooterVelocity", vel);
                return vel < 10_000.0;
            }
        };
    };
}


/*
public class ArmActions {
    CRServo sub_extender;
    public ArmActions(HardwareMap hardwareMap) {
        sub_extender = hardwareMap.get(CRServo.class, "sub_extender");
    }
    public class ExtendSubArm implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                sub_extender.setPower(0.8);
                initialized = true;
            }

            packet.put("hello", "sigma");
            return false;
        }
    }
    public class RetractSubArm implements Action {
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                sub_extender.setPower(-0.8);
                initialized = true;
            }

            packet.put("hello", "sigma");
            return false;
        }
    }
    public Action retractSubArm() {
        return new RetractSubArm();
    }
    public Action extendSubArm() {
        return new ExtendSubArm();
    }
}
*/





