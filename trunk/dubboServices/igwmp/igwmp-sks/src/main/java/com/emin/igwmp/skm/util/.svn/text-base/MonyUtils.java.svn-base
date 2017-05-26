package com.emin.igwmp.skm.util;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by Administrator on 2017/4/24.
 */
public class MonyUtils {
    private MonyUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 使用NumberFormat,保留小数点后两位
     */
    public static String format(double value) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		/*
		 * setMinimumFractionDigits设置成2
		 *
		 * 如果不这么做，那么当value的值是100.00的时候返回100
		 *
		 * 而不是100.00
		 */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
		/*
		 * 如果想输出的格式用逗号隔开，可以设置成true
		 */
        nf.setGroupingUsed(false);
        return nf.format(value);
    }
}
