package net.mingsoft.cms.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.cms.model.RecruitmentInfo;
import net.mingsoft.cms.service.RecruitmentInfoService;

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
 * @description 招聘管理控制器
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/recruitmentInfo")
public class RecruitmentInfoAction extends BaseAction {

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;

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
        return "/cms/recruitmentInfo/index"; // 这里表示显示/manager/cms/recruitmentInfo/recruitment_list.ftl
    }

    /**
     * 返回编辑界面peopleUser_form
     */
    @GetMapping("/form")
    public String form(@ModelAttribute RecruitmentInfo recruitmentInfo, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        if (recruitmentInfo.getId() != null) {
            RecruitmentInfo temp_recruitment = recruitmentInfoService.selectByPrimaryKey(recruitmentInfo.getId());
            model.addAttribute("recruitmentInfo", temp_recruitment);
        }
        return "/cms/recruitmentInfo/recruitmentInfo_form";
    }


   /* @ApiOperation(value = "保存基础信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "单位名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "单位编码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "address", value = "单位地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "imageUrl", value = "形象照", required = true, paramType = "query"),
            @ApiImplicitParam(name = "videoUrl", value = "公司视频介绍", required = true, paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
            @ApiImplicitParam(name = "detail", value = "详情", required = true, paramType = "query"),
    })
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("recruitmentInfo:save")
    public void save(@ModelAttribute @ApiIgnore RecruitmentInfo recruitmentInfo, HttpServletResponse response, HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(recruitmentInfo))) {
            this.outJson(response, null, false, checkInfo(recruitmentInfo));
            return;
        }
        recruitmentInfo.setCreateTime(new Date());
        recruitmentInfo.setUpdateTime(new Date());
        recruitmentInfoService.insertSelective(recruitmentInfo);
        this.outJson(response, true, "保存成功");
    }


    *//**
     * @param recruitments 基础信息表实体
     *                     <i>recruitments参数包含字段信息参考：</i><br/>
     *                     id:多个id直接用逗号隔开,例如id=1,2,3,4
     *                     批量删除用户基础信息表
     *                     <dt><span class="strong">返回</span></dt><br/>
     *                     <dd>{code:"错误编码",<br/>
     *                     result:"true｜false",<br/>
     *                     resultMsg:"错误信息"<br/>
     *                     }</dd>
     *//*
    @ApiOperation(value = "批量删除接口")
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("recruitmentInfo:del")
    public void delete(@RequestBody List<RecruitmentInfo> recruitments, HttpServletResponse response, HttpServletRequest request) {
        int count = recruitmentInfoService.deleteBatchBySelective(recruitments);
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
            @ApiImplicitParam(name = "introduce", value = "简介", required = true, paramType = "query"),
            @ApiImplicitParam(name = "detail", value = "详情", required = true, paramType = "query"),
    })
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("recruitmentInfo:update")
    public void update(@ModelAttribute @ApiIgnore RecruitmentInfo recruitmentInfo, HttpServletResponse response,
                       HttpServletRequest request) {
        if (!StringUtils.isEmpty(checkInfo(recruitmentInfo))) {
            this.outJson(response, null, false, checkInfo(recruitmentInfo));
            return;
        }
        recruitmentInfo.setUpdateTime(new Date());
        recruitmentInfoService.updateByPrimaryKeySelective(recruitmentInfo);

        this.outJson(response, true, "保存成功");
    }*/

    private String checkInfo(RecruitmentInfo recruitmentInfo) {
//        if (!StringUtil.checkLength(recruitmentInfo.getName() + "", 0, 50)) {
//            return getResString("err.length", this.getResString("recruitmentInfo.name"), "0", "50");
//        }
//        if (!StringUtil.checkLength(recruitmentInfo.getCode() + "", 0, 50)) {
//            return getResString("err.length", this.getResString("recruitmentInfo.code"), "0", "50");
//        }
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
            @ApiImplicitParam(name = "position", value = "职位名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "company_id", value = "单位ID", required = false, paramType = "query"),
    })
    @ResponseBody
    public EUListBean list(@ModelAttribute @ApiIgnore RecruitmentInfo recruitmentInfo, HttpServletResponse response, HttpServletRequest request, @ApiIgnore ModelMap model) {
        BasicUtil.startPage();
        List<RecruitmentInfo> recruitmentList = recruitmentInfoService.listBySelective(recruitmentInfo);
        EUListBean list = new EUListBean(recruitmentList, (int) BasicUtil.endPage(recruitmentList).getTotal());
        return list;
    }

}
