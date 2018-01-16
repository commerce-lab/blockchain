package io.clab.mpf.shop.admin.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import io.clab.mpf.shop.admin.modules.api.entity.TokenEntity;
import io.clab.mpf.shop.admin.modules.sys.dao.BaseDao;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
