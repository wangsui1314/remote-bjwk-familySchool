package com.bjwk.controller.admin;

import com.bjwk.service.admin.SowingMapService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.AdminTokenValidate;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @program: family-school
 * @description: 后台图片资源管理
 * @author: liqitian.
 * @create: 2018-06-20 16:26
 **/
@Controller
@RequestMapping("api/sowingMap")
public class SowingMapController {


    @Autowired
    private SowingMapService imagesManageService;


    /**
     * @Description: 添加轮播图
     * @Param: [imageUrl, type, isEnable] 图片路径；轮播图类型(0学生，1家长，2教师.默认是 0)；是否启用 0 不启用  1 启用 默认启用
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @RequestMapping(value="_insertSowingMap")
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> insertSowingMap(
            @RequestParam(value="imageUrl",required=true) String imageUrl,
            @RequestParam(value="type",required=false,defaultValue = "0") Integer type,
            @RequestParam(value="isEnable",required=false,defaultValue = "1") Integer isEnable
    ){
        System.out.println(1111);
        return imagesManageService.insertSowingMap(imageUrl,type,isEnable);
    }

    /**
     * @Description: 查看轮播图
     * @Param: [ type] 轮播图类型(0学生，1家长，2教师.默认是 0)
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @RequestMapping(value="_querySowingMapList")
    @ResponseBody
    public DataWrapper<List<String>> querySowingMapList(
            @RequestParam(value="type",required=true) Integer type
    ){
        return imagesManageService.querySowingMapList(type);
    }

    /**
     * @Description: 后台轮播图查看
     * @Param: [ type,isEnable] 轮播图类型(0学生，1家长，2教师.默认是 0)；是否启用 0是不启用  1是启用
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @RequestMapping(value="_querySowingMapListToBackstage")
    @ResponseBody
    @AdminTokenValidate
    public  DataWrapper<PageInfo<HashMap<String,Object>>> querySowingMapListToBackstage(
            @RequestParam(value="type",required=false) Integer type,
            @RequestParam(value="isEnable",required=false) Integer isEnable
    ){
        return imagesManageService.querySowingMapListToBackstage(type,isEnable);
    }

    /**
     * @Description: 后台轮播图启用或不启用
     * @Param: [ id,isEnable] 轮播图主键；是否启用 0是不启用  1是启用
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20updatEnableOper
     */
    @RequestMapping(value="_updatEnableOper")
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> updatEnableOper(
            @RequestParam(value="id",required=true) Integer id,
            @RequestParam(value="isEnable",required=true) Integer isEnable
    ){
        return imagesManageService.updatEnableOper(id,isEnable);
    }

    /**
     * @Description:题库列表展示测试
     * @Param: []
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/6/20
     */
    @RequestMapping(value="_test")
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<List<HashMap<String,Object>>> test(
    ){
        return imagesManageService.test();
    }
    /**
     * > set
     * global  time_zone = '+8:00'; ##修改mysql全局时区为北京时间，即我们所在的东8区
     *
     * > set
     * time_zone = '+8:00'; ##修改当前会话时区
     *
     * > flush privileges; #立即生效
     */
}
