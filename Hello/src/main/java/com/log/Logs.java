package com.log;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.*;

public class Logs {

		protected Logger log = null; //日志类
		protected static FileHandler FileHandler = null; //实例化的FileHandler对象
		protected String logDir = "C:/send/"; //日志文件存放路径
		protected String date_format = "yyyy-MM-dd HH:mm:ss"; //记录文件的日期格式
		protected String filenameformat = "yyyy-MM-dd"; //记录文件的日期格式
		protected String[] LevelArr = {"OFF", "ERROR", "DEBUG", "INFO", "ALL"}; //允许的等级参数列表
		protected String driver = "com.mysql.jdbc.Driver"; // 驱动程序名
		protected String url = "jdbc:mysql://localhost:3306/day13?characterEncoding=utf-8"; // URL指向要访问的数据库名
		protected String mysqlUser = "username"; // MySQL的用户名
		protected String password = "password"; // MySQL的密码
		protected Connection conn = null; //连接指针
		protected Statement statement = null; //statement用来执行SQL语句
		protected String logLevel = null;
		
		public static void main(String[] args) {

		}

		protected void getLogger() {
		try {
		String className = "Logs";
		log = Logger.getLogger(className); //获取调用日志的类
		log.setLevel(Level.ALL); //设置日志输出的等级值
		//判断文件夹是否存在，不存在则创建
		File Dir = new File(logDir);
		checkDir(Dir);
		//以当前日期为文件名创建文件
		String logFile = logDir + getDateTime(filenameformat) + ".txt";
		if (FileHandler == null) {
			
		FileHandler = new FileHandler(logFile, true); //以追加方式写文件
		FileHandler.setLevel(Level.ALL); //设置记录级别
		FileHandler.setFormatter(new MyLogHander()); //以自定义格式记录日志
		log.addHandler(FileHandler); //增加处理的文件处理对象
		}

		} catch (IOException ex) {
		ex.printStackTrace();
		}
		}

		public Logs() { //构造函数
		try {
		loadMysql(); //加载Mysql驱动程序
		getLogger(); //以单例的方式获取Log对象
		} catch (Exception ex) {
		ex.printStackTrace();
		}
		}

		public Logs(String MyLogLevel) { //构造函数
		try {
		loadMysql(); //加载Mysql驱动程序
		MyLogLevel = MyLogLevel.toUpperCase(); //传入值转大写
		
		boolean flag = false;
		for (int i = 0; i < LevelArr.length; i++) { //判断传入的等级值是否符合标准
		if (LevelArr[i].equals(MyLogLevel)) {
		flag = true;
		}
		}
		if (!flag) { //如果不符合标准，则不记录日志，将logLevel设置为OFF
		logLevel = "OFF";
		} else { //符合标准则以传入的等级值作为标准
		logLevel = MyLogLevel;
		}
		getLogger(); //以单例的方式获取Log对象
		} catch (Exception ex) {
		ex.printStackTrace();
		}
		}

		/**
		* 记录日志信息
		*
		* @param levelVal 级别
		* @param msg 内容
		*/
		public void LogMsg(String levelVal, String msg) {
		try {
		levelVal = levelVal.toUpperCase(); //等级值转大写
		//<start-根据等级值判断是否记录此条日志
		boolean flag = false;
		for (int i = 0; i < LevelArr.length; i++) { //判断传入的等级值是否符合标准
		if (LevelArr[i].equals(levelVal)) {
		flag = true;
		}
		}
		if (!flag) { //如果传入的时非法的等级值，则不记录
		return;
		} else {
		int iLevelVal = levelToNum(levelVal);
		int ilogLevel = levelToNum(logLevel);
		if (iLevelVal > ilogLevel) {
		return;
		}
		}
		//根据等级值判断是否记录此条日志 --end>
		String className = Thread.currentThread().getStackTrace()[2].getClassName(); //获取调用该方法的类名
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName(); //获取调用该方法的方法名
		int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber(); //获取调用该方法所在的行数
		String[] Content = {levelVal, msg, className, methodName, String.valueOf(lineNumber), this.getDateTime(date_format)}; //包含重要日志信息的数组
		String joinContent = ""; //将字符串数组转为字符串
		for (int i = 0; i < Content.length; i++) {
		joinContent += Content[i] + "&&";
		}
		joinContent = joinContent.substring(0, joinContent.length() - 2); //去除结尾的&&
		log.info(joinContent); //调用jdk自带日志类记录

		//开启了数据库记录
		openConn(); //打开连接
		statement = conn.createStatement(); //创建statement
		String name = ManagementFactory.getRuntimeMXBean().getName(); //获取进程信息
		String pid = name.split("@")[0]; //进程号
		// 要执行的SQL语句
		String sql = "insert into hyk_javalogs(level,date,pid,threadId,className,methodName,line,content,args) "
		+ "values ('" + levelVal + "','" + this.getDateTime(date_format) + "','" + pid + "','" + Thread.currentThread().getId() + "'"
		+ ",'" + className + "','" + methodName + "','" + lineNumber + "','" + msg + "','');";
		statement.execute(sql); //执行插入语句
		closeConn(); //关闭连接

		} catch (Exception ex) {
		ex.printStackTrace();
		}
		}

		/**
		* 记录日志信息
		*
		* @param levelVal 级别
		* @param msg 内容
		* @param Args 参数列表
		*/
		public void LogMsg(String levelVal, String msg, ArrayList<String> Args) {
		try {
		levelVal = levelVal.toUpperCase(); //等级值转大写
		//<start-根据等级值判断是否记录此条日志
		boolean flag = false;
		for (int i = 0; i < LevelArr.length; i++) { //判断传入的等级值是否符合标准
		if (LevelArr[i].equals(levelVal)) {
		flag = true;
		}
		}
		if (!flag) { //如果传入的时非法的等级值，则不记录
		return;
		} else {
		int iLevelVal = levelToNum(levelVal);
		int ilogLevel = levelToNum(logLevel);
		if (iLevelVal > ilogLevel) {
		return;
		}
		}
		//根据等级值判断是否记录此条日志 --end>
		String className = Thread.currentThread().getStackTrace()[2].getClassName(); //获取调用该方法的类名
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName(); //获取调用该方法的方法名
		int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber(); //获取调用该方法所在的行数
		String[] Content = {levelVal, msg, className, methodName, String.valueOf(lineNumber), this.getDateTime(date_format)}; //包含重要日志信息的数组
		String joinContent = ""; //将字符串数组转为字符串
		for (int i = 0; i < Content.length; i++) { //拼接级别、内容、调用类、调用方法、行数
		joinContent += Content[i] + "&&";
		}
		String argsVal = ""; //存入Mysql的参数的值
		for (int j = 0; j < Args.size(); j++) { //拼接参数
		String arg = "";
		arg = String.valueOf(Args.get(j));
		joinContent += arg + "&&";
		argsVal += "参数" + (j + 1) + ":" + arg + " | ";
		}
		joinContent = joinContent.substring(0, joinContent.length() - 2); //去除结尾的&&
		argsVal = argsVal.substring(0, argsVal.length() - 3); //去除结尾的&&
		log.info(joinContent); //调用jdk自带日志类记录

		//开启了数据库记录
		openConn(); //打开连接
		statement = conn.createStatement(); //创建statement
		String name = ManagementFactory.getRuntimeMXBean().getName(); //获取进程信息
		String pid = name.split("@")[0]; //进程号
		// 要执行的SQL语句
		String sql = "insert into hyk_javalogs(level,date,pid,threadId,className,methodName,line,content,args) "
		+ "values ('" + levelVal + "','" + this.getDateTime(date_format) + "','" + pid + "','" + Thread.currentThread().getId() + "'"
		+ ",'" + className + "','" + methodName + "','" + lineNumber + "','" + msg + "','" + argsVal + "');";
		statement.execute(sql); //执行插入语句
		closeConn(); //关闭连接
		} catch (Exception ex) {
		ex.printStackTrace();
		}
		}

		protected void loadMysql() {
		try {
		// 加载驱动程序
		Class.forName(driver);
		} catch (ClassNotFoundException e) {
		System.out.println("Sorry,can`t find the Driver!");
		e.printStackTrace();
		}
		}

		protected void openConn() {
		try {
		conn = DriverManager.getConnection(url, mysqlUser, password);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}

		protected void closeConn() {
		try {
		conn.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

		/**
		* 检查文件夹不存在时则创建文件夹
		*
		* @param file
		*/
		public void checkDir(File file) {
		if (!file.isDirectory()) {
		file.mkdir();
		}
		}

		/**
		* 返回当前日期
		*
		* @return
		*/
		public String getDateTime(String dateFormat) {
		//返回当前日期的文件名
		Date date = new Date();
		SimpleDateFormat DateTime = new SimpleDateFormat(dateFormat);
		return DateTime.format(date);
		}

		/**
		* 将等级值转化为数字
		*
		* @param Level 等级值
		* @return
		*/
		protected int levelToNum(String Level) {
		switch (Level) {
		case "OFF":
		return 0;
		case "ERROR":
		return 1;
		case "DEBUG":
		return 2;
		case "INFO":
		return 3;
		case "ALL":
		return 4;
		}
		return 5;
		}

		}

/**
 * 日志文件记录的format格式
 *
 * @author caisicen
 */
class MyLogHander extends Formatter {

	@Override
	public String format(LogRecord record) {
		String name = ManagementFactory.getRuntimeMXBean().getName(); // 获取进程信息
		String pid = name.split("@")[0]; // 进程号
		String[] Content = record.getMessage().split("&&");
		String Format = "级别：" + Content[0] + " | 时间：" + Content[5] + " | 进程号：" + pid + " | 线程号："
				+ Thread.currentThread().getId() + " | 调用的类：" + Content[2] + " | 调用的方法：" + Content[3] + " | 调用日志所在行数："
				+ Content[4] + "\n 内容：" + Content[1];
		if (Content.length > 6) { // 如果数组元素大于6，说明有传递参数数组，需要将参数数组也进行拼接
			Format += "\n 参数：";
			for (int i = 6, j = 1; i < Content.length; i++, j++) {
				Format += "arg" + j + ":" + Content[i] + " | ";
			}
			Format = Format.substring(0, Format.length() - 3); // 去除结尾的 |
		}
		Format += "\n\n";
		return Format;
	}

}
