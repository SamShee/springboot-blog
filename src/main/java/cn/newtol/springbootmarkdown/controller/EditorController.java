package cn.newtol.springbootmarkdown.controller;
import cn.newtol.springbootmarkdown.dao.ResumeTemplate;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.entity.ResumeDTO;
import cn.newtol.springbootmarkdown.services.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:10 2019/4/28
 * @params:
 */

@Controller
public class EditorController {
    @Resource
    private ResumeService resumeService;

    /**
     * 获取编辑页面
     * @return 返回简历编辑页面
     */
    @GetMapping("/editor")
    public String getEditor(@RequestParam String resumeId){
        return "editor";
    }


    /**
     * 简历上传界面
     * @param httpServletRequest 获取用户IP地址等信息
     * @param resumeDTO 简历内容
     * @return 是否填写成功
     */
    @PostMapping("/editor/myResume")
    @ResponseBody
    public ResultVO updateResume(HttpServletRequest httpServletRequest, @Valid ResumeDTO resumeDTO) throws Exception {
        return resumeService.saveResume(httpServletRequest,resumeDTO);
    }

    /**
     * 获取模板简历
     * @return 返回模板简历内容
     */
    @GetMapping("/editor/getTemplateResume/{resumeId}")
    @ResponseBody
    public ResultVO getTemplateResume(@PathVariable String resumeId){
        return resumeService.getResumeTemplate(resumeId);
    }

    /**
     * 进入预览页面
     * @return 返回预览页
     */
    @GetMapping("/editor/preview")
    public String getPreview(){
        return "preview";
    }

    /**
     * 模板编辑页面
     * @return
     */
    @GetMapping("/editorResumeTemplate")
    public String getEditorResumeTemplate(){
        return "editorResumeTemplate";
    }

    /**
     * 增加简历模板
     */
    @PostMapping("/addResumeTemplate")
    @ResponseBody
    public ResultVO add(@Valid ResumeTemplate resumeTemplate){
        return resumeService.addResumeTemplate(resumeTemplate);
    }

}