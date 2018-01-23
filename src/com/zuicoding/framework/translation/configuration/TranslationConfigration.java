package com.zuicoding.framework.translation.configuration;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.zuicoding.framework.translation.module.TranslationConfig;
import com.zuicoding.framework.translation.ui.TranslationSettingsComponent;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class TranslationConfigration implements SearchableConfigurable {

    private TranslationSettingsComponent settingsComponent;
    private TranslationConfig oldSettings;


    @NotNull
    @Override
    public String getId() {
        return "translationConfigration";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "百度翻译配置";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = TranslationSettingsComponent.getInstance();
        oldSettings = settingsComponent.getOldSettings();
        return settingsComponent.getContainer();
    }

    /**
     * 检测是否有更新，有就将apply按钮亮起
     * @return
     */
    @Override
    public boolean isModified() {
        if (oldSettings == null) return false;
        return !oldSettings.getApi()
                .equals(settingsComponent.getApiField().getText())||
                !oldSettings.getAppid().equals(settingsComponent.getAppidField().getText())||
                !oldSettings.getKey().equals(settingsComponent.getKeyField().getText());
    }

    /**
     * 确保应用按钮灰掉
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        oldSettings.setKey(settingsComponent.getKeyField().getText());
        oldSettings.setAppid(settingsComponent.getAppidField().getText());
        oldSettings.setApi(settingsComponent.getApiField().getText());
    }
}
