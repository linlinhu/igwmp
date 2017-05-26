package prdm;

import com.emin.igwmp.ms.domain.MachineControl;
import com.emin.igwmp.ms.domain.MachineInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
public class CurdTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");
        c.start();


        MachineControl control = new MachineControl();
        control.setId(8L);
        control.setOnlineStatus(0);
        control.setRunType(3);
//        control.setChanger(2);
        control.setBindStatus(9);
//        control.setOffTime(new Date().getTime());
//        control.setOnTime(new Date().getTime());
//
//        control.setLastModifyTime(new Date().getTime());
//        control.setCreateTime(new Date().getTime());
        control.setStatus(-1);

        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setRestaurantName("");
        machineInfo.setId(6L);

        TestCrudUtilsService crudUtils = ((TestCrudUtilsService) c.getBean("testCrudUtilsService"));
//        crudUtils.test(machineInfo);

        crudUtils.queryPageBySql(null,"");
        System.in.read();

        TestBean testBean = new TestBean();
        testBean.setName("sml");
        testBean.setId(1L);
        testBean.setSex(5);

        Class<?>  aClass = testBean.getClass();

        String s = aClass.getName();

        Field[] strings = aClass.getFields();

        Field field = aClass.getDeclaredField("sex");


        field.setAccessible(true); //设置些属性是可以访问的
        System.out.println("name=="+aClass);

        System.out.println("field==="+field.get(testBean));
        System.out.println("type==="+field.getType().toString());

    }
}
