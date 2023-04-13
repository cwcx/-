package 文件系统;

import java.awt.Desktop;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;

public class users extends JFrame {

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
	public JTextArea output;
	
	public users() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 250, 566, 203);
		contentPane.add(scrollPane);
		
		output = new JTextArea();
		scrollPane.setViewportView(output);
		
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
					String id=getTitle();
					if(c.indexOf("夹")!=-1) {/*选择文件夹*/
						f=c.replace("文件夹：", "");
						fp=fp+"\\"+f;/*修改当前目录的字符串*/
						renew();/*刷新*/
					}else{/*选择文件*/
						f=c.replace("文件：", "");
						File file1=new File(fp);
						try {/*查询文件表*/
							PreparedStatement pstm = con.prepareStatement("select * from files where fname='"+file1.getName()+"' and name='"+f+"' and acc='"+id+"';");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {								
								File file=new File(fp+"\\"+f);
								int auth=rs.getInt("auth");
								if(auth>1) {/*检查权限*/								
									Desktop.getDesktop().open(file);/*权限够则打开文件*/
								}else {/*权限不够，则仅查看文件*/
									BufferedReader br=new BufferedReader(new FileReader(file));
									String s;
									output.append("该文件只可查看：\n");
									while((s=br.readLine())!=null) {
										output.append(s+"\n");
									}
									br.close();
								}
								break;
							}
						} catch (Exception e2) {
							output.append("读取数据库发生错误！\n");
						}
					}
				} catch (Exception e2) {
					output.append("请选择一个文件或文件夹！\n");
				}
			}
		});
		
		renameButton.addActionListener(new ActionListener() {/*点击重命名按钮*/
			public void actionPerformed(ActionEvent e) {
				String nn=renametext.getText();
				String id=getTitle();
				if(nn.equals("")||nn.indexOf(" ")!=-1) {/*判断新名称是否合法*/
					output.append("新名称不能为空或包含空格！\n");
				}else {
					try {
						String c=(String) renamecombo.getSelectedItem();
						if(c.indexOf("夹")!=-1) {/*选择文件夹*/
							c=c.replace("文件夹：", "");
							try {/*检查权限*/
								PreparedStatement pstm = con.prepareStatement("select * from folder where acc='"+id+"' and fname='"+c+"';");
								ResultSet rs = pstm.executeQuery();
								while(rs.next()) {
									int auth=rs.getInt("auth");
									if(auth>1) {/*权限足够*/
										boolean flag=true;
										try {/*检查文件夹名称是否存在*/
											pstm = con.prepareStatement("select * from folder where fname='"+nn+"';");
											ResultSet rs1 = pstm.executeQuery();
											while(rs1.next()) {
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
									}else {
										output.append("权限不足！\n");
									}
									break;				
								} 
							}catch (Exception e2) {
								output.append("读取数据库发生错误！\n");
							}
						}else {/*选择文件*/
							c=c.replace("文件：", "");
							File file=new File(fp);
							File file1=new File(fp+"\\"+nn);
							try {/*检查权限*/
								PreparedStatement pstm = con.prepareStatement("select * from files where fname='"+file.getName()+"' and name='"+c+"' and acc='"+id+"';");
								ResultSet rs = pstm.executeQuery();
								while(rs.next()) {
									int auth=rs.getInt("auth");
									if(auth>1) {/*权限足够*/
										if(!file1.exists()) {/*检查当前文件夹下，文件是否重名*/
											try {/*修改文件表*/
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
									}else {
										output.append("权限不足！\n");
									}
									break;
								}
							}catch (Exception e2) {
								output.append("读取数据库发生错误！\n");
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
				String id=getTitle();
				if(fn.equals("")||fn.indexOf(" ")!=-1) {/*判断名称是否合法*/
					output.append("文件名称不能为空或包含空格！\n");
				}
				else {
					File file=new File(fp+"\\"+fn);
					File pf=new File(fp);
					if(!file.exists()) {/*检查文件是否存在*/
						try {/*检查权限*/
							PreparedStatement pstm = con.prepareStatement("select * from folder where acc='"+id+"' and fname='"+pf.getName()+"';");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {
								int auth=rs.getInt("auth");
								if(auth>1) {/*权限足够够*/
									file.createNewFile();/*创建文件*/
									output.append("文件创建成功！\n");
									try {/*写入文件表*/
										pstm = con.prepareStatement("select * from users;");
										ResultSet rs1 = pstm.executeQuery();
										while(rs1.next()) {
											String acc = rs1.getString("acc").trim();
											if(acc.equals("ccj")) {
												sta.executeUpdate("insert into files values('ccj','"+pf.getName()+"','"+fn+"',4);");
											}else if(acc.equals(id)) {
												sta.executeUpdate("insert into files values('"+id+"','"+pf.getName()+"','"+fn+"',3);");
											}
											else {
												sta.executeUpdate("insert into files values('"+acc+"','"+pf.getName()+"','"+fn+"',0);");
											}
										}
									} catch (Exception e2) {
										output.append("写入数据库发生错误！\n");
									}
								}else {
									output.append("权限不足！\n");
								}
								break;			
							}
						} catch (Exception e2) {
							output.append("读取数据库发生错误！\n");
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
					String id=getTitle();
					if(!file.exists()) {/*检查当前文件夹下，文件夹与文件是否重名*/
						try {/*检查权限*/
							PreparedStatement pstm = con.prepareStatement("select * from folder where acc='"+id+"' and fname='"+pf.getName()+"';");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {
								int auth=rs.getInt("auth");
								if(auth>1) {/*权限足够*/
									file.mkdir();/*新建文件夹*/
									output.append("文件夹创建成功！\n");
									try {/*写入文件夹表*/
										pstm = con.prepareStatement("select * from users;");
										ResultSet rs1 = pstm.executeQuery();
										while(rs1.next()) {
											String acc = rs1.getString("acc").trim();
											if(acc.equals("ccj")) {
												sta.executeUpdate("insert into folder values('ccj','"+fn+"','"+pf.getName()+"',4);");
											}else if(acc.equals(id)) {
												sta.executeUpdate("insert into folder values('"+id+"','"+fn+"','"+pf.getName()+"',3);");
											}else {
												sta.executeUpdate("insert into folder values('"+acc+"','"+fn+"','"+pf.getName()+"',0);");
											}
										}
									} catch (Exception e2) {
										output.append("写入数据库发生错误！\n");
									}
								}else {
									output.append("权限不足！\n");
								}
								break;
							}
						} catch (Exception e2) {
							output.append("读取数据库发生错误！\n");
						}	
					}else {
						output.append("文件夹已经存在！\n");
					}					
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {/*点击删除文件/文件夹按钮*/
			public void actionPerformed(ActionEvent e) {
				String id=getTitle();
				try {
					String c=(String) deletecombo.getSelectedItem();
					if(c.indexOf("夹")!=-1) {/*选择文件夹*/
						c=c.replace("文件夹：", "");
						try {/*检查权限*/
							PreparedStatement pstm = con.prepareStatement("select * from folder where acc='"+id+"' and fname='"+c+"';");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {
								int auth=rs.getInt("auth");
								if(auth>2) {/*权限足够*/
									drop(new File(fp+"\\"+c));/*删除文件夹*/
									sta.executeUpdate("delete from folder where fname='"+c+"';");/*修改数据库*/
									renew();/*刷新*/
								}else {
									output.append("权限不足！\n");
								}
								break;
							}
						}catch (Exception e1) {
							output.append("读取数据库发生错误！\n");
						}
					}else {/*删除文件*/
						c=c.replace("文件：", "");
						File dfile=new File(fp);
						try {/*检查权限*/
							PreparedStatement pstm = con.prepareStatement("select * from files where fname='"+dfile.getName()+"' and name='"+c+"' and acc='"+id+"';");
							ResultSet rs = pstm.executeQuery();
							while(rs.next()) {
								int auth=rs.getInt("auth");
								if(auth>2) {/*权限足够*/
									File df=new File(fp+"\\"+c);
									df.delete();/*删除文件*/
									sta.executeUpdate("delete from files where name='"+c+"';");/*修改文件表*/
									renew();/*刷新*/
								}else {
									output.append("权限不足！\n");
								}
							}
						}catch (Exception e2) {
							output.append("读取数据库发生错误！\n");
						}
					}
				} catch (Exception e2) {
					output.append("未选择正确的文件(文件夹)或数据库操作发生错误！\n");
				}
			}
		});
		
	}
	
	public void renew() {/*更新界面的函数*/
		File file=new File(fp);
		opencombo.removeAllItems();/*清空下拉框与信息输出框*/
		renamecombo.removeAllItems();
		deletecombo.removeAllItems();
		output.setText("");
		output.append("当前路径："+file.getPath()+"\n");
		String id=getTitle();
		for(File f:file.listFiles()) {/*遍历该文件夹下子文件夹与文件*/
			String fn=f.getName();
			if(f.isDirectory()) {/*如果是文件夹*/
				try {/*检查权限*/
					PreparedStatement pstm = con.prepareStatement("select * from folder where acc='"+id+"';");
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						String fname = rs.getString("fname").trim();
						if(fname.equals(fn)) {
							int auth=rs.getInt("auth");
							if(auth>0) {/*权限足够*/
								output.append("文件夹：");
								output.append("path="+f.getPath()+"\n");
								opencombo.addItem("文件夹："+fn);
								renamecombo.addItem("文件夹："+fn);
								deletecombo.addItem("文件夹："+fn);
							}
						}
					}							
				} catch (Exception e2) {
					output.append("读取数据库发生错误！\n");
				}
			}else{/*其他：均视为文件*/
				try {/*检查权限*/
					PreparedStatement pstm = con.prepareStatement("select * from files where acc='"+id+"';");
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						String fname = rs.getString("name").trim();
						if(fname.equals(fn)) {
							int auth=rs.getInt("auth");
							if(auth>0) {/*权限足够*/
								output.append("文件：");
								output.append("path="+f.getPath()+"\n");
								opencombo.addItem("文件："+fn);
								renamecombo.addItem("文件："+fn);
								deletecombo.addItem("文件："+fn);
							}
						}
					}							
				} catch (Exception e2) {
					output.append("读取数据库发生错误！\n");
				}
			}
		}
	}
	
	public void drop(File f) {/*删除文件夹的函数*/
		for(File f1:f.listFiles()) {
			if(f1.isDirectory()) {
				drop(f1);
			}else {
				f1.delete();
			}
		}
		f.delete();
	}
	
	public void close() {/*关闭自己的函数*/
		this.dispose();
	}

}