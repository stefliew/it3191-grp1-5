package Form;
import java.util.Arrays;
import java.util.Base64;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*; 

public class EncryptForm {

	private JFrame jFrame = null;  
	private JPanel jContentPane = null;
	private JLabel jLabelClearText = null;
	private JLabel jLabelKey = null;
	private JLabel jLabelCipherText = null;
	private JLabel jLabelMode = null;
	//private JLabel jLabelCipherText2 = null;
	private JTextArea jTextAreaCipherText = null;
	private JTextArea jTextAreaClearText = null;
	private JTextField jTextFieldKey = null;
	private JButton jButtonDecrypt = null;
	private JButton jButtonEncrypt = null;
	private JButton jButtonSubmit = null;
	private JButton jButtonExit = null;
	private ButtonGroup jBGEncryptMode = null;
	private JRadioButton jRBEncrypt = null;
	private JRadioButton jRBDecrypt = null;

	//private User userObj;
	//private String id, pwd;	
	//private boolean dbOk;
	private boolean isEncryptMode = true;

	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new java.awt.Dimension(510,300));
			jFrame.setTitle("Encryption");
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;	
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jBGEncryptMode = new ButtonGroup();
			jBGEncryptMode.add(getJRBEncrypt());
			jBGEncryptMode.add(getJRBDecrypt());
			jLabelClearText = new JLabel();
			jLabelClearText.setBounds(new java.awt.Rectangle(70,30,86,30));
			jLabelClearText.setText("Input:");
			jLabelKey = new JLabel();
			jLabelKey.setBounds(new java.awt.Rectangle(70,60,86,30));
			jLabelKey.setText("Passphrase:");
			jLabelCipherText = new JLabel();
			jLabelCipherText.setBounds(new java.awt.Rectangle(70,90,86,30));
			jLabelCipherText.setText("Output:");
			jLabelMode = new JLabel();
			jLabelMode.setBounds(new java.awt.Rectangle(190,0,120,30));
			jLabelMode.setText("Encrypt Mode");
			//jLabelCipherText2 = new JLabel();
			//jLabelCipherText2.setBounds(new java.awt.Rectangle(165,90,200,31));
			//jLabelCipherText2.setText("-");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelClearText, null);
			jContentPane.add(jLabelKey, null);
			jContentPane.add(jLabelCipherText, null);
			jContentPane.add(jLabelMode, null);
			//jContentPane.add(jLabelCipherText2, null);
			jContentPane.add(getJTextAreaCipherText(), null);
			jContentPane.add(getJTextAreaClearText(), null);
			jContentPane.add(getJTextFieldKey(), null);
			//jContentPane.add(getJButtonDecrypt(), null);
			//jContentPane.add(getJButtonEncrypt(), null);	
			jContentPane.add(getJButtonSubmit(), null);	
			jContentPane.add(getJButtonExit(), null);	
			jContentPane.add(getJRBEncrypt(), null);	
			jContentPane.add(getJRBDecrypt(), null);	
		}
		return jContentPane;	
	}
	
	private JRadioButton getJRBEncrypt() {
		if (jRBEncrypt == null) {
			jRBEncrypt = new JRadioButton();
			jRBEncrypt.setText("Encrypt");
			jRBEncrypt.setBounds(new java.awt.Rectangle(120,120,120,30));
			jRBEncrypt.setSelected(true);
			
			jRBEncrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					isEncryptMode = true;
//					jLabelClearText.setText("Clear text: ");
//					jLabelCipherText.setText("Cipher text: ");
					jLabelMode.setText("Encrypt Mode");
					jButtonSubmit.setText("Encrypt");
				}
			});
		}
		return jRBEncrypt;
	}
	
	private JRadioButton getJRBDecrypt() {
		if (jRBDecrypt == null) {
			jRBDecrypt = new JRadioButton();
			jRBDecrypt.setText("Decrypt");
			jRBDecrypt.setBounds(new java.awt.Rectangle(240,120,120,30));
			
			jRBDecrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					isEncryptMode = false;
//					jLabelClearText.setText("Clear text: ");
//					jLabelCipherText.setText("Cipher text: ");
					jLabelMode.setText("Decrypt Mode");
					jButtonSubmit.setText("Decrypt");
				}
			});
		}
		return jRBDecrypt;
	}

	private JTextArea getJTextAreaClearText() {
		if (jTextAreaClearText == null) {
			jTextAreaClearText = new JTextArea();
			jTextAreaClearText.setBounds(new java.awt.Rectangle(165,30,125,30));
		}
		return jTextAreaClearText;	
	}

	private JTextField getJTextFieldKey() {
		if (jTextFieldKey == null) {
			jTextFieldKey = new JTextField();
			jTextFieldKey.setBounds(new java.awt.Rectangle(165,60,125,30));
		}
		return jTextFieldKey;	
	}

	private JTextArea getJTextAreaCipherText() {
		if (jTextAreaCipherText == null) {
			jTextAreaCipherText = new JTextArea();
			jTextAreaCipherText.setBounds(new java.awt.Rectangle(165,90,200,30));
		}
		return jTextAreaCipherText;	
	}



	/*** This method initializes jButtonDecrypt		 */
	private JButton getJButtonDecrypt() {
		if (jButtonDecrypt == null) {
			jButtonDecrypt = new JButton();
			jButtonDecrypt.setBounds(new java.awt.Rectangle(260,120,120,30));
			jButtonDecrypt.setText("Decrypt Mode");
			jButtonDecrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					isEncryptMode = false;
					jLabelClearText.setText("Cipher text: ");
					jLabelCipherText.setText("Clear text: ");
					jLabelMode.setText("Decrypt Mode");
					jButtonSubmit.setText("Decrypt");
				}
			});
		}
		return jButtonDecrypt;
	}

	/*** This method initializes jButtonSubmit		 */
	private JButton getJButtonSubmit() {
		if (jButtonSubmit == null) {
			jButtonSubmit = new JButton();
			jButtonSubmit.setBounds(new java.awt.Rectangle(190,150,120,30));
			jButtonSubmit.setText("Encrypt");
			jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String text = getJTextAreaClearText().getText();
					String passphrase = getJTextFieldKey().getText();
					if (getJRBEncrypt().isSelected()) {
						encryptText(text, passphrase);
					}
					else {
						decryptText(text, passphrase);
					}
				}
			});
		}
		return jButtonSubmit;
	}

	/*** This method initializes jButtonEncrypt		 */
	private JButton getJButtonEncrypt() {
		if (jButtonEncrypt == null) {
			jButtonEncrypt = new JButton();
			jButtonEncrypt.setBounds(new java.awt.Rectangle(120,120,120,30));
			jButtonEncrypt.setText("Encrypt Mode");
			jButtonEncrypt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					isEncryptMode = true;
					jLabelClearText.setText("Clear text: ");
					jLabelCipherText.setText("Cipher text: ");
					jLabelMode.setText("Encrypt Mode");
					jButtonSubmit.setText("Encrypt");
				}
			});
		}
		return jButtonEncrypt;
	}


	/*** This method initializes jButtonExit		 */
	private JButton getJButtonExit() {
		if (jButtonExit == null) {
			jButtonExit = new JButton();
			jButtonExit.setBounds(new java.awt.Rectangle(190,180,120,30));
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

	private void encryptText(String text, String passphrase) {
		try {    
			//generate key

			byte[] key = (passphrase /* + username + password*/).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit

			SecretKeySpec specKey = new SecretKeySpec(key, "AES");

			//Create the cipher object and encrypt target text  
			Cipher cipher = Cipher.getInstance("AES");
			System.out.println("Encrypt with " + cipher.getAlgorithm() + "...");
			cipher.init(Cipher.ENCRYPT_MODE, specKey);
			System.out.println("Before : " + text);
			byte[] encrypted = cipher.doFinal(text.getBytes());

			Base64.getEncoder().encodeToString(encrypted);

			String encodedText = Base64.getEncoder().encodeToString(encrypted); 

			//jLabelCipherText2.setText(encodedText);
			jTextAreaCipherText.setText(encodedText);
			System.out.println("After : " + encodedText); 

			//Decrypt using the secret key  
			//cipher.init(Cipher.DECRYPT_MODE, specKey);
			//byte[] decrypted = cipher.doFinal(encrypted);
			//System.out.println("Decrypted : " + new String(decrypted));  
		} catch (Exception e) {  
			e.printStackTrace();   
		}  
	}

	private void decryptText(String text, String passphrase) {
		try {
			//generate the secret key  
			//KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			//SecretKey secretKey = keyGen.generateKey();
			//byte[] bytes = secretKey.getEncoded();
			//SecretKeySpec specKey = new SecretKeySpec(bytes, "AES");  

			byte[] key = (passphrase /* + username + password*/).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit

			SecretKeySpec specKey = new SecretKeySpec(key, "AES");

			// Create the cipher object and encrypt target text  
			Cipher cipher = Cipher.getInstance("AES");

			//System.out.println("Encrypt with " + cipher.getAlgorithm() + "...");

			//cipher.init(Cipher.ENCRYPT_MODE, specKey);
			//String target = getJTextFieldClearText().getText();

			//System.out.println("Before : " + target);

			//byte[] encrypted = cipher.doFinal(target.getBytes());

			//Base64.getEncoder().encodeToString(encrypted);

			//String encodedText = Base64.getEncoder().encodeToString(encrypted); 

			//jLabelCipherText2.setText(encodedText);
			//jTextFieldCipherText.setText(encodedText);

			//System.out.println("After : " + encodedText); 

			//Decrypt using the secret key  

			byte[] decodedBytes = Base64.getDecoder().decode(text);

			cipher.init(Cipher.DECRYPT_MODE, specKey);
			byte[] decrypted = cipher.doFinal(decodedBytes);
			System.out.println("Decrypted : " + new String(decrypted));  
			jTextAreaCipherText.setText(new String(decrypted));

		} catch (Exception e) {  
			e.printStackTrace();   
		}  
	}


	/** MAIN METHOD	 */
	public static void main(String[] args) {
		EncryptForm encryptWindow = new EncryptForm();
		encryptWindow.getJFrame().setVisible(true);
	}

}