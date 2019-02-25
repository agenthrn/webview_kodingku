package com.example.webview_kodingku;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    WebView web;
    ProgressDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {


            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(MainActivity.this, null,
                        "Please Wait...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }



            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
                web.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('footer-sidebar-wrapper')[0].style.display='none'; " +
                        "})()");
                web.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('comments-area')[0].style.display='none'; " +
                        "})()");
                web.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('comments-form-wrapper')[0].style.display='none'; " +
                        "})()");
                web.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('header-topline-wrapper')[0].style.display='none'; " +
                        "})()");
            }



            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                web.loadUrl("http:/blog.kodingku.net");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        web.loadUrl("http:/blog.kodingku.net");

        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
    }
    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}