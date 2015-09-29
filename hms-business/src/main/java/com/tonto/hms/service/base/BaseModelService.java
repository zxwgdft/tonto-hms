package com.tonto.hms.service.base;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.base.BaseDao;

public abstract class BaseModelService<Key extends Serializable,Model>{
	
	protected abstract BaseDao<Key,Model> getDao();
	
	/**
	 * 通过主键获取数据
	 * @param key
	 * @return
	 */
	@Transactional
	public Model get(Key key) {
		return getDao().selectByPrimaryKey(key);
	}
	
	/**
	 * 保存数据
	 * @param model
	 */
	public void save(Model model)
	{
		getDao().insert(model);
	}
	
	/**
	 * 更新数据
	 * @param model
	 */
	public void update(Model model) {
		getDao().updateByPrimaryKey(model);
	}
	
	/**
	 * 更具主键删除
	 * @param key
	 */
	public void delete(Key key)
	{
		getDao().deleteByPrimaryKey(key);
	}
}
