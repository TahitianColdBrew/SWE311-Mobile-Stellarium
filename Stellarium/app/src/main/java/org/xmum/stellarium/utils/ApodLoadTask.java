package org.xmum.stellarium.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xmum.stellarium.model.ApodModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApodLoadTask extends AsyncTask<String, Void, String> {
    private WebView picture;
    private TextView title, explanation;

    public ApodLoadTask(WebView picture, TextView title, TextView explanation) {
        this.picture = picture;
        this.title = title;
        this.explanation = explanation;
    }

    @Override
    protected String doInBackground(String... strings) {
        String res = null;
        try {
            //1,找水源--创建URL
            URL url = new URL(strings[0]);//放网站
            //2,开水闸--openConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //3，建管道--InputStream
            InputStream inputStream = httpURLConnection.getInputStream();
            //4，建蓄水池蓄水-InputStreamReader
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            //5，水桶盛水--BufferedReader
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuffer buffer = new StringBuffer();
            String temp = null;

            while ((temp = bufferedReader.readLine()) != null) {
                //取水--如果不为空就一直取
                buffer.append(temp);
            }
            bufferedReader.close();//记得关闭
            reader.close();
            inputStream.close();
            Log.e("APOD", buffer.toString());//打印结果
            System.out.println(buffer);
            res = buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();
        ApodModel apodModel = gson.fromJson(s, ApodModel.class);
        picture.loadUrl(apodModel.getUrl());
        title.setText(apodModel.getTitle());
        explanation.setText(apodModel.getExplanation());
    }
}
