package com.app.fyweddingplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fyweddingplanner.models.Offers;
import com.app.fyweddingplanner.models.WeddingPlanner;
import com.app.fyweddingplanner.util.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.com.fyweddingplanner.R;

public class WeddingPlannerDetails extends AppCompatActivity {
    private ImageLoader           imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_planner_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final WeddingPlanner allWeddingPlanner = (WeddingPlanner) getIntent().getExtras().getSerializable("offer");
        TextView textViewTitle = (TextView)findViewById(R.id.offerTitle);
        TextView textViewDetails = (TextView)findViewById(R.id.offerDetails);
        TextView textViewtextView8 = (TextView)findViewById(R.id.textView8);

        textViewDetails.setText(allWeddingPlanner.getDescription());
        textViewTitle.setText(allWeddingPlanner.getName()+"");
        textViewtextView8.setText(allWeddingPlanner.getpService()+"");


        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if (allWeddingPlanner.getImages() != null && allWeddingPlanner.getImages().size() > 0) {
            imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(allWeddingPlanner.getImages().get(0), imageView, ImageUtil.getDisplayImageOptions(this));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WeddingPlannerDetails.this, FragmentGallerySlider.class);
                    intent.putExtra("images", allWeddingPlanner.getImages());
                    intent.putExtra("title", allWeddingPlanner.getName() + "");
                }
            });
        }
    }

}
