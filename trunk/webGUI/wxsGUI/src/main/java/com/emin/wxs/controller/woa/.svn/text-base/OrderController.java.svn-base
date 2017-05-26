package com.emin.wxs.controller.woa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.emin.base.exception.EminException;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.WxOfficialAccountFacade;
import com.emin.wxs.facade.trading.callers.WxsToOrderCaller;
import com.emin.wxs.facade.trading.callers.WxsToPaymentCaller;
import com.emin.wxs.facade.trading.callers.WxsToTakeWineCaller;
import com.emin.wxs.util.DateUtil;
import com.emin.wxs.util.SMSJSONResult;
import com.emin.wxs.util.SmsUtil;
import com.emin.wxs.vo.trading.OrderItemVO;
import com.emin.wxs.vo.trading.OrderVO;
import com.emin.wxs.vo.trading.PaymentChannelVO;
import com.emin.wxs.vo.trading.TakeWineRecordVO;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/order")
public class OrderController extends WxsBaseController{

	@Autowired
	private WxsToOrderCaller wxsToOrderCaller;
	
	@Autowired
	private WxsToPaymentCaller WxsToPaymentCaller;
	
	@Autowired
	private WxsToTakeWineCaller wxsToTakeWineCaller;
	
	@Autowired
	private WxOfficialAccountFacade wxOfficialAccountFacade;
	
	@Reference(version="0.0.1")
	private ProductReportAccepter productReportAccepter;
	
	
	
	/**
	 * @param product
	 *            产品详情数据
	 * @return 用户产品详情
	 * @throws Exception 
	 */
	@RequestMapping("/create.do")
	public ModelAndView createOrder(String openId,
			Long productId,
			Double amount,
			Double price,
			Integer count,
			String productInfo
			) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/user/purchase_info");
		List<OrderItemVO> itemVOS = new ArrayList<>();
		OrderItemVO itemVO = new OrderItemVO();
		itemVO.setPrice(price);
		itemVO.setProductId(productId);
		itemVO.setAmount(amount);
		itemVO.setCount(count);
		itemVO.setProductInfo(JSONObject.fromObject(productInfo));
		itemVOS.add(itemVO);
		String orderNumber = wxsToOrderCaller.createOrderByFansOpenId(openId,itemVOS);
		List<PaymentChannelVO> channelVO = WxsToPaymentCaller.loadChannels();
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		mv.addObject("paymentChannels", channelVO);
		mv.addObject("orderNumber",orderNumber);
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	
	@RequestMapping("/takewine.do")
	@ResponseBody
	public ModelAndView queryOrder(String openId,int status) throws Exception {
		OrderStatus orderStatus = null;	

		if(status==1){
			orderStatus = OrderStatus.TOBETAKE;	
		}else{
			orderStatus = OrderStatus.TOOK;
		}
		ModelAndView mv = new ModelAndView("wxWebPage/user/take_wine");
		List<OrderVO> personalOrder = wxsToOrderCaller.loadPersonalOrder(openId,orderStatus);	
		
		JSONArray result = new JSONArray();
		if(null!=personalOrder&&personalOrder.size()>0){	
			for (OrderVO orderVO : personalOrder) {
				JSONObject item = new JSONObject();
				String orderNumber = orderVO.getOrderNumber();
				item.put("orderNumber", orderNumber);
				Double payedMoney = orderVO.getPayedMoney();				
				item.put("payedMoney", payedMoney);
				OrderItemVO orderItem =  orderVO.getItems().get(0);
				
				if(null!=orderItem) {				
					item.putAll(orderItem.getProductInfo());
				}

				TakeWineRecordVO takeWineRecordVO = wxsToTakeWineCaller.createOrGetTakeWineRecord(orderNumber,orderItem.getCount());			
				item.put("takeCode", takeWineRecordVO.getTakeCode());				
				item.put("creatTime", takeWineRecordVO.getCreateTime());
				item.put("codeExipreTime", takeWineRecordVO.getCodeExpireTime());			
				item.put("finishTime", takeWineRecordVO.getFinishTime());
				
				item.put("actualTakeMl", takeWineRecordVO.getActualTakeMl());//;//实际取酒量(ml)
				item.put("shouldTakeMl", takeWineRecordVO.getShouldTakeMl());//shouldTakeMl;//应该取酒量(ml)
				result.add(item);
			}		
		}
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		mv.addObject("status",status);
		mv.addObject("orderInfo",result);

		//mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	@RequestMapping("/porterCode.do")
	public ModelAndView porterCode(String orderNumber,int shouldTakeMl) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/user/porter_code");
		TakeWineRecordVO takeWineRecordVO = wxsToTakeWineCaller.createOrGetTakeWineRecord(orderNumber, shouldTakeMl);
		String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()
				+getRequest().getContextPath();
		String takeCode = takeWineRecordVO.getTakeCode();
		mv.addObject("basePath",basePath);
		mv.addObject("takeWineRecordVO",takeWineRecordVO);
		mv.addObject("orderNumber", orderNumber);
		String url = basePath+"/order/receiveOrder.do?orderNumber="+orderNumber+"&takeCode="+takeCode;
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));

		String urlLink =  wxsToOrderCaller.getGivingOrderUrl(Long.valueOf(mv.getModel().get("woaId").toString()), url);
		mv.addObject("urlLink",urlLink);
		return mv;
	}
	
	@RequestMapping("/rePorterCode.do")
	public ModelAndView rePorterCode(String orderNumber,int shouldTakeMl) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_porter_code");
		TakeWineRecordVO takeWineRecordVO = wxsToTakeWineCaller.createOrGetTakeWineRecord(orderNumber, shouldTakeMl);
		
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		mv.addObject("takeWineRecordVO",takeWineRecordVO);
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	
	@RequestMapping("/receiveOrder.do")
	public ModelAndView receiveOrder(String orderNumber,String takeCode) throws Exception {		
		ModelAndView mv = new ModelAndView("wxWebPage/user/detail_give");
		JSONObject item = new JSONObject();
		OrderVO detailInfo = wxsToOrderCaller.findOrderByNumber(orderNumber);
		Long vendeeId = detailInfo.getVendeeId();
		item.put("vendeeId", vendeeId);
		item.put("takeCode", takeCode);
		item.put("orderNumber", orderNumber);
		OrderItemVO orderItem=detailInfo.getItems().get(0);
		if(null!=orderItem) {				
			item.putAll(orderItem.getProductInfo());
		}
		String basePath = getBasePath();
        mv.addObject("productItem", item);
        
        mv.addObject("basePath",basePath);
        mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
        return mv;
	}
	
	@RequestMapping("/giveOrder.do")
	public JSONObject giveOrder(String orderNumber) throws Exception {		
	JSONObject json = new JSONObject();		
		
        String message = "";
        boolean success = false;
        try {
        	 wxsToOrderCaller.giveOrder(orderNumber);
        	 success = true;
        	 message = "赠送成功";
        	}
        catch (Exception e) {
            message = e.getMessage();
        }
        json.put("success", success);
        json.put("message", message);
        return json;
	}
	
	@RequestMapping("/cancelGiveOrder.do")
	public JSONObject cancelGiveOrder(String orderNumber) throws Exception {
		JSONObject json = new JSONObject();		
		
        String message = "";
        boolean success = false;	
        try {
        	 wxsToOrderCaller.giveOrder(orderNumber);
        	 success = true;
        	 message = "取消赠送";
        	}
        catch (Exception e) {
            message = e.getMessage();
        }
        json.put("success", success);
        json.put("message", message);
        return json;
	}
	
	@RequestMapping("/getGivingOrder.do")
	public JSONObject getGivingOrder(String orderNumber, Long receiverId) throws Exception {
		JSONObject json = new JSONObject();	
        String message = "";
        boolean success = false;
        try {
        	 wxsToOrderCaller.getGivingOrder(orderNumber,receiverId,null);
        	 success = true;
        	 message = "获取打酒码成功";
        	}
        catch (Exception e) {
            message = e.getMessage();
        }
        json.put("success", success);
        json.put("message", message);
        return json;
		
	}
	
}
