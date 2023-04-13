package 文件系统;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField acctext;
	private JTextField pwtext;
	
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	String user="root";
	String pass="born20010214";
	Connection con=null;
	Statement sta=null;

	public login() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel acc = new JLabel("\u8D26\u53F7\uFF1A");
		acc.setFont(new Font("宋体", Font.PLAIN, 18));
		acc.setBounds(10, 10, 60, 30);
		contentPane.add(acc);
		
		JLabel pw = new JLabel("\u5BC6\u7801\uFF1A");
		pw.setFont(new Font("宋体", Font.PLAIN, 18));
		pw.setBounds(10, 50, 60, 30);
		contentPane.add(pw);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("宋体", Font.PLAIN, 18));
		error.setBounds(10, 90, 276, 30);
		contentPane.add(error);
		
		acctext = new JTextField();
		acctext.setFont(new Font("宋体", Font.PLAIN, 18));
		acctext.setBounds(80, 10, 200, 30);
		acctext.setColumns(10);
		contentPane.add(acctext);
		
		pwtext = new JTextField();
		pwtext.setFont(new Font("宋体", Font.PLAIN, 18));
		pwtext.setBounds(80, 50, 200, 30);
		pwtext.setColumns(10);
		contentPane.add(pwtext);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.setFont(new Font("宋体", Font.PLAIN, 18));
		loginButton.setBounds(20, 130, 97, 30);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("\u6CE8\u518C");
		registerButton.setFont(new Font("宋体", Font.PLAIN, 18));
		registerButton.setBounds(170, 130, 97, 30);
		contentPane.add(registerButton);
		
		try {/*连接数据库*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			error.setText("数据库连接失败！");
		}		
		
		loginButton.addActionListener(new ActionListener() {/*点击登录按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					boolean flag=true;/*用于判断登录是否成功*/
					String id=acctext.getText();/*获取输入的账号*/
					String psw=pwtext.getText();/*获取输入的密码*/
					PreparedStatement pstm = con.prepareStatement("select * from users where acc='"+id+"';");/*查询用户表中是否有该账号*/
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {/*遍历查询结果进行查找*/
						String pw = rs.getString("pw").trim();
						if(pw.equals(psw)){/*如果密码正确*/
							error.setText("登录成功！");
							flag=false;
							if(id.equals("ccj")) {/*管理员*/
								try {
									adm uframe = new adm();
									uframe.setVisible(true);
									uframe.setTitle(id);/*账号作为管理员界面的标题*/
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							else {/*普通用户*/
								try {
									users uframe = new users();
									uframe.setVisible(true);
									uframe.setTitle(id);/*账号作为用户界面的标题*/
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							close();/*登录成功后关闭自己*/
						}
					}
					if(flag) {/*登录失败*/
						error.setText("账号或密码错误！");
					}
				} catch (Exception e2) {
					error.setText("输入错误！");
				}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {/*点击注册按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					register rframe = new register();/*打开注册页面*/
					rframe.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				close();/*关闭自己*/
			}
		});
	}
	public void close() {/*关闭本窗口的函数*/
		this.dispose();
	}
}