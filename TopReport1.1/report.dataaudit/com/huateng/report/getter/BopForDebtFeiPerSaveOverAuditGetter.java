package com.huateng.report.getter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.huateng.common.err.Module;
import com.huateng.common.err.Rescode;
import com.huateng.commquery.result.Result;
import com.huateng.commquery.result.ResultMng;
import com.huateng.ebank.business.common.PageQueryResult;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.report.common.ReportConstant;
import com.huateng.ebank.framework.web.commQuery.BaseGetter;
import com.huateng.exception.AppException;
import com.huateng.report.service.BopForDebtYinTuanService;

/**
 *
 * @author shishu.zhang
 *
 *         2012-8-15上午10:54:59
 */
@SuppressWarnings("unchecked")
public class BopForDebtFeiPerSaveOverAuditGetter extends BaseGetter {

	public Result call() throws AppException {
		try {
			PageQueryResult pageQueryResult = getData();

			setValue2DataBus(ReportConstant.QUERY_LOG_BUSI_NAME, "外债-非居民个人存款审核-变动信息查询");

			ResultMng.fillResultByList(getCommonQueryBean(), getCommQueryServletRequest(),
					pageQueryResult.getQueryResult(), getResult());
			result.setContent(pageQueryResult.getQueryResult());
			result.getPage().setTotalPage(pageQueryResult.getPageCount(getResult().getPage().getEveryPage()));
			result.init();
			return result;
		} catch (CommonException e) {
			throw new AppException(Module.SYSTEM_MODULE, Rescode.DEFAULT_RESCODE, e.getMessage());
		} catch (AppException appEx) {
			throw appEx;
		} catch (Exception ex) {
			throw new AppException(Module.SYSTEM_MODULE, Rescode.DEFAULT_RESCODE, ex.getMessage(), ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public PageQueryResult getData() throws CommonException, IllegalAccessException, InvocationTargetException {
		int pageSize = getResult().getPage().getEveryPage();
		int pageIndex = getResult().getPage().getCurrentPage();
		Map map = getCommQueryServletRequest().getParameterMap();
		String startdate = (String) map.get("startdate");
		String enddate = (String) map.get("enddate");

		String qactiontype = (String) map.get("qactiontype");
		String qrecStatus = (String) map.get("qrecStatus");

		String qapproveStatus = (String) map.get("qapproveStatus");
		String qrepStatus = (String) map.get("qrepStatus");

		String filler2 = (String) map.get("filler2");
		BopForDebtYinTuanService debtYinTuanService = BopForDebtYinTuanService.getInstance();
		return debtYinTuanService.queryAuditFeiPerSave("over", pageIndex, pageSize, startdate, enddate, qactiontype,
				qrecStatus, qapproveStatus, qrepStatus, filler2);
	}
}
