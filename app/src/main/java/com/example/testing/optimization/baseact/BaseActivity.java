package com.example.testing.optimization.baseact;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.testing.optimization.ToolRequest;
import com.example.testing.optimization.activities.MainAct;
import com.example.testing.optimization.dialogs.DialogUtils;
import com.example.testing.optimization.globaldata.MessageId;
import com.example.testing.optimization.utils.ToastUtils;

/**
 * Created by Administrator on 2017/6/1.
 */

public class BaseActivity extends AppCompatActivity implements Handler.Callback{
    protected final String TAG = getClass().getSimpleName();

    protected Handler   mHandler = new Handler(this);


    public void packMsgAndSend(int what, Object object) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = object;
        mHandler.sendMessage(msg);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MessageId.TOAST_TIP:
                ToastUtils.shortToast(this, (String) msg.obj);
                break;
            case MessageId.ACCOUNT_LOGIN:
                DialogUtils.showTitleEditDialog(this, "用户登录",  new DialogUtils.OnSubmitListener() {
                    @Override
                    public void onClick(DialogInterface dialog, String value1, String value2) {
                        if (TextUtils.isEmpty(value1) || TextUtils.isEmpty(value2)) {
                            packMsgAndSend(MessageId.TOAST_TIP, "请输入账号和密码");
                            return;
                        }
                        dialog.dismiss();
                        ToolRequest.getInstance().getLoginByAccount(BaseActivity.this, value1, value2);
                    }
                }).show();
                break;
        }
        return false;
    }
}
