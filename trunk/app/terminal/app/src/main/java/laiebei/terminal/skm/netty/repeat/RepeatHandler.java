package laiebei.terminal.skm.netty.repeat;

import java.util.ArrayList;
import java.util.List;

import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.ThrowException;
import laiebei.terminal.skm.netty.io.nettyIO;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/7.
 */

public class RepeatHandler extends Thread{

    private List<String>  messageRepeat = new ArrayList<String>();


    public void add(String message){
        if(EmptyUtils.isNotEmpty(message)){
            messageRepeat.add(message);
        }
    }

    private String take()throws ThrowException {
        if(EmptyUtils.isEmpty(messageRepeat)){
            throw new ThrowException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        return messageRepeat.remove(0);
    }

    @Override
    public void run() {
        super.run();
        try {
            String message = "";
            while (!EmptyUtils.isNotEmpty(message = take())){
                Timber.w("导致重发的数据:" + message);
                nettyIO.write(message);
            }
        } catch (ThrowException e) {
            e.printStackTrace();
        }

    }
}
