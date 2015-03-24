package com.exadel.operations;

import java.util.ArrayList;
import java.util.List;

import com.exadel.orm.Leagues;
import com.exadel.orm.Lplayers;
import com.exadel.orm.Roster;
import com.exadel.services.ILplayersServices;

public class DraftOperations {

    public static Lplayers playerToDraft(List<Roster> teamroster, Leagues league,
            ILplayersServices lpservice) {
        Lplayers player = new Lplayers();

        /*----------------round 1 QB RB------------*/
        if (teamroster.size() == 0) {
            // select QB or HB
            List<String> positions = new ArrayList<String>();
            positions.add("QB");
            positions.add("RB");
            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }
        /*--------------round 2 DE DT---------------*/
        if (teamroster.size() == 1) {
            // select DE or DT
            List<String> positions = new ArrayList<String>();
            if (playersOnPositionCount("DE", teamroster) == 0) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) == 0) {
                positions.add("DT");
            }
            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 3 WR CB----------------- */
        if (teamroster.size() == 2) {
            // select WR or CB
            List<String> positions = new ArrayList<String>();
            if (playersOnPositionCount("WR", teamroster) == 0) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) == 0) {
                positions.add("CB");
            }
            // if player picked WR and CB in first 2 rounds - pick QB or RB or
            // DE or DT
            if (playersOnPositionCount("CB", teamroster) != 0
                    && playersOnPositionCount("WR", teamroster) != 0) {
                positions.add("QB");
                positions.add("RB");
                positions.add("DE");
                positions.add("DT");
            }

            player = lpservice.playerToDraft(positions, league);// get top
                                                                // player by
                                                                // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 4 WR CB----------------- */
        if (teamroster.size() == 3) {
            // select WR or CB
            List<String> positions = new ArrayList<String>();
            if (playersOnPositionCount("WR", teamroster) == 0) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) == 0) {
                positions.add("CB");
            }
            // if CB and WR picked - pick DE DT QB RB
            if (playersOnPositionCount("CB", teamroster) != 0
                    && playersOnPositionCount("WR", teamroster) != 0) {
                if (playersOnPositionCount("DE", teamroster) == 0) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) == 0) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 5 OT----------------- */
        if (teamroster.size() == 4) {
            // select OT
            List<String> positions = new ArrayList<String>();
            if (playersOnPositionCount("OT", teamroster) == 0) {
                positions.add("OT");
            } else { // if OT picked
                if (playersOnPositionCount("DE", teamroster) == 0) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) == 0) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 6 MLB OLB SS FS----------------- */
        if (teamroster.size() == 5) {
            // select LB FS SS
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
                check++;
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
                check++;
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
                check++;
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another
                if (playersOnPositionCount("DE", teamroster) == 0) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) == 0) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 7 MLB OLB SS FS----------------- */
        if (teamroster.size() == 6) {
            // select MLB OLB SS FS
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
                check++;
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
                check++;
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
                check++;
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another
                if (playersOnPositionCount("DE", teamroster) == 0) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) == 0) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 8 MLB OLB SS FS----------------- */
        if (teamroster.size() == 7) {
            // select MLB OLB SS FS
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
                check++;
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
                check++;
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
                check++;
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another
                if (playersOnPositionCount("DE", teamroster) == 0) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) == 0) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }

            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 9 OG DE DT----------------- */
        if (teamroster.size() == 8) {
            // select OG or second DE DT
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("OG", teamroster) == 0) {
                positions.add("OG");
                check++;
            }
            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) == 0) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 10 second DE DT----------------- */
        if (teamroster.size() == 9) {
            // select second DE DT
            List<String> positions = new ArrayList<String>();
            int check = 0;

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) == 0) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 11 second DE DT----------------- */
        if (teamroster.size() == 10) {
            // select second DE DT
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
            }

            if (playersOnPositionCount("QB", teamroster) == 0) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) == 0) {
                positions.add("RB");
            }
            if (playersOnPositionCount("WR", teamroster) == 0) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) == 0) {
                positions.add("CB");
            }
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
            }
            if (playersOnPositionCount("OG", teamroster) == 0) {
                positions.add("OG");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 12 TE----------------- */
        if (teamroster.size() == 11) {
            // select TE
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("TE", teamroster) == 0) {
                positions.add("TE");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) == 0) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) == 0) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) == 0) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 13 all----------------- */
        if (teamroster.size() == 12) {
            // select null position
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
            }

            if (playersOnPositionCount("QB", teamroster) == 0) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) == 0) {
                positions.add("RB");
            }
            if (playersOnPositionCount("WR", teamroster) < 2) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 2) {
                positions.add("CB");
            }
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
            }
            if (playersOnPositionCount("OG", teamroster) == 0) {
                positions.add("OG");
            }
            if (playersOnPositionCount("TE", teamroster) == 0) {
                positions.add("TE");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 14 all----------------- */
        if (teamroster.size() == 13) {
            // select null position
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
            }

            if (playersOnPositionCount("QB", teamroster) == 0) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) == 0) {
                positions.add("RB");
            }
            if (playersOnPositionCount("WR", teamroster) < 2) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 2) {
                positions.add("CB");
            }
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OLB", teamroster) == 0) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
            }
            if (playersOnPositionCount("OG", teamroster) == 0) {
                positions.add("OG");
            }
            if (playersOnPositionCount("TE", teamroster) == 0) {
                positions.add("TE");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 15 C----------------- */
        if (teamroster.size() == 14) {
            // select C
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("C", teamroster) == 0) {
                positions.add("C");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) == 0) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 16 second OLB----------------- */
        if (teamroster.size() == 15) {
            // select second OLB
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OLB", teamroster) < 2) {
                positions.add("OLB");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 17  MLB----------------- */
        if (teamroster.size() == 16) {
            // select second OLB
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 18 second OT----------------- */
        if (teamroster.size() == 17) {
            // select second OT
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OT", teamroster) < 2) {
                positions.add("OT");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OG", teamroster) == 0) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 19 second OG----------------- */
        if (teamroster.size() == 18) {
            // select second OG
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OG", teamroster) < 2) {
                positions.add("OG");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 20 ALL----------------- */
        if (teamroster.size() == 19) {
            // select ALL
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
            }

            if (playersOnPositionCount("QB", teamroster) == 0) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) == 0) {
                positions.add("RB");
            }
            if (playersOnPositionCount("WR", teamroster) < 2) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 2) {
                positions.add("CB");
            }
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("C", teamroster) == 0) {
                positions.add("C");
            }
            if (playersOnPositionCount("OLB", teamroster) < 2) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
            }
            if (playersOnPositionCount("OT", teamroster) < 2) {
                positions.add("OT");
            }
            if (playersOnPositionCount("OG", teamroster) < 2) {
                positions.add("OG");
            }
            if (playersOnPositionCount("TE", teamroster) == 0) {
                positions.add("TE");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 21 ALL----------------- */
        if (teamroster.size() == 20) {
            // select ALL
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("DE", teamroster) < 2) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 2) {
                positions.add("DT");
            }

            if (playersOnPositionCount("QB", teamroster) == 0) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) == 0) {
                positions.add("RB");
            }
            if (playersOnPositionCount("WR", teamroster) < 2) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 2) {
                positions.add("CB");
            }
            if (playersOnPositionCount("MLB", teamroster) == 0) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("C", teamroster) == 0) {
                positions.add("C");
            }
            if (playersOnPositionCount("OLB", teamroster) < 2) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("SS", teamroster) == 0) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) == 0) {
                positions.add("FS");
            }
            if (playersOnPositionCount("OT", teamroster) < 2) {
                positions.add("OT");
            }
            if (playersOnPositionCount("OG", teamroster) < 2) {
                positions.add("OG");
            }
            if (playersOnPositionCount("TE", teamroster) == 0) {
                positions.add("TE");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 22 FB----------------- */
        if (teamroster.size() == 21) {
            // select FB
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 23 K----------------- */
        if (teamroster.size() == 22) {
            // select K
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 24 P----------------- */
        if (teamroster.size() == 23) {
            // select P
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            } else {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("QB", teamroster) == 0) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) == 0) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 25 QB RB----------------- */
        if (teamroster.size() == 24) {
            // select QB RB
            List<String> positions = new ArrayList<String>();

            int check = 0;

            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
                check++;
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
                check++;
            }
            if (check == 0) {
                if (playersOnPositionCount("DE", teamroster) < 2) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 2) {
                    positions.add("DT");
                }

                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 26 DE DT----------------- */
        if (teamroster.size() == 25) {
            // select DE
            List<String> positions = new ArrayList<String>();

            int check = 0;

            if (playersOnPositionCount("DE", teamroster) < 3) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 3) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("WR", teamroster) < 2) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 2) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 27 WR CB----------------- */
        if (teamroster.size() == 26) {
            // select WR CB
            List<String> positions = new ArrayList<String>();
            int count = 0;
            if (playersOnPositionCount("WR", teamroster) < 3) {
                positions.add("WR");
                count++;
            }
            if (playersOnPositionCount("CB", teamroster) < 3) {
                positions.add("CB");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }

                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }

                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 28 WR CB----------------- */
        if (teamroster.size() == 27) {
            // select WR CB
            List<String> positions = new ArrayList<String>();
            int count = 0;
            if (playersOnPositionCount("WR", teamroster) < 3) {
                positions.add("WR");
                count++;
            }
            if (playersOnPositionCount("CB", teamroster) < 3) {
                positions.add("CB");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }

                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }

                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("OT", teamroster) < 2) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 29 OT----------------- */
        if (teamroster.size() == 28) {
            // select OT
            List<String> positions = new ArrayList<String>();
            if (playersOnPositionCount("OT", teamroster) < 3) {
                positions.add("OT");
            } else {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }

                if (playersOnPositionCount("MLB", teamroster) == 0) {
                    positions.add("MLB");
                }

                if (playersOnPositionCount("OLB", teamroster) < 2) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) == 0) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) == 0) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }

                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 30 MLB OLB FS SS----------------- */
        if (teamroster.size() == 29) {
            // select MLB OLB FS SS
            List<String> positions = new ArrayList<String>();
            int count = 0;
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
                count++;
            }
            if (playersOnPositionCount("OLB", teamroster) < 3) {
                positions.add("OLB");
                count++;
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
                count++;
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }

                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 31 MLB OLB FS SS----------------- */
        if (teamroster.size() == 30) {
            // select MLB OLB FS SS
            List<String> positions = new ArrayList<String>();
            int count = 0;
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
                count++;
            }
            if (playersOnPositionCount("OLB", teamroster) < 3) {
                positions.add("OLB");
                count++;
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
                count++;
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 32 MLB OLB FS SS----------------- */
        if (teamroster.size() == 31) {
            // select MLB OLB FS SS
            List<String> positions = new ArrayList<String>();
            int count = 0;
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
                count++;
            }
            if (playersOnPositionCount("OLB", teamroster) < 3) {
                positions.add("OLB");
                count++;
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
                count++;
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }

                if (playersOnPositionCount("DT", teamroster) < 3) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("DE", teamroster) < 3) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 2) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 32 OG or second DE DT----------------- */
        if (teamroster.size() == 31) {
            // select OG or second DE DT
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("OG", teamroster) < 3) {
                positions.add("OG");
                check++;
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) < 3) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 33  second DE DT----------------- */
        if (teamroster.size() == 32) {
            // select second DE DT
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) < 3) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 34  second DE DT----------------- */
        if (teamroster.size() == 33) {
            // select second DE DT
            List<String> positions = new ArrayList<String>();
            int check = 0;
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
                check++;
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
                check++;
            }
            if (check == 0) {// if all this positions picked - pick another

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) < 3) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("TE", teamroster) == 0) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 35  second TE----------------- */
        if (teamroster.size() == 34) {
            // select second TE
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");

            } else {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");

                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) < 3) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("C", teamroster) == 0) {
                    positions.add("C");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 36  second all----------------- */
        if (teamroster.size() == 35) {
            // select second ALL
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");

            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OLB", teamroster) < 3) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("OG", teamroster) < 3) {
                positions.add("OG");
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
            }
            if (playersOnPositionCount("WR", teamroster) < 3) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 3) {
                positions.add("CB");
            }
            if (playersOnPositionCount("OT", teamroster) < 3) {
                positions.add("OT");
            }
            if (playersOnPositionCount("C", teamroster) == 0) {
                positions.add("C");
            }
            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 37  second all----------------- */
        if (teamroster.size() == 36) {
            // select second ALL
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("C", teamroster) == 0) {
                positions.add("C");
            }
            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");

            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OLB", teamroster) < 3) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("OG", teamroster) < 3) {
                positions.add("OG");
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
            }
            if (playersOnPositionCount("WR", teamroster) < 3) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 3) {
                positions.add("CB");
            }
            if (playersOnPositionCount("OT", teamroster) < 3) {
                positions.add("OT");
            }
            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 38  second C----------------- */
        if (teamroster.size() == 37) {
            // select second C
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("C", teamroster) < 2) {
                positions.add("C");
            } else {

                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 2) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OLB", teamroster) < 3) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 39  OLB----------------- */
        if (teamroster.size() == 38) {
            // select OLB
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OLB", teamroster) < 4) {
                positions.add("OLB");
            } else {
                if (playersOnPositionCount("C", teamroster) < 2) {
                    positions.add("C");
                }
                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 2) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }

                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 40  MLB----------------- */
        if (teamroster.size() == 39) {
            // select OLB
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            } else {
                if (playersOnPositionCount("C", teamroster) < 2) {
                    positions.add("C");
                }
                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 2) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("OLB", teamroster) < 4) {
                    positions.add("OLB");
                }

                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("OT", teamroster) < 3) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 41  OT----------------- */
        if (teamroster.size() == 40) {
            // select OT
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OT", teamroster) < 4) {
                positions.add("OT");
            } else {
                if (playersOnPositionCount("C", teamroster) < 2) {
                    positions.add("C");
                }
                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 2) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("OLB", teamroster) < 4) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OG", teamroster) < 3) {
                    positions.add("OG");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }

                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 42  OG----------------- */
        if (teamroster.size() == 41) {
            // select OG
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OG", teamroster) < 4) {
                positions.add("OG");
            } else {
                if (playersOnPositionCount("C", teamroster) < 2) {
                    positions.add("C");
                }
                if (playersOnPositionCount("QB", teamroster) < 2) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 2) {
                    positions.add("RB");
                }
                if (playersOnPositionCount("DE", teamroster) < 4) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 4) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 2) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("OLB", teamroster) < 4) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 2) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OT", teamroster) < 4) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("SS", teamroster) < 2) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 2) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("WR", teamroster) < 3) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 3) {
                    positions.add("CB");
                }

                if (playersOnPositionCount("FB", teamroster) == 0) {
                    positions.add("FB");
                }
                if (playersOnPositionCount("K", teamroster) == 0) {
                    positions.add("K");
                }
                if (playersOnPositionCount("P", teamroster) == 0) {
                    positions.add("P");
                }
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 43  all----------------- */
        if (teamroster.size() == 42) {
            // select all
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OG", teamroster) < 4) {
                positions.add("OG");
            }
            if (playersOnPositionCount("C", teamroster) < 2) {
                positions.add("C");
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
            }
            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
            }
            if (playersOnPositionCount("OLB", teamroster) < 4) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OT", teamroster) < 4) {
                positions.add("OT");
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
            }
            if (playersOnPositionCount("WR", teamroster) < 4) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 4) {
                positions.add("CB");
            }

            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 44  all----------------- */
        if (teamroster.size() == 43) {
            // select all
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OG", teamroster) < 4) {
                positions.add("OG");
            }
            if (playersOnPositionCount("C", teamroster) < 2) {
                positions.add("C");
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
            }
            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
            }
            if (playersOnPositionCount("OLB", teamroster) < 4) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OT", teamroster) < 4) {
                positions.add("OT");
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
            }
            if (playersOnPositionCount("WR", teamroster) < 4) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 4) {
                positions.add("CB");
            }

            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 45  all----------------- */
        if (teamroster.size() == 44) {
            // select all
            List<String> positions = new ArrayList<String>();

            if (playersOnPositionCount("OG", teamroster) < 4) {
                positions.add("OG");
            }
            if (playersOnPositionCount("C", teamroster) < 2) {
                positions.add("C");
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
            }
            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
            }
            if (playersOnPositionCount("OLB", teamroster) < 4) {
                positions.add("OLB");
            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
            }
            if (playersOnPositionCount("OT", teamroster) < 4) {
                positions.add("OT");
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
            }
            if (playersOnPositionCount("WR", teamroster) < 4) {
                positions.add("WR");
            }
            if (playersOnPositionCount("CB", teamroster) < 4) {
                positions.add("CB");
            }

            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        /*----------round 46-53  all full and other----------------- */
        if (teamroster.size() == 45 || teamroster.size() == 46 || teamroster.size() == 47
                || teamroster.size() == 48 || teamroster.size() == 49 || teamroster.size() == 50
                || teamroster.size() == 51 || teamroster.size() == 52 || teamroster.size() == 53) {
            // select all
            List<String> positions = new ArrayList<String>();

            int count = 0;

            if (playersOnPositionCount("OG", teamroster) < 4) {
                positions.add("OG");
                count++;
            }
            if (playersOnPositionCount("C", teamroster) < 2) {
                positions.add("C");
                count++;
            }
            if (playersOnPositionCount("QB", teamroster) < 2) {
                positions.add("QB");
                count++;
            }
            if (playersOnPositionCount("RB", teamroster) < 2) {
                positions.add("RB");
                count++;
            }
            if (playersOnPositionCount("DE", teamroster) < 4) {
                positions.add("DE");
                count++;
            }
            if (playersOnPositionCount("DT", teamroster) < 4) {
                positions.add("DT");
                count++;
            }
            if (playersOnPositionCount("TE", teamroster) < 2) {
                positions.add("TE");
                count++;
            }
            if (playersOnPositionCount("OLB", teamroster) < 4) {
                positions.add("OLB");
                count++;
            }
            if (playersOnPositionCount("MLB", teamroster) < 2) {
                positions.add("MLB");
                count++;
            }
            if (playersOnPositionCount("OT", teamroster) < 4) {
                positions.add("OT");
                count++;
            }
            if (playersOnPositionCount("SS", teamroster) < 2) {
                positions.add("SS");
                count++;
            }
            if (playersOnPositionCount("FS", teamroster) < 2) {
                positions.add("FS");
                count++;
            }
            if (playersOnPositionCount("WR", teamroster) < 4) {
                positions.add("WR");
                count++;
            }
            if (playersOnPositionCount("CB", teamroster) < 4) {
                positions.add("CB");
                count++;
            }

            if (playersOnPositionCount("FB", teamroster) == 0) {
                positions.add("FB");
                count++;
            }
            if (playersOnPositionCount("K", teamroster) == 0) {
                positions.add("K");
                count++;
            }
            if (playersOnPositionCount("P", teamroster) == 0) {
                positions.add("P");
                count++;
            }
            if (count == 0) {

                if (playersOnPositionCount("WR", teamroster) < 5) {
                    positions.add("WR");
                }
                if (playersOnPositionCount("CB", teamroster) < 5) {
                    positions.add("CB");
                }
                if (playersOnPositionCount("QB", teamroster) < 3) {
                    positions.add("QB");
                }
                if (playersOnPositionCount("RB", teamroster) < 3) {
                    positions.add("RB");

                }
                if (playersOnPositionCount("SS", teamroster) < 3) {
                    positions.add("SS");
                }
                if (playersOnPositionCount("FS", teamroster) < 3) {
                    positions.add("FS");
                }
                if (playersOnPositionCount("DE", teamroster) < 5) {
                    positions.add("DE");
                }
                if (playersOnPositionCount("DT", teamroster) < 5) {
                    positions.add("DT");
                }
                if (playersOnPositionCount("TE", teamroster) < 3) {
                    positions.add("TE");
                }
                if (playersOnPositionCount("OLB", teamroster) < 5) {
                    positions.add("OLB");
                }
                if (playersOnPositionCount("MLB", teamroster) < 3) {
                    positions.add("MLB");
                }
                if (playersOnPositionCount("OT", teamroster) < 5) {
                    positions.add("OT");
                }
                if (playersOnPositionCount("OG", teamroster) < 5) {
                    positions.add("OG");
                }

            }

            player = lpservice.playerToDraft(positions, league); // get top
                                                                 // player by
                                                                 // position
            System.err.println(player.getPplayer().getLastname());
        }

        return player;
    }

    /**
     * @param position
     * @param teamroster
     * @return number of players on position
     */
    public static int playersOnPositionCount(String position, List<Roster> teamroster) {
        int count = 0;
        for (int i = 0; i < teamroster.size(); i++) {
            // if player position == position
            if (teamroster.get(i).getRosterID().getLplayer().getPplayer().getDefaultPosition()
                    .equals(position)) {
                count++;
            }
        }

        return count;
    }

    public static boolean checkPosition(String position, List<Roster> teamroster) {
        boolean result = false;

        int count = 0;
        count = playersOnPositionCount(position, teamroster);

        // maximum 6, minimum 4
        if (position.equals("WR") || position.equals("CB") || position.equals("OLB")
                || position.equals("DE") || position.equals("DT") || position.equals("OT")
                || position.equals("OG")) {
            if (count > 5) {
                result = false;
            } else
                result = true;
        }

        // maximum 3, minimum 2
        if (position.equals("QB") || position.equals("RB") || position.equals("FS")
                || position.equals("SS") || position.equals("MLB") || position.equals("TE")
                || position.equals("C")) {
            if (count > 2) {
                result = false;
            } else
                result = true;
        }

        // maximum 2, minimum 1
        if (position.equals("K") || position.equals("P") || position.equals("FB")) {
            if (count > 1) {
                result = false;
            } else
                result = true;
        }

        return result;
    }

}
