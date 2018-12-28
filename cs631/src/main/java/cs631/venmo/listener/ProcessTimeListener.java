package cs631.venmo.listener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cs631.venmo.pojo.SendRecord;
import cs631.venmo.service.SendRecordService;

public class ProcessTimeListener implements ServletContextListener {

	private final int TEST_TIME = 30 * 1000;// 30秒
	private final int PROCESS_TIME = 30 * 1000;// 十分钟
	private final int PROCESS_TIME_USER = 15 * 24 * 60 * 60 * 1000;// 15天
	private final int LOAD_LIST_TIME = 30 * 1000;// 1分钟

	private SendRecordService sendRecordService;

	private static List<SendRecord> sendRecords;

	// 程序运行即启动监听
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		this.sendRecordService = (SendRecordService) applicationContext.getBean("sendRecordService");
		sendRecords = sendRecordService.getAllUnProcessSendRecords();
		Timer timer = new Timer();
		MyTimerTask myTimerTask = new MyTimerTask();
		timer.schedule(myTimerTask, LOAD_LIST_TIME, LOAD_LIST_TIME);// 1分钟间隔执行任务
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("扫描");
			sendRecords = sendRecordService.unProcessSendRecordsAndUpdate();
			for (SendRecord sendRecord : sendRecords) {
				if (sendRecord.getIsExit() == 1 
						&& System.currentTimeMillis() - sendRecord.getTime().getTime() >= PROCESS_TIME) {
					// 用户存在，且汇款时间超过10分钟
					sendRecordService.processSend(sendRecord);
				} else if (sendRecord.getIsExit() == 0 
						&& System.currentTimeMillis() - sendRecord.getTime().getTime() >= PROCESS_TIME_USER) {
					// 用户不存在，且汇款时间超过15天
					sendRecordService.cancelSend(sendRecord);
				}
			}
		}
	}
}
