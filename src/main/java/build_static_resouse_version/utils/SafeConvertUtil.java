package build_static_resouse_version.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 安全数据转换帮助类
 * Author:刘明明
 * CreateTime:2015年11月9日14:39:46
 */
public class SafeConvertUtil {
    /**
     * 字符串转换成Long类型<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月9日14:44:42
     *
     * @param s 待转换成Long类型的String
     * @return Long 转换后的值
     */
    public static Long toLong(Object s) {
        if (s == null) return new Long(0);
        try {
            return Long.parseLong(s.toString());
        } catch (NumberFormatException x) {
            return new Long(0);
        }
    }

    /**
     * 字符串转换成byte类型<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月19日15:49:19
     *
     * @param s 待转换成byte类型的Object
     * @return byte 转换后的值
     */
    public static byte toByte(Object s) {
        if (s == null) return 0;
        try {
            return Byte.parseByte(s.toString());
        } catch (NumberFormatException x) {
            return 0;
        }
    }

    /**
     * 字符串转换成Integer类型
     * Author:刘明明
     * CreateTime:2015年11月9日14:48:11
     *
     * @param s 待转换成Integer类型的String
     * @return Integer 转换后的值
     * */
    public static int toInteger(Object s) {
        if (s == null) return 0;
        try {
            return Integer.parseInt(s.toString());
        } catch (NumberFormatException x) {
            return 0;
        }
    }

    /**
     * 字符串转换成short类型
     * Author:刘明明
     * CreateTime:2015年11月18日11:16:34
     *
     * @param s 待转换成short类型的String
     * @return short 转换后的值
     * */
    public static short toShort(Object s) {
        if (s == null) return 0;
        try {
            return Short.parseShort(s.toString());
        } catch (NumberFormatException x) {
            return 0;
        }
    }

    /**
     * 字符串转换成Boolean类型
     * Author:刘明明
     * CreateTime:2015年11月9日20:35:38
     *
     * @param s 待转换成Boolean类型的String
     * @return Boolean 转换后的值
     * */
    public static Boolean toBoolean(Object s) {
        if (s == null) return false;
        try {
            return Boolean.parseBoolean(s.toString());
        } catch (NumberFormatException x) {
            return false;
        }
    }

    /**
     * 字符串转换成非Null字符串
     * Author:刘明明
     * CreateTime:2015年11月9日16:06:06
     *
     * @param s 待转换成非Null类型的String
     * @return Integer 转换后的值
     * */
    public static String toString(Object s) {
        if (s == null) return "";
        return s.toString().trim();
    }

    /**
     * 字符串转成成Double类型
     * @param s
     * @return
     */
    public static Double toDouble(Object s) {
        if (s == null) {
            return new Double(0);
        }
        try {
            return Double.parseDouble(s.toString());
        } catch (NumberFormatException num) {
            return new Double(0);
        }
    }

    /**
     * Object转换成HashSetIntList
     * Author:刘明明
     * CreateTime:2015年11月17日19:22:47
     *
     * @param s 待转换成HashSetIntList类型的String
     * @param separator 分隔字符串
     * @return HashSet<Integer> 转换后的值
     * */
    public static HashSet<Integer> toHashSetIntList(Object s, String separator){
        if (s == null || "".equals(s.toString())) return new HashSet<Integer>();
        String[] _list = s.toString().split(separator);
        HashSet<Integer> list = new HashSet<Integer>();
        for(String item : _list){
            list.add(toInteger(item));
        }
        return list;
    }



    /**
     * Object转换成ArrayListIntList
     * Author:刘明明
     * CreateTime:2015年11月30日16:02:40
     *
     * @param s 待转换成ArrayListIntList类型的String
     * @param separator 分隔字符串
     * @return ArrayList<Integer> 转换后的值
     * */
    public static ArrayList<Integer> toArrayListIntList(Object s, String separator){
        if (s == null) return new ArrayList<Integer>();
        String[] _list = s.toString().split(separator);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(String item : _list){
            list.add(toInteger(item));
        }
        return list;
    }

    /**
     * Object转换成HashSetStringList
     * Author:刘明明
     * CreateTime:2015年11月17日19:22:47
     *
     * @param s 待转换成HashSetStringList类型的Object
     * @param separator 分隔字符串
     * @return HashSet<String> 转换后的值
     * */
    public static HashSet<String> toHashSetStringList(Object s, String separator){
        if (s == null || "".equals(s.toString())) return new HashSet<String>();
        String[] _list = s.toString().split(separator);
        HashSet<String> list = new HashSet<String>();
        for(String item : _list){
            list.add(item);
        }
        return list;
    }

    /**
     * Object转换成HashSetLongList
     * Author:刘明明
     * CreateTime:2015年11月17日19:22:47
     *
     * @param s 待转换成HashSetIntList类型的String
     * @param separator 分隔字符串
     * @return HashSet<Integer> 转换后的值
     * */
    public static HashSet<Long> toHashSetLongList(Object s, String separator){
        if (s == null) return new HashSet<Long>();
        String[] _list = s.toString().split(separator);
        HashSet<Long> list = new HashSet<Long>();
        for(String item : _list){
            if(item == null || item.isEmpty()){
                continue;
            }
            list.add(toLong(item.trim()));
        }
        return list;
    }

    /**
     * 字符串转换成BigDecimal类型
     * Author:刘明明
     * CreateTime:2015年12月14日16:43:34
     *
     * @param s 待转换成BigDecimal类型的Object
     * @return BigDecimal 转换后的值
     */
    public static BigDecimal toBigDecimal(Object s) {
        if (s == null) return new BigDecimal(0);
        try {
            return BigDecimal.valueOf(toDouble(s));
        } catch (NumberFormatException x) {
            return new BigDecimal(0);
        }
    }

    static DecimalFormat moneyFormat = new DecimalFormat(",###,##0.00");
    static DecimalFormat numberFormat = new DecimalFormat(",###,##0");
    /**
     * 转换成string类型千分位格式化的金钱，保留两位小数<br>
     * author:刘明明<br>
     * updateTime:2017年3月3日10:37:38
     * @param money 金钱
     * */
    public static String toStringMoney(Object money){
        return moneyFormat.format(toDouble(money));
    }

    /**
     * 转换成string类型千分位格式化的数字，保留两位小数<br>
     * author:刘明明<br>
     * updateTime:2017年3月3日10:39:56
     * @param number 数字
     * */
    public static String toStringNumber(Object number){
        return numberFormat.format(toLong(number));
    }
}