/**
 * 
 */
package io.clab.mpf.shop.common.redis.template;

import io.clab.mpf.shop.common.redis.RedisEntityInfo;
import io.clab.mpf.shop.common.redis.RedisRepositoryException;
import io.clab.mpf.shop.common.redis.annotation.RedisRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;



/**
 * redis模板操作类
 * @author iceray
 *
 */
public abstract class RedisRepositoryHashTemplate<T extends RedisEntityInfo<ID>,ID extends Serializable> implements OperationCrudRepository<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(RedisRepositoryTemplate.class);
	
	private String store_key;
	
	@Autowired
	private RedisTemplate<ID,T> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String,ID,T> hashOperations;
	
	public RedisRepositoryHashTemplate()
	{
		RedisRepository redisRepository = this.getClass().getAnnotation(RedisRepository.class);
		if(redisRepository == null)
		{
			logger.error("can not find annotation @RedisRepository");
			throw new RedisRepositoryException("没有找到@RedisRepository的注解");
		}
		
		store_key = StringUtils.isEmpty(redisRepository.storeKey()) ? this.getClass().getSimpleName() : redisRepository.storeKey();
	}
	
	@Override
	public <S extends T> S save(S entity) {
		
		if(isNotValidate(entity))
			return entity;
		
		hashOperations.put(store_key, entity.getBeanID(), entity);
		return entity;
	}
	
	

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		
		if(entities == null)
			return null;
		
		Map<ID,S> entityMap = new HashMap<>();
		Iterator<S> entityIter = entities.iterator();
		while(entityIter.hasNext())
		{
			S entity = entityIter.next();
			if(isNotValidate(entity))
				continue;
			entityMap.put(entity.getBeanID(), entity);
		}
		
		if(entityMap.isEmpty())
			return null;
		hashOperations.putAll(store_key, entityMap);
		return entities;
	}

	@Override
	public T findOne(ID id) {
		
		if(isNotValidate(id))
			return null;
		
		return hashOperations.get(store_key, id);
		
	}

	@Override
	public boolean exists(ID id) {
		if(isNotValidate(id))
			return false;
		
		return hashOperations.hasKey(store_key, id);
	}

	@Override
	public Iterable<T> findAll() {
		
		return hashOperations.values(store_key).stream().filter(obj -> obj != null).collect(Collectors.toList());
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		
		if(isNotValidate())
			return null;
		
		if(ids instanceof Collection)
		    return hashOperations.multiGet(store_key, (Collection<ID>)ids).stream()
		    		              .filter(obj -> obj != null).collect(Collectors.toList());
		else
		{
			List<ID> idList = new ArrayList<ID>();
			Iterator<ID> idIter = ids.iterator();
			if(idIter.hasNext())
				idList.add(idIter.next());
			
			return hashOperations.multiGet(store_key, idList).stream()
					             .filter(obj -> obj != null).collect(Collectors.toList());
		}
	}

	@Override
	public long count() {
		
		if(isNotValidate())
			return 0;
		
		return hashOperations.size(store_key);
	}

	@Override
	public void delete(ID id) {
		
		if(isNotValidate(id))
			return;
		
		hashOperations.delete(store_key, id);
		
	}

	@Override
	public void delete(T entity) {
		
		if(isNotValidate(entity))
			return;
		
		hashOperations.delete(store_key, entity.getBeanID());
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		
		if(isNotValidate())
			return;
		
		List<ID> ids = new ArrayList<ID>();
		
		Iterator<? extends T> entityIter = entities.iterator();
		if(entityIter.hasNext())
		{
			ids.add(entityIter.next().getBeanID());
		}
		
		hashOperations.delete(store_key, ids.toArray());
		
	}

	@Override
	public void deleteAll() {
		
		if(isNotValidate())
			return;
		
		hashOperations.getOperations().delete(store_key);
	}

	public RedisTemplate<ID, T> getRedisTemplate() {
		return redisTemplate;
	}

	private <S extends T> boolean isNotValidate(S entity) {
		
		if(StringUtils.isEmpty(store_key)
				|| entity == null
				    || entity.getBeanID() == null)
		{
			logger.error("store key or id not  find in redis repository,please set it");
			return true;
		}
		return false;
	}
	
	private boolean isNotValidate(ID id)
	{
		if(StringUtils.isEmpty(store_key)
				|| id == null)
		{
			logger.error("store key or id not  find in redis repository,please set it");
			return true;
		}
		return false;
	}
	
	private boolean isNotValidate()
	{
		if(StringUtils.isEmpty(store_key))
		{
			logger.error("store key not  find in redis repository,please set it");
			return true;
		}
		return false;
	}
	
	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities, long timeout, TimeUnit unit) {
		
		if(this.save(entities) != null)
			hashOperations.getOperations().expire(store_key, timeout, unit);
		
		return entities;
	}
	
	@Override
	public <S extends T> S save(S entity, long timeout, TimeUnit unit) {
		/*redisTemplate.execute(new SessionCallback<S>(){

			@Override
			public <String, ID> S execute(RedisOperations<String, ID> operations) throws DataAccessException {
				operations.multi();
				operations.opsForHash().put((String) store_key, entity.getBeanID(), entity);
				operations.opsForHash().getOperations().expire((String) store_key, timeout, unit);
				return entity;
			}});*/
		if(this.save(entity) != null)
			hashOperations.getOperations().expire(store_key, timeout, unit); //对整个MAPkey设置过期，非对单独键设置过期
		
		return entity;
	}

}
