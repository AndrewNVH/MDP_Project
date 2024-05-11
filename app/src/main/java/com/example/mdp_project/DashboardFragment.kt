package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lineChart:LineChart = view.findViewById(R.id.lineChart)

        val description: Description = lineChart.getDescription()
        description.text = "Temperature"
        description.setPosition(250f,15f)

        lineChart.description = description
        lineChart.axisRight.isEnabled = false

        val xValues = mutableListOf("1", "2", "3", "4","5","6","7","8","9","10")
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
        xAxis.setLabelCount(xValues.size)
        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 100f
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

        val lineDataSet2 = LineDataSet(entries2, "Humidity")
        lineDataSet2.color = resources.getColor(R.color.blue)

        val lineData = LineData(lineDataSet1, lineDataSet2)
        lineChart.data = lineData
        lineChart.invalidate()
    }
}