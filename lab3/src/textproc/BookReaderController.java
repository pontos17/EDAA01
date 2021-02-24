package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BookReaderController {
	private GeneralWordCounter c;
	
	public BookReaderController (GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
		c = counter;
	}
	
	private void createWindow (GeneralWordCounter counter, String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		/*pane är en behållarkomponent till vilken de övriga komponenterna
		(listvy, knappar etc.) ska läggas till.*/
		
//		JLabel label = new JLabel(" ");
//		label.setBorder(new EmptyBorder(5,10,5,10));
		
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(c.getWordList());
				
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		listView.addListSelectionListener(event -> {
//			label.setText(Integer.toString(listView.getSelectedIndex()) + ": "
//					+ (listView.getSelectedValue().toString()));
//		});

		
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(200, 400));
		scrollPane.setBorder(new EmptyBorder(5,10,5,10));
	
		JPanel knappar = new JPanel();
		ButtonGroup radioButton = new ButtonGroup();
		
		JRadioButton rb1 = new JRadioButton("Alphabetic");
		JRadioButton rb2 = new JRadioButton ("Frequency");
		
//		V4
		radioButton.add(rb1);
		radioButton.add(rb2);
		knappar.add(rb1, 0);
		knappar.add(rb2, 1);
		
		JPanel search = new JPanel();
		JButton sb = new JButton("Search");

		JTextField sf = new JTextField(20);
		
		search.add(sf,0);
		search.add(sb,1);
	
		pane.add(knappar, BorderLayout.SOUTH);
		pane.add(search, BorderLayout.NORTH);
		pane.add(scrollPane, BorderLayout.CENTER);
//		pane.add(label,BorderLayout.SOUTH);
		
//		V3
		frame.getRootPane().setDefaultButton(sb);
		
		rb1.addActionListener(e -> {
			listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			rb1.updateUI();
		});
		rb2.addActionListener(e -> {
			listModel.sort((e1,e2) -> e2.getValue() - e1.getValue());
			rb2.updateUI();
		});
	
		sb.addActionListener(e ->{
//			V1
			String s = sf.getText().toLowerCase().trim();
			int pos = -1;
			for ( int i = 0; i < listModel.getSize();i++ ) {
				if (listModel.getElementAt(i).getKey().equalsIgnoreCase(s) ){
					pos = i;
					listView.ensureIndexIsVisible(pos);
					listView.setSelectedIndex(pos);
					break;
				}
				
//				V2
				if (i == listModel.getSize()-1) {
					JOptionPane.showMessageDialog(null, "Ditt sökta ord: " + s.toUpperCase() + ", fanns inte eller är ett undantagsord",  "No Such Word",JOptionPane.CLOSED_OPTION);
					break;
				}
			}
		});
		
		frame.pack();
		frame.setVisible(true);
	}

}
