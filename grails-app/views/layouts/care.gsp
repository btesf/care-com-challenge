<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Care"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <asset:javascript src="application.js" />

    <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

    <!-- CSS - from Skeleton - http://www.getskeleton.com
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <link rel="stylesheet" href="${resource(dir:'css', file:'normalize.css', base: baseUrl)}"/>
    <link rel="stylesheet" href="${resource(dir:'css', file:'skeleton.css', base: baseUrl)}"/>
    <link rel="stylesheet" href="${resource(dir:'css', file:'care.css', base: baseUrl)}"/>
    <link rel="stylesheet" href="${resource(dir:'css', file:'weatherWidget.css', base: baseUrl)}"/>
    <script type="text/javascript">
        //global variable to keep the application base url to avoid using relative paths in JS - especially useful for functional tst
        var appBaseUrl = "${g.createLink(absolute:true, uri:"/")}";
    </script>
    <g:layoutHead/>
</head>
<body>
    <div class="row">
        <div class="two columns">&nbsp;</div>
        <div class="eight columns">
            <g:layoutBody/>
        </div>
        <div class="two columns">&nbsp;</div>
    </div>
    <asset:javascript src="notify.min.js" />
</body>
</html>