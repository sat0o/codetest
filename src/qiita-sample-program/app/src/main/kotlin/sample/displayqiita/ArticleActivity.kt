package sample.displayqiita

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import sample.displayqiita.data.Article
import sample.displayqiita.view.ArticleView

/**
 * Qiitaの記事詳細画面を表示するActivityです.
 * 記事の詳細画面についてはwebviewで表示を行ってます.
 */
class ArticleActivity : AppCompatActivity() {

    companion object {

        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(ARTICLE_EXTRA, article)
    }

    private val articleView: ArticleView by lazy {
        findViewById(R.id.article_view) as ArticleView
    }

    private val webView: WebView by lazy {
        findViewById(R.id.web_view) as WebView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }
}