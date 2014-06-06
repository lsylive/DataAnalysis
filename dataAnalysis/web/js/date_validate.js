/**
 * check whether input is numeric.
 * @param  string  form/field name
 * @return boolean
 */
function IsNumeric(f,fld)
{
   var form=document.forms[f];
   var obj=form.item(fld);

   if(obj==null)
      return false;

   if(obj.value=="")
      return true;

   reg=/^\d{1,8}(\.\d{0,4})?$/gi;

   return reg.test(obj.value);
}

/**
 * check whether input is numeric.
 * @param  string  form/field name
 * @return boolean
 */
function IsNumeric(stringValue)
{
	if(stringValue == null)
		return true;

   reg=/^\d{1,8}(\.\d{0,4})?$/gi;
   return reg.test(stringValue);
}

/**
 * check whether input is validate date.
 * if not, focus the field, and make the filed selected
 * @param  string  form/field name(NOT control object)
 * @return boolean
 * @note   call this function in onblur
 */
function validateDate(fld)
{
	var oDate = document.forms[0].item(fld);	// the date input control
	if(oDate == null)
	{
		return true;
	}
	var sDate = oDate.value;				// date in String
	if(sDate == "")
	{
		return true;
	}

	if(!isDateString(sDate, "-"))	//not validate date
	{
		alert('无效日期格式，请按“年年年年-月月-日日”或“年年年年月月日日”格式输入。');
		oDate.value = "";
		oDate.focus();
		return false;
	}
	if (sDate.length == 8)
	{
		sDate = sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8);
	}
	oDate.value = sDate;
	return true;
}

/**
 * multi control using the same name
 * @param  string  form/field name(NOT control object)
 * @param  string  index  index of the control (01, 02......)
 * @return boolean
 * @note   call this function in onblur
 */
function validateDate(fld, index)
{
	var oMultiDate = document.forms[0].item(fld);	// the date input control
	var nIndex = index;

	if(oMultiDate == null)	//field not exist
	{
		return true;
	}

	if(oMultiDate.length > 1) // multi
	{
		oDate = oMultiDate[nIndex-1];
	}
	else
	{
		oDate = oMultiDate;
	}

	if(oDate == null )
	{
		return true;
	}

	var sDate = oDate.value;				// date in String
	if(sDate == "")
	{
		return true;
	}

	if(!isDateString(sDate, "-"))	//not validate date
	{
		alert('无效日期格式，请按“年年年年-月月-日日”或“年年年年月月日日”格式输入。');
		oDate.focus();
		oDate.value = "";
		return false;
	}
	if (sDate.length == 8)
	{
		sDate = sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8);
	}
	oDate.value = sDate;
	return true;
}

/*
 * validate a date, the date must be seperate by "-" ,like 2002-2-2
 * @param String
 *
 * @return boolean
 */
function isDateString(sDate)
{
	return isDateString(sDate, "-");
}

/*
 * validate a date
 * @param String sDate string date to validate
 * @param String sSpit split
 *
 * @return boolean
 */
function isDateString(sDate, sSpit)
{	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
	var iaDate = new Array(3);
	var year, month, day;

	if (sDate.length == 8)
	{
		sDate = sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8);
	}
	iaDate = sDate.split(sSpit);
	if (iaDate.length != 3) return false;
	if (iaDate[1].length > 2 || iaDate[2].length > 2) return false;

	//check numeric
	if(!IsNumeric(iaDate[0]) || !IsNumeric(iaDate[1]) || !IsNumeric(iaDate[2]) )
	{
		return false;
	}

	year = parseFloat(iaDate[0]);
	month = parseFloat(iaDate[1]);
	day=parseFloat(iaDate[2]);

	if (year < 1900 || year > 2200) return false;
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false;
	if (day < 1 || day > iaMonthDays[month - 1]) return false;
	return true;
}


//==================================数据检查方法======================================================

function isNaNStr(value)                                
{
	if (value =="") return false; else return isNaN(value);
}

function checkEmpty(txtField,fieldName)                     //检查一个字段是否允许为空。
{
	if (txtField.value=="") {
		alert("请输入“"+ fieldName +"”");
		txtField.focus();
		return false;
	}
	return true;
}

function checkNumber(txtField,fieldName)                       //检查一个字段是否是数字。 
{
	if(isNaNStr(txtField.value)) {
		alert("请在“"+ fieldName +"”中输入有效数字!");
		txtField.focus();
		txtField.select();
		return false;
	}
	return true;
}

function checkGreaterThanZero(txtField,fieldName)                       //检查一个字段是否大于0。 
{
  if (txtField.value<0){
		alert("不能在“"+ fieldName +"”中输入负数!");
		txtField.focus();
		txtField.select();
		return false;
	}
	return true;
}

function checkLength(txtField,fieldName,maxLength)                       //检查一个字段长度。 
{
	if(maxLength<=0) return true;
	var str=txtField.value;
	if(str==null) return true;
	
	nLen = str.length;   
  for(i=0;i<str.length;i++) if(str.charCodeAt(i)>255) nLen++;   

  if (nLen>maxLength){
		alert("“"+ fieldName +"”中输入的数据太长!");
		txtField.focus();
		return false;
	}
	return true;
}
//==========================================================================================


function checkNumeric(txtField,fieldName,maxlength,chkZero,chkEmpty)   // 检查数字字段
{
   var res = false;
   if(chkEmpty)  {
   	  res = checkEmpty(txtField,fieldName);
   	  if(!res) return false;
   }
   res = checkNumber(txtField,fieldName);
   if(!res) return false;
   if(chkZero)  {
   	  res = checkGreaterThanZero(txtField,fieldName);
   	  if(!res) return false;
   }
   res = checkLength(txtField,fieldName,maxlength);
   if(!res) return false;
   return true;   	  
}

function checkString(txtField,fieldName,maxlength,chkEmpty)    // 检查字符字段
{
   if(chkEmpty)  {
   	  res = checkEmpty(txtField,fieldName);
   	  if(!res) return false;
   }
   res = checkLength(txtField,fieldName,maxlength);
   if(!res) return false;
	 return true;
}

function checkDate(txtField,fieldName,chkEmpty)    // 检查日期字段
{
   if(chkEmpty)  {
   	  res = checkEmpty(txtField,fieldName);
   	  if(!res) return false;
   }
   if (txtField.value=="") return true;
   if (!isDateString(txtField.value,"-")){
  		alert("在“"+fieldName+"”中输入无效的日期格式，请按“YYYY-MM-DD”的格式输入");
		  return false;
	 }
	 return true;
}

function checkEmpty(txtField,fieldName)                     //检查一个字段是否允许为空。
{
	if (txtField.value=="") {
		alert("请输入“"+ fieldName +"”");
		txtField.focus();
		return false;
	}
	return true;
}

function showsrc()  {
	alert(window.document.body.innerHTML);
}
//验证表单所有Input是否输入了至少一个条件 expressctl 排除的控件id或name,以,分隔
function checkIfInput(expressctl){
	var ctrl=document.getElementsByTagName("input");
	var ctrl1=document.getElementsByTagName("select");
	var isInput=false;
	var temp;
	for(i=0;i<ctrl.length;i++){
		temp = (ctrl[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
		
		//alert("temp_ctrl is '" + temp + "'");
		//alert("expressctl.indexOf(ctrl[i].id) '" + expressctl.indexOf(ctrl[i].id) + "'");
		//alert("expressctl.indexOf(ctrl[i].name) '" + expressctl.indexOf(ctrl[i].name) + "'");
		
		if((expressctl.indexOf(ctrl[i].id)==-1 && expressctl.indexOf(ctrl[i].name)==-1) && ''!=temp){
			isInput=true;
		}
		
		//alert("isInput-ctrl " + isInput);
	}
	for(i=0;i<ctrl1.length;i++){
		temp = (ctrl1[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
		
		//alert("temp_ctrl1 is '" + temp + "'");
		
		if(expressctl.indexOf(ctrl1[i].name)==-1 && ''!=temp){
			isInput=true;
		}
		
		//alert("isInput-ctrl1 " + isInput);
	}
	if(!isInput){
		alert("请至少需要输入一个不为空的查询条件或选择一个下拉框的选项");
		return false;
	}else
		return true;
}
//检查控件是否有输入内容   objList 以,分隔的id或name
function checkIsNull(objList) {
       var isEleName=false;
        var objs=objList.split(",");
       
        for (i=0; i<objs.length;i++){
        	var obj=document.getElementById(objs[i]);
        	if(document.getElementsByName(objs[i])){
        		obj=document.getElementsByName(objs[i]);
        		isEleName=true;
        	}
        	if(!obj){
        		alert('找不到:'+objs[i])
        		return false;
        	}else if (!isEleName) {
        		if( "" == obj.value){
               		alert( '你没有输入"' + obj.title+'"'  );
                	if(obj.style.display != "none" ) 
                 		obj.focus();
                }
                return false;
            }else if(isEleName){
            	var isHasElement=false;
            	var objlen=obj.length;
            	for(j=0;j<objlen;j++){
            		if('' != obj[j].value)
            		{
            			if(obj[j].type=="radio" || obj[j].type=="checkbox")
            			{
            				if(obj[j].checked)
            					isHasElement=true;
            			}else
            				isHasElement=true;
            		}
            	}
            	if(!isHasElement){
            		alert( '你没有输入或选择"' + obj[0].title+'"' );
                	if(obj.style.display != "none" ) 
                 		obj.focus();	
            	}
            }
        }
        return true;
    }
