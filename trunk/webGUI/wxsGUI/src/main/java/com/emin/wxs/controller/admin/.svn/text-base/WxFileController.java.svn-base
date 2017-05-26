package com.emin.wxs.controller.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.emin.base.exception.EminException;
import com.emin.base.util.EminEnvUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.WxFile;
import com.emin.wxs.facade.WxFileFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/wxfile")
public class WxFileController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(WxFileController.class);
	@Autowired
	@Qualifier("wxFileFacade")
	private WxFileFacade wxFileFacade;	
	
	@RequestMapping(value="/upload.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private JSONObject upload(MultipartFile file,Long woaId){
		JSONObject json = new JSONObject();
		
		try {
			WxFile wxFile = new WxFile();
			wxFile.setWoaId(woaId);
			wxFile.setName(file.getOriginalFilename());
			wxFile.setSize(file.getSize());
			wxFile.setType(WxFile.TYPE_IMG);
			String time = String.valueOf(System.currentTimeMillis());
			String path = System.getenv(EminEnvUtil.EMIN_HOME)+File.separator+"wxs"+File.separator+"files";
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File targetFile = new File(path,time+suffix);
		    if(!targetFile.exists()){  
		    	targetFile.mkdirs();  
		    }  
			file.transferTo(targetFile);
			wxFile.setPath(targetFile.getAbsolutePath());
			wxFileFacade.saveFile(wxFile);
			json.put("success", true);
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			json.put("success", false);
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			json.put("success", false);
		}
		
		return json;
	}
	@RequestMapping(value="/list.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONArray loadFiles(Long woaId){
		JSONArray arr = wxFileFacade.loadFiles(woaId);
		String url = getRequest().getScheme() + "://"
		+ getRequest().getServerName() + ":" + getRequest().getServerPort()+getRequest().getContextPath()
		+"/wxfile/download.do?id=";
		for(int i=0;i<arr.size();i++){
			arr.getJSONObject(i).put("url", url+arr.getJSONObject(i).getLong("id"));
		}
		return arr;
	}
	@RequestMapping(value="/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deleteFile(Long id){
		JSONObject json = new JSONObject();
		try {
			wxFileFacade.deleteFile(id);
			json.put("success", true);
			json.put("message", "");
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			json.put("success", false);
			json.put("message", e.getLocalizedMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			json.put("success", false);
			json.put("message", "删除图片失败");
		}
		return json;
	}
	
	@RequestMapping("/download.do")
	public void downloadFile(Long id) throws IOException {

		try {
			WxFile file = wxFileFacade.getFile(id);

			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buf = new byte[1024];
			int len = 0;

			getResponse().reset(); // 非常重要
			// 纯下载方式
			getResponse().setContentType("application/x-msdownload");
			String fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
			fileName = fileName.replaceAll(",", "");
			getResponse().setHeader("Content-Disposition", "attachment; filename=" + fileName);

			OutputStream out = getResponse().getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
