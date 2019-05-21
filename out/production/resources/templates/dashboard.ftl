<!DOCTYPE html>
<!--[if IE 8 ]>
<html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]>
<html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>Abstract</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS
  ================================================== -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/base.css">
    <link rel="stylesheet" href="/static/css/vendor.css">
    <link rel="stylesheet" href="/static/css/main.css">

    <!-- script
    ================================================== -->
    <script src="/static/js/modernizr.js"></script>
    <script src="/static/js/pace.min.js"></script>

</head>

<body id="top">

<!-- header
================================================== -->
<header class="short-header">

    <div class="gradient-block"></div>

    <div class="row header-content">

        <div class="logo">
            <a href="/">Author</a>
        </div>

        <nav id="main-nav-wrap">
            <ul class="main-navigation sf-menu">
                <li class="current"><a href="/" title="">Home</a></li>
                <#if session??>
                    <li><a href="/logout" title="">Logout</a></li>
                    <li><a href="/admin" title="">Dashboard</a></li>
                <#else >
                    <li><a href="/login" title="">Login</a></li>
                    <li><a href="/register" title="">Register</a></li>
                </#if>
                <li><a href="about.html" title="">About</a></li>
                <li><a href="contact.html" title="">Contact</a></li>
            </ul>
        </nav> <!-- end main-nav-wrap -->

        <div class="search-wrap">

            <form role="search" method="get" class="search-form" action="#">
                <label>
                    <span class="hide-content">Search for:</span>
                    <input type="search" class="search-field" placeholder="Type Your Keywords" value="" name="s"
                           title="Search for:" autocomplete="off">
                </label>
                <input type="submit" class="search-submit" value="Search">
            </form>

            <a href="#" id="close-search" class="close-btn">Close</a>

        </div> <!-- end search wrap -->

        <div class="triggers">
            <a class="search-trigger" href="#"><i class="fa fa-search"></i></a>
            <a class="menu-toggle" href="#"><span>Menu</span></a>
        </div> <!-- end triggers -->

    </div>

</header> <!-- end header -->


<!-- masonry
================================================== -->
<section id="bricks">
    <div id="createBtn">
        <button>
            <a href="/articles/create"><i class="fas fa-plus"></i>Article</a>
        </button>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
        </tr>
        </thead>
        <tbody>


        <#list list as item>

            <tr>
            <td># ${item.id}</td>
            <td>${item.title}</td>
            <td>
                <button class="red">
                    <a href="/articles/${item.id}?action=delete" class="red"><i class="fas fa-trash"></i></a>
                </button>
            </td>
            </tr>

        </#list>
        </tbody>
    </table>
    <!-- end article -->
    </div> <!-- end brick-wrapper -->

</section> <!-- end bricks -->


<div id="preloader">
    <div id="loader"></div>
</div>

<!-- Java Script
================================================== -->
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/jquery.appear.js"></script>
<script src="js/main.js"></script>

</body>

</html>