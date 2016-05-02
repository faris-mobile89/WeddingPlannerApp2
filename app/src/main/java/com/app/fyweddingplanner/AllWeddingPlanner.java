package com.app.fyweddingplanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.fyweddingplanner.models.Config;
import com.app.fyweddingplanner.models.WeddingPlanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.fyweddingplanner.R;

public class AllWeddingPlanner extends Fragment implements View.OnClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    // Log tag
    private static final String TAG = OffersTab.class.getSimpleName();

    private List<WeddingPlanner> offerList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_offers,container,false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        //searchOffer = (Button) v.findViewById(R.id.offer_add_btn);
        //searchOffer.setOnClickListener(this);
        mRecyclerView = (RecyclerView)  v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public void getData() {
        //Add request Queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = Config.ipaddress+"/sample/webservices1.asmx/readAllWeddingPlannerFromDatabase";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            loadListViewWithData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String errorDescription = "";
                if (volleyError instanceof NetworkError) {
                } else if (volleyError instanceof ServerError) {
                    errorDescription = "Server Error";
                } else if (volleyError instanceof AuthFailureError) {
                    errorDescription = "AuthFailureError";
                } else if (volleyError instanceof ParseError) {
                    errorDescription = "Parse Error";
                } else if (volleyError instanceof NoConnectionError) {
                    errorDescription = "No Conenction";
                } else if (volleyError instanceof TimeoutError) {
                    errorDescription = "Time Out";

                } else {
                    try {
                        errorDescription = "Error";
                        Toast.makeText(getActivity(), errorDescription, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                //param.put("filter", et.getText().toString());
                return param;
            }
        };
        queue.add(req);

    }

    public void loadListViewWithData(String string){
        try {
            JSONObject obj = new JSONObject(string);
            JSONArray obje = obj.getJSONArray("WeddingPlanner");

            for (int i = 0; i < obje.length(); i++) {
                JSONObject x = obje.getJSONObject(i);
                String name = x.getString("wPName");
                String offers = x.getString("wPOffers");
                String description = x.getString("wPdiscription");
                String phone = x.getString("wPPhone");
                String location = x.getString("wPLocation");
                String pService = x.getString("wPServices");
                JSONArray jsonImages = x.getJSONArray("images");
                ArrayList<String> images = new ArrayList<>();
                if (jsonImages != null){
                    for (int index = 0; index < jsonImages.length();index++ ){
                        String imageName = jsonImages.getString(index);
                        if (imageName != null || imageName.length() > 0 ){
                            imageName = Config.ipaddress +"/sample/images/"+imageName+".jpg";
                            images.add(imageName);
                        }
                    }
                }
                WeddingPlanner m = new WeddingPlanner();
                m.setName(name+"");
                m.setDescription(description + "");
                m.setOffers(offers + "");
                m.setLocation(location + "");
                m.setPhone(phone);
                m.setpService(pService + "");
                m.setImages(images);
                offerList.add(m);
            }
            mAdapter = new WeddingCardAdapter(getActivity(), offerList);
            mRecyclerView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
    }
}


class WeddingCardAdapter extends RecyclerView.Adapter<WeddingCardAdapter.ViewHolder>{
   List<WeddingPlanner> mItems;
   Context context;
   Activity activity;

   public WeddingCardAdapter(Context context, List<WeddingPlanner> items) {
       this.context  = context;
       this.mItems   = items;
       this.activity = (Activity)context;
   }


   @Override
   public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recycler_view_item_planner, viewGroup, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }


   @Override
   public void onBindViewHolder(ViewHolder viewHolder, int i) {
       WeddingPlanner item = mItems.get(i);
       viewHolder.tvTitle.setText(item.getName());
       viewHolder.tvPhone.setText(item.getPhone()+"");
   }


   @Override
   public int getItemCount() {
       return mItems.size();
   }

   class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public ImageView imgThumbnail;
       public TextView tvTitle;
       //public TextView tvDetails;
       public TextView tvPhone;
       public TextView tvLocation;

       public ViewHolder(View itemView) {
           super(itemView);
           imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
           tvTitle      = (TextView)itemView.findViewById(R.id.tv_title);
           //tvDetails    = (TextView)itemView.findViewById(R.id.tv_text);
           //tvLocation    = (TextView)itemView.findViewById(R.id.tv_location);
           tvPhone    = (TextView)itemView.findViewById(R.id.tv_phone);
           itemView.setOnClickListener(this);
       }

       @Override
       public void onClick(View v) {
           final int itemPosition = getAdapterPosition();
           WeddingPlanner item = mItems.get(itemPosition);



           switch (v.getId()){

           }
       }
   }
}


