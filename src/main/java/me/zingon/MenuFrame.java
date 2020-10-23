package me.zingon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import freemarker.template.TemplateException;
import me.zingon.backup.util.Config;
import me.zingon.backup.util.Maps;
import me.zingon.backup.util.MyUtil;
import me.zingon.db.DBcon;
import me.zingon.db.TableDao;
import me.zingon.model.Field;
import me.zingon.model.FrontAttr;
import me.zingon.service.ControllerService;
import me.zingon.service.DaoService;
import me.zingon.service.FrontService;
import me.zingon.service.ModelService;
import me.zingon.service.QueryService;
import me.zingon.service.ServiceService;
import me.zingon.service.impl.ControllerServiceImpl;
import me.zingon.service.impl.DaoServiceImpl;
import me.zingon.service.impl.FrontServiceImpl;
import me.zingon.service.impl.ModelServiceImpl;
import me.zingon.service.impl.QueryServiceImpl;
import me.zingon.service.impl.ServiceServiceImpl;

//加载数据界面
public class MenuFrame extends JFrame {

	private ModelService ms=new ModelServiceImpl();
	private DaoService ds=new DaoServiceImpl();
	private ServiceService ss=new ServiceServiceImpl();
	private ControllerService cs=new ControllerServiceImpl();
	private FrontService fs=new FrontServiceImpl();
	private QueryService qs = new QueryServiceImpl();

	private TableDao tableDao  = new TableDao();


	private static String table = "";

	private JList fieldNameList = new JList();
	private List<Field> fieldList = null;


	DefaultTableModel datamodel = new DefaultTableModel(); 
	/**
	 * ui变量
	 * 
	 * -------------------------------------------------------------------------------------------------------------
	 */	
	//是否必填chk
	JCheckBox isNullchk = new JCheckBox();
	//是否生成controller
	JCheckBox ctlchk = new JCheckBox();
	//是否生成service
	JCheckBox servicechk = new JCheckBox();
	//是否生成dao
	JCheckBox daochk = new JCheckBox();
	//是否生成html和js
	JCheckBox frontchk = new JCheckBox();
	//是否生成model
	JCheckBox modelchk = new JCheckBox();
	//是否生成query
	JCheckBox querychk = new JCheckBox();

	
	
	
	
	//项目存放目录
	JTextField proPath = new JTextField(Config.get("path"));


	//前端类型下拉菜单
	JComboBox<String> typeBox =new JComboBox<String>(  );
	//前端对否必填下拉菜单
	JComboBox<String> nullBox =new JComboBox<String>(  );
	//是否查询条件
	JComboBox<String> isSearch =new JComboBox<String>(  );
	//列table
	JTable t;
	//分级目录
	private JTextField extra;
	//包名
	private JTextField pakageName;
	/**
	 * -------------------------------------------------------------------------------------------------------------
	 */

	public MenuFrame(String title) {
		super(title);
		this.setBounds(0, 0, 800, 800);
		this.setLocationRelativeTo(null);//居中

		nullBox.addItem("YES");
		nullBox.addItem("NO");

		
		isSearch.addItem("NO");
		isSearch.addItem("YES");
		isSearch.setSelectedIndex(0);
		
		typeBox.addItem("input");
		typeBox.addItem("textarea");
		typeBox.addItem("date");
		typeBox.addItem("select");
		typeBox.addItem("checkbox");
		typeBox.addItem("hidden");

		showTable();
	}


	public void showTable() {
		this.setLayout(new BorderLayout());
		add( addEast(),BorderLayout.WEST);
		add( addSouth(),BorderLayout.SOUTH);
		add(addCenter() , BorderLayout.CENTER);
		add(addNorth() , BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 开始生成
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	private void generate() throws IOException, TemplateException {
		
		if(modelchk.isSelected()) {
			ms.createModel(table);
		}
		if(daochk.isSelected()) {
			ds.createDao(table);
			ds.createMapper(table);
		}
		if(servicechk.isSelected()) {
			ss.createService(table);
			ss.createServiceImpl(table);
		}
		if(ctlchk.isSelected()) {
			cs.createController(table);
		}
		
		if(querychk.isSelected()) {
			qs.createQuery(table);
		}
		if(frontchk.isSelected()) {
			fs.createJsp(table);
		}
		
		
		
	}


	/**
	 * 中间列名
	 * @return
	 */
	
	private Component addCenter() {
		JPanel pan1 = new JPanel();
		String[] columnNames = new String[] { "列名", "类型", "是否null", "前端类型" };
		Object[][] heros = new Object[1][1] ;
		t = new JTable(datamodel);
		datamodel.setDataVector(heros, columnNames);

		// 根据t创建 JScrollPane
		t .setFillsViewportHeight(true);

		JScrollPane sp = new JScrollPane(t);
		pan1.add(sp);

		return pan1;
	}
	/**
	 * 添加上方
	 * @return
	 */
	private Component addNorth() {
		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 500, 500);
		pan1.setLayout(new GridLayout(2, 6, 5, 5));
		isNullchk.setText("是否必填");

		ctlchk.setText("生成controller");
		modelchk.setText("生成model");
		servicechk.setText("生成service");
		daochk.setText("生成dao");
		frontchk.setText("生成html和js");
		querychk.setText("生成query");


		pan1.add(ctlchk);
		pan1.add(servicechk);
		pan1.add(daochk);
		
		pan1.add(modelchk);
		pan1.add(querychk);
		pan1.add(frontchk);

		JButton chosePath = new JButton("项目路径");

		chosePath.addActionListener(new ActionListener() {//点击

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				if(file!=null && file.isDirectory()){
					System.out.println("文件夹:"+file.getAbsolutePath());
					proPath.setText( file.getAbsolutePath());
				}
//				System.out.println(jfc.getSelectedFile().getName());

			}
		});

		pan1.add(proPath);
		pan1.add(chosePath);
		
		JPanel pan2 = new JPanel();
		extra = new JTextField("分级目录" );
		pakageName = new JTextField("包名" );
		pan2.add(extra);
		pan2.add(pakageName);
		pan1.add(pan2);
		return pan1;
	}

	/**
	 * 列属性
	 * @return
	 */
	private Component addattrPane() {
		JPanel pan1 = new JPanel();
		pan1.setLayout(new FlowLayout());

		isNullchk.setText("是否必填");
		isNullchk.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//检查是否选中列
				if(fieldNameList.isSelectionEmpty()) {
					App.alert("请选中列");
				}else {
					//设置前端属性ui
					Field f = fieldList.get(fieldNameList.getSelectedIndex());
					if(f.getFrontattr() != null) {
						f.getFrontattr().setIsNull(e.getStateChange() == 1 ? 1 : 0);
					}
				}

			}
		});

		pan1.add(typeBox);
		pan1.add(isNullchk);
		return pan1;
	}


	/**
	 * 列名表
	 * @return
	 */
	private Component addFieldsPane() {
		JPanel pan1 = new JPanel();
		String[] fileds = {};
		fieldNameList = new JList(fileds);
		fieldNameList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!fieldNameList.getValueIsAdjusting()){	//设置只有释放鼠标时才触发
					Field f = fieldList.get(fieldNameList.getSelectedIndex());
					if(f.getFrontattr() ==null) {
						isNullchk.setSelected(false);
						FrontAttr ft = new FrontAttr();
						ft.setIsNull(0);
						f.setFrontattr(ft);
						//						System.out.println("重新创建属性");
					}else {
						FrontAttr ft = f.getFrontattr();
						isNullchk.setSelected( ft.getIsNull() == 0 ? false : true);
						//						System.out.println("以前有属性");
					}
				}
			}
		});
		pan1.add(fieldNameList);
		return pan1;
	}


	/**
	 * 下方按钮组
	 * @return
	 */
	private Component addSouth() {
		JPanel pan1 = new JPanel();
		JButton btn = new JButton("生成");
		JButton btn2 = new JButton("查看");
		pan1.setLayout(new FlowLayout());
		pan1.add(btn);
		pan1.add(btn2);

		btn.addActionListener(new ActionListener() {//点击

			@Override
			public void actionPerformed(ActionEvent e) {
				if(proPath.getText().equals("") || proPath.getText().equals("请选择项目位置") ) {
					App.alert("请输入项目路径");
					return;
				}
				System.out.println(extra.getText());
				if(extra.getText().equals("") || extra.getText().equals("分级目录") ) {
					App.expath = "";
				}else {
					App.expath = "." + extra.getText();
				}
				App.path = proPath.getText()+ File.separator;
				if(table.equals("")) {
					App.alert("请选择表");
					return;
				}
				
				try {
					//保存连接信息
					Config.set("path", proPath.getText());
					Config.save();
					//获取页面上的设置 
					Vector<Vector> dataVector = datamodel.getDataVector();
					List<Field> fields = Maps.getFields(table);
					for (Field field : fields) {
						for(Vector v : dataVector) {
							if(field.getField_().equals(v.get(0))) {
								field.setComment((String) v.get(2));
								field.setIsnull((String) v.get(3));
								field.setFrontType((String) v.get(4));
								String search = "NO";
								if(v.get(5) != null && !"NO".equals(v.get(5))) {
									search = "YES";
								}
								field.setIssearch(search);
								
							}
						}
					}
					generate();
					
				} catch (IOException e1) {
					App.alert("发生错误");
					e1.printStackTrace();
				} catch (TemplateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					App.alert("发生错误");
				} 
				
				App.alert("生成完毕");
			}
			
		});

		btn2.addActionListener(new ActionListener() {//点击

			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> dataVector = datamodel.getDataVector();
				System.out.println(MyUtil.getPath());
			}
		});

		return pan1;
	}
	
	


	/**
	 * 左边table
	 * @return
	 */
	private Component addEast() {
		JPanel pan1 = new JPanel();
//		pan1.setPreferredSize(new Dimension(200, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200,200));
		//查询数据表
		if(App.driver.equals("com.mysql.jdbc.Driver")) {
			tableDao.loadTables();
		}

		String[] tables =Maps.getTables().toArray(new String[Maps.getTables().size()]);
		JList list = new JList(tables);

		list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!list.getValueIsAdjusting()){	//设置只有释放鼠标时才触发
					System.out.println(list.getSelectedValue());
					table = (String) list.getSelectedValue();
					//查询表列
					showFields();
				}
			}
		});

		//		scrollPane.setViewportView(list);
		//		scrollPane.add(list);

		pan1.add(list);
		return pan1;
	}

	/**
	 * 查询表列
	 */
	private void showFields() {
		fieldList = tableDao.loadFields(table);



		//		 datamodel
		String[] columnNames = new String[] { "列名", "类型", "注释","是否null", "前端类型" ,"是否查询条件"};
		Object[][] data = new Object[fieldList.size()][6];
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			data[i][0] = field.getField_();
			data[i][1] = field.getTypeO();
			data[i][2] = field.getComment();
			data[i][3] = field.getNvll();
			data[i][4] = field.getTypeFront();

		}

		datamodel.setDataVector(data, columnNames);
		//第三列变成下拉菜单
		TableColumn sportColumn1 = t .getColumnModel().getColumn(3);
		sportColumn1.setCellEditor(new DefaultCellEditor(nullBox));

		TableColumn sportColumn = t .getColumnModel().getColumn(4);
		sportColumn.setCellEditor(new DefaultCellEditor(typeBox));
		
		TableColumn sportColumn2 = t .getColumnModel().getColumn(5);
		sportColumn2.setCellEditor(new DefaultCellEditor(isSearch));


		//		String[] strs = new String[fieldList.size()];
		//
		//		for (int i = 0; i < fieldList.size(); i++) {
		//			strs[i] = fieldList.get(i).getField_();
		//		}

		//		fieldNameList.setListData(strs);

	}
}
