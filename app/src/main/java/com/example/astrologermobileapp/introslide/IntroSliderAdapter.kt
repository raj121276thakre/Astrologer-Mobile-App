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

class IntroSliderAdapter(
    private val slides: List<SlideModel>,
    private val kundaliMatchingData: KundaliMatchingData
) : RecyclerView.Adapter<IntroSliderAdapter.SlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_intro_slide, parent, false)
        return SlideViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val slide = slides[position]
        holder.bind(slide, position)
    }

    override fun getItemCount(): Int = slides.size

    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView1: TextView = view.findViewById(R.id.tvSlideTitle1)
        private val titleTextView2: TextView = view.findViewById(R.id.tvSlideTitle2)
        private val inputEditText1: EditText = view.findViewById(R.id.etSlideInput1)
        private val inputEditText2: EditText = view.findViewById(R.id.etSlideInput2)
        private val continueButton: Button = view.findViewById(R.id.btnContinue)

        fun bind(slide: SlideModel, position: Int) {
            titleTextView1.text = slide.title1
            titleTextView2.text = slide.title2

            continueButton.setOnClickListener {
                // Save input data
                when (position) {
                    0 -> {
                        //Date of birth
                        kundaliMatchingData.boyDob = inputEditText1.text.toString()
                        kundaliMatchingData.girlDob = inputEditText2.text.toString()
                    }
                    1 -> {
                        //Time of birth
                        kundaliMatchingData.boyTob = inputEditText2.text.toString()
                        kundaliMatchingData.girlTob = inputEditText1.text.toString()

                    }
                    2 -> {
                        //lattitude
                        kundaliMatchingData.boyLat = inputEditText2.text.toString().toDouble()
                        kundaliMatchingData.girlLat = inputEditText1.text.toString().toDouble()

                    }
                    3 -> {
                        //longitude
                        kundaliMatchingData.boyLon = inputEditText1.text.toString().toDouble()
                        kundaliMatchingData.girlLon = inputEditText2.text.toString().toDouble()
                    }
                }

                // Move to next slide
                (itemView.context as IntroSlideActivity).moveToNextSlide()
            }
        }
    }
}
