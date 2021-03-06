package com.justbon.java8.dateutil;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/***
 * Instant：时间戳
 * Duration：持续时间，时间差
 * LocalDate：只包含日期，比如：2016-10-20
 * LocalTime：只包含时间，比如：23:12:10
 * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
 * Period：时间段
 * ZoneOffset：时区偏移量，比如：+8:00
 * ZonedDateTime：带时区的时间
 * Clock：时钟，比如获取目前美国纽约的时间
 *
 */
public class DateTest {

    /**
     * 获取今天的日期
     */
    @Test
    public void getTodayDate()
    {
        LocalDate todayDate = LocalDate.now();
        System.out.println("今天的日期："+todayDate.toString());
        LocalDate oneday  =todayDate;
        //取2016年10月的第1天
        LocalDate firstDay = oneday.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDay);

    //取2016年10月的第1天，另外一种写法
        LocalDate firstDay2 = oneday.withDayOfMonth(1);
        System.out.println(firstDay2);

//取2016年10月的最后1天，不用考虑大月，小月，平年，闰年
        LocalDate lastDay = oneday.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

//当前日期＋1天
        LocalDate tomorrow = oneday.plusDays(1);
        System.out.println(tomorrow);

//判断是否为闰年
        boolean isLeapYear = tomorrow.isLeapYear();
        System.out.println(isLeapYear);
    }
    /**
     * 生日检查或者账单日检查
     * 开发过程中，经常需要为过生日的用户送上一些祝福，例如，用户的生日为1990-10-12,
     * 如果今天是2016-10-12，那么今天就是用户的生日（按公历／身份证日期来算）,
     * 那么通过java8新的日期库，我们该如何来进行判断?
     * 在java8中，可以使用MonthDay，该类不包含年份信息，当然还有一个类是YearMonth
     *
     */
    @Test
    public void getCheckBirthdayDate(){

        LocalDate birthday = LocalDate.of(1990, 10, 12);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay today = MonthDay.from(LocalDate.of(2016, 10, 12));
        System.out.println(today.equals(birthdayMd));
    }

    /**
     *获取当前的时间
     */
    @Test
    public void getCurrentTime(){
        //获取当前的时间
        LocalTime nowTime = LocalTime.now(); //结果14:29:40.558
        System.out.println(nowTime.toString());
        //如果不想显示毫秒
        LocalTime nowTime2 = LocalTime.now().withNano(0); //14:43:14
        System.out.println(nowTime2.toString());
        //指定时间
        LocalTime time = LocalTime.of(14, 10, 21); //14:10:21
        LocalTime time2 = LocalTime.parse("12:00:01"); // 12:00:01
        //当前时间增加2小时
        LocalTime nowTimePlus2Hour = nowTime.plusHours(2); //16:47:23.144
        //或者
        LocalTime nowTimePlus2Hour2 = nowTime.plus(2, ChronoUnit.HOURS);

    }

    /**
     *日期前后比较
     */
    @Test
    public void compare(){

        LocalDate today = LocalDate.now();
        LocalDate specifyDate = LocalDate.of(2015, 10, 20);
        System.out.println(today.isAfter(specifyDate)); //true
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if(yesterday.isBefore(today)){
            System.out.println("之前的日期:"+yesterday);
        }
    }
    /**
     * 处理不同时区的时间
     */
    @Test
    public void defaultZone(){

        //查看当前的时区
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(defaultZone); //Asia/Shanghai

        //查看美国纽约当前的时间
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime shanghaiTime = LocalDateTime.now();
        LocalDateTime americaDateTime = LocalDateTime.now(america);
        System.out.println(shanghaiTime); //2016-11-06T15:20:27.996
        System.out.println(americaDateTime); //2016-11-06T02:20:27.996 ，可以看到美国与北京时间差了13小时
        //带有时区的时间
        ZonedDateTime americaZoneDateTime = ZonedDateTime.now(america);
    }

    /***
     *
     * 比较两个日期之前时间差
     */
    @Test
    public void compareDateDiff(){

        LocalDate today = LocalDate.now();
        LocalDate specifyDate = LocalDate.of(2015, 10, 2);
        Period period = Period.between(specifyDate, today);

        System.out.println(period.getDays());  //4
        System.out.println(period.getMonths()); //1
        System.out.println(specifyDate.until(today, ChronoUnit.DAYS)); //401
    }

    /**
     * 日期时间格式解析、格式化
     */
    @Test
    public void dateFormat(){
        //java8中，预定义了一些标准的时间格式，我们可以直接将时间转换为标准的时间格式.
        String specifyDate = "20151011";
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate formatted = LocalDate.parse(specifyDate,formatter);
        System.out.println(formatted);
        //当然，很多时间标准的时间格式可能也不满足我们的要求，我们需要转为自定义的时间格式
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("YYYY MM dd");
        System.out.println(formatter2.format(LocalDate.now()));
    }

    /**
     * java8 时间类与Date类的相互转化
     */

    @Test
    public  void translateDate(){
        //Date与Instant的相互转化
        Instant instant  = Instant.now();
        Date date = Date.from(instant);
        Instant instant2 = date.toInstant();

        //Date转为LocalDateTime
        Date date2 = new Date();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());

        //LocalDateTime转Date
        LocalDateTime localDateTime3 = LocalDateTime.now();
        Instant instant3 = localDateTime3.atZone(ZoneId.systemDefault()).toInstant();
        Date date3 = Date.from(instant);

        //LocalDate转Date
        //因为LocalDate不包含时间，所以转Date时，会默认转为当天的起始时间，00:00:00
        LocalDate localDate4 = LocalDate.now();
        Instant instant4 = localDate4.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date4 = Date.from(instant);

    }

    @Test
    public  void translateDate2(){
       int year =2019;
       int month=4;
        int q=4;
        System.out.println(getQuarter(1));
    }

    public  static List<LocalDate> getPreLocalDateByMonth(int year, int month){
        List<LocalDate>list = new ArrayList<>();
        LocalDate today = LocalDate.now();
        today=today.withYear(year).with(ChronoField.MONTH_OF_YEAR,month);
        list.add(today);
        LocalDate q1 =today.plusMonths(-3*1);
        LocalDate q2 =today.plusMonths(-3*2);
        LocalDate q3 =today.plusMonths(-3*3);
        list.add(q1);
        list.add(q2);
        list.add(q3);
        return list;
    }
    public int getQuarter(int m){
        int quarter =0;
        if (m >= 1 && m == 3) {
            quarter = 1;
        }if (m >= 4 && m <= 6) {
            quarter = 2;
        }
        if (m >= 7 && m <= 9) {
            quarter = 3;
        }
        if (m >= 10 && m <= 12)
        {
            quarter = 4;
        }
        return quarter;
    }
   public static List<LocalDate> getPreLocalDate(int year){
       List<LocalDate>list = new ArrayList<>();
       LocalDate today = LocalDate.now();
       today=today.withYear(year);
       list.add(today);
       LocalDate q1 =today.plusMonths(-3*1);
       LocalDate q2 =today.plusMonths(-3*2);
       LocalDate q3 =today.plusMonths(-3*3);
       list.add(q1);
       list.add(q2);
       list.add(q3);
        return list;
    }

    public static List<LocalDate> getPreLocalDateByQuarter(int year,int quarter){
        List<LocalDate>list = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int month =quarter*3;
        today=today.withYear(year).with(ChronoField.MONTH_OF_YEAR,month);;
        list.add(today);
        LocalDate q1 =today.plusMonths(-3*1);
        LocalDate q2 =today.plusMonths(-3*2);
        LocalDate q3 =today.plusMonths(-3*3);
        list.add(q1);
        list.add(q2);
        list.add(q3);
        return list;
    }
    /**
     * Java 8计算一年前或一年后的日期
     */
    @Test
     public void addOrSubYear (){
         LocalDate today = LocalDate.now();
         LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
         System.out.println("一年前的日期 : " + previousYear);
         LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
         System.out.println("一年后的日期:"+nextYear);
     }
    /**
     * Java 8增加了一个Clock时钟类用于获取当时的时间戳
     * ，或当前时区下的日期时间信息。
     * 以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
     */
    @Test
    public void clockDemo (){
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());
    }
    /**
     * Java 8中处理时区
     */

    @Test
    public void ZoneDateTimeDemo (){
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
    }
    /**
     * 如何表示信用卡到期这类固定日期，答案就在YearMonth
     */
    @Test
    public void YearMonthDemo (){
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }
    /**
     * 如何在Java 8中检查闰年
     */
    @Test
    public void YearCheckDemo (){
        LocalDate today = LocalDate.now();
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("2018 is not a Leap year");
        }
    }
    /**
     * 计算两个日期之间的天数和月数
     */
    @Test
    public void PeriodDemo (){
        LocalDate today = LocalDate.now();

        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths() );
    }
    /**
     * 在Java 8中获取当前的时间戳v Instant
     */
    @Test
    public void InstantDemo (){
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

    }
    /**
     * Java 8中如何使用预定义的格式化工具去解析或格式化日期
     */
    @Test
    public void formateDemo (){
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);
    }
    /***
     * 字符串互转日期类型
     */
    @Test
    public void convertDemo (){
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);

        System.out.println("日期转换为字符串:"+str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str,format2);
        System.out.println("日期类型:"+date2);
    }


    /**
     * 把指定字符串格式化为日期
     */
    @Test
    public void test6() {
        String str1 = "2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str1, dtf);
        System.out.println(localDateTime);

        long longtimestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.printf("longtimestamp="+longtimestamp);
    }
}
