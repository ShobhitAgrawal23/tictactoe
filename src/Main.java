import entity.Board;
import entity.Player;
import entity.PlayingSymbol;
import entity.SymbolType;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println( "Welcome to TicTacToe Game!" );
        System.out.println( "You can play with max 4 number of players and in nxn board" );
        System.out.println("Player can opt any of below symbol");
        Arrays.stream( SymbolType.values() ).forEach( x -> System.out.print( x.name()+" " ) );
        System.out.println();
        System.out.println("*****************************************");
        System.out.println( "Enter the number of players: " );
        Scanner scanner = new Scanner(System.in);
        int players = scanner.nextInt();
        Deque<Player> playerList = new LinkedList<>();
        Set<String> symbols = new HashSet<>();
        for ( int i = 0; i < players; i++ ) {
            System.out.println( "Enter Player " + (i + 1) + " name and symbol(Xyz X) " );
            String name = scanner.next();
            String symbol = scanner.next();
            if( symbols.contains( symbol ) ) {
                System.out.println( "Symbol " + symbol + " already exists" );
                i--;
                continue;
            }
            try {
                SymbolType symbolType = SymbolType.valueOf( symbol.toUpperCase() );
                symbols.add( symbol );
            }catch (Exception e) {
                System.out.println( "Invalid symbol please try again with below listed symbol, please try again" );
                Arrays.stream( SymbolType.values() ).forEach( x -> System.out.print( x.name()+" " ) );
                i--;
                continue;
            }
            playerList.addLast( new Player( name, new PlayingSymbol( SymbolType.valueOf( symbol.toUpperCase() ))  ) );
        }

        System.out.println( "Great! now enter the board size in which you want to play: " );
        int size=scanner.nextInt();

        Board board= null;
        if(size==3){
            board= new Board( size,3 );
        } else {
            System.out.println( "Enter the Continuous Symbol which result into win: " );
            int winCount = scanner.nextInt();
            board= new Board( size,winCount );
        }

        System.out.println( "All Done, lets start the game" );

        Game game= new Game( playerList, board );
        Player player = game.startGame();
        if(player==null){
            System.out.println( "Game is Tie" );
        }else{
            System.out.println( "Congratulations "+player.getName()+", you have won the game!" );
        }
        System.exit(0);


    }
}
