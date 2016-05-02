package com.app.fyweddingplanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fyweddingplanner.util.ImageUtil;
import com.app.fyweddingplanner.util.TouchImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import app.com.fyweddingplanner.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentGallerySlider extends AppCompatActivity {

	private Bundle                bundle;
	private ArrayList<String>     images ;
	private ImageLoader           imageLoader;

    @Bind(R.id.tv_title) TextView tv_title;
    private DisplayImageOptions   options;

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_chevron_left_64dp);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_gallery_pager);
        ButterKnife.bind(this);
        setupToolbar();
        bundle = getIntent().getExtras();
        images = bundle.getStringArrayList("images");
        tv_title.setText(bundle.getString("title")+"");
        imageLoader = ImageLoader.getInstance();
        options = ImageUtil.getDisplayImageOptions(this);

        if (images != null && images.size() > 0) {

            final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
            final FullScreenImageAdapter adapter = new FullScreenImageAdapter(this);
            viewPager.setAdapter(adapter);

            final CirclePageIndicator circleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
            circleIndicator.setViewPager(viewPager);
        }
    }

    public class FullScreenImageAdapter extends PagerAdapter {

        private Activity _activity;
        private LayoutInflater inflater;

        public FullScreenImageAdapter(Activity activity) {
            this._activity = activity;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TouchImageView imgDisplay;

            inflater = (LayoutInflater) _activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                    false);

            imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
            String imgUrl = images.get(position);
            imageLoader.displayImage(imgUrl, imgDisplay,options);
            ((ViewPager) container).addView(viewLayout);
            return viewLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


