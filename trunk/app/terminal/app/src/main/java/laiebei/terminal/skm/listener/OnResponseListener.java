package laiebei.terminal.skm.listener;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface OnResponseListener {
    public void OnResult(String row);
    public void OnFail(String error);
}
