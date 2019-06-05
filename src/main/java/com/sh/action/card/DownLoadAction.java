package com.sh.action.card;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


@Namespace(value = "/card")
@ParentPackage(value = "struts-default")
@Setter
@Getter
@Controller("downLoadAction")
@Scope("prototype")
public class DownLoadAction extends ActionSupport {
    private String contentType;
    private long contentLength;
    private String contentDisposition;
    private InputStream inputStream;
    private String fileName="��Ƭ.xls";
    public String getContentType(){
        return contentType;
    }
    public long getContentLength(){
        return contentLength;
    }
    public String getContentDisposition(){
        return contentDisposition;
    }
    public InputStream getInputStream(){
        return inputStream;
    }

    @Action(
            value = "/card/download",
            results = {
                    @Result(name = "success",type = "stream")
            }
    )
    public String executeDownLoad() throws Exception{
        String[] fieldList={"id","name","sex","department","mobile","phone","email","address","flag"};
        String[] titles={"���","����","�Ա�","��λ","�ֻ�","�绰","��������","��ַ","��ע"};
        String file = "��Ƭ.xls";
        HttpSession session = ServletActionContext.getRequest().getSession();
        String condition = (String) session.getAttribute("condition");
        String order = (String) session.getAttribute("order");
        String sql = "";
        if (condition!=null&&!condition.equals("")){
            sql=sql+"(name like '%"+condition+"%'";
            sql = sql +" or sex like '%"+condition+"%'";
            sql = sql +" or department like '%"+condition+"%'";
            sql = sql +" or mobile like '%"+condition+"%'";
            sql = sql +" or phone like '%"+condition+"%'";
            sql = sql +" or email like '%"+condition+"%'";
            sql = sql +" or address like '%"+condition+"%')";
        }
        contentType = "application/octet-stream";
        String name = java.net.URLEncoder.encode(fileName,"UTF-8");
        contentDisposition="attachment;filename="+name;
        ServletContext servletContext = ServletActionContext.getServletContext();
        String fileName2=servletContext.getRealPath("/download/"+file);
        File downloadFile = new File(fileName2);
        if (!downloadFile.exists()){
            downloadFile.getParentFile().mkdirs();
        }
//        DbToExcel.dBToExcel("card",fieldList,titles,sql,order,fileName2);
        inputStream = new FileInputStream(fileName2);
        contentLength = inputStream.available();
        return SUCCESS;
    }
}
