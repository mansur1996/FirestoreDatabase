package com.example.firebase_cloudfirestore.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_cloudfirestore.databinding.ItemUserBinding
import com.example.firebase_cloudfirestore.model.User

class UserAdapter(val list: List<User>) : RecyclerView.Adapter<UserAdapter.VH>() {

    inner class VH(private val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(user: User){
            itemUserBinding.tvName.text = user.name
            itemUserBinding.tvAge.text = "${user.age.toString()} year old"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}