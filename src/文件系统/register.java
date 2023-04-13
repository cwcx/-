package �ļ�ϵͳ;

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
		acc.setFont(new Font("����", Font.PLAIN, 18));
		acc.setBounds(10, 10, 60, 30);
		contentPane.add(acc);
		
		JLabel pw = new JLabel("\u5BC6\u7801\uFF1A");
		pw.setFont(new Font("����", Font.PLAIN, 18));
		pw.setBounds(10, 50, 60, 30);
		contentPane.add(pw);
		
		JLabel qrpw = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		qrpw.setFont(new Font("����", Font.PLAIN, 18));
		qrpw.setBounds(10, 90, 100, 30);
		contentPane.add(qrpw);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("����", Font.PLAIN, 18));
		error.setBounds(10, 130, 296, 30);
		contentPane.add(error);
		
		acctext = new JTextField();
		acctext.setFont(new Font("����", Font.PLAIN, 18));
		acctext.setBounds(96, 10, 210, 30);
		acctext.setColumns(10);
		contentPane.add(acctext);
		
		pwtext = new JTextField();
		pwtext.setFont(new Font("����", Font.PLAIN, 18));
		pwtext.setBounds(96, 50, 210, 30);
		pwtext.setColumns(10);
		contentPane.add(pwtext);
		
		qrpwtext = new JTextField();
		qrpwtext.setFont(new Font("����", Font.PLAIN, 18));
		qrpwtext.setColumns(10);
		qrpwtext.setBounds(96, 90, 210, 30);
		contentPane.add(qrpwtext);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.setFont(new Font("����", Font.PLAIN, 18));
		loginButton.setBounds(20, 170, 97, 30);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("\u6CE8\u518C");
		registerButton.setFont(new Font("����", Font.PLAIN, 18));
		registerButton.setBounds(170, 170, 97, 30);
		contentPane.add(registerButton);
		
		try {/*�������ݿ�*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			error.setText("���ݿ�����ʧ�ܣ�");
		}
		
		loginButton.addActionListener(new ActionListener() {/*�����¼��ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					login lframe = new login();/*�򿪵�¼����*/
					lframe.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				close();/*�ر��Լ�*/
			}
		});
		
		registerButton.addActionListener(new ActionListener() {/*���ע�ᰴť*/
			public void actionPerformed(ActionEvent e) {
				try {
					boolean flag=true;/*�����ж��˺��Ƿ����*/
					String id=acctext.getText();/*��ȡ������˺�*/
					String psw=pwtext.getText();/*��ȡ���������*/
					String qrpsw=qrpwtext.getText();/*��ȡ�����ȷ������*/
					PreparedStatement pstm = con.prepareStatement("select * from users where acc='"+id+"';");/*��ѯ�û������Ƿ��и��˺�*/
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {/*����˺��ظ�������*/
						error.setText("�˺��Ѵ��ڣ�");
						flag=false;
					}
					if(id.equals("")||psw.equals("")||id.indexOf(" ")!=-1||psw.indexOf(" ")!=-1) {
						error.setText("�˺����벻��Ϊ�ջ�����ո�");/*�ж������Ƿ�Ϸ�*/
					}else if(!psw.equals(qrpsw)) {/*�ж����������Ƿ�һ��*/
						error.setText("�����������벻һ�£�");
					}else if(flag) {/*ע��ɹ���д�����ݿ�*/
						sta.executeUpdate("insert into users values('"+id+"','"+psw+"');");
						sta.executeUpdate("insert into folder value('"+id+"','OStest',null,2);");
						error.setText("ע��ɹ���");
					}
				} catch (Exception e2) {
					error.setText("�������");
				}
			}
		});
	}
	
	public void close() {/*�رձ����ڵĺ���*/
		this.dispose();
	}
}