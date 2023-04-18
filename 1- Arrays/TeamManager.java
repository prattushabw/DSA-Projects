/**
 * This class Represents a TeamManager which has a String name;
 *         int numHits, numErrors;
 *         String input;
 *         int position;
 *         String stat;
 *         int index;
 *         int index2;
 *         int num;
 *         boolean run =
 *
 * @author Prattusha Biswas 114587992
 */

import java.util.Scanner;

public class TeamManager {
    public static final int MAX_TEAMS =5;

    public static void main (String[] args) throws FullTeamExceptions {

        Team[] teams = new Team[MAX_TEAMS];

        for (int i = 0; i < teams.length; i++) {
            teams[i] = new Team();
        }

        Team currentTeam = teams[0];
        Team p= new Team();
        Team backup=new Team();
        String name;
        int numHits, numErrors;
        String input;
        int position;
        String stat;
        int index;
        int index2;
        int num;
        boolean run = true;

        System.out.println("Welcome to TeamManager!");
        System.out.print("Menu: \n" +
                "A)  Add Player.\n" +
                "G)  Get Player stats. \n" +
                "L)  Get leader in a stat. \n" +
                "R)  Remove a player. \n" +
                "P)  Print all players.\n" +
                "S)  Size of team.\n" +
                "T)  Select team \n" +
                "C)  Clone team\n" +
                "E)  Team equals\n" +
                "U)  Update stat.\n" +
                "Q)  Quit.\n");

        while (run) {
            System.out.print ("\n\nPlease select a menu option: ");
            Scanner stdin = new Scanner(System.in);
            input = stdin.nextLine();
            input = input.toUpperCase();

            switch (input) {
                case "A":
                    Player player1 = new Player();

                    System.out.print("Enter the player's name: ");
                    name = stdin.nextLine();
                    player1.setName(name);

                    System.out.print("Enter the number of hits: ");
                    numHits = stdin.nextInt();
                    try {
                        player1.setNumHits(numHits);
                    } catch(IllegalArgumentException e){
                        System.out.println("not a integer");
                    }

                    System.out.print("Enter the number of errors: ");
                    numErrors = stdin.nextInt();
                    try {
                        player1.setNumErrors(numErrors);
                    } catch (IllegalArgumentException e){
                        System.out.println("not a integer");
                    }

                    System.out.print("Enter the position: ");
                    position = stdin.nextInt();
                    try {
                        currentTeam.addPlayer(player1, position);
                    }catch(IllegalArgumentException e){
                        System.out.println("failed to add player, provide position#");
                }

                    System.out.printf("Player added: %s - %d hits, %d errors", name, numHits, numErrors);

                    break;

                case "G":
                    System.out.print("Enter the position: ");
                    position = stdin.nextInt();

                    System.out.println(currentTeam.players[position-1].toString());

                    break;

                case "L":
                    System.out.print("Enter the stat- hits or errors: ");
                    stat = stdin.nextLine();

                    if (stat.equals("hits")) {
                        System.out.println("leader in stats: " + currentTeam.getLeader(stat));
                    } else if (stat.equals("errors")) {
                        System.out.println("leader in errors: " + currentTeam.getLeader(stat));
                    } else {
                        System.out.println("No such statistic.");
                    }

                    break;

                case "R":
                    System.out.print("Enter the position:");
                    position = stdin.nextInt();

                    try{
                        System.out.println("Player removed at position " + position);
                        System.out.println(currentTeam.players[position-1].getName() +" has been removed from the team.");
                        currentTeam.removePlayer(position-1);
                    }catch(IllegalArgumentException e){
                        System.out.println("failed to remove player, provide position#");
                    }

                    break;

                case "P":
                   System.out.print("Select team index: ");
                   index = stdin.nextInt();

                   currentTeam= teams[index-1];

                   currentTeam.printAllPlayers();

                    break;

                case "S":
                    System.out.println("There are " + currentTeam.size() + " player(s) in the current Team.");

                    break;

                case "T":
                    System.out.print("Enter team index to select: ");
                    index = stdin.nextInt();

                    if (index < 5 && index > 0) {
                        currentTeam = teams[index - 1];

                        System.out.println("Team " + index + " has been been selected.");

                    } else {
                        System.out.println("Invalid index for team.");
                    }

                    break;

                case "C":
                    System.out.print("Select team to clone from: ");
                    index = stdin.nextInt();

                    System.out.print("Select team to clone to: ");
                    index2 = stdin.nextInt();

                   // try{
                   //if (teams[index - 1] != null) {
                       Team team= (Team) backup.clone();
                       //return new Player{this.name, this.numHits, this.numErrors};
                       // Team teamCopy = teams[index - 1].clone();
                        //teams[index2 - 1] = teamCopy;
                        System.out.printf("Team %d has been copied to team %d.", index, index2);
                   // }}catch(RuntimeException e){
                    //    System.out.println("error");
                 //   }

                    break;

                case "E":
                    System.out.print("Select first team index: ");
                    index = stdin.nextInt();

                    System.out.print("Select first team index: ");
                    index2 = stdin.nextInt();

                    if (teams[index - 1].equal((Team) teams[index2 - 1])) {
                        System.out.print("These teams are equal.");
                    } else {
                        System.out.print("These teams are not equal.");
                    }

                    break;

                case "U":
                    System.out.print("Enter the player to update: ");
                    name = stdin.nextLine();

                    for (int i = 0; i <currentTeam.players.length; i++) {
                        if (name.equals(currentTeam.players[i].getName())) {
                            System.out.print("Enter stat to update- errors or hits: ");
                            stat = stdin.nextLine();

                            if (stat.equals("errors")) {
                                System.out.print("Enter the new number of errors: ");
                                num = stdin.nextInt();
                                try {
                                    currentTeam.players[i].setNumErrors(num);
                                    System.out.printf("Updated %s errors", name);
                                }catch (IllegalArgumentException e){
                                    System.out.println("not an integer");
                                }
                            } else if (stat.equals("hits")) {
                                System.out.print("Enter the new number of hits: ");
                                num = stdin.nextInt();
                                try {
                                    currentTeam.players[i].setNumHits(num);
                                    System.out.printf("Updated %s hits", name);
                                }catch(IllegalArgumentException e){
                                    System.out.println("not an integer");
                                }
                            } else {
                                System.out.print("Player not found.");
                            }
                        }break;
                    }
                    break;

                case "Q":
                   //how to quit?
                    System.out.println("Program terminating normally...");
                    System.exit(0);
            }}
        }

}
