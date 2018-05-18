package com.ws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ws.common.AppContext;
import com.ws.exception.ParameterException;
import com.ws.exception.ServiceException;
import com.ws.model.Pagination;
import com.ws.model.User;
import com.ws.service.UserService;
import com.ws.util.StringUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping("/page/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "go", defaultValue = "") String go) {
        User user = AppContext.getContext().getUser();
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.setView(this.getRedirectView("page/user/content"));
        } else {
            modelAndView.addObject("go", go);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @ApiOperation(value="�û���½��֤", notes="����url��id��ָ�����¶��󣬲����ݴ�������user��Ϣ�������û���ϸ��Ϣ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "�û���", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "����", required = true, dataType = "String"),
            @ApiImplicitParam(name = "queryString", value = "url��¼����", required = false, dataType = "String"),
            @ApiImplicitParam(name = "go", value = "url��ַ", required = false, dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginCheck(
                                   @RequestParam(value = "userName", defaultValue = "") String userName,
                                   @RequestParam(value = "password", defaultValue = "") String password,
                                   @RequestParam(value = "queryString", defaultValue = "") String queryString,
                                   @RequestParam(value = "go", defaultValue = "") String go
                                   ) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,String> message = new HashMap<String, String>();
        try {
            User user = null;
            user = userService.login(userName, password);
            this.addSession("USER", user);
            if (!StringUtil.isEmpty(queryString)) {
                if (queryString.startsWith("#")) {
                    queryString = queryString.substring(1);
                }
                go = go + "?" + queryString;
            }
            RedirectView redirectView = StringUtil.isEmpty(go) ? this.getRedirectView("page/user/content")
                                                               : new RedirectView(AppContext.getContextPath() + "/" + go);
            modelAndView.setView(redirectView);
        } catch (ParameterException parameterException) {
            message = parameterException.getErrorMessage();
            modelAndView.addObject("TIP_MESSAGE", message);
            modelAndView.setViewName("login");
        } catch (ServiceException serviceException) {
            int errorCode = serviceException.getCode();
            if (errorCode == 1000) {
                message.put("errorNameMessage", serviceException.getExceptionMessage());
            }
            if (errorCode == 1001) {
                message.put("errorPasswordMessage", serviceException.getExceptionMessage());
            }
            modelAndView.addObject("TIP_MESSAGE", message);
            modelAndView.setViewName("login");
        }
        this.removeSession("hiddens");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        this.removeSession("USER");
        modelAndView.setView(this.getRedirectView("page/home/init-data"));
        return modelAndView;
    }

    @ApiOperation(value="��ȡ�û��б�", notes="")
    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public ModelAndView allUser() {
    	String keyWord="";
    	String currentPageStr="";
    	if (StringUtil.isEmpty(currentPageStr)) {
            currentPageStr = "1";
        }
        Integer currentPage = Integer.parseInt(currentPageStr);
        if (currentPage < 1) {
            currentPage = 1;
        }
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
    	List<User> allUser = userService.getAllUser(pagination);
    	this.addSession("allUser", allUser);
    	this.addSession("pagination", pagination);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_manage");
        return modelAndView;
    }
}
