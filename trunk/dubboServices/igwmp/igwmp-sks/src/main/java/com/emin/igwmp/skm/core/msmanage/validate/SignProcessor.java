/**
 * 
 */
package com.emin.igwmp.skm.core.msmanage.validate;


import java.util.Arrays;

import com.emin.igwmp.skm.core.msmanage.crypt.Encrypt;

/**
 * @author limaocheng
 *
 */
public class SignProcessor {

	private static String privateKey = "lybprivateKey2017";

	/**
     * 功能描述: <br>
     * 〈生成待签名字符串〉
     *
     * @param arr
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String waitSignStr(String[] arr){
    	//待签名字符串排序
    	Arrays.sort(arr);
        String signStr = "";
        for(int i=0;i<arr.length;i++){
            if(i!=0){
                signStr = signStr+"&";
            }
            signStr = signStr+arr[i];
        }

        return signStr;
    }
    
    /**
     * 功能描述: <br>
     * 〈签名〉

     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String createSignStr(String[] arr){
    	String wStr = waitSignStr(arr);
        StringBuffer sb = new StringBuffer(wStr).append(privateKey);
        try {
            String strSign = Encrypt.encryptMD5ToString(sb.toString());
            return strSign;
        } catch (Exception e) {
        }
        return null;
    }
    
}
