package com.wl.learn.mytouchdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> implements SwipeDismissBehavior.OnDismissListener {

    private Context context;
    private List<String> list = new ArrayList<>();
    private RecyclerView recyclerView;

    public RecAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) holder.textView.getLayoutParams();
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(this);
        params.setBehavior(behavior);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewCompat.setAlpha(holder.textView, 1);
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDismiss(View view) {
        int position = recyclerView.getChildAdapterPosition(((View) view.getParent()));
        list.remove(position);
        notifyItemRemoved(position);

//        Snackbar.make(view, "确认删除么", Snackbar.LENGTH_LONG).setAction("确认", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.remove(position);
//                notifyItemRemoved(position);
//            }
//        }).show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.textView));
        }
    }
}
