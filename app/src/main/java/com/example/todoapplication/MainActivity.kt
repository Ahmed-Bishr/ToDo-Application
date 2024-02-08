package com.example.todoapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.ActivityAddTaskBinding
import com.example.todoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationButton.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_tasks -> {
                    pushFragment(listFragment())
                    changeTitletodo()
                }

                R.id.navigation_settings -> {
                    pushFragment(SettingsFragment())
                    changeTitlesettings()
                }
            }

            return@setOnItemSelectedListener true
        }
        binding.navigationButton.selectedItemId = R.id.navigation_tasks // initialize fragment

        binding.button.setOnClickListener {
            val buttonSheetDialogFragment = addTask()
            buttonSheetDialogFragment.show(supportFragmentManager,null)
        }
    }


    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commitNow( )
    }

    private fun changeTitlesettings() {
        val textTitle = getString(R.string.settings)
        binding.title.text = textTitle
    }

    private fun changeTitletodo() {
        val textTitle = getString(R.string.to_do_list)
        binding.title.text = textTitle
    }


}




