package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;



public class SudokuView {
	
	public SudokuView(Sudoku board) {
		 SwingUtilities.invokeLater(() -> createWindow(board, "Sudoku solver", 600, 600));
	}

	private void createWindow(Sudoku board, String title, int width, int height) {
	
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		SudokuBoard sBoard = new SudokuBoard();
		
		JPanel knappar = new JPanel();
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		knappar.add(solve, 0);
		knappar.add(clear, 1);
		
		clear.addActionListener(event -> board.clear());
		
		pane.add(sBoard, BorderLayout.CENTER);
		pane.add(knappar, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	private class SudokuBoard extends JPanel{
		private SubBoard [] subBoards;
		public SudokuBoard() {
			setBorder(new EmptyBorder(4,4,4,4));
			subBoards = new SubBoard[9];
			setLayout(new GridLayout(3,3,2,2));
			for (int r = 0; r < 9 ;r++) {
					SubBoard sb = new SubBoard();
					subBoards[r]=sb;
					add(sb);
			}
		}
	}
	private class SubBoard extends JPanel{

		private JTextField[] fields;
		public SubBoard (){
			setLayout(new GridLayout(3,3,2,2));
			fields = new JTextField[9];
			for (int r = 0; r < 9 ;r++) {
					JTextField text = new JTextField(4);
					fields[r] = text;
					add(text);
			}
		}
	}
	

}
