
package net.mingsoft.cms.biz;

import java.util.List;
import java.util.Map;

import net.mingsoft.basic.biz.IBasicBiz;
import net.mingsoft.cms.bean.ColumnArticleIdBean;
import net.mingsoft.cms.entity.ArticleEntity;
import net.mingsoft.mdiy.entity.ContentModelEntity;


/**
 * 
 * @ClassName:  IArticleBiz   
 * @Description:TODO(文章管理业务处理层 || 继承IBasicBiz业务处理层)   
 * @author: shjpgli
 * @author: 2019年5月31日 下午2:53:32   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
public interface IArticleBiz extends IBasicBiz {

	/**
	 * @para webId 网站编号
	 * @param basicCategoryId
	 *            栏目编号
	 * @param flag
	 *            文章属性
	 * @param noFlag
	 *            文章不存在的属性
	 * @param article
	 *            文件实体
	 * @return
	 */
	@Deprecated
	int count(int webId, int[] basicCategoryId, String flag, String noFlag, ArticleEntity article);


	/**
	 * 通过分类id获取文章内容
	 * 
	 * @param categoryId
	 *            分类编号
	 * @return
	 */
	@Deprecated
	public ArticleEntity getByCategoryId(int categoryId);

	/**
	 * 通过视图表来查询文章总数
	 * 
	 * @param basicId
	 *            文章编号
	 */
	public ArticleEntity getById(int basicId);


	/**
	 * 查找basicId下一篇文章
	 * 
	 * @param appId
	 *            应用编号
	 * @param basicId
	 *            文章编号
	 * @return
	 */
	@Deprecated
	public ArticleEntity getNext(int appId, int basicId, Integer categoryId);

	/**
	 * 查找basicId上一篇文章
	 * 
	 * @param appId
	 *            应用编号
	 * @param basicId
	 *            文章编号
	 * @return
	 */
	@Deprecated
	public ArticleEntity getPrevious(int appId, int basicId, Integer categoryId);

	/**
	 * 高级查询接口，主要提供给有自定义模型的栏目，返回總數
	 * 
	 * @param contentModel
	 *            自定义模型
	 * @param whereMap
	 *            條件
	 * @param appId
	 *            appId 應用編號
	 * @param ids
	 *            子类id
	 * @return 记录数量
	 * @see IArticleBiz.count
	 */
	public int getSearchCount(ContentModelEntity contentModel, Map whereMap, int appId, String ids);

	/**
	 * 文章查询
	 * 
	 * @para webId 网站编号
	 * @param basicCategoryIds
	 *            栏目编号集合
	 * @param flag
	 *            文章属性
	 * @param noFlag
	 *            文章不存在的属性
	 * @param orderBy
	 *            排序字段
	 * @param order
	 *            true 升序 false 降序 排序方式
	 * @param beginTime 开始时间    
	 * @param endTime 结束时间                
	 * @param article
	 *            文章实体，便于扩展查询
	 * @return 文章集合
	 */
	List<ArticleEntity> query(int webId, int[] basicCategoryIds, String flag, String noFlag, String orderBy,
			boolean order, String beginTime,String endTime, ArticleEntity article);

	/**
	 * 查询文章编号集合
	 * @param categoryId 栏目编号
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public List<ColumnArticleIdBean> queryIdsByCategoryIdForParser(int categoryId, String beginTime,String endTime);

	/**
	 * 根据页面栏目的id获取与其绑定的文章实体
	 * 
	 * @param basicCategoryId
	 * @return 文章实体
	 */
	@Deprecated
	public List<ArticleEntity> queryListByColumnId(int basicCategoryId);




	/**
	 * 高级查询接口，主要提供给有自定义模型的栏目，
	 * 
	 * @param conntentModel
	 *            自定义模型
	 * @param whereMap
	 *            條件
	 * @param page
	 *            分頁
	 * @param appId
	 *            應用編號
	 * @param ids
	 *            子类id
	 * @return 记录集合
	 */
	@Deprecated
	public List<ArticleEntity> queryListForSearch(ContentModelEntity conntentModel, Map whereMap,
			int appId, List ids, Map orders);

}