package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import biz.Userbiz;
import biz.impl.UserbizImpl;
import emptity.User;

public class UserLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jp_main=null;
	private JPanel jp_left=null;
	private JPanel jp_right=null;
	
	private JLabel jl_image=null;
	private JLabel jl_uname=null;
	private JLabel jl_upass=null;
	private JLabel jl_utype=null;
	private JTextField jt_uname=null;
	private JPasswordField jp_upass=null;
	private JComboBox<String> jc_utype=null;
	
	private JButton jb_login=null;
	private JButton jb_register=null;
	private Userbiz userbiz=null;
	
	public UserLogin() {
		userbiz=new UserbizImpl();
		init();
		Register();
	}
	
	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new GridLayout(1, 2));
		jp_left=new JPanel();
		jp_right=new JPanel();
		jp_right.setLayout(new GridLayout(4, 2, 0, 32));
		
		jl_image=new JLabel();
		jl_image.setIcon(new ImageIcon(ClassLoader.getSystemResource("image/login.jpg")));
		
		jl_uname=new JLabel("用   户：",JLabel.CENTER);
		jl_upass=new JLabel("密   码：",JLabel.CENTER);
		jl_utype=new JLabel("类   型：",JLabel.CENTER);
		jt_uname=new JTextField();
		jp_upass=new JPasswordField();
		jc_utype=new JComboBox<String>(new String[] {"管理员","普通用户"});
		jb_login=new JButton("登   录");
		jb_register=new JButton("注   册");
		
		jp_left.add(jl_image);
		jp_right.add(jl_uname);
		jp_right.add(jt_uname);
		jp_right.add(jl_upass);
		jp_right.add(jp_upass);
		jp_right.add(jl_utype);
		jp_right.add(jc_utype);
		jp_right.add(jb_login);
		jp_right.add(jb_register);
		jp_main.add(jp_left);
		jp_main.add(jp_right);
		this.getContentPane().add(jp_main);
		
		this.pack();
		this.setSize(400,250);
		this.setTitle("登录窗口");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Register() {
		
		jb_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRegister();
				UserLogin.this.dispose();
			}
		});
		jb_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=jt_uname.getText().trim();
				String upass=new String(jp_upass.getPassword());
				int utype=jc_utype.getSelectedIndex();
				if(uname.equals("")) {
					JOptionPane.showMessageDialog(UserLogin.this, "用户名不能为空");
					return;
				}else if(upass.equals("")) {
					JOptionPane.showMessageDialog(UserLogin.this, "密码不能为空");
					return;
				}
				User user=new User(uname, upass, utype);
				user=userbiz.login(user);
				if(user!=null) {
					if(user.getUtype()==1) {
						new UserMain(user);
					}else{
						new UserMain2(user);
					}
					UserLogin.this.dispose();
				}else {
					JOptionPane.showMessageDialog(UserLogin.this, "用户名或密码错误");
				}
			}
		});
	}
}
