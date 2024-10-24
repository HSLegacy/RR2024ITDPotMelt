package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import java.lang.Math;
public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


        Pose2d startPose = new Pose2d(37, 63, Math.toRadians(180));
        Pose2d bucketPose = new Pose2d(56, 58, 180);

        myBot.runAction(myBot.getDrive().actionBuilder(startPose)
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
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}