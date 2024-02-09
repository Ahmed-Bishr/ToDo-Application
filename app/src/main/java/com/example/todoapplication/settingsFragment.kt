package com.example.todoapplication

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.todoapplication.databinding.ActivityMainBinding
import com.example.todoapplication.databinding.ActivitySettingsFragmentBinding
import java.util.Locale

class SettingsFragment : Fragment() {

    private lateinit var binding: ActivitySettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySettingsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.auto.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            englishCase(selectedItem) // in case the language is English
            arabicCase(selectedItem) // in case the language is Arabic
            requireActivity().recreate()
        }

        binding.autoComplete.setOnItemClickListener { parent, view, position, id ->
            val selectedMode = parent.getItemAtPosition(position).toString()
            changeMode(selectedMode)
            requireActivity().recreate()
        }
    }

    override fun onResume() {
        super.onResume()
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapterLanguages =
            ArrayAdapter(requireContext(), R.layout.dropdown_language, languages)
        binding.auto.setAdapter(arrayAdapterLanguages) // language

        val modes = resources.getStringArray(R.array.modes)
        val arrayAdapterModes = ArrayAdapter(requireContext(), R.layout.dropdown_mode, modes)
        binding.autoComplete.setAdapter(arrayAdapterModes)
    }

    private fun setAppLanguage(languageCode: String, languageName: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)

        val resources = requireContext().resources
        resources.updateConfiguration(configuration, resources.displayMetrics)
        Toast.makeText(requireContext(), "Language set to $languageName", Toast.LENGTH_SHORT).show()
    }

    private fun changeMode(selectedMode: String) {
        when (selectedMode) {
            "Dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            "Light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            "System" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

        }
    }

    private fun englishCase(selectedItem: String) {
        when {
            selectedItem.contentEquals("english") -> setAppLanguage("en", selectedItem)
            selectedItem.contentEquals("arabic") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("en", "Default (English)")

        }
    }

    private fun arabicCase(selectedItem: String) {
        when {
            selectedItem.contentEquals("الانجليزية") -> setAppLanguage("en", selectedItem)
            selectedItem.contentEquals("العربية") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("ar", "العربية")
        }
    }


}