package com.emin.igwmp.tws.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.log.BussLog;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.domain.TakeWineStatus;
import com.emin.igwmp.tws.exception.TwsExceptionCode;
import com.emin.igwmp.tws.redis.RedisDao;
import com.emin.igwmp.tws.service.TakeWineRecordService;
import com.emin.igwmp.tws.util.EminPropertyPlaceholderConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
@Service("takeWineRecordService")
public class TakeWineRecordServiceImpl extends CRUDServiceImpl<TakeWineRecord> implements TakeWineRecordService{

	private Logger logger = LoggerFactory.getLogger(TakeWineRecordServiceImpl.class);
	
	@Autowired
	private RedisDao redisDao;
	
	private ObjectMapper objectMapper =new ObjectMapper();
	
	@Override
	@BussLog(description="创建或查询取酒单")
	public TakeWineRecord createOrGetRecord(String orderNumber,Integer shouldTakeMl) throws EminException, Exception{
		if(StringUtils.isBlank(orderNumber) || shouldTakeMl==null || shouldTakeMl.intValue()==0){
			throw new EminException(TwsExceptionCode.PARAMTER_INVALID);
		}
		List<String> keys = new ArrayList<>(redisDao.query("*_"+orderNumber+"_*"));
		TakeWineRecord record = null;
		if(keys.size()>0){
			String recordJson = redisDao.get(keys.get(0));		
			record = objectMapper.readValue(recordJson, TakeWineRecord.class);
		}else{
			String t = EminPropertyPlaceholderConfigurer.getContextProperty("tws.codeExpireHours");
			record = new TakeWineRecord();
			record.setRecordNumber(generateRecordNumber());
			record.setOrderNumber(orderNumber);
			record.setTakeCode(generateTakeCode());
			record.setShouldTakeMl(shouldTakeMl);
			record.setStatus(TakeWineStatus.CREATE);
			record.setCreateTime(System.currentTimeMillis());
			record.setActualTakeMl(0);
			Long codeExipreTime = record.getCreateTime()+TimeUnit.HOURS.toMillis(Long.valueOf(t));
			record.setCodeExpireTime(codeExipreTime);
			StringBuilder recordKey = new StringBuilder();
			recordKey.append("TAKEWINE_").append(record.getRecordNumber()).append("_").append(record.getOrderNumber()).append("_").append(record.getTakeCode()).append("_");
			redisDao.put(recordKey.toString(), JSONObject.fromObject(record));
			
			redisDao.expire(recordKey.toString(), Long.valueOf(t), TimeUnit.HOURS);
		}
		
		return record;
	}
	@Override
	@BussLog(description="根据订单号查询取酒记录")
	public TakeWineRecord getByOrderNumber(String orderNumber){
		PreFilter orderFilter = PreFilters.eq(TakeWineRecord.PROP_ORDER_NUMBER, orderNumber);
		return this.findUniqueByPreFilter(orderFilter);
	}
	
	@Override
	@BussLog(description="根据取酒单号查询取酒记录")
	public TakeWineRecord getByRecordNumber(String recordNumber){
		PreFilter recordFilter = PreFilters.eq(TakeWineRecord.PROP_RECORD_NUMBER, recordNumber);
		return this.findUniqueByPreFilter(recordFilter);
	}
	@Override
	@BussLog(description="使用取酒码并锁定")
	public TakeWineRecord lockTakeCode(String takeCode){
		logger.info("使用取酒码:"+takeCode);
		//验证取酒码是否已被锁定
		String code  = redisDao.get("LOCKTAKECODE_"+takeCode);
		if(StringUtils.isNotBlank(code)){
			//取酒码已被锁定抛出异常
			logger.info("取酒码:"+takeCode+"已被锁定");
			throw new EminException(TwsExceptionCode.TAKECODE_LOCKED);
		}
		List<String> keys = new ArrayList<>(redisDao.query("*_"+takeCode+"_*"));
		TakeWineRecord takeWineRecord = null;
		if(keys.size()>0){
			String recordJSON = redisDao.get(keys.get(0));
			try {
				takeWineRecord = objectMapper.readValue(recordJSON, TakeWineRecord.class);
				//让取酒单永不过期
				logger.info("取酒码对应订单号:"+takeWineRecord.getOrderNumber());
				redisDao.cancelExpire(keys.get(0));
				JSONObject lockCode = new JSONObject();
				lockCode.put("lockTime", System.currentTimeMillis());
				lockCode.put("recordNumber", takeWineRecord.getRecordNumber());
				redisDao.put("LOCKTAKECODE_"+takeWineRecord.getTakeCode(),lockCode);
				logger.info("锁定取酒码:"+takeCode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//返回取酒码对应的取酒单
		return takeWineRecord;
	}
	@Override
	@BussLog(description="根据取酒码查询取酒记录")
	public TakeWineRecord queryRecordByTakeCode(String takeCode){
		//验证取酒码是否已被锁定
		
		List<String> keys = new ArrayList<>(redisDao.query("*_"+takeCode+"_*"));
		TakeWineRecord takeWineRecord = null;
		if(keys.size()>0){
			String recordJSON = redisDao.get(keys.get(0));
			try {
				takeWineRecord = objectMapper.readValue(recordJSON, TakeWineRecord.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//返回取酒码对应的取酒单
		return takeWineRecord;
	}
	@Override
	@BussLog(description="取酒码解锁")
	public void unLockTakeCode(String takeCode){
		
		redisDao.delete("LOCKTAKECODE_"+takeCode);
	}
	
	private String generateRecordNumber(){
		StringBuffer sb = new StringBuffer();
		String randomStr = RandomStringUtils.random(8, true, true);
		//取酒单号规则：TW开头 UNIX时间戳 8位字母数字组合随机字符串 共计20位
		sb.append("TW").append(System.currentTimeMillis()/1000).append(randomStr.toUpperCase());
		return sb.toString();
	}
	
	private String generateTakeCode(){
		String code = RandomStringUtils.random(6, false, true);
		boolean exists = redisDao.query("*_"+code+"_*").size()>0;
		if(exists){
			code = generateTakeCode();
		}
		return code;
	}
	
}
