package pardieu.timothé.cms.db

import pardieu.timothé.cms.db.queries.SQLQuery
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment
import pardieu.timothé.cms.model.Model

class MysqlModel(val pool: ConnectionPool) : Model {

    val list = ArrayList<Article>()


    override fun getArticleList(): ArrayList<Article> {
        val list = ArrayList<Article>()
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles").use { stmt ->
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        list.add(
                            Article(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("text")
                            )
                        )
                    }
                }
            }
        }
        return list
    }

    override fun getArticle(id: Int): Article? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles where id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return Article(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("text")
                        )
                    }
                }
            }
        }
        return null
    }

    override fun createArticle(title: String?, text: String?): String {
        pool.useConnection { connection ->
            val stmt = connection.prepareStatement("INSERT INTO `articles` ( `title`, `text`) VALUES (?,?)")
            stmt.setString(1, title)
            stmt.setString(2, text)
            stmt.executeUpdate()
            try {
                val id = stmt.getGeneratedKeys()
                if (id.next())
                    return id.toString()
            } catch (e: Exception) {
                return "-1"

            }
        }
        return "-1"
    }


    override fun getComments(id: Int): List<Comment>? {
        val list = ArrayList<Comment>()
        pool.useConnection { connection ->
            val sql = SQLQuery("getComments")
            connection.prepareStatement(sql.query).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        list.add(
                            Comment(
                                rs.getInt("id"),
                                rs.getString("text"),
                                rs.getInt("idArticle")
                            )
                        )
                    }
                }
            }
        }
        return list
    }


    override fun createComment(text: String, idArticle: String) {
        pool.useConnection { connection ->
            val stmt = connection.prepareStatement("INSERT INTO `comments` ( `text`, `idArticle`) VALUES (?,?)")
            stmt.setString(1, text)
            stmt.setString(2, idArticle)
            stmt.executeUpdate()
        }
    }

}