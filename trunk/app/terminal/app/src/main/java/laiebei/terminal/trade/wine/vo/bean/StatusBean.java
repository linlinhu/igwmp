package laiebei.terminal.trade.wine.vo.bean;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/22 19:26.
 * 对外接口:
 */
public class StatusBean {
	private int status;
	private int channel;
	private int value;
	private String orderId;
	private int actutalValue;
	private int resultCode;
	private Long productId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getActutalValue() {
		return actutalValue;
	}

	public void setActutalValue(int actutalValue) {
		this.actutalValue = actutalValue;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
