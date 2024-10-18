package org.firstinspires.ftc.teamcode.auto;
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

import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")
public class testAuto extends LinearOpMode {

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


    @Override
    public void runOpMode() {

        ArmActions  Arm = new ArmActions(hardwareMap);

        Pose2d initialPose = new Pose2d(37, 58, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .lineToX(56)
                .lineToX(initialPose.position.x)
                .strafeTo(new Vector2d(initialPose.position.x, 25));


        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.update();
        }

        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;

        trajectoryActionChosen = tab1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen

                )
        );
    }
}
