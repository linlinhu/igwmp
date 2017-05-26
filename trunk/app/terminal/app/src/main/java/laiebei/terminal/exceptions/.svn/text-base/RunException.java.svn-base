package laiebei.terminal.exceptions;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/26 14:14.
 * 对外接口:
 */

public class RunException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public static final String PROPERTIES_FILE_NAME = "exception";
	public static final String EXCEPTION_KEY_PREFIX = "exception.code.";
	private final String code;
	private final Object[] arguments;

	public RunException(String code, Object... arguments) {
		this.code = code;
		this.arguments = arguments;
	}

	public RunException(Throwable e, String code, Object... arguments) {
		super(e);
		this.code = code;
		this.arguments = arguments;
	}


	public String getCode() {
		return this.code;
	}


	public void printStackTrace() {
		StringBuffer msg = new StringBuffer();
		StackTraceElement[] var5;
		int var4 = (var5 = this.getStackTrace()).length;

		for(int var3 = 0; var3 < var4; ++var3) {
			StackTraceElement ele = var5[var3];
			msg.append("FileName:" + ele.getFileName() + ",ClassName:" + ele.getClassName() + ",MethodName:" + ele.getMethodName() + ",LineNumber:" + ele.getLineNumber() + ";");
		}

	}
}
