package com.mits.LoanApproval.Beans;
import java.io.InputStream;
import java.io.OutputStream;
public class DocumentBean {
	
	
	private String documentType;
	private String mimeType;
	private String fileName;
	private long fileSize;
	private InputStream documentInputStreamObj;
	private OutputStream documentOutputStreamObj;
		
	
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public InputStream getDocumentInputStreamObj() {
		return documentInputStreamObj;
	}
	public void setDocumentInputStreamObj(InputStream documentInputStreamObj) {
		this.documentInputStreamObj = documentInputStreamObj;
	}
	public OutputStream getDocumentOutputStreamObj() {
		return documentOutputStreamObj;
	}
	public void setDocumentOutputStreamObj(OutputStream documentOutputStreamObj) {
		this.documentOutputStreamObj = documentOutputStreamObj;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
	
	
	

}
