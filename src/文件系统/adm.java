package �ļ�ϵͳ;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class adm extends JFrame {

	private JPanel contentPane;
	private JTextField newfiletext;
	private JTextField newflourtext;
	private JTextField renametext;
	
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	String user="root";
	String pass="born20010214";
	Connection con=null;
	Statement sta=null;
	public String fp="G:\\OStest";
	public JComboBox opencombo;
	public JComboBox renamecombo;
	public JComboBox deletecombo;
	public JComboBox filescombo;
	public JTextArea output;
	
	public adm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 330, 566, 123);
		contentPane.add(scrollPane);
		
		output = new JTextArea();
		scrollPane.setViewportView(output);
		
		JButton repwButton = new JButton("\u4FEE\u6539\u5BC6\u7801");
		repwButton.setFont(new Font("����", Font.PLAIN, 18));
		repwButton.setBounds(380, 10, 170, 30);
		contentPane.add(repwButton);
		
		JButton showButton = new JButton("\u67E5\u770B\u5F53\u524D\u6587\u4EF6\u5939");
		showButton.setFont(new Font("����", Font.PLAIN, 18));
		showButton.setBounds(10, 10, 170, 30);
		contentPane.add(showButton);
		
		JButton returnButton = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		returnButton.setFont(new Font("����", Font.PLAIN, 18));
		returnButton.setBounds(190, 10, 180, 30);
		contentPane.add(returnButton);
		
		JButton openButton = new JButton("\u6253\u5F00\u6587\u4EF6/\u6587\u4EF6\u5939");
		openButton.setFont(new Font("����", Font.PLAIN, 18));
		openButton.setBounds(190, 50, 180, 30);
		contentPane.add(openButton);
		
		JButton renameButton = new JButton("\u91CD\u547D\u540D\u4E3A");
		renameButton.setFont(new Font("����", Font.PLAIN, 18));
		renameButton.setBounds(190, 90, 180, 30);
		contentPane.add(renameButton);
		
		JButton newfileButton = new JButton("\u65B0\u5EFA\u6587\u4EF6");
		newfileButton.setFont(new Font("����", Font.PLAIN, 18));
		newfileButton.setBounds(190, 130, 180, 30);
		contentPane.add(newfileButton);
		
		JButton newflourButton = new JButton("\u65B0\u5EFA\u6587\u4EF6\u5939");
		newflourButton.setFont(new Font("����", Font.PLAIN, 18));
		newflourButton.setBounds(190, 170, 180, 30);
		contentPane.add(newflourButton);
		
		JButton deleteButton = new JButton("\u5220\u9664\u6587\u4EF6/\u6587\u4EF6\u5939");
		deleteButton.setFont(new Font("����", Font.PLAIN, 18));
		deleteButton.setBounds(190, 210, 180, 30);
		contentPane.add(deleteButton);
		
		JButton uesersButton = new JButton("\u67E5\u770B\u6240\u6709\u7528\u6237");
		uesersButton.setFont(new Font("����", Font.PLAIN, 18));
		uesersButton.setBounds(190, 250, 180, 30);
		contentPane.add(uesersButton);
		
		JButton authButton = new JButton("\u4FEE\u6539\u6743\u9650\u4E3A");
		authButton.setFont(new Font("����", Font.PLAIN, 18));
		authButton.setBounds(190, 290, 180, 30);
		contentPane.add(authButton);
		
		opencombo = new JComboBox();
		opencombo.setFont(new Font("����", Font.PLAIN, 18));
		opencombo.setBounds(10, 50, 170, 30);
		contentPane.add(opencombo);
		
		renamecombo = new JComboBox();
		renamecombo.setFont(new Font("����", Font.PLAIN, 18));
		renamecombo.setBounds(10, 90, 170, 30);
		contentPane.add(renamecombo);
		
		deletecombo = new JComboBox();
		deletecombo.setFont(new Font("����", Font.PLAIN, 18));
		deletecombo.setBounds(10, 210, 170, 30);
		contentPane.add(deletecombo);
		
		JComboBox userscombo = new JComboBox();
		userscombo.setFont(new Font("����", Font.PLAIN, 18));
		userscombo.setBounds(10, 250, 170, 30);
		contentPane.add(userscombo);
		
		filescombo = new JComboBox();
		filescombo.setFont(new Font("����", Font.PLAIN, 18));
		filescombo.setBounds(10, 290, 170, 30);
		contentPane.add(filescombo);
		
		JComboBox authcombo = new JComboBox();
		authcombo.setFont(new Font("����", Font.PLAIN, 18));
		authcombo.setBounds(380, 290, 170, 30);
		contentPane.add(authcombo);
		authcombo.addItem("0.���ɼ�");
		authcombo.addItem("1.����");
		authcombo.addItem("2.�ɶ�д");
		authcombo.addItem("3.��ɾ��");
		
		renametext = new JTextField();
		renametext.setFont(new Font("����", Font.PLAIN, 18));
		renametext.setColumns(10);
		renametext.setBounds(380, 90, 170, 30);
		contentPane.add(renametext);
		
		newfiletext = new JTextField();
		newfiletext.setFont(new Font("����", Font.PLAIN, 18));
		newfiletext.setBounds(10, 130, 170, 30);
		contentPane.add(newfiletext);
		newfiletext.setColumns(10);
		
		newflourtext = new JTextField();
		newflourtext.setFont(new Font("����", Font.PLAIN, 18));
		newflourtext.setColumns(10);
		newflourtext.setBounds(10, 170, 170, 30);
		contentPane.add(newflourtext);
		
		try {/*�������ݿ�*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			output.setText("���ݿ�����ʧ�ܣ�");
		}
		
		repwButton.addActionListener(new ActionListener() {/*����޸����밴ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					String id=getTitle();
					repw reframe = new repw();/*���޸��������*/
					reframe.setVisible(true);
					reframe.setTitle(id);/*�˺���Ϊ�޸��������ı���*/
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				close();/*�ر��Լ�*/
			}
		});
		
		showButton.addActionListener(new ActionListener() {/*����鿴��ǰ�ļ��а�ť*/
			public void actionPerformed(ActionEvent e) {
				renew();/*ˢ���������������Ϣ��*/
			}
		});
		
		returnButton.addActionListener(new ActionListener() {/*���������һ����ť*/
			public void actionPerformed(ActionEvent e) {
				File file=new File(fp);
				if(file.getName().equals("OStest")) {/*����Ŀ¼�ˣ��޷��ٷ�����*/
					output.append("�ѷ��ص���Ŀ¼���޷��ٷ�����һ����\n");
				}else {/*�޸ĵ�ǰĿ¼���ַ���*/
					fp=fp.replace("\\"+file.getName(), "");
					renew();/*ˢ���������������Ϣ��*/
				}
			}
		});
		
		openButton.addActionListener(new ActionListener() {/*������ļ�/�ļ��а�ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					String c=(String) opencombo.getSelectedItem();
					String f;
					if(c.indexOf("��")!=-1) {/*ѡ���ļ���*/
						f=c.replace("�ļ��У�", "");
						fp=fp+"\\"+f;/*�޸ĵ�ǰĿ¼���ַ���*/
						renew();/*ˢ���������������Ϣ��*/
					}else{/*ѡ���ļ�*/
						f=c.replace("�ļ���", "");
						File file=new File(fp+"\\"+f);
						Desktop.getDesktop().open(file);/*��*/
					}
				} catch (Exception e2) {
					output.append("��ѡ����ȷ���ļ����ļ��У�\n");
				}		
			}
		});
		
		renameButton.addActionListener(new ActionListener() {/*�����������ť*/
			public void actionPerformed(ActionEvent e) {
				String nn=renametext.getText();
				if(nn.equals("")||nn.indexOf(" ")!=-1) {/*�ж��������Ƿ�Ϸ�*/
					output.append("�����Ʋ���Ϊ�ջ�����ո�\n");
				}else {
					try {
						String c=(String) renamecombo.getSelectedItem();
						if(c.indexOf("��")!=-1) {/*ѡ���ļ���*/
							c=c.replace("�ļ��У�", "");
							boolean flag=true;
							try {/*����ļ��������Ƿ����*/
								PreparedStatement pstm = con.prepareStatement("select * from folder where fname='"+nn+"';");
								ResultSet rs = pstm.executeQuery();
								while(rs.next()) {
									flag=false;
									output.append("�ļ��������Ѿ����ڣ�\n");
									break;
								}
							} catch (Exception e2) {
								flag=false;
								output.append("��ȡ���ݿⷢ������\n");
							}
							if(flag) {/*�޸����ݿ�*/
								try {
									sta.executeUpdate("set FOREIGN_KEY_CHECKS=0;");
									sta.executeUpdate("update folder set fname='"+nn+"' where fname='"+c+"';");
									sta.executeUpdate("update files set fname='"+nn+"' where fname='"+c+"';");
									sta.executeUpdate("set FOREIGN_KEY_CHECKS=1;");
									sta.executeUpdate("update folder set pname='"+nn+"' where pname='"+c+"';");
								} catch (Exception e2) {
									output.append("д�����ݿⷢ������\n");
								}
								File nfile=new File(fp+"\\"+c);
								nfile.renameTo(new File(fp+"\\"+nn));/*�������ļ���*/
								renew();/*ˢ��*/
							}
						}else {/*ѡ���ļ�*/
							c=c.replace("�ļ���", "");
							File file=new File(fp);
							File file1=new File(fp+"\\"+nn);
							if(!file1.exists()) {/*��鵱ǰ�ļ����£��ļ��Ƿ�����*/
								try {/*�޸����ݿ�*/
									sta.executeUpdate("update files set name='"+nn+"' where name='"+c+"' and fname='"+file.getName()+"';");
								} catch (Exception e2) {
									output.append("д�����ݿⷢ������\n");
								}
								File nfile=new File(fp+"\\"+c);
								nfile.renameTo(new File(fp+"\\"+nn));/*�������ļ�*/
								renew();/*ˢ��*/
							}else {
								output.append("�ļ��Ѵ��ڣ�\n");
							}
						}
					} catch (Exception e2) {
						output.append("��ѡ����ȷ���ļ����ļ��У�\n");
					}
				}
			}
		});
		
		newfileButton.addActionListener(new ActionListener() {/*����½��ļ���ť*/
			public void actionPerformed(ActionEvent e) {
				String fn=newfiletext.getText();
				if(fn.equals("")||fn.indexOf(" ")!=-1) {/*�ж������Ƿ�Ϸ�*/
					output.append("�ļ����Ʋ���Ϊ�ջ�����ո�\n");
				}else {
					File file=new File(fp+"\\"+fn);
					File pf=new File(fp);
					if(!file.exists()) {/*��鵱ǰ�ļ����£��ļ��Ƿ����*/
						try {
							file.createNewFile();
							output.append("�ļ������ɹ���\n");
							try {/*д���ļ���*/
								PreparedStatement pstm = con.prepareStatement("select * from users;");
								ResultSet rs = pstm.executeQuery();
								while(rs.next()) {
									String acc = rs.getString("acc").trim();
									if(acc.equals("ccj")) {
										sta.executeUpdate("insert into files values('ccj','"+pf.getName()+"','"+fn+"',4);");
									}
									else {
										sta.executeUpdate("insert into files values('"+acc+"','"+pf.getName()+"','"+fn+"',0);");
									}
								}
							} catch (Exception e2) {
								output.append("д�����ݿⷢ������\n");
							}
						} catch (IOException e1) {
							output.append("�ļ�����ʧ�ܣ�\n");
						}
					}else {
						output.append("�ļ��Ѿ����ڣ�\n");
					}
				}
			}
		});
		
		newflourButton.addActionListener(new ActionListener() {/*����½��ļ��а�ť*/
			public void actionPerformed(ActionEvent e) {
				String fn=newflourtext.getText();
				boolean flag=true;
				if(fn.equals("")||fn.indexOf(" ")!=-1) {/*�ж������Ƿ�Ϸ�*/
					output.append("�ļ������Ʋ���Ϊ�ջ�����ո�\n");
					flag=false;
				}else {
					try {/*����ļ��������Ƿ����*/
						PreparedStatement pstm = con.prepareStatement("select * from folder where fname='"+fn+"';");
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							flag=false;
							output.append("�ļ��������Ѿ����ڣ�\n");
							break;
						}
					} catch (Exception e2) {
						flag=false;
						output.append("��ȡ���ݿⷢ������\n");
					}					
				}
				if(flag){
					File file=new File(fp+"\\"+fn);
					File pf=new File(fp);
					if(!file.exists()) {/*��鵱ǰ�ļ����£��ļ����Ƿ�����*/
						file.mkdir();
						output.append("�ļ��д����ɹ���\n");
						try {/*д���ļ��б�*/
							PreparedStatement pstm = con.prepareStatement("select * from users;");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {
								String acc = rs.getString("acc").trim();
								if(acc.equals("ccj")) {
									sta.executeUpdate("insert into folder values('ccj','"+fn+"','"+pf.getName()+"',4);");
								}
								else {
									sta.executeUpdate("insert into folder values('"+acc+"','"+fn+"','"+pf.getName()+"',0);");
								}
							}
						} catch (Exception e2) {
							output.append("д�����ݿⷢ������\n");
						}
					}else {
						output.append("�ļ����Ѿ����ڣ�\n");
					}
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {/*���ɾ���ļ��а�ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					String c=(String) deletecombo.getSelectedItem();
					if(c.indexOf("��")!=-1) {/*ѡ���ļ���*/
						c=c.replace("�ļ��У�", "");
						drop(new File(fp+"\\"+c));/*����ɾ���ļ��еĺ���*/
						sta.executeUpdate("delete from folder where fname='"+c+"';");/*�޸����ݿ�*/
						renew();/*ˢ��*/
					}else {/*ѡ���ļ�*/
						c=c.replace("�ļ���", "");
						File df=new File(fp+"\\"+c);
						df.delete();/*ɾ���ļ�*/
						sta.executeUpdate("delete from files where name='"+c+"';");/*�޸��ļ���*/
						renew();/*ˢ��*/
					}
				} catch (Exception e2) {
					output.append("δѡ����ȷ���ļ�(�ļ���)�����ݿ������������\n");
				}
			}
		});
		
		uesersButton.addActionListener(new ActionListener() {/*����鿴�����û���ť*/
			public void actionPerformed(ActionEvent e) {
				userscombo.removeAllItems();/*����û�չʾ������*/
				output.setText("");/*��������Ϣ��*/
				try {
					PreparedStatement pstm = con.prepareStatement("select * from users;");
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {/*�����û���*/
						String acc = rs.getString("acc").trim();/*������������ͨ�û�����Ϣ�������ʾ�����û�*/
						if(acc.equals("ccj")) {
							output.append("����Ա��ccj\n");
						}
						else {
							output.append("�û���"+acc+"\n");
							userscombo.addItem(acc);
						}
					}
				} catch (Exception e2) {
					output.append("��ȡ���ݿⷢ������\n");
				}
			}
		});
		
		authButton.addActionListener(new ActionListener() {/*����޸�Ȩ�ް�ť*/
			public void actionPerformed(ActionEvent e) {
				try {
					String u=(String) userscombo.getSelectedItem();/*ѡ����û�*/
					String f=(String) filescombo.getSelectedItem();/*ѡ����ļ����ļ���*/
					int a=authcombo.getSelectedIndex();/*ѡ���Ȩ�޵ȼ�*/
					if(u.length()==0) {}
					if(f.indexOf("��")!=-1) {/*ѡ���ļ�*/
						f=f.replace("�ļ��У�", "");
						try {/*�޸��ļ��б�*/
							sta.executeUpdate("update folder set auth="+a+" where acc='"+u+"' and fname='"+f+"';");
							output.append("�޸ĳɹ���\n");
						} catch (Exception e2) {
							output.append("д�����ݿⷢ������\n");
						}
					}else {/*ѡ���ļ���*/
						f=f.replace("�ļ���", "");
						try {/*�޸��ļ���*/
							sta.executeUpdate("update files set auth="+a+" where acc='"+u+"' and fname='"+(new File(fp).getName())+"' and name='"+f+"';");
							output.append("�޸ĳɹ���\n");
						} catch (Exception e2) {
							output.append("д�����ݿⷢ������\n");
						}
					}
				} catch (Exception e2) {
					output.append("��ѡ����ȷ���ļ����ļ��У�\n");
				}
				
			}
		});
		
	}
	
	public void renew() {/*���½���ĺ���*/
		File file=new File(fp);
		opencombo.removeAllItems();/*���������������Ϣ�������*/
		renamecombo.removeAllItems();
		deletecombo.removeAllItems();
		filescombo.removeAllItems();
		output.setText("");
		output.append("��ǰ·����"+file.getPath()+"\n");
		for(File f:file.listFiles()) {/*�����ļ����ݣ�����������򣬲������Ӧ��Ϣ*/
			if(f.isDirectory()) {/*������ļ���*/
				output.append("�ļ��У�");
				output.append("path="+f.getPath()+"\n");
				opencombo.addItem("�ļ��У�"+f.getName());
				renamecombo.addItem("�ļ��У�"+f.getName());
				deletecombo.addItem("�ļ��У�"+f.getName());
				filescombo.addItem("�ļ��У�"+f.getName());
			}else{/*����������Ϊ�ļ�*/
				output.append("�ļ���");
				output.append("path="+f.getPath()+"\n");
				opencombo.addItem("�ļ���"+f.getName());
				renamecombo.addItem("�ļ���"+f.getName());
				deletecombo.addItem("�ļ���"+f.getName());
				filescombo.addItem("�ļ���"+f.getName());
			}
		}
	}
	
	public void drop(File f) {/*ɾ���ļ��еĺ���*/
		for(File f1:f.listFiles()) {/*�������ļ���*/
			if(f1.isDirectory()) {/*������ļ��У��ݹ����ɾ��*/
				drop(f1);
			}else {/*�����ļ�ֱ��ɾ��*/
				f1.delete();
			}
		}
		f.delete();/*���ɾ���Լ�*/
	}
	
	public void close() {/*�رձ����ڵĺ���*/
		this.dispose();
	}
}
