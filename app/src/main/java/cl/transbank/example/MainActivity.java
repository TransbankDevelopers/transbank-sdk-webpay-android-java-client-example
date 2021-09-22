package cl.transbank.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "cl.transbank.example.MESSAGE";
    public static final String BACKEND_URL = "https://transbank-android-backend-node.herokuapp.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImage();
    }

    private void loadImage(){
        ImageView imageView = (ImageView) findViewById(R.id.productImgView);
        String imageUrl = "https://ed.team/sites/default/files/styles/16_9_medium/public/2018-04/guia-de-estilos.jpg?itok=73JysFzx";
        try {
            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void openWebPay(int productId){
        String url = "https://transbank-android-backend-node.herokuapp.com/webpay-plus/create?from=browser_android&producId=" + productId;
        Log.i("openWebPay",url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void pay(View view) {
        openWebPay(888);
    }

    public void openWebPayInWebView(View view) {
        Intent intent = new Intent(this, WebPayInWebViewActivity.class);
        startActivity(intent);
    }


}