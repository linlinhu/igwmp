package com.emin.wxs.controller.weixin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.WxOfficialAccountFacade;
import com.emin.wxs.facade.prds.WxsToProductCaller;
import com.emin.wxs.facade.trading.callers.WxsToOrderCaller;
import com.emin.wxs.vo.ProductInfoVO;
import com.emin.wxs.vo.ProductVO;
import com.emin.wxs.vo.trading.OrderItemVO;
import com.emin.wxs.vo.trading.OrderVO;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/weixin")
public class ProductWxController extends WxsBaseController {
	
	
//private Logger logger = LoggerFactory.getLogger(ProductWxController.class);
	
	@Autowired
	@Qualifier("wxsToProductCaller")
	private WxsToProductCaller wxsToProductCaller;
	@Autowired
	private WxOfficialAccountFacade wxOfficialAccountFacade;
	
	@Autowired
	private WxsToOrderCaller wxsToOrderCaller;

	/**
	 * @param product
	 *            产品数据
	 * @return 用户产品首页
	 * @throws Exception 
	 */
	@RequestMapping("/list.htm")
	public ModelAndView list() throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/user/index");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		List<Condition> conditions = new ArrayList<>();
		PagedResult<ProductVO> productList= wxsToProductCaller.loadPagedProductsByCondition(getPageRequestData(), conditions);
		mv.addObject("product",productList);
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
//	@RequestMapping("/list.htm")
//	public ModelAndView list() throws Exception {
//		ModelAndView mv = new ModelAndView("wxWebPage/user/index");
//		String basePath = getBasePath();
//		mv.addObject("basePath",basePath);
//		List<Condition> conditions = new ArrayList<>();
//		PagedResult<ProductVO> productList= wxsToProductCaller.loadPagedProductsByCondition(getPageRequestData(), conditions);
//		List<ProductVO>products =  productList.getResultList();
//		JSONArray result = new JSONArray();
//		result.add(productList);
//		for (ProductVO productVO : products) {
//			JSONObject record = new JSONObject();
//			Long productId =  productVO.getId();
//			JSONObject productRecord = wxsToProductCaller.getProcductSaleAmount(productId);
//			record.putAll(productRecord);
//			result.add(record);
//		}		
//		mv.addObject("product",result);
//		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
//		return mv;
//	}
	
	/**
	 * @param product
	 *            产品详情数据
	 * @return 用户产品详情
	 * @throws Exception 
	 */
	@RequestMapping("/detail.htm")
	public ModelAndView detail(Long id) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/user/detail");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		List<Condition> conditions = new ArrayList<Condition>();
		if(null!=id){
			conditions.add(new Condition(Product.PROP_ID, ConditionOperator.EQ, ConditionType.CHARACTER, id));
		}
	
		PagedResult<ProductInfoVO> detailList= wxsToProductCaller.loadPagedProductInfosByCondition(getPageRequestData(), conditions);		
		mv.addObject("productInfo",detailList);
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	
	@RequestMapping("/personal.htm")
	public ModelAndView personal() throws Exception {	
		ModelAndView mv = new ModelAndView("wxWebPage/user/personal_order");
		String basePath = getBasePath();		
		mv.addObject("basePath",basePath);		
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	@RequestMapping("/contact.htm")
	public ModelAndView contact() throws Exception {	
		ModelAndView mv = new ModelAndView("wxWebPage/user/contact");
		String basePath = getBasePath();		
		mv.addObject("basePath",basePath);		
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	@RequestMapping("/record.htm")
	public ModelAndView record(String openId,int status) throws Exception {
		OrderStatus orderStatus = null;	
		if(status==1){
			orderStatus = OrderStatus.TOBETAKE;	
		}else{
			orderStatus = OrderStatus.TOOK;
		}
		ModelAndView mv = new ModelAndView("wxWebPage/user/take_wine_record");
		String basePath = getBasePath();
		List<OrderVO> personal = wxsToOrderCaller.loadPersonalOrder(openId,orderStatus);
		
		JSONArray result = new JSONArray();
		if(null!=personal&&personal.size()>0){	
			for (OrderVO orderVO : personal) {
				JSONObject item = new JSONObject();
				OrderItemVO orderItem =  orderVO.getItems().get(0);
				Long productId = orderItem.getProductId();
				if(null!=orderItem) {				
					item.put("productId",productId);
					item.putAll(orderItem.getProductInfo());
				    JSONObject productRecord = wxsToProductCaller.getProcductSaleAmount(productId);
				    item.putAll(productRecord);
				    result.add(item);
				}
			}		
		}
		
		mv.addObject("basePath",basePath);
		mv.addObject("productRecord",result);
		
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	
}


