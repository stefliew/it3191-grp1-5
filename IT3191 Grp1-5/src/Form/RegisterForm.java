package Form;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.User;

public class RegisterForm {

	private JFrame jFrame = null;  
	private JPanel jContentPane = null;
	private JLabel jLabelName = null;
	private JLabel jLabelPassword = null;
	private JLabel jLabelCfmPassword = null;
	private JTextField jTextFieldUserId = null;
	private JPasswordField jPasswordField = null;
	private JPasswordField jPasswordCfmField = null;
	private JButton jButtonExit = null;
	private JButton jButtonSubmit = null;

	private User userObj;
	private String id, pwd;
	private boolean dbOk;

	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new java.awt.Dimension(510,300));
			jFrame.setTitle("Register");
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
			jLabelCfmPassword = new JLabel();
			jLabelCfmPassword.setBounds(new java.awt.Rectangle(70,90,110,30));
			jLabelCfmPassword.setText("Confirm Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelName, null);
			jContentPane.add(jLabelPassword, null);
			jContentPane.add(jLabelCfmPassword, null);
			jContentPane.add(getJTextFieldUserId (), null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getJPasswordCfmField(), null);
			jContentPane.add(getJButtonExit(), null);
			jContentPane.add(getJButtonSubmit(), null);	
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

	private JPasswordField getJPasswordCfmField() {
		if (jPasswordCfmField == null) {
			jPasswordCfmField = new JPasswordField();
			jPasswordCfmField.setBounds(new java.awt.Rectangle(190,90,125,30));
		}
		return jPasswordCfmField;	
	}



	/*** This method initializes jButtonExit		 */
	private JButton getJButtonExit() {
		if (jButtonExit == null) {
			jButtonExit = new JButton();
			jButtonExit.setBounds(new java.awt.Rectangle(419,231,75,30));
			jButtonExit.setText("Back");
			jButtonExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(getJFrame(), 
							"Do you want to return to the login screen?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION){
						getJFrame().dispose();
						LoginForm loginWindow = new LoginForm();
						loginWindow.getJFrame().setVisible(true);
					}
				}
			});
		}
		return jButtonExit;
	}

	/*** This method initializes jButtonSubmit		 */
	private JButton getJButtonSubmit() {
		if (jButtonSubmit == null) {
			jButtonSubmit = new JButton();
			jButtonSubmit.setBounds(new java.awt.Rectangle(120,120,76,30));
			jButtonSubmit.setText("Submit");
			jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String id = getJTextFieldUserId ().getText();
					String pwd = new String(getJPasswordField().getPassword());

					if (validateInput()) {

						User userObj = new User(id,pwd);
						try {
							dbOk = userObj.createUser();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if (dbOk) {
							JOptionPane.showMessageDialog(getJFrame(), "Success! You may now log in with your new user id.");
							getJFrame().dispose();
							LoginForm loginWindow = new LoginForm();
							loginWindow.getJFrame().setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(getJFrame(), "Error - User id already exists.");
						}
					}
				}
			});
		}
		return jButtonSubmit;
	}

	private boolean validateInput() {
		String id;
		char[] pass, cfmpass;
		id = getJTextFieldUserId().getText();
		pass = getJPasswordField().getPassword();
		cfmpass = getJPasswordCfmField().getPassword();

		if(id.length() ==0 || pass.length == 0 || cfmpass.length == 0) {
			JOptionPane.showMessageDialog(getJFrame(), "All fields must be filled in.");

			return false;
		}
		else {
			if (pass.length == cfmpass.length) {

				boolean check = false;
				for (int i = 0; i < pass.length; i++) {
					if (pass[i] != cfmpass[i]) {
						JOptionPane.showMessageDialog(getJFrame(), "Both password fields must match.");
						return false;
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(getJFrame(), "Both password fields must match.");
				return false;
			}
		}
		return true;
	}

	/** MAIN METHOD	 */
	public static void main(String[] args) {
		RegisterForm myWindow = new RegisterForm();
		myWindow.getJFrame().setVisible(true);
	}

}


