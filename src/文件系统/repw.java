package �ļ�ϵͳ;

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
		pw.setFont(new Font("����", Font.PLAIN, 18));
		pw.setBounds(10, 10, 60, 30);
		contentPane.add(pw);
		
		JLabel qrpw = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		qrpw.setFont(new Font("����", Font.PLAIN, 18));
		qrpw.setBounds(10, 50, 100, 30);
		contentPane.add(qrpw);
		
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("����", Font.PLAIN, 18));
		error.setBounds(10, 90, 296, 30);
		contentPane.add(error);
		
		pwtext = new JTextField();
		pwtext.setFont(new Font("����", Font.PLAIN, 18));
		pwtext.setBounds(96, 10, 210, 30);
		pwtext.setColumns(10);
		contentPane.add(pwtext);
		
		qrpwtext = new JTextField();
		qrpwtext.setFont(new Font("����", Font.PLAIN, 18));
		qrpwtext.setColumns(10);
		qrpwtext.setBounds(96, 50, 210, 30);
		contentPane.add(qrpwtext);
		
		JButton repwButton = new JButton("\u4FEE\u6539\u5BC6\u7801");
		repwButton.setFont(new Font("����", Font.PLAIN, 18));
		repwButton.setBounds(100, 130, 110, 30);
		contentPane.add(repwButton);
			
		try {/*�������ݿ�*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			error.setText("���ݿ�����ʧ�ܣ�");
		}
		
		repwButton.addActionListener(new ActionListener() {/*����޸����밴ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					String id=getTitle();/*��ȡ�û��˺�*/
					String psw=pwtext.getText();/*��ȡ�û����������*/
					String qrpsw=qrpwtext.getText();/*��ȡ�û������ȷ������*/
					if(psw.equals("")||psw.indexOf(" ")!=-1) {/*�ж������Ƿ�Ϸ�*/
						error.setText("���벻��Ϊ�ջ�����ո�");
					}else if(!psw.equals(qrpsw)) {/*�ж����������Ƿ�һ��*/
						error.setText("�����������벻һ�£�");
					}else{/*�޸ĳɹ����޸��û���*/
						sta.executeUpdate("update users set pw='"+psw+"' where acc='"+id+"';");
						error.setText("�޸�����ɹ���");
						try {
							login frame = new login();/*�򿪵�¼����*/
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						close();/*�ر��Լ�*/
					}
				} catch (Exception e2) {
					error.setText("�������");
				}
			}
		});
	}
	
	public void close() {
		this.dispose();
	}

}
