package �ļ�ϵͳ;

import java.awt.EventQueue;

public class main {
	public static void main(String[] args) {/*��������*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();/*�򿪵�¼����*/
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}