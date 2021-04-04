package admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

//DB ó��
public class AdminDAO {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";

	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB �н�����
//	Manager_List mList;

	public AdminDAO() {

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
	public AdminDTO getAdminDTO(String id) {

		AdminDTO dto = new AdminDTO();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from admin where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(2, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setAdmin_code(rs.getInt("admin_code"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}


	/** �������Ʈ ��� */
	public Vector getAdminList() {

		Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from admin order by id asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String password = rs.getString("password");
				int admin_code = rs.getInt("admin_code");


				Vector row = new Vector();
				row.add(name);
				row.add(id);
				row.add(password);
				row.add(admin_code);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()

	/** ȸ�� ��� */
	public boolean insertAdmin(AdminDTO dto) {
		boolean ok = false;

		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			// ȸ��
			con = getConn();
			String sql = "insert into admin(" + "name, id, password, admin_code) "
					+ "values(?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getId());
			ps.setString(3, dto.getPassword());
			ps.setInt(4, dto.getAdmin_code());
			
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
	public boolean updateAdmin(AdminDTO vMem) {
		System.out.println("dto=" + vMem.toString());
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConn();
			String sql = "update admin set name=?, id=?, admin_code=? " + "where password=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, vMem.getName());
			ps.setString(2, vMem.getId());
			ps.setString(3, vMem.getPassword());
			ps.setInt(4, vMem.getAdmin_code());
			
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
	public boolean deleteAdmin(String id) {

		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from admin where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(2, id);
			int r = ps.executeUpdate(); // ���� -> ����
			if (r > 0)
				ok = true; // ������;
			
		} catch (Exception e) {
			System.out.println(e + "-> �����߻�");
		}
		return ok;
	}

	/** DB������ �ٽ� �ҷ����� */
	public void adminSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from admin order by id asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel�� �ִ� ������ �����
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4) };

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> adminSelectAll fail");
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
			String sql = "select * from admin";
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