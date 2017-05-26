package laiebei.terminal.application;

import android.content.Context;

import com.laiebei.terminal.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/26 15:46.
 * 对外接口:
 */

public class Config {
	public static final String APP_DIR = "laiebei";
	public static final String CRASH = APP_DIR + "/crash";
	public static final String NOTE = APP_DIR + "/log";
	public static final String RES = APP_DIR + "/res";
	public static final String CACHE = APP_DIR + "/cache";

	/*******运维日志*******/
	public static final String IMPORT_NOTE = "import-";
	public static final String ALL_NOTE = "all-";

	public static Properties getProperties(Context context){
		Properties pro = new Properties();
		InputStream is = context.getResources().openRawResource(R.raw.config);
		try {
			pro.load(is);
			return pro;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}
}
