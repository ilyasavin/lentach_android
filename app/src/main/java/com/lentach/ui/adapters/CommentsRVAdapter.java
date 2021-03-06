package com.lentach.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.lentach.R;
import com.lentach.api.models.wallcomments.WallComment;
import com.lentach.router.ActivityRouter;
import com.lentach.ui.components.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * An adapter for the list of WallComments
 */
public class CommentsRVAdapter extends RecyclerView.Adapter<CommentsRVAdapter.ViewHolder> {

    private List<WallComment> wallCommentsList;
    private Context context;
    private int lastPosition = -1;

    public CommentsRVAdapter(Context context, List<WallComment> WallCommentsList) {
        this.context = context;
        this.wallCommentsList = WallCommentsList;

        removeUnusedPComments();


    }

    private void removeUnusedPComments() {
        for (int i = 0; i < wallCommentsList.size(); i++) {
            if (wallCommentsList.get(i).getAttachments() != null)
                if (wallCommentsList.get(i).getAttachments().get(0).getPhoto() == null)
                    wallCommentsList.remove(i);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);


        return new ViewHolder(view, viewGroup.getContext());
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if(wallCommentsList.get(i).getText().equals(""))
            viewHolder.commentText.setVisibility(GONE);
        else
            viewHolder.commentText.setText(" " + wallCommentsList.get(i).getText());

        viewHolder.commentatorName.setText(" " + wallCommentsList.get(i).getUsername());
        viewHolder.likesAmount.setText(" " + wallCommentsList.get(i).getLikes().getCount());
        viewHolder.postDate.setReferenceTime(wallCommentsList.get(i).getDate()*1000L);

        loadPhotoToView(viewHolder, i);

        Animation animation = AnimationUtils.loadAnimation(context,
                (i > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_to_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = i;

    }

    private void loadPhotoToView(ViewHolder viewHolder, int i) {
        if (wallCommentsList.get(i).getAttachments() != null){
            if (wallCommentsList.get(i).getAttachments().size() > 0) {
                if(wallCommentsList.get(i).getAttachments().get(0).getPhoto()!=null){
                    viewHolder.postImage.setVisibility(View.VISIBLE);
                    Picasso.with(context).load(wallCommentsList.get(i).getAttachments().get(0).getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder)
                            .into(viewHolder.postImage);}
                else {viewHolder.postImage.setVisibility(View.GONE);};}}
        else viewHolder.postImage.setVisibility(GONE);


            }


    @Override
    public int getItemCount() {
        return wallCommentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name)
        TextView commentatorName;
        @Bind(R.id.tv_textComment)
        TextView commentText;
        @Bind(R.id.likesAmount)
        TextView likesAmount;
        @Bind(R.id.postImage)
        ImageView postImage;
        @Bind(R.id.tv_PostDate)
        RelativeTimeTextView postDate;

        protected Context context;

        public ViewHolder(View view, Context context) {
            super(view);

            ButterKnife.bind(this, view);
            postImage.setOnClickListener(this);
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.postImage:
                    countPhotoItemAndZoom();
                    break;
            }

        }

        private void countPhotoItemAndZoom() {
            int photoAttach=0;
            for (int i = 0; i <wallCommentsList.get(getPosition()).getAttachments().size() ; i++) {

                if(wallCommentsList.get(getPosition()).getAttachments().get(i).getPhoto()!=null)
                    photoAttach = i;
            }
            if (wallCommentsList.get(getPosition()).getAttachments().get(photoAttach).getPhoto()!=null)
            ActivityRouter.startPhotoCommentActivity(
                    context,wallCommentsList.get(getPosition()).getAttachments().get(photoAttach).getPhoto().getPhoto604(),
                    postImage,
                    Constants.IMAGE_COMMENT_TRANSITION);
        }
    }
}