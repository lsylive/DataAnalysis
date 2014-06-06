/**************************************************
GetSelectedCount(theForm,sName) 获取Selected 的个数
Input:
  theForm:表单对象
  sName:select名称

Output:
  int (CheckBox 的 Selected 的个数)



***************************************************/
function getSelectedCount(theForm,sName){
  var obj;
  var iCount=0;
	
  for ( iLoop=0;iLoop<theForm.length;iLoop++) {
    obj = theForm.elements[iLoop];
	if ((obj.name==sName)&&(obj.checked)){
        iCount++;
	}
  }
  return iCount;

}
function deleteSelectedItem(theForm,sName){
	 var obj=document.getElementsByName(sName)[0];
	 obj.remove(obj.selectedIndex); 
}

	

/**
 * 向select对象增加optinon,
 * oSelect :Select 对象
 * sDisplay:以","号分隔的String,作option显示用
 * sValue:以","号分隔的String,作为option值
*/
function addSelectValue(oSelect,sDisplay,sValue){
    var aDisplay=sDisplay.split(";");
    var aValue=sValue.split(";");
    var iCount=aValue.length;
    var iLoop=0;
    var pos = 0 ;

    for(iLoop=0;iLoop<iCount;iLoop++){
        pos = 0 ;
        var oOption = document.createElement("OPTION");
        oSelect.options.add(oOption);
        pos = aDisplay[iLoop].indexOf("|");
        if ( pos > 0 ) {
            oOption.innerText = aDisplay[iLoop].substring(0,pos);
            if (aValue[iLoop].indexOf("|") > -1 ) {
                //如果放入Select中的Value也是有 “|”这个符号的就取前部分
                oOption.Value = aValue[iLoop].substring(0,pos);
            }else{
                oOption.Value = aValue[iLoop];
            }
        }else{
            oOption.innerText = aDisplay[iLoop];
            oOption.Value = aValue[iLoop];
        }
    }
}

function strComp(str1,str2){
	if(str1.length!=str2.length){
		return false;
	}
	for(i=0;i<str1.length;i++){
		if(str1.charAt(i)!=str2.charAt(i)){
			return false;
		}
	}
	return true;
}




/*********************************************
GetSelectedValue(theForm,sName) 获取Selected值
Input:
  theForm:表单对象
  sName:select名称

Output:
  string(以“，”号分隔的值串)


***********************************************/

function getSelectedValue(theForm,sName){
  var obj;
  var value="";
  //alert(theForm.length+sName);
  obj=document.getElementsByName(sName)[0];
  for ( iLoop=0;iLoop<obj.length;iLoop++) {
      value+=obj.options[iLoop].value+",";
	}
  return value.substr(0,value.length-1);
}

function getSelectedIndex(theForm,sName)
{
   var obj=theForm.sName;
   var index=obj.selectedIndex;
   return index;
}



/********************************************
SetCheckBox(theForm,sCBName,vValue)

Input:
 theForm:表单对象
 sCBName:CheckBox名称
 vValue:要SET的CheckBox 的值

Output:
   int(Checked过的数目  )


**********************************************/

function setCheckBox(theForm,sCBName,vValue){
  var obj;
  var iCount=0;

  for ( iLoop=0;iLoop<theForm.length;iLoop++) {
    obj = theForm.elements[iLoop];
	if ((obj.name==sCBName)&&(obj.value==vValue)){
      obj.checked=true;
      iCount++;
	}


  }
  return iCount;
}

/********************************************
setAllCheckBox(form, name, state, flag)

Input:
	form:  表单对象
	name:  checkbox名称
	state: 要set成的状态(true/false)
	flag:  是否要检查该对象是否disabled(true/false)

Output:
	int(checked过的数目)


**********************************************/
function setAllCheckBox(form, name, state, flag)
{
	var obj;
	var count = 0;

	for(var i = 0; i < form.length; i++)
	{
		obj = form.elements[i];

		if(obj.name == name && (!flag || !obj.disabled))
		{
			obj.checked = state;
			count++;
		}
	}

	return count;
}



/********************************************
SetInputValue(theForm,sInputName,vValue)
Input:
  theForm:表单对象
  sName:select名称

Output:
  Boolean(成功与否)


*********************************************/
function setInputValue(theForm,sInputName,vValue){
  var obj;
  var bSet=false;
//alert();
  for ( iLoop=0;iLoop>theForm.length;iLoop++) {
    obj = theForm.elements[iLoop];
	//alert(obj.name);
	if (obj.name==sInputName){
      switch (obj.type){

        case "checkbox" :
		  if (obj.value==vValue){
		    obj.checked=true;
		    bSet=true;

		  }
		  break;

        case "radio" :
          if (obj.value==vValue){
		    obj.checked=true;
		    bSet=true;
		  }
		  break;

	case "select" :
		  for(iInLoop=0;iInLoop<obj.length;iInLoop++){
			if (obj.options [iInLoop].value==vValue) {
				//alert(obj.options [iInLoop].value);
			  obj.selectedIndex =iInLoop;
			}
		  }
		  bSet=true;
		  break;

		default:
		  obj.value=vValue;
          bSet=true;

	   }
	 //break;
	}
  }
  return bSet;
}



/**
*  取Form里的对象
*  tTheForm:Form对象
*  tObjName:对象名
* /
  function getObjByName(tTheForm,tObjName) {
       myform = tTheForm;
       for ( iLoop=0;iLoop<tTheForm.length;iLoop++) {
           obj = tTheForm.elements[iLoop];
           if (obj.name==tObjName){
               return obj;
           }
        }
    }





/*********************
IsDate(sDate)
  是否合法日期
Input:
  sDate:以'-'号分隔的日期字符串 如：'1999-5-6'

Return:
  boolean


**********************/
function isDate(sDate){
  var re='[0-9]{4}-[0-1]{0,1}[0-9]-[0-1]{0,1}[0-9]';
  return (sDate.match(re)!=null) ;

}




function getRadioValue(theForm,sInputName){
  var obj;
  var bMatch=false;

  for ( iLoop=0;iLoop<theForm.length;iLoop++) {
    obj = theForm.elements[iLoop];
	if (obj.name==sInputName){
	  if (obj.checked==true){
		return obj.value;
	  }
	}
  }
}


/**
 * 判断输入的字符是符合要求
 * 输入的字符串
 * 输入范围字符串
 * 符合反回true否则 false
 * **/
function checkStr(aSource,aLand){
    var checkOK = aLand;
    var checkStr = aSource;
    var allValid = true;
    var decPoints = 0;
    var allNum = "";
    for (i = 0;  i < checkStr.length;  i++)        {
        ch = checkStr.charAt(i);
        for (j = 0;  j < checkOK.length;  j++)  if (ch == checkOK.charAt(j))  break;
        if (j == checkOK.length)  {
            allValid = false;
            break;
        }
        if (ch == ".")   {
            allNum += ".";
            decPoints++;
        }else if (ch != ",")  allNum += ch;
    }
    if (!allValid) {
        return (false);
    }else{
        return (true);
    }
}

/**
 * 是否已经选了当单选框
 * 返回：true 已经选中
 *       false 没有选中
 * **/
function checkRadioIsSelect(theForm,objName) {
    var obj;
    var bMatch=false;
    var iLoop;
    for ( iLoop=0;iLoop<theForm.length;iLoop++) {
        obj = theForm.elements[iLoop];
        if (obj.name==objName){
            if (obj.checked==true){
                bMatch=true;
            }
        }
    }
    if (bMatch == false)   
    {
      return (false);
    }
    else
    {
        return (true);
    }
}



/**
 * 使得ObjNameRadio 得到焦点
 * **/
function setRadioFocus(theForm,objName){
     var obj;
     var iLoop;
      for ( iLoop=0;iLoop<theForm.length;iLoop++) {
        obj = theForm.elements[iLoop];
            if (obj.name==objName){
              obj.focus();
              return;
            }
      }
}





/**
LogicalValue:用于判断对象的值是否符合条件，现已提供的选择有：
integer：整型，还可判断正整型和负整型
number ：数值型，同样可判断正负
date ：日期型，可支持以自定义分隔符的日期格式，缺省是以'-'为分隔符
string ：判断一个字符串包括或不包括某些字符
返回值：
true或false

参数：
ObjStr ：对象标识符——对象名；
ObjType：对象类型('integer','number','date','string'之一)

其他说明：
当对象值为空时，则返回错误。



例子：
example 1:要求检验输入框text1的输入数据是否是“整型”数据，若不是，则提示
if (!LogicalValue('text1','integer')) alert('Error: Your must input a integer number');
example 2:要求检验输入框text1的输入数据是否是“正整型”数据，若不是，则提示
if (!LogicalValue('text1','integer','+')) alert('Error: Your must input a positive integer number');
example 3:要求检验输入框text1的输入数据是否是“负整型”数据，若不是，则提示
if (!LogicalValue('text1','integer','-')) alert('Error: Your must input a negative integer number');
exmaple 4:要求检验输入框text1的输入数据是否是数值，若不是，则提示
if (!LogicalValue('text1','number')) alert('Error: Your must input a number');
exmaple 5:要求检验输入框text1的输入数据是否是“正”数值，若不是，则提示
if (!LogicalValue('text1','number','+')) alert('Error: Your must input a number');
exmaple 6:要求检验输入框text1的输入数据是否是“负”数值，若不是，则提示
if (!LogicalValue('text1','number','-')) alert('Error: Your must input a number');
example 7:要求检验输入框text1的输入数据是否是日期型，若不是，则提示
if (!LogicalValue('text1','date')) alert('Error: Your must input a logical date value');
若它的分隔符为A，“A”是一个变量，如常用的'-'和'/'，则用如下语法
if (!LogicalValue('text1','date',A)) alert('Error: Your must input a logical date value');
或当分隔符为'/'时
if (!LogicalValue('text1','date','/')) alert('Error: Your must input a logical date value');
当分隔符为'-'时，可不要参数'-'，可带上
example 8:要求检验输入框text1的输入表示颜色的字符串是否合理，若不合理，则提示
if (!LogicalValue('text1','string','0123456789ABCDEFabcdef')) alert('Error: Your must input a logical color value');
example 9:要求检验输入框text1的输入表示密码的字符串是否不含“'"@#$%&^*”这些字符，若含有，则提示
if (!LogicalValue('text1','string','\'"@#$%&^*',false)) alert('Error: Your password can not contain \'"@#$%&^*');
其中false表示不包含有某些字符，true表示必须是哪些字符，缺省值为true
*/


function logicalValue(ObjStr,ObjType){
    var str='';
    if ((ObjStr==null) || (ObjStr=='') || ObjType==null){
        alert('函数LogicalValue缺少参数');
        return false;
    }
    var obj = document.all(ObjStr);
    if (obj.value=='') return false;
    for (var i=2;i<arguments.length;i++){
        if (str!='')
        str += ',';
        str += 'arguments['+i+']';
    }
    str=(str==''?'obj.value':'obj.value,'+str);
    var temp=ObjType.toLowerCase();
    if (temp=='integer'){
        return eval('IsInteger('+str+')');
    }else if (temp=='number'){
        return eval('IsNumber('+str+')');
    }else if (temp=='string'){
        return eval('SpecialString('+str+')');
    }else if (temp=='date'){
        alert(str);
        return eval('IsDateL('+str+')');
    }else{
        alert('"'+temp+'"类型在现在版本中未提供');
        return false;
    }
}

/**
IsInteger: 用于判断一个数字型字符串是否为整形，
还可判断是否是正整数或负整数，返回值为true或false
string: 需要判断的字符串
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断

sample:
var a = '123';
if (IsInteger(a))
{
alert('a is a integer');
}
if (IsInteger(a,'+'))
{
alert(a is a positive integer);
}
if (IsInteger(a,'-'))
{
alert('a is a negative integer');
}
*/

function isInteger(string ,sign){
    var integer;
    if ((sign!=null) && (sign!='-') && (sign!='+')){
        alert('IsInter(string,sign)的参数出错：\nsign为null或"-"或"+"');
        return false;
    }
    integer = parseInt(string);
    if (isNaN(integer)){
        return false;
    }else if (integer.toString().length==string.length){
        if ((sign==null) || (sign=='-' && integer<0) || (sign=='+' && integer>0)){
            return true;
        }else return false;
    }else return false;
}

/**
IsDate: 用于判断一个字符串是否是日期格式的字符串

返回值：
true或false

参数：
DateString： 需要判断的字符串
Dilimeter ： 日期的分隔符，缺省值为'-'


sample:
var date = '1999-1-2';
if (IsDate(date))
{
alert('You see, the default separator is "-");
}
date = '1999/1/2";
if (IsDate(date,'/'))
{
alert('The date\'s separator is "/");
}
*/


function isDateL(DateString , Dilimeter){
    if (DateString==null) return false;
    if (Dilimeter=='' || Dilimeter==null) Dilimeter = '-';
    var tempy='';
    var tempm='';
    var tempd='';
    var tempArray;
    if (DateString.length<8 && DateString.length>10) return false;
    tempArray = DateString.split(Dilimeter);
    if (tempArray.length!=3) return false;
    if (tempArray[0].length==4){
        tempy = tempArray[0];
        tempd = tempArray[2];
    }else{
        tempy = tempArray[2];
        tempd = tempArray[1];
    }
    tempm = tempArray[1];
    var tDateString = tempy + '/'+tempm + '/'+tempd+' 8:0:0';//加八小时是因为我们处于东八区
    var tempDate = new Date(tDateString);
    if (isNaN(tempDate)) return false;
    if (((tempDate.getUTCFullYear()).toString()==tempy) && (tempDate.getMonth()==parseInt(tempm)-1) && (tempDate.getDate()==parseInt(tempd)))
    {
        return true;
    }else{
        return false;
    }
}

/**
IsNumber: 用于判断一个数字型字符串是否为数值型，
还可判断是否是正数或负数，返回值为true或false
string: 需要判断的字符串
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断

sample:
var a = '123';
if (IsNumber(a))
{
alert('a is a number');
}
if (IsNumber(a,'+'))
{
alert(a is a positive number);
}
if (IsNumber(a,'-'))
{
alert('a is a negative number');
}
*/

function isNumber(string,sign){
    var number;
    if (string==null) return false;
    if ((sign!=null) && (sign!='-') && (sign!='+')){
        alert('IsNumber(string,sign)的参数出错：\nsign为null或"-"或"+"');
        return false;
    }
    number = new Number(string);
    if (isNaN(number)){
        return false;
    }else if ((sign==null) || (sign=='-' && number<0) || (sign=='+' && number>0))   {
        return true;
    }else return false;
}



/**
SpecialString: 用于判断一个字符串是否含有或不含有某些字符

返回值：
true或false

参数：
string ： 需要判断的字符串
compare ： 比较的字符串(基准字符串)
BelongOrNot： true或false，“true”表示string的每一个字符都包含在compare中，
“false”表示string的每一个字符都不包含在compare中


sample1:
var str = '123G';
if (SpecialString(str,'1234567890'))
{
alert('Yes, All the letter of the string in \'1234567890\'');
}
else
{
alert('No, one or more letters of the string not in \'1234567890\'');
}
结果执行的是else部分
sample2:
var password = '1234';
if (!SpecialString(password,'\'"@#$%',false))
{
alert('Yes, The password is correct.');
}
else
{
alert('No, The password is contain one or more letters of \'"@#$%\'');
}
结果执行的是else部分
*/
function specialString(string,compare,BelongOrNot){
    if ((string==null) || (compare==null) || ((BelongOrNot!=null) && (BelongOrNot!=true) && (BelongOrNot!=false)))
    {
        alert('function SpecialString(string,compare,BelongOrNot)参数错误');
        return false;
    }
    if (BelongOrNot==null || BelongOrNot==true){
        for (var i=0;i<string.length;i++)   {
            if (compare.indexOf(string.charAt(i))==-1)    return false
        }
        return true;
    } else{
    for (var i=0;i<string.length;i++)    {
        if (compare.indexOf(string.charAt(i))!=-1)     return false
    }
    return true;
    }
}





    //使用方法
    function showOjb(theForm){
		
		var  checkIsNullFieldList = "wenhao_int,total_area";
		
        if (checkIsNull(theForm,checkIsNullFieldList)){
            alert( " null ok" )
        }else{
            alert(" null lost " );
        }

		var  checkIsNumberFiledList = "wenhao_int,total_area";
        if (checkIsNumber(theForm,checkIsNumberFieldList,"0123456789")){
            alert( " number ok" )
        }else{
            alert(" number lost " );
        }


        if (checkIsSelected(theForm,"plan_year_e")){
            alert( " selected ok" )
        }else{
            alert(" selected lost " );
        }


        if (checkIsDate(theForm,"city_seal_date")){
            alert( " date ok" )
        }else{
            alert(" date lost " );
        }

    }



    /**
     * 检查Ｏｂｊｌｉｓｔ中的对象是否为空
     * theForm:当前Ｆｏｒｍ
     * ＯｂｊＬｉｓｔ：要检查的对象的ｎａｍｅ的列表。
     */
     

   /* function checkIsNull( theForm,objList) {
        var coll = theForm.elements;
        var s ="";
        var checkLoop = 0 ;
        if (coll!=null) {
            for (checkLoop=0; checkLoop<coll.length;checkLoop++){
                if (coll.item(checkLoop).name!="" && objList.indexOf(coll.item(checkLoop).name) > -1  ) {
                    var obj = coll.item(checkLoop);
                    if (obj.value == "" ) {                    	
                        alert( "你没有输入" + obj.title  );
                        if(obj.style.display != "none" ) {
                        	obj.focus();
                        }
                        return (false);
                    }
                }
            }
        }
        return (true);
    }*/
    function checkIsNull( theForm,objList) {
        if(theForm.elements==null||objList=="")
        	return (true);
        var objs=objList.split(",");
        for (i=0; i<objs.length;i++){
        	var obj=theForm.elements[objs[i].replace(/\s+/gi,"")];
        	if(!obj){
        		alert('找不到:'+objs[i])
        		return false;
        	}else if (obj.value == "" ) {                    	
               	alert( "你没有输入" + obj.title  );
                if(obj.style.display != "none" ) {
                 	obj.focus();
                }
                return (false);
            }
        }
        return (true);
    }
	
    /**
     * 检查Ｏｂｊｌｉｓｔ中的对象是选择了一个值
     * theForm:当前Ｆｏｒｍ
     * ＯｂｊＬｉｓｔ：要检查的对象的ｎａｍｅ的列表。
     */

    function checkIsSelected( theForm,objList) {
        var coll = theForm.elements;
        var s ="";
        var checkLoop = 0 ;
        if (coll!=null) {
            for (checkLoop=0;checkLoop <coll.length;checkLoop++){
                if (coll.item(checkLoop).name!="" && objList.indexOf(coll.item(checkLoop).name) > -1  ) {
                    var obj = coll.item(checkLoop);
                    if (obj.selectedIndex == 0 ) {
                        alert("请选择"  + obj.title );
                      	if ( obj.style.display != "none" ) {
                        	obj.focus();
                        }
                        return (false);
                    }
                }
            }
        }
        return (true);
    }
/*********************************************
GetCheckBoxValue(theForm,sName,aSp) 获取checkBox值
Input:
  theForm:表单对象
  sName:checkBox名称

Output:
  string(以 aSp 号分隔的值串)
aSp
  反回的分隔符


***********************************************/

function GetCheckBoxValue(theForm,sName,aSp){
  var obj;
  var value="";
  //alert(theForm.length+sName);
  for ( iLoop=0;iLoop<theForm.length;iLoop++) {
    obj = theForm.elements[iLoop];
    if ((obj.name==sName)&&(obj.checked)){
        value+=obj.value+aSp;
    }
  }
  return value.substr(0,value.length-1);
}


    /**
     * 检查Ｏｂｊｌｉｓｔ中的对象是为有效日期
     * theForm:当前Ｆｏｒｍ
     * ＯｂｊＬｉｓｔ：要检查的对象的ｎａｍｅ的列表。
     */

    function checkIsDate( theForm,objList) {
        var coll = theForm.elements;
        var s ="";
        var checkLoop = 0 ;

        if (coll!=null) {
            for (checkLoop=0; checkLoop<coll.length;checkLoop++){
                if (coll.item(checkLoop).name!="" && objList.indexOf(coll.item(checkLoop).name) > -1  ) {
                    var obj = coll.item(checkLoop);
                    if ( isDate(obj.value)  == false ) {
                        alert(  obj.title+"输入有误");
                        if ( obj.style.display != "none" ) {                        
                        	obj.focus();
                        }
                        return (false);
                    }
                }
            }
        }
        return (true);
    }

/**
 * 检查formItem的name是否在objList中出现
 *
 * 该方法为了避免之前简单地使用indexOf()所出现的错误
 * 其它在前台验证form的方法都可以改用这个方法
 *
 * 如果objList中存在name的话，返回name在objList中的index；否则，返回-1
 *
 * 
 */
function checkNameList(name, objList)
{
	if (objList == null || objList == "" || name == null || name == "")
		return -1;

	var nameListReg = new RegExp("(^|,) *(" + name + ") *(,|$)");
	var arr = null;

	if ((arr = nameListReg.exec(objList)) != null)
		return objList.indexOf(arr[2], arr.index);

	return -1;
}

/**
 * 检查Ｏｂｊｌｉｓｔ中的对象是否为一个指定字符串的字符
 * theForm:当前Ｆｏｒｍ
 * ＯｂｊＬｉｓｔ：要检查的对象的ｎａｍｅ的列表。
 * CheckModel : 可以输入的字符列表。
 */
function checkIsNumber(theForm, objList, checkModel)
{
	var numStr = checkModel;
	var coll = theForm.elements;
	var s = "";
	var checkLoop = 0;

	if (coll != null)
	{
		for (checkLoop = 0; checkLoop < coll.length; checkLoop++)
		{
			var obj = coll.item(checkLoop);

			if (checkNameList(obj.name, objList) < 0)
				continue;

			if (obj.value == null || obj.value == "")
				continue;

			if (!checkStr(obj.value , numStr))
			{
				alert(obj.title + "中只能输入以下字符'"  + numStr + "'");

				if (obj.style.display != "none")
					obj.focus();

				return false;
			}
		}
	}

	return true;
}
    function CheckEmail(theForm,objList){ 
    	 var coll = theForm.elements;
        var checkLoop = 0 ;
        if (coll!=null) {
            for (checkLoop=0; checkLoop<coll.length;checkLoop++){
                var obj = coll.item(checkLoop);
 
                if (obj.name!="" && objList.indexOf(obj.name) > -1  ) {
										var pattern = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
 										flag = pattern.test(obj.value); 
                    if(!flag){
                    	alert(obj.title+'框输入电子邮件地址非法');
                    	obj.focus();
                    	return (false); 
                    }
                }
            }
        }
        return (true);
}

    
/**
  *获取当前日期 1900-01-01
  *
  */  
function getCurDate()
{
    theDate=new Date();
	var year=theDate.getYear();
	var month=theDate.getMonth()+1;
	var day=theDate.getDate();
	if(month<10)
	{
	  month='0'+month;
	}
	if(day<10)
	{
	  day='0'+day;
	}
	var rdate=year+'-'+month+'-'+day;
	return rdate;
}

// this function gets the cookie, if it exists
function getCookie( name ) {	
	var start = document.cookie.indexOf( name + "=" );
	var len = start + name.length + 1;
	if ( ( !start ) &&
	( name != document.cookie.substring( 0, name.length ) ) )
	{
	return null;
	}
	if ( start == -1 ) return null;
	var end = document.cookie.indexOf( ";", len );
	if ( end == -1 ) end = document.cookie.length;
	return unescape( document.cookie.substring( len, end ) );
}
function setCookie( name, value, expires, path, domain, secure ) 
{
	// set time, it's in milliseconds
	var today = new Date();
	today.setTime( today.getTime() );
	
	/*
	if the expires variable is set, make the correct 
	expires time, the current script below will set 
	it for x number of days, to make it for hours, 
	delete * 24, for minutes, delete * 60 * 24
	*/
	if ( expires )
	{
	expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date( today.getTime() + (expires) );
	
	document.cookie = name + "=" +escape( value ) +
	( ( expires ) ? ";expires=" + expires_date.toGMTString() : "" ) + 
	( ( path ) ? ";path=" + path : "" ) + 
	( ( domain ) ? ";domain=" + domain : "" ) +
	( ( secure ) ? ";secure" : "" );
}
//删除cookie 
function delCookie(name) 
{ 
var exp = new Date(); 
exp.setTime(exp.getTime() - 1); 
var cval=getCookie(name); 
if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
} 

	function submitToUrl(frm,url){
		frm.action=url;
		frm.submit();
	}

	function openwin(url,width,height){
		var title="";
		var xposition=0;    
		var yposition=0;  
		if((parseInt(navigator.appVersion)>=4)){  
        	xposition = Math.floor((screen.width - width) / 2);  
        	yposition = Math.floor((screen.height - height-25) / 2);  
		}   		
		var style='width='+width+',height='+height+',top='+yposition+',left='+xposition+', top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no';
		window.open (url, title, style);
	}

//Add to favorite
function AddFavorite(title,url,desc)
{
	if ((typeof window.sidebar == 'object') && (typeof window.sidebar.addPanel == 'function'))//Gecko
	{
		window.sidebar.addPanel(title,url,desc);
	}
	else//IE
	{
		window.external.AddFavorite(url,title);
	}
}

	function getEncodedUrl(){
		var url=window.location.pathname+window.location.search;
		return escape(encode_base64(url));
	}
	
	function getDecodedUrl(encodedURL){
		alert(unescape(encodedURL));
		return decode_base64(unescape(encodedURL));
	}
	
	function dyniframesize(){
		var dyniframe=new Array()
		for (i=0; i<iframeids.length; i++){
			if (document.getElementById){
				//自动调整iframe高度
				dyniframe[dyniframe.length] = document.getElementById(iframeids[i]);
				if (dyniframe[i] && !window.opera){
					dyniframe[i].style.display="block"
					if(dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape
						dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight+100; 
				    else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight) //如果用户的浏览器是IE
					{
					dyniframe[i].height = dyniframe[i].Document.body.scrollHeight+50;
	  	    		}
			    }
		   	}
   			//根据设定的参数来处理不支持iframe的浏览器的显示问题
			if ((document.all || document.getElementById) && iframehide=="no"){
				var tempobj=document.all? document.all[iframeids[i]] : document.getElementById(iframeids[i])
				tempobj.style.display="block"
			}
		}
	}	
function getFormAsString(formName){
        
    //Setup the return String
     returnString ="";
        
    //Get the form values
    formElements=document.forms[formName].elements;
        
     //loop through the array, building up the url
    //in the format '/strutsaction.do&name=value'
  
     for(var i=formElements.length-1;i>=0; --i ){
           //we escape (encode) each value
           returnString+="&" 
           +escape(formElements[i].name)+"=" 
           +escape(formElements[i].value);
    }
        
     //return the values
     return returnString; 
  }
function addAddPanel(url,title) 
{ 
   if (window.sidebar) { 
	window.sidebar.addPanel(title, url,""); 
} else if( document.all ) {
	window.external.AddFavorite( url, title);
} else if( window.opera && window.print ) {
return true;
}


} 
function copyToClipboard(txt) {    
     if(window.clipboardData) {    
             window.clipboardData.clearData();    
             window.clipboardData.setData("Text", txt);  
	      alert('复制成功,请粘贴到你的文件中');  
     } else if(navigator.userAgent.indexOf("Opera") != -1) {    
          window.location = txt;    
     } else if (window.netscape) {    
          try {    
               netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");    
          } catch (e) {    
               alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");    
          }    
          var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);    
          if (!clip)    
               return;    
          var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);    
          if (!trans)    
               return;    
          trans.addDataFlavor('text/unicode');    
          var str = new Object();    
          var len = new Object();    
          var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);    
          var copytext = txt;    
          str.data = copytext;    
          trans.setTransferData("text/unicode",str,copytext.length*2);    
          var clipid = Components.interfaces.nsIClipboard;    
          if (!clip)    
               return false;    
          clip.setData(trans,null,clipid.kGlobalClipboard);   
	  alert('复制成功,请粘贴到你的QQ/MSN上推荐给你的好友');
     }    
}   
function showhidediv(container)
	{
		if(document.getElementById(container).style.display=='none')
			document.getElementById(container).style.display='';
		else
			document.getElementById(container).style.display='none';
	}


function SetHome(obj,vrl){
        try
        {
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
                        } 
                        catch (e) { 
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
}