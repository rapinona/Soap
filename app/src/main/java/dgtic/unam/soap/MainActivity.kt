package dgtic.unam.soap

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dgtic.unam.soap.databinding.ActivityMainBinding
import java.util.concurrent.Executors
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val executor=Executors.newSingleThreadExecutor()
    private val myHandler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val dataOne=binding.parameterOne.text.toString().trim()
            val dataTwo=binding.parameterTwo.text.toString().trim()
            getService(dataOne,dataTwo)
        }
    }
    private fun getService(paramOne:String,paramTwo:String){
        executor.execute{
            val response=CallServiceWeb().callApi(Utils.METHOD_ADD,paramOne,paramTwo)
            myHandler.post{
                try {
                    binding.textView.text=response
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}
