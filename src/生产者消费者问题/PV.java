package ����������������;

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
		P.setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(P);
		
		JLabel V = new JLabel("\u6D88\u8D39\u8005\u4E2A\u6570\uFF1A");
		V.setBounds(10, 60, 110, 30);
		V.setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(V);
		
		JLabel M = new JLabel("\u7F13\u51B2\u533A\u5927\u5C0F\uFF1A");
		M.setFont(new Font("����", Font.PLAIN, 18));
		M.setBounds(10, 110, 110, 30);
		contentPane.add(M);
		
		JLabel error = new JLabel("");
		error.setBounds(200, 60, 226, 30);
		error.setForeground(Color.RED);
		error.setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(error);
		
		Ptext = new JTextField();
		Ptext.setBounds(130, 10, 60, 30);
		Ptext.setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(Ptext);
		Ptext.setColumns(10);
		
		Vtext = new JTextField();
		Vtext.setBounds(130, 60, 60, 30);
		Vtext.setFont(new Font("����", Font.PLAIN, 18));
		Vtext.setColumns(10);
		contentPane.add(Vtext);
		
		Mtext = new JTextField();
		Mtext.setFont(new Font("����", Font.PLAIN, 18));
		Mtext.setColumns(10);
		Mtext.setBounds(130, 110, 60, 30);
		contentPane.add(Mtext);
		
		JButton startButton = new JButton("\u5F00\u59CB\u6A21\u62DF");
		startButton.setBounds(250, 10, 110, 30);
		startButton.setFont(new Font("����", Font.PLAIN, 18));
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
					error.setText("�����������������룡");
				}

			}
		});
	}
	
	public class buffer {
		private List<String> list=new ArrayList<>();	
		private int max;/*��������С*/
		public buffer() { super(); }
		public buffer(int m) { super(); max=m; }
		public boolean pro(String s) {/*�����ߵ���*/
			synchronized (this) {
				if(list.size()<max) {
					list.add(s);
					textoutput.append("������@"+Thread.currentThread().getName()+":������"+s+"����ǰ��Ʒ����"+list.size()+"\n");
					try {notify();} catch (Exception e) {}
					return true;
				}
				else {
					textoutput.append("������������������ȴ�ϵͳ�����������󡭡�\n");
					try {wait(500);} catch (Exception e) {}
					return false;
				}
			}
		}
		public void con() {/*�����ߵ���*/
			synchronized (this) {
				if(list.size()>0) {
					String s=list.remove(0);
					count++;
					textoutput.append("������@"+Thread.currentThread().getName()+":������"+s+"�������Ѳ�Ʒ����"+list.size()+"\n");
					try {notify();} catch (Exception e) {}
				}
				else {
					textoutput.append("������@"+Thread.currentThread().getName()+"���޲�Ʒ���ȴ��С���\n");
					try {wait(500);} catch (Exception e) {}
				}
			}
		}
	}
	
	public class P implements Runnable {
		private int n;//һλ������������Ʒ������
		private buffer b;
		public P() {super();}
		public P(buffer pb,int i) {super();b=pb;n=i;}
		public void run() {
			int temp=1;
			textoutput.append("������@"+Thread.currentThread().getName()+":�ƻ�����"+n+"�ݲ�Ʒ��\n");
			while (true) {
				textoutput.append("������@"+Thread.currentThread().getName()+"����������"+temp+"�ݲ�Ʒ��\n");
				boolean f=b.pro("��Ʒ"+Thread.currentThread().getName()+'-'+temp);
				if(f) {temp++;n--;}
				if(n<1) { break; }
				try {Thread.sleep(100);}
				catch (InterruptedException e) {}
			}
			textoutput.append("������@"+Thread.currentThread().getName()+"->���������\n");
		}
	}
	
	public class V implements Runnable {
		private buffer b;
		public V() {super();}
		public V(buffer pb) {super();b=pb;}
		public void run() {
			while (true) {
				if(count<ALL) {
					textoutput.append("������@"+Thread.currentThread().getName()+":��������1�ݲ�Ʒ��\n");
					b.con();
				}
				else {
					textoutput.append("���в�Ʒ�Ѿ������꣬���̼߳����˳���������@"+Thread.currentThread().getName()+"\n");
					break;
				}
				try {Thread.sleep(100);}
				catch (InterruptedException e) {}
			}
		}
	}
}