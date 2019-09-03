package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.DVDDao;
import dao.RecordDao;
import dao.impl.DVDDaoImpl;
import dao.impl.RecordDaoImpl;
import emptity.DVD;
import emptity.Record;
import emptity.Record2;
import emptity.User;

public class DVDReturn extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_center=null;
	private JPanel jp_right=null;
	
	private JTable jt_text=null;
	private JLabel jl_type=null;
	private JComboBox<String> jc_type=null;
	private JButton jb_search=null;
	private JButton jb_return=null;
	private JButton jb_exit=null;
	private JScrollPane js_table=null;
	private ToTable toTable=null;
	private RecordDao recordDao=null;
	private User user=null;
	private List<Record2> list=null;
	private DVDDao dvdDao=null;
	
	public DVDReturn(User user) {
		recordDao=new RecordDaoImpl();
		dvdDao=new DVDDaoImpl();
		this.user=user;
		init();
		Register();
	}

	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new BorderLayout());
		jp_center=new JPanel();
		jp_center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"个人租赁记录"));
		jp_right=new JPanel();
		jp_right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"查询操作"));
		jp_right.setLayout(new GridLayout(7, 1,0,20));
		
		list=new ArrayList<>();
		jt_text=new JTable();
		js_table=new JScrollPane(jt_text);
		jl_type=new JLabel("查询类型");
		jc_type=new JComboBox<String>(new String[] {"全部记录","借DVD记录","还DVD记录"});
		jb_search=new JButton("查   询");
		jb_return=new JButton("退   还");
		jb_return.setEnabled(false);
		jb_exit=new JButton("退   出");
		
		jp_center.add(js_table);
		jp_right.add(jl_type);
		jp_right.add(jc_type);
		jp_right.add(jb_search);
		jp_right.add(jb_return);
		jp_right.add(new JLabel());
		jp_right.add(new JLabel());
		jp_right.add(jb_exit);
		
		jp_main.add(jp_right,BorderLayout.EAST);
		jp_main.add(jp_center,BorderLayout.CENTER);
		this.add(jp_main);
		
		this.setTitle("DVD租赁记录");
		this.setSize(600, 400);
		this.setClosable(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Register() {
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(DVDReturn.this, "是否退出",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVDReturn.this.dispose();
				}
			}
		});
		
		jb_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=jt_text.getSelectedRow();
				int id=Integer.parseInt(jt_text.getValueAt(row, 0).toString());
				int did=Integer.parseInt(jt_text.getValueAt(row, 1).toString());
				String dname=jt_text.getValueAt(row, 3).toString();
				String lendtime=jt_text.getValueAt(row, 4).toString();
				int flag=JOptionPane.showInternalConfirmDialog(DVDReturn.this, "确定退还DVD?",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVD dvd1=dvdDao.selectDVDId(did);
					DVD dvd=new DVD(did,dname,dvd1.getDcount(),0);
					boolean flag1=dvdDao.updateDVD(dvd);
					Calendar calendar=Calendar.getInstance();
					String returntime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
					Record record=new Record(id,user.getId(),did,lendtime,returntime); 
					boolean flag2=recordDao.updateRecord(record);
					if(flag1&&flag2) {
						JOptionPane.showMessageDialog(DVDReturn.this,"退还成功！");
					}else {
						JOptionPane.showMessageDialog(DVDReturn.this,"退还失败！");
					}
					
				}
			}
		});
		jt_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jt_text.getSelectedRow()!=-1) {
					jb_return.setEnabled(true);
				}
			}
		});
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=jc_type.getSelectedIndex();
				if(index==0) {
					list=recordDao.selectRecordUname(user.getUname());
				}else if(index==1) {
					list=recordDao.selectRecordTime(false, user.getUname());
				}else if(index==2) {
					list=recordDao.selectRecordTime(true, user.getUname());
				}
				reflush(list);
			}
		});
	}
	
	private void reflush(List<Record2> list) {
		toTable=new ToTable(list);
		jt_text.setModel(toTable);
	}
	
	private class ToTable implements TableModel{
		private List<Record2> list=null;
		
		public ToTable(List<Record2> list) {
			this.list=list;
		}
		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "记录编号";
			}else if(columnIndex==1) {
				return "DVD编号";
			}else if(columnIndex==2) {
				return "用户姓名";
			}else if(columnIndex==3) {
				return "DVD名字";
			}else if(columnIndex==4) {
				return "借出时间";
			}else if(columnIndex==5) {
				return "归还时间";
			}else {
				return "错误";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Record2 record2=list.get(rowIndex);
			if(columnIndex==0) {
				return record2.getId();
			}else if(columnIndex==1) {
				return record2.getDid();
			}else if(columnIndex==2) {
				return record2.getUname();
			}else if(columnIndex==3) {
				return record2.getDname();
			}else if(columnIndex==4) {
				return record2.getLendtime();
			}else if(columnIndex==5) {
				return record2.getReturntime();
			}else {
				return "错误";
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			
		}
		
	}
}
