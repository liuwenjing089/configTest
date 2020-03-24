package com.andon.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andon.bean.Picture;
import com.andon.dao.PictureDao;
import com.andon.utils.UUIDString;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.andon.commons.ExceptionCode;
import com.andon.utils.PropertiesFileUtils;

@Controller
@RequestMapping ("/file")
public class UploaderController extends BaseController{

    @Autowired
    private PictureDao pictureDao;
    @RequestMapping(value = "/uploadImg.do", method = RequestMethod.POST)
    public @ResponseBody Object uploadPicture(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

		Map<String, String> pMap = PropertiesFileUtils.loadPropertiesFile("properties/config.properties");
		String uploadPath = pMap.get("UPLOAD_PATH");
		String uploadPathBaoQuan = pMap.get("UPLOAD_PATH_BAOQUAN");
    	
        File targetFile=null;
        String url="";//返回存储路径
        String fileName=file.getOriginalFilename();//获取文件名加后缀

        if(fileName!=null&&fileName!=""){
            String path = uploadPath;
            
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
            //先判断文件是否存在
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            //获取文件夹路径
            //File file1 =new File(path+"/"+fileAdd);
            File file1 =new File(path);
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }

            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                //url = uploadPath+fileAdd+"/"+fileName;
                url = "/" + uploadPathBaoQuan + fileName;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //writeJson(response, result);
        return resultHandler(url);
    }
	
     @RequestMapping("/ajaxFileUpload")
	 public  @ResponseBody Object  ajaxFileUpload(HttpServletRequest request,HttpServletResponse response,
			 @RequestParam(value = "file") MultipartFile file){
    	 
 		 Map<String, String> pMap = PropertiesFileUtils.loadPropertiesFile("properties/config.properties");
 		 String uploadPath = pMap.get("UPLOAD_PATH");
 		 String uploadPathBaoQuan = pMap.get("UPLOAD_PATH_BAOQUAN");
    	 
    	 Map<String, Object> map = new HashMap<String, Object>();
         File targetFile=null;
         String url="";//返回存储路径
         String fileName=file.getOriginalFilename();//获取文件名加后缀
         
         if(fileName!=null&&fileName!=""){
             String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/metronic/upload/";//存储路径
             //String path = request.getSession().getServletContext().getRealPath("metronic/upload"); //文件存储位置
             String path = uploadPath;
             
             String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
             fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

             //先判断文件是否存在
             SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
             String fileAdd = sdf.format(new Date());
             //获取文件夹路径
             //File file1 =new File(path+"/"+fileAdd);
             File file1 =new File(path);
             //如果文件夹不存在则创建
             if(!file1 .exists()  && !file1 .isDirectory()){
                 file1 .mkdir();
             }
             //将图片存入文件夹
             targetFile = new File(file1, fileName);
             try {
                 //将上传的文件写到服务器上指定的文件。
                 file.transferTo(targetFile);
                 //url = uploadPath+fileAdd+"/"+fileName;
                 url = "/" + uploadPathBaoQuan + fileName;

                 map.put("rc", 1);
                 map.put("msg", "上传成功");
                 map.put("value", url);
                 return  resultHandler(map);
             } catch (Exception e) {
                e.printStackTrace();
    			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
             }
         }else{
             map.put("rc", 2);
             map.put("msg", "上传失败");
             map.put("value", null);
             return  resultHandler(map);
         }
     }

    //批量上传
    @RequestMapping(value = "/batchUploadImg.do", method = RequestMethod.POST)
    public @ResponseBody Object batchUploadPicture(@RequestParam(value="file") MultipartFile[] file, HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> pMap = PropertiesFileUtils.loadPropertiesFile("properties/config.properties");
        String uploadPath = pMap.get("UPLOAD_PATH");
        String uploadPathBaoQuan = pMap.get("UPLOAD_PATH_BAOQUAN");
        List<String> reList = new ArrayList<>();
        File targetFile = null;
        String url = "";//返回存储路径
        if (file != null && file.length > 0) {
            List<Picture> pictures = new ArrayList<>();
            for (int i = 0; i < file.length; i++) {
                String fileName = file[i].getOriginalFilename();//获取文件名加后缀
                if (fileName != null && fileName != "") {
                    String path = uploadPath;

                    String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
                    fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名
                    //先判断文件是否存在
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                    //获取文件夹路径
                    //File file1 =new File(path+"/"+fileAdd);
                    File file1 = new File(path);
                    //如果文件夹不存在则创建
                    if (!file1.exists() && !file1.isDirectory()) {
                        file1.mkdir();
                    }

                    //将图片存入文件夹
                    targetFile = new File(file1, fileName);
                    try {
                        //将上传的文件写到服务器上指定的文件。
                        file[i].transferTo(targetFile);
                        //url = uploadPath+fileAdd+"/"+fileName;
                        url = "/" + uploadPathBaoQuan + fileName;
                        reList.add(url);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String res = StringUtils.join(reList.toArray(), ",");
        return resultHandler(res);
    }
}
