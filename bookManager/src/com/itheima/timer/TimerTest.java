package com.itheima.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
			}
		}, 2000, 1000);

	}

}
