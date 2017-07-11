<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" fata-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="${createLink(uri: '/')}">
                <img src="${assetPath(src: 'logo.svg')}" class="logo" alt="Secret Escapes">
            </a>
        </div>

        <div class="collapse navbar-collapse">
            <div class="nav navbar-nav navbar-right">
                <a href="${createLink(uri: '/account/index')}" class="btn btn-success header-top-padding">Account</a>
                <a href="${createLink(uri: '/transaction/showPay')}" class="btn btn-success header-top-padding">Pay</a>
            </div>
        </div>	<!--/.nav-collapse -->
    </div>
</div>