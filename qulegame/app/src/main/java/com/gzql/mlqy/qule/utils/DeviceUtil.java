package com.gzql.mlqy.qule.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.NetworkInterface;


/**
 * Created by LeonYu on 2017/5/27.
 */

public class DeviceUtil {
    // 获取手机IMEI
    public static String GetIMEI(Context ctx) {
        final TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);

        String IMEI = tm.getDeviceId();

        return IMEI;
    }

    public static String getUDID(){
        String serial = Build.SERIAL;
        if(serial.equals(Build.UNKNOWN)) {
            String mac = getMacAddress();
            if(!TextUtils.isEmpty(mac)) {
                serial = Md5.getMD5(mac);
            }else{
                serial = getMaxUDID();
            }
        }
        return serial.toUpperCase();
    }

    private static String getMaxUDID() {
        String devID = "CQE"+
                Build.BOARD.length()%100+ Build.BRAND.length()%100 +

                Build.DEVICE.length()%100 + Build.HARDWARE.length()%100+

                Build.DISPLAY.length()%100 + Build.HOST.length()%100 +

                Build.ID.length()%100 + Build.MANUFACTURER.length()%100 +

                Build.MODEL.length()%100 + Build.PRODUCT.length()%100 +

                Build.TAGS.length()%100 + Build.TYPE.length()%100 +

                Build.USER.length()%100;
        return devID;
    }

    public static String getMacAddress(){
        String mac = "";
        String [] wlan = new String[]{"eth0", "wlan0"};
        NetworkInterface network = null;
        try {
            for (int i = 0; i < wlan.length; i++) {
                network = NetworkInterface.getByName(wlan[i]);
                if(network != null) break;
            }
            mac = byte2hex(network.getHardwareAddress(), ":");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mac;
    }

    private static String byte2hex(byte[] b, CharSequence glue) {
        StringBuilder sb = new StringBuilder();
        String tmp = "";
        glue = glue == null?"":glue;
        for(int i = 0; i<b.length; i++){
            tmp = Integer.toHexString(b[i] & 0xff);
            if(tmp.length() == 1){
                sb.append("0").append(tmp);
            }else{
                sb.append(tmp);
            }
            sb.append(glue);
        }
        int len = glue.length();
        if(len > 0){
            sb.delete(sb.length()-len, sb.length());
        }

        return sb.toString().toUpperCase();
    }
}
