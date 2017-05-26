package laiebei.terminal.common.cache.ram;


import laiebei.terminal.common.cache.CacheUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;

/**
 * Created by Administrator on 2017/4/6.
 */

public class RamLruCache <V> {

    public static RamLruCache INSTANCE = new RamLruCache();
    private LruCache<String, V> cache;

    private RamLruCache(){
        cache = new LruCache(10);
    }

    public   V getCache( String key) throws RunException{
        if(EmptyUtils.isEmpty(key)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        return  cache.get(CacheUtils.hashKey(key));
    }

    public   V saveCache( String key, V Values) {
        if(EmptyUtils.isEmpty(key) || EmptyUtils.isEmpty(Values)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        return cache.put(CacheUtils.hashKey(key), Values);
    }

    public V remove(String key){
        if(EmptyUtils.isEmpty(key)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        return cache.remove(CacheUtils.hashKey(key));
    }

    public void clear(){
        cache.clear();
    }



}
