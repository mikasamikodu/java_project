package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import emptity.User;

public class UserRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_1=null;
	private JPanel jp_2=null;
	private JPanel jp_3=null;
	private JPanel jp_4=null;
	private JPanel jp_5=null;
	
	private JLabel jl_uname=null;
	private JTextField jt_uname=null;
	private JLabel jl_upass=null;
	private JPasswordField jp_upass=null;
	private JLabel jl_upass2=null;
	private JPasswordField jp_upass2=null;
	private JButton jb_true=null;
	private JButton jb_exit=null;
	private UserDao userDao=null; 
	
	public UserRegister() {
		userDao=new UserDaoImpl();
		init();
		Register();
	}
	
	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new GridLayout(5, 1));
		jp_1=new JPanel();
		jp_2=new JPanel();
		jp_3=new JPanel();
		jp_4=new JPanel();
		jp_5=new JPanel();
		
		jl_uname=new JLabel("用户名    ：");
		jl_upass=new JLabel("初始密码：");
		jl_upass2=new JLabel("确认密码：");
		jt_uname=new JTextField(10);
		jp_upass=new JPasswordField(10);
		jp_upass2=new JPasswordField(10);
		jb_exit=new JButton("退   出");
		jb_true=new JButton("提   交");
		
		jp_2.add(jl_uname);
		jp_2.add(jt_uname);
		jp_3.add(jl_upass);
		jp_3.add(jp_upass);
		jp_4.add(jl_upass2);
		jp_4.add(jp_upass2);
		jp_5.add(jb_true);
		jp_5.add(jb_exit);
		
		jp_main.add(jp_1);
		jp_main.add(jp_2);
		jp_main.add(jp_3);
		jp_main.add(jp_4);
		jp_main.add(jp_5);
		this.getContentPane().add(jp_main);
		
		this.setTitle("注册窗口");
		this.setSize(300, 240);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(jb_true);
		this.setVisible(true);
	}
	
	private void Register() {
		jb_true.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=jt_uname.getText().trim();
				String upass=new String(jp_upass.getPassword());
				String upass2=new String(jp_upass2.getPassword());
				if(uname.equals("")) {
					JOptionPane.showMessageDialog(UserRegister.this, "用户名为空，请填写");
					return;
				}
				if(upass.equals("")) {
					JOptionPane.showMessageDialog(UserRegister.this, "密码为空，请填写");
					return;
				}
				if(!upass.equals(upass2)) {
					JOptionPane.showMessageDialog(UserRegister.this, "密码不一致，请确认");
					return;
				}
				User user=new User(uname,upass,1);
				boolean b1=userDao.saveUser(user);
				if(b1) {
					JOptionPane.showMessageDialog(UserRegister.this,"注册成功,请登录。");
					UserRegister.this.dispose();
					new UserLogin();
				}else {
					JOptionPane.showMessageDialog(UserRegister.this,"注册失败");
					return;
				}
			}
		});
	}
}
