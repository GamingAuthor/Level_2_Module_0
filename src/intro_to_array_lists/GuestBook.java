package intro_to_array_lists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GuestBook implements ActionListener {
	// Create a GUI with two buttons. One button reads "Add Name" and the other button reads "View Names". 
	// When the add name button is clicked, display an input dialog that asks the user to enter a name. Add
	// that name to an ArrayList. When the "View Names" button is clicked, display a message dialog that displays
	// all the names added to the list. Format the list as follows:
	// Guest #1: Bob Banders
	// Guest #2: Sandy Summers
	// Guest #3: Greg Ganders
	// Guest #4: Donny Doners
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton add = new JButton("Add Name");
	JButton view = new JButton("View Names");
	String names;
	ArrayList<String> nam = new ArrayList<String>();
	public static void main(String[] args) {
		GuestBook demetri = new GuestBook();
		demetri.ui();
	}
	void ui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(add);
		panel.add(view);
		add.addActionListener(this);
		view.addActionListener(this);
		frame.pack();
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(add)) {
			names = JOptionPane.showInputDialog("Enter in the name of a guest.");
			nam.add(names);
		}
		if(e.getSource().equals(view)) {
		String ok = "";
			for(int i = 0; i<nam.size();i++) {
				int x = i;
				String lol = "Guest #"+(x+=1)+": "+nam.get(i)+"\n";	
				ok+=lol;
			}
			JOptionPane.showMessageDialog(null, ok);
		}
	}
}
