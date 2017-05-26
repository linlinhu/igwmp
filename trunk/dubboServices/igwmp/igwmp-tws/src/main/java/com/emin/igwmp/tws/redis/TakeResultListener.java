package com.emin.igwmp.tws.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.domain.TakeWineStatus;
import com.emin.igwmp.tws.service.TakeWineRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Component("takeResultListener")
public class TakeResultListener {

	private Logger logger = LoggerFactory.getLogger(TakeResultListener.class);
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private TakeWineRecordService takeWineRecordService;
	
	public void handleMessage(Serializable message) throws Exception{
		logger.info("取酒完成监听");
		logger.info("取酒完成:"+message.toString());
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode msg = mapper.readValue(message.toString(), ObjectNode.class);
		TakeWineStatus status = TakeWineStatus.valueOf(msg.get("status").textValue());
		String orderNumber = msg.get("orderNumber").textValue();
		TakeWineRecord record = null;
		List<String> keys = new ArrayList<>(redisDao.query("*_"+orderNumber+"_*"));
		if(keys.size()>0){
			String recordJson = redisDao.get(keys.get(0));
			record = mapper.readValue(recordJson, TakeWineRecord.class);
			record.setActualTakeMl(msg.get("actualTakeMl").intValue());
			record.setStatus(status);
			record.setFinishTime(System.currentTimeMillis());
			record.setTakeInfo(msg.get("takeInfo").textValue());
			//解锁取酒码
			takeWineRecordService.unLockTakeCode(record.getTakeCode());
			//保存取酒历史记录
			takeWineRecordService.save(record);
			redisDao.delete(keys.get(0));
			//将取酒结果通知订单服务
			ObjectNode orderMsg = mapper.createObjectNode();
			orderMsg.put("orderNumber", orderNumber);
			boolean success = status.equals(TakeWineStatus.SUCCESS);
			orderMsg.put("success", success);
			orderMsg.put("actualTakeMl", record.getActualTakeMl());
			orderMsg.put("shouldTakeMl", record.getShouldTakeMl());
		
			redisDao.sendMessage("ordertake", mapper.writeValueAsString(orderMsg));
			
		}
		
	}
}
