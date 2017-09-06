<#import "/templets/commonQuery/CommonQueryTagMacro.ftl" as CommonQueryMacro>
<#assign opType=RequestParameters["opType"]?default("")>
<#assign info = Session["USER_SESSION_INFO"]>
<@CommonQueryMacro.page title="���л�����Ϣά��">
<@CommonQueryMacro.CommonQuery id="BankInfoManage" init="true" submitMode="current">
   <table align="center" width="100%">
	<tr>
		<td align="left">
				<@CommonQueryMacro.Group id="BankInfoManageGroup" label="���л�����Ϣά��"
	        			  fieldStr="brcode,brno,brname,address,postno,teleno" colNm=4/>
		</td>
	</tr>
	<tr >
		<td align="center">
	  		<@CommonQueryMacro.Button id="btSave" />
		 	&nbsp;&nbsp;&nbsp;&nbsp;
			<@CommonQueryMacro.Button id="btCancel" />
	  	</td>
	</tr>
</table>
</@CommonQueryMacro.CommonQuery>

<script language="javascript">
	
	var roleType = "${info.roleTypeList}";
	var opType = "${opType}";

	function initCallGetter_post(dataset) {
		var brcode = BankInfoManage_dataset.getValue("brcode");
		if (brcode == null || brcode == "") {
			BankInfoManage_dataset.setFieldReadOnly("brcode", false);
			//document.getElementById("unLock").style.disabled = "none";
			//document.getElementById("btResetPwd").style.disabled = "none";
		} else {
			BankInfoManage_dataset.setFieldReadOnly("brcode", true);
		}
	}

	function btSave_onClickCheck(button) {
		return checkValue();
	}

	function checkValue() {
		if (BankInfoManage_dataset.getValue("blnUpBrcode") == ""
				&& BankInfoManage_dataset.getValue("brclass") != "1") {
			alert("�ֶ�[�ϼ�����]��ӦΪ�ա�");
			return false;
		}

		if (BankInfoManage_dataset.getValue("brclass") == "") {
			alert("�ֶ�[��������]��ӦΪ�ա�");
			return false;
		}
		return true;
	}
	
	//�����ˢ�µ�ǰҳ
	function btSave_postSubmit(button) {
		alert("����ɹ�");
		button.url = "/fpages/regonization/ftl/BranchEntry.ftl";
	}
</script>
</@CommonQueryMacro.page>