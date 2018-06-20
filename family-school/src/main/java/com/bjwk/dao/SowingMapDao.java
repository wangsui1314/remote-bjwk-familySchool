package com.bjwk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SowingMapDao {

    int insertSowingMap(@Param("imageUrl") String imageUrl,@Param("type") Integer type, @Param("isEnable")Integer isEnable);

    List<String> querySowingMapList(@Param("type")Integer type);

    List<HashMap<String,Object>> querySowingMapListToBackstage(@Param("type")Integer type,@Param("isEnable")Integer isEnable);

    int updatEnableOper(@Param("id")Integer id, @Param("isEnable")Integer isEnable);
}
