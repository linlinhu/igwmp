package com.emin.wxs.facade.prds.impl;
 
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.facade.accepter.ProductPriceAccepter;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.domain.Product; 
import com.emin.igwmp.prds.domain.Taste;
import com.emin.igwmp.prds.domain.WineTaster;
import com.emin.igwmp.prds.facade.accepters.ImageAccepter;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.prds.facade.accepters.TasteAccepter;
import com.emin.igwmp.prds.facade.accepters.WineTasterAccepter;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.wxs.facade.prds.WxsToProductCaller;
import com.emin.wxs.vo.PCProductVO; 
import com.emin.wxs.vo.ProductInfoVO;
import com.emin.wxs.vo.ProductVO;
import com.emin.wxs.vo.TasteProductVO;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
 

/** 
 * 商品
 * @author Administrator
 *
 */
@Component("wxsToProductCaller")
public class WxsToProductCallerImpl implements WxsToProductCaller {
	
	
	
	@Reference(version="0.0.1")
	private ProductAccepter productAccepter;

	@Reference(version="0.0.1")
	private WineTasterAccepter wineTasterAccepter;

	@Reference(version="0.0.1")
	private TasteAccepter tasteAccepter;
	
	@Reference(version="0.0.1")
	private ProductReportAccepter productReportAccepter;

	@Reference(version="0.0.1")
	private ProductPriceAccepter productPriceAccepter;
	
	@Reference(version="0.0.1")
	private ImageAccepter imageAccepter;
	 

	//查找商品
	
	@Override
	public PagedResult<ProductVO> loadPagedProductsByCondition(PageRequest req, List<Condition> conditions){
		PagedResult<Product> products = productAccepter.loadPagedProductsByCondition(req,conditions);
		List<ProductVO> vos = new ArrayList<>();
		for(Product product : products.getResultList()){
			ProductVO vo = ProductVO.productToVO(product);
			ProductPrice pp = productPriceAccepter.getProductPrice(product.getId(), 0L);
			
			if(null!=pp){
				vo.setRealPrice(pp.getOriginalPrice());
				vo.setDiscountedPrice(pp.getSellingPrice());
			}
			List<Images> images = imageAccepter.findImages(product.getId(), Images.PROP_SCOPE_PRODUCT_LIST);
			if(null!=images&&images.size()>0){
				Images img = images.get(0);
				vo.setProductImgUrl(img.getUrl());
			}
			vos.add(vo); 
		}
		PagedResult<ProductVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
		return result;
	}
	
	//删除商品
	@Override
	public void deleteProduct(Long id) { 
		productAccepter.deleteProduct(id);
		ProductPrice pp = productPriceAccepter.getProductPrice(id, 0L);
		if(null!=pp){
			productPriceAccepter.deleteProductPrice(pp.getId());
		}
	}
	

	@Override
	public void saveOrUpdateProduct(ProductVO productvo) {
		//to do vo to po
		//productAccepter.saveOrUpdateProduct(productvo.convertToProduct());
	}

	private Taste getTaste(Long productId){
		List<Condition> conditions =new ArrayList<>();
		Condition condition = new Condition(Taste.PROP_PRODUCT_ID, ConditionOperator.EQ, ConditionType.OTHER, productId);
		conditions.add(condition);
		List<Taste> list= tasteAccepter.findTastes(conditions);
		if(null!=list&&list.size()>0)return list.get(0);
		return null;
	}
	
	@Override
	public PagedResult<PCProductVO> loadPagedPCProductsByCondition(PageRequest req, List<Condition> conditions) {
		PagedResult<Product> products = productAccepter.loadPagedProductsByCondition(req,conditions);
		List<PCProductVO> vos = new ArrayList<>();
		for(Product product : products.getResultList()){
			PCProductVO pcvo =new PCProductVO();
			pcvo = PCProductVO.productToVO(product);
			Taste taste = getTaste(product.getId());
			if(null!=taste){
				pcvo.setMasterId(taste.getId());
				pcvo.setMasterName(taste.getName());
			}
			//extjs
			List<Images> listImages = imageAccepter.findImages(product.getId(), Images.PROP_SCOPE_PRODUCT_LIST);
			if(null!=listImages&&listImages.size()>0){
				Images img = listImages.get(0);
				pcvo.setProductListImgs(img.getUrl());
			}
			//手机
			List<Images> cellphoneImages = imageAccepter.findImages(product.getId(), Images.PROP_SCOPE_PRODUCT_DETAIL);
			if(null!=cellphoneImages&&cellphoneImages.size()>0){
				Images img = cellphoneImages.get(0);
				pcvo.setProductDetailImgs(img.getUrl());
			}
			//终端
			List<Images> mobileImages = imageAccepter.findImages(product.getId(), Images.PROP_SCOPE_TERMINAL_LIST);
			if(null!=mobileImages&&mobileImages.size()>0){
				Images img = mobileImages.get(0);
				pcvo.setMobileImgs(img.getUrl());
			}
			vos.add(pcvo);
		}
		PagedResult<PCProductVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
		return result;
	}

	@Override
	public PagedResult<ProductInfoVO> loadPagedProductInfosByCondition(PageRequest req, List<Condition> conditions) {
		PagedResult<Product> products = productAccepter.loadPagedProductsByCondition(req,conditions);
		List<ProductInfoVO> vos = new ArrayList<>();
		for(Product product : products.getResultList()){
			ProductInfoVO vo = ProductInfoVO.productToVO(product);
			ProductPrice pp = productPriceAccepter.getProductPrice(product.getId(), 0L);
			if(null!=pp){
				vo.setRealPrice(pp.getOriginalPrice());
				vo.setDiscountedPrice(pp.getSellingPrice());
			}
			List<Images> images = imageAccepter.findImages(product.getId(), Images.PROP_SCOPE_PRODUCT_LIST);
			if(null!=images&&images.size()>0){
				Images img = images.get(0);
				vo.setProductImgUrl(img.getUrl());
			}
			Taste taste = getTaste(product.getId());
			if(null!=taste){ 
				WineTaster wt = wineTasterAccepter.getWineTaster(taste.getWineTasterId());
				vo.setExpertImgUrl(wt.getUrl());
				vo.setExpertName(taste.getName());
				vo.setExpertProfession(wt.getDescription());
			}
			vos.add(vo); 
		}
		PagedResult<ProductInfoVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
		return result;
	}

	@Override
	public void saveOrUpdateProduct(String product) {
		JSONObject json = JSONObject.fromObject(product);
		Product p = productAccepter.saveProduct(product);
		//Long pID = p.getId();
		Long productId = json.optLong("productId");
		/*	Taste taste = new Taste();
		taste.setProductId((long) 12222);
		taste.setWineTasterId(json.optLong("masterID"));
		taste.setName(json.optString("masterName"));
		tasteAccepter.saveOrUpdate(taste);*/
		
		if(null==productId||productId<=0){
			ProductPrice pp =new ProductPrice();
			pp.setCreateTime(System.currentTimeMillis());
			pp.setLastModifyTime(System.currentTimeMillis());
			pp.setAreaId(0L);
			pp.setOriginalPrice(0D);
			pp.setPurchPrice(0D);
			pp.setSellingPrice(0D);
			pp.setProductId(p.getId());
			pp.setProductName(p.getName()); 
			pp.setStatus(ProductPrice.STATUS_VALID);
			pp.setRunType(p.getCategoryId());
			productPriceAccepter.saveOrUpdateProductPrice(pp);
		}

	}
	
	
	
	@Override
	public PagedResult<TasteProductVO> loadPagedTasteProductsByCondition(PageRequest req, List<Condition> conditions) {
		PagedResult<Product> products = productAccepter.loadPagedProductsByCondition(req,conditions);
		List<TasteProductVO> vos = new ArrayList<>();
		for(Product product : products.getResultList()){
			TasteProductVO tvo =new TasteProductVO();
			tvo = TasteProductVO.productToVO(product);
			Taste taste = getTaste(product.getId());
			if(null!=taste){
				tvo.setId(taste.getId());
				tvo.setMasterId(taste.getWineTasterId());
				tvo.setMasterName(taste.getName());
				tvo.setDescription(taste.getDescription());
			}
			vos.add(tvo);
		}
		PagedResult<TasteProductVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
		return result;
	}

	@Override
	public ProductInfoVO getProductInfoForId(Long productId) {
		// TODO Auto-generated method stub
		Product pro = productAccepter.getProductById(productId);
		ProductInfoVO infoVo = new ProductInfoVO();
		ProductInfoVO vo = ProductInfoVO.productToVO(pro);
		ProductPrice pp = productPriceAccepter.getProductPrice(pro.getId(), 0L);
		if(null!=pp){
			vo.setRealPrice(pp.getOriginalPrice());
			vo.setDiscountedPrice(pp.getSellingPrice());
		}
		List<Images> images = imageAccepter.findImages(pro.getId(), Images.PROP_SCOPE_PRODUCT_LIST);
		if(null!=images&&images.size()>0){
			Images img = images.get(0);
			vo.setProductImgUrl(img.getUrl());
		}
		Taste taste = getTaste(pro.getId());
		if(null!=taste){ 
			WineTaster wt = wineTasterAccepter.getWineTaster(taste.getWineTasterId());
			vo.setExpertImgUrl(wt.getUrl());
			vo.setExpertName(taste.getName());
			vo.setExpertProfession(wt.getDescription());
		}
		return vo;
	}

	@Override
	public JSONObject getProcductSaleAmount(Long productId) {
		return productReportAccepter.getProcductSaleAmount(productId);
	}
	
}
