package com.zuicoding.framework.translation;

import java.util.ArrayList;
import java.util.List;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.zuicoding.framework.translation.module.TranslationConfig;
import com.zuicoding.framework.translation.ui.TranslationDialog;
import com.zuicoding.framework.translation.ui.TranslationSettingsComponent;
import com.zuicoding.framework.translation.utils.StringTools;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/22.
 * <p>
 * <p>
 * </p>
 */
public class RightClickCallTranslationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        TranslationSettingsComponent component = TranslationSettingsComponent.getInstance();
        TranslationConfig config = component.getOldSettings();

        TranslationDialog dialog = new TranslationDialog();
        if (config != null){
            final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
            //获得选中的文本
            String selectedText =  editor.getSelectionModel().getSelectedText();
            dialog.setApiFieldValue(config.getApi());
            dialog.setAppIdFieldValue(config.getAppid());
            dialog.setKeyFieldValue(config.getKey());
            dialog.refreshFromBox(config.getLanguages());
            if (StringTools.isNotBank(selectedText)){
                dialog.setFromAreaText(selectedText);
            }
            List list = new ArrayList(config.getLanguages());
            list.remove(0);
            dialog.refreshToBox(list);
        }

        dialog.show();
        dialog.translation();
    }
}
