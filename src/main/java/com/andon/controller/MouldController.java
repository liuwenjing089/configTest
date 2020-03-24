package com.andon.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.CodeList;
import com.andon.bean.Combox;
import com.andon.bean.Mould;
import com.andon.bean.MouldFaultParts;
import com.andon.bean.MouldPartsInfo;
import com.andon.bean.MouldPreventionType;
import com.andon.bean.MouldRepair;
import com.andon.bean.TestMould;
import com.andon.bean.dto.MouldRepairHistory;
import com.andon.bean.dto.TestMouldRepair;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.CodeListService;
import com.andon.service.MouldFaultPartsService;
import com.andon.service.MouldPreventionTypeService;
import com.andon.service.MouldRepairService;
import com.andon.service.MouldService;
import com.andon.service.TestMouldService;
import com.andon.utils.PrintException;
import com.andon.utils.UUIDString;
import com.andon.validateInterface.First;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/mould")
public class MouldController extends BaseController {

    @Autowired
    private MouldService mouldService;
    
    @Autowired
    private MouldRepairService mouldRepairService;
    
    @Autowired
    private TestMouldService testMouldService;

    @Autowired
	private MouldFaultPartsService mouldFaultPartsService;

    @Autowired
	private CodeListService codeListService;
    
    @Autowired
    private MouldPreventionTypeService mouldPreventionTypeService;
    //模具列表信息
    @RequestMapping(value = "/getMouldList.do", method = RequestMethod.POST)
    public @ResponseBody Object getMouldList(HttpServletRequest request, Mould mould) {
    	
		try {

			List<Mould> mouldList = mouldService.getMouldList(mould);
			return resultHandler(mouldList);

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }
    
    //模具列表信息不分页
    @RequestMapping(value = "/getMouldLists.do", method = RequestMethod.POST)
    public @ResponseBody Object getMouldLists(HttpServletRequest request, Mould mould) {
    	
		try {

			List<Mould> mouldList = mouldService.getMouldLists(mould);
			return resultHandler(mouldList);

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }

    //模具列表数量
    @RequestMapping(value = "/getMouldCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getMouldCount(HttpServletRequest request, Mould mould) {
		try {

			int pageCount;

			pageCount = mouldService.getMouldCount(mould);

			return resultHandler(pageCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }

    //插入
    @RequestMapping(value = "/insertMould.do", method = RequestMethod.POST)
    @ResponseBody
    public Object insertMould(HttpServletRequest request, Mould mould, HttpSession session)throws ParseException {
		try {
			//效验重复
			int count = mouldService.checkForRecurrence(mould);
			if(count > 0){
				return resultHandler(exceptionHandle(ExceptionCode.MOULD_REPEAT));
			}
			
//			File targetFile=null;
//			String url="";//返回存储路径
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
//			MultipartFile pictureUrl =  multipartRequest.getFile("picture3D");
//			
//			
//			 String fileName=pictureUrl.getOriginalFilename();//获取文件名加后缀
//		        if(fileName!=null&&fileName!=""){
//		            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/metronic/upload/";//存储路径
//		            String path = request.getSession().getServletContext().getRealPath("metronic/upload"); //文件存储位置
//		            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//		            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
//
//		            //先判断文件是否存在
//		            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		            String fileAdd = sdf.format(new Date());
//		            //获取文件夹路径
//		            File file1 =new File(path+"/"+fileAdd);
//		            //如果文件夹不存在则创建
//		            if(!file1 .exists()  && !file1 .isDirectory()){
//		                file1 .mkdir();
//		            }
//		            //将图片存入文件夹
//		            targetFile = new File(file1, fileName);
//	                //将上传的文件写到服务器上指定的文件。
//		            pictureUrl.transferTo(targetFile);
//	                url = returnUrl + fileAdd+"/"+fileName;
//                    
//		        }
		        
			mould.setMouldKey(UUIDString.getPartsKeyByUUId());
//		    mould.setPictureUrl(url);
			String userName = session.getAttribute("username").toString();
			mould.setCreateUser(userName);
			mould.setUpdateUser(userName);
			mouldService.insertMould(mould);
			return resultHandler(null);
//		} catch (MaxUploadSizeExceededException e) {
//			return exceptionHandle(ExceptionCode.FILE_UPLOAD_FAILED);
//		} catch (IOException e) {
//			return exceptionHandle(ExceptionCode.FORMATFILE_NOT_IMAGE);

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }


    //根据id查询模具详情
    @RequestMapping(value = "/selectMouldById.do", method = RequestMethod.POST)
    public @ResponseBody Object selectMouldById(HttpServletRequest request, int id) {
		try {

			Mould mould = mouldService.selectMouldById(id);
			return resultHandler(mould);
			
		} catch (Exception e) {
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }

    //修改
    @RequestMapping(value = "/updateMouldById.do", method = RequestMethod.POST)
    public @ResponseBody Object updateMouldById(HttpServletRequest request, Mould mould, HttpSession session) {
		try {
//			File targetFile=null;
//			String url="";//返回存储路径
				
//			try {
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
//				MultipartFile pictureUrl =  multipartRequest.getFile("picture3D");
//							
//				String fileName=pictureUrl.getOriginalFilename();//获取文件名加后缀
//				if(fileName!=null&&fileName!=""){
//				    String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/metronic/upload/";//存储路径
//				    String path = request.getSession().getServletContext().getRealPath("metronic/upload"); //文件存储位置
//				    String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//				    fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
//
//				    //先判断文件是否存在
//				    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//				    String fileAdd = sdf.format(new Date());
//				    //获取文件夹路径
//				    File file1 =new File(path+"/"+fileAdd);
//				    //如果文件夹不存在则创建
//				    if(!file1 .exists()  && !file1 .isDirectory()){
//				        file1 .mkdir();
//				    }
//				    //将图片存入文件夹
//				    targetFile = new File(file1, fileName);
//				    //将上传的文件写到服务器上指定的文件。
//				    pictureUrl.transferTo(targetFile);
//				    url = returnUrl + fileAdd+"/"+fileName;
//				    
//				}
//			} catch (ClassCastException e) {
//				
//			}
//		    mould.setPictureUrl(url);   
			String userName = session.getAttribute("username").toString();
			mould.setUpdateUser(userName);
			mouldService.updateMouldById(mould);
			return resultHandler(null);
//		} catch (MaxUploadSizeExceededException e) {
//			return exceptionHandle(ExceptionCode.FILE_UPLOAD_FAILED);
//		} catch (IOException e) {
//			return exceptionHandle(ExceptionCode.FORMATFILE_NOT_IMAGE);

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //报修
    @RequestMapping(value = "/repairMould.do", method = RequestMethod.POST)
    public @ResponseBody Object repairMould(@Validated({First.class}) MouldRepair mouldRepair, BindingResult result, HttpServletRequest request,  HttpSession session) {
    	
		//校验转型	
		if (result.hasErrors()) {
            return resultHandler(convertErrors(result));
        }
		
		try {
			String userName = session.getAttribute("username").toString();
			mouldRepair.setCreateUser(userName);
			mouldRepair.setUpdateUser(userName);
			mouldRepairService.insertMouldRepair(mouldRepair);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //模具维修记录填写，补全
    @RequestMapping(value = "/selectRepairMouldByRepairId.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByRepairId(int id, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			MouldRepair mouldRepair = mouldRepairService.selectMouldRepairById(id);
			
			//查询试模记录中是否已经存在成功的记录
			int count = testMouldService.selectTestMouldToSucceedById(String.valueOf(id));
			if(count > 0){
				mouldRepair.setFlag(true);
			}else{
				mouldRepair.setFlag(false);
			}
			//部品品番赋值
			List<MouldFaultParts> outparts = new ArrayList<>();
			List<MouldFaultParts> partsList = mouldFaultPartsService.selectParts(String.valueOf(mouldRepair.getReId()));
			if(partsList != null && partsList.size()>0){
				for (MouldFaultParts parts:partsList){
					outparts.add(parts);
				}
				mouldRepair.setPartsList(outparts);
			}

			return resultHandler(mouldRepair);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //模具维修记录详情页面
    @RequestMapping(value = "/selectRepairMouldByConfirm.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByConfirm(int id, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			MouldRepair mouldRepair = mouldRepairService.selectMouldRepairById(id);

			//部品品番赋值
			List<MouldFaultParts> outparts = new ArrayList<>();
			List<MouldFaultParts> partsList = mouldFaultPartsService.selectParts(mouldRepair.getReId());
			if(partsList != null && partsList.size()>0){
				for (MouldFaultParts parts:partsList){
					outparts.add(parts);
				}
				mouldRepair.setPartsList(outparts);
			}

			//查询试模记录
			List<TestMould> testMouldList = testMouldService.getTestMouldList(String.valueOf(id));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MouldRepair", mouldRepair);
			map.put("TestMouldList", testMouldList);
			
			return resultHandler(map);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //保全填写或补全信息
    @RequestMapping(value = "/updateRepairMould.do", method = RequestMethod.POST)
    public @ResponseBody Object updateRepairMould(MouldRepair mouldRepair, HttpServletRequest request,  HttpSession session) {
    	
		try {
			//更新部品品番
			List<MouldFaultParts> partsList = mouldFaultPartsService.selectParts(mouldRepair.getReId());
			if(partsList!=null&&partsList.size()>0){
				mouldFaultPartsService.delete(mouldRepair.getId());
			}
			String insertList = mouldRepair.getPartsListStr();
			if(!"".equals(insertList) && insertList != null) {
				JSONArray jsonArray= JSONArray.fromObject(insertList);
				@SuppressWarnings("rawtypes")
				Collection collection  = JSONArray.toCollection(jsonArray, MouldFaultParts.class);
				@SuppressWarnings("rawtypes")
				Iterator it = collection.iterator();
				List<MouldFaultParts> partsList1 = new ArrayList<MouldFaultParts>();
				while (it.hasNext()) {
					MouldFaultParts q = (MouldFaultParts) it.next();
					q.setReId(String.valueOf(mouldRepair.getId()));
					q.setIsActive(1);
					partsList1.add(q);
				}
				mouldFaultPartsService.add(partsList1);
			}

			String userName = session.getAttribute("username").toString();
			mouldRepair.setCreateUser(userName);
			mouldRepair.setUpdateUser(userName);
			mouldRepairService.updateMouldRepairById(mouldRepair);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //保全填写或补全信息
    @RequestMapping(value = "/updateRepairMoulds.do", method = RequestMethod.POST)
    public @ResponseBody Object updateRepairMoulds(@RequestBody MouldRepair mouldRepair, HttpServletRequest request,  HttpSession session) {
    	
		try {

			String userName = session.getAttribute("username").toString();
			mouldRepair.setCreateUser(userName);
			mouldRepair.setUpdateUser(userName);
			mouldRepairService.updateMouldRepairById(mouldRepair);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //各级别确认
    @RequestMapping(value = "/mouldRepairByMonitorConfirm.do", method = RequestMethod.GET)
    public @ResponseBody Object mouldRepairByMonitorConfirm(int id, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			int userType = Integer.parseInt(session.getAttribute("userType").toString());

			mouldRepairService.mouldRepairByMonitorConfirm(id, userName, userType);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //各级别确认 + 插入模具报修种类
    @RequestMapping(value = "/mouldRepairByMonitorConfirmAndAddType.do", method = RequestMethod.GET)
    public @ResponseBody Object mouldRepairByMonitorConfirmAndAddType(int id, int mouldId, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			int userType = Integer.parseInt(session.getAttribute("userType").toString());

			mouldRepairService.mouldRepairByMonitorConfirmAndAddType(id, userName, userType, mouldId);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
	/**
	 * 查询所有下拉框
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initCombox.do", method = RequestMethod.GET)
	public @ResponseBody Object initCombox(HttpServletRequest request) {
	    
	    //工厂combox
		List<Combox> manufacturerCombox = new ArrayList<Combox>();
		Combox c1 = new Combox();
		c1.setId(Constant.MANUFACTURER_ID_1);
		c1.setName(Constant.MANUFACTURER_NAME_1);
		manufacturerCombox.add(c1);
		Combox c2 = new Combox();
		c2.setId(Constant.MANUFACTURER_ID_2);
		c2.setName(Constant.MANUFACTURER_NAME_2);
		manufacturerCombox.add(c2);
		Combox c3 = new Combox();
		c3.setId(Constant.MANUFACTURER_ID_3);
		c3.setName(Constant.MANUFACTURER_NAME_3);
		manufacturerCombox.add(c3);


		return resultHandler(manufacturerCombox);
	}
	
	/**
	 * 查询所有下拉框
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initComboxs.do", method = RequestMethod.GET)
	public @ResponseBody Object initComboxs(HttpServletRequest request) {

		try {
		    //工厂combox
			List<Combox> manufacturerCombox = new ArrayList<Combox>();
			Combox c1 = new Combox();
			c1.setId(Constant.MANUFACTURER_ID_1);
			c1.setName(Constant.MANUFACTURER_NAME_1);
			manufacturerCombox.add(c1);
			Combox c2 = new Combox();
			c2.setId(Constant.MANUFACTURER_ID_2);
			c2.setName(Constant.MANUFACTURER_NAME_2);
			manufacturerCombox.add(c2);
			Combox c3 = new Combox();
			c3.setId(Constant.MANUFACTURER_ID_3);
			c3.setName(Constant.MANUFACTURER_NAME_3);
			manufacturerCombox.add(c3);
	
	    	List<CodeList> codeLists = codeListService.getByType(Constant.CODE_LIST_TYPE_7,1);
	    	List<Combox> mouldType = new ArrayList<Combox>();
	    	for(CodeList codeList: codeLists){
	    		Combox combox = new Combox();
	    		combox.setId(codeList.getId());
	    		combox.setName(codeList.getCodeName());
	    		mouldType.add(combox);
	    	}
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("factory", manufacturerCombox);
	    	map.put("mouldType", mouldType);
	    	
			return resultHandler(map);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
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
	public @ResponseBody Object initFaultTypeCombox(HttpServletRequest request) {
        try {
    		List<CodeList> lists = codeListService.getByType(Constant.CODE_LIST_TYPE_4,1);
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
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

	}
	
	
	/**
	 * 查询模具报修详情页面所有下拉
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initAllTypeCombox.do", method = RequestMethod.GET)
	public @ResponseBody Object initAllTypeCombox(HttpServletRequest request) {
        try {
        	
        	Map<String, Object> comboxMap = new HashMap<String, Object>();
        	
    		List<CodeList> lists = codeListService.getByType(Constant.CODE_LIST_TYPE_9,1);
    		List<Combox> manufacturerCombox = new ArrayList<Combox>();
    		if(lists!= null && lists.size()>0){
    			for(int i=0;i<lists.size();i++){
    				Combox combox = new Combox();
    				combox.setId(lists.get(i).getId());
    				combox.setName(lists.get(i).getCodeName());
    				manufacturerCombox.add(combox);
    			}
    		}
    		comboxMap.put("manufacturerCombox", manufacturerCombox);
    		
    		
    		List<CodeList> lists1 = codeListService.getByType(Constant.CODE_LIST_TYPE_8,1);
    		List<Combox> failurePeriodCombox = new ArrayList<Combox>();
    		if(lists1!= null && lists1.size()>0){
    			for(int i=0;i<lists1.size();i++){
    				Combox combox = new Combox();
    				combox.setId(lists1.get(i).getId());
    				combox.setName(lists1.get(i).getCodeName());
    				failurePeriodCombox.add(combox);
    			}
    		}
    		
    		comboxMap.put("failurePeriodCombox", failurePeriodCombox);
    		
    		
    		List<CodeList> lists2 = codeListService.getByType(Constant.CODE_LIST_TYPE_10,1);
    		List<Combox> faultReasonCombox = new ArrayList<Combox>();
    		if(lists2!= null && lists2.size()>0){
    			for(int i=0;i<lists2.size();i++){
    				Combox combox = new Combox();
    				combox.setId(lists2.get(i).getId());
    				combox.setName(lists2.get(i).getCodeName());
    				faultReasonCombox.add(combox);
    			}
    		}
    		
    		comboxMap.put("faultReasonCombox", faultReasonCombox);
	
    		return resultHandler(comboxMap);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

	}


	/**
	 * 查询损坏部件分类下拉框和作业内容下拉
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initFaultPartsComboxToType.do", method = RequestMethod.POST)
	public @ResponseBody Object initFaultPartsComboxToType(HttpServletRequest request) {
		
        try {
    		//combox
        	Map<String, Object> comboxMap = new HashMap<String, Object>();
        	
        	MouldPartsInfo mouldPartsInfo = new MouldPartsInfo();
        	List<MouldPartsInfo> list = mouldRepairService.selectMouldPartsInfoToPartsType(mouldPartsInfo);
        	comboxMap.put("mouldPartsInfo", list);
        	
	    	List<CodeList> codeLists = codeListService.getByType(Constant.CODE_LIST_TYPE_11,1);
	    	comboxMap.put("mouldPartsRepairContent", codeLists);
	    	
    		return resultHandler(comboxMap);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }


	}
	
	/**
	 * 查询损坏部件名称下拉框
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initFaultPartsComboxToName.do", method = RequestMethod.POST)
	public @ResponseBody Object initFaultPartsComboxToName(HttpServletRequest request, MouldPartsInfo mouldPartsInfo) {
		
        try {
    		//combox
        	List<MouldPartsInfo> list = mouldRepairService.selectMouldPartsInfoToPartsName(mouldPartsInfo);

    		return resultHandler(list);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }


	}
	
	/**
	 * 查询损坏部件品番或型号下拉框
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initFaultPartsComboxToNum.do", method = RequestMethod.POST)
	public @ResponseBody Object initFaultPartsComboxToNum(HttpServletRequest request, MouldPartsInfo mouldPartsInfo) {
		
        try {
    		//combox
        	List<MouldPartsInfo> list = mouldRepairService.selectMouldPartsInfoToPartsNum(mouldPartsInfo);
    		return resultHandler(list);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }


	}

	
    //查询模具  点检类型下拉框
    @RequestMapping(value = "/spotRepairHistoryList.do", method = RequestMethod.GET)
    public @ResponseBody Object spotRepairHistoryList(HttpServletRequest request, HttpSession httpSession, int id) {
        try {
        	List<MouldRepairHistory> list = mouldService.mouldRepairHistoryList(id);
            return resultHandler(list);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //查询模具报修一条记录详情
    @RequestMapping(value = "/selectRepairMouldByRepairIds.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByRepairIds(int id, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			TestMouldRepair testMouldRepair = mouldRepairService.selectMouldRepairByIds(id);
			

			return resultHandler(testMouldRepair);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //查询模具故障类型
    @RequestMapping(value = "/getMouldPreventionTypeList.do", method = RequestMethod.POST)
    public @ResponseBody Object getMouldPreventionTypeList(int reId, HttpServletRequest request,  HttpSession session) {
    	
		try {

			List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeService.getMouldPreventionTypeList(reId);
			
			return resultHandler(mouldPreventionTypeList);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //插入模具故障类型
    @RequestMapping(value = "/insertMouldPreventionType.do", method = RequestMethod.POST)
    public @ResponseBody Object insertMouldPreventionType(MouldPreventionType mouldPreventionType, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			mouldPreventionType.setCreateUser(userName);
			mouldPreventionType.setUpdateUser(userName);
			mouldPreventionTypeService.insertMouldPreventionType(mouldPreventionType);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
}
