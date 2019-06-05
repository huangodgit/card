package com.sh.action.card;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.io.File;

//import com.sh.util.DbToExcel;


@Namespace(value = "/card")
@ParentPackage(value = "struts-default")
@Setter@Getter
@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends ActionSupport {
    private File file;
    private String fileFileName;
    private String fileContentType;

    @Action(
            value = "/card/upload",
            results = {
                    @Result(name = "success",location = "/find",type = "redirectAction")
            }
    )
    public String upload() throws Exception{
        ServletContext servletContext = ServletActionContext.getServletContext();
        String dir = servletContext.getRealPath("/upload");
        File saveFile = new File(dir,fileFileName);
        FileUtils.copyFile(file,saveFile);
        String fieldList = "(id,name,sex,department,mobile,phone,email,address)";
//        DbToExcel.excelToDb(dir+"/"+fileFileName,"card",fieldList,8);
        return SUCCESS;
    }
}
