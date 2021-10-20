package me.zingon;

import freemarker.template.TemplateException;
import me.zingon.backup.util.Config;
import me.zingon.db.DBcon;
import me.zingon.db.TableDao;
import me.zingon.service.*;
import me.zingon.service.impl.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//更多请阅读：https://www.yiibai.com/javafx/javafx-tutorial-for-beginners.html



import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//更多请阅读：https://www.yiibai.com/javafx/javafx-tutorial-for-beginners.html



/**
 * Hello world!
 */
public class App  {
	
	public static String types[] = Config.get("dbtype").split(",");
	public static String dbdriver[] = Config.get("dbdriver").split(",");
	
	//项目路径
	public static String path = "";
	//拓展路劲
	public static String expath = "";
	
	public static String type= "";
	public static String driver= "";
	public static String url="";
	public static String username="";
	public static String pwd="";
	
	
	
	private static JTextField jdriver =  new JTextField(dbdriver[1]);   
	private static JTextField jurl  = new JTextField(Config.get("db.url"));  
	private static JTextField jusername =  new JTextField(Config.get("db.username"));    
	private static JTextField jpwd = new JTextField(Config.get("db.password"));     
	
	
	public static JFrame frame = null;
	
	
	static {
		jdriver.setEditable(false);
		driver = jdriver.getText();
	}
	
	
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");


	public static void main(String[] args) throws IOException, TemplateException, ParseException, InterruptedException {
		Logger logger= Logger.getLogger("");
//		TableDao td=new TableDao();
//		td.loadTables();
//		logger.info("数据库加载完成"); 
//		ModelService ms=new ModelServiceImpl();
//		DaoService ds=new DaoServiceImpl();
//		ServiceService ss=new ServiceServiceImpl();
//		ControllerService cs=new ControllerServiceImpl();
//		FrontService fs=new FrontServiceImpl();
//		/*
//		 * // List<String> tables = Maps.getTables(); // for (String table : tables) {
//		 * // ms.createModel(table); // ds.createDao(table); // ds.createMapper(table);
//		 * // ss.createService(table); // ss.createServiceImpl(table); //
//		 * cs.createController(table); // } // // 根据数据库生成所有 model
//		 */		        ms.createModels();
//		        ds.createDaos();
//		        ss.createServices();
//		        ss.createServiceImpls();
//		        cs.createControllers();
//		        fs.createJss();
//		        fs.createJsps();
//		String table = "messages";
//		ms.createModel(table);
//		ds.createDao(table);
//		ds.createMapper(table);
//		ss.createService(table);
//		ss.createServiceImpl(table);
//		cs.createController(table); 
		setLayout();
	}
	
	
	//打开fx界面
	public  static void start() {
		
	}
	
	
	public static void setLayout() {
	    frame =  new JFrame();
		frame.setTitle("生成器");
		frame.setBounds(0, 0, 500, 500);
		frame.setLocationRelativeTo(null);//居中
		frame.setLayout(new BorderLayout());
		
		
		frame.add(addNorth() ,BorderLayout.NORTH);
		frame.add(addCenter() ,BorderLayout.CENTER);
		frame.add(addSouth(),BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private static Component addSouth() {
		JPanel pan1 = new JPanel();
		pan1.setLayout(new FlowLayout());
		JButton conn = new JButton("连接");
		conn.addActionListener(new ActionListener() {//点击
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jurl.getText().equals("") || jurl.getText().equals("连接字符串")) {
					alert("请输入连接字符串");
					return;
				}
				if(jusername.getText().equals("") || jusername.getText().equals("用户名")) {
					alert( "请输入用户名");
					return;
				}
				if(jpwd.getText().equals("") || jpwd.getText().equals("密码")) {
					alert( "请输入密码");
					return;
				}
				//尝试连接
				Connection conn =  null;
				try {
					driver = jdriver .getText();
					url = jurl .getText();
					username =  jusername  .getText();
					pwd = jpwd .getText();
					conn  = DBcon.getCon(driver,  url, username, pwd);
					if(conn == null  && !conn.isClosed()) {
						alert( "连接失败");
					}else if(conn != null) {
						
						frame.setVisible(false);
						MenuFrame menu = new MenuFrame("生成器数据界面");
						menu.setVisible(true);
						//保存连接信息
						Config.set("db.url", url);
						Config.set("db.username", username);
						Config.set("db.password", pwd);
						Config.save();
					}
				} catch (Exception e1) {
					String msg = "";
					e1.printStackTrace();
					if(e1 instanceof ClassNotFoundException) {
						msg = "驱动错误或没有找到驱动";
					}
					alert( "连接失败:" +msg + e1.getMessage() );
				}
			}
		});
		pan1.add(conn);
		
		return pan1;
	}


	private static Component addCenter() {
		JPanel pan1 = new JPanel();
		 //指定面板的布局为GridLayout，1行4列，间隙为5
		pan1.setLayout(new GridLayout(5,1,5,50));
		pan1.add(jdriver);
		pan1.add(jurl);
		pan1.add(jusername);
		pan1.add(jpwd);
		return pan1;
	}


	private static JPanel addNorth() {
		JPanel pan1 = new JPanel();
		
		pan1.setLayout(new FlowLayout());
		pan1.add(new JLabel("请选择数据库类型"));
		//添加下拉菜单
				JComboBox<String> box = new JComboBox<String>();
				box.setEditable(true);
				for(String s : types) {
					box.addItem(s);
				}
				box.addItemListener(new ItemListener() {
					
					//选中事件
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == e.SELECTED){
						
							for(int i = 0 ; i < dbdriver.length; i++) {
								if(types[i].equals(e.getItem().toString())) {
									driver = dbdriver[i];
									
									jdriver.setText(driver);
								}
							}
						  }
					}
					
				});
		pan1.add(box);
		return pan1;
	}
	
	public static void alert(String msg) {
		JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.ERROR_MESSAGE); 
	}
	
	
}
