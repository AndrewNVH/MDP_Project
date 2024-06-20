package com.example.mdp_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.core.view.size
import com.example.mdp_project.databinding.FragmentDashboardBinding
import com.example.mdp_project.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    var toggleTemp = 1
    var toggleHum = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lineChart:LineChart = view.findViewById(R.id.lineChart)

//        val description: Description = lineChart.getDescription()
//        description.text = "Temperature"
//        description.setPosition(300f,15f)

//        lineChart.description = description
        lineChart.axisRight.isEnabled = false

        val dateList: ArrayList<Date> = MockDB.date
        val temperatureList: ArrayList<Float> = MockDB.temperature
        val humidityList: ArrayList<Float> = MockDB.humidity
        Log.d("dateList", dateList.toString())
        Log.d("temperatureList", temperatureList.toString())
        Log.d("humidityList", humidityList.toString())

        val entries1: ArrayList<Entry> = ArrayList<Entry>()
        for (i in dateList.indices) {
            val timestamp = dateList[i].time.toFloat()
            val temperature = temperatureList[i]
            entries1.add(Entry(timestamp, temperature))
        }

        Log.d("entries1", entries1.toString())

        val entries2: ArrayList<Entry> = ArrayList<Entry>()
        for (i in dateList.indices) {
            val timestamp = dateList[i].time.toFloat()
            val humidity = humidityList[i]
            entries2.add(Entry(timestamp, humidity))
        }

        Log.d("entries2", entries2.toString())

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val date = Date(value.toLong())
                return dateFormat.format(date)
            }
        }

        Log.d("AAAAAA", "line88")

        xAxis.setLabelCount(entries2.size)
        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 50f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = resources.getColor(R.color.black)
        yAxis.setLabelCount(10)

        Log.d("AAAAAA", "line100")

        val lineDataSet1 = LineDataSet(entries1, "Temperature")
        lineDataSet1.color = resources.getColor(R.color.red)
        lineDataSet1.valueTextSize = 10F
        Log.d("AAAAAA", "line105")
        val lineDataSet2 = LineDataSet(entries2, "Humidity")
        lineDataSet2.color = resources.getColor(R.color.blue)
        lineDataSet2.valueTextSize = 10F
        Log.d("AAAAAA", "line109")
        val lineData = LineData(lineDataSet1, lineDataSet2)
        lineChart.data = lineData
        lineChart.invalidate()
        Log.d("AAAAAA", "line113")
        binding.TitleDashboard.text = "Temperature & Humidity"
        Log.d("AAAAAA", "line115")

        binding.blinkTemp.setOnClickListener{
            if(toggleTemp == 1){
                Log.d("AAAAAA", "line119")
                val lineData = LineData(lineDataSet2)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleTemp = 0
                binding.TitleDashboard.text = "Humidity"
            }else if(toggleHum == 1){
                val lineData = LineData(lineDataSet1, lineDataSet2)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleTemp = 1
                binding.TitleDashboard.text = "Temperature & Humidity"
            }else{
                val lineData = LineData(lineDataSet1)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleTemp = 1
                binding.TitleDashboard.text = "Temperature"

            }
        }
        Log.d("AAAAAA", "line140")
        binding.blinkHum.setOnClickListener{
            if(toggleHum == 1){
                val lineData = LineData(lineDataSet1)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleHum = 0
                binding.TitleDashboard.text = "Temperature"
            }else if(toggleTemp == 1){
                val lineData = LineData(lineDataSet1, lineDataSet2)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleHum = 1
                binding.TitleDashboard.text = "Temperature & Humidity"
            }else{
                val lineData = LineData(lineDataSet2)
                lineChart.data = lineData
                lineChart.invalidate()
                toggleHum = 1
                binding.TitleDashboard.text = "Humidity"
            }
        }

    }
}