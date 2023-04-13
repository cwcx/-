package 文件系统;

import java.awt.EventQueue;

public class main {
	public static void main(String[] args) {/*启动函数*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();/*打开登录界面*/
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}