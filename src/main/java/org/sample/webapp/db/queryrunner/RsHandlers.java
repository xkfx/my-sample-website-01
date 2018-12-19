package org.sample.webapp.db.queryrunner;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.sample.webapp.entity.Profile;

/**
 * 业务相关，保存各式各样的ResultSetHandler常量
 */
public interface RsHandlers {

    BeanListHandler<Profile> PROFILE_LIST = new BeanListHandler<>(Profile.class);

    BeanHandler<Profile> PROFILE = new BeanHandler<>(Profile.class);
}
