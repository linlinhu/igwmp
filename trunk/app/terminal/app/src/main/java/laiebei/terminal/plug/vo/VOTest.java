package laiebei.terminal.plug.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import laiebei.terminal.exceptions.FaildVO;
import laiebei.terminal.plug.vo.bean.PayBean;
import laiebei.terminal.plug.vo.bean.RelationBean;
import laiebei.terminal.plug.vo.bean.ReplaceBean;
import laiebei.terminal.plug.vo.bean.WinesBean;

/**
 * Created by Administrator on 2017/4/17.
 */

public class VOTest {

    public static String getRelationInfo(){
        RelationInfoVO vo = new RelationInfoVO();
        RelationBean bean = new RelationBean();
        vo.setSuccess(true);
        bean.setName("来e杯测试机");
        bean.setCreatetime(System.currentTimeMillis());
        bean.setUpdatetime(System.currentTimeMillis());
        vo.setRows(bean);
        return JSON.toJSONString(vo);
    }

    public static String getWinesInfo(){
        WinesInfoVO vo = new WinesInfoVO();
        WinesBean bean0 = new WinesBean();
        WinesBean bean1 = new WinesBean();
        WinesBean bean2 = new WinesBean();
        WinesBean bean3 = new WinesBean();
        WinesBean bean4 = new WinesBean();
        WinesBean bean5 = new WinesBean();
        vo.setSuccess(true);
        bean0.setName("习酒特曲1");
        bean0.setDegress(54);
        bean0.setEnable(true);
        bean0.setFlavor("酱香型");
        bean0.setId(123456);
        bean0.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean0.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean0.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean0.setOriginalPrice(24.6);
        bean0.setSellingPrice(12.5);
        bean0.setRemainder(500);
        bean0.setProduct("贵州省习水县");

        bean1.setName("习酒特曲2");
        bean1.setDegress(54);
        bean1.setEnable(false);
        bean1.setFlavor("酱香型");
        bean1.setId(456789);
        bean1.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean1.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean1.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean1.setOriginalPrice(43.5);
        bean1.setSellingPrice(22.5);
        bean1.setRemainder(10);
        bean1.setProduct("贵州省习水县");

        bean2.setName("习酒特曲3");
        bean2.setDegress(53);
        bean2.setEnable(false);
        bean2.setFlavor("酱香型");
        bean2.setId(222222);
        bean2.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean2.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean2.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean2.setOriginalPrice(11.5);
        bean2.setSellingPrice(3.6);
        bean2.setRemainder(10);
        bean2.setProduct("贵州省习水县");

        bean3.setName("习酒特曲4");
        bean3.setDegress(53);
        bean3.setEnable(true);
        bean3.setFlavor("浓香型");
        bean3.setId(3333333);
        bean3.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean3.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean3.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean3.setOriginalPrice(9.0);
        bean3.setSellingPrice(4.6);
        bean3.setRemainder(211);
        bean3.setProduct("贵州省习水县");

        bean4.setName("习酒特曲5");
        bean4.setDegress(29);
        bean4.setEnable(true);
        bean4.setFlavor("浓香型");
        bean4.setId(11111111);
        bean4.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean4.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean4.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean4.setOriginalPrice(12.0);
        bean4.setSellingPrice(5.6);
        bean4.setRemainder(15);
        bean4.setProduct("贵州省习水县");

        bean5.setName("习酒特曲5");
        bean5.setDegress(52);
        bean5.setEnable(true);
        bean5.setFlavor("董香型");
        bean5.setId(11111111);
        bean5.setDescribe("远离世俗，远离轻薄，远离娇柔的狐媚");
        bean5.setImgList("http://text.91lyb.com/download/res/images/logo.png");
        bean5.setImgDetails("http://text.91lyb.com/download/res/images/logo.png");
        bean5.setOriginalPrice(12.0);
        bean5.setSellingPrice(5.6);
        bean5.setRemainder(16);
        bean5.setProduct("贵州省习水县");
        List<WinesBean> lists = new ArrayList<WinesBean>();
        lists.add(bean0);
        lists.add(bean1);
        lists.add(bean2);
        lists.add(bean3);
        lists.add(bean4);
        lists.add(bean5);
        vo.setTotal(lists.size());
        vo.setRows(lists);
        return JSON.toJSONString(vo);
    }

    public static String getReplaces(){
        ReplaceVO vo = new ReplaceVO();
        ReplaceBean bean0 = new ReplaceBean();
        ReplaceBean bean1 = new ReplaceBean();
        ReplaceBean bean2 = new ReplaceBean();
        vo.setSuccess(true);
        bean0.setId(111111);
        bean0.setName("习酒大曲");
        bean0.setQuantity(5);
        bean0.setEnable(true);
        bean0.setCode(123456);
        bean0.setTable("1号桌");

        bean1.setId(111111);
        bean1.setName("习酒大曲");
        bean1.setQuantity(2);
        bean1.setEnable(true);
        bean1.setCode(123454);
        bean1.setTable("2号桌");

        bean2.setId(111111);
        bean2.setName("珍酒");
        bean2.setQuantity(8);
        bean2.setEnable(false);
        bean2.setCode(123454);
        bean2.setTable("10号桌");

        List<ReplaceBean> rows = new ArrayList<ReplaceBean>();
        rows.add(bean0);
        rows.add(bean1);
        rows.add(bean2);
        vo.setTotal(rows.size());
        vo.setRows(rows);
        return JSON.toJSONString(vo);
    }

    public static String getPay(){
        PayVO vo = new PayVO();
        PayBean bean = new PayBean();
        vo.setSuccess(true);
        bean.setPay(24.1);
        bean.setType(0);
        bean.setPayQr("rqrqerqr");
        vo.setRows(bean);
        return JSON.toJSONString(vo);
    }

    public static String getResult(){
        FaildVO vo = new FaildVO();
        vo.setSuccess(true);
        vo.setCode(0);
        return JSON.toJSONString(vo);
    }
}
