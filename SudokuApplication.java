package sudoku;

public class SudokuApplication {
	public static void main(String[] args) {
		Sudoku board = new Sudoku();
		new SudokuView(board);
	}
}
