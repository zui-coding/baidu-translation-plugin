package com.zuicoding.framework.translation.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class HttpPost {


    private String url;

    private Map<String,String> params;

    public HttpPost(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    public HttpPost(String url) {
        this.url = url;
    }

    public HttpPost addParam(String name,String value){
        if (params == null){
            params = new HashMap<>();
        }
        params.put(name, value);
        return this;
    }

    public String post(){
        try {
            URL _url_ = new URL(this.url);
            HttpURLConnection connection =(HttpURLConnection) _url_.openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestMethod("POST");
            //3，post请求的参数名/值队要放在HTTP正文中
            connection.setDoOutput(true);//设置是否使用URL连接进行输出
            //4.把请求参数添加到连接对象中
            if (params != null && !params.isEmpty()){
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                PrintWriter pw = new PrintWriter(osw,true);

                for(Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
                    it.hasNext();){
                    Map.Entry<String,String> entry = it.next();
                    pw.write(entry.getKey());
                    pw.write("=");
                    pw.write(entry.getValue());
                    if(it.hasNext()){
                        pw.write("&");
                    }
                }
                pw.close();
            }

            connection.connect();

            //6.获取输入流用来读取数据
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //遍历输出读取到的数据
            StringBuilder result = new StringBuilder();
            for(String value = null;(value=br.readLine())!=null;){
                result.append(value);
            }

            return result.toString();
        }catch (Exception e){

        }
        return null;

    }
}
