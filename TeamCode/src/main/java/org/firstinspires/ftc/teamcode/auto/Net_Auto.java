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
import org.firstinspires.ftc.teamcode.auto.actions.ArmActions;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")
public class Net_Auto extends LinearOpMode {
    HardwareMap hardwareMap;

    ArmActions armActions = new ArmActions(hardwareMap);
    @Override
    public void runOpMode() {

        //ArmActions  Arm = new ArmActions(hardwareMap);

        Pose2d startPose = new Pose2d(37, 63, Math.toRadians(180));
        Pose2d bucketPose = new Pose2d(56, 58, 180);

        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(startPose.position.x, startPose.position.y - 10))
                .stopAndAdd(armActions.runIntake(true))
                //pre-loaded sample
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                //first sample
                .strafeToLinearHeading(new Vector2d(37, 25), 0)
                .stopAndAdd(armActions.runIntake(true))
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                //second sample
                .strafeToLinearHeading(new Vector2d(50, 25), 0)
                .stopAndAdd(armActions.runIntake(true))
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                // third sample
                .strafeToLinearHeading(new Vector2d(60, 25), 0)
                .stopAndAdd(armActions.runIntake(true))
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
