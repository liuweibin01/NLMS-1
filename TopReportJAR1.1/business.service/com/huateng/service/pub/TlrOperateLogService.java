/*
 * Created on 2017-08-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.huateng.service.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import resource.bean.pub.TlrOperateLog;
import resource.dao.base.HQLDAO;

import com.huateng.ebank.business.common.BaseDAOUtils;
import com.huateng.ebank.business.common.ErrorCode;
import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.business.common.PageQueryCondition;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.ApplicationContextUtils;
import com.huateng.ebank.framework.util.DataFormat;
import com.huateng.ebank.framework.util.DateUtil;
import com.huateng.ebank.framework.util.ExceptionUtil;

/**
 * @author Administrator
 *
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TlrOperateLogService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(TlrOperateLogService.class);

	/**
	 * get instance.
	 *
	 * @return
	 */
	public synchronized static TlrOperateLogService getInstance() {
		return (TlrOperateLogService) ApplicationContextUtils
				.getBean(TlrOperateLogService.class.getName());
	}

	public TlrOperateLogService() {
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void saveTlrOperateLog(String operateType, String queryType,
			String queryNum, String measssage) throws CommonException {
		HQLDAO hqldao = BaseDAOUtils.getHQLDAO();
		GlobalInfo gi = GlobalInfo.getCurrentInstance();
		TlrOperateLog tlrOperateLog = new TlrOperateLog();
		tlrOperateLog.setId(UUID.randomUUID().toString().replaceAll("-", "")
				.toUpperCase());
		tlrOperateLog.setBrNo(gi.getBrno());
		tlrOperateLog.setTlrNo(gi.getTlrno());
		tlrOperateLog.setTlrIP(gi.getIp());
		tlrOperateLog.setOperateType(operateType);
		tlrOperateLog.setQueryType(queryType);
		tlrOperateLog.setQueryRecordNumber(queryNum);
		tlrOperateLog.setCreateDate(DateUtil.getCurrentDate());
		tlrOperateLog.setMessage(measssage);
		try {
			hqldao.getHibernateTemplate().save(tlrOperateLog);
		} catch (Exception e) {
			logger.error("update(TlrOperateLog)", e);
			ExceptionUtil.throwCommonException(e.getMessage(),
					ErrorCode.ERROR_CODE_TLR_INFO_INSERT, e);
		}
	}

	public PageQueryResult queryTlrOperateLogDetail(int pageIndex,
			int pageSize, String qtlrNo, String qtlrIP, String qbrNo,
			String stdate, String endate) throws CommonException {
		StringBuffer sb = new StringBuffer("");
		List<Object> list = new ArrayList<Object>();
		// sb.append("select log from TlrLoginLog log where 1=1");
		sb.append("select log from TlrOperateLog log where 1=1");
		if (!DataFormat.isEmpty(qtlrNo)) {
			sb.append(" and  log.tlrNo= ?");
			list.add(qtlrNo);
		}
		if (!DataFormat.isEmpty(qtlrIP)) {
			sb.append(" and  log.tlrIP= ?");
			list.add(qtlrIP);
		}
		if (!DataFormat.isEmpty(qbrNo)) {
			sb.append(" and log.brNo = ?");
			list.add(qbrNo);
		}

		if (!DataFormat.isEmpty(stdate)) {
			sb.append(" and log.createDate>=?");
			list.add(DateUtil.stringToDate2(stdate));
		}
		if (!DataFormat.isEmpty(endate)) {
			sb.append(" and log.createDate<?");
			list.add(DateUtil.getStartDateByDays(
					DateUtil.stringToDate2(endate), -1));
		}
		sb.append(" order by log.tlrNo");

		HQLDAO hqldao = BaseDAOUtils.getHQLDAO();

		PageQueryCondition queryCondition = new PageQueryCondition();
		queryCondition.setQueryString(sb.toString());
		queryCondition.setPageIndex(pageIndex);
		queryCondition.setPageSize(pageSize);
		queryCondition.setObjArray(list.toArray());
		PageQueryResult pageQueryResult = hqldao.pageQueryByQL(queryCondition);
		return pageQueryResult;
	}
}
