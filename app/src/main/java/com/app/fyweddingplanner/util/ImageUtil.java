package com.app.fyweddingplanner.util;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import app.com.fyweddingplanner.R;


public class ImageUtil {

    public static DisplayImageOptions getDisplayImageOptions(Context context){
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageOnLoading(context.getResources().getDrawable(R.drawable.no_thumbnail))
                .showImageOnFail(context.getResources().getDrawable(R.drawable.no_thumbnail))
                .cacheOnDisk(true)
                .build();
    }
}
