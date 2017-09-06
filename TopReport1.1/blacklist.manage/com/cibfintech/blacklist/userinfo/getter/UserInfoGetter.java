package com.cibfintech.blacklist.userinfo.getter;

import java.util.Date;
import java.util.List;

import resource.bean.pub.RoleInfo;
import resource.bean.pub.TlrOperateLog;

import com.cibfintech.blacklist.userinfo.service.UserInfoService;
import com.cibfintech.blacklist.userinfo.service.UserOperateLogService;
import com.cibfintech.blacklist.util.GenerateID;
import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.business.common.SystemConstant;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.report.common.ReportConstant;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;
import com.huateng.service.pub.UserMgrService;

@SuppressWarnings("unchecked")
public class UserInfoGetter extends BaseGetter {
	/*
	 * 获取商行黑名单
	 * 
	 * @author huangcheng
	 */
	@Override
	public Result call() throws AppException {
		try {
			this.setValue2DataBus(ReportConstant.QUERY_LOG_BUSI_NAME, "用户信息查询");
			PageQueryResult pageResult = getData();
			ResultMng.fillResultByList(getCommonQueryBean(), getCommQueryServletRequest(), pageResult.getQueryResult(), getResult());
			result.setContent(pageResult.getQueryResult());
			result.getPage().setTotalPage(pageResult.getPageCount(getResult().getPage().getEveryPage()));
			result.init();
			return result;
		} catch (AppException appEx) {
			throw appEx;
		} catch (Exception ex) {
			throw new AppException(Module.SYSTEM_MODULE, Rescode.DEFAULT_RESCODE, ex.getMessage(), ex);
		}
	}

	protected PageQueryResult getData() throws Exception {
		String userNo = getCommQueryServletRequest().getParameter("qtlrno");
		String userName = getCommQueryServletRequest().getParameter("qtlrnoName");

		int pageSize = this.getResult().getPage().getEveryPage();
		int pageIndex = this.getResult().getPage().getCurrentPage();

		GlobalInfo globalinfo = GlobalInfo.getCurrentInstance();
		List<RoleInfo> roleInfos = UserMgrService.getInstance().getUserRoles(globalinfo.getTlrno());
		boolean isSuperManager = false;
		for (RoleInfo roleInfo : roleInfos) {
			if (roleInfo.getRoleType().equals(SystemConstant.ROLE_TYPE_SYS_MNG)) {
				isSuperManager = true;
			}
		}

		PageQueryResult pqr = UserInfoService.getInstance().pageQueryByHql(pageSize, pageIndex, userNo, userName, isSuperManager, globalinfo);
		String message = "用户信息管理:brNo=" + userNo + ",brName=" + userName;
		recordOperateLog(globalinfo, pqr, message);
		return pqr;
	}

	// 记录查询日志
	private void recordOperateLog(GlobalInfo globalinfo, PageQueryResult pqr, String message) {
		UserOperateLogService service = UserOperateLogService.getInstance();
		TlrOperateLog bean = new TlrOperateLog();
		bean.setBrNo(globalinfo.getBrno());
		bean.setId(String.valueOf(GenerateID.getId()));
		bean.setQueryType("");
		bean.setQueryRecordNumber(String.valueOf(null == pqr ? "0" : pqr.getTotalCount()));
		bean.setTlrIP(globalinfo.getIp());
		bean.setTlrNo(globalinfo.getTlrno());
		bean.setOperateType(SystemConstant.LOG_QUERY);
		bean.setMessage(message);
		bean.setCreateDate(new Date());
		try {
			service.addEntity(bean);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

}
