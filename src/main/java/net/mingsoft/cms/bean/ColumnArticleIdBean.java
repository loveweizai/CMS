
package net.mingsoft.cms.bean;

import net.mingsoft.basic.entity.ColumnEntity;

/**
 * 文章解析生成
 * @author shjpgli
 * @date 2019年5月31日
 */
public class ColumnArticleIdBean extends ColumnEntity{
	/**
	 * 文章编号
	 */
	private int articleId;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
}