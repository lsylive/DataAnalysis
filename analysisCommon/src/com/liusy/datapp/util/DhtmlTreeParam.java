


//   DhtmlTreeParam.java

package com.liusy.datapp.util;


public class DhtmlTreeParam
{

	private String idField;
	private String nameField;
	private String userDataFields[];
	private String userDataNames[];
	private String tagName;
	private String imgPath;

	public String getIdField()
	{
		return idField;
	}

	public void setIdField(String idField)
	{
		this.idField = idField;
	}

	public String getNameField()
	{
		return nameField;
	}

	public void setNameField(String nameField)
	{
		this.nameField = nameField;
	}

	public String[] getUserDataFields()
	{
		return userDataFields;
	}

	public void setUserDataFields(String userDataFields[])
	{
		this.userDataFields = userDataFields;
	}

	public String getTagName()
	{
		return tagName;
	}

	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	public DhtmlTreeParam(String idField, String nameField, String tagName, String imgPath)
	{
		this.idField = idField;
		this.nameField = nameField;
		this.tagName = tagName;
		this.imgPath = imgPath;
	}

	public DhtmlTreeParam(String idField, String nameField, String tagName, String userDataFields[], String userDataNames[], String imgPath)
	{
		this.idField = idField;
		this.nameField = nameField;
		this.tagName = tagName;
		this.userDataFields = userDataFields;
		this.userDataNames = userDataNames;
		this.imgPath = imgPath;
	}

	public String[] getUserDataNames()
	{
		return userDataNames;
	}

	public void setUserDataNames(String userDataNames[])
	{
		this.userDataNames = userDataNames;
	}

	public String getImgPath()
	{
		return imgPath;
	}

	public void setImgPath(String imgPath)
	{
		this.imgPath = imgPath;
	}
}
