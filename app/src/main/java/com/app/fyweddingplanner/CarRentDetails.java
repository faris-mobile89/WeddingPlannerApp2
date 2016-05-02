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

import com.app.fyweddingplanner.models.CarSponser;
import com.app.fyweddingplanner.models.Offers;
import com.app.fyweddingplanner.util.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import app.com.fyweddingplanner.R;

public class CarRentDetails extends AppCompatActivity {
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CarSponser carSponser = (CarSponser) getIntent().getExtras().getSerializable("car");
        TextView textViewTitle = (TextView)findViewById(R.id.offerTitle);
        TextView textViewDetails = (TextView)findViewById(R.id.offerDetails);

        textViewDetails.setText(carSponser.getDetails()+"");
        textViewTitle.setText(carSponser.getName()+"");


        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if (carSponser.getImages() != null && carSponser.getImages().size() > 0){
            imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(carSponser.getImages().get(0),imageView, ImageUtil.getDisplayImageOptions(this));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CarRentDetails.this,FragmentGallerySlider.class);
                    intent.putExtra("images",carSponser.getImages());
                    intent.putExtra("title",carSponser.getName()+"");
                }
            });
        }
    }
}
