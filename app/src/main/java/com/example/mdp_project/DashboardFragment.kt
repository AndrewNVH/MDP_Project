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

        val xValues = mutableListOf("1", "2", "3", "4","5","6","7","8","9","10")
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
        xAxis.setLabelCount(xValues.size)
        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 50f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = resources.getColor(R.color.black)
        yAxis.setLabelCount(10)

        // Temperature
        val entries1: MutableList<Entry> = mutableListOf()
        entries1.add(Entry(0f, 10f))
        entries1.add(Entry(1f, 10f))
        entries1.add(Entry(2f, 15f))
        entries1.add(Entry(3f, 15f))
        entries1.add(Entry(4f, 20f))
        entries1.add(Entry(5f, 20f))
        entries1.add(Entry(6f, 25f))
        entries1.add(Entry(7f, 25f))
        entries1.add(Entry(8f, 30f))
        entries1.add(Entry(9f, 30f))

        // Humidity
        val entries2: MutableList<Entry> = mutableListOf()
        entries2.add(Entry(0f, 5f))
        entries2.add(Entry(1f, 15f))
        entries2.add(Entry(2f, 25f))
        entries2.add(Entry(3f, 30f))
        entries2.add(Entry(4f, 35f))
        entries2.add(Entry(5f, 40f))
        entries2.add(Entry(6f, 45f))
        entries2.add(Entry(7f, 50f))
        entries2.add(Entry(8f, 25f))
        entries2.add(Entry(9f, 10f))

        val lineDataSet1 = LineDataSet(entries1, "Temperature")
        lineDataSet1.color = resources.getColor(R.color.red)
        lineDataSet1.valueTextSize = 10F

        val lineDataSet2 = LineDataSet(entries2, "Humidity")
        lineDataSet2.color = resources.getColor(R.color.blue)
        lineDataSet2.valueTextSize = 10F

        val lineData = LineData(lineDataSet1, lineDataSet2)
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