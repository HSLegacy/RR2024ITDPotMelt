package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Pose2d startPose = new Pose2d(37, 58, 0);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        Pose2d bucketPose = new Pose2d(56, startPose.position.y, 180);
        myBot.runAction(myBot.getDrive().actionBuilder(startPose)
                .strafeToLinearHeading(bucketPose.position, bucketPose.heading)
                .strafeToLinearHeading(startPose.position, startPose.heading)
                .strafeTo(new Vector2d(startPose.position.x, 25))
                .strafeTo(new Vector2d(56, 58))
                //second sample
                .strafeTo(new Vector2d(50, 25))
                .strafeTo(bucketPose.position)
                .strafeTo(new Vector2d(60, 25))
                .strafeTo(bucketPose.position)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}