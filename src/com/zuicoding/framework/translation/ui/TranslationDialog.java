package com.zuicoding.framework.translation.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;

import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.ui.DialogWrapper;
import com.zuicoding.framework.translation.http.HttpPost;
import com.zuicoding.framework.translation.module.LangItem;
import com.zuicoding.framework.translation.ui.model.LangModel;
import com.zuicoding.framework.translation.utils.LangConfigUtil;
import com.zuicoding.framework.translation.utils.MD5Util;

/**
 * Created by <a href="mailto:stephen.linicoding@gmail.com">Stephen.lin</a> on 2018/1/18.
 * <p>
 * <p>
 * </p>
 */
public class TranslationDialog extends DialogWrapper {
    private JTextArea fromArea;
    private JPanel container;
    private JTextArea toArea;
    private JTextField apiField = new JTextField("http://api.fanyi.baidu.com/api/trans/vip/translate");
    private JComboBox fromBobox;
    private JComboBox toBobox;
    private JTextField appIdField;
    private JTextField keyField;
    final Random random = new Random();
    public TranslationDialog(){
        super(false);
        init();
        setTitle("百度翻译");
        setOKButtonText("翻译");
        LangComboBoxRenderer renderer = new LangComboBoxRenderer();
        fromBobox.setEditable(false);
        ComboBoxModel model1 = new LangModel(LangConfigUtil.getFullLangs());

        fromBobox.setModel(model1);
        fromBobox.setRenderer(renderer);
        fromBobox.setSelectedIndex(2);


        toBobox.setEditable(false);
        ComboBoxModel model2 = new LangModel(LangConfigUtil.getWithoutAutoLangs());
        toBobox.setModel(model2);
        toBobox.setRenderer(renderer);
        toBobox.setSelectedIndex(0);


    }

    @NotNull
    @Override
    protected Action[] createActions() {
        Action[] actions = super.createActions();

        actions[0] = new DialogWrapperAction("翻译") {

            @Override
            protected void doAction(ActionEvent actionEvent) {
                translation();

            }
        };
        return actions;
    }

    public void translation(){
        HttpPost post = new HttpPost(apiField.getText());

        int r = random.nextInt();
        String sign = MD5Util.md5(appIdField.getText() +
                fromArea.getText() +
                r + keyField.getText()

        );
        LangItem from = (LangItem)fromBobox.getSelectedItem();
        LangItem to = (LangItem)toBobox.getSelectedItem();
        if (from == null || to == null){
            return;
        }

        post.addParam("q",fromArea.getText())
                .addParam("from",from.getCode())
                .addParam("to",to.getCode())
                .addParam("appid",appIdField.getText())
                .addParam("salt",String.valueOf(r))
                .addParam("sign",sign);
        String result = post.post();
        if (result == null) return;
        JSONObject jo = JSON.parseObject(result);
        JSONArray trans_result = jo.getJSONArray("trans_result");
        jo = trans_result.getJSONObject(0);
        String dst = jo.getString("dst");
        toArea.setText(dst);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return container;
    }

    public void setApiFieldValue(String value) {
        this.apiField.setText(value);
    }

    public void setFromAreaText(String text) {
        this.fromArea.setText(text);
    }

    public void setAppIdFieldValue(String value) {
        this.appIdField.setText(value);
    }

    public void setKeyFieldValue(String value) {
        this.keyField.setText(value);
    }

    public void refreshFromBox(List<LangItem> langs){
        this.fromBobox.setModel(new LangModel(langs));
        this.fromBobox.setSelectedIndex(2);
    }
    public void refreshToBox(List<LangItem> langs){
        this.toBobox.setModel(new LangModel(langs));
        this.toBobox.setSelectedIndex(0);
    }
}
