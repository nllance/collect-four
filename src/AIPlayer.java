import java.util.Random;

public class AIPlayer extends Player
{
    public AIPlayer(char symbol, Board board, String name)
    {
        super(symbol, board, name);
    }

    public void makeMove(Board board)
    {
        int move = chooseMove(board);
        System.out.println(name + ", please input your move: " + (move + 1));
    }

    // Get the proper move
    public int chooseMove(Board board)
    {
        int columns = board.getColumns();

        // Check winning move for AI player
        for (int i = 0; i < columns; i++){
            if (board.validMove(i)){
                board.drop(symbol, i);
                if (board.containsWin()){
                    return i;
                }
                board.cancel(i);
            }
        }

        // Check winning move for the other player
        char other = board.otherSymbol(symbol);
        if (other != ' '){
            for (int i = 0; i < columns; i++){
                if (board.validMove(i)){
                    board.drop(other, i);
                    if (board.containsWin()) {
                        board.cancel(i);
                        board.drop(symbol, i);
                        return i;
                    }
                    board.cancel(i);
                }
            }
        }

        // No winning move for either player, take a random move
        Random rand = new Random();
        while(true){
            int move = rand.nextInt(columns);
            if (board.validMove(move)) {
                board.drop(symbol, move);
                return move;
            }
        }
    }
}
