package com.emin.igwmp.prds.facade.accepters.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException; 
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Category;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.ProductDetail;
import com.emin.igwmp.prds.domain.Winery;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.prds.service.CategoryService;
import com.emin.igwmp.prds.service.ImagesService;
import com.emin.igwmp.prds.service.ProductDetailService;
import com.emin.igwmp.prds.service.ProductService;
import com.emin.igwmp.prds.service.WineryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("productAccepter")
@Service(version="0.0.1")
public class ProductAccepterImpl implements ProductAccepter {

	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	@Autowired
	@Qualifier("imagesService")
	private ImagesService imagesService;
	@Autowired
	@Qualifier("productDetailService")
	private ProductDetailService productDetailService;
	@Autowired
	@Qualifier("wineryService")
	private WineryService wineryService;
	
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	@Override
	public Product saveProduct(String product) throws EminException { 
		JSONObject json = JSONObject.fromObject(product);

		 
		Product p =  getProduct(json);
		ProductDetail pd =  getProductDetail(json);


		String wineryName = json.optString("wineryName");
		String categoryName = json.optString("categoryName");

		setWinery(p, wineryName);
		setCategory(p, categoryName);
		productService.saveOrUpdate(p);
		pd.setProductId(p.getId());		
		productDetailService.saveOrUpdate(pd);
		
		deleteImages(p.getId());
		//1.产品列表2.产品详情3.终端列表4.终端详情5.大师品鉴图
		JSONArray productListImgs = json.getJSONArray("productListImgs");
		List<Images> productImgList = getImagesList(productListImgs);
		saveOrUpdateImages(productImgList, p.getId(), Images.PROP_SCOPE_PRODUCT_LIST);
		

		JSONArray productDetailImgs = json.getJSONArray("productDetailImgs");
		List<Images> productDetailImgList = getImagesList(productDetailImgs);
		saveOrUpdateImages(productDetailImgList, p.getId(),  Images.PROP_SCOPE_PRODUCT_DETAIL);
		

		JSONArray mobileImgs = json.getJSONArray("mobileImgs");
		List<Images> mobileImgList = getImagesList(mobileImgs);
		saveOrUpdateImages(mobileImgList, p.getId(),Images.PROP_SCOPE_TERMINAL_LIST);
		return p;
	}
	
	private Product getProduct(JSONObject json){
		Product product = null;
		Long id = json.optLong("productId");
		if(null!=id&&id>0){
			product = productService.findById(id);
		}else{
			product =new Product();
			product.setCreateTime(System.currentTimeMillis()); 
			product.setStatus(Product.STATUS_VALID); 
		}
		product.setLastModifyTime(System.currentTimeMillis());	
		product.setName(json.optString("name"));
		product.setCategoryId(json.optLong("categoryId"));
		product.setDegree(json.optDouble("degree"));
		product.setFlavorTypes(json.optString("flavorTypes"));
		product.setNumber(json.optString("number"));
		product.setWineryId(json.optLong("wineryId"));

		return product;
	}
	
	private ProductDetail getProductDetail(JSONObject json){
		ProductDetail productDetail = null;
		Long id = json.optLong("productDetailId");
		if(null!=id&&id>0){
			productDetail = productDetailService.findById(id);
		}else{
			productDetail =new ProductDetail();
			productDetail.setCreateTime(System.currentTimeMillis()); 
			productDetail.setStatus(Product.STATUS_VALID); 
		}
		productDetail.setLastModifyTime(System.currentTimeMillis());
		productDetail.setCapacity(json.optString("capacity"));
		productDetail.setDescription(json.optString("description"));
		productDetail.setUnit(json.optString("unit"));
		productDetail.setSpec(json.optString("spec"));

		return productDetail;
	}

	private void setCategory(Product p, String categoryName) {
		if(StringUtils.isEmpty(categoryName))return;
		List<Category> categories = categoryService.findByPreFilter(PreFilters.eq(Category.PROP_NAME, categoryName),wineryService.getStatusFilter());
		if(null!=categories&&categories.size()>0){
			p.setCategory(categories.get(0));
			p.setCategoryId(categories.get(0).getId());
		}else{
			Category category = new Category(); 
			category.setParentId(0L);
			category.setName(categoryName);
			category.setLastModifyTime(System.currentTimeMillis());
			category.setCreateTime(System.currentTimeMillis());
			category.setStatus(Winery.STATUS_VALID);
			categoryService.saveOrUpdate(category); 
			p.setCategory(category);
			p.setCategoryId(category.getId());
		}
	}

	private void setWinery(Product p, String wineryName) {
		if(StringUtils.isEmpty(wineryName))return;
		List<Winery> wineries= wineryService.findByPreFilter(PreFilters.eq(Winery.PROP_NAME, wineryName),wineryService.getStatusFilter());
		if(null!=wineries&&wineries.size()>0){
			p.setWinery(wineries.get(0));
			p.setWineryId(wineries.get(0).getId());
		}else{
			Winery winery = new Winery();
			winery.setDescription("");
			winery.setName(wineryName);
			winery.setLastModifyTime(System.currentTimeMillis());
			winery.setCreateTime(System.currentTimeMillis());
			winery.setStatus(Winery.STATUS_VALID);
			wineryService.saveOrUpdate(winery); 
			p.setWinery(winery);
			p.setWineryId(p.getId());
		}
	}

	private List<Images> getImagesList(JSONArray imgs) {
		if(null==imgs||imgs.size()<=0)return null;
		List<Images> imgList = new ArrayList<>();
		for (int i = 0; i < imgs.size(); i++) {
			JSONObject img = imgs.getJSONObject(i);
			Images image = new Images();
			image.setUrl(img.getString("url"));
			image.setName(img.optString("name"));
			image.setDescription(img.optString("description"));
			imgList.add(image);
		}
		return imgList;
	}

	 
	@Override
	public Product saveOrUpdateProduct(Product product) throws EminException {
		productService.saveOrUpdate(product);	
		return product;
	}

	@Override
	public void deleteProduct(Long id) throws EminException {
		productService.deleteById(id);		
	}

	
	@Override
	public PagedResult<Product> loadPagedProductsByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException {
		 
		return productService.loadPagedProductsByCondition(pageRequest, conditions);
	}

	@Override
	public PagedResult<Product> loadPagedProductsByMatch(PageRequest pageRequest, String... match)throws EminException {
		 
		return productService.loadPagedProductsByMatch(pageRequest, match);
	}
	
 
	@Override
	public ProductDetail saveOrUpdateProductDetail(ProductDetail pd) throws EminException {
		productDetailService.saveOrUpdate(pd);
		return pd;
	}

	@Override
	public void deleteProductDetail(Long productId) throws EminException {
		productDetailService.deleteById(productId);
		
	}

	@Override
	public List<ProductDetail> findProductDetail(Long[] productIds) throws EminException {
		 
		return productDetailService.findByPreFilter(PreFilters.in(ProductDetail.PROP_PRODUCT_ID, (Object[])productIds),
				PreFilters.eq(ProductDetail.PROP_STATUS, ProductDetail.STATUS_VALID)
				);
	}

	@Override
	public List<ProductDetail> findProductDetails() throws EminException {
		 
		return productDetailService.findByPreFilter(
				PreFilters.eq(ProductDetail.PROP_STATUS, ProductDetail.STATUS_VALID)
				);
	}
	
	@Override
	public ProductDetail getProductDetail(Long productId) throws EminException {
		 
		return productDetailService.findUniqueByPreFilter(PreFilters.eq(ProductDetail.PROP_PRODUCT_ID, productId));
	}

	@Override
	public void saveOrUpdateImages(List<Images> images, Long productId, Integer type) throws EminException {
		if(null==images)return;
		for (Images image : images) {
			image.setRelationId(productId);
			image.setScope(type);
			image.setCreateTime(System.currentTimeMillis());
			image.setLastModifyTime(System.currentTimeMillis());
			image.setStatus(Images.STATUS_VALID);
			imagesService.saveOrUpdate(image);
		}		
	}

	@Override
	public void deleteImages(Long productId) throws EminException {
		 
		imagesService.deleteImages(productId);
	}

	@Override
	public List<Images> findImages(Long productId, Integer type) throws EminException {
		 
		return imagesService.findImages(productId,type);
	}

	@Override
	public List<Product> findProducts(List<Condition> conditions) { 
		return productService.findProducts(conditions);
	}

	@Override
	public Images saveOrUpdateImage(Images img) throws EminException {
		imagesService.save(img);
		return img;
	}

	@Override
	public Product getProductById(Long productId) throws EminException {
		
		return productService.findById(productId);
	}

 
}
