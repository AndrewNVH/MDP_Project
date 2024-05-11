package com.example.mdp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.mdp_project.databinding.FragmentSettingsBinding
import java.util.Locale

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languageSelector: Spinner = binding.langugeSel;
        val languages = resources.getStringArray(R.array.languageDropDown)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        languageSelector.adapter = adapter

        languageSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val language = when (position) {
                    0 -> "jr"
                    1 -> "jk"
                    else -> "jr"
                }
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
        val resources = requireContext().resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // Restart activity to apply language change
        requireActivity().recreate()
    }
    private fun getCurrentLanguage(): String {
        return resources.configuration.locale.language
    }

}


