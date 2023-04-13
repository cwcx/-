package 文件系统;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField acctext;
	private JTextField pwtext;
	private JTextField qrpwtext;
	
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	String user="root";
	String pass="born20010214";
	Connection con=null;
	Statement sta=null;

	public register() {
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 250);
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
		
		JLabel qrpw = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		qrpw.setFont(new Font("宋体", Font.PLAIN, 18));
		qrpw.setBounds(10, 90, 100, 30);
		contentPane.add(qrpw);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("宋体", Font.PLAIN, 18));
		error.setBounds(10, 130, 296, 30);
		contentPane.add(error);
		
		acctext = new JTextField();
		acctext.setFont(new Font("宋体", Font.PLAIN, 18));
		acctext.setBounds(96, 10, 210, 30);
		acctext.setColumns(10);
		contentPane.add(acctext);
		
		pwtext = new JTextField();
		pwtext.setFont(new Font("宋体", Font.PLAIN, 18));
		pwtext.setBounds(96, 50, 210, 30);
		pwtext.setColumns(10);
		contentPane.add(pwtext);
		
		qrpwtext = new JTextField();
		qrpwtext.setFont(new Font("宋体", Font.PLAIN, 18));
		qrpwtext.setColumns(10);
		qrpwtext.setBounds(96, 90, 210, 30);
		contentPane.add(qrpwtext);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.setFont(new Font("宋体", Font.PLAIN, 18));
		loginButton.setBounds(20, 170, 97, 30);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("\u6CE8\u518C");
		registerButton.setFont(new Font("宋体", Font.PLAIN, 18));
		registerButton.setBounds(170, 170, 97, 30);
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
					login lframe = new login();/*打开登录界面*/
					lframe.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				close();/*关闭自己*/
			}
		});
		
		registerButton.addActionListener(new ActionListener() {/*点击注册按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					boolean flag=true;/*用于判断账号是否存在*/
					String id=acctext.getText();/*获取输入的账号*/
					String psw=pwtext.getText();/*获取输入的密码*/
					String qrpsw=qrpwtext.getText();/*获取输入的确认密码*/
					PreparedStatement pstm = con.prepareStatement("select * from users where acc='"+id+"';");/*查询用户表中是否有该账号*/
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {/*如果账号重复，报错*/
						error.setText("账号已存在！");
						flag=false;
					}
					if(id.equals("")||psw.equals("")||id.indexOf(" ")!=-1||psw.indexOf(" ")!=-1) {
						error.setText("账号密码不能为空或包含空格！");/*判断输入是否合法*/
					}else if(!psw.equals(qrpsw)) {/*判断两次密码是否一致*/
						error.setText("两次输入密码不一致！");
					}else if(flag) {/*注册成功，写入数据库*/
						sta.executeUpdate("insert into users values('"+id+"','"+psw+"');");
						sta.executeUpdate("insert into folder value('"+id+"','OStest',null,2);");
						error.setText("注册成功！");
					}
				} catch (Exception e2) {
					error.setText("输入错误！");
				}
			}
		});
	}
	
	public void close() {/*关闭本窗口的函数*/
		this.dispose();
	}
}