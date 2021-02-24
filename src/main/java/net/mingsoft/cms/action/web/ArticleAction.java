
package net.mingsoft.cms.action.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import net.mingsoft.cms.dao.IArticleDao;
import net.mingsoft.mdiy.util.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.biz.IColumnBiz;
import net.mingsoft.basic.entity.CategoryEntity;
import net.mingsoft.basic.entity.ColumnEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.cms.biz.IArticleBiz;
import net.mingsoft.cms.constant.ModelCode;
import net.mingsoft.cms.entity.ArticleEntity;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import net.mingsoft.mdiy.biz.IContentModelBiz;
import net.mingsoft.mdiy.biz.IContentModelFieldBiz;
import net.mingsoft.mdiy.entity.ContentModelEntity;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName:  ArticleAction   
 * @Description:TODO 前段文章控制，如果标签不能满足可以使用这个控制来满足用户的查询文章需求，主要是通过ajax返回json数据格式   
 * @author: shjpgli
 * @author: 2019年5月31日 下午2:52:44   
 *     
 * @Copyright: 2018 www.mingsoft.net Inc. All rights reserved.
 */
@Controller("webArticle")
@RequestMapping("/mcms/article")
@Api(value = "文章管理接口", description = "文章管理接口")
public class ArticleAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 文章管理业务处理层
	 */
	@Autowired
	private IArticleBiz articleBiz;

	/**
	 * 栏目管理业务处理层
	 */
	@Autowired
	private IColumnBiz columnBiz;

	/**
	 * 内容模型管理业务处理层
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;

	/**
	 * 自定义字段管理业务处理层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;

	@Autowired
	private IArticleDao iArticleDao;
	/**
	 * 文章信息
	 * 
	 * @param basicId
	 *            文章编号
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {"basicCategoryId":分类编号,basicTitle
	 *            :"标题",basicDescription:"描述",basicThumbnails:"缩略图",
	 *            basicDateTime:"发布时间",basicUpdateTime:"更新时间","basicHit":点击数,
	 *            "basicId":编号 articleContent:"文章内容","basicSort":排序,[自定义模型字段]}
	 */
	@GetMapping("/{basicId}/detail")
	@ResponseBody
	@ApiOperation(value="文章详情信息", response = ArticleEntity.class)
	public void detail(@PathVariable int basicId, HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("ArticleAction-->detail:查询详情,basicId:{}", basicId);
			ArticleEntity article = articleBiz.getById(basicId);
			if (article == null) {
				//this.outJson(response, "");
				this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "文章信息不能为空")));
				//return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  article);
			}
			// 获取文章栏目id获取栏目实体
			ColumnEntity column = (ColumnEntity) columnBiz.getEntity(article.getBasicCategoryId());
			ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
					.getEntity(column.getColumnContentModelId());
	
			// 判断内容模型的值
			if (contentModel != null) {
				Map where = new HashMap();
				// 压入basicId字段的值
				where.put("basicId", basicId);
				// 遍历所有的字段实体,得到字段名列表信息
				List<String> listFieldName = new ArrayList<String>();
				listFieldName.add("basicId");
				// 查询新增字段的信息
				List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
				if (fieldLists.size() > 0) {
					Map map = (Map) fieldLists.get(0);
					article.setExtendsFields(map);
				}
			}
			this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  article)));
			//this.outJson(response, JSONObject.toJSONStringWithDateFormat(article, "yyyy-MM-dd hh:mm:ss"));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("ArticleAction-->detail:查询详情异常:", e);
			this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "查询详情异常")));
		}
	}


	/**
	 * 查询栏目绑定的文章信息
	 *
	 * @param request
	 * @return 修改文章的页面地址
	 */
	@GetMapping("/category/{id}/detail")
	@ResponseBody
	@ApiOperation(value="查询栏目绑定的文章信息", response = ArticleEntity.class)
	public void articleDetail(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		try {
			// 板块id
//			int categoryId = BasicUtil.getInt("categoryId", 0);
//			if(categoryId == 0){
//				this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "栏目ID不能为空")));
//			}
//			logger.info("ArticleAction-->detail:查询详情,categoryId:{}", categoryId);
			ArticleEntity article = articleBiz.getByCategoryId(id);
			if (article == null) {
				//this.outJson(response, "");
				this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "文章信息不能为空")));
				//return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  article);
			}
			this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "查询栏目绑定的文章信息成功",  article)));
			//this.outJson(response, JSONObject.toJSONStringWithDateFormat(article, "yyyy-MM-dd hh:mm:ss"));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("ArticleAction-->detail:查询栏目绑定的文章信息异常:", e);
			this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "查询栏目绑定的文章信息异常")));
		}
	}

	/**
	 * 文章列表信息
	 * 
	 * @param pageSize
	 *            一页显示数量
	 * @param pageNum
	 *            当前页码
	 * @param basicCategoryId
	 *            分类编号
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            {"list":"[{
	 *            "basicTitle":"标题",
	 *            "basicDescription":"描述",
	 *            "basicThumbnails":"缩略图",
	 *            "basicDateTime":"发布时间",
	 *            "basicUpdateTime":"更新时间",
	 *            "basicHit":点击数,
	 *            "basicId":编号,
	 *            "articleContent":文章内容,
	 *            "articleAuthor":文章作者
	 *      	  "articleType":文章属性,
	 *      	  "articleSource":文章的来源,
	 *      	  "articleUrl":文章跳转链接地址,
	 *      	  "articleKeyword":文章关键字,
	 *      	  "articleCategoryId":文章所属的分类Id,
	 *      	  "articleTypeLinkURL":文章分类url地址，主要是用户生成html使用,
	 *            "order":"排序方式",
	 *            "orderBy":"排序字段     
	 *            }],
	 *             "page":{"endRow": 2,  当前页面最后一个元素在数据库中的行号
	 * 				"firstPage": 1, 第一页页码
	 * 				"hasNextPage": true存在下一页false不存在, 
	 * 				"hasPreviousPage": true存在上一页false不存在, 
	 * 				"isFirstPage": true是第一页false不是第一页, 
	 * 				"isLastPage": true是最后一页false不是最后一页, 
	 * 				"lastPage": 最后一页的页码, 
	 * 				"navigatePages": 导航数量，实现 1...5.6.7....10效果, 
	 * 				"navigatepageNums": []导航页码集合, 
	 * 				"nextPage": 下一页, 
	 * 				"pageNum": 当前页码, 
	 * 				"pageSize": 一页显示数量, 
	 * 				"pages": 总页数, 
	 * 				"prePage": 上一页, 
	 * 				"size": 总记录, 
	 * 				"startRow":当前页面第一个元素在数据库中的行号, 
	 * 				"total":总记录数量
	 * 				}
	 */
	@ApiOperation(value="根据父栏目获取子栏目下所有文章列表信息",notes = "文章列表", response = ArticleEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "basicCategoryId", value = "栏目类别ID", required = false,paramType="query"),
		@ApiImplicitParam(name = "order", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
	@RequestMapping(value = "/list",method= RequestMethod.POST)
	@ResponseBody
	public void list(@ApiIgnore ArticleEntity article, HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("ArticleAction-->list:查询列表,article:{}", article);
			int appId = BasicUtil.getAppId();
			int[] ids = null;
//			if (article.getBasicCategoryId()>0) {
//				 ids = new int[]{article.getBasicCategoryId()};
//			}
			if(article.getBasicCategoryId() > 0){
				ids = columnBiz.queryChildrenCategoryIds(article.getBasicCategoryId(), BasicUtil.getAppId(),
						BasicUtil.getModelCodeId(ModelCode.CMS_COLUMN));
			}
			//默认为desc排序
			boolean isOrder = true;
			if(!StringUtils.isBlank(article.getOrder())){
				String	basicOrder = article.getOrder();
				if(basicOrder.equalsIgnoreCase("asc")){
					isOrder = false;
				}
			}
			BasicUtil.startPage();
			List<ArticleEntity> list = articleBiz.query(appId, ids, null, null, article.getOrderBy(), isOrder, null, null, article);
			
			for(ArticleEntity _article : list){
				// 获取文章栏目id获取栏目实体
				ColumnEntity column = (ColumnEntity) columnBiz.getEntity(_article.getBasicCategoryId());
				ContentModelEntity contentModel = (ContentModelEntity) contentModelBiz
						.getEntity(column.getColumnContentModelId());
	
				// 判断内容模型的值
				if (contentModel != null) {
					Map where = new HashMap();
					// 压入basicId字段的值
					where.put("basicId", _article.getBasicId());
					// 遍历所有的字段实体,得到字段名列表信息
					List<String> listFieldName = new ArrayList<String>();
					listFieldName.add("basicId");
					// 查询新增字段的信息
					List fieldLists = fieldBiz.queryBySQL(contentModel.getCmTableName(), listFieldName, where);
					if (fieldLists.size() > 0) {
						Map map = (Map) fieldLists.get(0);
						_article.setExtendsFields(map);
					}
				}
			}
			//查询文章列表
			//List<ArticleEntity> articleList = articleBiz.query(appId, basicCategoryIds, articleType, null, null, true, null, null, article);
			
			ListBean temp_list =  new ListBean(list, BasicUtil.endPage(list));
			this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", temp_list)));
			//this.outJson(response, JSONArray.toJSONString(new ListBean(list, BasicUtil.endPage(list)),new DateValueFilter("yyyy-MM-dd HH:mm:ss")));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("ArticleAction-->list:查询列表异常:", e);
			//return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
			this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "查询列表异常")));
		}
	}
	
	@ApiOperation(value="根据父栏目获取子栏目信息",notes = "栏目列表", response = CategoryEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "categoryCategoryId", value = "父栏目Id", required = false,paramType="query"),
    })
	@RequestMapping(value = "/listByCategoryId",method= RequestMethod.POST)
	@ResponseBody
	public void listByCategoryId(@ApiIgnore CategoryEntity category, HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("ArticleAction-->list:查询列表,category:{}", category);
			//CategoryEntity categoryEntity = new CategoryEntity();
			//categoryEntity.setCategoryCategoryId(article);
			List<CategoryEntity>  entity = iArticleDao.selectCategoryList(category);
			if(entity != null && entity.size() > 0) {
				CategoryEntity temp = new CategoryEntity();
				temp.setCategoryParentId(String.valueOf(entity.get(0).getCategoryId()));
				List<CategoryEntity>  categoryList= iArticleDao.selectCategoryList(temp);
				this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", categoryList)));
			}else {
				this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "无对应的栏目信息", entity)));
			}
			//this.outJson(response, JSONArray.toJSONString(new ListBean(list, BasicUtil.endPage(list)),new DateValueFilter("yyyy-MM-dd HH:mm:ss")));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("ArticleAction-->list:查询列表异常:", e);
			//return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
			this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "查询列表异常")));
		}
	}
	
	/**
	 * 
	 * 根据模块查询所有栏目
	 * @param request
	 * @param response
	 */
	@ApiOperation(value = "获取所有栏目", notes = "栏目列表", response = ColumnEntity.class)
	@PostMapping("/listByModelId")
	@ResponseBody
	public void listByModelId(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			// 获取站点id
			int appId = BasicUtil.getAppId();
			List<ColumnEntity> list = columnBiz.queryAll(appId, this.getModelCodeId(request, ModelCode.CMS_COLUMN));
			
			//return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
			this.outJson(response, JSONObject.toJSONString(ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list)));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("FieldAction-->listModel:查询列表异常:", e);
			this.outJson(response, JSONObject.toJSONString(ResultUtils.error(Constant.CODE_ERROR, "查询列表异常")));
			//return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
		
	}

}