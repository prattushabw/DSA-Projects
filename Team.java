/**
 * This class Represents a team which has a sz, clone, equal, leaders, addplayer, remove player
 *
 * @author Prattusha Biswas 114587992
 */
public class Team {

    static final int MAX_PLAYERS = 40;
    int sz;
    Player[] players;

    public Team() {
        this.sz=0;
        this.players= new Player[MAX_PLAYERS];
    }

    //clone

    public Object clone() {
        Team newTeam = new Team();
        for(int i=0; i< this.sz; i++) {

            try {
                newTeam.addPlayer((Player)this.players[i].clone(), i);
            } catch (FullTeamExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return newTeam;
    }


    public boolean equal(Object obj) {
        if (obj instanceof Team) {
            Team newTeam = (Team) obj;
            for (int i = 0; i < newTeam.size() && newTeam.size() == this.size(); i++) {
                return (newTeam.players[i].equal(this.players[i]));
            }}
        return false;
    }



    public int size() {
            return sz;
        }



    void addPlayer(Player p, int position) throws FullTeamExceptions{
         if (position < 1 && position > this.sz+1) {
            throw new IllegalArgumentException();
        } else if (sz > players.length) {
            throw new FullTeamExceptions("no more room");

            //if a user enters two players in the same position then the first player will be moved down so teh second input can be placed in
        } else if(this.players[position-1] == this.players[position-1] ){
           for(int i=0;i<sz;i++){
               this.players[i+1]=this.players[i];
           }
           //sz increases as a new player and its info is added to the team
           this.players[position-1]=p;
           sz++;

        }}

    //?
    void removePlayer(int position) {
        if (0 > position && position > this.sz) {
            throw new IllegalArgumentException();
        }for(int i=0;i<this.sz-1;i++){
            players[i]= players[i+1];
        }players[sz-1]=null;
        this.sz--;
        }

    Player getPlayer(int position){
        return players[position];
    }

    Player getLeader(String stat){
        //creating a new object for highest player score
        Player HighestStat= new Player();

        if(stat.equals("hits")){
            for(int i=0; i<this.sz;i++){
             for(int j=0; j<this.sz;j++){
                if(players[i].numHits > players[j].numHits){
                HighestStat= players[i];
      }}}} else if(stat.equals("errors")){
            for(int i=0; i<this.sz;i++){
                for(int j=0; j<this.sz;j++){
                    if(players[i].numErrors < players[j].numErrors){
                        HighestStat= players[i];
        }}}}
    return HighestStat;
    }

    public void printAllPlayers(){
        System.out.print(this.toString());
    }

    public String toString(){
        String newString="Player#    Name             Hits          Errors";
        String line= "\n-------------------------------------------------\n";
        int playerNum;
        String playerList="";
        for(int i=0; i<sz;i++){
            playerNum= i+1;
            playerList += (""+playerNum + "          "+ this.players[i].getName()+  "             "+this.players[i].getNumHits()+ "              "+this.players[i].getNumErrors()+"\n");
        }
        return (newString+line+playerList);
    }

}
