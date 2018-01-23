package com.zuicoding.framework.translation.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.zuicoding.framework.translation.module.LangItem;
import com.zuicoding.framework.translation.module.TranslationConfig;
import com.zuicoding.framework.translation.utils.LangConfigUtil;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
@State(name = "baiduTranslationSettings",storages = @Storage(id = "other",file =
        "$APP_CONFIG$/baiduTranslationSettings.xml"))
public class TranslationSettingsComponent implements PersistentStateComponent<TranslationConfig>{
    private JPanel container;
    private JTextField apiField;
    private JTextField appidField;
    private JTable langTable;
    private JTextField keyField;

    private String[] header = new String[]{"code","名称"};

    private TranslationConfig oldSettings;

    //private List<LangItem> defaultLangs = LangConfigUtil.getFullLangs();

    @Nullable
    @Override
    public TranslationConfig getState() {
        TranslationConfig config = new TranslationConfig();
        config.setApi(apiField.getText());
        config.setAppid(appidField.getText());
        config.setKey(keyField.getText());
        int rows = langTable.getRowCount();
        List<LangItem> list = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            String code = langTable.getValueAt(i,0).toString();
            String name = langTable.getValueAt(i,1).toString();
            list.add(new LangItem(code,name));
        }
        config.setLanguages(list);
        return config;
    }

    @Override
    public void loadState(TranslationConfig config) {
        appidField.setText(config.getAppid());
        apiField.setText(config.getApi());
        keyField.setText(config.getKey());
        buildTable(config.getLanguages());
        oldSettings = config;
    }

    private void buildTable(List<LangItem> langs){
        if (langs == null || langs.isEmpty()) {
            langs = new ArrayList<>(LangConfigUtil.getFullLangs());
        }
        if (langs == null || langs.isEmpty()) return;
        Object[][] data = new Object[langs.size()][2];
        for (int i = 0; i < langs.size(); i++) {
            LangItem item = langs.get(i);
            data[i] = new Object[] {item.getCode(), item.getName()};
        }
        langTable.setModel(new DefaultTableModel(data,header));
    }

    public static TranslationSettingsComponent getInstance(){

        TranslationSettingsComponent component = ServiceManager.getService(TranslationSettingsComponent.class);
        return component;
    }

    public JPanel getContainer() {
        return container;
    }

    public JTextField getApiField() {
        return apiField;
    }

    public JTextField getAppidField() {
        return appidField;
    }

    public JTextField getKeyField() {
        return keyField;
    }

    public TranslationConfig getOldSettings() {
        return oldSettings;
    }
}
