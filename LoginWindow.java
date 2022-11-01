import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {
	
	WorkerTransactions workerTransactions = new WorkerTransactions();

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JLabel lblmessageField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWindow() {
		
		setTitle("Login Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(178, 95, 169, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(72, 93, 96, 19);
		contentPane.add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 140, 169, 19);
		contentPane.add(passwordField);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(72, 138, 96, 19);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				lblmessageField.setText("");
				
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				boolean LoginSuccess = workerTransactions.getLoggedIn(username, password);
				
				if(LoginSuccess) {
				
					EmployeesPanel employeesPanel = new EmployeesPanel();
					setVisible(false);
					
					employeesPanel.setVisible(true);
					//System.exit(0);
					
				}else {
					lblmessageField.setText("Login Failed. Please check your username or password");
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(212, 219, 110, 25);
		contentPane.add(btnLogin);
		
		lblmessageField = new JLabel("");
		lblmessageField.setHorizontalAlignment(SwingConstants.CENTER);
		lblmessageField.setForeground(new Color(0, 0, 0));
		lblmessageField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmessageField.setBounds(72, 180, 394, 25);
		contentPane.add(lblmessageField);
	}
}
