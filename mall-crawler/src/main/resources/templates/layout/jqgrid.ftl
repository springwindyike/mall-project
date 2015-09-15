<!-- The jQuery library is a prerequisite for all jqSuite products -->
<script type="text/ecmascript" src="${springMacroRequestContext.contextPath}/jqgrid/js/jquery-1.11.0.min.js"></script>
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="${springMacroRequestContext.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
<!-- This is the Javascript file of jqGrid -->
<script type="text/ecmascript" src="${springMacroRequestContext.contextPath}/jqgrid/js/jquery.jqGrid.min.js"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/bootstrap.min.css">
<!-- The link to the CSS that the grid needs -->
<link rel="stylesheet" type="text/css" media="screen"
      href="${springMacroRequestContext.contextPath}/jqgrid/css/ui.jqgrid-bootstrap.css"/>
<script>
    $.jgrid.defaults.width = 'auto';
    $.jgrid.defaults.styleUI = 'Bootstrap';
</script>
<script src="${springMacroRequestContext.contextPath}/js/bootstrap.min.js"></script>