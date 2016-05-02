package com.app.fyweddingplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.fyweddingplanner.models.Offers;
import com.app.fyweddingplanner.util.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import app.com.fyweddingplanner.R;

public class OfferDetails extends AppCompatActivity {
    private ImageLoader           imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Offers offer = (Offers) getIntent().getExtras().getSerializable("offer");
        TextView textViewTitle = (TextView)findViewById(R.id.offerTitle);
        TextView textViewDetails = (TextView)findViewById(R.id.offerDetails);

        textViewDetails.setText(offer.getDisciption()+"");
        textViewTitle.setText(offer.getName()+"");


        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if (offer.getImages() != null && offer.getImages().size() > 0){
            imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(offer.getImages().get(0),imageView,ImageUtil.getDisplayImageOptions(this));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OfferDetails.this,FragmentGallerySlider.class);
                    intent.putExtra("images",offer.getImages());
                    intent.putExtra("title",offer.getName()+"");
                }
            });
        }
    }
}
