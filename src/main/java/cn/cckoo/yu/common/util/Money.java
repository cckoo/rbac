package cn.cckoo.yu.common.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Money {
    private static final Double cardinalNumber = 100.00;
    private static final Double profitNumber = 10000.00;
    private static DecimalFormat decimalFormatForView = new DecimalFormat("#,##0.00");
    private static final int scale = 2;


    public static String formatFromInternalToView(long money) {
        return formatForView(formatFenToYuan(money));
    }

    public static double formatFenToYuan(String money) {
        return div(Double.parseDouble(money), cardinalNumber).doubleValue();
    }

    public static double formatFenToYuan(long money) {
        return div(money, cardinalNumber).doubleValue();
    }

    public static long formatYuanToFen(double money) {
        return mul(money, cardinalNumber).longValue();
    }

    public static long formatYuanToFen(String money) {
        return formatYuanToFen(Double.parseDouble(money));
    }

    public static String formatForView(double money) {
        return decimalFormatForView.format(money);
    }

    public static long stringToLongWithMoney(String money) {
        return Long.parseLong(money);
    }

    public static int formatTaxRateFromInputToSystem(String rate) {
        return formatTaxRateFromInputToSystem(
                Double.parseDouble(
                        rate.trim()
                                .replace('%','0')
                                .replace(' ', '0')
                )
        );
    }

    public static int formatTaxRateFromInputToSystem(double rate) {
        return mul(rate, cardinalNumber).intValue();
    }

    public static double formatTaxRateFromSystemToOutput(int rate) {
        return div(rate, cardinalNumber).doubleValue();
    }

    public static long roundForProfit(long profit) {
        return Math.round(div(profit, profitNumber).doubleValue());
    }

    private static BigDecimal add(double numFir, double numSec){
        BigDecimal decimalNumFir = new BigDecimal(Double.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Double.toString(numSec));
        return decimalNumFir.add(decimalNumSec);
    }

    private static BigDecimal sub(double numFir, double numSec){
        BigDecimal decimalNumFir = new BigDecimal(Double.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Double.toString(numSec));
        return decimalNumFir.subtract(decimalNumSec);
    }

    private static BigDecimal mul(long numFir, long numSec){
        BigDecimal decimalNumFir = new BigDecimal(Long.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Long.toString(numSec));
        return decimalNumFir.multiply(decimalNumSec);
    }

    private static BigDecimal mul(double numFir, double numSec){
        BigDecimal decimalNumFir = new BigDecimal(Double.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Double.toString(numSec));
        return decimalNumFir.multiply(decimalNumSec);
    }


    /**
     * 除法,除不尽时四舍五入到小数点后2位
     * @param numFir
     * @param numSec
     * @return
     */
    private static BigDecimal div(double numFir, double numSec){
        BigDecimal decimalNumFir = new BigDecimal(Double.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Double.toString(numSec));
        return decimalNumFir.divide(decimalNumSec, scale, BigDecimal.ROUND_CEILING);
    }

    /**
     * 除法,除不尽时四舍五入到小数点后2位
     * @param numFir
     * @param numSec
     * @return
     */
    private static BigDecimal div(long numFir, double numSec){
        BigDecimal decimalNumFir = new BigDecimal(Long.toString(numFir));
        BigDecimal decimalNumSec = new BigDecimal(Double.toString(numSec));
        return decimalNumFir.divide(decimalNumSec, scale, BigDecimal.ROUND_CEILING);
    }
}
