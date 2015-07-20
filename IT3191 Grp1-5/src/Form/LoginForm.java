package Form;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.User;

public class LoginForm {

	private JFrame jFrame = null;  
	private JPanel jContentPane = null;
	private JLabel jLabelName = null;
	private JLabel jLabelPassword = null;
	private JTextField jTextFieldUserId = null;
	private JPasswordField jPasswordField = null;
	private JButton jButtonExit = null;
	private JButton jButtonSubmit = null;
	private JButton jButtonRegister = null;

	private User userObj;
	private String id, pwd;
	private boolean dbOk;

	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new java.awt.Dimension(510,300));
			jFrame.setTitle("Login");
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;	
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelName = new JLabel();
			jLabelName.setBounds(new java.awt.Rectangle(70,30,86,30));
			jLabelName.setText("User id:");
			jLabelPassword = new JLabel();
			jLabelPassword.setBounds(new java.awt.Rectangle(70,60,86,30));
			jLabelPassword.setText("Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelName, null);
			jContentPane.add(jLabelPassword, null);
			jContentPane.add(getJTextFieldUserId (), null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getJButtonExit(), null);
			jContentPane.add(getJButtonSubmit(), null);	
			jContentPane.add(getJButtonRegister(), null);
		}
		return jContentPane;	
	}

	private JTextField getJTextFieldUserId() {
		if (jTextFieldUserId == null) {
			jTextFieldUserId = new JTextField();
			jTextFieldUserId.setBounds(new java.awt.Rectangle(190,30,125,30));
		}
		return jTextFieldUserId;	
	}

	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new java.awt.Rectangle(190,60,125,30));
		}
		return jPasswordField;	
	}

	private JButton getJButtonRegister() {
		if (jButtonRegister == null) {
			jButtonRegister = new JButton();
			jButtonRegister.setBounds(new java.awt.Rectangle(225,90,90,31));
			jButtonRegister.setText("Register");
			jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrame().dispose();
					RegisterForm registerWindow = new RegisterForm();
					registerWindow.getJFrame().setVisible(true);
				}
			});
		}
		return jButtonRegister;
	}

	/*** This method initializes jButtonExit		 */
	private JButton getJButtonExit() {
		if (jButtonExit == null) {
			jButtonExit = new JButton();
			jButtonExit.setBounds(new java.awt.Rectangle(419,231,75,30));
			jButtonExit.setText("Exit");
			jButtonExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Good Bye");
					int option = JOptionPane.showConfirmDialog(getJFrame(), 
							"Do you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION)
						System.exit(0);
				}
			});
		}
		return jButtonExit;
	}

	/*** This method initializes jButtonSubmit		 */
	private JButton getJButtonSubmit() {
		if (jButtonSubmit == null) {
			jButtonSubmit = new JButton();
			jButtonSubmit.setBounds(new java.awt.Rectangle(120,90,76,31));
			jButtonSubmit.setText("Login");
			jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String id = getJTextFieldUserId ().getText();
					String pwd = new String(getJPasswordField().getPassword());

					if (validateInput()){

						User userObj = new User(id,pwd);
						dbOk = userObj.retrieveUser();
						if (dbOk) {
							//						JOptionPane.showMessageDialog(getJFrame(), "Success");
							getJFrame().dispose();
							EncryptForm encryptWindow = new EncryptForm();
							encryptWindow.getJFrame().setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(getJFrame(), "Invalid username and/or password.");
						}
					}
				}
			});
		}
		return jButtonSubmit;
	}
	
	private boolean validateInput() {
		String id;
		char[] pass;
		id = getJTextFieldUserId().getText();
		pass = getJPasswordField().getPassword();

		if(id == null || pass == null) {
			JOptionPane.showMessageDialog(getJFrame(), "All fields must be filled in.");

			return false;
		}
		return true;
	}

	/** MAIN METHOD	 */
	public static void main(String[] args) {
		LoginForm loginWindow = new LoginForm();
		loginWindow.getJFrame().setVisible(true);
	}

}


