
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String totalpoints() {
        return name + " " + team + " " + goals + " + " + assists + " = " + (goals + assists);
    }
    
    @Override
    public String toString() {
        return name + ", team " + team + ", goals " + goals + ", assists " + assists;
    }

    @Override
    public int compareTo(Player player) {
        int points = this.goals + this.assists;
        int comparablePoints = player.getGoals() + player.getAssists();
        return points - comparablePoints;
    }
      
}
