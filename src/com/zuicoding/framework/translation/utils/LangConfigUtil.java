package com.zuicoding.framework.translation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.zuicoding.framework.translation.module.LangItem;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 *     语言配置
 * </p>
 */
public class LangConfigUtil {

    private LangConfigUtil(){}
    private static final String LANG_CONFIG_NAME = "lang.txt";
    private static List<LangItem> langItems = new ArrayList<>();

    static   {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try(InputStream stream = LangConfigUtil
                .class.getClassLoader()
                .getResourceAsStream("lang.txt");) {
            isr = new InputStreamReader(stream,"UTF-8");
            br =  new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null){
               String[]  datas =  line.split("\\s+");
               langItems.add(new LangItem(datas[0],datas[1]));
            }

        }catch (Exception e){

        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static List<LangItem> getFullLangs(){
        langItems.get(1).setSelected(true);
        return langItems;
    }

    public static List<LangItem> getWithoutAutoLangs(){
        List<LangItem> withoutAutoLangs = new ArrayList<>(langItems);
        withoutAutoLangs.remove(0);
        withoutAutoLangs.get(1).setSelected(true);
        return withoutAutoLangs;
    }


}
