package main;

import javax.swing.JFrame;

public class MainClass extends JFrame{
	public static MainFrame main_frame;
	
	public static void main(String[] args) {
		main_frame = new MainFrame();
	}
	public MainClass() {
		super("������");
		
	}
}
class MainFrame extends JFrame {
	
	static LoginPanel login_panel; // �α��� ���� ȭ�� �г�
	
	public MainFrame() {
		// panel�� �ʱ�ȭ
		login_panel = new LoginPanel();
		login_panel.setVisible(true);
		// ���� login_panel�� �߰���
		add(login_panel); 
		
		// MainFrame �Ӽ� ����
		setTitle("���̳�(������ �̸� �븮��)"); // JFrame ���� �� Ÿ��Ʋ ����
		setBounds(50, 50, 1280, 720); // JFrame ��ġ�� ũ�� (x, y, w, h)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x������ ����
		setResizable(false);
		getContentPane().setLayout(null); // ������ ���̾ƿ� null�� ����
		setVisible(true);
	}

	public static LoginPanel getLogin_panel() {
		return login_panel;
	}

}
