package com.sborzenko.materialtoolbarspinner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.magorasystems.materialtoolbarspinner.MaterialToolbarSpinner
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }

        val list = listOf("Element 1", "Element 2", "Element 3", "Element 4", "Element very long Element very long Element very long Element very long")
        materialToolbarSpinner.adapter = object : MaterialToolbarSpinner.SimpleAdapter(this) {
            override fun getItem(position: Int): MaterialToolbarSpinner.Item  = MaterialToolbarSpinner.Item(list[position])

            override fun getCount() = list.size
        }

        materialToolbarSpinner.onClickListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                textView.text = list[position]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                Toast.makeText(this@MainActivity, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }



    }
}
