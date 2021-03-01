package sudoku;

import java.awt.*;
import javax.swing.*;

public class SudokuView {
	private JTextField[][] textFields;
	private Sudoku board;
	private String wrongcell;

	public SudokuView(Sudoku board) {
		SwingUtilities.invokeLater(() -> createWindow(board));
		this.board = board;
	}

	private void createWindow(Sudoku board) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		frame.setTitle("Sudoku Solver");
		frame.setPreferredSize(new Dimension(600, 600));

		textFields = new JTextField[9][9];
		JPanel sBoard = new JPanel(new GridLayout(9, 9, 3, 3));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				JTextField jtf = new JTextField();
				jtf.setFont(new Font("Times New Roman", Font.BOLD, 40));
				jtf.setHorizontalAlignment(JTextField.CENTER);
				if ((r / 3 + c / 3) % 2 == 0) {
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
		frame.getRootPane().setDefaultButton(solve);

		solve.addActionListener(evt -> {
			if (checkBoard()) {

				if (board.solve()) {
					for (int r = 0; r < 9; r++) {
						for (int c = 0; c < 9; c++) {
							textFields[r][c].setText(Integer.toString(board.getNumber(r, c)));
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Det finns ingen lösning, då något strider mot reglerna",
							"Ingen lösning", JOptionPane.CLOSED_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Rutan på " + wrongcell, "Dålig indata", JOptionPane.CLOSED_OPTION);
			}
		});
		clear.addActionListener(event -> {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					textFields[r][c].setText(null);
				}
			}
			board.clear();
		});
		pane.add(sBoard, BorderLayout.CENTER);
		pane.add(knappar, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);

//		JOptionPane.showMessageDialog(null, "Skriv in siffror mellan 1 och 9 på valfria rutor och se sudokut lösa sig. "
//				+ "\n Men kom ihåg skriv bara en likadan siffra på varje kolumn, rad och 3x3-ruta", "Välkommen", JOptionPane.CLOSED_OPTION);
	}

	private boolean checkBoard() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				String s = textFields[r][c].getText();
				if (!s.isEmpty()) {
					int nbr;
					try {
						nbr = Integer.parseInt(s);
					} catch (NumberFormatException e) {
						wrongcell = "rad: " + String.valueOf(r + 1) + " och kolumn: " + String.valueOf(c + 1)
								+ ", hade något annat än siffror";
						return false;
					}

					try {
						board.setNumber(r, c, nbr);
					} catch (Exception e) {
						wrongcell = "rad: " + String.valueOf(r + 1) + " och kolumn: " + String.valueOf(c + 1)
								+ ", hade en ogiltig siffa";
						return false;
					}
				}
			}
		}
		return true;
	}
}
