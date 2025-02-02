package main;

import javax.swing.JFrame;

public class MainClass extends JFrame{
	public static MainFrame main_frame;
	
	public static void main(String[] args) {
		main_frame = new MainFrame();
	}
	public MainClass() {
		super("도서관");
		
	}
}
class MainFrame extends JFrame {
	
	static LoginPanel login_panel; // 로그인 선택 화면 패널
	static MyPagePanel my_page_panel; // 로그인 성공 후 -> 마이페이지
	
	public MainFrame() {
		// panel들 초기화
		login_panel = new LoginPanel();
		login_panel.setVisible(true);
		add(login_panel); 
		
		my_page_panel = new MyPagePanel();
		my_page_panel.setVisible(false);
		add(my_page_panel); 
		
		// MainFrame 속성 설정
		setTitle("도미노(도서관 미림 노리방)"); // JFrame 생성 및 타이틀 설정
		setBounds(50, 50, 1280, 720); // JFrame 위치와 크기 (x, y, w, h)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x누르면 닫힘
		setResizable(false);
		getContentPane().setLayout(null); // 프레임 레이아웃 null로 설정
		setVisible(true);
	}

	public static LoginPanel getLogin_panel() {
		return login_panel;
	}

	public static MyPagePanel getMy_page_panel() {
		return my_page_panel;
	}
}