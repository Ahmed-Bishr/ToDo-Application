package com.example.todoapplication

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    private fun setAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)

        requireContext().resources.updateConfiguration(
            configuration,
            requireContext().resources.displayMetrics
        )
    }

    private fun englishCase(selectedItem: String) {
        if (selectedItem.contains("English")) {
            setAppLanguage("en")
            Toast.makeText(requireContext(), "Language set to English", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        } else if (selectedItem.contains("Arabic")) {
            setAppLanguage("ar")
            Toast.makeText(requireContext(), "Language set to Arabic", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        } else {
            setAppLanguage("en")
            Toast.makeText(requireContext(), "Language set to English", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        }
    }

    private fun arabicCase(selectedItem: String) {
        if (selectedItem.contains("الانجليزية")) {
            setAppLanguage("en")
            Toast.makeText(requireContext(), "اللغة المختارة الانجليزية", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        } else if (selectedItem.contains("العربية")) {
            setAppLanguage("ar")
            Toast.makeText(requireContext(), "اللغة المختارة العربية", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        } else {
            setAppLanguage("ar")
            Toast.makeText(requireContext(), "اللغة المختارة العربية", Toast.LENGTH_SHORT)
                .show()
            requireActivity().recreate()
        }
    }
}