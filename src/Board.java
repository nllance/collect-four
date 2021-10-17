import java.util.Arrays;

public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROWS = 6;
	private char[][] data;
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 */
	
	public Board() {
		data = new char[NUM_OF_ROWS][NUM_OF_COLUMNS];
		for (char[] row : data){
			Arrays.fill(row, ' ');
		}
	}

	public int getColumns(){
		return NUM_OF_COLUMNS;
	}
	
	public void printBoard() {
		for (int i = 0; i < NUM_OF_ROWS - 1; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++){
				System.out.print("|" + data[i][j]);
			}
			System.out.println("|");
		}
		for (int i = 0; i < NUM_OF_COLUMNS; i++){
			if (data[NUM_OF_ROWS - 1][i] == ' '){
				System.out.print("|_");
			}
			else{
				System.out.print("|" + data[NUM_OF_ROWS - 1][i]);
			}
		}
		System.out.println("|");
	}
	
	public boolean containsWin()
	{
		// Check horizontal
		for (char[] row : data){
			for (int i = 0; i < NUM_OF_COLUMNS - 3; i++){
				char value = row[i];
				if ((value != ' ') && (row[i+1] == value && row[i+2] == value && row[i+3] == value)) {
					return true;
				}
			}
		}

		//Check vertical
		for (int i = 0; i < NUM_OF_ROWS - 3; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++){
				char value = data[i][j];
				if ((value != ' ') && (data[i+1][j] == value && data[i+2][j] == value && data[i+3][j] == value)) {
					return true;
				}
			}
		}

		//Check one direction of diagonal
		for (int i = 3; i < NUM_OF_ROWS; i++){
			for (int j = 0; j < NUM_OF_COLUMNS - 3; j++){
				char value = data[i][j];
				if ((value != ' ') && (data[i-1][j+1] == value && data[i-2][j+2] == value && data[i-3][j+3] == value)) {
					return true;
				}
			}
		}

		//Check another direction of diagonal
		for (int i = 0; i < NUM_OF_ROWS - 3; i++){
			for (int j = 0; j < NUM_OF_COLUMNS - 3; j++){
				char value = data[i][j];
				if ((value != ' ') && (data[i+1][j+1] == value && data[i+2][j+2] == value && data[i+3][j+3] == value)) {
					return true;
				}
			}
		}

		return false;

	}
	
	public boolean isTie()
	{
		for (char[] row : data){
			for (int i = 0; i < NUM_OF_COLUMNS; i++){
				if (row[i] == ' ') {return false; }
			}
		}
		return true;
	}
	
	public void reset()
	{
		for (char[] row : data){
			Arrays.fill(row, ' ');
		}
	}

	// Check if a column is full
	public boolean validMove(int column)
	{
		return (data[0][column] == ' ');
	}

	// Make a move
	public void drop(char symbol, int column)
	{
		for (int i = NUM_OF_ROWS - 1; i >= 0; i--){
			if (data[i][column] == ' '){
				data[i][column] = symbol;
				break;
			}
		}
	}

	// Roll back a testing move - for the AI player
	public void cancel(int column)
	{
		for (int i = 0; i < NUM_OF_ROWS; i++){
			if (data[i][column] != ' '){
				data[i][column] = ' ';
				break;
			}
		}
	}

	// Get the symbol of the other player for the AI player
	public char otherSymbol(char symbol)
	{
		for (char[] row : data){
			for (int i = 0; i < NUM_OF_COLUMNS; i++){
				if ((row[i] != ' ') && (row[i] != symbol)){
					return row[i];
				}
			}
		}
		return ' ';
	}
	
}