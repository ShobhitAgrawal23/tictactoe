package entity;

public class Board {
    int size;
    PlayingSymbol[][] playingBoard;
    int WinCount;

    public Board(int size, int winCount) {
        this.size = size;
        this.WinCount = winCount;
        playingBoard = new PlayingSymbol[size][size];
    }

    public PlayingSymbol[][] getPlayingBoard() {
        return playingBoard;
    }

    public boolean addPlayingSymbol(PlayingSymbol playingSymbol, int row, int col) {
        if (playingBoard[row][col] == null) {
            playingBoard[row][col] = playingSymbol;
            return true;
        }
        return false;
    }

    public void printBoard() {
        for (PlayingSymbol[] playingSymbols : playingBoard) {
            for (PlayingSymbol playingSymbol : playingSymbols) {
                if (playingSymbol != null) {
                    System.out.print( "| " + playingSymbol.getSymbolType().name() + " |" );
                } else {
                    System.out.print( "|   |" );
                }
            }
            System.out.println();
        }
    }

    public boolean isFreeSpaceAvailable() {
        for (PlayingSymbol[] playingSymbols : playingBoard) {
            for (PlayingSymbol playingSymbol : playingSymbols) {
                if (playingSymbol == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int getWinCount() {
        return WinCount;
    }
}
