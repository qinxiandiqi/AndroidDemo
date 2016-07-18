package com.qinxiandiqi.androiddemo;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.qinxiandiqi.androiddemo.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText etAddress;
    private Button btnGo;
    private WebView wvContent;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        etAddress = (EditText) view.findViewById(R.id.et_address);
        btnGo = (Button) view.findViewById(R.id.btn_go);
        wvContent = (WebView) view.findViewById(R.id.wv_content);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.logStr("onPageStarted:" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtils.logStr("onPageFinished:" + url);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    view.requestFocus();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                String strReloadSchema = uri.getScheme();
                String strReloadHost = uri.getHost();
                List<String> ltReloadPaths = uri.getPathSegments();
                if ("Jianan".equals(strReloadSchema)) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && request.getUrl().toString().contains("js_bridge.js")){
                    LogUtils.logStr("Try replace js_bridge.js");
                    try {
                        WebResourceResponse response = new WebResourceResponse("application/x-javascript", "utf-8", getActivity().getAssets().open("js/js_bridge.js"));
                        return response;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return super.shouldInterceptRequest(view, request);
                    }
                }else{
                    return super.shouldInterceptRequest(view, request);
                }
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && url.contains("js_bridge.js")){
                    LogUtils.logStr("Try replace js_bridge.js");
                    try {
                        WebResourceResponse response = new WebResourceResponse("application/x-javascript", "utf-8", getActivity().getAssets().open("js/js_bridge.js"));
                        return response;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return super.shouldInterceptRequest(view, url);
                    }
                }else{
                    return super.shouldInterceptRequest(view, url);
                }
            }
        });

        wvContent.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //为什么要在这里注入JS
                //1 OnPageStarted中注入有可能全局注入不成功，导致页面脚本上所有接口任何时候都不可用
                //2 OnPageFinished中注入，虽然最后都会全局注入成功，但是完成时间有可能太晚，当页面在初始化调用接口函数时会等待时间过长
                //3 在进度变化时注入，刚好可以在上面两个问题中得到一个折中处理
                //为什么是进度大于25%才进行注入，因为从测试看来只有进度大于这个数字页面才真正得到框架刷新加载，保证100%注入成功
//                if (newProgress <= 25) {
//                    mIsInjectedJS = false;
//                } else if (!mIsInjectedJS) {
//
//                    mIsInjectedJS = true;
//                    Log.d(TAG, " inject js interface completely on progress " + newProgress);
//
//                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                LogUtils.logStr("Prompt:" + message + ";value:" + defaultValue);
                if ("Jianan".equals(message)) {
                    try {
                        JSONObject jsonObject = new JSONObject(defaultValue);
                        String success = jsonObject.getString("success");
//                        success = success.substring(success.indexOf("{") + 1, success.lastIndexOf("}"));
//                        Log.e("Javascript", success);
                        view.loadUrl("javascript:eval('r=" + success + ";r();')");
                        result.confirm("success");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        wvContent.loadUrl("file:///android_asset/jstest.html");
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etAddress.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    url = "file:///android_asset/jstest.html";
                } else if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                wvContent.loadUrl(url);
            }
        });
        return view;
    }
}
