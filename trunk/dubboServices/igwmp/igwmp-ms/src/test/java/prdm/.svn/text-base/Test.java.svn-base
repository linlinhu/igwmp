package prdm;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.igwmp.ms.core.InitRun;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineControl;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.service.MachineChannelService;
import com.emin.igwmp.ms.service.MachineControlService;
import com.emin.igwmp.ms.service.MachineInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;

/**
 * user : shimingliang
 * date : 2017/3/17
 * time : 16:45
 * des :
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(InitRun.class);


    public static void query(ClassPathXmlApplicationContext c) {



        MachineInfoService machineInfoService = ((MachineInfoService) c.getBean("machineInfoService"));
        MachineControlService controlService = ((MachineControlService) c.getBean("machineControlService"));
        MachineChannelService channelService = ((MachineChannelService) c.getBean("machineChannelService"));

        logger.info("设备新增更新测试Start");

        MachineInfo machineInfo = new MachineInfo();
        machineInfo = machineInfoService.findById(46L);

        java.util.List<MachineChannel> channelList = new ArrayList<MachineChannel>();

        PreFilter filters1 = null;
        PreFilter filters2 = null;
        filters1 = PreFilters.eq("machineInfoId",machineInfo.getId());
        filters2 = PreFilters.eq("status",1);

        channelList = channelService.findByPreFilter(PreFilters.and(filters1, filters2));

        MachineControl control = new MachineControl();
        control = controlService.findById(machineInfo.getMachineControl().getId());

//        machineInfo.setChannelList(channelList);
        machineInfo.setMachineControl(control);


        System.out.println("MachineChannel ====== "+channelList.toString());
        System.out.println("MachineInfo ====== "+machineInfo.toString());
        System.out.println("MachineControl ====== "+control.toString());

        logger.info("设备新增更新测试End");

    }

    public static void update(ClassPathXmlApplicationContext c) {
        MachineInfoService machineInfoService = ((MachineInfoService) c.getBean("machineInfoService"));
        MachineControlService controlService = ((MachineControlService) c.getBean("machineControlService"));
        MachineChannelService channelService = ((MachineChannelService) c.getBean("machineChannelService"));

        logger.info("设备新增更新测试Start");

        MachineInfo machineInfo = new MachineInfo();
        machineInfo = machineInfoService.findById(17L);

        machineInfo.getMachineControl().setBindTime(new Date().getTime());
        machineInfo.setMachineControl(machineInfo.getMachineControl());
        machineInfo.setChannelList(machineInfo.getChannelList());


        java.util.List<MachineChannel> channelList = new ArrayList<MachineChannel>();

        PreFilter filters1 = null;
        PreFilter filters2 = null;
        filters1 = PreFilters.eq("machineInfoId",machineInfo.getId());
        filters2 = PreFilters.eq("status",1);

        channelList = channelService.findByPreFilter(PreFilters.and(filters1, filters2));

//        MachineControl control = new MachineControl();
//        control = controlService.findById(machineInfo.getMachineControl().getId());

        machineInfo.setChannelList(channelList);
//        machineInfo.setMachineControl(control);

        machineInfoService.addOrUpdateMachineInfo(machineInfo);

        logger.info("设备新增更新测试End");

    }


    public static void save(ClassPathXmlApplicationContext c) {
        logger.info("设备新增更新测试Start");

        MachineInfo machineInfo = new MachineInfo();
//		machineInfo.setId(8L);
        machineInfo.setCode("GZKSJJ-SDNI-12434");//设备资产编号‘
        machineInfo.setIpcCode("BE33334343434");
        machineInfo.setSimCode("3345567654323");
        machineInfo.setProductModel("T-232");
        machineInfo.setChannelSum(8);
        machineInfo.setLastModifyTime(new Date().getTime());
        machineInfo.setCreateTime(new Date().getTime());
        machineInfo.setStatus(1);
        machineInfo.setRestaurantName("索马里是明亮");
        machineInfo.setRestaurantId((long)Math.random()*100);


        java.util.List channelList = new ArrayList<MachineChannel>();

        for (int i = 0; i < 6; i++) {

            MachineChannel channel = new MachineChannel();
            channel.setMachineInfoId(machineInfo.getId());
            channel.setChannelNo(1);
            channel.setAllowance(30);
            channel.setAlarmValue(10);
            channel.setLiquorInfoId(1L);
            channel.setLiquorName("珍酒");
            channel.setSort(i + 1);
            channel.setLastModifyTime(new Date().getTime());
            channel.setCreateTime(new Date().getTime());
            channel.setStatus(1);
            channelList.add(channel);
        }


        MachineControl control = new MachineControl();
        control.setOnlineStatus(0);
        control.setRunType(3);
        control.setChanger(2);
        control.setOffTime(new Date().getTime());
        control.setOnTime(new Date().getTime());

        control.setLastModifyTime(new Date().getTime());
        control.setCreateTime(new Date().getTime());
        control.setStatus(1);


//        RestaurantPublicInfo restaurantPublicInfo = new RestaurantPublicInfo();
//        restaurantPublicInfo.setAddress("世纪金源购物中心");
////        restaurantPublicInfo.setAreaId("10011");
//        restaurantPublicInfo.setCuisine(1);
//        restaurantPublicInfo.setExplaination("树厨");
//        restaurantPublicInfo.setLatitude(10.334324324234);
//        restaurantPublicInfo.setLongitude(123.423324234);
//        restaurantPublicInfo.setUsingScene(1);
//        restaurantPublicInfo.setPhone("13823242423");
//        restaurantPublicInfo.setName("树厨");

//		machineInfo.setRestaurantPublicInfo(restaurantPublicInfo);
        machineInfo.setChannelList(channelList);
        machineInfo.setMachineControl(control);


        ((MachineInfoService) c.getBean("machineInfoService")).saveOrUpdate(machineInfo);
//        ((MachineControlService) c.getBean("machineControlService")).saveOrUpdate(control);

        logger.info("设备新增更新测试End id==="+machineInfo.getId());

    }


    public static void main(String[] args) {


    }

}
