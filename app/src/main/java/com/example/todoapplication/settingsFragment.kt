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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.todoapplication.databinding.ActivityMainBinding
import com.example.todoapplication.databinding.ActivitySettingsFragmentBinding
import java.util.Locale

class SettingsFragment : Fragment() {

    private lateinit var binding: ActivitySettingsFragmentBinding
    private lateinit var bindingMain: ActivityMainBinding

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

    private fun englishCase(selectedItem: String) {
        when {
            selectedItem.contains("english") -> setAppLanguage("en", selectedItem)
            selectedItem.contains("arabic") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("en", "Default (English)")

        }
    }

    private fun arabicCase(selectedItem: String) {
        when {
            selectedItem.contains("الانجليزية") -> setAppLanguage("en", selectedItem)
            selectedItem.contains("العربية") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("ar", "العربية")
        }
    }



}