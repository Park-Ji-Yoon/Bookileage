package book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import user.UserDAO;

public class BookDAO {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";

	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB �н�����

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
	
	public Vector getBookList(String logined_id) {

		Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from book where id=? order by id asc";
			ps = con.prepareStatement(sql);
			ps.setString(1, logined_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int book_number = rs.getInt("book_number");
				String book_name = rs.getString("book_name");
				String id = rs.getString("id");
				String publisher = rs.getString("publisher");
				String author = rs.getString("author");
				int check = rs.getInt("check");
				Date borrow_date = rs.getDate("borrow_date");
				String genre = rs.getString("genre");

				Vector row = new Vector();
				row.add(book_number);
				row.add(book_name);
				row.add(id);
				row.add(publisher);
				row.add(author);
				row.add(check);
				row.add(borrow_date);
				row.add(genre);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()
}
