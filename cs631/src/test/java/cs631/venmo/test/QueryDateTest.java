package cs631.venmo.test;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cs631.venmo.dao.SendRecordDao;
import cs631.venmo.dao.SendRecordDaoImpl;
import cs631.venmo.pojo.SendRecord;

public class QueryDateTest {

	private long testTime = 1000 * 60 * 60 * 24 * 7;
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
//		System.out.println(session.load(SendRecord.class, 1));
//		String hql = "select sr from SendRecord sr where sr.time < :endTime";
		String hql = "select sr from SendRecord sr where sr.time between :startTime and :endTime";
		Query query = session.createQuery(hql);		
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", new Timestamp(new Date().getTime()-testTime));
		map.put("endTime", new Timestamp(new Date().getTime()));
		setAliasParameter(query, map);
//		query.setParameter("endTime", new Timestamp(new Date().getTime()-testTime));
		System.out.println(query.list().size());
		
	}
	
	@SuppressWarnings("unused")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					query.setParameterList(key, (Collection)val);
				}else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
}
