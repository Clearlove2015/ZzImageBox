package me.zhouzhuo.zzimageboxdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import me.zhouzhuo.zzimagebox.ZzImageBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rgNumbers = (RadioGroup) findViewById(R.id.rg_numbers);

        rgNumbers.check(R.id.rb_number_three);

        final ZzImageBox imageBoxAddMode = (ZzImageBox) findViewById(R.id.zz_image_box_add_mode);
        //如果加载网络图片，需要设置此代理
        imageBoxAddMode.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Log.e("TTT", "url=" + url);
                //本例使用Glide加载
                Glide.with(MainActivity.this).load(url).into(iv);
            }
        });
        //点击监听
        imageBoxAddMode.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String filePath, ImageView iv) {
                Toast.makeText(MainActivity.this, "你点击了+" + position + "的图片:url=" + filePath, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position, String filePath) {
                //移除position位置的图片
                imageBoxAddMode.removeImage(position);
            }

            @Override
            public void onAddClick() {
                //添加网络图片
                imageBoxAddMode.addImage("http://p1.so.qhimg.com/dmfd/290_339_/t01e15e0f1015e44e41.jpg");
            }
        });


        /**
         *            显示模式：设置添加图片为透明色并关闭删除功能 即可
         *            app:zib_img_add="@android:color/transparent"
         *            app:zib_img_deletable="false"
         */
        final ZzImageBox imageBoxShowMode = (ZzImageBox) findViewById(R.id.zz_image_box_show_mode);
        //如果加载网络图片，需要设置此代理
        imageBoxShowMode.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Log.e("TTT", "url=" + url);
                //本例使用Glide加载
                Glide.with(MainActivity.this).load(url).into(iv);
            }
        });

        imageBoxShowMode.addImage("http://p1.so.qhimg.com/dmfd/290_339_/t01e15e0f1015e44e41.jpg");
        imageBoxShowMode.addImage("http://p1.so.qhimg.com/dmfd/290_339_/t01e15e0f1015e44e41.jpg");
        imageBoxShowMode.addImage("http://p1.so.qhimg.com/dmfd/290_339_/t01e15e0f1015e44e41.jpg");
        imageBoxShowMode.addImage("http://p1.so.qhimg.com/dmfd/290_339_/t01e15e0f1015e44e41.jpg");

        //点击监听
        imageBoxShowMode.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String filePath, ImageView iv) {
                Toast.makeText(MainActivity.this, "你点击了+" + position + "的图片:url=" + filePath, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position, String filePath) {
            }

            @Override
            public void onAddClick() {
            }
        });

        rgNumbers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_number_three:
                        imageBoxAddMode.setImageSizeOneLine(3);
                        imageBoxShowMode.setImageSizeOneLine(3);
                        break;
                    case R.id.rb_number_four:
                        imageBoxAddMode.setImageSizeOneLine(4);
                        imageBoxShowMode.setImageSizeOneLine(4);
                        break;
                    case R.id.rb_number_five:
                        imageBoxAddMode.setImageSizeOneLine(5);
                        imageBoxShowMode.setImageSizeOneLine(5);
                        break;
                }
            }
        });

        final TextView tvLeftMargin = (TextView) findViewById(R.id.tv_left_margin);
        final TextView tvRightMargin = (TextView) findViewById(R.id.tv_right_margin);
        final TextView tvImagePadding = (TextView) findViewById(R.id.tv_image_padding);

        SeekBar leftMarginSeekBar = (SeekBar) findViewById(R.id.left_margin_seekbar);
        leftMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageBoxAddMode.setLeftMarginInPixel(progress);
                imageBoxShowMode.setLeftMarginInPixel(progress);
                tvLeftMargin.setText(progress + " px");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar rightMarginSeekBar = (SeekBar) findViewById(R.id.right_margin_seekbar);
        rightMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageBoxAddMode.setRightMarginInPixel(progress);
                imageBoxShowMode.setRightMarginInPixel(progress);
                tvRightMargin.setText(progress + " px");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar paddingSeekBar = (SeekBar) findViewById(R.id.padding_seekbar);
        paddingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageBoxAddMode.setImagePadding(progress);
                imageBoxShowMode.setImagePadding(progress);
                tvImagePadding.setText(progress + " px");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
