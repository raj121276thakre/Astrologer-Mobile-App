package com.example.astrologermobileapp.introslide

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologermobileapp.R
import java.util.Calendar

class IntroSliderAdapter(
    private val slides: List<SlideModel>,
    private val targetFragment: String
) : RecyclerView.Adapter<IntroSliderAdapter.SlideViewHolder>() {

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


            // Open DatePicker when the question is related to Date of Birth
            if (slide.title1.contains("Date of Birth", true)) {
                inputEditText1.isFocusable = false
                inputEditText1.isFocusableInTouchMode = false

                inputEditText1.setOnClickListener {
                    showDatePickerDialog(inputEditText1)
                }
            }
            if (slide.title2.contains("Date of Birth", true)) {
                inputEditText2.isFocusable = false
                inputEditText2.isFocusableInTouchMode = false

                inputEditText2.setOnClickListener {
                    showDatePickerDialog(inputEditText2)
                }
            }

            // Open TimePicker when the question is related to Time of Birth
            if (slide.title1.contains("Time of Birth", true)) {
                inputEditText1.isFocusable = false
                inputEditText1.isFocusableInTouchMode = false

                inputEditText1.setOnClickListener {

                    showTimePickerDialog(inputEditText1)
                }
            }
            if (slide.title2.contains("Time of Birth", true)) {
                inputEditText2.isFocusable = false
                inputEditText2.isFocusableInTouchMode = false

                inputEditText2.setOnClickListener {
                    showTimePickerDialog(inputEditText2)
                }
            }



            continueButton.setOnClickListener {
                when (position) {
                    0 -> {
                        // Slide 1 - Collect Name or Names
                        if (targetFragment == "Get Free Kundali") {

                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the name"
                                return@setOnClickListener
                            }

                            kundaliData.name = inputEditText1.text.toString()
                        } else {
                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the boy's name"
                                return@setOnClickListener
                            }
                            if (inputEditText2.text.toString().isEmpty()) {
                                inputEditText2.error = "Please enter the girl's name"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyName = inputEditText1.text.toString()
                            kundaliMatchingData.girlName = inputEditText2.text.toString()
                        }
                    }

                    1 -> {
                        // Slide 2 - Collect Date of Birth and Time of Birth
                        if (targetFragment == "Get Free Kundali") {
                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the Date of Birth"
                                return@setOnClickListener
                            }
                            if (inputEditText2.text.toString().isEmpty()) {
                                inputEditText2.error = "Please enter the Time of Birth"
                                return@setOnClickListener
                            }
                            kundaliData.dob = inputEditText1.text.toString()
                            kundaliData.tob = inputEditText2.text.toString()
                        } else {
                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the boy's Date of Birth"
                                return@setOnClickListener
                            }
                            if (inputEditText2.text.toString().isEmpty()) {
                                inputEditText2.error = "Please enter the girl's Date of Birth"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyDob = inputEditText1.text.toString()
                            kundaliMatchingData.girlDob = inputEditText2.text.toString()
                        }
                    }

                    2 -> {
                        // Slide 3 - Collect Latitude and Longitude or Time of Birth
                        if (targetFragment == "Get Free Kundali") {
                            val lat = inputEditText1.text.toString().toDoubleOrNull()
                            val lon = inputEditText2.text.toString().toDoubleOrNull()

                            if (lat == null || lat < -90 || lat > 90) {
                                inputEditText1.error = "Please enter a valid Latitude"
                                return@setOnClickListener
                            }
                            if (lon == null || lon < -180 || lon > 180) {
                                inputEditText2.error = "Please enter a valid Longitude"
                                return@setOnClickListener
                            }
                            kundaliData.lat = lat
                            kundaliData.lon = lon
                        } else {
                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the boy's Time of Birth"
                                return@setOnClickListener
                            }
                            if (inputEditText2.text.toString().isEmpty()) {
                                inputEditText2.error = "Please enter the girl's Time of Birth"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyTob = inputEditText1.text.toString()
                            kundaliMatchingData.girlTob = inputEditText2.text.toString()
                        }
                    }

                    3 -> {
                        // Slide 4 - Collect Place of Birth
                        if (targetFragment != "Get Free Kundali") {
                            if (inputEditText1.text.toString().isEmpty()) {
                                inputEditText1.error = "Please enter the boy's Place of Birth"
                                return@setOnClickListener
                            }
                            if (inputEditText2.text.toString().isEmpty()) {
                                inputEditText2.error = "Please enter the girl's Place of Birth"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyPob = inputEditText1.text.toString()
                            kundaliMatchingData.girlPob = inputEditText2.text.toString()
                        }
                    }

                    4 -> {
                        // Slide 5 - Collect Latitude
                        if (targetFragment != "Get Free Kundali") {
                            val boyLat = inputEditText1.text.toString().toDoubleOrNull()
                            val girlLat = inputEditText2.text.toString().toDoubleOrNull()

                            if (boyLat == null || boyLat < -90 || boyLat > 90) {
                                inputEditText1.error = "Please enter a valid Latitude for the boy"
                                return@setOnClickListener
                            }
                            if (girlLat == null || girlLat < -90 || girlLat > 90) {
                                inputEditText2.error = "Please enter a valid Latitude for the girl"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyLat = boyLat
                            kundaliMatchingData.girlLat = girlLat
                        }
                    }

                    5 -> {
                        // Slide 6 - Collect Longitude
                        if (targetFragment != "Get Free Kundali") {
                            val boyLon = inputEditText1.text.toString().toDoubleOrNull()
                            val girlLon = inputEditText2.text.toString().toDoubleOrNull()

                            if (boyLon == null || boyLon < -180 || boyLon > 180) {
                                inputEditText1.error = "Please enter a valid Longitude for the boy"
                                return@setOnClickListener
                            }
                            if (girlLon == null || girlLon < -180 || girlLon > 180) {
                                inputEditText2.error = "Please enter a valid Longitude for the girl"
                                return@setOnClickListener
                            }
                            kundaliMatchingData.boyLon = boyLon
                            kundaliMatchingData.girlLon = girlLon
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


        private fun showDatePickerDialog(editText: EditText) {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                itemView.context,
                R.style.CustomDatePickerDialogTheme,  // Apply custom theme here
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                    editText.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )


            datePickerDialog.setOnShowListener {
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(itemView.context, android.R.color.holo_orange_light))
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_orange_light))
            }

            datePickerDialog.show()


        }


        private fun showTimePickerDialog(editText: EditText) {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                itemView.context,
                R.style.CustomTimePickerDialogTheme,  // Apply custom theme here
                { _, hourOfDay, minute ->
                    val time = String.format("%02d:%02d", hourOfDay, minute)
                    editText.setText(time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.setOnShowListener {
                timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(itemView.context, android.R.color.holo_orange_light))
                timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_orange_light))
            }

            timePickerDialog.show()
        }



    }


}
