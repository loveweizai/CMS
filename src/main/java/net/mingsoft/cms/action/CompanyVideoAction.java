package net.mingsoft.cms.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.cms.model.CompanyStructure;
import net.mingsoft.cms.model.CompanyVideoInfo;
import net.mingsoft.cms.service.CompanyService;
import net.mingsoft.cms.service.CompanyVideoInfoService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhongwei
 * @description 组织结构管理
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/companyVideo")
public class CompanyVideoAction extends BaseAction {

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

    @Autowired
    private CompanyVideoInfoService companyVideoInfoService;
    
    @Autowired
    private CompanyService companyService;

    /**
     * 加载页面显示所有信息
     *
     * @param request
     * @return 返回文章页面显示地址
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response, ModelMap model) {
        List<CompanyStructure> temp_company = companyService.listBySelective(null);
        model.addAttribute("companyList", temp_company);
        // 返回路径
        return "/cms/company_video/index"; // 这里表示显示/manager/cms/company/company_list.ftl
    }

    /**
     * 返回编辑界面peopleUser_form
     */
    @GetMapping("/form")
    public String form(@ModelAttribute CompanyVideoInfo companyVideoInfo, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        if (companyVideoInfo.getId() != null) {
            CompanyVideoInfo temp_companyVideoInfo = companyVideoInfoService.selectByPrimaryKey(companyVideoInfo.getId());
            model.addAttribute("companyVideo", temp_companyVideoInfo);
        }
        
        List<CompanyStructure> temp_company = companyService.listBySelective(null);
        model.addAttribute("companyList", temp_company);
        return "/cms/company_video/companyVideo_form";
    }


    @ApiOperation(value = "保存基础信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "videoName", value = "视频名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "单位Id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "videoUrl", value = "公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobileVideoUrl", value = "移动端公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
    })
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("companyVideo:save")
    public void save(@ModelAttribute @ApiIgnore CompanyVideoInfo companyVideoInfo, HttpServletResponse response, HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(companyVideoInfo))) {
            this.outJson(response, null, false, checkInfo(companyVideoInfo));
            return;
        }
        companyVideoInfo.setCreateTime(new Date());
        companyVideoInfo.setUpdateTime(new Date());
        companyVideoInfoService.insertSelective(companyVideoInfo);
        this.outJson(response, true, "保存成功");
    }


    /**
     * @param companys 基础信息表实体
     *                 <i>companys参数包含字段信息参考：</i><br/>
     *                 id:多个id直接用逗号隔开,例如id=1,2,3,4
     *                 批量删除用户基础信息表
     *                 <dt><span class="strong">返回</span></dt><br/>
     *                 <dd>{code:"错误编码",<br/>
     *                 result:"true｜false",<br/>
     *                 resultMsg:"错误信息"<br/>
     *                 }</dd>
     */
    @ApiOperation(value = "批量删除接口")
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("companyVideo:del")
    public void delete(@RequestBody List<CompanyVideoInfo> companyVideoInfos, HttpServletResponse response, HttpServletRequest request) {
        int count = companyVideoInfoService.deleteBatchBySelective(companyVideoInfos);
        if (count <= 0) {
            this.outJson(response, false, "删除失败");
            return;
        }
        this.outJson(response, true, "删除成功");
    }

    @ApiOperation(value = "修改基础信息接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
        @ApiImplicitParam(name = "videoName", value = "视频名称", required = true, paramType = "query"),
        @ApiImplicitParam(name = "companyId", value = "单位Id", required = true, paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "videoUrl", value = "公司视频介绍", required = true, paramType = "query"),
        @ApiImplicitParam(name = "mobileVideoUrl", value = "移动端公司视频介绍", required = true, paramType = "query"),
        @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
    })
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("companyVideo:update")
    public void update(@ModelAttribute @ApiIgnore CompanyVideoInfo companyVideoInfo, HttpServletResponse response,
                       HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(companyVideoInfo))) {
            this.outJson(response, null, false, checkInfo(companyVideoInfo));
            return;
        }
        companyVideoInfo.setUpdateTime(new Date());
        companyVideoInfoService.updateByPrimaryKeySelective(companyVideoInfo);

        this.outJson(response, true, "保存成功");
    }

    private String checkInfo(CompanyVideoInfo companyVideoInfo) {
        if (!StringUtil.checkLength(companyVideoInfo.getVideoName() + "", 0, 50)) {
            return getResString("err.length", this.getResString("companyVideoInfo.name"), "0", "50");
        }
        return null;
    }

    /**
     * 加载页面显示所有信息
     *
     * @param request
     * @return 返回页面显示数据
     */

    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoName", value = "视频名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "视频名称", required = false, paramType = "query"),
    })
    @ResponseBody
    public EUListBean list(@ModelAttribute @ApiIgnore CompanyVideoInfo companyVideoInfo, HttpServletResponse response, HttpServletRequest request, @ApiIgnore ModelMap model) {
        BasicUtil.startPage();
        List<CompanyVideoInfo> companyList = companyVideoInfoService.listBySelective(companyVideoInfo);
        EUListBean list = new EUListBean(companyList, (int) BasicUtil.endPage(companyList).getTotal());
        return list;
    }

}
