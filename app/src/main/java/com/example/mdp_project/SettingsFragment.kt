package com.example.mdp_project

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.mdp_project.databinding.FragmentSettingsBinding
import java.util.Locale


private var selectedLanguage: Int = 0

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedLanguage", selectedLanguage)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            selectedLanguage = it.getInt("selectedLanguage", -1)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val languageSelector: Spinner = binding.langugeSel;
        val languages = resources.getStringArray(R.array.languageDropDown)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        languageSelector.adapter = adapter

        binding.langugeSel.setSelection(selectedLanguage)

        languageSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val language = when (position) {
                    0 -> "en"
                    1 -> "in"
                    else -> "en"
                }
                selectedLanguage = position
                if (language != getCurrentLanguage())
                    setLocale(language)
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val activityContext = requireActivity()
        val resources = activityContext.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        val intent = Intent("com.example.ACTION_LANGUAGE_CHANGED")
        intent.putExtra("languageCode", languageCode)
        requireContext().sendBroadcast(intent)


        requireActivity().recreate()

    }
    private fun getCurrentLanguage(): String {
        return requireActivity().resources.configuration.locale.language
    }


}


