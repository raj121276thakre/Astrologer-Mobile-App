package com.example.astrologermobileapp.introslide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologermobileapp.R

class IntroSliderAdapter(
    private val slides: List<SlideModel>,
    private val targetFragment: String
) : RecyclerView.Adapter<IntroSliderAdapter.SlideViewHolder>()
{

    var onLastSlideCompleted: ((Any) -> Unit)? = null
    private var kundaliData = KundaliData()
    private var kundaliMatchingData = KundaliMatchingData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_intro_slide, parent, false)
        return SlideViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val slide = slides[position]
        holder.bind(slide, position)
    }

    override fun getItemCount(): Int = slides.size

    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.ivSlideImage)
        private val titleTextView1: TextView = view.findViewById(R.id.tvSlideTitle1)
        private val titleTextView2: TextView = view.findViewById(R.id.tvSlideTitle2)
        private val inputEditText1: EditText = view.findViewById(R.id.etSlideInput1)
        private val inputEditText2: EditText = view.findViewById(R.id.etSlideInput2)
        private val continueButton: Button = view.findViewById(R.id.btnContinue)

        fun bind(slide: SlideModel, position: Int) {
            imageView.setImageResource(slide.imageResId)
            titleTextView1.text = slide.title1
            titleTextView2.text = slide.title2


            continueButton.setOnClickListener {
                when (position) {
                    0 -> {
                        if (targetFragment == "Get Free Kundali") {
                            kundaliData.name = inputEditText1.text.toString()
                        } else {
                            kundaliMatchingData.boyName = inputEditText1.text.toString()
                            kundaliMatchingData.girlName = inputEditText2.text.toString()
                        }
                    }
                    1 -> {
                        if (targetFragment == "Get Free Kundali") {
                            kundaliData.dob = inputEditText1.text.toString()
                            kundaliData.tob = inputEditText2.text.toString()
                        } else {
                            kundaliMatchingData.boyDob = inputEditText1.text.toString()
                            kundaliMatchingData.girlDob = inputEditText2.text.toString()
                        }
                    }
                    2 -> {
                        if (targetFragment == "Get Free Kundali") {
                            kundaliData.lat = inputEditText1.text.toString().toDouble()
                            kundaliData.lon = inputEditText2.text.toString().toDouble()
                        } else {
                            kundaliMatchingData.boyTob = inputEditText2.text.toString()
                            kundaliMatchingData.girlTob = inputEditText1.text.toString()
                        }
                    }
                    3 -> {
                        if (targetFragment != "Get Free Kundali") {
                            kundaliMatchingData.boyPob = inputEditText1.text.toString()
                            kundaliMatchingData.girlPob = inputEditText2.text.toString()
                        }

                    }
                    4 -> {
                        if (targetFragment != "Get Free Kundali") {
                            kundaliMatchingData.boyLat = inputEditText1.text.toString().toDouble()
                            kundaliMatchingData.girlLat = inputEditText2.text.toString().toDouble()
                        }

                    }
                    5 -> {
                        if (targetFragment != "Get Free Kundali") {
                            kundaliMatchingData.boyLon = inputEditText1.text.toString().toDouble()
                            kundaliMatchingData.girlLon = inputEditText2.text.toString().toDouble()
                        }
                    }

                }

                if (position == slides.size - 1) {
                    if (onLastSlideCompleted != null) {
                        if (targetFragment == "Get Free Kundali") {
                            onLastSlideCompleted?.invoke(kundaliData)
                        } else {
                            onLastSlideCompleted?.invoke(kundaliMatchingData)
                        }
                    }
                } else {
                    (itemView.context as IntroSlideActivity).moveToNextSlide()
                }
            }


        }

    }


}


/*   continueButton.setOnClickListener {
               // Save input data
               when (position) {
                   0 -> {
                       //Names
                       kundaliMatchingData.boyName = inputEditText1.text.toString()
                       kundaliMatchingData.girlName = inputEditText2.text.toString()
                   }

                   1 -> {
                       //Date of birth
                       kundaliMatchingData.boyDob = inputEditText1.text.toString()
                       kundaliMatchingData.girlDob = inputEditText2.text.toString()
                   }

                   2 -> {
                       //Time of birth
                       kundaliMatchingData.boyTob = inputEditText2.text.toString()
                       kundaliMatchingData.girlTob = inputEditText1.text.toString()

                   }

                   3 -> {
                       //lattitude
                       kundaliMatchingData.boyLat = inputEditText2.text.toString().toDouble()
                       kundaliMatchingData.girlLat = inputEditText1.text.toString().toDouble()

                   }

                   4 -> {
                       //longitude
                       kundaliMatchingData.boyLon = inputEditText1.text.toString().toDouble()
                       kundaliMatchingData.girlLon = inputEditText2.text.toString().toDouble()
                   }
               }

               // Move to next slide
               (itemView.context as IntroSlideActivity).moveToNextSlide()
           } */


/*  continueButton.setOnClickListener {
      when (position) {

          0 -> {
              kundaliData.name = inputEditText1.text.toString()

          }

          1 -> {
              kundaliData.dob = inputEditText1.text.toString()
              kundaliData.tob = inputEditText2.text.toString()
          }

          2 -> {
              kundaliData.lat = inputEditText1.text.toString().toDouble()
              kundaliData.lon = inputEditText2.text.toString().toDouble()
          }



          3 -> {
              //Names
              kundaliMatchingData.boyName = inputEditText1.text.toString()
              kundaliMatchingData.girlName = inputEditText2.text.toString()
          }

          4 -> {
              //Date of birth
              kundaliMatchingData.boyDob = inputEditText1.text.toString()
              kundaliMatchingData.girlDob = inputEditText2.text.toString()
          }

          5 -> {
              //Time of birth
              kundaliMatchingData.boyTob = inputEditText2.text.toString()
              kundaliMatchingData.girlTob = inputEditText1.text.toString()

          }

          6 -> {
              //lattitude
              kundaliMatchingData.boyLat = inputEditText2.text.toString().toDouble()
              kundaliMatchingData.girlLat = inputEditText1.text.toString().toDouble()

          }

          7 -> {
              //longitude
              kundaliMatchingData.boyLon = inputEditText1.text.toString().toDouble()
              kundaliMatchingData.girlLon = inputEditText2.text.toString().toDouble()
          }
      }

      if (position == slides.size - 1) {
          if (onLastSlideCompleted != null) {
              if (slides.size == 3) {
                  onLastSlideCompleted?.invoke(kundaliData)
              } else {
                  onLastSlideCompleted?.invoke(kundaliMatchingData)
              }
          }
      } else {
          (itemView.context as IntroSlideActivity).moveToNextSlide()
      }
  } */
