package com.mits.LoanApproval.Beans;
import java.sql.Date;
import java.util.ArrayList;

public class OperationalParametersBean {
	
    
	private ApplicationBean applicationBean;
	private ArrayList<DocumentBean> documentBean;
	private String applicationNumber;
	private String creditRating;
	private int loanAmountRequested;
	private String kycCheck;
	private int loanAmountGranted;
	private int tenureRequested;
	private int tenureApproved;
	private String applicationStatus;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	public ApplicationBean getApplicationBean() {
		return applicationBean;
	}

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public ArrayList<DocumentBean> getDocumentBean() {
		return documentBean;
	}

	public void setDocumentBean(ArrayList<DocumentBean> documentBean) {
		this.documentBean = documentBean;
	}
	
	public OperationalParametersBean()
	{
		
	}
	
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public int getLoanAmountRequested() {
		return loanAmountRequested;
	}
	public void setLoanAmountRequested(int loanAmountRequested) {
		this.loanAmountRequested = loanAmountRequested;
	}
	public String getKycCheck() {
		return kycCheck;
	}
	public void setKycCheck(String kycCheck) {
		this.kycCheck = kycCheck;
	}
	public int getLoanAmountGranted() {
		return loanAmountGranted;
	}
	public void setLoanAmountGranted(int loanAmountGranted) {
		this.loanAmountGranted = loanAmountGranted;
	}
	public int getTenureRequested() {
		return tenureRequested;
	}
	public void setTenureRequested(int tenureRequested) {
		this.tenureRequested = tenureRequested;
	}
	public int getTenureApproved() {
		return tenureApproved;
	}
	public void setTenureApproved(int tenureApproved) {
		this.tenureApproved = tenureApproved;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	
	

}
