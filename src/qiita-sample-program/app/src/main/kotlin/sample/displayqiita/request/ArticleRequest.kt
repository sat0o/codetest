package sample.displayqiita.request

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import sample.displayqiita.data.Article

/**
 * 記事の検索を行うためのインターフェースです.
 */
interface ArticleRequest {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}