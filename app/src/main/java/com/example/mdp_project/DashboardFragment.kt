package com.example.mdp_project

import android.os.Bundle
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



//        lineChart.axisRight.isEnabled = false
//
//        val xValues = mutableListOf("1", "2", "3", "4","5","6","7","8","9","10")
//        val xAxis = lineChart.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
//        xAxis.setLabelCount(xValues.size)
//        xAxis.granularity = 1f
//
//        val yAxis = lineChart.axisLeft
//        yAxis.axisMinimum = 0f
//        yAxis.axisMaximum = 50f
//        yAxis.axisLineWidth = 2f
//        yAxis.axisLineColor = resources.getColor(R.color.black)
//        yAxis.setLabelCount(10)
//
//        // Temperature
//        val entries1: MutableList<Entry> = mutableListOf()
//        entries1.add(Entry(0f, 10f))
//        entries1.add(Entry(1f, 10f))
//        entries1.add(Entry(2f, 15f))
//        entries1.add(Entry(3f, 15f))
//        entries1.add(Entry(4f, 20f))
//        entries1.add(Entry(5f, 20f))
//        entries1.add(Entry(6f, 25f))
//        entries1.add(Entry(7f, 25f))
//        entries1.add(Entry(8f, 30f))
//        entries1.add(Entry(9f, 30f))
//
//        // Humidity
//        val entries2: MutableList<Entry> = mutableListOf()
//        entries2.add(Entry(0f, 5f))
//        entries2.add(Entry(1f, 15f))
//        entries2.add(Entry(2f, 25f))
//        entries2.add(Entry(3f, 30f))
//        entries2.add(Entry(4f, 35f))
//        entries2.add(Entry(5f, 40f))
//        entries2.add(Entry(6f, 45f))
//        entries2.add(Entry(7f, 50f))
//        entries2.add(Entry(8f, 25f))
//        entries2.add(Entry(9f, 10f))
//
//        val lineDataSet1 = LineDataSet(entries1, "Temperature")
//        lineDataSet1.color = resources.getColor(R.color.red)
//        lineDataSet1.valueTextSize = 10F
//
//        val lineDataSet2 = LineDataSet(entries2, "Humidity")
//        lineDataSet2.color = resources.getColor(R.color.blue)
//        lineDataSet2.valueTextSize = 10F
//
//        val lineData = LineData(lineDataSet1, lineDataSet2)
//        lineChart.data = lineData
//        lineChart.invalidate()

        val description: Description = lineChart.getDescription()
        description.text = "This is a test chart"
        description.setPosition(150f,15f)

        lineChart.description = description
        lineChart.axisRight.isEnabled = false
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.setPinchZoom(true)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dbDate = MockDB.date
        val xValues: MutableList<String> = dbDate.map { date -> dateFormat.format(date) }.toMutableList()

        // yyyy-MM-dd HH:mm
//        val xValues = mutableListOf("2024-06-20 00:00", "2024-06-20 01:00", "2024-06-20 02:00", "2024-06-20 03:00",
//            "2024-06-20 04:00", "2024-06-20 05:00", "2024-06-20 06:00", "2024-06-20 07:00", "2024-06-20 08:00",
//            "2024-06-20 09:00", "2024-06-20 10:00", "2024-06-20 11:00", "2024-06-20 12:00", "2024-06-20 13:00",
//            "2024-06-20 14:00", "2024-06-20 15:00")
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
        xAxis.setLabelCount(xValues.size, true)

        // Rotate labels to avoid overlapping
        xAxis.labelRotationAngle = 90f

        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 100f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = resources.getColor(R.color.black)
        yAxis.setLabelCount(10)

        val temperatureList = MockDB.temperature.toMutableList()
        val humidityList = MockDB.humidity.toMutableList()

//        val temperatureList : MutableList<Float> = mutableListOf(
//            10f, 10f, 15f, 15f, 20f, 20f, 25f, 25f, 30f, 30f, 35f, 35f, 40f, 40f, 45f, 45f
//        )
//        val humidityList : MutableList<Float> = mutableListOf(
//            5f, 15f, 25f, 30f, 35f, 45f, 50f, 25f, 10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f
//        )

        //x nya index, Y nya ganti temperature value
        val entries1: MutableList<Entry> = mutableListOf()
        for (i in temperatureList.indices) {
            entries1.add(Entry(i.toFloat(), temperatureList[i]))
        }
//        entries1.add(Entry(0f, 10f))
//        entries1.add(Entry(1f, 10f))
//        entries1.add(Entry(2f, 15f))
//        entries1.add(Entry(3f, 15f))
//        entries1.add(Entry(4f, 20f))
//        entries1.add(Entry(5f, 20f))
//        entries1.add(Entry(6f, 25f))
//        entries1.add(Entry(7f, 25f))
//        entries1.add(Entry(8f, 30f))
//        entries1.add(Entry(9f, 30f))
//        entries1.add(Entry(10f, 35f))
//        entries1.add(Entry(11f, 35f))
//        entries1.add(Entry(12f, 40f))
//        entries1.add(Entry(13f, 40f))
//        entries1.add(Entry(14f, 45f))
//        entries1.add(Entry(15f, 145f))

        // X nya index, Y nya ganti humidity value
        val entries2: MutableList<Entry> = mutableListOf()
        for (i in humidityList.indices) {
            entries2.add(Entry(i.toFloat(), humidityList[i]))
        }
//        entries2.add(Entry(0f, 5f))
//        entries2.add(Entry(1f, 15f))
//        entries2.add(Entry(2f, 25f))
//        entries2.add(Entry(3f, 30f))
//        entries2.add(Entry(4f, 35f))
//        entries2.add(Entry(6f, 45f))
//        entries2.add(Entry(7f, 50f))
//        entries2.add(Entry(8f, 25f))
//        entries2.add(Entry(9f, 10f))
//        entries2.add(Entry(10f, 15f))
//        entries2.add(Entry(11f, 20f))
//        entries2.add(Entry(12f, 25f))
//        entries2.add(Entry(13f, 30f))
//        entries2.add(Entry(14f, 35f))
//        entries2.add(Entry(15f, 40f))

        val lineDataSet1 = LineDataSet(entries1, "Temperature")
        lineDataSet1.color = resources.getColor(R.color.red)

        val lineDataSet2 = LineDataSet(entries2, "Humidity")
        lineDataSet2.color = resources.getColor(R.color.blue)

        val lineData = LineData(lineDataSet1, lineDataSet2)
        lineChart.data = lineData
        lineChart.invalidate()
        lineChart.data = lineData
        lineChart.invalidate()

        binding.TitleDashboard.text = "Temperature & Humidity"

        binding.blinkTemp.setOnClickListener{
            if(toggleTemp == 1){
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
