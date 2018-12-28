package cs631.venmo.service;

import org.springframework.beans.factory.annotation.Autowired;

import cs631.venmo.dao.BaseDao;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected BaseDao<T> baseDao;
	
	@Override
	public T add(T t) {
		return baseDao.add(t);
	}

	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T load(int id) {
		return baseDao.load(id);
	}

}
