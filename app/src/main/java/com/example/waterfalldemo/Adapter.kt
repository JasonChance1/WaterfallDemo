package com.example.waterfalldemo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

/**
 * @description:
 * @author yanglei
 * @date :2023/11/22
 * @version 1.0.0
 */
class Adapter(private var imageList: MutableList<Int>, private val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        var initialHeight: Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false)

        // 设置初始高度
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val viewHolder = ViewHolder(view)
        viewHolder.initialHeight = getRandomHeight()
        layoutParams.height = viewHolder.initialHeight
        view.layoutParams = layoutParams

        return viewHolder
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 获取初始高度
        val initialHeight = holder.initialHeight

        // 在初始高度的基础上添加一个随机数
        val maxIncrement = 50 // 最大增量
        val minIncrement = 0 // 最小增量
        val randomIncrement = Random.nextInt(maxIncrement - minIncrement + 1) + minIncrement
        val newHeight = initialHeight + randomIncrement

        // 设置新的高度
        val layoutParams = holder.cardView.layoutParams
        layoutParams.height = newHeight
        holder.cardView.layoutParams = layoutParams

        holder.image.setImageResource(imageList[position])
        holder.title.text = "标题${position}"
    }

    private fun getRandomHeight(): Int {
        // 为初始高度设置一个随机数
        val maxHeight = 800 // 初始最大高度
        val minHeight = 600 // 初始最小高度
        return Random.nextInt(maxHeight - minHeight + 1) + minHeight
    }
}
