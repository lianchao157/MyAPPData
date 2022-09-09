package activity.com.myappdata.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;

import activity.com.myappdata.MVPEaily.view.MIVActivity;
import activity.com.myappdata.R;
import activity.com.myappdata.application.MyApplication;
import activity.com.myappdata.listionnet.NetworkUtil;
import activity.com.myappdata.mvp.base.utilsmvp.Constants;
import activity.com.myappdata.util.LogUtil;


/***
 * 使用微信进行登陆   引用依赖
 *11111
 *     implementation 'com.tencent.mm.opensdk:wechat-sdk-android-without-mta:+'
 *     /*debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.3'
 */
public class   WeixinAPIActivity    extends Activity  implements IWXAPIEventHandler {
    private static String TAG = "MicroMsg.WXEntryActivity"; ///打印log

    private IWXAPI api;
    private MyHandler handler;
    // 自定义一个myhanderl

    public  static class MyHandler extends Handler{
        private final WeakReference<WeixinAPIActivity> wxEntryActivityWeakReference;


        public MyHandler(WeixinAPIActivity wxEntryActivity) {
            wxEntryActivityWeakReference = new WeakReference<WeixinAPIActivity>(wxEntryActivity);
        }

       public  void   handerMessage(Message msg){
            int tag = msg.what;
            switch (tag) {
                case NetworkUtil.GET_TOKEN: {
//                    try {
//                        json = new JSONObject(data.getString("result"));
//                        String openId, accessToken, refreshToken, scope;
//                        openId = json.getString("openid");
//                        accessToken = json.getString("access_token");
//                        refreshToken = json.getString("refresh_token");
//                        scope = json.getString("scope");
//                        Intent intent = new Intent(wxEntryActivityWeakReference.get(), WeixinAPIActivity.class);
//                        intent.putExtra("openId", openId);
//                        intent.putExtra("accessToken", accessToken);
//                        intent.putExtra("refreshToken", refreshToken);
//                        intent.putExtra("scope", scope);
//                        wxEntryActivityWeakReference.get().startActivity(intent);
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                    }
                }
            }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        handler = new MyHandler(this);
        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_weixin_a_p_i);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                //goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                //goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        int result = 0;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                LogUtil.d(TAG, String.format("分享成功, errorCode=%s", baseResp.errCode));
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }

        LogUtil.d(TAG, String.format("分享结果=%s, errorCode=%s, type=%s", result, baseResp.errCode, baseResp.getType()));

        if (baseResp.getType() == ConstantsAPI.COMMAND_SUBSCRIBE_MESSAGE) {
            SubscribeMessage.Resp subscribeMsgResp = (SubscribeMessage.Resp) baseResp;
            String text = String.format("openid=%s\ntemplate_id=%s\nscene=%d\naction=%s\nreserved=%s",
                    subscribeMsgResp.openId, subscribeMsgResp.templateID, subscribeMsgResp.scene, subscribeMsgResp.action, subscribeMsgResp.reserved);

            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        if (baseResp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            WXLaunchMiniProgram.Resp launchMiniProgramResp = (WXLaunchMiniProgram.Resp) baseResp;
            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s",
                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr);

            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        if (baseResp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_VIEW) {
            WXOpenBusinessView.Resp launchMiniProgramResp = (WXOpenBusinessView.Resp) baseResp;
            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s\nbusinessType=%s",
                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr,launchMiniProgramResp.businessType);

            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        if (baseResp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_WEBVIEW) {
            WXOpenBusinessWebview.Resp response = (WXOpenBusinessWebview.Resp) baseResp;
            String text = String.format("businessType=%d\nresultInfo=%s\nret=%d",response.businessType,response.resultInfo,response.errCode);

            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            SendAuth.Resp authResp = (SendAuth.Resp)baseResp;
            final String code = authResp.code;
            NetworkUtil.sendWxAPI(handler, String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                            "appid=%s&secret=%s&code=%s&grant_type=authorization_code", "wxd930ea5d5a258f4f",
                    "1d6d1d57a3dd063b36d917bc0b44d964", code), NetworkUtil.GET_TOKEN);
        }
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}