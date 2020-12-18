package com.atguigu.principle.inversion;

public class DependencyPass {

	public static void main(String[] args) {
		ChangHong changHong = new ChangHong();
		
//		setter方法依赖传递
		OpenAndClose openAndClose = new OpenAndClose();
		openAndClose.setterItv(changHong);
		openAndClose.open();
		
//		构造方法依赖传递
//		IOpenAndClose iOpenAndClose = new OpenAndClose(changHong);
//		iOpenAndClose.open();
		
//		接口依赖传递
//		IOpenAndClose iOpenAndClose = new OpenAndClose();
//		iOpenAndClose.open(changHong);

	}

}

/**
 * 方式一：接口传递依赖
 * @author miku
 *
 */
//interface IOpenAndClose{
//	void open(ITV itv);//接收接口
//}
//
//interface ITV{
//	void play();
//}
//class OpenAndClose implements IOpenAndClose{
//	public void open(ITV itv) {
//		itv.play();
//	}
//}
class ChangHong implements ITV{
	@Override
	public void play() {
		System.out.println("打开长虹电视");
	}
}

/**
 * 方式二：构造方法传递依赖
 * @author miku
 *
 */
//interface IOpenAndClose{
//	void open();//接收接口
//}
//
//interface ITV{
//	void play();
//}
//class OpenAndClose implements IOpenAndClose{
//	
//	private  ITV itv;
//	
//	public OpenAndClose(ITV itv) {//接收接口
//		this.itv = itv;
//	}
//	public void open() {
//		itv.play();
//	}
//}

/**
 * 方法三：setter方法接收依赖
 * @author miku
 *
 */
interface IOpenAndClose{
	void open();//接收接口
}

interface ITV{
	void play();
}
class OpenAndClose implements IOpenAndClose{
	
	private  ITV itv;
	
	public void setterItv(ITV itv) {
		this.itv = itv;
	}
	
	public void open() {
		itv.play();
	}
}