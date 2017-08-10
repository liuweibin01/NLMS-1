/*
 * ==================================================================
 * The Huateng Software License
 *
 * Copyright (c) 2004-2005 Huateng Software System.  All rights
 * reserved.
 * ==================================================================
 */
package com.huateng.ebank.business.opermng.getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resource.bean.pub.TlrInfo;
import resource.dao.pub.TlrInfoDAO;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.business.common.DAOUtils;
import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.business.common.service.BctlService;
import com.huateng.ebank.framework.report.common.ReportConstant;
import com.huateng.ebank.framework.util.DataFormat;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;

/**
 * @author zhiguo.zhao
 *
 */
public class OperMngEntryGetter extends BaseGetter {

	public Result call() throws AppException {
		try {

			this.setValue2DataBus(ReportConstant.QUERY_LOG_BUSI_NAME, "用户管理查询");

			PageQueryResult pageResult = getData();
			ResultMng.fillResultByList(getCommonQueryBean(), getCommQueryServletRequest(), pageResult.getQueryResult(),
					getResult());
			result.setContent(pageResult.getQueryResult());
			result.getPage().setTotalPage(pageResult.getPageCount(getResult().getPage().getEveryPage()));
			result.init();
			this.setValue2DataBus(ReportConstant.QUERY_LOG_BUSI_NAME, "用户管理查询");
			return result;
		} catch (AppException appEx) {
			throw appEx;
		} catch (Exception ex) {
			throw new AppException(Module.SYSTEM_MODULE, Rescode.DEFAULT_RESCODE, ex.getMessage(), ex);
		}
	}

	protected PageQueryResult getData() throws Exception {
		String qtlrnoName = (String) getCommQueryServletRequest().getParameterMap().get("qtlrnoName");
		String qtlrno = (String) getCommQueryServletRequest().getParameterMap().get("qtlrno");
		PageQueryResult pageQueryResult = new PageQueryResult();
		GlobalInfo globalinfo = GlobalInfo.getCurrentInstance();
		TlrInfoDAO dao = DAOUtils.getTlrInfoDAO();
		List tlrInfoList = new ArrayList();
		String hql = "1=1 ";
		if (!DataFormat.isEmpty(qtlrnoName)) {
			hql += "and po.tlrName = '" + qtlrnoName + "' ";
		}
		if (!DataFormat.isEmpty(qtlrno)) {
			hql += "and po.tlrno like '%" + qtlrno + "%' ";
		}
		hql += " order by po.tlrno";
		tlrInfoList = dao.queryByCondition(hql);

		int pageIndex = getResult().getPage().getCurrentPage();
		int pageSize = getResult().getPage().getEveryPage();
		int maxIndex = pageIndex * pageSize;
		/** 对最后一页的处理 */
		if (maxIndex > tlrInfoList.size()) {
			maxIndex = tlrInfoList.size();
		}
		List rs = new ArrayList();
		rs = tlrInfoList.subList((pageIndex - 1) * pageSize, maxIndex);
		for (Iterator it = rs.iterator(); it.hasNext();) {
			TlrInfo tlrInfo1 = (TlrInfo) it.next();
			if (tlrInfo1.getBrcode() != null) {
				tlrInfo1.setBrno(BctlService.getInstance().getExtBrno(tlrInfo1.getBrcode()));
			}
		}
		pageQueryResult.setTotalCount(tlrInfoList.size());
		pageQueryResult.setQueryResult(rs);
		return pageQueryResult;
	}
}
