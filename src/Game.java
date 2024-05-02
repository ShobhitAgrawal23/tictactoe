import entity.Board;
import entity.Player;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Game {

    Deque<Player> players;
    Board board;

    public Game(Deque<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public Player startGame() {
        boolean gameOver = false;
        while(!gameOver){
            // checking for free space in board
            if(!board.isFreeSpaceAvailable()){
                gameOver = true;
                continue;
            }

            // getting the player whose turn
            Player player= players.removeFirst();

            board.printBoard();

            // asking player to provide the coordinates
            System.out.println( player.getName() + " please enter coordinates(x y): " );
            Scanner scanner = new Scanner( System.in );
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(x>= board.getSize() || y>= board.getSize() || x<0 || y<0){
                System.out.println("Invalid coordinates, please try again");
                players.addFirst(player);
                continue;
            }
            // add the input if the given coordinate is empty
            boolean symbolAddedSuccessfully = board.addPlayingSymbol( player.getPlayingSymbol(), x, y );
            if(!symbolAddedSuccessfully){
                System.out.println("Place is already occupied, please try again");
                players.addFirst(player);
                continue;
            }else{
                players.addLast( player );
            }

            if(gotWinner(player, x, y)){
                gameOver = true;
                board.printBoard();
                return player;
            }


        }
        return null;

    }

    private boolean gotWinner(Player player, int x, int y) {
        int rowMatchCount = 0;
        int colMatchCount = 0;
        int diagMatchCount = 0;
        int antiDiagMatchCount = 0;

        // checking row right
        for(int i=y; i < board.getSize(); i++){
            if(board.getPlayingBoard()[x][i]==null || board.getPlayingBoard()[x][i]!=player.getPlayingSymbol()){
                break;
            }
            rowMatchCount++;
        }
        // checking row left
        for(int i=y-1; i >= 0; i--){
            if(board.getPlayingBoard()[x][i]==null || board.getPlayingBoard()[x][i]!=player.getPlayingSymbol()){
                break;
            }
            rowMatchCount++;
        }
        if(rowMatchCount>=board.getWinCount()){
            return true;
        }

        // checking column down
        for(int i=x; i < board.getSize(); i++){
            if(board.getPlayingBoard()[i][y]==null || board.getPlayingBoard()[i][y]!=player.getPlayingSymbol()){
                break;
            }
            colMatchCount++;
        }
        // checking column up
        for(int i=x-1; i >= 0; i--){
            if(board.getPlayingBoard()[i][y]==null || board.getPlayingBoard()[i][y]!=player.getPlayingSymbol()){
                break;
            }
            colMatchCount++;
        }
        if(colMatchCount>=board.getWinCount()){
            return true;
        }

        // checking diagonal down
        for(int i=x, j=y; i < board.getSize() && j < board.getSize(); i++, j++){
            if(board.getPlayingBoard()[i][j]==null || board.getPlayingBoard()[i][j]!=player.getPlayingSymbol()){
                break;
            }
            diagMatchCount++;
        }
        // checking diagonal up
        for(int i=x-1, j=y-1; i >= 0 && j >= 0; i--, j--){
            if(board.getPlayingBoard()[i][j]==null || board.getPlayingBoard()[i][j]!=player.getPlayingSymbol()){
                break;
            }
            diagMatchCount++;
        }
        if(diagMatchCount>=board.getWinCount()){
            return true;
        }

        // checking anti diagonal down
        for(int i=x, j=y; i < board.getSize() && j >= 0; i++, j--){
            if(board.getPlayingBoard()[i][j]==null || board.getPlayingBoard()[i][j]!=player.getPlayingSymbol()){
                break;
            }
            antiDiagMatchCount++;
        }
        // checking anti diagonal up
        for(int i=x-1, j=y+1; i >= 0 && j < board.getSize(); i--, j++){
            if(board.getPlayingBoard()[i][j]==null || board.getPlayingBoard()[i][j]!=player.getPlayingSymbol()){
                break;
            }
            antiDiagMatchCount++;
        }
        if(antiDiagMatchCount>=board.getWinCount()){
            return true;
        }

        return false;



    }

}
