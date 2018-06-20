package com.bjwk.service.admin;

import com.bjwk.utils.DataWrapper;

import java.util.HashMap;
import java.util.List;

/**
 * @program: family-school
 * @description:
 * @author: liqitian.
 * @create: 2018-06-20 16:27
 **/

public interface SowingMapService {

    DataWrapper<Void> insertSowingMap(String imageUrl,Integer type,Integer isEnable);

    DataWrapper<List<String>> querySowingMapList(Integer type);

    DataWrapper<List<HashMap<String,Object>>>  querySowingMapListToBackstage(Integer type,Integer isEnable);

    DataWrapper<Void> updatEnableOper(Integer id, Integer isEnable);
}
