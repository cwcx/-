package 生产者消费者问题;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;


public class PV extends JFrame {
	private JPanel contentPane;
	private JTextField Ptext;
	private JTextField Vtext;
	private int count;
	private int ALL;
	public JTextArea textoutput = new JTextArea();
	private JTextField Mtext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PV frame = new PV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PV() {
		setTitle("\u751F\u4EA7\u8005\u6D88\u8D39\u8005\u95EE\u9898");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel P = new JLabel("\u751F\u4EA7\u8005\u4E2A\u6570\uFF1A");
		P.setBounds(10, 10, 110, 30);
		P.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(P);
		
		JLabel V = new JLabel("\u6D88\u8D39\u8005\u4E2A\u6570\uFF1A");
		V.setBounds(10, 60, 110, 30);
		V.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(V);
		
		JLabel M = new JLabel("\u7F13\u51B2\u533A\u5927\u5C0F\uFF1A");
		M.setFont(new Font("宋体", Font.PLAIN, 18));
		M.setBounds(10, 110, 110, 30);
		contentPane.add(M);
		
		JLabel error = new JLabel("");
		error.setBounds(200, 60, 226, 30);
		error.setForeground(Color.RED);
		error.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(error);
		
		Ptext = new JTextField();
		Ptext.setBounds(130, 10, 60, 30);
		Ptext.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(Ptext);
		Ptext.setColumns(10);
		
		Vtext = new JTextField();
		Vtext.setBounds(130, 60, 60, 30);
		Vtext.setFont(new Font("宋体", Font.PLAIN, 18));
		Vtext.setColumns(10);
		contentPane.add(Vtext);
		
		Mtext = new JTextField();
		Mtext.setFont(new Font("宋体", Font.PLAIN, 18));
		Mtext.setColumns(10);
		Mtext.setBounds(130, 110, 60, 30);
		contentPane.add(Mtext);
		
		JButton startButton = new JButton("\u5F00\u59CB\u6A21\u62DF");
		startButton.setBounds(250, 10, 110, 30);
		startButton.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(startButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 416, 303);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(textoutput);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textoutput.setText("");
					error.setText("");
					int n1=Integer.parseInt(Ptext.getText());
					int n2=Integer.parseInt(Vtext.getText());
					int max=Integer.parseInt(Mtext.getText());
					count=0;
					ALL=0;
					for(int i=1;i<=n1;++i) {
						ALL+=i;
					}
					buffer b=new buffer(max);
					for(int i=1;i<=n1;++i) {
						new Thread(new P(b,i)).start();
					}
					for(int i=1;i<=n2;++i) {
						new Thread(new V(b)).start();
					}
				} catch (Exception e2) {
					error.setText("输入有误，请重新输入！");
				}

			}
		});
	}
	
	public class buffer {
		private List<String> list=new ArrayList<>();	
		private int max;/*缓冲区大小*/
		public buffer() { super(); }
		public buffer(int m) { super(); max=m; }
		public boolean pro(String s) {/*生产者调用*/
			synchronized (this) {
				if(list.size()<max) {
					list.add(s);
					textoutput.append("生产者@"+Thread.currentThread().getName()+":已生产"+s+"，当前产品总量"+list.size()+"\n");
					try {notify();} catch (Exception e) {}
					return true;
				}
				else {
					textoutput.append("生产请求队列已满，等待系统处理生产请求……\n");
					try {wait(500);} catch (Exception e) {}
					return false;
				}
			}
		}
		public void con() {/*消费者调用*/
			synchronized (this) {
				if(list.size()>0) {
					String s=list.remove(0);
					count++;
					textoutput.append("消费者@"+Thread.currentThread().getName()+":已消费"+s+"，待消费产品余量"+list.size()+"\n");
					try {notify();} catch (Exception e) {}
				}
				else {
					textoutput.append("消费者@"+Thread.currentThread().getName()+"暂无产品，等待中……\n");
					try {wait(500);} catch (Exception e) {}
				}
			}
		}
	}
	
	public class P implements Runnable {
		private int n;//一位生产者生产产品的总量
		private buffer b;
		public P() {super();}
		public P(buffer pb,int i) {super();b=pb;n=i;}
		public void run() {
			int temp=1;
			textoutput.append("生产者@"+Thread.currentThread().getName()+":计划生产"+n+"份产品。\n");
			while (true) {
				textoutput.append("生产者@"+Thread.currentThread().getName()+"正在生产第"+temp+"份产品。\n");
				boolean f=b.pro("产品"+Thread.currentThread().getName()+'-'+temp);
				if(f) {temp++;n--;}
				if(n<1) { break; }
				try {Thread.sleep(100);}
				catch (InterruptedException e) {}
			}
			textoutput.append("生产者@"+Thread.currentThread().getName()+"->完成生产。\n");
		}
	}
	
	public class V implements Runnable {
		private buffer b;
		public V() {super();}
		public V(buffer pb) {super();b=pb;}
		public void run() {
			while (true) {
				if(count<ALL) {
					textoutput.append("消费者@"+Thread.currentThread().getName()+":打算消费1份产品。\n");
					b.con();
				}
				else {
					textoutput.append("所有产品已经消费完，本线程即刻退出。消费者@"+Thread.currentThread().getName()+"\n");
					break;
				}
				try {Thread.sleep(100);}
				catch (InterruptedException e) {}
			}
		}
	}
}