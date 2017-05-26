package com.emin.wxs.controller.fs;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.emin.base.exception.EminException; 
import com.emin.wxs.controller.WxsBaseController; 
import com.emin.wxs.facade.fs.WxsToFileCaller; 
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/file")
public class FileController extends WxsBaseController {

	private Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	@Qualifier("wxsToFileCaller")
	private WxsToFileCaller wxsToFileCaller;	
	
	@RequestMapping("/upload.do")
	@ResponseBody
	private JSONObject upload(MultipartFile file){
		JSONObject json = new JSONObject();
		
		try {	 

			Long id = wxsToFileCaller.uploadFile(file.getBytes(), file.getName());
			String url = getRequest().getScheme() + "://"
					+ getRequest().getServerName() + ":" + getRequest().getServerPort()+getRequest().getContextPath()
					+"/file/download.do?id="+id;
			json.put("url", url);
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
	 
	 
	
	@RequestMapping("/download.do")
	public void downloadFile(Long id) throws IOException {

		try {
			byte[] file = wxsToFileCaller.getFileById(id);
			getResponse().reset(); // 非常重要
			getResponse().setContentType("image/jpeg"); 
			getResponse().setHeader("Content-Disposition", "attachment; ");
			//缓存
//			getResponse().setHeader("Pragma", "no-cache");
//			getResponse().setHeader("Cache-Control", "no-cache");
//			getResponse().setDateHeader("Expires", 0);

			OutputStream out = getResponse().getOutputStream();
			out.write(file);
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
