<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign info = Session["USER_SESSION_INFO"]>
<@CommonQueryMacro.page title="�û�����">
<@CommonQueryMacro.CommonQuery id="UserInfoEntry" init="true" submitMode="current">
	<table width="100%" align="left">
		<tr valign="center">
			<td valign="top" colspan="2">
				<@CommonQueryMacro.Interface id="intface" label="�������ѯ����" colNm=6 />
			</td>
		</tr>
	  	<tr>
	  		<td valign="top">
				<@CommonQueryMacro.PagePilot id="ddresult" maxpagelink="9" showArrow="true" />
			</td>
		 </tr>
		 <tr>
			 <td colspan="2">
				<@CommonQueryMacro.DataTable id ="datatable1" paginationbar="-,btAdd" 
					fieldStr="tlrno[60],tlrName[100],flag[55],status[55],lock[55],brname,lastaccesstm[150],lastlogouttm[150],opr[85]" 
					readonly="true" width="100%" hasFrame="true" height="280" />
			 </td>
		 </tr>
		 <tr align="center">
			<td>
				<div style="display:none">
					<@CommonQueryMacro.Button id= "btDel" />
					<@CommonQueryMacro.Button id= "btModify"/>
					<@CommonQueryMacro.Button id= "btDetail"/>
					<@CommonQueryMacro.Button id= "btResetPwd"/>
				 </div>
			</td>
		</tr>
	</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
    var currentTlrno = "${info.tlrNo}";
    var roleType = "${info.roleTypeList}";
    
    //��λһ����¼
    function locate(id) {
        var record = UserInfoEntry_dataset.find(["tlrno"], [id]);
        if (record) {
            UserInfoEntry_dataset.setRecord(record);
        }
    }

    function datatable1_opr_onRefresh(cell, value, record) {
        if (record && record != null) {
            var id = record.getValue("tlrno");
            var branchId = record.getValue("brno");
            var st = record.getValue("st");
            var innerStr = "<PRE>";
            //if (st == "1" || st == "2" || st == "3") {
            if (roleType.indexOf("12") >- 1 || roleType.indexOf("13") >- 1  || roleType.indexOf("14") >- 1 || roleType.indexOf("15") >- 1 ) {
                innerStr = innerStr + "<a style=\"color:#666666\" title=\"��¼�����������ܲ���\">�޸�</a> " +
                    " <a style=\"color:#666666\" title=\"��¼�����������ܲ���\">��������</a> " + "<a style=\"color:#666666\" title=\"��¼�����������ܲ���\">ɾ��</a>" + "</PRE>";
            } else {
                innerStr = innerStr + " <a href=\"JavaScript:btModifyShow('" + id + "')\">�޸�</a>" +
                    " <a href=\"JavaScript:resetPwd('" + id + "')\">��������</a>"  + 
                    " <a href=\"JavaScript:doDel('" + id + "')\">ɾ��</a> "  + "</PRE>";
            }
            cell.innerHTML = innerStr;
        } else {
            cell.innerHTML = "";
        }
    }
    
    function btModifyShow(id) {
		locate(id);
		btModify.click();
    }
    
	function btAdd_onClick() {
		locate(id);
		UserInfoEntry_dataset.insertRecord();
		UserInfoEntry_dataset.setParameter("op", "new");
		UserInfoEntry_dataset.setParameter("id", "0");
	}

    
    function resetPwd(tlrno) {
        if (tlrno == currentTlrno) {
            alert("���������Լ�������");
        } else {
            if (!confirm("ȷ��Ҫ���ø��û���������?")) {
                return;
            }
            locate(tlrno);
            btResetPwd.click();
        }
    }

    function btResetPwd_postSubmit(button) {
        var retParam = button.returnParam;
       	alert("�������óɹ�,��ʼ��Ϊ" + retParam.DefaultPWD);
        flushCurrentPage();
    }

    
    function doDel(id) {
        locate(id);
        btDel.click();
    }

    function btDel_onClickCheck(button) {
    	var del = UserInfoEntry_dataset.getValue("del");
		if (del == false) {
			if (confirm("ȷ��ɾ��������¼��")) {
				UserInfoEntry_dataset.setParameter("delet", "T");
				return true;
			} else {
				return false;
			}
		} else {
			if (confirm("ȷ�ϻָ�������¼��")) {
				UserInfoEntry_dataset.setParameter("delet", "F");
				return true;
			} else {
				return false;
			}
		}
    }
    
    function btDel_postSubmit(button) {
        alert("ɾ����¼�ɹ�");
        button.url = "#";
        //ˢ�µ�ǰҳ
        flushCurrentPage();
    }

    //չʾ�Աȹ��ܵ�js
    function datatable1_tlrno_onRefresh(cell, value, record) {
        if (record != null) {
            var sta = record.getValue("st");
            var tlrno = record.getValue("tlrno");
            cell.innerHTML = "<a href=\"Javascript:showDetail('" + tlrno + "','" + sta + "')\">" + tlrno + "</a>";
        } else {
            cell.innerHTML = ""
        }
    }

    function showDetail(id) {
		locate(id);
		btDetail.click();
        //showWin("�û���ϸ��Ϣ", "${contextPath}/fpages/blacklistManage/ftl/UserInfoDetail.ftl?op=detail&tlrno=" + id, "", "", window);
    }

    //ˢ�µ�ǰҳ
    function flushCurrentPage() {
    	UserInfoEntry_dataset.flushData(UserInfoEntry_dataset.pageIndex);
    }
    
    /* function btStatus_onClickCheck(button) {
        var status = UserInfoEntry_dataset.getValue("flag");
        if (status == '0') {
            if (confirm("ȷ�Ͻ����û�����Ϊ��Ч?")) {
                UserInfoEntry_dataset.setParameter("statu", "1");
                return true;
            } else {
                return false;
            }
        } else {
            if (confirm("ȷ�Ͻ����û�����Ϊ��Ч?")) {
                UserInfoEntry_dataset.setParameter("statu", "0");
                return true;
            } else {
                return false;
            }
        }
    }
    
    function btStatus_postSubmit(button) {
        alert("���óɹ�");
        flushCurrentPage();
    }

    function btLoginStatus_onClickCheck(button) {
        if (confirm("ȷ�Ͻ����û�ǿ��ǩ��?")) {
            UserInfoEntry_dataset.setParameter("statu", "logout");
            return true;
        } else {
            return false;
        }
    }
    
    function btLoginStatus_postSubmit(button) {
        alert("ǩ�˳ɹ�");
        flushCurrentPage();
    }

    function UserInfoEntry_dataset_dataset_afterScroll(dataset) {
        unLock.disable(dataset.getValue("lock") != 'true' || dataset.getValue("tlrno") == currentTlrno);
    }

    //ˢ������
    function flushPage() {
        bopAccDsRecordAD_dataset.flushData();
    }

    function winZone_onCloseCheck() {
        flushCurrentPage();
        return true;
    }

    function btAuthShow(tlrno) {
        var paramMap = new Map();
        var op = "auth";
        paramMap.put("tlrno", tlrno);
        paramMap.put("op", op);
        loadPageWindows("userWin", "��ɫ�趨", "/fpages/regonization/ftl/OperMngRoleInfo.ftl", paramMap, "winZone");
    }

    
    function unLock_onClickCheck(button) {
        UserInfoEntry_dataset.setParameter("tlrno", UserInfoEntry_dataset.getValue("tlrno"));
    }
    
    function unLock_postSubmit(button) {
        alert("�����ɹ���");
        flushCurrentPage();
    }*/
/*
    function UserInfoEntry_dataset_afterScroll(dataset) {
        unLock.disable(dataset.getValue("lock") != 'true');
        btLoginStatus.disable(dataset.getValue("status") != '1');
        btStatus.disable(false);
        if (dataset.getValue("tlrno") == currentTlrno) {
            btStatus.disable(true);
            unLock.disable(true);
            btLoginStatus.disable(true);
        }
        var st = dataset.getValue("st");
        if (st == "1" || st == "2" || st == "3") {
            btStatus.disable(true);
            unLock.disable(true);
            btLoginStatus.disable(true);
        }
    } 
*/
</script>
</@CommonQueryMacro.page>