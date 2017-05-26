package laiebei.terminal.common.cache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import laiebei.terminal.common.utilcode.ConvertUtils;

/**
 * Created by Administrator on 2017/4/6.
 */

public class CacheUtils {

    public static String hashKey(String key) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        String timeKey = key +format;
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(timeKey.getBytes());
            cacheKey = ConvertUtils.bytes2HexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(timeKey.hashCode());
        }
        return cacheKey;
    }
}
