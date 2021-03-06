package com.lentach.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentach.R;
import com.lentach.api.models.comment.Comment;
import com.lentach.router.ActivityRouter;
import com.lentach.util.LinkUrlSearchHelper;
import com.lentach.util.UnixConverter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An adapter for the list of WallComments
 */
public class TopCommentsOfDayRVAdapter extends RecyclerView.Adapter<TopCommentsOfDayRVAdapter.ViewHolder> {

    private List<Comment> wallCommentsList = new ArrayList<>();
    private Context context;

    public TopCommentsOfDayRVAdapter(Context context, List<Comment> WallCommentsList) {

        this.context = context;
        if(WallCommentsList!=null&&WallCommentsList.size()>0){
        this.wallCommentsList.addAll(WallCommentsList.subList(0,10));
           // Collections.reverse(wallCommentsList);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_comment, viewGroup, false);


        return new ViewHolder(view, viewGroup.getContext());
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        List<String> listLinks = new ArrayList<>();
                listLinks.addAll(LinkUrlSearchHelper.extractUrls(wallCommentsList.get(i).getText()));

        viewHolder.commentText.setText(" " + wallCommentsList.get(i).getText());


        viewHolder.commentatorName.setText(" ");
        viewHolder.likesAmount.setText(" " + wallCommentsList.get(i).getLikes().count);
        viewHolder.postDate.setText(" " + UnixConverter.convertToString(wallCommentsList.get(i).getDate()));

        if(listLinks.size()>0)
            Picasso.with(context).load(listLinks.get(0)
                    ).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder).into(viewHolder.postImage);

        addClickableLinkToText(viewHolder);

    }

    private void addClickableLinkToText(ViewHolder viewHolder) {
        viewHolder.commentText.setLinksClickable(true);
        Pattern httpPattern = Pattern.compile("[a-z]+:\\/\\/[^ \\n]*");
        Linkify.addLinks(viewHolder.commentText, httpPattern,"");
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
        TextView postDate;
        @Bind(R.id.tv_toPost)
        TextView postLink;

        protected Context context;

        public ViewHolder(View view, Context context) {
            super(view);

            ButterKnife.bind(this, view);
            postLink.setOnClickListener(this);
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.tv_toPost:
                    int post  = wallCommentsList.get(getPosition()).getPost().getId();//TODO ??????? ?? ??????? ???????? ? ????
                    ActivityRouter.startPostActivity(context,wallCommentsList.get(getPosition()).getPost(),postImage);
                    break;
            }

        }

    }
}