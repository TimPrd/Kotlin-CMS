package pardieu.timothé.cms.tpl

import pardieu.timothé.cms.model.Article

data class IndexContext (val list:List<Article>, val session: UserSession?){
}