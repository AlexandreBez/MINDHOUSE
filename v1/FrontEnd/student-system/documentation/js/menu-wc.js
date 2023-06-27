'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">
                        <img alt="" class="img-responsive" data-type="custom-logo" data-src="images/Logo.gif">
                    </a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AdminModule.html" data-type="entity-link" >AdminModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' : 'data-target="#xs-components-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' :
                                            'id="xs-components-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' }>
                                            <li class="link">
                                                <a href="components/AdminComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AdminComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HeaderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' : 'data-target="#xs-injectables-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' :
                                        'id="xs-injectables-links-module-AdminModule-7b2376f820f36dc95efd72c92f2ab04ee43bfd82b598f958dd5fdf1d4c7bdc48b0c733ee5ea399af194f4c4b3586ecb685f19dca6beab3205f2257b0e7ff29b8"' }>
                                        <li class="link">
                                            <a href="injectables/AppService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' : 'data-target="#xs-components-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' :
                                            'id="xs-components-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' : 'data-target="#xs-injectables-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' :
                                        'id="xs-injectables-links-module-AppModule-8051fef6527656ef78d8f323ddae3bb028df84ceac42f889decd1033fb2f410e34c04142b091ffd5ecec1d049298ca594983de53fb800c25f891e47f7dfc7871"' }>
                                        <li class="link">
                                            <a href="injectables/AppService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/ClockModule.html" data-type="entity-link" >ClockModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-ClockModule-0766afd5b985cd1acc7406231b75998f06a8fe78af08bf959e8dbe28502149832a65c37d9c1dac90a2e51d7a04097925778d31e82346e34fb1803a0410b09bb8"' : 'data-target="#xs-components-links-module-ClockModule-0766afd5b985cd1acc7406231b75998f06a8fe78af08bf959e8dbe28502149832a65c37d9c1dac90a2e51d7a04097925778d31e82346e34fb1803a0410b09bb8"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ClockModule-0766afd5b985cd1acc7406231b75998f06a8fe78af08bf959e8dbe28502149832a65c37d9c1dac90a2e51d7a04097925778d31e82346e34fb1803a0410b09bb8"' :
                                            'id="xs-components-links-module-ClockModule-0766afd5b985cd1acc7406231b75998f06a8fe78af08bf959e8dbe28502149832a65c37d9c1dac90a2e51d7a04097925778d31e82346e34fb1803a0410b09bb8"' }>
                                            <li class="link">
                                                <a href="components/ClockComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ClockComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/HomeModule.html" data-type="entity-link" >HomeModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' : 'data-target="#xs-components-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' :
                                            'id="xs-components-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' }>
                                            <li class="link">
                                                <a href="components/HomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' : 'data-target="#xs-injectables-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' :
                                        'id="xs-injectables-links-module-HomeModule-586a26febe49e17423cd5f216a2af72913bf98fffdba01652e043619d8c71fda5ffb6511644dd9234c94e4f72ba454d2113a97aef47a871cc6eead4f8b53206c"' }>
                                        <li class="link">
                                            <a href="injectables/AppService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/HomeApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeApiService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/HomeService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/LoginModule.html" data-type="entity-link" >LoginModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' : 'data-target="#xs-components-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' :
                                            'id="xs-components-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' }>
                                            <li class="link">
                                                <a href="components/LoginComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' : 'data-target="#xs-injectables-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' :
                                        'id="xs-injectables-links-module-LoginModule-134b2da11f022711db0e26ad9a15d26ebdc0880574075c4ac4869555a1ca23d53fbe16e1ee21072453bc88c91b64094986297d542331e524d1b62874eef2a961"' }>
                                        <li class="link">
                                            <a href="injectables/AuthAPIService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AuthAPIService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/NotFoundModule.html" data-type="entity-link" >NotFoundModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NotFoundModule-95fb028f1755ee4a562b2b6a3b10fe7ec618084f390f3104b32f5898bbacf40f63b34b30b04c6741a4b730c5f2458efde7215cb181e31fd11e8e6f9a73f02338"' : 'data-target="#xs-components-links-module-NotFoundModule-95fb028f1755ee4a562b2b6a3b10fe7ec618084f390f3104b32f5898bbacf40f63b34b30b04c6741a4b730c5f2458efde7215cb181e31fd11e8e6f9a73f02338"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NotFoundModule-95fb028f1755ee4a562b2b6a3b10fe7ec618084f390f3104b32f5898bbacf40f63b34b30b04c6741a4b730c5f2458efde7215cb181e31fd11e8e6f9a73f02338"' :
                                            'id="xs-components-links-module-NotFoundModule-95fb028f1755ee4a562b2b6a3b10fe7ec618084f390f3104b32f5898bbacf40f63b34b30b04c6741a4b730c5f2458efde7215cb181e31fd11e8e6f9a73f02338"' }>
                                            <li class="link">
                                                <a href="components/NotFoundComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >NotFoundComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/RoutesModule.html" data-type="entity-link" >RoutesModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/SendTokenModule.html" data-type="entity-link" >SendTokenModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' : 'data-target="#xs-components-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' :
                                            'id="xs-components-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' }>
                                            <li class="link">
                                                <a href="components/SendTokenComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SendTokenComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' : 'data-target="#xs-injectables-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' :
                                        'id="xs-injectables-links-module-SendTokenModule-972c9e83ac26aa33a531ff0eff0ebf661d155605fef98ba15dfb3faa4a108d6578e360ee76a9d5fb36e96ec9d0f1a9528c0abc8830b3e6de5be3302caeb5523d"' }>
                                        <li class="link">
                                            <a href="injectables/AuthAPIService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AuthAPIService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/SpinnerModule.html" data-type="entity-link" >SpinnerModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-SpinnerModule-911adc52ffed1a8ac04af2441294bb27331ca0bdd61621c1166e37a80c42569896d7674cf321fb888df0c3e2150144e7ec55887ef859099f0b122fbc350c160c"' : 'data-target="#xs-components-links-module-SpinnerModule-911adc52ffed1a8ac04af2441294bb27331ca0bdd61621c1166e37a80c42569896d7674cf321fb888df0c3e2150144e7ec55887ef859099f0b122fbc350c160c"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-SpinnerModule-911adc52ffed1a8ac04af2441294bb27331ca0bdd61621c1166e37a80c42569896d7674cf321fb888df0c3e2150144e7ec55887ef859099f0b122fbc350c160c"' :
                                            'id="xs-components-links-module-SpinnerModule-911adc52ffed1a8ac04af2441294bb27331ca0bdd61621c1166e37a80c42569896d7674cf321fb888df0c3e2150144e7ec55887ef859099f0b122fbc350c160c"' }>
                                            <li class="link">
                                                <a href="components/SpinnerComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SpinnerComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/UsersModule.html" data-type="entity-link" >UsersModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' : 'data-target="#xs-components-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' :
                                            'id="xs-components-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' }>
                                            <li class="link">
                                                <a href="components/UsersComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UsersComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' : 'data-target="#xs-injectables-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' :
                                        'id="xs-injectables-links-module-UsersModule-378c1efd6b92b3b40fdafb9501b043bd83cfca98c287d8a294d2b69869d42469588da3fc3cf081a41b90f0d3f1dbe9d09a0a1b69e8decb52c0a46b731b247981"' }>
                                        <li class="link">
                                            <a href="injectables/AppService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/UserApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserApiService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/ValidateTokenModule.html" data-type="entity-link" >ValidateTokenModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' : 'data-target="#xs-components-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' :
                                            'id="xs-components-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' }>
                                            <li class="link">
                                                <a href="components/ResetPasswordComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ResetPasswordComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ValidateTokenComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ValidateTokenComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' : 'data-target="#xs-injectables-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' :
                                        'id="xs-injectables-links-module-ValidateTokenModule-38b2e521a7046197c2b17913b84580328d86346a1a30eeca1a3659b8b163e992e714e049de8f6fc09f15a0ebe1df40dfb2587e7e2d300de9f273fa0aa6c72a97"' }>
                                        <li class="link">
                                            <a href="injectables/AuthAPIService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AuthAPIService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#components-links"' :
                            'data-target="#xs-components-links"' }>
                            <span class="icon ion-md-cog"></span>
                            <span>Components</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="components-links"' : 'id="xs-components-links"' }>
                            <li class="link">
                                <a href="components/ResetPasswordComponent.html" data-type="entity-link" >ResetPasswordComponent</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/AppService.html" data-type="entity-link" >AppService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/AuthAPIService.html" data-type="entity-link" >AuthAPIService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/HomeApiService.html" data-type="entity-link" >HomeApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/HomeService.html" data-type="entity-link" >HomeService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserApiService.html" data-type="entity-link" >UserApiService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#guards-links"' :
                            'data-target="#xs-guards-links"' }>
                            <span class="icon ion-ios-lock"></span>
                            <span>Guards</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="guards-links"' : 'id="xs-guards-links"' }>
                            <li class="link">
                                <a href="guards/JWTTokenVerify.html" data-type="entity-link" >JWTTokenVerify</a>
                            </li>
                            <li class="link">
                                <a href="guards/ResetPasswordAccessGuard.html" data-type="entity-link" >ResetPasswordAccessGuard</a>
                            </li>
                            <li class="link">
                                <a href="guards/RoleAdminCheck.html" data-type="entity-link" >RoleAdminCheck</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#interfaces-links"' :
                            'data-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/CustomResp.html" data-type="entity-link" >CustomResp</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/LoginReqModel.html" data-type="entity-link" >LoginReqModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/LoginResModel.html" data-type="entity-link" >LoginResModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ResetPwdHelper.html" data-type="entity-link" >ResetPwdHelper</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SearchFilter.html" data-type="entity-link" >SearchFilter</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/User.html" data-type="entity-link" >User</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Userlist.html" data-type="entity-link" >Userlist</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/UsersQtdRolesData.html" data-type="entity-link" >UsersQtdRolesData</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});