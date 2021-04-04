package user;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import user.UserDAO;
import user.UserDTO;

public class UserProc extends JFrame implements ActionListener {

	JPanel p;
	JTextField tf_id, tf_name, tf_borrow_cnt, tf_return_cnt, tf_overdue_cnt, tf_maximum, tf_email;
	JPasswordField pf_password; // ��й�ȣ
	JButton btnInsert, btnCancel, btnUpdate, btnDelete; // ����, ���, ���� , Ż�� ��ư

	GridBagLayout gb;
	GridBagConstraints gbc;
	UserList mList;

	public UserProc() { // ���Կ� ������
		createUI(); // UI�ۼ����ִ� �޼ҵ�
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		// ������ �� �� ������
		ImageIcon icon = new ImageIcon("images/logo_2.png");
		setIconImage(icon.getImage());
	}// ������

	public UserProc(UserList mList) { // ���Կ� ������
		createUI(); // UI�ۼ����ִ� �޼ҵ�
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		this.mList = mList;

		// ������ �� �� ������
		ImageIcon icon = new ImageIcon("images/logo_2.png");
		setIconImage(icon.getImage());
	}// ������

	public UserProc(String id, UserList mList) { // ����/������ ������
		createUI();
		btnInsert.setEnabled(false);
		btnInsert.setVisible(false);
		this.mList = mList;

		System.out.println("id=" + id);

		UserDAO dao = new UserDAO();
		UserDTO vMem = dao.getUserDTO(id);
		viewData(vMem);

		// ������ �� �� ������
		ImageIcon icon = new ImageIcon("images/logo_2.png");
		setIconImage(icon.getImage());

	}// id�� ������ ����

	// MemberDTO �� ȸ�� ������ ������ ȭ�鿡 �������ִ� �޼ҵ�
	private void viewData(UserDTO vMem) {

		String id = vMem.getId();
		String name = vMem.getName();
		String password = vMem.getPassword();
		int borrow_cnt = vMem.getBorrow_cnt();
		int return_cnt = vMem.getReturn_cnt();
		int overdue_cnt = vMem.getOverdue_cnt();
		int maximum = vMem.getMaximum();
		String email = vMem.getEmail();
		System.out.print(id + name + password + borrow_cnt + return_cnt + overdue_cnt + maximum + email);

		// ȭ�鿡 ����
		tf_id.setText(id);
		tf_name.setText(name);
		tf_id.setEditable(false); // ���� �ȵǰ�
		pf_password.setText(password); // ��й�ȣ�� �Ⱥ����ش�.
		tf_borrow_cnt.setText(String.valueOf(borrow_cnt));
		tf_return_cnt.setText(String.valueOf(return_cnt));
		tf_maximum.setText(String.valueOf(maximum));
		tf_email.setText(email);
	}// viewData

	private void createUI() {
		this.setTitle("ȸ������");
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// ���̵�
		JLabel id = new JLabel("���̵� ");
		id.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_id = new JTextField(20);
		tf_id.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(id, 0, 0, 1, 1);
		gbAdd(tf_id, 1, 0, 3, 1);

		// �̸�
		JLabel name = new JLabel("�̸� : ");
		name.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_name = new JTextField(20);
		tf_name.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		// �׸���鿡 ���̱�
		gbAdd(name, 0, 1, 1, 1);
		gbAdd(tf_name, 1, 1, 3, 1);

		// ��й�ȣ
		JLabel password = new JLabel("��й�ȣ : ");
		password.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		pf_password = new JPasswordField(20);
//		pfPwd.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(password, 0, 2, 1, 1);
		gbAdd(pf_password, 1, 2, 3, 1);

		// borrow_cnt
		JLabel borrow_cnt = new JLabel("borrow_cnt :");
		borrow_cnt.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_borrow_cnt = new JTextField(20);
		tf_borrow_cnt.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(borrow_cnt, 0, 4, 1, 1);
		gbAdd(tf_borrow_cnt, 1, 4, 3, 1);

		// return_cnt
		JLabel return_cnt = new JLabel("return_cnt : ");
		return_cnt.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_return_cnt = new JTextField(20);
		tf_return_cnt.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		// �׸���鿡 ���̱�
		gbAdd(return_cnt, 0, 5, 1, 1);
		gbAdd(tf_return_cnt, 1, 5, 3, 1);

		// overdue_cnt
		JLabel overdue_cnt = new JLabel("overdue_cnt : ");
		overdue_cnt.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_overdue_cnt = new JTextField(5);
		tf_overdue_cnt.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(overdue_cnt, 0, 6, 1, 1);
		gbAdd(tf_overdue_cnt, 1, 6, 3, 1);
		
		// maximum
		JLabel maximum = new JLabel("maximum : ");
		maximum.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_maximum = new JTextField(5);
		tf_maximum.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(maximum, 0, 7, 1, 1);
		gbAdd(tf_maximum, 1, 7, 3, 1);
		
		// email
		JLabel email = new JLabel("email : ");
		maximum.setFont(new Font("������ũ��� M", Font.PLAIN, 18));
		tf_email = new JTextField(5);
		tf_email.setFont(new Font("������ũ��� L", Font.PLAIN, 18));
		gbAdd(email, 0, 8, 1, 1);
		gbAdd(tf_email, 1, 8, 3, 1);

		// ��ư
		JPanel pButton = new JPanel();
//		pButton.setBackground(new Color(255, 199, 194));
		btnInsert = new JButton("����");
		btnInsert.setFont(new Font("������ũ��� M", Font.PLAIN, 22));
		btnUpdate = new JButton("����");
		btnUpdate.setFont(new Font("������ũ��� M", Font.PLAIN, 22));
		btnDelete = new JButton("Ż��");
		btnDelete.setFont(new Font("������ũ��� M", Font.PLAIN, 22));
		btnCancel = new JButton("���");
		btnCancel.setFont(new Font("������ũ��� M", Font.PLAIN, 22));
		pButton.add(btnInsert);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancel);
		gbAdd(pButton, 0, 10, 5, 1);
		
		btnInsert.setBorderPainted(false);
		btnInsert.setContentAreaFilled(false);
		btnInsert.setFocusPainted(false);
		btnInsert.setOpaque(false);
		
		btnUpdate.setBorderPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setOpaque(false);
		
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setFocusPainted(false);
		btnCancel.setOpaque(false);
		
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setOpaque(false);

		// ��ư�� �����⸦ ������
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);

		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //���α׷�����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose(); //����â�� �ݴ´�.
		setBounds(681, 275, 500, 500);

	}// createUI

	// �׸���鷹�̾ƿ��� ���̴� �޼ҵ�
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
	}// gbAdd

	public static void main(String[] args) {
		new UserProc();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnInsert) {
			insertMember();
			System.out.println("insertMember() ȣ�� ����");
		} else if (ae.getSource() == btnCancel) {
			this.dispose(); // â�ݱ� (����â�� ����)
			// system.exit(0)=> ���� ��� ��� â�� �� ����
		} else if (ae.getSource() == btnUpdate) {
			UpdateMember();
		} else if (ae.getSource() == btnDelete) {
			// int x = JOptionPane.showConfirmDialog(this,"���� �����Ͻðڽ��ϱ�?");
			int x = JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.OK_OPTION) {
				deleteMember();
			} else {
				JOptionPane.showMessageDialog(this, "������ ����Ͽ����ϴ�.");
			}
		}

		// jTable���� ���� �޼ҵ� ȣ��
		mList.jTableRefresh();

	}// actionPerformed

	private void deleteMember() {
		String id = tf_id.getText();
		if (id.length() == 0) { // ���̰� 0�̸�
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �� �Է��ϼ���!");
			return; // �޼ҵ� ��
		}
		// System.out.println(mList);
		UserDAO dao = new UserDAO();
		boolean ok = dao.deleteUser(id);

		if (ok) {
			JOptionPane.showMessageDialog(this, "�����Ϸ�");
			dispose();

		} else {
			JOptionPane.showMessageDialog(this, "��������");

		}

	}// deleteMember

	private void UpdateMember() {

		// 1. ȭ���� ������ ��´�.
		UserDTO dto = getViewData();
		// 2. �������� DB�� ����
		UserDAO dao = new UserDAO();
		boolean ok = dao.updateUser(dto);

		if (ok) {
			JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "��������: ���� Ȯ���ϼ���");
		}
	}

	private void insertMember() {

		// ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
		UserDTO dto = getViewData();
		UserDAO dao = new UserDAO();
		boolean ok = dao.insertUser(dto);

		if (ok) {
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "������ ���������� ó������ �ʾҽ��ϴ�.");
		}
	}// insertMember

	public UserDTO getViewData() {

		// ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
		UserDTO dto = new UserDTO();
		String id = tf_id.getText();
		String name = tf_name.getText();
		String password = pf_password.getText();
		int borrow_cnt = Integer.parseInt(tf_borrow_cnt.getText());
		int return_cnt = Integer.parseInt(tf_return_cnt.getText());
		int overdue_cnt = Integer.parseInt(tf_overdue_cnt.getText());
		int maximum = Integer.parseInt(tf_maximum.getText());
		String email = tf_email.getText();

		// dto�� ��´�.
		dto.setId(id);
		dto.setName(name);
		dto.setPassword(password);
		dto.setBorrow_cnt(borrow_cnt);
		dto.setReturn_cnt(return_cnt);
		dto.setOverdue_cnt(overdue_cnt);
		dto.setMaximum(maximum);
		dto.setEmail(email);

		return dto;
	}
}// end