package com.exadel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author st02
 * 
 */
public class SimpleOperations {
    private static Random random = new Random();

    public static int getRandomValue(final int min, final int max) {
        return min + (int) (random.nextDouble() * (max - min + 1));
    }

    /** generate situation with some chance */
    public static boolean generateChance(final int chance) { //
        boolean temp = false;
        final int randomValue = getRandomValue(1, 100); // 100%
        if (randomValue <= chance) {
            temp = true;
        }
        return temp;
    }

    /** select combination depending on down and left yards */
    public static int select(final int yards, final int down) {

        int combination = 0;

        switch (down) {
        case 1:
            if (generateChance(50)) {
                combination = 1;
            } else {
                combination = 0;
            }
            break;

        case 2:
            if (yards >= 9) {
                if (generateChance(70)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            if (yards > 5 && yards < 9) {
                if (generateChance(50)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            if (yards < 5) {
                if (generateChance(20)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            break;

        case 3:
            if (yards >= 9) {
                if (generateChance(90)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            if (yards > 5 && yards < 9) {
                if (generateChance(70)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            if (yards < 5) {
                if (generateChance(50)) {
                    combination = 1;
                } else {
                    combination = 0;
                }
            }
            break;

        default:
            combination = 0;
            break;
        }
        return combination;

    }

    /**
     * generate devision games for the team. Teams with first number in group
     * play with 2nd team, then with 3rd team and then with 4th team. Teams with
     * second number in group at first play with 1st team, then with 4th team
     * and then with 3rd team.
     */
    public static List<Integer> getDevisionGames(int teamNumber) {
        List<Integer> list = new ArrayList<Integer>();
        final List<Integer> list1 = Arrays.asList(1, 5, 9, 13, 17, 21, 25, 29);
        final List<Integer> list2 = Arrays.asList(2, 6, 10, 14, 18, 22, 26, 30);
        final List<Integer> list3 = Arrays.asList(3, 7, 11, 15, 19, 23, 27, 31);
        final List<Integer> list4 = Arrays.asList(4, 8, 12, 16, 20, 24, 28, 32);

        if (list1.contains(teamNumber)) {
            list.add(teamNumber + 1);
            list.add(teamNumber + 2);
            list.add(teamNumber + 3);

        } else if (list2.contains(teamNumber)) {
            list.add(teamNumber - 1);
            list.add(teamNumber + 3);
            list.add(teamNumber + 1);

        } else if (list3.contains(teamNumber)) {
            list.add(teamNumber + 1);
            list.add(teamNumber - 2);
            list.add(teamNumber - 1);

        } else if (list4.contains(teamNumber)) {
            list.add(teamNumber - 1);
            list.add(teamNumber - 2);
            list.add(teamNumber - 3);
        }
        return list;
    }

    /**
     * generate team's schedule. Each team plays two games with each team from
     * team's devision, six games with teams from team's conference and four
     * games with teams from another conference. In conference games teams from
     * 1st devision play with two teams in 2nd group, then with 2 teams in 3rd
     * group and the with two teams from fourth group. Teams from second group
     * play with 2 teams from 1st group, then with teams from 4th group, and
     * then with teams from 3rd group. In non-conference games each team plays
     * vs team with the same number, then with team with reverse number
     */
    public static List<Integer> getAllGames(int teamNumber) {
        List<Integer> list = new ArrayList<Integer>();

        List<Integer> divisionList = new ArrayList<Integer>();
        divisionList = getDevisionGames(teamNumber);

        List<Integer> nonDivisionGames = new ArrayList<Integer>();

        /* teams from first group play against 2 teams from 2,3 and 4 group */
        /* first team plays against 1 and 4 team in group */
        if (teamNumber == 1 || teamNumber == 17) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 7);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 11);
            nonDivisionGames.add(teamNumber + 12);
            nonDivisionGames.add(teamNumber + 15);
        }
        /* second team plays against 2 and 3 team in group */
        if (teamNumber == 2 || teamNumber == 18) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 5);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 9);
            nonDivisionGames.add(teamNumber + 12);
            nonDivisionGames.add(teamNumber + 13);
        }
        /* third team plays against 3 and 2 team in group */
        if (teamNumber == 3 || teamNumber == 19) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 3);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 7);
            nonDivisionGames.add(teamNumber + 12);
            nonDivisionGames.add(teamNumber + 11);
        }
        /* fourth team plays against 4 and 1 team in group */
        if (teamNumber == 4 || teamNumber == 20) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 1);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 5);
            nonDivisionGames.add(teamNumber + 12);
            nonDivisionGames.add(teamNumber + 9);
        }

        /* teams from second group play against 2 teams from 1,4 and 3 group */
        /*
         * first team plays vs 1,4, second vs 2,3, third vs 3,2, fourth vs 4,1
         * in group
         */
        if (teamNumber == 5 || teamNumber == 21) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 1);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 11);
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 7);
        }
        if (teamNumber == 6 || teamNumber == 22) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 3);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 9);
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 5);
        }
        if (teamNumber == 7 || teamNumber == 23) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 5);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 7);
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 3);

        }
        if (teamNumber == 8 || teamNumber == 24) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 7);
            nonDivisionGames.add(teamNumber + 8);
            nonDivisionGames.add(teamNumber + 5);
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 1);
        }
        /* teams from third group play against 2 teams from 4,1 and 2 group */
        /*
         * first team plays vs 1,4, second vs 2,3, third vs 3,2, fourth vs 4,1
         * in group
         */
        if (teamNumber == 9 || teamNumber == 25) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 7);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 5);
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber - 1);
        }
        if (teamNumber == 10 || teamNumber == 26) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 5);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 7);
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 3);
        }
        if (teamNumber == 11 || teamNumber == 27) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 3);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 9);
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 5);
        }
        if (teamNumber == 12 || teamNumber == 28) {
            nonDivisionGames.add(teamNumber + 4);
            nonDivisionGames.add(teamNumber + 1);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 11);
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 7);
        }
        /* teams from fourth group play against 2 teams from 3,2 and 1 group */
        /*
         * first team plays vs 1,4, second vs 2,3, third vs 3,2, fourth vs 4,1
         * in group
         */
        if (teamNumber == 13 || teamNumber == 29) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 1);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 5);
            nonDivisionGames.add(teamNumber - 12);
            nonDivisionGames.add(teamNumber - 9);
        }
        if (teamNumber == 14 || teamNumber == 30) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 3);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 7);
            nonDivisionGames.add(teamNumber - 12);
            nonDivisionGames.add(teamNumber - 11);
        }
        if (teamNumber == 15 || teamNumber == 31) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 5);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 9);
            nonDivisionGames.add(teamNumber - 12);
            nonDivisionGames.add(teamNumber - 13);
        }
        if (teamNumber == 16 || teamNumber == 32) {
            nonDivisionGames.add(teamNumber - 4);
            nonDivisionGames.add(teamNumber - 7);
            nonDivisionGames.add(teamNumber - 8);
            nonDivisionGames.add(teamNumber - 11);
            nonDivisionGames.add(teamNumber - 12);
            nonDivisionGames.add(teamNumber - 15);
        }
        /* end of getting non-division games */

        list.add(0, divisionList.get(0));
        list.add(1, nonDivisionGames.get(0));
        list.add(2, nonDivisionGames.get(1));
        list.add(3, divisionList.get(1));
        list.add(4, nonDivisionGames.get(2));
        list.add(5, nonDivisionGames.get(3));
        list.add(6, divisionList.get(2));
        list.add(7, nonDivisionGames.get(4));
        list.add(8, nonDivisionGames.get(5));
        list.add(9, divisionList.get(0));
        
        /* if team from 1st conference*/
        if (teamNumber < 17) {
            list.add(10, teamNumber + 16);
            list.add(11, 33 - teamNumber);

        } else { // if team from 2nd conference
            list.add(10, teamNumber - 16);
            list.add(11, 33 - teamNumber);
        }

        list.add(12, divisionList.get(1));

        /* if team from 1st or 2nd group, or from 1st and 2nd group in other conference*/
        if (teamNumber < 9 || (teamNumber > 16 && teamNumber < 25)) {
            list.add(13, 25 - teamNumber);
        } else {
            list.add(13, 41 - teamNumber);
        }

        /* if team from 1st group or from 1st group in other conference*/
        if (teamNumber < 5 || (teamNumber > 16 && teamNumber < 21)) {
            list.add(14, 21 - teamNumber);
        } else if ((teamNumber > 4 && teamNumber < 9)
                || (teamNumber > 20 && teamNumber < 25)) {
            list.add(14, 29 - teamNumber);
        } else if ((teamNumber > 8 && teamNumber < 13)
                || (teamNumber > 24 && teamNumber < 29)) {
            list.add(14, 37 - teamNumber);
        } else {
            list.add(14, 45 - teamNumber);
        }

        list.add(15, divisionList.get(2));

        return list;
    }
}
