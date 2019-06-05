package com.sh.action.card;

import com.opensymphony.xwork2.ActionSupport;
import com.sh.domain.Card;
import com.sh.service.ICardService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpSession;
import java.util.List;

@Namespace(value = "/card")
@ParentPackage(value = "struts-default")
@Setter@Getter
@Controller("cardAction")
@Scope("prototype")
public class CardAction extends ActionSupport {
    @Autowired
    private ICardService cardService;

    //提交页面：查询信息提交
    private Card card;
    private String condition;
    private String[] checkList;
    private int id;
    private String order;
    private long pageNo;
    private int pageSize;
    //返回执行结果的返回信息
    private long recordCount;
    private long pageCount;
    private List<Card> listCard;
    private String msg;
    private HttpSession session;

    @Action(
            value = "/card/insert",
            results = {
                    @Result(name="success",location = "/find",type = "redirectAction")
            }
    )
    public String insert() throws Exception{
        cardService.insert(card);
        msg = "插入一条记录成功";
        return SUCCESS;
    }

    @Action(
            value = "/card/insertList",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String insertList() throws Exception{
        int n = cardService.insertList(listCard);
        msg="插入一组("+n+"条)记录成功";
        return "success";
    }

    @Action(
            value = "/card/delete",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String delete() throws Exception{
        cardService.delete(id);
        msg = "删除一条记录成功";
        return SUCCESS;
    }

    @Action(
            value = "/card/deleteList",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String deleteList() throws Exception{
        int ids[] = new int[checkList.length];
        for (int i = 0; i <checkList.length; ++i) {
            ids[i] = Integer.parseInt(checkList[i]);
        }
        int n = cardService.deleteList(ids);
        msg = "删除一组（"+n+"条）记录成功!";
        return SUCCESS;
    }

    @Action(
            value = "/card/find",
            results = {
                    @Result(name="success",location = "/card/list.jsp")
            }
    )
    public String find() throws Exception{
        listCard = cardService.findByCondition(condition,"0");
        session = ServletActionContext.getRequest().getSession();
        session.setAttribute("condition",condition);
        session.setAttribute("order",order);
        return SUCCESS;
    }

    @Action(
            value = "/card/findUpdate",
            results = {
                    @Result(name = "success",location = "/card/update.jsp")
            }
    )
    public String findUpdate() throws Exception{
        card=cardService.findById(id,"0");
        return SUCCESS;
    }

    @Action(
            value = "/card/retrieve",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String retrieve() throws Exception{
        int ids[] = new int[checkList.length];
        for (int i = 0; i < checkList.length; ++i) {
            ids[i] = Integer.parseInt(checkList[i]);
        }
        int id1 = cardService.retrieve(ids);
        return SUCCESS;
    }

    @Action(
            value = "/card/update",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String update() throws Exception{
        cardService.update(card);
        return SUCCESS;
    }
}
