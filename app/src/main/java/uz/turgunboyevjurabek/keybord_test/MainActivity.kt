package uz.turgunboyevjurabek.keybord_test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button =findViewById<Button>(R.id.buttonn)

        test1()

//        keyingiyul()

     //yangiyulbilan()
//eskiyulbilan()
    }

    fun yangiyulbilan(){

        val edt=findViewById<EditText>(R.id.edt)
        val button =findViewById<Button>(R.id.buttonn)

        edt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                button.translationY=-900f
                true
            } else {

                button.translationY=0f
                false
            }
        }



    }


    private fun eskiyulbilan() {
        val edt=findViewById<EditText>(R.id.edt)
        val button =findViewById<Button>(R.id.buttonn)

        edt.setOnTouchListener(object :View.OnTouchListener{
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    button.translationY=-900f
                }
                return false
            }
        })
    }


    private fun keyingiyul(){

        val constraintSet = ConstraintSet()

// ConstraintLayoutni ishlatganingizdan so'ng, asosiy ConstraintSetni olamiz
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint)
        val edt=findViewById<EditText>(R.id.edt)
        val button =findViewById<Button>(R.id.buttonn)

        constraintSet.clone(constraintLayout)

        edt.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            edt.getWindowVisibleDisplayFrame(r)
            val screenHeight = edt.rootView.height
            val keypadHeight = screenHeight - r.bottom

            if (keypadHeight > screenHeight * 0.15) { // 0.15 o'zgartirilishi mumkin
                // Klaviatura ochilgan
                constraintSet.connect(button.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            } else {
                // Klaviatura yopilgan
                constraintSet.connect(button.id, ConstraintSet.BOTTOM, edt.id, ConstraintSet.TOP, 0)
            }

            constraintSet.applyTo(constraintLayout)
        }



    }

    private fun test1(){

        val edt=findViewById<EditText>(R.id.edt)
        val button =findViewById<Button>(R.id.buttonn)

        edt.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            edt.getWindowVisibleDisplayFrame(rect)
            val screenHeight = edt.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) { // Klaviatura ochilgan
                button.translationY = (-keypadHeight).toFloat()
            } else { // Klaviatura yopilgan
                button.translationY = 0f
            }
        }


    }

}