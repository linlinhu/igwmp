package laiebei.terminal.exceptions;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/7.
 */

public class ThrowException extends Exception {
    private final String code;
    private final String arguments;

    public ThrowException(String code, String arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    public void printStackTrace() {
        Timber.e(code + ":" + this.arguments );
    }
}
