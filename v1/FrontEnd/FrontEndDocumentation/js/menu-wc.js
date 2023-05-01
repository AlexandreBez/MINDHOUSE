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
                    <a href="index.html" data-type="index-link">&#x27;StudentManagementSystem&#x27;</a>
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
                                <a href="modules/AddCourseModule.html" data-type="entity-link" >AddCourseModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' : 'data-target="#xs-components-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' :
                                            'id="xs-components-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' }>
                                            <li class="link">
                                                <a href="components/AddCourseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AddCourseComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' : 'data-target="#xs-injectables-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' :
                                        'id="xs-injectables-links-module-AddCourseModule-09b16d8f4221b4b0048cb30e8c5c2c6cc312546ffc52fa4608bea3ba549aa1300a483ed91e5d6605700f68e6809df2d5d7e409a739dc60b1d36520e5172a4a4e"' }>
                                        <li class="link">
                                            <a href="injectables/CourseApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CourseApiService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/AddStudentModule.html" data-type="entity-link" >AddStudentModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' : 'data-target="#xs-components-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' :
                                            'id="xs-components-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' }>
                                            <li class="link">
                                                <a href="components/AddStudentComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AddStudentComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' : 'data-target="#xs-injectables-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' :
                                        'id="xs-injectables-links-module-AddStudentModule-627b2fe0de011d03ed366a9560f315a05b7a8ff6f1b803a0bc096c9636ac84e374447cd0d433506af64bbdd8c9a52045243bb5e687ad1ee4f5167de8b0f8268d"' }>
                                        <li class="link">
                                            <a href="injectables/StudentApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentApiService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-519ad6400ccb49b1ea07dbe08346c202b13c401c7dcdf5a3aa128d1aed276511010cd603bd680c8d992c40a3af46906e049616decc81746772c9809c72604864"' : 'data-target="#xs-components-links-module-AppModule-519ad6400ccb49b1ea07dbe08346c202b13c401c7dcdf5a3aa128d1aed276511010cd603bd680c8d992c40a3af46906e049616decc81746772c9809c72604864"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-519ad6400ccb49b1ea07dbe08346c202b13c401c7dcdf5a3aa128d1aed276511010cd603bd680c8d992c40a3af46906e049616decc81746772c9809c72604864"' :
                                            'id="xs-components-links-module-AppModule-519ad6400ccb49b1ea07dbe08346c202b13c401c7dcdf5a3aa128d1aed276511010cd603bd680c8d992c40a3af46906e049616decc81746772c9809c72604864"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/CoursesModule.html" data-type="entity-link" >CoursesModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' : 'data-target="#xs-components-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' :
                                            'id="xs-components-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' }>
                                            <li class="link">
                                                <a href="components/CoursesComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CoursesComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UpdateCourseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UpdateCourseComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' : 'data-target="#xs-injectables-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' :
                                        'id="xs-injectables-links-module-CoursesModule-b37c56c54ca32a01b124b4c16b51cbeb9d8513192447f556f3ef48bbedd5f7bdc7329579ad0f17298e56fdb17c4f6307a249c5e8932e6068586765e6eaa72de3"' }>
                                        <li class="link">
                                            <a href="injectables/CourseApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CourseApiService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/HeaderModule.html" data-type="entity-link" >HeaderModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-HeaderModule-d4645f7a29797626f1c1f6fb83dcda848f67f178efc5255b43205b25bc70cbf96aa149e7b36c9d42d67f8c24c53e9e0adfe4e47660579ac2ddcb9910c0379998"' : 'data-target="#xs-components-links-module-HeaderModule-d4645f7a29797626f1c1f6fb83dcda848f67f178efc5255b43205b25bc70cbf96aa149e7b36c9d42d67f8c24c53e9e0adfe4e47660579ac2ddcb9910c0379998"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-HeaderModule-d4645f7a29797626f1c1f6fb83dcda848f67f178efc5255b43205b25bc70cbf96aa149e7b36c9d42d67f8c24c53e9e0adfe4e47660579ac2ddcb9910c0379998"' :
                                            'id="xs-components-links-module-HeaderModule-d4645f7a29797626f1c1f6fb83dcda848f67f178efc5255b43205b25bc70cbf96aa149e7b36c9d42d67f8c24c53e9e0adfe4e47660579ac2ddcb9910c0379998"' }>
                                            <li class="link">
                                                <a href="components/HeaderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/NotFoundModule.html" data-type="entity-link" >NotFoundModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NotFoundModule-8d88a5b1b44a41186153ebe853104e4ef8bb733257c1b4710db5a30dc1de696a84d517e2c86fecccc30453ce46abac6800388f359c9d6e23d6122d3b192f93aa"' : 'data-target="#xs-components-links-module-NotFoundModule-8d88a5b1b44a41186153ebe853104e4ef8bb733257c1b4710db5a30dc1de696a84d517e2c86fecccc30453ce46abac6800388f359c9d6e23d6122d3b192f93aa"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NotFoundModule-8d88a5b1b44a41186153ebe853104e4ef8bb733257c1b4710db5a30dc1de696a84d517e2c86fecccc30453ce46abac6800388f359c9d6e23d6122d3b192f93aa"' :
                                            'id="xs-components-links-module-NotFoundModule-8d88a5b1b44a41186153ebe853104e4ef8bb733257c1b4710db5a30dc1de696a84d517e2c86fecccc30453ce46abac6800388f359c9d6e23d6122d3b192f93aa"' }>
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
                                <a href="modules/SpinnerModule.html" data-type="entity-link" >SpinnerModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-SpinnerModule-46ca84b0284ba2cfb0c8e58c967e7ee32ce8c13896c7c10baf87a198df6dd4e5ca0941be0e91bc10357babc658ef812116b37392def17a65c2c980488080b8d2"' : 'data-target="#xs-components-links-module-SpinnerModule-46ca84b0284ba2cfb0c8e58c967e7ee32ce8c13896c7c10baf87a198df6dd4e5ca0941be0e91bc10357babc658ef812116b37392def17a65c2c980488080b8d2"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-SpinnerModule-46ca84b0284ba2cfb0c8e58c967e7ee32ce8c13896c7c10baf87a198df6dd4e5ca0941be0e91bc10357babc658ef812116b37392def17a65c2c980488080b8d2"' :
                                            'id="xs-components-links-module-SpinnerModule-46ca84b0284ba2cfb0c8e58c967e7ee32ce8c13896c7c10baf87a198df6dd4e5ca0941be0e91bc10357babc658ef812116b37392def17a65c2c980488080b8d2"' }>
                                            <li class="link">
                                                <a href="components/SpinnerComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SpinnerComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/StudentsModule.html" data-type="entity-link" >StudentsModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' : 'data-target="#xs-components-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' :
                                            'id="xs-components-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' }>
                                            <li class="link">
                                                <a href="components/StudentCourseComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentCourseComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/StudentGradesComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentGradesComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/StudentInfoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentInfoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/StudentPaymentsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentPaymentsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/StudentsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UpdateStudentComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UpdateStudentComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' : 'data-target="#xs-injectables-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' :
                                        'id="xs-injectables-links-module-StudentsModule-a0f963b2f328f4ba190aff799ef9207e48192f90d325a182a156501eea9139f51725c8570ebe57019f89ce9a842cefb25b3e66a18e0ce6db7a49da572fb489b3"' }>
                                        <li class="link">
                                            <a href="injectables/GradeApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >GradeApiService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/PaymentApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >PaymentApiService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/StudentApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentApiService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/StudentClassroomCourseApiService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >StudentClassroomCourseApiService</a>
                                        </li>
                                    </ul>
                                </li>
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
                                    <a href="injectables/CourseApiService.html" data-type="entity-link" >CourseApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/GradeApiService.html" data-type="entity-link" >GradeApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/PaymentApiService.html" data-type="entity-link" >PaymentApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/StudentApiService.html" data-type="entity-link" >StudentApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/StudentClassroomCourseApiService.html" data-type="entity-link" >StudentClassroomCourseApiService</a>
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
                                <a href="interfaces/Classroom.html" data-type="entity-link" >Classroom</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Course.html" data-type="entity-link" >Course</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Grade.html" data-type="entity-link" >Grade</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Payment.html" data-type="entity-link" >Payment</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Promotion.html" data-type="entity-link" >Promotion</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Student.html" data-type="entity-link" >Student</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/StudentClassroomCourse.html" data-type="entity-link" >StudentClassroomCourse</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Teacher.html" data-type="entity-link" >Teacher</a>
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
                            <img data-src="images/compodoc-vectorise-inverted.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});