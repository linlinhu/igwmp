package laiebei.terminal.skm.msmanage.entity.bean;
import java.util.List;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/10 14:54.
 * 对外接口:
 */
public class BodyBean {


	/**
	 * code : 0
	 * type : 0
	 * rows : [{"rowId":0,"row":"具体业务消息实体(AES加密)"}]
	 * describe :
	 */

	private int code;
	private int type;
	private String describe;
	private List<RowsBean> rows;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<RowsBean> getRows() {
		return rows;
	}

	public void setRows(List<RowsBean> rows) {
		this.rows = rows;
	}

}
