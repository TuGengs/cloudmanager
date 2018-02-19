package com.halfmoon.cloudmanager.dao.sql.user.icon.impl;

import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.user.icon.IIconDao;
import com.halfmoon.cloudmanager.model.user.icon.Icon;

@Repository
public class IconDao extends BaseDao<Icon> implements IIconDao {

}
