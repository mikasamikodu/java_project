package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import emptity.User;

public class UserMain2 extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_top=null;
	private JPanel jp_right=null;
	private JDesktopPane jd_center=null;
	
	private JLabel jl_welcome=null;
	private JLabel jl_desk=null;
	private JButton jb_rent=null;
	private JButton jb_record=null;
	private JButton jb_exit=null;
	private User user=null;
	public UserMain2(User user) {
		this.user=user;
		init();
		Register();
	}
	
	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new BorderLayout());
		jp_top=new JPanel();
		jp_right=new JPanel();
		jp_right.setLayout(new GridLayout(7, 1,0,30));
		jd_center=new JDesktopPane();
		
		jl_welcome=new JLabel();
		jl_welcome.setText("欢    迎   "+user.getUname()+"  光     临    影    碟     租    赁    系    统");
		jl_welcome.setFont(new Font("黑体", Font.BOLD, 20));
		jl_welcome.setForeground(Color.BLUE);
		
		jb_rent=new JButton("管理员DVD操作");
		jb_record=new JButton("查看租赁DVD记录");
		jb_exit=new JButton("退出系统");
		jp_right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"快捷功能区域"));
		
		ImageIcon image=new ImageIcon("src/image/108.jpg");
		jl_desk=new JLabel(image);
		jl_desk.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		
		jp_top.add(jl_welcome);
		jp_right.add(new JLabel());
		jp_right.add(new JLabel());
		jp_right.add(jb_rent);
		jp_right.add(jb_record);
		jp_right.add(jb_exit);
		jp_right.add(new JLabel());
		jp_right.add(new JLabel());
		jd_center.add(jl_desk,new Integer(Integer.MIN_VALUE));
		
		jp_main.add(jp_top,BorderLayout.NORTH);
		jp_main.add(jp_right,BorderLayout.EAST);
		jp_main.add(jd_center,BorderLayout.CENTER);
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Thread(new WordThread()).start();
			}
		});
		this.getContentPane().add(jp_main);
		
		this.setTitle("影碟租赁系统");
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Register() {
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});
		jb_rent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DVDRent2 rent=new DVDRent2();
				jd_center.add(rent);
				rent.toFront();
			}
		});
		jb_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DVDReturn2 return2=new DVDReturn2();
				jd_center.add(return2);
				return2.toFront();
			}
		});
	}
	
	private class WordThread implements Runnable{
		@Override
		public void run() {
				while(true) {
					for(int i=800;i>-85;i--) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						jl_welcome.setLocation(i,5);
					}
				}
		}
	}
	
}
