package com.zovee.lenovo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by lenovo on 2017/1/2.
 */
public class GainService extends Service {

    private Thread workThread;
    String city;

    String httpUrl;
    String httpArg;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        city = (String) bundle.getSerializable("city");
        city = cn2Spell(city);//转化为拼音

        //服务器访问地址
        httpUrl = "http://apis.baidu.com/thinkpage/weather_api/suggestion";
        //访问参数
        httpArg = "location="+city+"&language=zh-Hans&unit=c&start=0&days=3";


        Runnable backgroundWork = new Runnable() {
            @Override
            public void run() {
                try {
                    while(!Thread.interrupted()){
                        Message msg = new Message(); //生成消息
                        msg.what = MainActivity.QUERY_FINISH; //设置消息类型
                        //生成Bundle携带数据
                        Bundle data = new Bundle();
                        String rs = GainDataUtils.request(httpUrl,httpArg);
                        Log.v("获得的天气数据流：",rs);
                        data.putString("value", rs);
                        msg.setData(data);
                        //利用Handler发送消息
                        MainActivity.handler.sendMessage(msg);
                    }
                } finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        //生成新线程
        workThread = new  Thread(backgroundWork);
        //isAlive方法用于判断workThread线程是否被开启
        if(!workThread.isAlive()){
            workThread.start();
        }else{
            Log.v("workThread", "线程已经被调用");
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //利用interrupt方式中断该线程
        workThread.interrupt();
        Log.v("ServiceLife","onDestroy方法被调用");
    }
    /**
     * 获取汉字串拼音，英文字符不变
     */
    public  String cn2Spell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {//-127到128的不是汉字
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }


}
