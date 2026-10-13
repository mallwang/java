package funktionen;

public class Rangliste {
    protected String player;
    protected String points;

    public Rangliste() {
        player = "";
        points = "";
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}