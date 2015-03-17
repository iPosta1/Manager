package com.exadel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author st02
 * 
 */
public class Offense implements IOffense {

    private static final Logger LOG = Logger.getLogger(Offense.class.getName()); // for
                                                                                 // logging

    /**
     * 
     * @see com.exadel.IOffense#selectCombination(int, int, int, int, int)
     */
    public int selectCombination(final int yards, final int down,
            final int timeLeft, final int pointsDifference, final int yardsLeft) {
        /* yards - unlitl first down, yardsLeft - yards until end zone */

        /* 0 - rush, 1 - pass, 2 - punt, 3 - field goal */
        int combinationType = 0;

        if (down == 4) {
            /* if team loses with more than 3 points, filed goal is not allowed */
            if (pointsDifference < -3 && timeLeft <= 3) {
                combinationType = 1;
            }

            /* if team loses fewer than 3 points, but is's not field goal range */
            if (pointsDifference > -4 && pointsDifference < 0 && timeLeft <= 3
                    && yardsLeft >= 50) {
                combinationType = 1;
            }

            /* if team loses fewer than 3 points, and it's field goal range */
            if (pointsDifference > -4 && pointsDifference < 0 && timeLeft <= 3
                    && yardsLeft < 50) {
                combinationType = 3;
            }

            /*
             * if there is more than 3 minutes to go, team has enough time to
             * gain some points
             */
            if (yardsLeft >= 50 && timeLeft > 3) {
                combinationType = 2;
            }

            /* if team on filed goal range and needs less than 3 points */
            if (pointsDifference > -4 && yardsLeft < 50) {
                combinationType = 3;
            }

            /* normal situation on 4th down */
            if (pointsDifference > -1 && yardsLeft >= 50) {
                combinationType = 2;
            }

            if (timeLeft > 3 && yardsLeft < 50) {
                combinationType = 3;
            }
        } else {
            /* select combination on 1,2 and 3 down */
            combinationType = SimpleOperations.select(yards, down);
        }

        return combinationType;

    }

    /**
     * @see com.exadel.IOffense#simulatePass(java.util.List, java.util.List,
     *      java.util.List)
     */
    public List<String> simulatePass(final List<String> statsWR,
            final List<String> statsCB, final List<String> statsQB) {

        List<String> passResult = new ArrayList<String>();
        // set empty elements to the list
        /* 0 complete\incomplete, 1 yards , 2 time left(seconds), 3 turnover */
        passResult.add(0, "");
        passResult.add(1, "");
        passResult.add(2, "");
        passResult.add(3, "");

        try {
            /* wide reciver params */
            int speedWR;
            int agilityWR;
            int catchingWR;
            int jumpingWR;
            /* Corner back params */
            int speedCB;
            int agilityCB;
            int catchingCB;
            int jumpingCB;
            /* Quarterback params */
            int throwAccuracy;
            int throwPower;

            /* taking WR parameters */
            speedWR = Integer.parseInt(statsWR.get(0));
            agilityWR = Integer.parseInt(statsWR.get(1));
            catchingWR = Integer.parseInt(statsWR.get(2));
            jumpingWR = Integer.parseInt(statsWR.get(3));

            /* taking CB parameters */
            speedCB = Integer.parseInt(statsCB.get(0));
            agilityCB = Integer.parseInt(statsCB.get(1));
            catchingCB = Integer.parseInt(statsCB.get(2));
            jumpingCB = Integer.parseInt(statsCB.get(3));

            /* taking QB parameters */
            throwAccuracy = Integer.parseInt(statsQB.get(0));
            throwPower = Integer.parseInt(statsQB.get(1));

            // checking pass, complete or incomplete
            /* ratio - difference between WR stats and CB stats */
            final double ratio = (double) (speedWR + agilityWR + jumpingWR)
                    / (speedCB + agilityCB + jumpingCB);

            /* ratio - the most important value, so *100 */
            final int chance = (int) (((throwAccuracy + throwPower) / 2
                    + catchingWR + ratio * 100) / 4);

            /* generate event */
            final boolean complitionResult = SimpleOperations
                    .generateChance(chance);

            if (complitionResult) { // if complete
                /* getting passing yards */
                final int passingYards = (int) (SimpleOperations
                        .getRandomValue(1, 15) * (speedWR + agilityWR) / 2 * 0.01)
                        + SimpleOperations.getRandomValue(1, 10);
                passResult.set(0, "complete");
                passResult.set(1, Integer.toString(passingYards));
                passResult.set(3, "false");

            } else { // if incomplete
                /* checking for interception */
                final int intercepChance = (int) (SimpleOperations
                        .getRandomValue(1, 7) * catchingCB * 0.1);
                /* generate event */
                final boolean intercepResult = SimpleOperations
                        .generateChance(intercepChance);

                if (intercepResult) { // if intercepted
                    final int intercepYards = SimpleOperations.getRandomValue(
                            1, 20);
                    passResult.set(0, "incomplete");
                    passResult.set(1, Integer.toString(intercepYards));
                    passResult.set(3, "true");
                } else { // if no interception
                    passResult.set(0, "incomplete");
                    passResult.set(1, "0");
                    passResult.set(3, "false");
                }
            }
            /* generate wasted time (seconds) */
            final int seconds = SimpleOperations.getRandomValue(3, 15);

            passResult.set(2, Integer.toString(seconds));// set time

        } catch (NumberFormatException e) {
            LOG.log(Level.SEVERE, "Exception ", e);
        }

        return passResult;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.exadel.IOffense#simulateRush(java.util.List, java.util.List,
     *      java.util.List)
     */
    public List<String> simulateRush(final List<String> statsRB,
            final List<String> statsDline, final List<String> statsOline) {
        List<String> rushResult = new ArrayList<String>();

        // add empty elements to the list
        /* 0 - rushing yards, 1 - wasted time, 2 - turnover status */
        rushResult.add(0, "");
        rushResult.add(1, "");
        rushResult.add(2, "");

        try {
            /* RB - running back */
            int speedRB;
            int agilityRB;
            int carryingRB;
            int breakTackleRB;
            /* DL - Defensive Line */
            int tackleDL;
            int strengthDL;
            int agilityDL;
            /* OL - Offensive Line */
            int tackleOL;
            int strengthOL;
            int agilityOL;

            /* taking RB parameters */
            speedRB = Integer.parseInt(statsRB.get(0));
            agilityRB = Integer.parseInt(statsRB.get(1));
            carryingRB = Integer.parseInt(statsRB.get(2));
            breakTackleRB = Integer.parseInt(statsRB.get(3));

            /* taking defensive line parameters */
            tackleDL = Integer.parseInt(statsDline.get(0));
            strengthDL = Integer.parseInt(statsDline.get(1));
            agilityDL = Integer.parseInt(statsDline.get(2));

            /* taking offensive line parameters */
            tackleOL = Integer.parseInt(statsOline.get(0));
            strengthOL = Integer.parseInt(statsOline.get(1));
            agilityOL = Integer.parseInt(statsOline.get(2));

            /* get ratio between offensive line and defensive file */
            final double ratio = (double) (tackleOL + strengthOL + agilityOL)
                    / (tackleDL + strengthDL + agilityDL);

            /* get random yards, depending on RB parameters and ratio */
            int yards = (int) ((ratio * 100 + speedRB + agilityRB + carryingRB + breakTackleRB)
                    / 6 * 0.1 + SimpleOperations.getRandomValue(0, 7) - SimpleOperations
                    .getRandomValue(0, 10));

            /* generate additional yards */
            if (SimpleOperations.generateChance(50)) {// chance 50% to gain
                                                      // additional yards
                yards = +SimpleOperations.getRandomValue(0, 7);
            }

            /* generate chance of fumble, depending on RB carrying */
            if (SimpleOperations
                    .generateChance((int) ((100 - carryingRB) * 0.1))) { // if
                                                                         // fumble
                /* set fumble return yards */
                rushResult.set(0, Integer.toString(SimpleOperations
                        .getRandomValue(1, 20)));

                /* set wasted time */
                rushResult.set(1, Integer.toString(SimpleOperations
                        .getRandomValue(3, 10)));

                /* set turnover status */
                rushResult.set(2, "true"); // set turnover status
            } else { // if no fumble
                /* set rush yards */
                rushResult.set(0, Integer.toString(yards));

                /* set wasted time */
                rushResult.set(1, Integer.toString(SimpleOperations
                        .getRandomValue(3, 10)));

                /* set turnover status */
                rushResult.set(2, "false");
            }
        } catch (NumberFormatException e) {
            LOG.log(Level.SEVERE, "Exception:", e);
        }
        return rushResult;
    }

    /**
     * @see com.exadel.IOffense#simulateFieldGoal(java.util.List, int)
     */
    public List<String> simulateFieldGoal(final List<String> kicker,
            final int yardsLeft) {
        List<String> kickResult = new ArrayList<String>();
        // add empty elements to the list
        /* 0 - success or not, 1 - wasted time */
        kickResult.add(0, "");
        kickResult.add(1, "");

        try {

            int kickingAccuracy;
            int kickingPower;

            /* getting kicker's parameters */
            kickingAccuracy = Integer.parseInt(kicker.get(0));
            kickingPower = Integer.parseInt(kicker.get(1));

            /*
             * get kick chance, depending on kicker's accuracy, power and field
             * goal range
             */
            final boolean fieldGoal = SimpleOperations
                    .generateChance(((100 - yardsLeft) * 4 + kickingAccuracy + kickingPower) / 6);

            if (fieldGoal) {
                kickResult.set(0, "true");
            } else {
                kickResult.set(0, "false");
                /* field goal takes 3-5 seconds */
                kickResult
                        .set(1, Integer.toString(SimpleOperations
                                .getRandomValue(3, 5)));
            }
        } catch (NumberFormatException e) {
            LOG.log(Level.SEVERE, "Exception:", e);
        }
        return kickResult;
    }

    /**
     * @see com.exadel.IOffense#simulateKickOff(java.util.List)
     */
    public List<String> simulateKickOff(final List<String> kicker) {

        List<String> kickOffResult = new ArrayList<String>();
        /* 0 - return yards, 1 - wasted time */
        kickOffResult.add(0, "");
        kickOffResult.add(1, "");

        try {

            int kickingAccuracy;
            int kickingPower;

            /* getting kicker's parameters */
            kickingAccuracy = Integer.parseInt(kicker.get(0));
            kickingPower = Integer.parseInt(kicker.get(1));

            /* get returning yards, depending on kickers accuracy and power */
            final int yards = (int) ((100 - (kickingAccuracy + kickingPower) / 2) * 0.1 + SimpleOperations
                    .getRandomValue(10, 30));
            kickOffResult.set(0, Integer.toString(yards));

            /* set time */
            kickOffResult.set(1,
                    Integer.toString(SimpleOperations.getRandomValue(4, 15)));

        } catch (NumberFormatException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return kickOffResult;

    }

    /**
     * @see com.exadel.IOffense#simulatePunt(java.util.List)
     */
    public List<String> simulatePunt(final List<String> punter) {
        List<String> puntResult = new ArrayList<String>();
        // add empty elements to the list
        /* 0 - return yards, 1 - wasted seconds, 2 - kick range */
        puntResult.add(0, "");
        puntResult.add(1, "");
        puntResult.add(2, "");

        try {
            int kickingAccuracy;
            int kickingPower;

            /* getting punter's parameters */
            kickingAccuracy = Integer.parseInt(punter.get(0));
            kickingPower = Integer.parseInt(punter.get(1));

            /* generate return yards */
            final int returnYards = (int) ((100 - (kickingAccuracy + kickingPower) / 2) * 0.1 + SimpleOperations
                    .getRandomValue(0, 5));
            puntResult.set(0, Integer.toString(returnYards));

            /* set return yards */
            puntResult.set(1,
                    Integer.toString(SimpleOperations.getRandomValue(4, 15)));

            /* punt length depends on punter's accuracy and power*/
            final int puntLength = (int) ((kickingAccuracy + kickingPower) / 2)
                    - SimpleOperations.getRandomValue(20, 40);

            puntResult.set(2, Integer.toString(puntLength));
        } catch (NumberFormatException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        return puntResult;
    }

}
