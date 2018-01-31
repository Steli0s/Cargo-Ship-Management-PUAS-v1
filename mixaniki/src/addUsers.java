

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class addUsers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable_Show_user;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private JTextField txtName;
	private JTextField txtId;
	Connection conn = null;
	Statement pst = null;
	private JLabel lblDipart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					addUsers frame = new addUsers();
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

	public ArrayList<adduser_call> getAddUserList() {

		ArrayList<adduser_call> AddUserList = new ArrayList<adduser_call>();
		Connection conn = MySqlConnect.ConnectDB();

		String query = "SELECT * FROM Users";
		Statement st;
		java.sql.ResultSet rs;

		try {

			st = conn.createStatement();
			rs = st.executeQuery(query);
			adduser_call AddUser_call;

			while (rs.next()) {
				AddUser_call = new adduser_call(rs.getInt("id"), rs.getString("fullname"), rs.getString("username"),
						rs.getString("password"), rs.getString("dipart"));
				AddUserList.add(AddUser_call);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return AddUserList;
	}

	// Emfanisis Dedomenon sto JTable

	public void Show_Tmimata_In_JTabale() {

		ArrayList<adduser_call> list = getAddUserList();
		DefaultTableModel model = (DefaultTableModel) jTable_Show_user.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getName();
			row[2] = list.get(i).getUsename();
			row[3] = list.get(i).getPassword();
			row[4] = list.get(i).getDipart().toString();

			model.addRow(row);
		}
	}

	public void executeSQlQuery(String query, String message) {
		Connection conn = MySqlConnect.ConnectDB();
		Statement st;
		try {
			st = conn.createStatement();
			if ((st.executeUpdate(query)) == 1) {
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_user.getModel();
				model.setRowCount(0);
				Show_Tmimata_In_JTabale();

				JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public addUsers() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(addUsers.class.getResource("")));

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1359, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "CEO", "Manager","Guest" }));

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtName.getText().equals("") || txtUsername.getText().equals("")
							|| txtPassword.getText().equals("")) {
						String message = "Add USe ";
						JOptionPane.showMessageDialog(new JFrame(), message, "Simpliroste ola ta kena", JOptionPane.ERROR_MESSAGE);

					} else {
						pst.executeUpdate("insert into Users (fullname, username,password,dipart) VALUES ('"
								+ txtName.getText() + "','" + txtUsername.getText() + "','" + txtPassword.getText()
								+ "','" + comboBox.getSelectedItem().toString() + "')");
					}
				}

				catch (SQLException e) {
					System.out.println("Exception:" + e);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_user.getModel();
				model.setRowCount(0);
				Show_Tmimata_In_JTabale();
				txtName.setText("");
				txtId.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				comboBox.setSelectedItem("");

			}

		});
		
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtName.getText().equals("") || txtUsername.getText().equals("")
							|| txtPassword.getText().equals("") || comboBox.getSelectedItem().toString().equals("")) {
						String message = "Simpliroste ola ta kena";
						JOptionPane.showMessageDialog(new JFrame(), message, "Simpliroste ola ta kena", JOptionPane.ERROR_MESSAGE);

					} else
						pst.executeUpdate("UPDATE Users SET fullname='" + txtName.getText() + "', username='"
								+ txtUsername.getText() + "', password='" + txtPassword.getText() + "', dipart='"
								+ comboBox.getSelectedItem().toString() + "' WHERE id = '" + txtId.getText() + "'");

				} catch (SQLException e2) {
					System.out.println("Exception:" + e2);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_user.getModel();
				model.setRowCount(0);
				Show_Tmimata_In_JTabale();
				txtName.setText("");
				txtId.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				comboBox.setSelectedItem("");

			}

		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtName.getText().equals("") || txtUsername.getText().equals("")
							|| txtPassword.getText().equals("")) {
						String message = "Add User";
						JOptionPane.showMessageDialog(new JFrame(), message, "ÅRROR", JOptionPane.ERROR_MESSAGE);

					} else
						pst.executeUpdate("DELETE FROM `Users` WHERE id = " + txtId.getText());

				} catch (SQLException e2) {
					System.out.println("Exception:" + e2);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_user.getModel();
				model.setRowCount(0);
				Show_Tmimata_In_JTabale();
				txtName.setText("");
				txtId.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				comboBox.setSelectedItem("");

			}

		});
		
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblName = new JLabel("Full Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtPassword = new JTextField();
		txtPassword.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);

		JLabel label = new JLabel("Add User");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblDipart = new JLabel("Dikeomata:");
		lblDipart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDipart.setHorizontalAlignment(SwingConstants.RIGHT);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
										.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 114,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 114,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblId, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(lblName, Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE, 114,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(lblDipart, GroupLayout.PREFERRED_SIZE, 165,
														GroupLayout.PREFERRED_SIZE))
										.addGap(108)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 262,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 262,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 262,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 262,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 264,
														GroupLayout.PREFERRED_SIZE))
										.addGap(80))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE)
										.addGap(105)))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(397).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(32)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)
										.addGap(7))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(txtPassword,
										GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPassword,
												GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDipart, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(44)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
				.addGap(49)));

		jTable_Show_user = new JTable();
		jTable_Show_user.setSurrendersFocusOnKeystroke(true);
		jTable_Show_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get The Index Of The Slected Row
				int i = jTable_Show_user.getSelectedRow();

				TableModel model = jTable_Show_user.getModel();

				// Display Slected Row In JTexteFields
				txtId.setText(model.getValueAt(i, 0).toString());

				txtName.setText(model.getValueAt(i, 1).toString());

				txtUsername.setText(model.getValueAt(i, 2).toString());

				txtPassword.setText(model.getValueAt(i, 3).toString());

				comboBox.setSelectedItem(model.getValueAt(i, 4).toString());

			}
		});
		jTable_Show_user.setFillsViewportHeight(true);
		jTable_Show_user.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "\u039F\u03BD\u03BF\u03BC\u03B1\u03C4\u03B5\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF",
						"Username", "Password", "\u0394\u03B9\u03BA\u03B1\u03B9\u03CE\u03BC\u03B1\u03C4\u03B1" }));
		scrollPane.setViewportView(jTable_Show_user);
		Show_Tmimata_In_JTabale();

		contentPane.setLayout(gl_contentPane);
	}
}
