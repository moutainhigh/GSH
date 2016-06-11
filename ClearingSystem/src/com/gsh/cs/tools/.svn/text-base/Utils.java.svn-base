package com.gsh.cs.tools;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Carey    
 * QQ: 454898498
 * E-mail:weibinbinlove@126.com
 * ����ʱ�䣺2010-11-18  ����04:05:51
 *
 *  δ�������Ͻ�
 *
 */
@SuppressWarnings({"unchecked","unused"})
public class Utils {

	public static String getTime(String date1,String date2) throws Exception{
		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");    
		   java.util.Date now = df.parse(date2);    
		   java.util.Date date=df.parse(date1);    
		   long l=now.getTime()-date.getTime();    
		   long day=l/(24*60*60*1000);    
		   long hour=(l/(60*60*1000)-day*24)+day*24;    
		   long min=((l/(60*1000))-day*24*60-hour*60);    
//		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);    
		   System.out.println(""+hour+":"+min);  
		   return ""+hour+":"+min;
	}
	
	
	public static float stringToFloat(String str){
		if(str!=null&&!str.trim().equals("")){
			return Float.valueOf(str.trim());
		}
		
		return 0f;
	} 
	
	
	/**
     * ��ȡ��ǰ���������ڼ�<br>
     * 
     * @param dt
     * @return ��ǰ���������ڼ�
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    public static String getRouteDateForChinese(Date dt) {
        String[] weekDays = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
        	w = 0;
        }
        String week="";
        if(w==0){
        	week = "周日";
        }else if(w==1){
        	week = "周一";
        }else if(w==2){
        	week = "周二";
        }else if(w==3){
        	week = "周三";
        }else if(w==4){
        	week = "周四";
        }else if(w==5){
        	week = "周五";
        }else if(w==6){
        	week = "周六";
        } 
        String[] a=String.valueOf(dt).split("-");
        return a[1]+"月"+a[2]+"日"+", "+week;
    }
	
    /**
     * ��ȡ��ǰ���ڵ�Ӣ���·�<br>
     * 
     * @param dt
     * @return ��ǰ���ڵ�Ӣ���·�
     */
    public static String getMOnthToEmonth(Date dt) {
        String[] weekDays = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG", "SEP", "OCT", "NOV", "DEC"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.MONTH);
        return weekDays[w];
    }
	
    /**
     * 转换日期形式为01JAN
     * 如果跨年则加上年份
     */
	public static String getDayMonth(Date dt){
		String[] weekDays = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG", "SEP", "OCT", "NOV", "DEC"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.MONTH);
        String month=weekDays[w];
        String day=String.valueOf(dt.getDate());
        if(day.length()==1){
        	day="0"+day;
        }
        return day+month;
	}
	
	
	
	/**
	 * ���ַ�ת����Stringʱ������(201077120000)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String dateToString(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss"); 
		Calendar calendar = Calendar.getInstance();
		
		return sd.format(calendar.getTime());
	}
	
	/**
	 * ���ַ�ת����stringʱ������(2010-7-7 12:00:00)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String dateToString(Date date){
		if(date==null){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 		
		return sd.format(date);
	}
	
	/**
	 * ���ַ�ת����Timestampʱ������(2010-7-7 12:00:00)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate(String date) {
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time;
		try {
			time = sd.parse(date);
			return new Timestamp(time.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���ַ�ת����Timestampʱ������(2010-7-7 12:00)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate2(String date) throws ParseException{
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time=sd.parse(date);
		return new Timestamp(time.getTime());
	}
	
	/**
	 * ���ַ�ת����sql.dateʱ������(2010-7-7)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringTosqlDate(String date) throws ParseException{
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date time=sd.parse(date);
		return new java.sql.Date(time.getTime());
	}
	
	/**
	 * ���ַ�ת����Timestampʱ������(20100707)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate5(String date) throws ParseException{
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Date time=sd.parse(date);
		return new Timestamp(time.getTime());
	}
	
	
	/**
	 * ��Timestampת�����ַ�����(2010-7-7)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String datesToTring(Date date) throws ParseException{
		if(date==null){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String time=sd.format(date);
		return time;
	}
	
	/**
	 * ��Timestampת�����ַ�����(2010-7)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String datesToTring1(Date date) throws ParseException{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		String time=sd.format(date);
		return time;
	}
	
	
	/**
	 * ���ַ�ת����Timestampʱ������(2010-7-7)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate1(String date) {
		if(isNullOrEmpty(date)){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date time=sd.parse(date);
			return new Timestamp(time.getTime());
		} catch (Exception e) {
			return null;
		}
		
		
	}
	/**
	 * ���ַ�ת����Timestampʱ������(2010/7/7)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate4(String date) throws ParseException{
		if(isNullOrEmpty(date)){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		Date time=sd.parse(date);
		return new Timestamp(time.getTime());
	}
	/**
	 * ���ַ�ת����Timestampʱ������(2010-7-7 HH:mm)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToDate3(String date) throws ParseException{
		if(isNullOrEmpty(date)){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time=sd.parse(date);
		return new Timestamp(time.getTime());
	}
	
	
	/**
	 * ��ȡ��ǰϵͳʱ��ʱ������(2010-7-7 16:19:00)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp getSysDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Timestamp.valueOf(sd.format(Calendar.getInstance().getTime()));
	}
	
	public static int compareTime(Date date1,Date date2){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date1);
		int d1=calendar.get(Calendar.DAY_OF_YEAR); 
		int y1=calendar.get(Calendar.YEAR);
		calendar.setTime(date2);
		int d2=calendar.get(Calendar.DAY_OF_YEAR); 
		int y2=calendar.get(Calendar.YEAR);
		if((y1-y2)<0){
			return -1;
		}else if((y1-y2)>0){
			return 1;
		}
		return d1-d2;
	}
	
	
	/**
	 *    ���Ǽ�ȥ������
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:24:46
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp getAddDate(int date){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i-date; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        System.out.println(sd.format(calendar.getTime()));
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		
		//System.out.println(time);
		return time;
	}
	
	/**
	 *    ���Ǽ�ȥ������
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:24:46
	 *
	 *  δ�������Ͻ�
	 * @throws ParseException 
	 *
	 */
	public static Date getDelDate(String dateTime,int date) throws ParseException{		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sd.parse(dateTime));
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i-date; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        System.out.println(sd.format(calendar.getTime()));
		return calendar.getTime();
	}
	
	
	
	
	
	/***
	 *   �Ӷ�����
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp getAddDate1(Date date,int day){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i+day; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        System.out.println(sd.format(calendar.getTime()));
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		
		//System.out.println(time);
		return time;
	}
	
	/***
	 *   �Ӷ�����
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp getAddDate1(int date){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i+date; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        System.out.println(sd.format(calendar.getTime()));
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		
		//System.out.println(time);
		return time;
	}
	
	/***
	 *   �Ӷ�����
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp timeDel(Timestamp t,int date){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(t);
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i+date; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		return time;
	}
	
	
		
	public static void deleteFile (String folder) {
        File folder1 = new File(folder);
        if (folder1.exists()) {
        	folder1.delete();
        }
    }
	
	
	
	
	
	
	/**
	 * ������ת������  "," �ָ����ַ�
	 *
	 */
	public static String arryToString(Object[] objs){
		String str="";
		for (Object object : objs) {
			str+=","+object;
		}
		if(str.length()>0)
			return str.substring(1);		
		return null;
	}
	
	/**
	 * ������ת����Integer[]
	 *
	 */
	public static Integer[] objectArryToIntegerArry(Object[] objs){
		Integer[] ints=new Integer[objs.length];
		for (int i=0;i<objs.length;i++) {
			ints[i]=Integer.parseInt(objs[i].toString()) ;
		}
		return ints;
	}
	

	/**
	 * ����ת��
	 * 
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2010-11-19  ����05:29:38
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static String URLDecoder(String str,String encoding){
		try {
			return java.net.URLDecoder.decode(str, encoding);			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * �����תΪ��д
	 *
	 */
	public static String numToChinese(String input){ 
		String mowei=input.substring(input.indexOf(".")+1);
		if ( mowei.equals("00") || mowei.equals("0")) {
			input=input.substring(0, input.indexOf("."));
		}
		String s1="��Ҽ��������½��ƾ�";      
		String s4="�ֽ���Ԫʰ��Ǫ��ʰ��Ǫ��ʰ��Ǫ";        
		String temp="";    
		String result="";       
		if(input==null) 
			return "�����ִ��������ִ�ֻ�ܰ��������ַ�('0'-'9')�������ִ����ֻ�ܾ�ȷ��Ǫ�ڣ�С���ֻ����λ��";      
		temp=input.trim();      
		
		float f;    
		try       
		{         
			f=Float.parseFloat(temp);      
			}         
		catch(Exception e) 
		{         
			return "�����ִ��������ִ�ֻ�ܰ��������ַ�('0'-'9')�������ִ����ֻ�ܾ�ȷ��Ǫ�ڣ�С���ֻ����λ��";        
			}             
		int len=0;       
		if(temp.indexOf(".")==-1) len=temp.length();      
		else len=temp.indexOf(".");      
		if(len>s4.length()-3) return "�����ִ����ֻ�ܾ�ȷ��Ǫ�ڣ�С���ֻ����λ��";        
		int n1,n2=0;     
		String num="";      
		String unit="";      
		for(int i=0;i<temp.length();i++)     
		{        
			if(i>len+2) {break;        
           }          
         if(i==len) {continue;}      
         n1=Integer.parseInt(String.valueOf(temp.charAt(i)));    
         num=s1.substring(n1,n1+1);      
         n1=len-i+2;        
         unit=s4.substring(n1,n1+1);      
         result=result.concat(num).concat(unit);      
         }              
		if((len==temp.length()) || (len==temp.length()-1))    
			result=result.concat("��");    
		if(len==temp.length()-2) result=result.concat("���");       
		return result;
		}
	
	
	/**  
	* @param date1 ��Ҫ�Ƚϵ�ʱ�� ����Ϊ��(null),��Ҫ��ȷ�����ڸ�ʽ ,�磺2009-09-12 
	* @param date2 ���Ƚϵ�ʱ��  Ϊ��(null)��Ϊ��ǰʱ��  
	* @param stype ����ֵ����   0Ϊ�����죬1Ϊ���ٸ��£�2Ϊ������  
	* @return  
	* ����
	* compareDate("2009-09-12", null, 0);//�Ƚ���
	* compareDate("2009-09-12", null, 1);//�Ƚ���
	* compareDate("2009-09-12", null, 2);//�Ƚ���
	*/ 

	public static int compareDate(String startDay,String endDay,int stype){  
		int n = 0;  
		String[] u = {"��","��","��"};  
		String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  

		endDay = endDay==null?getCurrentDate("yyyy-MM-dd"):endDay;  

		DateFormat df = new SimpleDateFormat(formatStyle);  
		Calendar c1 = Calendar.getInstance();  
		Calendar c2 = Calendar.getInstance();  
		try {  
		c1.setTime(df.parse(startDay));  
		c2.setTime(df.parse(endDay));  
		} catch (Exception e3) {  
		System.out.println("wrong occured");  
		}  
		//List list = new ArrayList();  
		while (!c1.after(c2)) {                   // ѭ���Աȣ�ֱ����ȣ�n ������Ҫ�Ľ��  
		//list.add(df.format(c1.getTime()));    // ������԰Ѽ�������ڴ浽������ ��ӡ����  
		n++;  
		if(stype==1){  
		c1.add(Calendar.MONTH, 1);          // �Ƚ��·ݣ��·�+1  
		}  
		else{  
		c1.add(Calendar.DATE, 1);           // �Ƚ���������+1  
		}  
		}  
		n = n-1;  
		if(stype==2){  
		n = (int)n/365;  
		}     
		System.out.println(startDay+" -- "+endDay+" ������"+u[stype]+":"+n);        
		return n;  
		}   

		public static String getCurrentDate(String format){
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE,0); 
		SimpleDateFormat sdf=new SimpleDateFormat(format);//"yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
		}
	
	public static boolean isNullOrEmpty(Timestamp date){
		return date==null;
	}
	public static boolean isNullOrEmpty(Date date){
		return date==null;
	}
	public static boolean isNullOrEmpty(Object[] obj){
		return obj==null||obj.length==0;
	}
	
	public static boolean isNullOrEmpty(List list){
		return list==null||list.size()==0;
	}
	
	public static boolean isNullOrEmpty(Set set){
		return set==null||set.size()==0;
	}
	
	public static boolean isNullOrEmpty(Map set){
		return set==null||set.size()==0;
	}
	
	
	public static boolean isNullOrEmpty(String str){
		return (str==null || str.trim().isEmpty());
	}
	
	public static boolean isNullOrEmpty(Double obj){
		return (obj==null || obj==0);
	}
	public static boolean isNullOrEmpty(Float obj){
		return (obj==null || obj==0);
	}
	public static boolean isNullOrEmpty(Long obj){
		return (obj==null || obj==0L);
	}
	
	
	public static boolean isNullOrEmpty(Integer obj){
		return (obj==null || obj==0);
	}
	
	public static ArrayList createArrayList(){
		return new ArrayList(0);
	}
	
	public static String isNull(String str){
		if(!isNullOrEmpty(str)){
			return str;
		}
		
		return null;
	}
	
	/**
	 * ��ȡ�����
	 * @param num    Ϊ���ͷ��ؼ�λ       ��num<1ʱ   ����null
	 * @return
	 */
	public static String getRandom(int num){
		if(num<1){
			return null;
		}
		StringBuffer str=new StringBuffer();
        for(int i=0;i<6;i++)
        {
        	str.append((int)(Math.random()*10));
        }
        System.out.println(str);
        return str.toString();
	}
	
	public static String getString(String str){
		if(!Utils.isNullOrEmpty(str)){
			return str.trim();
		}
		return null;
	}
	//ȥ���ַ�����пո�
	public static String removeAllSpace(String s){  
		 String endString = "";        
		 // ���ȥ��ո����ַ�      
		 for (int i = 0; i<s.length(); i++) {  
			 // �����ַ��е�ÿ���ַ�    
			 char c = s.charAt(i);        
			 // ���һ���ַ�       
			 if (c != KeyEvent.VK_SPACE){   
				 // �����ַ��ǿո�         
				 endString += String.valueOf(c); 
				 // ���Ϊ�µ��ַ�              }     
				 }        
		} 
	 return endString;   
	 }
//	public static void main(String[] args) {
//		Utils.getRandom();
//	}
	//��������ʱ��֮�������
	public static int getIntervalDays(Date startday,Date endday){       
	       if(startday.after(endday)){   
	            Date cal=startday;   
	            startday=endday;   
	            endday=cal;   
	       }          
	       long sl=startday.getTime();   
	       long el=endday.getTime();         
	       long ei=el-sl;             
	       return (int)(ei/(1000*60*60*24));   
	}
	
	
	/***
	 * 
	 * 
	 * ���·ݵ�Ӣ����дת�������ֵ��ַ�
	 * 
	 * m ��  JAN��FEB��MAR��APR��MAY��JUN��JUL��AUG
	 *      SEP��OCT��NOV��DEC
	 * 
	 * return ��  01��02��03��04��05��06��07��08��09
	 *           10��11��12 
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2010-12-31  ����02:11:55
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static String getMonth(String m){
		if("JAN".equals(m)){
			return "01";
		}else if("FEB".equals(m)){
			return "02";
		}else if("MAR".equals(m)){
			return "03";
		}else if("APR".equals(m)){
			return "04";
		}else if("MAY".equals(m)){
			return "05";
		}else if("JUN".equals(m)){
			return "06";
		}else if("JUL".equals(m)){
			return "07";
		}else if("AUG".equals(m)){
			return "08";
		}else if("SEP".equals(m)){
			return "09";
		}else if("OCT".equals(m)){
			return "10";
		}else if("NOV".equals(m)){
			return "11";
		}else if("DEC".equals(m)){
			return "12";
		}		
		return null;
	}
	
	/***
	 *   ��ȡʱ��   
	 *   mm:  �·�
	 *   dd��  ����
	 *   
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 * @throws ParseException 
	 *
	 */
	public static Timestamp stringToDate2(String mm,String dd) throws ParseException{		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR); 
        int m = calendar.get(Calendar.MONTH);
        if(m<Integer.parseInt(mm)){
        	y=y-1;
        }
        Timestamp time = stringToDate1(y+"-"+mm+"-"+dd);	
		return time;
	}
	
	/***
	 *   ��ȡʱ��   
	 *   mm:  �·�
	 *   dd��  ����
	 *   mm�·�С�ڵ�ǰʱ����·�  �������+1
	 *   
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 * @throws ParseException 
	 *
	 */
	public static Timestamp stringToDate3(String mm,String dd) throws ParseException{		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR); 
        int m = calendar.get(Calendar.MONTH);
        int mm1=Integer.parseInt(mm);
        if(m>mm1&&(mm1+12)<=(m+3)){
        	y=y+1;
        }
        Timestamp time = stringToDate1(y+"-"+mm+"-"+dd);	
		return time;
	}
	
	/**
	 * ��ȡ���
	 *    ��mm>С��ϵͳ��ǰ�·�    AND  mm+12<=(m+3)   �����=ϵͳ��ǰ���+1
	 * 
	 * 
	 * @param mm   �·�
	 * @return
	 */
	public static String getYear(String mm) {
		Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR); 
        int m = calendar.get(Calendar.MONTH);
        int mm1=Integer.parseInt(mm);
        if(m>mm1&&(mm1+12)<=(m+3)){
        	y=y+1;
        }
		return String.valueOf(y);
	}
	//ȥ��ո�͡�-��
	public static String formartStr(String str) {
		String [] strs = str.split(" ");
		if(strs!=null&&strs.length>0){
			String ddd = "";
			for(int i = 0;i<strs.length;i++){
				ddd+=strs[i];
			}
			str = ddd;
		}
		String []strs1 = str.split("-");
		if(strs1!=null&&strs1.length>0){
			String ddd = "";
			for(int i = 0;i<strs1.length;i++){
				ddd+=strs1[i];
			}
			str = ddd;
		}
		return str;
	}
	
	/***
	 *   �Ӷ��ٷ���
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:25:20
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp getAddMM(int mm){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.MINUTE); 
        i=i+mm; 
        calendar.set(Calendar.MINUTE, i); 
        System.out.println(sd.format(calendar.getTime()));
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		return time;
	}
	
	/**
	 *    ���ǼӶ�����
	 *    ��ʽ  2012-01-05 00:00:00
	 *    
	 * @author Carey    
	 * QQ: 454898498
	 * E-mail:weibinbinlove@126.com
	 * ����ʱ�䣺2011-1-19  ����11:24:46
	 *
	 *  δ�������Ͻ�
	 *
	 */
	public static Timestamp getAddDate3(int date){		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR); 
        i=i+date; 
        calendar.set(Calendar.DAY_OF_YEAR, i); 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(sd.format(calendar.getTime()));
        Timestamp time = Timestamp.valueOf(sd.format(calendar.getTime()));	
		
		//System.out.println(time);
		return time;
	}
	
	/**
	 *    �ж��ַ����Ƿ��ָ���ַ�
	 *
	 */
	public static Boolean StringBstr(String str,String strl){		
		int result  = str.indexOf(strl);
		if(result==-1){
			return false;
		}
		return true;
	}
	
	 /**
     * ��ȡӢ���·ݵ�����<br>
     * 
     * @param dt
     * @return Ӣ���·ݲ���
     */
    public static String getEmonthToNum(String mon) {
        String[] eMonth = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG", "SEP", "OCT", "NOV", "DEC"};
        String[] cMonth = {"01", "02", "03", "04", "05", "06", "07","08", "09", "10", "11", "12"};
        for (int i = 0; i < eMonth.length; i++) {
			if(eMonth[i].equals(mon)){
				return cMonth[i];
			}
		}
        return null;
    }
    
    
    /***
	 * �ַ� תASCII 16���Ʊ���
	 * @param code
	 * @return
	 */
	public static String getAscii16(String str){
		String result="";
		byte[] b;
		try {
			b = str.getBytes("gb2312");
			for (int i = 0; i < b.length; i++)
			{
			    String hex = Integer.toHexString(b[i] & 0xFF);
			    if (hex.length() == 1){
			        hex = '0' + hex;
			    }
			    result += hex.toUpperCase();
			    }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/***
	 * �ַ� תASCII 16���Ʊ�����˵���|��
	 * @param code
	 * @return
	 */
	public static String getA16(String str){
		byte[] b;
		try {
			b = str.getBytes("gb2312");
			str="";
			for (int i = 0; i < b.length; i++)
			{
				if(b[i]=='|'){
					str +="|";
					System.out.println(b[i]);
					continue;
				}
			    String hex = Integer.toHexString(b[i] & 0xFF);
			    if (hex.length() == 1){
			        hex = '0' + hex;
			    }
			    str += hex.toUpperCase();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	public static String byte2hex(byte[] b){// ������ת�ַ�

	String hs = "";

	String stmp = "";

	for (int n = 0; n < b.length; n++) {

	stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

	if (stmp.length() == 1)
	hs = hs + "0" + stmp;
	else
	hs = hs + stmp;

	}

	return hs;

	}

	public static byte[] hex2byte(String str) { // �ַ�ת������

	if (str == null)

	return null;

	str = str.trim();

	int len = str.length();

	if (len == 0 || len % 2 == 1)

	return null;

	byte[] b = new byte[len / 2];

	try {

	for (int i = 0; i < str.length(); i += 2) {

	b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();

	}

	return b;

	} catch (Exception e) {

    return null;

    }

    }
	/**
	 * �ж��ַ��Ƿ�Ӣ��
	 * */
	public static boolean strIsEnglish(String word) {  
        boolean sign = true; // ��ʼ����־ΪΪ'true'  
        for (int i = 0; i < word.length(); i++) {  
            if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')  
                    && !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')) {  
                return false;  
            }  
        }  
        return true;  
    }  
	
	public static Long[] stringsToLongs(String[] strs){
		Long[] longs=new Long[strs.length];
		for (int i = 0; i < longs.length; i++) {
			longs[i]=Long.valueOf(strs[i]);
		}
		return longs;
	}
	
	/**
	 * 根据获得的下标获得颜色代码
	 * @param num
	 * @return
	 */
	public static String getColorCode(Integer num){
		String[] colors={"#FFFFFF","#FFB5B5","#FFE4CA","#FFFFCE","#CEFFCE","#D9FFFF","#DDDDFF","#E2C2DE",
				"#FFFFFF","#FFB5B5","#FFE4CA","#FFFFCE","#CEFFCE","#D9FFFF","#DDDDFF","#E2C2DE"};
		return colors[num];
	}
}
