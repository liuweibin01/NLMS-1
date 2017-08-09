package resource.bean.report;

import java.util.HashSet;
import java.util.Set;

// Generated 2012-8-14 9:48:50 by Hibernate Tools 3.4.0.CR1

/**
 * BiImportLog generated by hbm2java
 */
public class BiImportLog implements java.io.Serializable {

	private String id;
	private String fuid;
	private String fileName;
	private String tableName;
	private String workDate;
	private Integer batchNo;
	private Integer seqNo;
	private String importStatus;
	private Integer totalRows;
	private Integer correctRows;
	private Integer errorRows;
	private Integer filterRows;
	private String errorMessage;
	private String modFlg;
	private String beginTime;
	private String endTime;
	private String errFilePath;
	private String errFile;
	private Set<BiImportLogDtl> logDtls = new HashSet<BiImportLogDtl>();
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Integer getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getImportStatus() {
		return this.importStatus;
	}

	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}

	public Integer getTotalRows() {
		return this.totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getCorrectRows() {
		return this.correctRows;
	}

	public void setCorrectRows(Integer correctRows) {
		this.correctRows = correctRows;
	}

	public Integer getErrorRows() {
		return this.errorRows;
	}

	public void setErrorRows(Integer errorRows) {
		this.errorRows = errorRows;
	}

	public Integer getFilterRows() {
		return this.filterRows;
	}

	public void setFilterRows(Integer filterRows) {
		this.filterRows = filterRows;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getModFlg() {
		return modFlg;
	}

	public void setModFlg(String modFlg) {
		this.modFlg = modFlg;
	}

	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getErrFilePath() {
		return errFilePath;
	}

	public void setErrFilePath(String errFilePath) {
		this.errFilePath = errFilePath;
	}

	public String getErrFile() {
		return errFile;
	}

	public void setErrFile(String errFile) {
		this.errFile = errFile;
	}

	public Set<BiImportLogDtl> getLogDtls() {
		return logDtls;
	}

	public void setLogDtls(Set<BiImportLogDtl> logDtls) {
		this.logDtls = logDtls;
	}

}
