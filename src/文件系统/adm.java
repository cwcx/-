package 文件系统;

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
		repwButton.setFont(new Font("宋体", Font.PLAIN, 18));
		repwButton.setBounds(380, 10, 170, 30);
		contentPane.add(repwButton);
		
		JButton showButton = new JButton("\u67E5\u770B\u5F53\u524D\u6587\u4EF6\u5939");
		showButton.setFont(new Font("宋体", Font.PLAIN, 18));
		showButton.setBounds(10, 10, 170, 30);
		contentPane.add(showButton);
		
		JButton returnButton = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		returnButton.setFont(new Font("宋体", Font.PLAIN, 18));
		returnButton.setBounds(190, 10, 180, 30);
		contentPane.add(returnButton);
		
		JButton openButton = new JButton("\u6253\u5F00\u6587\u4EF6/\u6587\u4EF6\u5939");
		openButton.setFont(new Font("宋体", Font.PLAIN, 18));
		openButton.setBounds(190, 50, 180, 30);
		contentPane.add(openButton);
		
		JButton renameButton = new JButton("\u91CD\u547D\u540D\u4E3A");
		renameButton.setFont(new Font("宋体", Font.PLAIN, 18));
		renameButton.setBounds(190, 90, 180, 30);
		contentPane.add(renameButton);
		
		JButton newfileButton = new JButton("\u65B0\u5EFA\u6587\u4EF6");
		newfileButton.setFont(new Font("宋体", Font.PLAIN, 18));
		newfileButton.setBounds(190, 130, 180, 30);
		contentPane.add(newfileButton);
		
		JButton newflourButton = new JButton("\u65B0\u5EFA\u6587\u4EF6\u5939");
		newflourButton.setFont(new Font("宋体", Font.PLAIN, 18));
		newflourButton.setBounds(190, 170, 180, 30);
		contentPane.add(newflourButton);
		
		JButton deleteButton = new JButton("\u5220\u9664\u6587\u4EF6/\u6587\u4EF6\u5939");
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 18));
		deleteButton.setBounds(190, 210, 180, 30);
		contentPane.add(deleteButton);
		
		JButton uesersButton = new JButton("\u67E5\u770B\u6240\u6709\u7528\u6237");
		uesersButton.setFont(new Font("宋体", Font.PLAIN, 18));
		uesersButton.setBounds(190, 250, 180, 30);
		contentPane.add(uesersButton);
		
		JButton authButton = new JButton("\u4FEE\u6539\u6743\u9650\u4E3A");
		authButton.setFont(new Font("宋体", Font.PLAIN, 18));
		authButton.setBounds(190, 290, 180, 30);
		contentPane.add(authButton);
		
		opencombo = new JComboBox();
		opencombo.setFont(new Font("宋体", Font.PLAIN, 18));
		opencombo.setBounds(10, 50, 170, 30);
		contentPane.add(opencombo);
		
		renamecombo = new JComboBox();
		renamecombo.setFont(new Font("宋体", Font.PLAIN, 18));
		renamecombo.setBounds(10, 90, 170, 30);
		contentPane.add(renamecombo);
		
		deletecombo = new JComboBox();
		deletecombo.setFont(new Font("宋体", Font.PLAIN, 18));
		deletecombo.setBounds(10, 210, 170, 30);
		contentPane.add(deletecombo);
		
		JComboBox userscombo = new JComboBox();
		userscombo.setFont(new Font("宋体", Font.PLAIN, 18));
		userscombo.setBounds(10, 250, 170, 30);
		contentPane.add(userscombo);
		
		filescombo = new JComboBox();
		filescombo.setFont(new Font("宋体", Font.PLAIN, 18));
		filescombo.setBounds(10, 290, 170, 30);
		contentPane.add(filescombo);
		
		JComboBox authcombo = new JComboBox();
		authcombo.setFont(new Font("宋体", Font.PLAIN, 18));
		authcombo.setBounds(380, 290, 170, 30);
		contentPane.add(authcombo);
		authcombo.addItem("0.不可见");
		authcombo.addItem("1.仅读");
		authcombo.addItem("2.可读写");
		authcombo.addItem("3.可删除");
		
		renametext = new JTextField();
		renametext.setFont(new Font("宋体", Font.PLAIN, 18));
		renametext.setColumns(10);
		renametext.setBounds(380, 90, 170, 30);
		contentPane.add(renametext);
		
		newfiletext = new JTextField();
		newfiletext.setFont(new Font("宋体", Font.PLAIN, 18));
		newfiletext.setBounds(10, 130, 170, 30);
		contentPane.add(newfiletext);
		newfiletext.setColumns(10);
		
		newflourtext = new JTextField();
		newflourtext.setFont(new Font("宋体", Font.PLAIN, 18));
		newflourtext.setColumns(10);
		newflourtext.setBounds(10, 170, 170, 30);
		contentPane.add(newflourtext);
		
		try {/*连接数据库*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
			sta= con.createStatement();
		}
		catch(Exception e) {
			output.setText("数据库连接失败！");
		}
		
		repwButton.addActionListener(new ActionListener() {/*点击修改密码按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					String id=getTitle();
					repw reframe = new repw();/*打开修改密码界面*/
					reframe.setVisible(true);
					reframe.setTitle(id);/*账号作为修改密码界面的标题*/
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				close();/*关闭自己*/
			}
		});
		
		showButton.addActionListener(new ActionListener() {/*点击查看当前文件夹按钮*/
			public void actionPerformed(ActionEvent e) {
				renew();/*刷新下拉框与输出信息框*/
			}
		});
		
		returnButton.addActionListener(new ActionListener() {/*点击返回上一级按钮*/
			public void actionPerformed(ActionEvent e) {
				File file=new File(fp);
				if(file.getName().equals("OStest")) {/*到根目录了，无法再返回了*/
					output.append("已返回到根目录，无法再返回上一级！\n");
				}else {/*修改当前目录的字符串*/
					fp=fp.replace("\\"+file.getName(), "");
					renew();/*刷新下拉框与输出信息框*/
				}
			}
		});
		
		openButton.addActionListener(new ActionListener() {/*点击打开文件/文件夹按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					String c=(String) opencombo.getSelectedItem();
					String f;
					if(c.indexOf("夹")!=-1) {/*选择文件夹*/
						f=c.replace("文件夹：", "");
						fp=fp+"\\"+f;/*修改当前目录的字符串*/
						renew();/*刷新下拉框与输出信息框*/
					}else{/*选择文件*/
						f=c.replace("文件：", "");
						File file=new File(fp+"\\"+f);
						Desktop.getDesktop().open(file);/*打开*/
					}
				} catch (Exception e2) {
					output.append("请选择正确的文件或文件夹！\n");
				}		
			}
		});
		
		renameButton.addActionListener(new ActionListener() {/*点击重命名按钮*/
			public void actionPerformed(ActionEvent e) {
				String nn=renametext.getText();
				if(nn.equals("")||nn.indexOf(" ")!=-1) {/*判断新名称是否合法*/
					output.append("新名称不能为空或包含空格！\n");
				}else {
					try {
						String c=(String) renamecombo.getSelectedItem();
						if(c.indexOf("夹")!=-1) {/*选择文件夹*/
							c=c.replace("文件夹：", "");
							boolean flag=true;
							try {/*检查文件夹名称是否存在*/
								PreparedStatement pstm = con.prepareStatement("select * from folder where fname='"+nn+"';");
								ResultSet rs = pstm.executeQuery();
								while(rs.next()) {
									flag=false;
									output.append("文件夹名称已经存在！\n");
									break;
								}
							} catch (Exception e2) {
								flag=false;
								output.append("读取数据库发生错误！\n");
							}
							if(flag) {/*修改数据库*/
								try {
									sta.executeUpdate("set FOREIGN_KEY_CHECKS=0;");
									sta.executeUpdate("update folder set fname='"+nn+"' where fname='"+c+"';");
									sta.executeUpdate("update files set fname='"+nn+"' where fname='"+c+"';");
									sta.executeUpdate("set FOREIGN_KEY_CHECKS=1;");
									sta.executeUpdate("update folder set pname='"+nn+"' where pname='"+c+"';");
								} catch (Exception e2) {
									output.append("写入数据库发生错误！\n");
								}
								File nfile=new File(fp+"\\"+c);
								nfile.renameTo(new File(fp+"\\"+nn));/*重命名文件夹*/
								renew();/*刷新*/
							}
						}else {/*选择文件*/
							c=c.replace("文件：", "");
							File file=new File(fp);
							File file1=new File(fp+"\\"+nn);
							if(!file1.exists()) {/*检查当前文件夹下，文件是否重名*/
								try {/*修改数据库*/
									sta.executeUpdate("update files set name='"+nn+"' where name='"+c+"' and fname='"+file.getName()+"';");
								} catch (Exception e2) {
									output.append("写入数据库发生错误！\n");
								}
								File nfile=new File(fp+"\\"+c);
								nfile.renameTo(new File(fp+"\\"+nn));/*重命名文件*/
								renew();/*刷新*/
							}else {
								output.append("文件已存在！\n");
							}
						}
					} catch (Exception e2) {
						output.append("请选择正确的文件或文件夹！\n");
					}
				}
			}
		});
		
		newfileButton.addActionListener(new ActionListener() {/*点击新建文件按钮*/
			public void actionPerformed(ActionEvent e) {
				String fn=newfiletext.getText();
				if(fn.equals("")||fn.indexOf(" ")!=-1) {/*判断名称是否合法*/
					output.append("文件名称不能为空或包含空格！\n");
				}else {
					File file=new File(fp+"\\"+fn);
					File pf=new File(fp);
					if(!file.exists()) {/*检查当前文件夹下，文件是否存在*/
						try {
							file.createNewFile();
							output.append("文件创建成功！\n");
							try {/*写入文件表*/
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
								output.append("写入数据库发生错误！\n");
							}
						} catch (IOException e1) {
							output.append("文件创建失败！\n");
						}
					}else {
						output.append("文件已经存在！\n");
					}
				}
			}
		});
		
		newflourButton.addActionListener(new ActionListener() {/*点击新建文件夹按钮*/
			public void actionPerformed(ActionEvent e) {
				String fn=newflourtext.getText();
				boolean flag=true;
				if(fn.equals("")||fn.indexOf(" ")!=-1) {/*判断名称是否合法*/
					output.append("文件夹名称不能为空或包含空格！\n");
					flag=false;
				}else {
					try {/*检查文件夹名称是否存在*/
						PreparedStatement pstm = con.prepareStatement("select * from folder where fname='"+fn+"';");
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							flag=false;
							output.append("文件夹名称已经存在！\n");
							break;
						}
					} catch (Exception e2) {
						flag=false;
						output.append("读取数据库发生错误！\n");
					}					
				}
				if(flag){
					File file=new File(fp+"\\"+fn);
					File pf=new File(fp);
					if(!file.exists()) {/*检查当前文件夹下，文件夹是否重名*/
						file.mkdir();
						output.append("文件夹创建成功！\n");
						try {/*写入文件夹表*/
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
							output.append("写入数据库发生错误！\n");
						}
					}else {
						output.append("文件夹已经存在！\n");
					}
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {/*点击删除文件夹按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					String c=(String) deletecombo.getSelectedItem();
					if(c.indexOf("夹")!=-1) {/*选择文件夹*/
						c=c.replace("文件夹：", "");
						drop(new File(fp+"\\"+c));/*调用删除文件夹的函数*/
						sta.executeUpdate("delete from folder where fname='"+c+"';");/*修改数据库*/
						renew();/*刷新*/
					}else {/*选择文件*/
						c=c.replace("文件：", "");
						File df=new File(fp+"\\"+c);
						df.delete();/*删除文件*/
						sta.executeUpdate("delete from files where name='"+c+"';");/*修改文件表*/
						renew();/*刷新*/
					}
				} catch (Exception e2) {
					output.append("未选择正确的文件(文件夹)或数据库操作发生错误！\n");
				}
			}
		});
		
		uesersButton.addActionListener(new ActionListener() {/*点击查看所有用户按钮*/
			public void actionPerformed(ActionEvent e) {
				userscombo.removeAllItems();/*清空用户展示下拉框*/
				output.setText("");/*清空输出信息框*/
				try {
					PreparedStatement pstm = con.prepareStatement("select * from users;");
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {/*遍历用户表*/
						String acc = rs.getString("acc").trim();/*下拉框仅添加普通用户，信息输出框显示所有用户*/
						if(acc.equals("ccj")) {
							output.append("管理员：ccj\n");
						}
						else {
							output.append("用户："+acc+"\n");
							userscombo.addItem(acc);
						}
					}
				} catch (Exception e2) {
					output.append("读取数据库发生错误！\n");
				}
			}
		});
		
		authButton.addActionListener(new ActionListener() {/*点击修改权限按钮*/
			public void actionPerformed(ActionEvent e) {
				try {
					String u=(String) userscombo.getSelectedItem();/*选择的用户*/
					String f=(String) filescombo.getSelectedItem();/*选择的文件或文件夹*/
					int a=authcombo.getSelectedIndex();/*选择的权限等级*/
					if(u.length()==0) {}
					if(f.indexOf("夹")!=-1) {/*选择文件*/
						f=f.replace("文件夹：", "");
						try {/*修改文件夹表*/
							sta.executeUpdate("update folder set auth="+a+" where acc='"+u+"' and fname='"+f+"';");
							output.append("修改成功！\n");
						} catch (Exception e2) {
							output.append("写入数据库发生错误！\n");
						}
					}else {/*选择文件夹*/
						f=f.replace("文件：", "");
						try {/*修改文件表*/
							sta.executeUpdate("update files set auth="+a+" where acc='"+u+"' and fname='"+(new File(fp).getName())+"' and name='"+f+"';");
							output.append("修改成功！\n");
						} catch (Exception e2) {
							output.append("写入数据库发生错误！\n");
						}
					}
				} catch (Exception e2) {
					output.append("请选择正确的文件或文件夹！\n");
				}
				
			}
		});
		
	}
	
	public void renew() {/*更新界面的函数*/
		File file=new File(fp);
		opencombo.removeAllItems();/*清空下拉框和输出信息框的内容*/
		renamecombo.removeAllItems();
		deletecombo.removeAllItems();
		filescombo.removeAllItems();
		output.setText("");
		output.append("当前路径："+file.getPath()+"\n");
		for(File f:file.listFiles()) {/*遍历文件内容，添加至下拉框，并输出相应信息*/
			if(f.isDirectory()) {/*如果是文件夹*/
				output.append("文件夹：");
				output.append("path="+f.getPath()+"\n");
				opencombo.addItem("文件夹："+f.getName());
				renamecombo.addItem("文件夹："+f.getName());
				deletecombo.addItem("文件夹："+f.getName());
				filescombo.addItem("文件夹："+f.getName());
			}else{/*其他：均视为文件*/
				output.append("文件：");
				output.append("path="+f.getPath()+"\n");
				opencombo.addItem("文件："+f.getName());
				renamecombo.addItem("文件："+f.getName());
				deletecombo.addItem("文件："+f.getName());
				filescombo.addItem("文件："+f.getName());
			}
		}
	}
	
	public void drop(File f) {/*删除文件夹的函数*/
		for(File f1:f.listFiles()) {/*遍历该文件夹*/
			if(f1.isDirectory()) {/*如果是文件夹，递归调用删除*/
				drop(f1);
			}else {/*其他文件直接删除*/
				f1.delete();
			}
		}
		f.delete();/*最后删除自己*/
	}
	
	public void close() {/*关闭本窗口的函数*/
		this.dispose();
	}
}
