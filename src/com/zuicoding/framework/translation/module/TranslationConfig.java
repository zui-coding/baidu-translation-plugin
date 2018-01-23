package com.zuicoding.framework.translation.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/19.
 * <p>
 * <p>
 * </p>
 */
public class TranslationConfig implements Serializable {
    private static final long serialVersionUID = 2555066666377989883L;

    private String api;
    private String appid;
    private String key;

    private List<LangItem> Languages;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<LangItem> getLanguages() {
        return Languages;
    }

    public void setLanguages(List<LangItem> languages) {
        Languages = languages;
    }
}
