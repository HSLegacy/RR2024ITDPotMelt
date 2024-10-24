package org.firstinspires.ftc.teamcode.auto;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")
public class Net_Auto extends LinearOpMode {
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

    @Override
    public void runOpMode() {

        //ArmActions  Arm = new ArmActions(hardwareMap);

        Pose2d startPose = new Pose2d(37, 63, Math.toRadians(180));
        Pose2d bucketPose = new Pose2d(56, 58, 180);

        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(startPose.position.x, startPose.position.y - 10))
                //pre-loaded sample
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                //first sample
                .strafeToLinearHeading(new Vector2d(37, 25), 0)
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                //second sample
                .strafeToLinearHeading(new Vector2d(50, 25), 0)
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                // third sample
                .strafeToLinearHeading(new Vector2d(60, 25), 0)
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading);


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
