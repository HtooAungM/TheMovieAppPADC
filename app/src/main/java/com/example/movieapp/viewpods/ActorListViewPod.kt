package com.example.movieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.ActorAdapter
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.databinding.ViewPodActorListBinding


class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    private lateinit var binding: ViewPodActorListBinding
    lateinit var mActorAdapter: ActorAdapter
    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodActorListBinding.bind(this)
        setUpActorRecyclerView()
    }
    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String){
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorReference))
        binding.tvBestActor.text = titleText
        binding.tvMoreActors.text = moreTitleText
        binding.tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
    fun setData(actorList:List<ActorVO>){
        mActorAdapter.setNewData(actorList)
    }
    private fun setUpActorRecyclerView(){
        mActorAdapter = ActorAdapter()
        binding.rvBestActorList.adapter = mActorAdapter
        binding.rvBestActorList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}