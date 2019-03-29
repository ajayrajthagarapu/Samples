package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mits.LoanApproval.Beans.DocumentBean;
import com.mits.LoanApproval.Dao.WorkFlowDao;


@WebServlet("/DocumentDownloadCOntroller")
public class DocumentDownloadCOntroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 20000;
	static final Logger logger = Logger.getLogger(DocumentDownloadCOntroller.class); 

  
    public DocumentDownloadCOntroller() 
    {
        super();
      
    }

	//downloading the documents using this controller by getting the values through link
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("IN DocumentDownloadCOntroller ************************");
		String buttonValue = request.getParameter("submit");
		System.out.println(buttonValue);
		if("retreiveDocument".equals(buttonValue))
		{
			String APPLICATIONNUMBER = request.getParameter("applicationnumber");
			String form = request.getParameter("form");
			WorkFlowDao wfd=new WorkFlowDao();
			DocumentBean documentBean = wfd.getDocuments(APPLICATIONNUMBER, form);
			if(documentBean!=null)
			{
				
				String mimeType = documentBean.getMimeType();
				String fileName = documentBean.getFileName();
				long fileSize = documentBean.getFileSize();
				InputStream documentInputStreamObj = documentBean.getDocumentInputStreamObj();
			    if(mimeType == null)
			    {
			    	mimeType = "application/octet-stream";
			    }
			    response.setContentType(mimeType);
                response.setContentLength((int) fileSize);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
               
                byte[] buffer = new byte[BUFFER_SIZE];
            
                int bytesRead = -1;
                System.out.println("12345SS"+documentInputStreamObj==null);
                if(documentInputStreamObj != null)
                
                while ((bytesRead = documentInputStreamObj.read(buffer)) != -1)
                {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.close();
                
                if(documentInputStreamObj != null)
                {
                documentInputStreamObj.close();
                }
                
			}
			else
			{
				response.getWriter().println("FILE NOT FOUND");
				
				
			}
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
