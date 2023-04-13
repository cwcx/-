package 文件系统;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class repw extends JFrame {

	private JPanel contentPane;
	private JTextField pwtext;
	private JTextField qrpwtext;
	
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	String user="root";
	String pass="born20010214";
	Connection con=null;
	Statement sta=null;

	public repw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pw = new JLabel("\u5BC6\u7801\uFF1A");
		pw.setFont(new Font("宋体", Font.PLAIN, 18));
		pw.setBounds(10, 10, 60, 30);
		contentPane.add(pw);
		
		JLabel qrpw = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		qrpw.setFont(new Font("宋体", Font.PLAIN, 18));
		qrpw.setBounds(10, 50, 100, 30);
		contentPane.add(qrpw);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("宋体", Font.PLAIN, 18));
		error.setBounds(10, 90, 296, 30);
		contentPane.add(error);
		
		pwtext = new JTextField();
		pwtext.setFont(new Font("宋体", Font.PLAIN, 18));
		pwtext.setBounds(96, 10, 210, 30);
		pwtext.setColumns(10);
		contentPane.add(pwtext);
		
		qrpwtext = new JTextField();
		qrpwtext.setFont(new Font("宋体", Font.PLAIN, 18));
		qrpwtext.setColumns(10);
		qrpwtext.setBounds(96, 50, 210, 30);
		contentPane.add(qrpwtext);
		
		JButton repwButton = new JButton("\u4FEE\u6539\u5BC6\u7801");
		repwButton.setFont(new Font("宋体", Font.PLAIN, 18));
		repwButton.setBounds(100, 130, 110, 30);
		contentPane.add(repwButton);
			
		try {/*连接数据库*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			error.setText("数据库连接失败！");
		}
		
		repwButton.addActionListener(new ActionListener() {/*点击修改密码按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					String id=getTitle();/*获取用户账号*/
					String psw=pwtext.getText();/*获取用户输入的密码*/
					String qrpsw=qrpwtext.getText();/*获取用户输入的确认密码*/
					if(psw.equals("")||psw.indexOf(" ")!=-1) {/*判断密码是否合法*/
						error.setText("密码不能为空或包含空格！");
					}else if(!psw.equals(qrpsw)) {/*判断两次密码是否一致*/
						error.setText("两次输入密码不一致！");
					}else{/*修改成功，修改用户表*/
						sta.executeUpdate("update users set pw='"+psw+"' where acc='"+id+"';");
						error.setText("修改密码成功！");
						try {
							login frame = new login();/*打开登录界面*/
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						close();/*关闭自己*/
					}
				} catch (Exception e2) {
					error.setText("输入错误！");
				}
			}
		});
	}
	
	public void close() {
		this.dispose();
	}

}
