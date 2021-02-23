package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;




public class SudokuView {
	
	public SudokuView(Sudoku board) {
		 SwingUtilities.invokeLater(() -> createWindow(board, "Sudoku solver", 1000, 600));
	}

	private void createWindow(Sudoku board, String title, int width, int height) {
	
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		pane.setSize(width, height);
		
		JTextField[][] textFields = new JTextField[9][9];
		JPanel sBoard = new JPanel(new GridLayout(9, 9 , 3, 3));
		for(int r = 0; r < 9; r++) {
			for(int c =0; c<9;c++) {
				JTextField jtf = new JTextField(4);
				if((r/3 + c /3) %2 ==0) {
					jtf.setBackground(Color.orange);
				}
				sBoard.add(textFields[r][c] = jtf);
			}
		}
		JPanel knappar = new JPanel();
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		knappar.add(solve, 0);
		knappar.add(clear, 1);

		solve.addActionListener(evt -> {
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					if(textFields[r][c].getText().length() != 0) {
						board.setNumber(r, c, (int) Long.parseLong(textFields[r][c].getText()));
					}
				}
			}
		
			if(board.solve()) {
				for(int r = 0; r < 9; r++) {
					for(int c =0; c<9;c++) {
						if(board.getNumber(r, c) != 0) {
							textFields[r][c].setText(Integer.toString(board.getNumber(r, c)));
						} else {
							textFields[r][c].setText(null);
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Det finns ingen lösning",  "Ingen lösning",JOptionPane.CLOSED_OPTION);		
			}

		});
		clear.addActionListener(event -> {
			for(int r = 0; r < 9; r++) {
				for(int c =0; c<9;c++) {
					textFields[r][c].setText(null);
				}
			}
			board.clear();
		
		});
		
		pane.add(sBoard, BorderLayout.CENTER);
		pane.add(knappar, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
}
