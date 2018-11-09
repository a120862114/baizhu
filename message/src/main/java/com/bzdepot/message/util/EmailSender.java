package com.bzdepot.message.util;

import com.alibaba.fastjson.JSONObject;
import com.bzdepot.message.model.Message;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class EmailSender {
    /**引入logger4j*/
    protected final static Logger logger = LoggerFactory.getLogger(EmailSender.class);
    protected static Properties pro=null;
    protected static String  hostName=null;
    protected static String  emailName=null;
    protected static String  emailPass=null;
    protected static String forname=null;
    private static final String encode = "UTF-8";//文件的编码格式

    /*static {
        if(pro==null){
            try {
                pro=new Properties();
                pro.load(ClassLoader.getSystemResourceAsStream("email.properties"));
               hostName=pro.getProperty("hostName","mtp.163.com");
               emailName=pro.getProperty("emailName","taoyinwangjj@163.com");
                emailPass=pro.getProperty("emailPass","jjsjjs521521");
                forname=pro.getProperty("forname","淘印网");
                System.out.println(hostName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/

    public static boolean SendEmail(Message mes)  {
        boolean flag=true;
        HtmlEmail email = new HtmlEmail ();
        email.setHostName(mes.getHostName());//smtp host
        email.setAuthentication(mes.getEmailName(),mes.getEmailPass());//登陆邮件服务器的用户名和密码
        String to_email=mes.getToEmail();
        String content=mes.getContent();

        try {
            email.addTo(to_email, "来自淘印网");//接收人(第二个参数为收件邮箱别称)
            email.setFrom(mes.getEmailName(), mes.getFrom_name());//发送人(第二个参数为发件邮箱别称)
            email.setSubject(content);//标题
            email.setHtmlMsg("<b>-------------邮件来源于套印网---------------</b>");//邮件内容
            email.send();//发送
        } catch (EmailException e) {
            logger.error("send emai to "+to_email+" error happened "+e.getMessage());
        }
        return flag;
    }
    public static boolean SendEmails(Message mes)  {
        boolean flag=true;
        String to_email=mes.getToEmail();
        for(String o:to_email.split(";")){
            HtmlEmail email = new HtmlEmail ();
            email.setHostName(mes.getHostName());//smtp host
            email.setAuthentication(mes.getEmailName(),mes.getEmailPass());//登陆邮件服务器的用户名和密码
            String content=mes.getContent();
            try {
                email.addTo(o, o);//接收人(第二个参数为收件邮箱别称)
                email.setFrom(mes.getEmailName(), mes.getFrom_name());//发送人(第二个参数为发件邮箱别称)
                email.setSubject(content);//标题
                email.setHtmlMsg(content);//邮件内容
                email.send();//
            } catch (EmailException e) {
                flag=false;
                logger.error("send emai to "+to_email+" error happened "+e.getMessage());
            }
        }

        return flag;
    }
    public static boolean SendEmail(JSONObject jso)  {
        boolean flag=true;
        HtmlEmail email = new HtmlEmail ();
           email.setHostName(hostName);//smtp host
        email.setAuthentication(emailName,emailPass);//登陆邮件服务器的用户名和密码
        String to_email=jso.getString("ToEmail");
        String content=jso.getString("CONTENT");

        try {
           email.addTo(to_email, "来自淘印网");//接收人(第二个参数为收件邮箱别称)
           email.setFrom(emailName, forname);//发送人(第二个参数为发件邮箱别称)
            email.setSubject(content);//标题
            email.setHtmlMsg("<b>-------------邮件来源于套印网---------------</b>");//邮件内容
            email.send();//发送
        } catch (EmailException e) {
            logger.error("send emai to "+to_email+" error happened "+e.getMessage());
        }
        return flag;
    }
    public static boolean SendEmails(JSONObject jso)  {
        boolean flag=true;
        String to_email=jso.getString("ToEmail");
        for(String o:to_email.split(";")){
            HtmlEmail email = new HtmlEmail ();
            email.setHostName(hostName);//smtp host
            email.setAuthentication(emailName,emailPass);//登陆邮件服务器的用户名和密码
            String content=jso.getString("CONTENT");
            try {
                email.addTo(o, o);//接收人(第二个参数为收件邮箱别称)
                email.setFrom(emailName, forname);//发送人(第二个参数为发件邮箱别称)
                email.setSubject(content);//标题
                email.setHtmlMsg(content);//邮件内容
                email.send();//
            } catch (EmailException e) {
                flag=false;
                logger.error("send emai to "+to_email+" error happened "+e.getMessage());
            }
        }

        return flag;
    }
    public static String getValue(String key) throws Exception {
        return new String(pro.getProperty(key).getBytes("ISO8859-1"), encode);
    }
    public static void main(String[] args) throws EmailException {
        HtmlEmail email = new HtmlEmail ();
        email.setHostName("smtp.163.com");//smtp host
        email.setAuthentication("taoyinwangjj@163.com","jjsjjs521521");//登陆邮件服务器的用户名和密码
        email.addTo("352041396@qq.com", "John Doe");//接收人(第二个参数为收件邮箱别称)
        email.setFrom("taoyinwangjj@163.com", "Me");//发送人(第二个参数为发件邮箱别称)
        email.setSubject("你是煞笔");//标题
        email.setHtmlMsg("<b>-------------This is a simple test of commons-email---------------</b>");//邮件内容
        email.send();//发送
    }

}
