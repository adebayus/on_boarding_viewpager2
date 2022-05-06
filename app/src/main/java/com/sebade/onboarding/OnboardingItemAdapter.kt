package com.sebade.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingItemAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnboardingItemAdapter.OnBoardingViewHolder>() {
    inner class OnBoardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onBoardingItem: OnBoardingItem) {
            imageOnBoarding.setImageResource(onBoardingItem.onboardingImage)
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {

        return OnBoardingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.onboarding_item_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val item = onBoardingItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = onBoardingItems.size
}