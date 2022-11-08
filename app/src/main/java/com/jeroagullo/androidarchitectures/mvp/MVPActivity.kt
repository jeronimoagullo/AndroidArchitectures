package com.jeroagullo.androidarchitectures.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.jeroagullo.androidarchitectures.R
import com.jeroagullo.androidarchitectures.mvc.MVCActivity

class MVPActivity : AppCompatActivity(), CountriesPresenter.View {
    private val TAG: String = MVCActivity::javaClass.name

    private lateinit var listValues: MutableList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>

    // layout elements
    private lateinit var listView: ListView
    private lateinit var retryButton: Button
    private lateinit var progressBar: ProgressBar

    // Presenter
    private lateinit var presenter: CountriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        listValues = mutableListOf<String>()

        Log.i(MVCActivity::class.java.name, "Creating controller")
        presenter = CountriesPresenter(this)
        Log.i(MVCActivity::class.java.name, "Controller created")

        // Access the listView from xml file
        listView = findViewById<ListView>(R.id.list)
        retryButton = findViewById(R.id.retryButton)
        progressBar = findViewById(R.id.progressBar)

        //arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listValues)
        arrayAdapter = ArrayAdapter(this, R.layout.row_layout, R.id.ListText, listValues)

        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "you clicked ${listValues.get(position)}", Toast.LENGTH_SHORT).show()
        }

        retryButton.setOnClickListener(View.OnClickListener {
            presenter.onRefresh()
            retryButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            listView.visibility = View.GONE
        })

    }

    override fun setValues(values: List<String>){
        listValues.clear()
        listValues.addAll(values)
        retryButton.visibility = View.GONE
        progressBar.visibility = View.GONE
        listView.visibility = View.VISIBLE
        arrayAdapter.notifyDataSetChanged()
    }

    override fun onError(){
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        retryButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        listView.visibility = View.GONE
    }

}