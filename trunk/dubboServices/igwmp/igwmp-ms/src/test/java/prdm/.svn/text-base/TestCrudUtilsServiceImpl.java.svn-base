package prdm;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineInfo;
import org.springframework.stereotype.Service;
import utils.impl.CrudUtilsServiceImpl;

import java.util.List;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
@Service("testCrudUtilsService")
public class TestCrudUtilsServiceImpl extends CrudUtilsServiceImpl<MachineInfo> implements TestCrudUtilsService {


    public void test(MachineInfo machineControl) {
        try {
            this.addOrUpdate(machineControl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PagedResult<Object> pageTest(PageRequest pageRequest, List<Condition> conditions) {

        StringBuffer stringBuffer = new StringBuffer();

        String con = "";
        if (conditions != null && conditions.size() > 0) {

//            con = " where " + ConditonBuilder.sqlCondition(conditions);

        }

        String sql = "select * from ms.machine_info" + con;

        return this.queryPageBySql(pageRequest, sql);
    }
}
