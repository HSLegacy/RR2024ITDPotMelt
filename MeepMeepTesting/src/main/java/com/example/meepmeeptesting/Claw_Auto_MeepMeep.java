package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import java.lang.Math;
public class Claw_Auto_MeepMeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


        Pose2d startPose = new Pose2d(-16, 63, Math.toRadians(90));
        Pose2d subPoseMid = new Pose2d(0, 35, Math.toRadians(90));
        Pose2d spikeRight = new Pose2d(-50, 35, Math.toRadians(90));
        Pose2d subStation = new Pose2d(-70, 70, Math.toRadians(-90));

        myBot.runAction(myBot.getDrive().actionBuilder(startPose)
                //preload
                .strafeTo(new Vector2d(subPoseMid.position.x - 10, subPoseMid.position.y - 5))
                .strafeTo(new Vector2d(subPoseMid.position.x - 10, subPoseMid.position.y))

                // right spike mark
                .strafeTo(new Vector2d(spikeRight.position.x, spikeRight.position.y))
                .strafeToLinearHeading(new Vector2d(subStation.position.x, subStation.position.y), subStation.heading)
                .strafeToLinearHeading(new Vector2d(subPoseMid.position.x - 5, subPoseMid.position.y), subPoseMid.heading)
                .strafeTo(new Vector2d(subPoseMid.position.x - 5, subPoseMid.position.y - 5))
                .strafeTo(new Vector2d(subPoseMid.position.x - 5, subPoseMid.position.y))

                //middle spike mark
                .strafeTo(new Vector2d(spikeRight.position.x - 10, spikeRight.position.y))
                .strafeToLinearHeading(new Vector2d(subStation.position.x, subStation.position.y), subStation.heading)
                .strafeToLinearHeading(new Vector2d(subPoseMid.position.x, subPoseMid.position.y), subPoseMid.heading)
                .strafeTo(new Vector2d(subPoseMid.position.x, subPoseMid.position.y - 5))
                .strafeTo(new Vector2d(subPoseMid.position.x, subPoseMid.position.y))

                //left spike mark
                .strafeTo(new Vector2d(spikeRight.position.x - 20, spikeRight.position.y))
                .strafeToLinearHeading(new Vector2d(subStation.position.x, subStation.position.y), subStation.heading)
                .strafeToLinearHeading(new Vector2d(subPoseMid.position.x + 10, subPoseMid.position.y), subPoseMid.heading)
                .strafeTo(new Vector2d(subPoseMid.position.x + 10, subPoseMid.position.y - 5))
                .strafeTo(new Vector2d(subPoseMid.position.x + 10, subPoseMid.position.y))




                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}