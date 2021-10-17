import java.util.Scanner;

public class HumanPlayer extends Player
{
    public HumanPlayer(char symbol, Board board, String name)
    {
        super(symbol, board, name);
    }

    public void makeMove(Board board)
    {
        Scanner scanner = new Scanner(System.in);
        int columns = board.getColumns();
        // Get a valid input
        while (true){
            System.out.print(name + ", please input your move: ");
            int move = scanner.nextInt();
            if ((1 <= move) && (move <= columns) && board.validMove(move - 1)) {
                board.drop(symbol, (move - 1));
                break;
            }
        }
    }
}