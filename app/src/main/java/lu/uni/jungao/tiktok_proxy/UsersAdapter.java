package lu.uni.jungao.tiktok_proxy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<String> userIDs;
    private TextView view2Fill;

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        TextView tv = new TextView(parent.getContext());
        UsersViewHolder vh = new UsersViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int i) {
        holder.tv.setText(userIDs.get(i));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v = (TextView) view;
                String content = v.getText().toString();
                view2Fill.setText(content);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userIDs.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public UsersViewHolder(TextView v) {
            super(v);
            tv = v;
        }
    }

    public UsersAdapter(List<String> userIDs, EditText view2Fill) {
        this.userIDs = userIDs;
        this.view2Fill = view2Fill;
    }

}

