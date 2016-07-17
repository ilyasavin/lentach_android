package com.lentach.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.lentach.ChatActivity;
import com.lentach.MainActivity;
import com.lentach.PhotoViewActivity;
import com.lentach.PostActivity;
import com.lentach.VKPermissionsActivity;
import com.lentach.components.Constants;


/**
 * Activity navigation.
 */
public class ActivityNavigator {

    public static void startPostActivity(Context context, Parcelable parcelable, View imageView) {

        Intent intent = new Intent(context, PostActivity.class);

        intent.putExtra(Constants.POST_EXTRA, parcelable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(Constants.IMAGE_TRANSITION);
            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity)context, pair1);
            context.startActivity(intent, options.toBundle());
        } else
            context.startActivity(intent);
    }

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(context,android.R.anim.fade_in,android.R.anim.fade_out);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent,optionsCompat.toBundle());
    }

    public static void startPhotoActivity(Context activity,String photoUrl,View imageView){
        Intent intent = new Intent(activity, PhotoViewActivity.class);

        intent.putExtra("Photo",photoUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(Constants.IMAGE_TRANSITION);
            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity)activity, pair1);
            activity.startActivity(intent, options.toBundle());
        } else
            activity.startActivity(intent);
    }
    public static void startVKPermissionActivity(Context context){
        Intent intent = new Intent(context, VKPermissionsActivity.class);
        context.startActivity(intent);
    }
    public static void startChatActivity(Context context){
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }

}

