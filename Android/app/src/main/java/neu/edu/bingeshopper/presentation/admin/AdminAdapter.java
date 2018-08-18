package neu.edu.bingeshopper.presentation.admin;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.databinding.ItemUserListBinding;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    private List<User> userList = new ArrayList<>();
    private AdminFragment.AdminCallBack callBack;

    public AdminAdapter(AdminFragment.AdminCallBack callBack) {
        this.callBack = callBack;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_list, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemUserListBinding binding;

        public ViewHolder(ItemUserListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int pos) {
            final User user = userList.get(pos);
            binding.approveUser.setChecked(user.getApproved());
            binding.deleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onDeleteClicked(user.getId());
                }
            });

            binding.userName.setText(user.getUsername());
            binding.userType.setText(user.getUserType().toString());
            binding.approveUser.setChecked(user.getApproved());
            binding.approveUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    callBack.ontoggle(user.getId(), isChecked);

                }
            });
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onUserClicked(user);
                }
            });

            binding.executePendingBindings();
        }
    }
}
