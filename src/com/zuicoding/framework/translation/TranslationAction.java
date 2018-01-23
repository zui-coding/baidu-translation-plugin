package com.zuicoding.framework.translation;

import java.util.ArrayList;
import java.util.List;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zuicoding.framework.translation.module.TranslationConfig;
import com.zuicoding.framework.translation.ui.TranslationDialog;
import com.zuicoding.framework.translation.ui.TranslationSettingsComponent;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class TranslationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        TranslationSettingsComponent component = TranslationSettingsComponent.getInstance();
        TranslationConfig config = component.getOldSettings();

        TranslationDialog dialog = new TranslationDialog();
        if (config != null){
            dialog.setApiFieldValue(config.getApi());
            dialog.setAppIdFieldValue(config.getAppid());
            dialog.setKeyFieldValue(config.getKey());
            dialog.refreshFromBox(config.getLanguages());
            List list = new ArrayList(config.getLanguages());
            list.remove(0);
            dialog.refreshToBox(list);
        }

        dialog.show();

    }
}
