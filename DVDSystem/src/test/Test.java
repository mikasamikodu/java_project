package test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.*;

public class Test{

	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                }
	                new UserLogin();
	            }
	        });
	}

}
