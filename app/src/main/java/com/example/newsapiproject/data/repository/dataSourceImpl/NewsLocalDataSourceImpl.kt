import com.example.newsapiproject.data.db.ArticleDAO
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.dataSource.NewsLocalDataSource


class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
): NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        TODO("Not yet implemented")
        articleDAO.insert(article )
    }
}