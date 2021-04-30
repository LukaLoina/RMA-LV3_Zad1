package hr.ferit.lloina.lv3_zad1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.ferit.lloina.lv3_zad1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var count : Int = 0;
    var color : Int = 0;
    var textColor: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initCounter()


        binding.btnCrna.setOnClickListener { handleButtonClick(Color.rgb(0,0,0), Color.rgb(255,255,255))}
        binding.btnBijela.setOnClickListener { handleButtonClick(Color.rgb(255,255,255))}
        binding.btnPlava.setOnClickListener { handleButtonClick(Color.rgb(0,0,255)) }
        binding.btnCrvena.setOnClickListener { handleButtonClick(Color.rgb(255,0,0))}
        binding.btnZuta.setOnClickListener { handleButtonClick(Color.rgb(255,255,0)) }
        binding.btnZelena.setOnClickListener { handleButtonClick(Color.rgb(0,255,0))}

        binding.btnReset.setOnClickListener {
            count = 0
            color = Color.rgb(255,255,255)
            textColor = Color.rgb(0,0,0)
            updateCounter()
        }

        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        initCounter()
    }

    private fun initCounter(){
        val prefs = getPreferences(MODE_PRIVATE)
        count = prefs.getInt("count", 0)
        color = prefs.getInt("color", Color.rgb(0,0,0))
        textColor = prefs.getInt("textColor", Color.rgb(255,255,255))
        updateCounter()
    }

    private fun handleButtonClick(color : Int, textColor : Int = Color.rgb(0,0,0)){
        this.count += 1;
        this.color = color
        this.textColor = textColor
        updateCounter()
    }

    private fun updateCounter(){
        binding.tvBrojPtica.setBackgroundColor(color)
        binding.tvBrojPtica.setTextColor(textColor)
        binding.tvBrojPtica.text = count.toString()

        val prefs = getPreferences(MODE_PRIVATE)
        with(prefs.edit()){
            putInt("count", count)
            putInt("color", color)
            putInt("textColor", textColor)
            apply()
        }
    }
}