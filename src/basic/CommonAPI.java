package basic;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class CommonAPI {
	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
		test6();
	}
	
	/**
	 * Math.round(11.5)等于多少？Math.round(- 11.5) 又等于多少?
	 * 四舍五入的原理是在参数上加0.5然后进行取整
	 * */

	/**
	 * switch是否能作用在byte 上，是否能作用在long上，是否能作用在String上?
	 * 之所以能使用byte short char是因为存在自动类型转换
	 * expression也可以是 enum 类型
	 * expression还可以是字符串（String）
	 * 但是长整型（long）在目前所有的版本中都是不可以的
	 * */

	
	/**
	 * 数组有没有length()方法？String有没有length()方法？
	 * 数组没有length()方法，而是有length属性
	 * String有length()方法
	 * */
	
	
	/**
	 * String 、StringBuilder 、StringBuffer 的区别？
	 * String 是只读字符串，也就意味着 String 引用的字符串内容是不能被改变的
	 * StringBuffer/StringBuilder 表示的字符串对象可以直接进行修改
	 * StringBuilder是Java5中引入的，它和StringBuffer的方法完全相同，
	 * 区别在于它是在单线程环境下使用的，因为它的所有方法都没有被 synchronized 修饰，
	 * 因此它的效率理论上也比 StringBuffer要高
	 * */
	
	/**
	 * String 对象的 intern()方法会得到字符串对象在常量池中对应的版本的引用
	 * 如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用
	 * 
	 * 字符串的+操作其本质是创建了 StringBuilder 对象进行 append 操作，
	 * 然后将拼接后的  StringBuilder  对象用 toString 方法处理成 String 对象
	 * */
	static void test1() {
		String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
		
        System.out.println(s1 == s2);  //false
        System.out.println(s1 == s5);  //true
        System.out.println(s1 == s6);  //false
        System.out.println(s1 == s6.intern());  //true
        System.out.println(s2 == s2.intern());  //false
		
	}
	
	/**
	 * 如何取得年月日、小时分钟秒？
	 * */
	static void test2() {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)); // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));
        
        //java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue()); // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
	}
	
	/**
	 * 如何取得从 1970 年 1 月 1 日 0 时 0 分 0 秒到现在的毫秒数？
	 * */
	static void test3() {
		System.out.println("第一种：" + Calendar.getInstance().getTimeInMillis());
        System.out.println("第二种：" + System.currentTimeMillis());
        System.out.println("第三种：" + Clock.systemDefaultZone().millis());
        
        System.out.println("第三种：" + Clock.systemDefaultZone().millis());
        System.out.println("第二种：" + System.currentTimeMillis());
        System.out.println("第一种：" + Calendar.getInstance().getTimeInMillis());
	}
	
	
	/**
	 * 如何取得某月的最后一天？
	 * */
	static void test4() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为 1 号，当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("first:" + first);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("last:" + last);
        //Java 8
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天" + firstday);
        System.out.println("本月的最后一天" + lastDay);
	}
	
	/**
	 * 如何格式化日期？
	 * java.text.DataFormat的子类（如 SimpleDateFormat 类）中的 format(Date)方法可将日期格式化
	 * Java 8 中可以用 java.time.format.DateTimeFormatter 来格式化时间日期
	 * 
	 * Java 8 中引入了新的时间日期 API，
	 * 其中包括 LocalDate、LocalTime、LocalDateTime、Clock、Instant 等类，
	 * 这些的类的设计都使用了不变模式，因此是线程安全的设计
	 * */
	static void test5() {
		SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        System.out.println(oldFormatter.format(date1));
        // Java 8
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date2 = LocalDate.now();
        System.out.println(date2.format(newFormatter));
		
	}
	
	/**
	 * 打印昨天的当前时刻？
	 * */
	static void test6() {
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    System.out.println(cal.getTime());
		
		//Java 8
	    LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);
	}
	
	/**
	 * JSR310 规范 Joda-Time 的区别？
	 * https://www.ibm.com/developerworks/cn/java/j-jodatime.html
	 * */
}
