import entity.Board;
import entity.Player;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Game {

    public static final String ROW = "ROW";
    public static final String COLUMN = "COLUMN";
    public static final String DIAGONAL = "DIAGONAL";
    public static final String ANTI_DIAGONAL = "ANTI_DIAGONAL";
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

            boolean symbolAddedSuccessfully = board.addPlayingSymbol( player.getPlayingSymbol(), x, y );
            if(!symbolAddedSuccessfully){
                System.out.println("Place is already occupied, please try again");
                players.addFirst(player);
                continue;
            }else{
                players.addLast( player );
            }

            if(gotWinnerWith(player, x, y)){
                gameOver = true;
                board.printBoard();
                return player;
            }


        }
        return null;

    }

    private boolean gotWinnerWith(Player player, int x, int y) {
        ArrayList<String> directions = new ArrayList<>();
        directions.add( ROW );
        directions.add( COLUMN );
        directions.add( DIAGONAL );
        directions.add( ANTI_DIAGONAL );
        for (String direction : directions) {
            int[] xCoordinates = new int[0];
            int[] yCoordinates = new int[0];
            switch (direction) {
                case ROW:
                    xCoordinates= new int[]{ 0, 0 };
                    yCoordinates= new int[]{ -1, 1 };
                    break;
                case COLUMN:
                    xCoordinates= new int[]{ -1, 1 };
                    yCoordinates= new int[]{ 0, 0 };
                    break;
                case DIAGONAL:
                    xCoordinates= new int[]{ 1, -1 };
                    yCoordinates= new int[]{ 1, -1 };
                    break;
                case ANTI_DIAGONAL:
                    xCoordinates= new int[]{ 1, -1 };
                    yCoordinates= new int[]{ -1, 1 };
                    break;
            }
            if (validateDirection( player, x, y, xCoordinates, yCoordinates, new boolean[board.getSize()][board.getSize()])>=board.getWinCount())
                return true;
        }
        return false;
    }

    private int validateDirection(Player player, int x, int y, int[] xCoordinates, int[] yCoordinates, boolean[][] visited) {
        if(x<0 || y<0 || x>=board.getSize() || y>=board.getSize() || visited[x][y] || board.getPlayingBoard()[x][y]==null || board.getPlayingBoard()[x][y]!=player.getPlayingSymbol()){
            return 0;
        }
        visited[x][y] = true;
        int sum = 0;
        for(int i=0; i<2; i++){
            int newX = x + xCoordinates[i];
            int newY = y + yCoordinates[i];
            sum = 1+ validateDirection( player, newX, newY, xCoordinates, yCoordinates, visited );
        }
        return sum;
    }

}
