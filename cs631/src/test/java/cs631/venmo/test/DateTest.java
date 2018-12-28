package cs631.venmo.test;


import java.sql.Timestamp;
import java.util.Date;


public class DateTest {

	private final static int PROCESS_TIME = 10 * 60 * 1000;//十分钟
	private final int PROCESS_TIME_USER = 15 * 24 * 60 * 60 * 1000;//15天
	
	public static void main(String[] args) {
		Date date = new Date();       
		Timestamp timeStamep = new Timestamp(PROCESS_TIME);
		System.out.println(timeStamep.getTime());
		Integer sum1 = 1;
		Integer sum2  = null;
		System.out.println(sum1+sum2);
	}
}
