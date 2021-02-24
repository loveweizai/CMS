
package net.mingsoft.cms.constant.e;

import net.mingsoft.base.constant.e.BaseEnum;

/**
 * 
 * @ClassName:  ColumnTypeEnum   
 * @Description:TODO(文章栏目分类类型)   
 * @author: shjpgli
 * @author: 2019年5月31日 下午2:55:18   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public enum ColumnTypeEnum implements BaseEnum {
	/**
	 * 列表
	 */
	COLUMN_TYPE_LIST(1),
	/**
	 * 单页
	 */
	COLUMN_TYPE_COVER(2),
	/**
	 * 跳转地址
	 */
	COLUMN_TYPE_URL(3);
	ColumnTypeEnum(Object code) {
		this.code = code;
	}
	
	private Object code;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code+"");
	}
	
}