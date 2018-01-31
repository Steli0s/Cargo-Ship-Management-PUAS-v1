import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class CEOLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1350132800108469293L;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					CEOLogin frame = new CEOLogin();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CEOLogin() {
		setBackground(new Color(192, 192, 192));
		setResizable(false);
		setTitle("Human Resources Managment Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CEOLogin.class.getResource("")));
		setForeground(new Color(204, 204, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblUsername = new JLabel("Username: ");

		JLabel lblPassword = new JLabel("Password: ");

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					conn = MySqlConnect.ConnectDB(); 
					String Sql = "Select * from Users where username=? and password=? and dipart = 'CEO'";
					pst = conn.prepareStatement(Sql);
					pst.setString(1, txtUsername.getText());
					pst.setString(2, txtPassword.getText());
					rs = pst.executeQuery();

					int count = 0;
					while (rs.next()) {
						count = count + 1;
					}

					if (count == 1) {
						CEOMain w = new CEOMain();
						w.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Access Denied, " + "Invalid username or password",

								"Access Denied", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					pst.close();
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, e);
				}
				dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login w = new Login();
				w.setVisible(true);
				dispose();
			}
		});

		JLabel lblCeoLoginForm = new JLabel("CEO Login Form");
		lblCeoLoginForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCeoLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CEOLogin.class.getResource("/img/CSM-LOGO.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCeoLoginForm, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtUsername)
										.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
							.addGap(46)))
					.addGap(28))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCeoLoginForm, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(128))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
