package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

//DB ó��
public class UserDAO {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";

	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB �н�����
//	Manager_List mList;

	public UserDAO() {

	}

//	public UserDAO(Manager_List mList) {
//		this.mList = mList;
//		System.out.println("DAO=>" + mList);
//	}

	/** DB���� �޼ҵ� */
	public Connection getConn() {
		Connection con = null;

		try {
			Class.forName(DRIVER); // 1. ����̹� �ε�
			con = DriverManager.getConnection(URL, USER, PASS); // 2. ����̹� ����

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/** �ѻ���� ȸ�� ������ ��� �޼ҵ� */
	public UserDTO getUserDTO(String id) {

		UserDTO dto = new UserDTO();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from user where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setBorrow_cnt(rs.getInt("borrow_cnt"));
				dto.setReturn_cnt(rs.getInt("return_cnt"));
				dto.setOverdue_cnt(rs.getInt("overdue_cnt"));
				dto.setMaximum(rs.getInt("maximum"));
				dto.setEmail(rs.getString("email"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	/** �������Ʈ ��� */
	public Vector getUserList() {

		Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from user order by id asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int borrow_cnt = rs.getInt("borrow_cnt");
				int return_cnt = rs.getInt("return_cnt");
				int overdue_cnt = rs.getInt("overdue_cnt");
				int maximum = rs.getInt("maximum");
				String email = rs.getString("email");

				Vector row = new Vector();
				row.add(id);
				row.add(name);
				row.add(password);
				row.add(borrow_cnt);
				row.add(return_cnt);
				row.add(overdue_cnt);
				row.add(maximum);
				row.add(email);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()

	/** ȸ�� ��� */
	public boolean insertUser(UserDTO dto) {
		boolean ok = false;

		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			// ȸ��
			con = getConn();
			String sql = "insert into user(" + "id, name, password, borrow_cnt, return_cnt, overdue_cnt, maximum, email) "
					+ "values(?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPassword());
			ps.setInt(4, dto.getBorrow_cnt());
			ps.setInt(5, dto.getReturn_cnt());
			ps.setInt(6, dto.getOverdue_cnt());
			ps.setInt(7, dto.getMaximum());
			ps.setString(8, dto.getEmail());
			
			int r = ps.executeUpdate(); // ���� -> ����
			if (r > 0)
				ok = true; // ������ �����Ǹ� ok���� true�� ����
			if (r > 0) {
				System.out.println("���� ����");
				ok = true;
			} else {
				System.out.println("���� ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;
	}// insertMmeber

	/** ȸ������ ���� */
	public boolean updateUser(UserDTO vMem) {
		System.out.println("dto=" + vMem.toString());
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConn();
			String sql = "update user set id=?, name=?, email=? " + "where password=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, vMem.getId());
			ps.setString(2, vMem.getName());
			ps.setString(3, vMem.getEmail());
			ps.setString(4, vMem.getPassword());

			int r = ps.executeUpdate(); // ���� -> ����
			// 1~n: ���� , 0 : ����

			if (r > 0)
				ok = true; // ������ �����Ǹ� ok���� true�� ����

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;
	}

	/**
	 * ȸ������ ���� : tip: �ǹ������� ȸ�������� Delete ���� �ʰ� Ż�𿩺θ� üũ�Ѵ�.
	 */
	public boolean deleteUser(String id) {

		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from user where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			int r = ps.executeUpdate(); // ���� -> ����
			if (r > 0)
				ok = true; // ������;
			
		} catch (Exception e) {
			System.out.println(e + "-> �����߻�");
		}
		return ok;
	}

	/** DB������ �ٽ� �ҷ����� */
	public void userSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from user order by id asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel�� �ִ� ������ �����
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8) };

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public ArrayList<String> getIdList() {

		ArrayList<String> list = new ArrayList<String>();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("id"));				
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}