/**
 * 
 */
package io.clab.mpf.shop.common.redis.template;

import io.clab.mpf.shop.common.redis.RedisEntityInfo;
import io.clab.mpf.shop.common.redis.RedisRepositoryException;
import io.clab.mpf.shop.common.redis.annotation.RedisRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

/**
 * redis操作类模板(键值对)
 * @author iceray
 *
 */
public abstract class RedisRepositoryTemplate<T extends RedisEntityInfo<ID>,ID extends Serializable> implements OperationCrudRepository<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(RedisRepositoryTemplate.class);
	
	private String store_key;
	
	@Autowired
	private RedisTemplate<ID,T> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private ValueOperations<String, T> valueOps;
	
	public RedisRepositoryTemplate()
	{
		RedisRepository redisRepository = this.getClass().getAnnotation(RedisRepository.class);
		if(redisRepository == null)
		{
			logger.error("can not find annotation @RedisRepository");
			throw new RedisRepositoryException("没有找到@RedisRepository的注解");
		}
		
		store_key = StringUtils.isEmpty(redisRepository.storeKey()) ? this.getClass().getSimpleName() : redisRepository.storeKey()+"_";
	}
	
	@Override
	public <S extends T> S save(S entity) {
		
		if(isNotValidate(entity))
			return entity;
		
		valueOps.set(store_key+entity.getBeanID(), entity);
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		
		if(entities == null)
			return entities;
		
		Map<String,S> entityMap = new HashMap<>();
		Iterator<S> entityIter = entities.iterator();
		while(entityIter.hasNext())
		{
			S entity = entityIter.next();
			if(isNotValidate(entity))
				continue;
			entityMap.put(store_key+entity.getBeanID(), entity);
		}
		
		if(entityMap.isEmpty())
			return entities;
		
		valueOps.multiSet(entityMap);
		
		return entities;
	}

	@Override
	public T findOne(ID id) {
		
		if(isNotValidate(id))
			return null;
		
		return valueOps.get(store_key+id);
	}

	@Override
	public boolean exists(ID id) {
		
		return this.findOne(id) != null;
		
	}

	@Override
	public Iterable<T> findAll() {
		
		logger.info("value template not support find all yet");
		
		return null;
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		
		List<String> idList = transferId(ids);
		
		return valueOps.multiGet(idList).stream().filter(obj -> obj != null).collect(Collectors.toList());
	}

	private List<String> transferId(Iterable<ID> ids) {
		List<String> idList = new ArrayList<String>();
		Iterator<ID> idIter = ids.iterator();
		while(idIter.hasNext())
		{
			idList.add(store_key + idIter.next());
		}
		return idList;
	}

	@Override
	public long count() {
		return valueOps.getOperations().keys(store_key+"*").size();
	}

	@Override
	public void delete(ID id) {
		
		if(isNotValidate(id))
			return;
		
		valueOps.getOperations().delete(store_key + id);
	}

	@Override
	public void delete(T entity) {
		
		if(isNotValidate(entity))
			return;
		
		valueOps.getOperations().delete(store_key + entity.getBeanID());
		
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		
		List<String> idList = new ArrayList<String>();
		Iterator<? extends T> idIter = entities.iterator();
		while(idIter.hasNext())
		{
			idList.add(store_key + idIter.next().getBeanID());
		}
		
		
		valueOps.getOperations().delete(idList);
		
	}

	@Override
	public void deleteAll() {
		
		logger.info("not supprot delete all operatio yet");
		
	}
	
	public RedisTemplate<ID, T> getRedisTemplate() {
		return redisTemplate;
	}

	@Override
	public <S extends T> S save(S entity, long timeout, TimeUnit unit) {
		
		/*redisTemplate.execute(new SessionCallback<S>(){

			@Override
			public S execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				operations.opsForValue().set(store_key+entity.getBeanID(), entity);
				operations.opsForValue().getOperations().expire(store_key+entity.getBeanID(), timeout, unit);
				operations.exec();
				return entity;
			}});*/
		
		if(this.save(entity) == null)
			return null;
		
		this.valueOps.getOperations().expire(store_key+entity.getBeanID(), timeout, unit);
		
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities, long timeout, TimeUnit unit) {
		
		if(this.save(entities) == null)
			return null;
		
		Iterator<S> entityIter = entities.iterator();
		while(entityIter.hasNext())
		{
			this.valueOps.getOperations().expire(store_key+entityIter.next().getBeanID(), timeout, unit);
		}
		
		return entities;
	}
	
    private <S extends T> boolean isNotValidate(S entity) {
		
		if(entity == null
				    || entity.getBeanID() == null)
		{
			logger.error("id not  find in redis repository,please set it");
			return true;
		}
		return false;
	}
	
	private boolean isNotValidate(ID id)
	{
		if(id == null)
		{
			logger.error("id not  find in redis repository,please set it");
			return true;
		}
		return false;
	}

}
