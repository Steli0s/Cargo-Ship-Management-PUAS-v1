import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1350132800108469293L;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(new Color(192, 192, 192));
		setResizable(false);
		setTitle("Ship Managment Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("")));
		setForeground(new Color(204, 204, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnCeoLogin = new JButton("CEO Login");
		btnCeoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CEOLogin ceolog = new CEOLogin();
				ceolog.setVisible(true);
				dispose();
			}

		});

		JButton btnHrLogin = new JButton("Manager Login");
		btnHrLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManagerLogin ManagerLog = new ManagerLogin();
				ManagerLog.setVisible(true);
				dispose();
			}

		});

		JLabel lblSelectYourDepartment = new JLabel("Select Login Option");
		lblSelectYourDepartment.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSelectYourDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnGuest = new JButton("Guest Login");
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guestLogin GuestLog = new guestLogin();
				GuestLog.setVisible(true);
				dispose();
			}
		});
		btnHrLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		

		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/img/CSM-LOGO.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGuest, GroupLayout.PREFERRED_SIZE, 469, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblSelectYourDepartment, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 17, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnHrLogin, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
										.addComponent(btnCeoLogin, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)))
								.addGap(18)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSelectYourDepartment, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(btnCeoLogin, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnHrLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGuest, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(236, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
