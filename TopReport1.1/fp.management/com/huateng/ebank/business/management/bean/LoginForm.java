/*
 * LoginForm.java
 * Created by yuweihua on 2004-10-18
 */
package com.huateng.ebank.business.management.bean;

import com.huateng.ebank.framework.struts.HTBaseForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Form bean for the Login main page. There are two fields on this form used for
 * authentication
 * <ul>
 * <li>username - the username to login
 * <li>password - the password to authenticate
 * </ul>
 */
public final class LoginForm extends HTBaseForm {

	private String userName = null;
	private String passWord = null;
	private String brCode = null;

	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}

	/**
	 * Get the userName
	 *
	 * @return String
	 */
	public String getUserName() {
		return (userName);
	}

	public String getPassWord() {
		if (passWord != null) {
			try {
				return new String(new BASE64Decoder().decodeBuffer(passWord));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (passWord);
	}

	/**
	 * Set the userName.
	 *
	 * @param userName
	 */
	public void setUserName(String newUserName) {
		userName = newUserName;
	}

	public void setPassWord(String newPassWord) {
		passWord = newPassWord;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		userName = null;
		passWord = null;
	}

	/**
	 * Validate the properties that have been set from this HTTP request, and
	 * return an <code>ActionErrors</code> object that encapsulates any
	 * validation errors that have been found. If no errors are found, return
	 * <code>null</code> or an <code>ActionErrors</code> object with no
	 * recorded error messages.
	 *
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return (errors);
	}

}