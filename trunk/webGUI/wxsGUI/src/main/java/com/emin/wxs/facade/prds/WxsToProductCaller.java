package com.emin.wxs.facade.prds;
 

import java.util.List; 
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.ProductDetail;
import com.emin.wxs.vo.PCProductVO;
import com.emin.wxs.vo.ProductInfoVO;
import com.emin.wxs.vo.ProductVO;
import com.emin.wxs.vo.TasteProductVO;

import net.sf.json.JSONObject;  
 

/**
 *  商品 
 */
public interface WxsToProductCaller {
	
	/**
	 * 调用平台服务保存用户
	 * @param PersonVO personVO
	 */	
	public void saveOrUpdateProduct(ProductVO productVO);
	/**
	 * 获取产品列表
	 * @param req
	 * @param conditions
	 * @return
	 */
	public PagedResult<ProductVO> loadPagedProductsByCondition(PageRequest req,List<Condition> conditions);
	 
	/**
	 * 获取产品列表
	 * @param req
	 * @param conditions
	 * @return
	 */
	public PagedResult<ProductInfoVO> loadPagedProductInfosByCondition(PageRequest req,List<Condition> conditions);
	 
	
	
	/**
	 * 获取后台PC端需要的对象
	 * @param req
	 * @param conditions
	 * @return
	 */
	public PagedResult<PCProductVO> loadPagedPCProductsByCondition(PageRequest req,List<Condition> conditions);
	
	void deleteProduct(Long id);
	/**
	 * 添加或者修改产品 参数如下
	 * @param product 
	 * { 
	 * productId:0,//0为添加 其它为修改
	 * productDetailId:0,//0为添加其它为修改
	 * name:'五粮液',
	 * categoryId:1,
	 * categoryName:'测试',
	 * wineryId:1,
	 * wineryName:'茅台酒厂',
	 * flavorTypes:'酱香',
	 * degree:50.26,
	 * number:'12312312', 
	 * unit:'瓶',
	 * spec:'5*12',
	 * capacity:'500ml',
	 * description:'这个没有什么好说的', //图片可以先不传
	 * productListImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}],"
	 * productDetailImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}],"
	 * mobileImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}]}";
	 */
	public void saveOrUpdateProduct(String product);
	
	PagedResult<TasteProductVO> loadPagedTasteProductsByCondition(PageRequest req, List<Condition> conditions);
 
	public ProductInfoVO getProductInfoForId(Long productId);
	
	public JSONObject getProcductSaleAmount(Long productId);

}
