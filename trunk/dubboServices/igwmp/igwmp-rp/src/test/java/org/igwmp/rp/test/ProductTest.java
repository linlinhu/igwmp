package org.igwmp.rp.test;
 
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import com.emin.igwmp.rp.facade.accepters.RestaurantReportAccepter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-servlet.xml"})
public class ProductTest {

	@Autowired
	RestaurantReportAccepter restaurantReportAccepter;
//	@Test
//	public void testDeleteCategory(){
// 
//	}
	@Test
	public void testReport(){
		JSONArray json = restaurantReportAccepter.findSaleRandRanking(0L, 0L, 0L);
		for (Object object : json) {
			JSONObject item = JSONObject.fromObject(object);
			
			for (Object key : item.keySet()) {
				System.out.println(key.toString()+" :  " + item.getString(key.toString()));
			}
		}
	}
}
