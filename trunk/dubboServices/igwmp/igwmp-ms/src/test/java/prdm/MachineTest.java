package prdm;

import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineControl;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.service.MachineInfoService;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;

/**
 * user : shimingliang
 * date : 2017/3/17
 * time : 10:43
 * des :
 */
public class MachineTest extends BaseJunit4Test {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineInfoService machineInfoService;


    @Test
    public void testSaveOrUpdate() {
        logger.info("设备新增更新测试Start");

        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setCode("GZKSJJ-SDNI-12434");//设备资产编号‘
        machineInfo.setIpcCode("BE33334343434");
        machineInfo.setSimCode("3345567654323");
        machineInfo.setProductModel("T-232");
        machineInfo.setChannelSum(8);
        machineInfo.setLastModifyTime(new Date().getTime());
        machineInfo.setCreateTime(new Date().getTime());
        machineInfo.setStatus(1);


        java.util.List channelList = new ArrayList<MachineChannel>();

        for (int i = 0; i < 6; i++) {

            MachineChannel channel = new MachineChannel();
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
//        control.setRunStatus(3);
        control.setChanger(2);
//        control.setOffDate(new Date().getTime());
//        control.setOnDate(new Date().getTime());

        control.setLastModifyTime(new Date().getTime());
        control.setCreateTime(new Date().getTime());
        control.setStatus(1);


        RestaurantPublicInfo restaurantPublicInfo = new RestaurantPublicInfo();
        restaurantPublicInfo.setAddress("世纪金源购物中心");
//        restaurantPublicInfo.setAreaId("10011");
        restaurantPublicInfo.setCuisine(1);
        restaurantPublicInfo.setExplaination("树厨");
        restaurantPublicInfo.setLatitude(10.334324324234);
        restaurantPublicInfo.setLongitude(123.423324234);
        restaurantPublicInfo.setUsingScene(1);
        restaurantPublicInfo.setPhone("13823242423");
        restaurantPublicInfo.setName("树厨");

//        machineInfo.setRestaurantPublicInfo(restaurantPublicInfo);
        machineInfo.setMachineControl(control);
        machineInfo.setChannelList(channelList);

        machineInfoService.addOrUpdateMachineInfo(machineInfo);

        logger.info("设备新增更新测试End");

    }
}
