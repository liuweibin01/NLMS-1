/**
 * 
 */
package com.huateng.report.imports.model;

import java.io.Serializable;

/**
 * 字段值
 * 
 * @author chl_seu
 * 
 */
public class TFieldValueInfo implements Serializable {
	public String FieldName; // 字段名称
	public int FieldLength; // 字段长度
	public String FieldValue; // 字段值
	public String DataType; // 字段类型
	public String bUpdateFlag; // 入库标志
}
