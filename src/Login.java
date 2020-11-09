import java.awt.Button;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener {
	JLabel user = new JLabel("username: ");
	JTextField userText = new JTextField();
	JLabel password = new JLabel("password: ");
	JPasswordField passwordText = new JPasswordField();
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
		if(arg0.getSource() == login){
			try {
				BufferedReader input = new BufferedReader(new FileReader("passwords.txt"));
				String pass = null;
				String line = input.readLine();
				while(line != null){
					StringTokenizer st = new StringTokenizer(line);
					if(userText.getText().equals(st.nextToken())){
						pass = st.nextToken();
					}
					line = input.readLine();
				}
				input.close();
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(new String(passwordText.getPassword()).getBytes());
				byte byteData[] = md.digest();
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<byteData.length; i++){
					sb.append(Integer.toString((byteData[i] & 0xFF)+ 0x100, 16).substring(1));
				}
				if(pass.equals(sb.toString())){
					System.out.println("user has logged in");
					add(new FileBrowser(userText.getText()), "fb");
					cl.show(this, "fb");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(arg0.getSource() == register){
			add(new Register(), "register");
			cl.show(this, "register");
		}

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
