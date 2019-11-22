package com.application.es.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME: Constant
 * @DESC:
 * @USER: 孤狼
 **/
public class Constant {
	/**
	 * 法定人身份证号
	 */
	public static  String[] legalNoArray = null;
	
	/**
	 * 初始数据.
	 */
	static {
		int  count = 1000;
		//计划指南id
		List<String> tmpList = new ArrayList<>();
		//法定人身份证号
		tmpList = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			tmpList.add("612326198509"+DataUtil.getNumber(6));
		}
		legalNoArray = DataUtil.getArray(tmpList);
		tmpList.clear();
	}
	
	/**
	 * 項目負責人
	 */
	public static  String[] projectManArray = new String[]{"张三","李四","王五","赵六","王二","和珅","李坤","赵琦","文忠","张靖","李笑恋","王京华","赵钱","王不二","乾龙","李尚卿","赵敏琦","吴培荣"};
	
	/**
	 * 項目申报人
	 */
	public static  String[] projectApplyManArray = new String[]{"李建中","王明","健达康","刘琦生","李宝林","城建","苏明","姜凯","廖文强","赵权","沈海","郭发刚","杜瑞华","薛岳文","钱伯君","许文强","冯程","张海","张强","白龙"};
	
	/**
	 * 经营范围
	 */
	public static  String[] busFirstScopArray = new String[]{"科技","文化","传媒广告","咨询","管理","商贸"};
	
	/**
	 * 地址
	 */
	public static  String[] addressArray = new String[]{"碑林区","莲湖区","灞桥区","雁塔区","阎良区","未央区","新城区","长安区","临潼区","咸阳市","铜川市","渭南市","延安市","榆林市","汉中市","安康市","商洛市","宝鸡市"};
	
	/**
	 * 主题类别
	 */
	public static  String[] applyArray = new String[]{"企业","高校","科研机构"};
	
}
