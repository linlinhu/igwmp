package com.emin.igwmp.rstm.util;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
	
	/**
	 * 根据上一个合同编号生成新的编号
	 * @param preNum
	 * @return
	 */
	public static String gernerateRgstNumByPreNum(boolean isSigned,String preNum){
		//int preNumLength = preNum.length();
		String suffixStr ="";
		if(StringUtils.isNoneBlank(preNum)){
			int prefixIndex = preNum.lastIndexOf("-");
			System.out.println("prefixIndex =" +prefixIndex);
			suffixStr = preNum.substring(prefixIndex+1);
			System.out.println("suffixStr =" +suffixStr);
			int currentNum = Integer.parseInt(suffixStr);
			System.out.println("currentNum =" +currentNum);
			currentNum++;
			System.out.println("after selfplus currentNum =" +currentNum);
			suffixStr = String.valueOf(currentNum);
			int suffixStrLen = suffixStr.length();
			switch(suffixStrLen){
			case 1:
				suffixStr = "0000"+suffixStr;
				break;
			case 2:
				suffixStr = "000"+suffixStr;
				break;
			case 3:
				suffixStr = "00"+suffixStr;
				break;
			case 4:
				suffixStr = "0"+suffixStr;
				break;
			default:
				break;			   
			}
			System.out.println("final suffixStr = " +currentNum);			
		}
		else{
			System.out.println("preNum start from 00001");
			suffixStr = "00001";				
		}   
		String isSignedFlag = isSigned?"Y":"N";
		System.out.println("The rgstNum = "+"DJ-"+ isSignedFlag + "-" +suffixStr);
		return "DJ-"+ isSignedFlag + "-" +suffixStr;
	}
	
	public static void main(String[] args){		
		gernerateRgstNumByPreNum(true,"DJ-Y-03467");
	}

}
