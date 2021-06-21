package com.ukv.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolder> {

    private List<User> mUser;
    private Context mContext;

    public AdapterData(Context mContext, List<User> mUser){
         this.mContext = mContext;
         this.mUser = mUser;
    }
    @NonNull
    @Override
    public AdapterData.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data, parent, false);
        return new AdapterData.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.ViewHolder holder, int position) {
       User user = mUser.get(position);

       holder.subject.setText(user.getSubject());
//       holder.semester.setText(user.getSemester());
       holder.status.setText(user.getGrade());
       //holder.user.setText(user.getUser());
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView subject;
        public TextView status;
        public TextView semester;
        public TextView user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.dSubject);
            status = itemView.findViewById(R.id.dStatus);
           /* semester = itemView.findViewById(R.id.dSemester);
            user = itemView.findViewById(R.id.dUser);*/

        }
    }
}
