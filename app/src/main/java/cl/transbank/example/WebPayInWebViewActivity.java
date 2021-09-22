package cl.transbank.example;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


public class WebPayInWebViewActivity extends AppCompatActivity {

    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_pay_in_web_view_message);
        webView = (WebView) findViewById(R.id.webPayWebView);
        loadWebPay(888);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadWebPay(int productId){
        String url = "https://transbank-android-backend-node.herokuapp.com/webpay-plus/create?from=web_view&producId=" + productId;
        webView = (WebView) findViewById(R.id.webPayWebView);
        setupWebView(webView, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(WebView webView, String url) {
        webView.setWebViewClient(new WebViewTbkClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        //webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(url);
    }

    public void regresar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void destroyWebView(WebView webView){
        CookieManager.getInstance().removeAllCookies(null);
        webView.clearCache(true);
        webView.setWebChromeClient(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView = (WebView) findViewById(R.id.webPayWebView);
        destroyWebView(webView);
    }

}