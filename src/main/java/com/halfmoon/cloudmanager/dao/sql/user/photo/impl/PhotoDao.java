package com.halfmoon.cloudmanager.dao.sql.user.photo.impl;

import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.user.photo.IPhotoDao;
import com.halfmoon.cloudmanager.model.user.photo.Photo;

@Repository
public class PhotoDao extends BaseDao<Photo> implements IPhotoDao {

}
