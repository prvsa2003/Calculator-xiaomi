package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculater.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    private var operater : Char = '+'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        onNumbersclicked()

        onOperaterClicked()
    }

    private fun onOperaterClicked() {
        binding.btnplus.setOnClickListener {
            if(binding.txtexptrstion.text.isNotEmpty()) {
                val mychar = binding.txtexptrstion.text.last()
                if (mychar != '+' && mychar != '*' && mychar != '/' && mychar != '-') {
                    appendtext("+")
                }

            }
        }
        binding.btnmainez.setOnClickListener {
            if(binding.txtexptrstion.text.isNotEmpty()) {
                val mychar = binding.txtexptrstion.text.last()
                if (mychar != '+' && mychar != '*' && mychar != '/' && mychar != '-') {
                    appendtext("-")
                }
            }
            }
        binding.btnmosavi.setOnClickListener {
            try {
                val exprestion = ExpressionBuilder(binding.txtexptrstion.text.toString()).build()
                val result = exprestion.evaluate()
                val longresult = result.toLong()

                if (result == longresult.toDouble()) {
                    binding.txtjavab.text = longresult.toString()
                } else {
                    binding.txtjavab.text = result.toString()


                }
            }catch (e:Exception){
                binding.txtexptrstion.text = ""
                binding.txtjavab.text = ""
                Toast.makeText(this, "Erorr", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnzarb.setOnClickListener {
            if (binding.txtexptrstion.text.isNotEmpty()) {
                val mychar = binding.txtexptrstion.text.last()
                if (mychar != '+' && mychar != '*' && mychar != '/' && mychar != '-') {
                    appendtext("*")
                }
            }
        }
        binding.btndevice.setOnClickListener {
            if(binding.txtexptrstion.text.isNotEmpty()) {
                val mychar = binding.txtexptrstion.text.last()
                if (mychar != '+' && mychar != '*' && mychar != '/' && mychar != '-') {
                    appendtext("/")
                }
            }
        }
        binding.btnparantezBaz.setOnClickListener {
            appendtext("(")
        }
        binding.btnparantezBAste.setOnClickListener {
            appendtext(")")
        }
        binding.btnpakidn.setOnClickListener {

            val oldtext = binding.txtexptrstion.text.toString()
            if(oldtext.isNotEmpty()){
                binding.txtexptrstion.text=oldtext.substring(0,oldtext.length-1)
            }

        }
        binding.btnAC.setOnClickListener {
            binding.txtexptrstion.text=""
            binding.txtjavab.text=""
        }
    }

    private  fun onNumbersclicked(){
        binding.btnzero.setOnClickListener {
            if(binding.txtexptrstion.text.isNotEmpty()) {
                appendtext("0")
            }
        }
        binding.btnone.setOnClickListener {
            appendtext("1")
        }
        binding.btntwo.setOnClickListener {
            appendtext("2")
        }
        binding.btnthree.setOnClickListener {
            appendtext("3")
        }
        binding.btnfour.setOnClickListener {
            appendtext("4")
        }
        binding.btnfive.setOnClickListener {
            appendtext("5")
        }
        binding.btnsix.setOnClickListener {
            appendtext("6")
        }
        binding.btnsevin.setOnClickListener {
            appendtext("7")
        }
        binding.btneight.setOnClickListener {
            appendtext("8")
        }
        binding.btnnine.setOnClickListener {
            appendtext("9")
        }
        binding.btnnoghte.setOnClickListener {
            if(binding.txtexptrstion.text.isEmpty() || binding.txtjavab.text.isNotEmpty()) {
                appendtext("0.")
            }else if(!binding.txtexptrstion.text.contains(".")){
                appendtext(".")
            }
        }
    }

    private  fun appendtext(newtext : String){
        if(binding.txtjavab.text.isNotEmpty()){
            binding.txtexptrstion.text = ""
        }
        binding.txtjavab.text = ""
        binding.txtexptrstion.append(newtext)

        val viewtree : ViewTreeObserver = binding.horizontakscrolview.viewTreeObserver
        viewtree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{

            override fun onGlobalLayout() {
                binding.horizontakscrolview.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizontakscrolview.scrollTo(binding.txtexptrstion.width , 0 )


            }

        })
    }
}