package com.liusy.datapp.service.aop;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.liusy.core.util.LogUtil;
import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.util.PageQuery;
import org.apache.log4j.MDC;


/**
 * 日志aop实现类
 * @author andy
 *
 */
public class LogAopService implements MethodInterceptor, InitializingBean {
	private  Logger logger = Logger.getLogger(this.getClass());
	private static String OPERTYPE_SYNTHESIS="02";
	public Object invoke(MethodInvocation methodinvocation) throws Throwable {
		Object[] beforeobj=methodinvocation.getArguments();
		String metholdname=methodinvocation.getMethod().getName();
		String className=methodinvocation.getThis().getClass().getSimpleName();
		System.out.println("metholdname="+metholdname);
		System.out.println("className="+className);
		if(className.contains("Service")){
			//证明在Service
			
		}
		else if(className.contains("Dao")){
			//证明在Dao层
			StringBuffer condiStr=new StringBuffer();
			if(className.startsWith("Synthesis")){
				condiStr.append(beforeobj[0].toString());
				MDC.put("optType", OPERTYPE_SYNTHESIS);
			} 
//				else {
//				BaseDao dao = (BaseDao) methodinvocation.getThis();
//				String tableName = dao.getTableName();
//				System.out.println("tableName=" + tableName);
//				// queryBySelectId  拦截底层的查询语句
//				if (metholdname.equalsIgnoreCase("queryBySelectId")) {
//					// 含pageQuery的方法
//					PageQuery pq = (PageQuery) beforeobj[0];
//					Map condi = pq.getParameters();
//					Collection entry = condi.values();
//					Iterator it = entry.iterator();
//					while (it.hasNext()) {
//						String value = it.next().toString();
//						// System.out.println("{"+value+"}");
//						condiStr.append("{" + value + "}");
//					}
//					System.out.println("用pagequery查询了。。。。。。");
//				} else if (metholdname.equalsIgnoreCase("delete")
//						|| metholdname.equalsIgnoreCase("get")
//						|| metholdname.equalsIgnoreCase("load")
//						|| metholdname.equalsIgnoreCase("save")
//						|| metholdname.equalsIgnoreCase("saveOrUpdate")
//						|| metholdname.equalsIgnoreCase("update")
//						|| metholdname.equalsIgnoreCase("refresh")) {
//					condiStr.append(beforeobj[0].toString());
//				} else if (metholdname.equalsIgnoreCase("countByField")
//						|| metholdname.equalsIgnoreCase("deleteByField")
//						|| metholdname.equalsIgnoreCase("findByField")) {
//					condiStr.append("{ " + beforeobj[0].toString() + "="
//							+ beforeobj[1].toString() + " }");
//				} else {
//					//TODO do nothing
//				}
//			}
			String condit=condiStr.toString().replaceAll("'","''");
			System.out.println(" condiStr="+condiStr.toString());
			MDC.put("queryCondi", condit);
			System.err.println(MDC.getContext().toString());
			logger.debug("");
			LogUtil.clearMap();
		}
		else{
			 //TODO do noting
		}

		return methodinvocation.proceed();
	}

	public void afterPropertiesSet() throws Exception {

	}

}
