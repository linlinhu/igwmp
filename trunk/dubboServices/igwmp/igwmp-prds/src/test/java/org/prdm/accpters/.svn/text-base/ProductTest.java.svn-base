package org.prdm.accpters;
 

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emin.igwmp.prds.domain.Product; 
import com.emin.igwmp.prds.facade.accepters.CategoryAccepter;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.prds.facade.accepters.TasteAccepter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-servlet.xml"})
public class ProductTest {

	//@Reference(version="0.0.1")
	@Autowired
	@Qualifier("productAccepter")
	private ProductAccepter productAccepter;
	@Autowired
	@Qualifier("categoryAccepter")
	private CategoryAccepter categoryAccepter;
	
	@Autowired
	@Qualifier("tasteAccepter")
	private TasteAccepter tasteAccepter;
	
	@Test
	public void testDeleteCategory(){
		//categoryAccepter.deleteCategory(9L);
//		Taste taste =new Taste();
//		taste.setId(30L);
//		taste.setDescription("345345345");
//		taste.setName("222");
//		taste.setProductId(22L);
//		taste.setWineTasterId(2L);
//		tasteAccepter.saveOrUpdate(taste);
		List<Product> list = productAccepter.findProducts(null);
		for (Product product : list) {
			System.out.println(product.getName());
			if(null == product.getCategory()){
				System.out.println(product.getCategory().getName());
			}
		}
	}
	
//	@Test
//	public void testloadOperations(){
// 
//		String json  = "{name:'五粮液',categoryId:1,categoryName:'测试',wineryId:1,wineryName:'茅台酒厂',flavorTypes:'酱香',degree:50.26,number:'12312312'"
//				+ ", unit:'瓶',spec:'5*12',capacity:'500ml',description:'这个没有什么好说的'"
//				+ ",productListImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}]"
//				+ ",productDetailImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}]"
//				+ ",mobileImgs:[{name:'测试图片',url:'http://pic.qiushibaike.com/system/pictures/11883/118836708/medium/app118836708.jpg',description:'但是开发和快乐的事富华大厦发'}]}";
//		Product product  = productAccepter.saveProduct(json);
//		System.out.println(product.getId());
//	}
	 
//	@Test
//	public void test(){
//		ProductDetail pd = productAccepter.getProductDetail(1L);
//		System.out.println(pd.getDescription());
//	}
 

}
