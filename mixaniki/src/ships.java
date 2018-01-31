import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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

public class ships extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable_Show_Ships;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField txtCountry;
	private JTextField txtExpDate;
	private JTextField txtBarcode;
	private JTextField txtFood;
	private JTextField txtShip;
	private JTextField txtCompany;
	private JTextField txtQuantity;
	private JTextField txtId;
	Connection conn = null;
	Statement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ships frame = new ships();
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

	public ArrayList<ships_call> getShipsList() {

		ArrayList<ships_call> ShipsList = new ArrayList<ships_call>();
		Connection conn = MySqlConnect.ConnectDB();

		String query = "SELECT * FROM `ships`";
		Statement st;
		java.sql.ResultSet rs;

		try {

			st = conn.createStatement();
			rs = st.executeQuery(query);
			ships_call Ships_call;

			while (rs.next()) {
				Ships_call = new ships_call(rs.getInt("id"), rs.getString("Ship"), rs.getString("Food"),
						rs.getString("Barcode"), rs.getString("ExpDate"), rs.getString("Country"), rs.getString("Company"),
						rs.getString("Quantity"));
				ShipsList.add(Ships_call);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return ShipsList;

	}

	// Emfanisis Dedomenon sto JTable

	public void Show_Ships_In_JTabele() {

		ArrayList<ships_call> list = getShipsList();
		DefaultTableModel model = (DefaultTableModel) jTable_Show_Ships.getModel();
		Object[] row = new Object[10];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getShip();
			row[2] = list.get(i).getFood();
			row[3] = list.get(i).getBarcode();
			row[4] = list.get(i).getExpDate();
			row[5] = list.get(i).getCountry();
			row[6] = list.get(i).getCompany();
			row[7] = list.get(i).getQuantity();
			  
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
				DefaultTableModel model = (DefaultTableModel) jTable_Show_Ships.getModel();
				model.setRowCount(0);
				
				Show_Ships_In_JTabele();

				JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ships() {
		

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1359, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				try {

					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtShip.getText().equals("") || txtFood.getText().equals("") || txtBarcode.getText().equals("")
							|| txtExpDate.getText().equals("") || txtCountry.getText().equals("") || txtCompany.getText().equals("") || txtQuantity.getText().equals("")) {
						String message = "Please fill all fields ";
						JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);

					} else {
						String query2 = "insert into ships (ship,food,barcode,expdate,country,company,quantity) values(?,?,?,?,?,?,?)";
						PreparedStatement pst1 = conn.prepareStatement(query2);
						pst1.setString(1, txtShip.getText());
						pst1.setString(2, txtFood.getText());
						pst1.setString(3, txtBarcode.getText());
						pst1.setString(4, txtCountry.getText());
						pst1.setString(5, txtExpDate.getText());
						pst1.setString(6, txtCompany.getText());
						pst1.setString(7, txtQuantity.getText());
						pst1.executeUpdate();

					}
				}

				catch (SQLException e) {
					System.out.println("Exception:" + e);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_Ships.getModel();
				model.setRowCount(0);
				Show_Ships_In_JTabele();
				txtId.setText("");
				txtShip.setText("");
				txtFood.setText("");
				txtBarcode.setText("");
				txtCountry.setText("");
				txtExpDate.setText("");
				txtCompany.setText("");
				txtQuantity.setText("");
				

			}

		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtShip.getText().equals("") || txtFood.getText().equals("") || txtBarcode.getText().equals("")
							|| txtExpDate.getText().equals("") || txtCountry.getText().equals("")
							) {
						String message = "Please fill all fields ";
						JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);

					} else
						pst.executeUpdate("UPDATE ships SET ship='" + txtShip.getText() + "', food='"
								+ txtFood.getText() + "', barcode='" + txtBarcode.getText() + "', country='"
								+ txtCountry.getText() + "', expdate='" + txtExpDate.getText() + "', company='"
								+ txtCompany.getText() + "', quantity='" + txtQuantity.getText()  + "' WHERE id = '"
								+ txtId.getText() + "'");

				} catch (SQLException e2) {
					System.out.println("Exception:" + e2);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_Ships.getModel();
				model.setRowCount(0);
				Show_Ships_In_JTabele();
				txtId.setText("");
				txtShip.setText("");
				txtFood.setText("");
				txtBarcode.setText("");
				txtCountry.setText("");
				txtExpDate.setText("");
				txtCompany.setText("");
				txtQuantity.setText("");
				

			}

		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection conn = MySqlConnect.ConnectDB();
					Statement pst = conn.createStatement();
					if (txtShip.getText().equals("") || txtFood.getText().equals("") || txtBarcode.getText().equals("")
							|| txtExpDate.getText().equals("") || txtCountry.getText().equals("")) {
						String message = "Please fill all fields ";
						JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);

					} else
						pst.executeUpdate("DELETE FROM `ships` WHERE id = " + txtId.getText());

				} catch (SQLException e2) {
					System.out.println("Exception:" + e2);

				}
				// refresh jtable data
				DefaultTableModel model = (DefaultTableModel) jTable_Show_Ships.getModel();
				model.setRowCount(0);
				Show_Ships_In_JTabele();
				txtId.setText("");
				txtShip.setText("");
				txtFood.setText("");
				txtBarcode.setText("");
				txtCountry.setText("");
				txtExpDate.setText("");
				txtCompany.setText("");
				txtQuantity.setText("");
				

			}

		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblExpData = new JLabel("Exp. Date:");
		lblExpData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpData.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblFood = new JLabel("Food:");
		lblFood.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFood.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblShip = new JLabel("Ship:");
		lblShip.setHorizontalAlignment(SwingConstants.RIGHT);
		lblShip.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtCountry = new JTextField();
		txtCountry.setColumns(10);

		txtExpDate = new JTextField();
		txtExpDate.setText("2018-05-22");
		txtExpDate.setColumns(10);

		txtBarcode = new JTextField();
		txtBarcode.setColumns(10);

		txtFood = new JTextField();
		txtFood.setColumns(10);

		txtShip = new JTextField();
		txtShip.setColumns(10);

		txtCompany = new JTextField();
		txtCompany.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);

		JLabel lblShipList = new JLabel("Ship List");
		lblShipList.setHorizontalAlignment(SwingConstants.CENTER);
		lblShipList.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(39, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblFood, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBarcode, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblExpData, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCountry, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblId, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblShip, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblCompany, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCompany, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtExpDate, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBarcode, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFood, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtShip, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
							.addGap(82))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(73)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(397)
					.addComponent(lblShipList, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(447, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShipList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtShip, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(7))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblShip, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtFood, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFood, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(txtBarcode, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblBarcode, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(5)
									.addComponent(txtExpDate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblExpData, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(lblCountry, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCompany, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCompany, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
							.addGap(97)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
					.addGap(49))
		);

		jTable_Show_Ships = new JTable();
		jTable_Show_Ships.setSurrendersFocusOnKeystroke(true);
		jTable_Show_Ships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get The Index Of The Slected Row
				int i = jTable_Show_Ships.getSelectedRow();

				TableModel model = jTable_Show_Ships.getModel();

				// Display Slected Row In JTexteFields
				txtId.setText(model.getValueAt(i, 0).toString());

				txtShip.setText(model.getValueAt(i, 1).toString());

				txtFood.setText(model.getValueAt(i, 2).toString());

				txtBarcode.setText(model.getValueAt(i, 3).toString());

				txtExpDate.setText(model.getValueAt(i, 4).toString());

				txtCountry.setText(model.getValueAt(i, 5).toString());
				
				txtCompany.setText(model.getValueAt(i, 6).toString());
				
				txtQuantity.setText(model.getValueAt(i, 7).toString());
				
			}
		});
		jTable_Show_Ships.setFillsViewportHeight(true);
		jTable_Show_Ships.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ship", "Food", "Barcode", "Country",  "Exp. Date", "Company", "Quntity"
			}
		));
		scrollPane.setViewportView(jTable_Show_Ships);
		Show_Ships_In_JTabele();

		contentPane.setLayout(gl_contentPane);
	}

}
