package entity;

public class Player {
    String name;
    PlayingSymbol playingSymbol;
    public Player(String name, PlayingSymbol playingSymbol) {
        this.name = name;
        this.playingSymbol = playingSymbol;
    }

    public String getName() {
        return name;
    }

    public PlayingSymbol getPlayingSymbol() {
        return playingSymbol;
    }
}
