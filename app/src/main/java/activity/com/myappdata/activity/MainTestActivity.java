package activity.com.myappdata.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;
import java.util.Map;

import activity.com.myappdata.R;
//https://blog.csdn.net/llixiangjian/article/details/52761925

/***
 *
 * StringBuilder message = new StringBuilder();
 message.append("开始测试:"); //正常颜色
 message.append("<font color=\"#00FF00\">").append("TF卡检测成功:")).append("1024.0 ").append("MB").append("</font>"); //绿色
 message.append("....");  //红色，写法和上面一样
 mTestItem.setText(Html.fromHtml(message.toString())); //设置strings
 *
 */
public class MainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        TextView tvmt = (TextView) findViewById(R.id.tvmt);

        TextView tvmthtml= (TextView) findViewById(R.id.tvmthtml);

        String string=getString(R.string.html_string);
        tvmthtml.setText(Html.fromHtml(string));
        String str = "WD/网点1|PS/00001|ZZ/ZH000001ZZ,ZH000002ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100001,DY202100002|PS/00002|ZZ/ZH000003ZZ,ZH000004ZZ|XJ/100,10000;50,20000|ZK/存储,100;汇票凭证,50|DY/DY202100003,DY202100004";
        String str2 = str.replace("WD/", "\r\r网点:");
        String str22 = str2.replace("PS/", "\r\r配送单号:");
        String str222 = str22.replace("ZZ/", "\r\r周转箱:");
        String str2222 = str222.replace("XJ/", "\r\r现金:");
        String str22222 = str2222.replace("ZK/", "\r\r账户资料:");
        String str22223 = str22222.replace("DY/", "\r\r抵质押品:");
        String str222222 = str22223.replace("PS/", "\r\r配送单号:");
        String str3 = str222222.replace("|", "\n");
        SpannableString   spannableString = new SpannableString(str3);
////            SpannableString spannableStringvalue = new SpannableString(entry.getValue());
////            Spannable span = new SpannableString(entry.getKey());
            StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
//            StyleSpan styleSpan_I  = new StyleSpan(Typeface.ITALIC);
            spannableString.setSpan(styleSpan_B, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            spannableStringvalue.setSpan(styleSpan_I, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            span.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            span.setSpan(new AbsoluteSizeSpan(40), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvmt.setText(spannableString);
        Map<String, String> maplist = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
//            if(str.equals("WD")){
            maplist.put("WD/", "网点1|");
            maplist.put("PS/", "00001|");
            maplist.put("ZZ/", "ZH000001ZZ,ZH000002ZZ|");
//            }
        }
//        SpannableString spannableString = null;
//        for(Map.Entry<String, String> entry:maplist.entrySet()){
//            System.out.println(entry.getKey()+"--->"+entry.getValue());
//            int countlen = entry.getKey().length();
//            int start = 0;
//            int start = entry.getKey().indexOf(0);
//            if (str3.contains(":")) {
//            记录字符串起始和终止位置
//            int end = entry.getKey().indexOf("/");
//             spannableString = new SpannableString(entry.getKey()+entry.getValue());
////            SpannableString spannableStringvalue = new SpannableString(entry.getValue());
////            Spannable span = new SpannableString(entry.getKey());
//            StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
//            StyleSpan styleSpan_I  = new StyleSpan(Typeface.ITALIC);
//            spannableString.setSpan(styleSpan_B, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            spannableStringvalue.setSpan(styleSpan_I, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            span.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            span.setSpan(new AbsoluteSizeSpan(40), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


//            multiWord  =multiWord.append(span+entry.getValue());
//        }
//        tvmt.setText(spannableString);

//        String  str4=str3.replace("/","\n");
//        StringBuilder message = new StringBuilder();
//        message.append("开始测试:"); //正常颜色
//        message.append("<font color=\"#00FF00\">").append("TF卡检测成功:")).append("1024.0 ").append("MB").append("</font>"); //绿色
//        message.append("....");  //红色，写法和上面一样
//        if (str3.contains("\r\r")) {
//        SpannableStringBuilder ssb = new SpannableStringBuilder(str3);


//        int countlen=
//        for (int i = 0; i < str222222.length(); i++) {
//            Spannable span = new SpannableString(str222222);
//            int countlen = str222222.length();
//            int start = str222222.indexOf("\r\r");
////            if (str3.contains(":")) {
////            记录字符串起始和终止位置
//            int end = str222222.indexOf(":");
//            span.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            span.setSpan(new AbsoluteSizeSpan(60), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if (!str222222.contains("|")) {
//            } else {
//                String str5 = str222222.substring(0, str222222.indexOf("|"));
//                str222222 = str222222.substring(str5.length() + 1);
// 获取字符串中 “|”后面的字符串
//            }
//            StringBuffer    stringBuffer=new StringBuffer();
//            stringBuffer.append(str222222);
////String    s=str222222.
//            String str4 = span.toString().replace("|", "\n");
//            tvmt.setText(span);
//        }


        FlexboxLayout flexboxlayoutmaintest = (FlexboxLayout) findViewById(R.id.flexboxlayoutmaintest);
        flexboxlayoutmaintest.jumpDrawablesToCurrentState();


//        跳转
        Button btnhedui = (Button) findViewById(R.id.btnhedui);
        btnhedui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(MainTestActivity.this, JingDongShouyeActivity.class);
                startActivity(q);
            }
        });


    }
}
