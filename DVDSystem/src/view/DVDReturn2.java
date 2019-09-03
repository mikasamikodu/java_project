package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.RecordDao;
import dao.impl.RecordDaoImpl;
import emptity.Record2;

public class DVDReturn2 extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_center=null;
	private JPanel jp_right=null;
	
	private JTable jt_text=null;
	private JLabel jl_type=null;
	private JComboBox<String> jc_type=null;
	private JButton jb_search=null;
	private JTextField jt_search=null;
	private JButton jb_exit=null;
	private JScrollPane js_table=null;
	private List<Record2> list=null;
	private RecordDao recordDao=null;
	private ToTable toTable=null;
	
	public DVDReturn2() {
		recordDao=new RecordDaoImpl();
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
		jp_right.setLayout(new GridLayout(9, 1,0,5));
		
		list=new ArrayList<>();
		jt_text=new JTable();
		js_table=new JScrollPane(jt_text);
		jl_type=new JLabel("查询类型");
		jc_type=new JComboBox<String>(new String[] {"用户租赁记录","DVD租赁记录"});
		jt_search=new JTextField();
		jb_search=new JButton("查   询");
		jb_exit=new JButton("退   出");
		
		jp_center.add(js_table);
		jp_right.add(jl_type);
		jp_right.add(jc_type);
		jp_right.add(jt_search);
		jp_right.add(jb_search);
		jp_right.add(new JLabel());
		jp_right.add(new JLabel());
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
				int flag=JOptionPane.showInternalConfirmDialog(DVDReturn2.this, "是否退出",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVDReturn2.this.dispose();
				}
			}
		});
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=jt_search.getText().trim();
				int index=jc_type.getSelectedIndex();
				if(index==0) {
					if(name.equals("")) {
						JOptionPane.showMessageDialog(DVDReturn2.this,"输入框内容不能为空");
						return;
					}
					list=recordDao.selectRecordUname(name);
				}else {
					if(name.equals("")) {
						JOptionPane.showMessageDialog(DVDReturn2.this,"输入框内容不能为空");
						return;
					}
					list=recordDao.selectRecordDname(name);
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
				return "用户编号";
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
