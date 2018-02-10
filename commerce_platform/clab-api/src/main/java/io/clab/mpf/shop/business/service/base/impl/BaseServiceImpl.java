package io.clab.mpf.shop.business.service.base.impl;

import java.util.List;

import org.apache.log4j.Logger;

import util.PageResult;

import com.github.pagehelper.PageHelper;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.service.base.IBaseService;


/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 *
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    private static Logger logger = Logger.getLogger(BaseServiceImpl.class);

    protected abstract IBusBaseMapper<T> getMapper();

    @Override
    public int insertEntity(T entity) throws Exception {
        	int  result = getMapper().insertEntity(entity);
        	return result;
    }
    /**
     * 插入数据(选择性地)
     * 
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int insertSelectiveEntity(T entity) throws Exception{
        int    result = getMapper().insertSelectiveEntity(entity);
        return result;
    }
    @Override
    public int updateByPrimaryKey(T entity) throws Exception {
        int  result = getMapper().updateByPrimaryKey(entity);
        return result;
    }
    /**
     * 更新数据(选择性地)
     * 
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int updateByPrimaryKeySelective(T entity) throws Exception{
        int  result = getMapper().updateByPrimaryKeySelective(entity);
        return result;
    }
    @Override
    public int deleteByPrimaryKey(int id) throws Exception {
          int  result = getMapper().deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public int delete(T entity) throws Exception {
         int   result = getMapper().delete(entity);
        return result;
    }

    @Override
    public T selectByPrimaryKey(int id) {
        T obj = null;
        try {
            obj = getMapper().selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return obj;
    }

    @Override
    public T getOne(T entity) {
        T obj = null;
        try {
            obj = getMapper().getOne(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return obj;
    }

    @Override
    public Object getObject(Object obj) {
        Object result = null;
        try {
            result = getMapper().getObject(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
    @Override
    public List<T> getPage(T obj){
        List<T> list = null;
        try {
            list = getMapper().getPage(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    @Override
	public PageResult<T> queryByPage(PageResult<T> t,T entity) {
    	int pageNo=t.getPageNo();
    	int pageSize=t.getPageSize();
		pageNo = pageNo == 0?1:pageNo;
		pageSize = pageSize == 0?10:pageSize;
		PageHelper.startPage(pageNo,pageSize); 
		return PageResult.toPageResult(getPage(entity),t);
	}
    
    @Override
    public int getCount(T entity) {
        int result = 0;
        try {
            result = getMapper().getCount(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
    public List<T> getAll(){
    	 List<T> list = null;
         try {
             list = getMapper().getAll();
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
         }
         return list;
    }
    public List<T> getAllBySelect(T entity){
    	List<T> list = null;
        try {
            list = getMapper().getAllBySelect(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
    public List<T> getPageFront(T obj){
        List<T> list = null;
        try {
            list = getMapper().getPageFront(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
	public PageResult<T> queryByPageFront(PageResult<T> t,T entity) {
    	int pageNo=t.getPageNo();
    	int pageSize=t.getPageSize();
		pageNo = pageNo == 0?1:pageNo;
		pageSize = pageSize == 0?10:pageSize;
		PageHelper.startPage(pageNo,pageSize); 
		return PageResult.toPageResult(getPageFront(entity),t);
	}
    
}
