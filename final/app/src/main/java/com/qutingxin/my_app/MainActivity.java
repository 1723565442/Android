package com.qutingxin.my_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qutingxin.my_app.Fragment.Frag_home;
import com.qutingxin.my_app.Fragment.Frag_user;
import com.qutingxin.my_app.Fragment.Frag_video;

public class MainActivity extends AppCompatActivity {


    private ImageView home;
    private ImageView video;
    private ImageView user;
    private Bundle bundle;
    private String addnews;
    public static String username;
    public int frag;
    static public String news_data = "[" +
            "{" +
            "'title':'辽宁四人采蘑菇突遇暴雨致1死3伤'," +
            "'subtitle':'头条新闻'," +
            "'tip':'置顶'" +
            "}," +
            "{" +
            "'title':'成都大运会'," +
            "'subtitle':'人民日报'," +
            "'tip':'热点'" +
            "}," +
            "{" +
            "'title':'BlackPink跳叮叮当当'," +
            "'subtitle':'参考消息'," +
            "'tip':'热点'," +
            "'pic':'app/src/main/res/drawable/p1.png'" +
            "}," +
            "{" +
            "'title':'蔡英文财产曝光：存款5406万 名下拥6笔不动产'," +
            "'subtitle':'人民日报海外网'," +
            "'tip':'置顶'," +
            "'pic':'app/src/main/res/drawable/p1.png'" +
            "}," +
            "{" +
            "'title':'毛不易演唱会取消'," +
            "'subtitle':'毛不易'," +
            "'tip':'置顶'" +
            "}" +
            "]";
    static public String video_data = "[" +
            "{" +
            "'video_title':'天空'," +
            "'username':'曲廷锌'," +
            "'video_img':'https://img1.imgtp.com/2023/07/30/8jWSkIUZ.jpg'," +
            "'video_src':'http://vjs.zencdn.net/v/oceans.mp4'" +
            "}," +
            "{" +
            "'video_title':'雨天'," +
            "'username':'廷锌曲'," +
            "'video_img':'https://img1.imgtp.com/2023/07/30/cx5QOrB3.jpg'," +
            "'video_src':'http://6o2.cn/2JHyQG'" +
            "}," +
            "{" +
            "'video_title':'懒洋洋'," +
            "'username':'曲锌廷'," +
            "'video_img':'https://img1.imgtp.com/2023/07/30/5rG8ZDRO.jpg'," +
            "'video_src':'http://vjs.zencdn.net/v/oceans.mp4'" +
            "}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = this.getIntent().getExtras();

        home = findViewById(R.id.home);
        video = findViewById(R.id.video);
        user = findViewById(R.id.user);
        turn_frag(home);

        if (bundle != null) {
            if (bundle.getInt("flag") == 1) {
                addnews = "{'title':'" + bundle.getString("title") + "'," +
                        "'subtitle':'" + bundle.getString("subtitle") + "'," +
                        "'tip':'" + bundle.getString("tip") + "'";
                if (bundle.getString("pic") != null)
                    addnews += ",'pic':'" + bundle.getString("pic") + "'},";
                else
                    addnews += "},";
                StringBuilder s = new StringBuilder(news_data);
                s.insert(1, addnews);
                news_data = s.toString();

                //写入文件
                //获取准确的路径,context.getPackageName()得到包名
                File dir = new File("data/data/" + this.getPackageName());
                //如果文件夹不存在，则创建指定的文件
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdir();
                }
                //文件声明
                File file = new File(dir, "news.txt");
                //输入流
                InputStream inputStream = null;
                //输出流
                OutputStream outputStream = null;
                //若不存在，通过IO流的方式，将assets目录下的数据库文件，写入到项目模拟手机中，当开启模拟
                //器时，会将数据库文件写入到模拟手机的内存中
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    //创建文件
                    file.createNewFile();
                    //加载文件
                    inputStream = new ByteArrayInputStream(news_data.getBytes());
                    //输出到文件
                    outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int len;
                    //按字节写入
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //关闭资源
                    if (outputStream != null) {

                        try {
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            } else if (bundle.getInt("flag") == 2) {
                turn_frag(video);
            } else if (bundle.getInt("flag") == 3) {
                turn_frag(user);
            }
        }

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                turn_frag(view);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                turn_frag(view);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                turn_frag(view);
            }
        });

    }


    public void turn_frag(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Frag_home f1 = new Frag_home();
        Frag_video f2 = new Frag_video();
        Frag_user f3 = new Frag_user();
        switch (v.getId()) {
            case R.id.home:
                if(frag != 1) {
                    ft.replace(R.id.frag, f1);
                    frag = 1;
                }
                break;
            case R.id.video:
                if(frag != 2) {
                    ft.replace(R.id.frag, f2);
                    frag = 2;
                }
                break;
            case R.id.user:
                if(frag != 3) {
                    ft.replace(R.id.frag, f3);
                    frag = 3;
                }
                break;
        }
        ft.commit();
    }


}

