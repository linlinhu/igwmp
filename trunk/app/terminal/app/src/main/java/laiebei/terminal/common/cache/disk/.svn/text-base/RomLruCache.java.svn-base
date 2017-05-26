package laiebei.terminal.common.cache.disk;

import android.content.Context;


import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import laiebei.terminal.common.cache.CacheUtils;
import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.common.utilcode.ConvertUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.mainten.logs.LogcatEngine;


/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2016/12/9 11:22.
 * 对外接口:
 */

public class RomLruCache {

    public static final long CHCHE_MAX_SIZE = 20*1024*1024;

//    private static RomLruCache mLruCacheApi;
    private static Context mContext;
    private static DiskLruCache mDiskLruCache = null;

    public static RomLruCache INSTANCE;

    public static synchronized  void init(Context context){
        mContext = context;
        INSTANCE = new RomLruCache(mContext);
    }

    private  void cacheInit(Context context){
        File cacheDir = LruCacheUtil.getDiskCacheDir(context);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, AppUtils.getAppVersionCode(context),1,CHCHE_MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加关键日志记录
        LogcatEngine.INSTANCE.addImportTag(RomLruCache.class.getSimpleName());
    }

    private RomLruCache(Context context){
        cacheInit(context);
    }


    //保存缓存
    public  void saveForDisk(String key, String value){
        String savekey = CacheUtils.hashKey(key);
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(savekey);
            editor.set(0,value);
            editor.commit();
            mDiskLruCache.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取缓存
    public  String getForDisk(String key){
        String savekey = CacheUtils.hashKey(key);
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(savekey);
            if(EmptyUtils.isNotEmpty(snapShot)){
                return snapShot.getString(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //删除key对应的缓存
    public  void removeCache(String key){
        String savekey = CacheUtils.hashKey(key);
        try {
            mDiskLruCache.remove(savekey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //清除所有缓存
    public  void resetCache(){
        if(EmptyUtils.isNotEmpty(mDiskLruCache)){
            try {
                mDiskLruCache.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
