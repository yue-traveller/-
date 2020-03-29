package com.google.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.domain.ArcType;
import com.google.domain.Article;
import com.google.domain.User;
import com.google.service.ArcTypeService;
import com.google.service.ArticleService;
import com.google.service.UserService;
import com.google.util.CheckShareLinkEnableUtil;
import com.google.util.Constes;
import com.google.util.Cryptographyutil;
import com.google.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private JavaMailSenderImpl mailSender;
    @Autowired
    private ArcTypeService arcTypeService;
    @Autowired
    private ArticleService articleService;
    @PostMapping("/register")
    @ResponseBody
    public Map<String,Object> register(@Valid User user, BindingResult bindingResult){

        Map<String,Object> map=new HashMap<>();
        if (bindingResult.hasErrors()){
            map.put("success",false);
            map.put("errorInfo", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }else if (userService.selectUserByUsername(user.getUserName())!=null) {
            map.put("success", false);
            map.put("errorInfo", "用户名已存在，请重新输入!");
        }else if (userService.selectUserByEmail(user.getEmail())!=null) {
            map.put("success", false);
            map.put("errorInfo", "邮箱已存在，请更换！");

        }else {
            user.setPassword(Cryptographyutil.md5(user.getPassword(),Cryptographyutil.SALT));
            user.setRegistrationDate(new Date());
            user.setLatelyLoginTime(new Date());
            user.setHeadPortrait("tou.jpg");
            userService.insert(user);
            map.put("success",true);
        }

        return map;
    }
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> login(User user, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        if (StringUtil.isEmpty(user.getUserName())){
            map.put("success",false);
            map.put("errorInfo","请输入用户名");
        }else if (StringUtil.isEmpty(user.getPassword())){
            map.put("success",false);
            map.put("errorInfo","请输入密码");
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),Cryptographyutil.md5(user.getPassword(),Cryptographyutil.SALT));
            try {
                subject.login(token);
                String username = (String) SecurityUtils.getSubject().getPrincipal();
                User currentUser = userService.selectUserByUsername(username);
                if (currentUser.getIsOff()){
                    map.put("success",false);
                    map.put("errorInfo","该用户已被封禁，请联系管理员");
                    subject.logout();
                }else {
                    currentUser.setLatelyLoginTime(new Date());
                    userService.updateByPrimaryKeySelective(currentUser);
                    session.setAttribute(Constes.CURRENT_USER,currentUser);
                    map.put("success",true);

                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
                map.put("success",false);
                map.put("errorInfo","用户名或者密码错误");
            }
        }
      return map;
    }
    @RequestMapping("/sendEmail")
    @ResponseBody
    public Map<String,Object> sendEmail(String email,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(email)){
            map.put("success",false);
            map.put("errorInfo","邮箱不能为空，请重新输入");
            return map;
        }
        User user = userService.selectUserByEmail(email);
        if (user==null){
            map.put("success",false);
            map.put("errInfo","该邮箱不存在，请重新输入");
            return map;
        }
        String mailCode = StringUtil.getSixRandom();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2249006799@qq.com");
        message.setTo(email);
        message.setSubject("Java资源分享网-用户找回密码");
        message.setText("您本次的验证码是："+mailCode);
        mailSender.send(message);
        System.out.println(mailCode);
        session.setAttribute(Constes.MAILL_CODE_NAME,mailCode);
        session.setAttribute(Constes.USER_ID_NAME,user.getUserId());

    map.put("success",true);
    return map;

    }
    @RequestMapping("/checkYzm")
    @ResponseBody
    public Map<String,Object> cheekYzm(String yzm,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(yzm)){
            map.put("success",false);
            map.put("errorInfo","验证码不能为空");
            return map;
        }
       String code = (String) session.getAttribute(Constes.MAILL_CODE_NAME);
        Integer userId = (Integer) session.getAttribute(Constes.USER_ID_NAME);
        if (!code.trim().equals(yzm)){
         map.put("success",false);
         map.put("errorInfo","验证码有误，请重新输入");
         return map;

        }
        User user = userService.selectByPrimaryKey(userId);

            user.setPassword(Cryptographyutil.md5(Constes.DEFAULT_Password,Cryptographyutil.SALT));
            userService.updateByPrimaryKeySelective(user);
            map.put("success",true);
            return map;
    }

    @RequestMapping("/articleManage")
    public String articleManage(){
        return "/user/articleManage";
    }

    @GetMapping("/articleList")
    @ResponseBody
     public Map<String,Object>articleList(@RequestParam(required = false,value = "page")Integer page,
                                      @RequestParam(required = false,value = "limit") Integer pageSize, String name, Model model){

        Map<String,Object> map = new HashMap<>();


        PageInfo<ArcType> pageInfo = arcTypeService.findAll(name, page, pageSize);
        map.put("code",0);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());


        return map;




 }
 @RequestMapping("/toAddArticle")
 public String addArticle(Map<String,Object> map){
     List<ArcType> arcTypes = arcTypeService.selectAll();
     map.put("arcType",arcTypes);

     return "/user/addArticle";
 }
 @PostMapping("/saveArticle")
 @ResponseBody
  public Map<String,Object> saveArticle (Article article,HttpSession session) throws IOException {
        Map<String,Object> reslut = new HashMap<>();
        if (article.getPoints()<0||article.getPoints()>10){
            reslut.put("success",false);
            reslut.put("erroInfo","积分超出正常区间！");
            return reslut;
        }
        if (!CheckShareLinkEnableUtil.cheek(article.getDownload())){
          reslut.put("success",false);
          reslut.put("erroInfo","百度云分享链接已经失效，请重新发布！");
          return reslut;
      }

      Integer id = (Integer) session.getAttribute(Constes.USER_ID_NAME);
      if (article.getArticleId()==null){
          article.setPublishDate(new Date());
          article.setUserId(id);
          if (article.getPoints()==0){
              article.setIsFree(true);
          }
          article.setState(1);
          article.setClick(new Random().nextInt(150)+50);
          articleService.insertSelective(article);
          reslut.put("success",true);
      }
      return reslut;
  }
}
