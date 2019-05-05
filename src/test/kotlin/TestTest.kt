import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.model.Model
import pardieu.timothé.cms.model.Article
import pardieu.timothé.control.ArticleListPresenterImpl
import pardieu.timothé.control.ArticlePresenterImpl

internal class PresenterTests {


    @Test
    fun testArticlePresenter(){
        val article = Article(0,"Title","LongTextDescription")
        val view = mock<ArticlePresenter.View>()
        val model = mock<Model> {
            on { getArticle(article.id) } doReturn article
        }
        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(article.id)

        verify(model).getArticle(article.id)
        verify(view).displayArticle(article, comments)
        //verify(model).getArticle(7)
        //verify(view).displayArticleNotFound()
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testInvalideArticlePresenter(){
        val view = mock<ArticlePresenter.View>()
        val model = mock<Model> {
            on { getArticle(any()) } doReturn null
        }
        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(42)

        verify(model).getArticle(42)
        verify(view).displayArticleNotFound()
        verifyNoMoreInteractions(model, view)
    }


    @Test
    fun testArticleListPresenter() {

        val list = listOf(Article(0,"1","Z"))

        val view = mock<ArticleListPresenter.View>()
        val model = mock<Model> {
            on { getArticleList() } doReturn list
        }
        val presenter = ArticleListPresenterImpl(model, view)
        presenter.start()

        verify(model).getArticleList()
        verify(view).displayArticleList(list)
        verifyNoMoreInteractions(model, view)


        /*
            var displayCalled = false
            val model = object : Model{
            override fun createArticle(title: String?, text: String?): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getArticle(id: Int): Article? = throw IllegalStateException()

            override fun getArticleList(): List<Article> = listOf(
                Article(1,"in","coucou")
            )
        }



        val view = object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                displayCalled = true
                inOrder(a,b) {
                    verify(a).doSomething()
                    verify(b).doSomething()
                }
                assertEquals(2, list.size)
                assertEquals("in", list[0].title)
            }

        }*/



    }
}
