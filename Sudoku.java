package sudoku;

public class Sudoku implements SudokuSolver {
	private int[][] board = null;
	private int r;
	private int c;
	
	public Sudoku() {
	board = new int [getDimension()][getDimension()];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		board[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		return board[r][c];
		
	}

	@Override
	public void clearNumber(int r, int c) {
		board[r][c] = 0;
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		if (0 <= r && r <= 8 && 0 <= c && c <= 8 && 1<= nbr && nbr <= 9) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAllValid() {
		for (int m = 0; m < 9 ; m++) {
			for (int n = 0; n < 9; n++){
				int nbr = board[m][n];
				if(!isValid(m,n,nbr)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0,0);
	}
	private boolean solve (int r, int c) {
		if (r == 8 && c == 8) {
			return true;
		}
		for (int row = r; )
		for(int i = 1; i < 10; i++) {
			if (isValid(r,c,i)) {
				board[r][c] = i;
				return true;
			} else {
				board[r][c] = 0;
				
			}
		}
		return false;
	}

	@Override
	public void clear() {
		board = null;
	}

	@Override
	public int[][] getMatrix() {
		return board;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		board = nbrs;
	}

}
