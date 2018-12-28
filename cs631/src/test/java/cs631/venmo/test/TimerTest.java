package cs631.venmo.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		MyTimerTask myTimerTask = new MyTimerTask();
		timer.schedule(myTimerTask, 1000l, 1000l);// 5秒间隔执行任务
	}

}

class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("执行任务");
	}

}