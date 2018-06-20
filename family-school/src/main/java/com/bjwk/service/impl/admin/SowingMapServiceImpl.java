package com.bjwk.service.impl.admin;

import com.bjwk.dao.SowingMapDao;
import com.bjwk.service.admin.SowingMapService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: family-school
 * @description: 后台图片资源管理实现类
 * @author: liqitian.
 * @create: 2018-06-20 16:28
 **/

@Service
public class SowingMapServiceImpl implements SowingMapService {

    @Autowired
    private SowingMapDao sowingMapDao;

    /**
     * @Description: 添加轮播图
     * @Param: [imageUrl, type, isEnable] 图片路径；轮播图类型(0学生，1家长，2教师.默认是 0)；是否启用 0 不启用  1 启用 默认启用
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @Override
    public DataWrapper<Void> insertSowingMap(String imageUrl,Integer type,Integer isEnable){
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        if(sowingMapDao.insertSowingMap(imageUrl,type,isEnable)==0){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }
        dataWrapper.setMsg("添加成功");
        return dataWrapper;
    }

    /**
     * @Description: 查看轮播图
     * @Param: [ type] 轮播图类型(0学生，1家长，2教师.默认是 0)
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @Override
    public DataWrapper<List<String>> querySowingMapList(Integer type) {
        DataWrapper<List<String>> dataWrapper=new DataWrapper<List<String>>();
        List<String> sowingMapList=sowingMapDao.querySowingMapList(type);
        if(sowingMapList!=null&&!sowingMapList.isEmpty()){
            dataWrapper.setData(sowingMapList);
        }else{
            dataWrapper.setData(new ArrayList<String>());
        }
        return dataWrapper;
    }

    /**
     * @Description: 后台轮播图列表
     * @Param: [ type] 轮播图类型(0学生，1家长，2教师.默认是 0)
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @Override
    public DataWrapper<List<HashMap<String,Object>>> querySowingMapListToBackstage(Integer type,Integer isEnable) {
        DataWrapper<List<HashMap<String,Object>>> dataWrapper=new DataWrapper<List<HashMap<String,Object>>>();
        //pageHelper someCode
        System.out.println(isEnable);
        List<HashMap<String,Object>> sowingMapList=sowingMapDao.querySowingMapListToBackstage(type,isEnable);

        if(sowingMapList!=null&&!sowingMapList.isEmpty()){
            dataWrapper.setData(sowingMapList);
        }else{
            dataWrapper.setData(new ArrayList<HashMap<String,Object>>());
        }
        return dataWrapper;
    }

    /**
     * @Description: 后台轮播图启用或不启用
     * @Param: [ id,isEnable] 轮播图主键；是否启用 0是不启用  1是启用
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20updatEnableOper
     */
    @Override
    public DataWrapper<Void> updatEnableOper(Integer id, Integer isEnable) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        if(sowingMapDao.updatEnableOper(id,isEnable)==0){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }
        dataWrapper.setMsg("修改成功");
        return dataWrapper;
    }


}
