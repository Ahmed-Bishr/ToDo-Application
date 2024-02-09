package com.example.todoapplication

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
        binding =
            ActivitySettingsFragmentBinding.inflate(inflater) // Inflate the layout for this fragment using View Binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the listener for language selection dropdown
        binding.auto.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Handle language selection for English and Arabic
            englishCase(selectedItem)
            arabicCase(selectedItem)
            showSplashScreen() // Show the splash screen after language selection
        }

        // Set up the listener for mode selection dropdown
        binding.autoComplete.setOnItemClickListener { parent, view, position, id ->
            val selectedMode = parent.getItemAtPosition(position).toString()
            // Handle mode selection for English
            changeModeEnglishCase(selectedMode)
            // Handle mode selection for Arabic
            changeModeArabicCase(selectedMode)
            // Update the text of the AutoCompleteTextView with the selected mode
            binding.autoComplete.setText(selectedMode)
            // Show the splash screen after mode selection
            showSplashScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        // Load the list of languages from resources and set up the adapter for language selection dropdown
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapterLanguages = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_language,
            languages
        )
        binding.auto.setAdapter(arrayAdapterLanguages)

        // Load the list of modes from resources and set up the adapter for mode selection dropdown
        val modes = resources.getStringArray(R.array.modes)
        val arrayAdapterModes = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_mode,
            modes
        )
        binding.autoComplete.setAdapter(arrayAdapterModes)
    }

    // Function to set the application language
    private fun setAppLanguage(languageCode: String, languageName: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)

        val resources = requireContext().resources
        resources.updateConfiguration(configuration, resources.displayMetrics)
        // set toast for the language selection
        Toast.makeText(requireContext(), R.string.language_set, Toast.LENGTH_SHORT).show()
    }

    // Function to handle mode selection for English
    private fun changeModeEnglishCase(selectedMode: String) {
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

    // Function to handle mode selection for Arabic
    private fun changeModeArabicCase(selectedMode: String) {
        when (selectedMode) {
            "داكن" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            "مضئ" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            "النظام" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    // Function to handle language selection for English and Default (English)
    private fun englishCase(selectedItem: String) {
        when {
            selectedItem.contentEquals("English") -> setAppLanguage("en", selectedItem)
            selectedItem.contentEquals("Arabic") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("en", "English")
        }
    }

    // Function to handle language selection for Arabic and Default (Arabic)
    private fun arabicCase(selectedItem: String) {
        when {
            selectedItem.contentEquals("الانجليزية") -> setAppLanguage("en", selectedItem)
            selectedItem.contentEquals("العربية") -> setAppLanguage("ar", selectedItem)
            else -> setAppLanguage("ar", "العربية")
        }
    }

    // Function to show the splash screen
    private fun showSplashScreen() {
        val intent = Intent(requireContext(), splashScreen::class.java)
        val options = ActivityOptions.makeCustomAnimation(
            requireContext(),
            android.R.anim.fade_in, // Enter animation resource
            android.R.anim.fade_out // Exit animation resource
        ).toBundle()
        startActivity(intent, options)
        requireActivity().finish()
    }
}
