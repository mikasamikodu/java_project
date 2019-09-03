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
import emptity.User;

public class DVDRent extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_center=null;
	private JPanel jp_right=null;
	
	private JTable jt_text=null;
	private JLabel jl_type=null;
	private JComboBox<String> jc_type=null;
	private JButton jb_search=null;
	private JButton jb_rent=null;
	private JButton jb_exit=null;
	private DVDDao  dvdDao=null;
	private List<DVD> list=null;
	private ToTable toTable=null;
	private JScrollPane js_table=null;
	private RecordDao recordDao=null;
	private User user=null;
	
	public DVDRent(User user) {
		dvdDao=new DVDDaoImpl();
		recordDao=new RecordDaoImpl();
		this.user=user;
		init();
		Register();
	}
	
	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new BorderLayout());
		jp_center=new JPanel();
		jp_center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"查询信息"));
		jp_right=new JPanel();
		jp_right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"查询操作"));
		jp_right.setLayout(new GridLayout(7, 1,0,20));
		
		jt_text=new JTable();
		js_table=new JScrollPane(jt_text);
		jl_type=new JLabel("查询类型");
		jc_type=new JComboBox<String>(new String[] {"全部DVD","可借DVD","已借DVD","热门DVD"});
		jb_search=new JButton("查   询");
		jb_rent=new JButton("租   赁");
		jb_rent.setEnabled(false);
		jb_exit=new JButton("退   出");
		list=new ArrayList<>();
		
		
		jp_center.add(js_table);
		jp_right.add(jl_type);
		jp_right.add(jc_type);
		jp_right.add(jb_search);
		jp_right.add(jb_rent);
		jp_right.add(new JLabel());
		jp_right.add(new JLabel());
		jp_right.add(jb_exit);
		
		jp_main.add(jp_right,BorderLayout.EAST);
		jp_main.add(jp_center,BorderLayout.CENTER);
		this.add(jp_main);
		
		this.setTitle("DVD查询操作");
		this.setSize(600, 400);
		this.setClosable(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Register() {
		jb_rent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=jt_text.getSelectedRow();
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent.this, "是否租赁此DVD",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					int did=Integer.parseInt(jt_text.getValueAt(row, 0).toString());
					String dname=jt_text.getValueAt(row, 1).toString();
					int dcount=Integer.parseInt(jt_text.getValueAt(row, 2).toString());
					String status=jt_text.getValueAt(row, 3).toString();
					if(status.equals("可借")) {
						DVD dvd=new DVD(did,dname,(dcount+1),1);
						boolean flag1=dvdDao.updateDVD(dvd);
						Calendar calendar=Calendar.getInstance();
						String lendtime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
						Record record=new Record(user.getId(),did,lendtime,null); 
						boolean flag2=recordDao.saveRecord(record);
						if(flag1&&flag2) {
							JOptionPane.showMessageDialog(DVDRent.this,"租赁成功");
						}else {
							JOptionPane.showMessageDialog(DVDRent.this,"租赁失败");
						}
					}else if(status.equals("已借")){
						JOptionPane.showMessageDialog(DVDRent.this, "DVD已借出，无法租赁！");
						return;
					}
				}
			}
		});
		jt_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jt_text.getSelectedRow()!=-1) {
					jb_rent.setEnabled(true);
				}
			}
		});
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent.this, "是否退出",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVDRent.this.dispose();
				}
			}
		});
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=jc_type.getSelectedIndex();
				if(index==0) {
					list=dvdDao.selectDVD();
				}else if(index==1) {
					list=dvdDao.selectDVDStatus(0);
				}else if(index==2) {
					list=dvdDao.selectDVDStatus(1);
				}else if(index==3) {
					list=dvdDao.selectDVDNumber(0, 5);
				}
				reflush(list);
			}
		});
	}
	
	private class ToTable implements TableModel{
		private List<DVD> list=null;
		
		public ToTable(List<DVD> list) {
			this.list=list;
		}
		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "影碟ID";
			}else if(columnIndex==1) {
				return "DVD名字";
			}else if(columnIndex==2) {
				return "DVD借出次数";
			}else if(columnIndex==3) {
				return "DVD状态";
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
			DVD dvd=list.get(rowIndex);
			if(columnIndex==0) {
				return dvd.getId();
			}else if(columnIndex==1) {
				return dvd.getDname();
			}else if(columnIndex==2) {
				return dvd.getDcount();
			}else if(columnIndex==3) {
				return (dvd.getStatus()==1?"已借":"可借");
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
	
	private void reflush(List<DVD> list) {
		toTable=new ToTable(list);
		jt_text.setModel(toTable);
	}
}
