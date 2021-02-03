package com.app.mvi.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mvi.R
import com.app.mvi.model.User
import kotlinx.android.synthetic.main.item_layout.view.*


class MainAdapter(private val users: ArrayList<User>)
    : RecyclerView.Adapter<MainAdapter.DataViewHolder>()
{


    class DataViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                fun bind(user: User){
                    itemView.textViewUserName.text = user.name
                    itemView.textViewUserEmail.text = user.email
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= DataViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(users[position])
    override fun getItemCount() = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }

}