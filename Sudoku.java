package sudoku;



public class Sudoku implements SudokuSolver {
	private int[][] board = null;
	private int[][] refboard = null;

	
	public Sudoku() {
	board = new int [getDimension()][getDimension()];
	refboard = new int [getDimension()][getDimension()];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if(0 < nbr && nbr < 10) {
			board[r][c] = nbr;
			refboard[r][c] = nbr;
		} else {
			throw new IllegalArgumentException();
		}
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
		for (int i = 0; i < 9 ; i++) {
			if (board[r][i] == nbr && i != c) {
				return false;
			}
			if (board[i][c] == nbr && i != r) {
				return false;
			}
			if ((board[(r/3)*3 + (i%3)][(c/3)*3 + (i/3)] == nbr) && ((r/3)*3 + (i%3) != r) 
					&& ((c/3)*3 + (i/3) != c)) {
				return false;				
			}
		}
		return true;
	}

	@Override
	public boolean isAllValid() {
		for (int r = 0; r < 9 ; r++) {
			for (int c = 0; c < 9; c++){
				if(isValid(r,c,board[r][c])) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean solve() {
//		if (isAllValid()) {
			return solve(0,0);
//		}
//		return false;
		
	}
	private boolean solve (int r, int c) {
		if (c > 8) {
			c = 0; 
			r++;
		} if(r > 8) {
			return true;
		}
		if (board[r][c] != 0) {
			if(isValid(r,c,board[r][c])) { 
				return solve(r, c+1);
			}
		}
		else {
			for(int i = 1; i < 10; i++) {
				if (isValid(r,c,i)) {
					board[r][c] = i;
					if (solve(r,c+1)) {
						return true;
					}else {
						board[r][c] = 0;
					}
				}
			}
		}
		return false;
	}
	@Override
	public void clear() {
		board = new int [getDimension()][getDimension()];
	}

	@Override
	public int[][] getMatrix() {
		return board;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		for (int r = 0; r < 9 ; r++ ) {
			for (int c = 0; c < 9 ; c++) {
				board[r][c] = nbrs[r][c];
			}
		}

	}

}
