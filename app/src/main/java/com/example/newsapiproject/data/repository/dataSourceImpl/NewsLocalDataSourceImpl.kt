import com.example.newsapiproject.data.db.ArticleDAO
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow


class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
): NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        TODO("Not yet implemented")
        articleDAO.insert(article )
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Article) {
         articleDAO.deleteArticle(article)
    }
}