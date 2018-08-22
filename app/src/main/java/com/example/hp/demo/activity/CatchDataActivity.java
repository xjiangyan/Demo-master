package com.example.hp.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.CatchBean;
import com.example.hp.demo.constant.Static;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatchDataActivity extends AppCompatActivity {

    private GridView listview;
    private Document recommend;
    private List<CatchBean> data;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 1:
                    Document resultdata = (Document) msg.obj;
                    //处理消息
                    data = Transformation(resultdata);
                    //展示数据
                    initView(data);
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_data);
        listview = (GridView) findViewById(R.id.listview);
        getData();
    }

    private void getData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //爬取网页的内容封装成document
                    recommend = Jsoup.connect("http://www.jcodecraeer.com/plus/list.php?tid=31").get();
                    //成功后发送消息给handler
                    Message message = Message.obtain();
                    message.arg1 = 1;
                    message.obj = recommend;
                    mHandler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                    Static.showMessage(listview, e + "");
                }

            }
        }).start();


    }

    private void initView(final List<CatchBean> list) {
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = View.inflate(getApplicationContext(), R.layout.catchdata_item, null);
                    viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.imageview);
                    viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                Picasso.with(CatchDataActivity.this)
                        .load(list.get(position).getImg())
                        .error(R.drawable.p9)
                        .into(viewHolder.mImageView);

                viewHolder.mTextView.setText(list.get(position).getText());
                return convertView;
            }
        });
    }

    private class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    /**
     * 获取需要的内容
     *
     * @param doc
     * @return
     */
    public static List<CatchBean> Transformation(Document doc) {
        //获取class类名为codeli的一组数据
        List<Element> detail = doc.getElementsByAttributeValue("class", "codeli");
        Log.e("tag", "TransToRecommendComic: " + detail);
        ArrayList<CatchBean> imgsdata = new ArrayList<>();
        //        Random random = new Random();
        //        int result = random.nextInt(5);
        //        for (int i = (result * 6); i < (result + 1) * 6; i++) {
        //            //            comic.setTitle(detail.get(i).select("a").attr("title"));
        //            String img = detail.get(i).select("img").attr("data-original");
        //            imgsdata.add(img);
        //            //            Element ElementDescribe = detail.get(i).getElementsByAttributeValue("class", "mod-cover-list-intro").get(0);
        //            //            comic.setDescribe(ElementDescribe.select("p").text());
        //            //            comic.setId(Long.parseLong(getID(detail.get(i).select("a").attr("href"))));
        //
        //
        //        }
        for (int i = 0; i < detail.size(); i++) {
            CatchBean catchBean = new CatchBean();
            //取其中的class类名为codeli-photo的一组数据 然后选择其中标签为img 属性为src的值
            catchBean.setImg(detail.get(i).getElementsByAttributeValue("class", "codeli-photo").select("img").attr("src"));
            //取其中的class类名为codeli-info的一组数据 然后选择其中标签为p的属性值
            catchBean.setText(detail.get(i).getElementsByAttributeValue("class", "codeli-info").select("p").text());
            imgsdata.add(catchBean);
        }

        return imgsdata;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
