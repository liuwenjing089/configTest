package com.andon.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.CodeList;
import com.andon.bean.Combox;
import com.andon.bean.MouldRepair;
import com.andon.bean.Parts;
import com.andon.bean.Picture;
import com.andon.bean.dto.EquipRepairOutput;
import com.andon.bean.dto.UpdateEquipRepair;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.dao.PictureDao;
import com.andon.service.CodeListService;
import com.andon.service.EquipRepairService;
import com.andon.service.MouldRepairService;
import com.andon.service.PartsService;
import com.andon.utils.DateUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/equipRepair")
public class EquipRepairController extends BaseController{

    @Autowired
    private EquipRepairService equipRepairService;
    @Autowired
    private MouldRepairService mouldRepairService;
    @Autowired
    private PartsService partsService;
    @Autowired
    private CodeListService codeListService;
    @Autowired
    private PictureDao pictureDao;
    //修改
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request, HttpSession session, @RequestBody UpdateEquipRepair updateEquipRepair) {

        //更新部品信息
        try {
            List<Parts> partsList = partsService.selectParts(updateEquipRepair.getId());
            if(partsList!=null&&partsList.size()>0){
                partsService.delete(updateEquipRepair.getId());
            }
            String insertList = updateEquipRepair.getPartsListStr();
            if(!"".equals(insertList) && insertList != null) {
                JSONArray jsonArray=JSONArray.fromObject(insertList);
                @SuppressWarnings("rawtypes")
				Collection collection  = JSONArray.toCollection(jsonArray, Parts.class);
                @SuppressWarnings("rawtypes")
				Iterator it = collection.iterator();
                List<Parts> partsList1 = new ArrayList<Parts>();
                while (it.hasNext()) {
                    Parts q = (Parts) it.next();
                    q.setReId(updateEquipRepair.getId());
                    q.setIsActive(1);
                    partsList1.add(q);
                }
                partsService.add(partsList1);
            }
            String userName = session.getAttribute("username").toString();
            updateEquipRepair.setUpdateUser(userName);
            updateEquipRepair.setUpdateTime(DateUtils.getNowDate());

            //地址设为null
            if(updateEquipRepair.getLocationUrl()!= null && updateEquipRepair.getLocationUrl().isEmpty()){
                updateEquipRepair.setLocationUrl(null);
            }
            equipRepairService.update(updateEquipRepair);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //更新设备确认状态
    @RequestMapping(value = "/updateDetailState.do", method = RequestMethod.POST)
    public @ResponseBody Object updateDetailState(HttpServletRequest request, HttpSession session, @RequestBody UpdateEquipRepair updateEquipRepair) {

        //更新部品信息
        try {
            String userName = session.getAttribute("username").toString();
            updateEquipRepair.setUpdateUser(userName);
            updateEquipRepair.setUpdateTime(DateUtils.getNowDate());
            equipRepairService.updateDetailState(updateEquipRepair);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //设备维修详细信息
    @RequestMapping(value = "/getInfo.do", method = RequestMethod.POST)
    public @ResponseBody Object getInfo(HttpServletRequest request, int id) {

        try {
            EquipRepairOutput equipRepairOutput = null;
            equipRepairOutput = equipRepairService.getByid(id);

            //设置使用部品信息
            List<Parts> outparts = new ArrayList<>();
            List<Parts> partsList = partsService.selectParts(id);
            if(partsList != null && partsList.size()>0){
                for (Parts parts:partsList){
                    outparts.add(parts);
                }
                equipRepairOutput.setPartsList(outparts);
            }

            //获取图片地址
            if(equipRepairOutput.getLocationUrl()!=null&& !equipRepairOutput.getLocationUrl().isEmpty()){
                List<String> stringList = new ArrayList<>(Arrays.asList(equipRepairOutput.getLocationUrl().split(",")));
                List<Picture> pictures = pictureDao.selectPicture(stringList);
                if(pictures.size()>0){
                    List<String> pp = new ArrayList<String>();
                    for(Picture picture:pictures){
                        pp.add(picture.getUrl());
                    }
                    String urls =  StringUtils.join(pp.toArray(), ",");
                    equipRepairOutput.setLocationUrl(urls);
                }
            }
            //获取当前系统时间
            equipRepairOutput.setNowDate(DateUtils.getCurrentDate());
            equipRepairOutput.setNowTime(DateUtils.getCurrentDateMinute().substring(11));
            return resultHandler(equipRepairOutput);
        } catch (Exception e) {
            e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //修改
    @RequestMapping(value = "/updateMould.do", method = RequestMethod.POST)
    public @ResponseBody Object updateMould(HttpServletRequest request, MouldRepair MouldRepair) {
        try {
            mouldRepairService.updateMouldRepairById(MouldRepair);
            return resultHandler(null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }

    /**
     * 查询故障类型所有下拉框
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/initFaultTypeCombox.do", method = RequestMethod.GET)
    public @ResponseBody Object initfaultTypeCombox(HttpServletRequest request) {
    	
        try {

            List<CodeList> lists = codeListService.getByType(Constant.CODE_LIST_TYPE_2,Constant.IS_PARENT);
            List<Combox> manufacturerCombox = new ArrayList<Combox>();
            if(lists!= null && lists.size()>0){
                for(int i=0;i<lists.size();i++){
                    Combox combox = new Combox();
                    combox.setId(lists.get(i).getId());
                    combox.setName(lists.get(i).getCodeName());
                    manufacturerCombox.add(combox);
                }
            }
            return resultHandler(manufacturerCombox);
        } catch (Exception e) {
            e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }

    /**
     * 查询维修用时分类所有下拉框
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/initRepairUsetimeTypeCombox.do", method = RequestMethod.GET)
    public @ResponseBody Object initRepairUsetimeTypeCombox(HttpServletRequest request) {

        try {
            List<CodeList> lists = codeListService.getByType(Constant.CODE_LIST_TYPE_3,Constant.IS_PARENT);
            List<Combox> manufacturerCombox = new ArrayList<Combox>();
            if(lists!= null && lists.size()>0){
                for(int i=0;i<lists.size();i++){
                    Combox combox = new Combox();
                    combox.setId(lists.get(i).getId());
                    combox.setName(lists.get(i).getCodeName());
                    manufacturerCombox.add(combox);
                }
            }
            return resultHandler(manufacturerCombox);
        } catch (Exception e) {
            e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }
}


