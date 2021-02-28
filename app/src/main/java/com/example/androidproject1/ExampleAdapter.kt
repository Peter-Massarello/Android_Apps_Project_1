package com.example.androidproject1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(private val exampleList: List<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.loanView.text = currentItem.loanAmount
        holder.interestView.text = currentItem.interestAmount
        holder.principalView.text = currentItem.principalAmount
        holder.escrowView.text = currentItem.escrowAmount
        holder.monthView.text = currentItem.monthCount
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loanView: TextView = itemView.loan_amount
        val interestView: TextView = itemView.interest_amount
        val principalView: TextView = itemView.principle_amount
        val escrowView: TextView = itemView.escrow_amount
        val monthView: TextView = itemView.month_text
    }
}