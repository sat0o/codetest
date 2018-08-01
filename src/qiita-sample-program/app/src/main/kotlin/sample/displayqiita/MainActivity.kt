package sample.displayqiita

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sample.displayqiita.request.ArticleRequest
import javax.inject.Inject

/**
 * Qiitaの記事詳細画面を表示するActivityです.
 * 記事の詳細画面についてはwebviewで表示を行ってます.
 */
class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleRequest: ArticleRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaSampleApp).component.inject(this)
        setContentView(R.layout.activity_main)

        val listView = findViewById(R.id.list_view) as ListView
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar
        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button

        val listAdapter = ArticleListAdapter(applicationContext)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this, listAdapter.getItem(position))
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            listAdapter.clear()
            articleRequest.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.addAll(it)
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })
        }
    }
}
