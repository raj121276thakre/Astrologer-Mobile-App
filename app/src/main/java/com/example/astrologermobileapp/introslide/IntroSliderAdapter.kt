package com.example.astrologermobileapp.introslide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.fragments.KundaliFragment

class IntroSliderAdapter(private val slides: List<SlideModel>) : RecyclerView.Adapter<IntroSliderAdapter.SlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_intro_slide, parent, false)
        return SlideViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val slide = slides[position]
        holder.bind(slide)
    }

    override fun getItemCount(): Int = slides.size

    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.ivSlideImage)
        private val titleTextView: TextView = view.findViewById(R.id.tvSlideTitle)
        private val inputEditText: EditText = view.findViewById(R.id.etSlideInput)
        private val continueButton: Button = view.findViewById(R.id.btnContinue)

        fun bind(slide: SlideModel) {
            imageView.setImageResource(slide.imageRes)
            titleTextView.text = slide.title

            // Handle click and pass data
            continueButton.setOnClickListener {
                slide.inputText = inputEditText.text.toString()
                if (adapterPosition == slides.size - 1) {
                    // All slides completed, do something with data
                    (itemView.context as IntroSlideActivity).hideViewPager()
                    // Navigate to KundaliFragment or KundaliMatchingFragment
                    val fragment = KundaliFragment.newInstance(slides)
                    (itemView.context as AppCompatActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                } else {
                    (itemView.context as IntroSlideActivity).moveToNextSlide()
                }
            }
        }
    }
}
