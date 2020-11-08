import java.awt.Button;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener {
	JLabel user = new JLabel("username: ");
	JTextField userText = new JTextField();
	JLabel password = new JLabel("password: ");
	JTextField passwordText = new JTextField();
	JPanel loginPanel = new JPanel(new GridLayout(3,2));
	JPanel fpanel = new JPanel();
	Button login = new Button("login");
	Button register = new Button("register");
	CardLayout cl;
	Login(){
		setLayout(new CardLayout());
		loginPanel.add(user);
		loginPanel.add(userText);
		loginPanel.add(password);
		loginPanel.add(passwordText);
		loginPanel.add(login);
		loginPanel.add(register);
		
		login.addActionListener(this);
		register.addActionListener(this);
		fpanel.add(loginPanel);
		add(fpanel, "login");
		cl = (CardLayout) getLayout();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.setSize(500,500);
		Login login = new Login();
		frame.add(login);
		frame.setVisible(true);
	}


}
