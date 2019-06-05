package com.sh.action.user;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sh.dao.impl.UserDao;
import com.sh.domain.User;
import com.sh.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Getter
@Setter
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
    private User user;
    private String re_password;
    private String msg;
    @Autowired
    private IUserService userService;
    public String userLogin() throws Exception {
        String forward = null;
        User user2 = userService.login(user);
        if (user2 != null) {
            ActionContext.getContext().getSession().put("User", user2);
            forward = "success";
        } else {
            msg = "�û��������ڻ�������󣬵�¼ʧ�ܣ������µ�¼��ע�ᣡ";
            forward = "failure";
        }
        return forward;
    }

    public String userRegister() throws Exception{
        String forward=null;
        int flag=0;
        User user2 = userService.login(user);
        if ((user2!=null)&&(user2.getUserName().equals(user.getUserName()))){
            msg="���û��Ѵ��ڣ�������ע�ᣡ";
            forward=ERROR;
        }else {
            flag = userService.insert(user);
            if (flag==1){
                forward=SUCCESS;
            }else {
                msg="���ݿ����";
                forward=ERROR;
            }
        }
        return forward;
    }
}
