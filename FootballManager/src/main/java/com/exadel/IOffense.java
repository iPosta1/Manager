package com.exadel;

import java.util.List;

/**
 * @author st02
 * 
 */
public interface IOffense {
    /**
     * this method selects combination, depending on down, yards until first
     * down, remaining time and difference between points
     * 
     * @param yards
     * @param down
     * @param timeLeft
     * @param pointsDifference
     * @param yardsLeft
     * @return the number of selected combination
     */
    public int selectCombination(final int yards, final int down,
            final int timeLeft, final int pointsDifference, final int yardsLeft);

    /**
     * method simulatePass simulates passing combination, chance of success
     * depends on WR's and CB's parameters pass can be complete, incomplete or
     * intercepted
     * 
     * @param statsWR
     * @param statsCB
     * @param statsQB
     * @return yards, wasted time and turnover status
     */
    public List<String> simulatePass(final List<String> statsWR,
            final List<String> statsCB, final List<String> statsQB);

    /**
     * this method simulates ground attack, number of gain yards depends on
     * running back's parameters and parameters of Defensive line and Offensive
     * line
     * 
     * @param statsRB
     * @param statsDline
     * @param statsOline
     * @return number of yards, wasted time and status of turnover
     */
    public List<String> simulateRush(final List<String> statsRB,
            final List<String> statsDline, final List<String> statsOline);

    /**
     * this method simulates field goal, success of field goal depends on
     * kicker's accuracy and power and field goal range
     * 
     * @param kicker
     * @param yardsLeft
     * @return field goal status and wasted time
     */
    public List<String> simulateFieldGoal(final List<String> kicker,
            final int yardsLeft);

    /**
     * this method simulates field goal
     * 
     * @param kicker
     * @return status of field goal and wasted time
     */
    public List<String> simulateKickOff(final List<String> kicker);

    /**
     * method simulates punt, punt range and returning yards depends on punter's
     * kicking power and accuracy
     * 
     * @param punter
     * @return punt range, returned yards and wasted time
     */
    public List<String> simulatePunt(final List<String> punter);

}