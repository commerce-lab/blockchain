/**
 * 
 */
package io.clab.mpf.shop.common.redis;

import java.io.Serializable;

/**
 * 实体信息类
 * @author iceray
 *
 */

public interface RedisEntityInfo<ID extends Serializable> extends Serializable {

	ID getBeanID();
}
