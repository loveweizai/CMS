package net.mingsoft.cms.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.cms.model.CompanyStructure;
import net.mingsoft.cms.service.CompanyService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author lizhongwei
 * @description 组织结构管理
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/company")
public class CompanyAction extends BaseAction {

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

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
    public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
        // 返回路径
        return "/cms/company/index"; // 这里表示显示/manager/cms/company/company_list.ftl
    }

    /**
     * 返回编辑界面peopleUser_form
     */
    @GetMapping("/form")
    public String form(@ModelAttribute CompanyStructure company, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        if (company.getId() != null) {
            CompanyStructure temp_company = companyService.selectByPrimaryKey(company.getId());
            model.addAttribute("company", temp_company);
        }
        return "/cms/company/company_form";
    }


    @ApiOperation(value = "保存基础信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "单位名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "单位编码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "address", value = "单位地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "imageUrl", value = "形象照", required = true, paramType = "query"),
            @ApiImplicitParam(name = "videoUrl", value = "公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobileVideoUrl", value = "移动端公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
            @ApiImplicitParam(name = "detail", value = "详情", required = true, paramType = "query"),
    })
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("company:save")
    public void save(@ModelAttribute @ApiIgnore CompanyStructure company, HttpServletResponse response, HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(company))) {
            this.outJson(response, null, false, checkInfo(company));
            return;
        }
        company.setCreateTime(new Date());
//        company.setUpdateTime(new Date());
        companyService.insertSelective(company);
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
    @RequiresPermissions("company:del")
    public void delete(@RequestBody List<CompanyStructure> companys, HttpServletResponse response, HttpServletRequest request) {
        int count = companyService.deleteBatchBySelective(companys);
        if (count <= 0) {
            this.outJson(response, false, "删除失败");
            return;
        }
        this.outJson(response, true, "删除成功");
    }

    @ApiOperation(value = "修改基础信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "单位名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "单位编码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "address", value = "单位地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "imageUrl", value = "形象照", required = true, paramType = "query"),
            @ApiImplicitParam(name = "videoUrl", value = "公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mobileVideoUrl", value = "移动端公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
            @ApiImplicitParam(name = "detail", value = "详情", required = true, paramType = "query"),
    })
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("company:update")
    public void update(@ModelAttribute @ApiIgnore CompanyStructure company, HttpServletResponse response,
                       HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(company))) {
            this.outJson(response, null, false, checkInfo(company));
            return;
        }
//        company.setUpdateTime(new Date());
        companyService.updateByPrimaryKeySelective(company);

        this.outJson(response, true, "保存成功");
    }

    private String checkInfo(CompanyStructure company) {
        if (!StringUtil.checkLength(company.getName() + "", 0, 50)) {
            return getResString("err.length", this.getResString("company.name"), "0", "50");
        }
        if (!StringUtil.checkLength(company.getCode() + "", 0, 50)) {
            return getResString("err.length", this.getResString("company.code"), "0", "50");
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
            @ApiImplicitParam(name = "name", value = "单位名称", required = false, paramType = "query"),
    })
    @ResponseBody
    public EUListBean list(@ModelAttribute @ApiIgnore CompanyStructure company, HttpServletResponse response, HttpServletRequest request, @ApiIgnore ModelMap model) {
        BasicUtil.startPage();
        List<CompanyStructure> companyList = companyService.listBySelective(company);
        EUListBean list = new EUListBean(companyList, (int) BasicUtil.endPage(companyList).getTotal());
        return list;
    }

}
