package com.sebade.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val TAG: String? = "Main"
    private lateinit var onboardingItemsAdapter: OnboardingItemAdapter
    private lateinit var indicatorLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setUpIndicator()
        setCurrentIndicator(0)
    }

    private fun navigatedToHome() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemAdapter(
            generatorList()
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        Log.d(
            TAG,
            "setOnboardingItems: $onboardingViewPager || ${onboardingViewPager.getChildAt(0)}"
        )
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            Log.d(TAG, "currentItem: ${onboardingViewPager.currentItem} ")
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1

            } else {
                navigatedToHome()
            }

        }

    }

    private fun generatorList(): List<OnBoardingItem> {
        return listOf(
            OnBoardingItem(
                R.drawable.image1,
                "In at nunc nec erat suscipit congue.",
                "Pellentesque vel enim bibendum, vulputate diam eget, fermentum enim."
            ),
            OnBoardingItem(
                R.drawable.image2,
                "Donec interdum tellus ac mollis placerat.",
                "Vivamus pharetra purus sed magna volutpat, eget malesuada velit vestibulum."
            ),
            OnBoardingItem(
                R.drawable.image3,
                "Sed ut est nec turpis volutpat faucibus.",
                "Maecenas sit amet quam imperdiet, venenatis sem sit amet, molestie sem."
            ),
        )
    }

    private fun setUpIndicator() {
        indicatorLayout = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 9, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                indicatorLayout.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorLayout.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}