package io.clab.mpf.shop.admin.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import io.clab.mpf.shop.admin.modules.api.entity.UserEntity;
import io.clab.mpf.shop.admin.modules.sys.dao.BaseDao;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
