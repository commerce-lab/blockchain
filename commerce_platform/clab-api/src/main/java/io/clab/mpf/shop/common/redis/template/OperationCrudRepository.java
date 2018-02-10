/**
 * 
 */
package io.clab.mpf.shop.common.redis.template;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author iceray
 *
 */
@NoRepositoryBean
public interface OperationCrudRepository<T,ID extends Serializable> extends CrudRepository<T, ID> {

	<S extends T> S save(S entity,long timeout, TimeUnit unit);
	
	<S extends T> Iterable<S> save(Iterable<S> entities,long timeout, TimeUnit unit);
}
