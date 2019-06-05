package com.sh.action.card;

import com.opensymphony.xwork2.ActionSupport;
import com.sh.dao.impl.CardDao;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Namespace(value = "/card2")
@ParentPackage(value = "struts-default")
@Setter@Getter
@Controller("cardRetrieveAction")
@Scope("prototype")
public class CardRetrieveAction extends ActionSupport {
    @Autowired
    private ICardService cardService;
    //提交页面：查询信息提交
    private Card card;
    private String condition;
    private String[] checkList;
    private int id;
    private String order;
//    private long pageNo;
//    private int pageSize;
    //返回执行结果的返回信息
//    private long recordCount;
//    private long pageCount;
    private List<Card> listCard;
    private String msg;
    private HttpSession session;

    @Action(
            value = "/card2/find",
            results = {
                    @Result(name="success",location = "/card/retrieve.jsp")
            }
    )
    public String find() throws Exception{
        listCard = cardService.findByCondition(condition,"1");
        session = ServletActionContext.getRequest().getSession();
        session.setAttribute("condition",condition);
        session.setAttribute("order",order);
        return SUCCESS;
    }


    @Action(
            value = "/card2/delete",
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
            value = "/card2/deleteList",
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
            value = "/card2/revert",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String revert() throws Exception{
        int ids[] = new int[checkList.length];
        for (int i = 0; i < checkList.length; ++i) {
            ids[i] = Integer.parseInt(checkList[i]);
        }
        cardService.revert(ids);
        return SUCCESS;
    }
}
