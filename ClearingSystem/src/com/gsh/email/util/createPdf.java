package com.gsh.email.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class createPdf {
	//生成pdf  
    public static boolean createPdf(String pdfPathName,String content,String title)throws Exception {  
        //String rootpath = "f:/TDDOWNLOAD";//rootpath.replaceAll("\\\\", "/");
        boolean flag = false;  
        String outputFile = pdfPathName;  
        //指定目录导出文件    
        OutputStream os = new FileOutputStream(outputFile);  
        ITextRenderer renderer = new ITextRenderer();  
        StringBuffer html = new StringBuffer();  
        //组装成符合W3C标准的html文件，否则不能正确解析  
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")  
            .append("<head>")  
            .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
            .append("<title>"+title+"</title>")
            .append("</head>")  
            .append("<body>");
        html.append(content);
        html.append("</body></html>");  
        try {  
            renderer.setDocumentFromString(html.toString());  
            // 解决图片的相对路径问题,图片路径必须以file开头  
            //renderer.getSharedContext().setBaseURL("file:/" + rootpath);  
            renderer.layout();  
            renderer.createPDF(os);  
            flag = true;  
        } catch (Exception e) {  
            flag = false;  
            e.printStackTrace();  
        }finally{
        	os.close(); 
        }
        return flag;  
    } 
}
