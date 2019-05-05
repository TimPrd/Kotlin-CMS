<#-- @ftlvariable name="data" type="pardieu.timothé.cms.tpl.IndexContext" -->


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

    <script>
        function cutString(string, id) {
            if (string.length > 255) {
                return "coucou"
                //document.getElementById("text-" + id ).append(document.createElement("p").append(document.createTextNode(string.slice(0, 100))))
            }
        }

    </script>

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
                <li><a href="/login" title="">Login</a></li>
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

    <div class="row masonry">
        <button>
            <a href="/articles/create"><i class="fas fa-plus"></i> Article</a>
        </button>


        <!-- brick-wrapper -->
        <div class="bricks-wrapper">

            <div class="grid-sizer"></div>

            <div class="brick entry featured-grid animate-this">
                <div class="entry-content">
                    <div id="featured-post-slider" class="flexslider">
                        <ul class="slides">

                            <li>
                                <div class="featured-post-slide">

                                    <div class="post-background"
                                         style="background-image:url('/static/images/thumbs/featured/featured-1.jpg');"></div>

                                    <div class="overlay"></div>

                                    <div class="post-content">
                                        <ul class="entry-meta">
                                            <li>September 06, 2016</li>
                                            <li><a href="#">Naruto Uzumaki</a></li>
                                        </ul>

                                        <h1 class="slide-title"><a href="single-standard.html" title="">Minimalism Never
                                                Goes Out of Style</a></h1>
                                    </div>

                                </div>
                            </li> <!-- /slide -->

                            <li>
                                <div class="featured-post-slide">

                                    <div class="post-background"
                                         style="background-image:url('/static/images/thumbs/featured/featured-2.jpg');"></div>

                                    <div class="overlay"></div>

                                    <div class="post-content">
                                        <ul class="entry-meta">
                                            <li>August 29, 2016</li>
                                            <li><a href="#">Sasuke Uchiha</a></li>
                                        </ul>

                                        <h1 class="slide-title"><a href="single-standard.html" title="">Enhancing Your
                                                Designs with Negative Space</a></h1>
                                    </div>

                                </div>
                            </li> <!-- /slide -->

                            <li>
                                <div class="featured-post-slide">

                                    <div class="post-background"
                                         style="background-image:url('/static/images/thumbs/featured/featured-3.jpg');;"></div>

                                    <div class="overlay"></div>

                                    <div class="post-content">
                                        <ul class="entry-meta">
                                            <li>August 27, 2016</li>
                                            <li><a href="#" class="author">Naruto Uzumaki</a></li>
                                        </ul>

                                        <h1 class="slide-title"><a href="single-standard.html" title="">Music Album
                                                Cover Designs for Inspiration</a></h1>
                                    </div>

                                </div>
                            </li> <!-- end slide -->

                        </ul> <!-- end slides -->
                    </div> <!-- end featured-post-slider -->
                </div> <!-- end entry content -->
            </div>
            <#list list as item>
                <article class="brick entry format-standard animate-this">

                <div class="entry-thumb">
                    <a href="single-standard.html" class="thumb-link">
                        <img src="https://picsum.photos/700/800" alt="picture">
                    </a>
                </div>

                <div class="entry-text">
                <div class="entry-header">

                <div class="entry-meta">
               			<span class="cat-links">
               				<a href="#">Cat</a>
               			</span>
                </div>

                <h1 class="entry-title"><a href="/articles/${item.id}">${item.title}</a></h1>

                </div>
            <div id="text-${item.id}" class="entry-excerpt">

                <#if item.text?length &gt; 100>
                    <script>
                        var t = document.createTextNode("${item.text?js_string}".slice(0, 250) + "... [voir la suite]");
                        var p = document.createElement("p");
                        p.append(t);
                        document.getElementById("text-${item.id}").append(p)


                    </script>
                <#else>
                    ${item.text}
                </#if>


                </div>
                </div>
                </article>
            </#list>

            <!-- end article -->
        </div> <!-- end brick-wrapper -->

    </div> <!-- end row -->

    <div class="row">

        <nav class="pagination">
            <span class="page-numbers prev inactive">Prev</span>
            <span class="page-numbers current">1</span>
            <a href="#" class="page-numbers">2</a>
            <a href="#" class="page-numbers">3</a>
            <a href="#" class="page-numbers">4</a>
            <a href="#" class="page-numbers">5</a>
            <a href="#" class="page-numbers">6</a>
            <a href="#" class="page-numbers">7</a>
            <a href="#" class="page-numbers">8</a>
            <a href="#" class="page-numbers">9</a>
            <a href="#" class="page-numbers next">Next</a>
        </nav>

    </div>

</section> <!-- end bricks -->


<!-- footer
================================================== -->
<footer>

    <div class="footer-main">

        <div class="row">

            <div class="col-four tab-full mob-full footer-info">

                <h4>About Our Site</h4>

                <p>
                    Lorem ipsum Ut velit dolor Ut labore id fugiat in ut fugiat nostrud qui in dolore commodo eu magna
                    Duis cillum dolor officia esse mollit proident Excepteur exercitation nulla. Lorem ipsum In
                    reprehenderit commodo aliqua irure labore.
                </p>

            </div> <!-- end footer-info -->

            <div class="col-two tab-1-3 mob-1-2 site-links">

                <h4>Site Links</h4>

                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Terms</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                </ul>

            </div> <!-- end site-links -->

            <div class="col-two tab-1-3 mob-1-2 social-links">

                <h4>Social</h4>

                <ul>
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">Facebook</a></li>
                    <li><a href="#">Dribbble</a></li>
                    <li><a href="#">Google+</a></li>
                    <li><a href="#">Instagram</a></li>
                </ul>

            </div> <!-- end social links -->

            <div class="col-four tab-1-3 mob-full footer-subscribe">

                <h4>Subscribe</h4>

                <p>Keep yourself updated. Subscribe to our newsletter.</p>

                <div class="subscribe-form">

                    <form id="mc-form" class="group" novalidate="true">

                        <input type="email" value="" name="dEmail" class="email" id="mc-email"
                               placeholder="Type &amp; press enter" required="">

                        <input type="submit" name="subscribe">

                        <label for="mc-email" class="subscribe-message"></label>

                    </form>

                </div>

            </div> <!-- end subscribe -->

        </div> <!-- end row -->

    </div> <!-- end footer-main -->

    <div class="footer-bottom">
        <div class="row">

            <div class="col-twelve">
                <div class="copyright">
                    <span>© Copyright Abstract 2016</span>
                    <span>Design by <a href="http://www.styleshout.com/">styleshout</a></span>
                </div>

                <div id="go-top">
                    <a class="smoothscroll" title="Back to Top" href="#top"><i class="icon icon-arrow-up"></i></a>
                </div>
            </div>

        </div>
    </div> <!-- end footer-bottom -->

</footer>

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