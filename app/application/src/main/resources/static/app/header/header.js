// language=JavaScript

window.volvox = window.volvox || {};

window.volvox.VolvoxHeader = isc.defineClass('VolvoxHeader', 'HLayout').addProperties({
    autoDraw: false,
    alwaysShowScrollbars: false,
    //border: '1px solid black',
    height: 50,
    layoutMargin: 0,
    leaveScrollbarGap: true,
    showResizeBar: false,
    canDragResize: false,
    settingsWindow: isc.ModalWindow.create({
        ID: 'volvox-settings-window',
        autoCenter: true,
        autoDraw: false,
        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings} ]]*/'Settings',
        width: '50%',
        height: '50%',
        members: [
            isc.HLayout.create({
                autoDraw: false,
                height: '100%',
                width: '100%',
                members: [
                    isc.TreeGrid.create({
                        autoDraw: false,
                        height: '100%',
                        minWidth: 200,
                        width: '30%',
                        showHeader: false,
                        showNodeIcons: false,
                        showOpener: true,
                        data: isc.Tree.create({
                            modelType: 'children',
                            nameProperty: 'title',
                            childrenProperty: 'children',
                            root: {
                                children: [{
                                    title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.general} ]]*/'General',
                                    children: [{
                                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.timezone} + ' / ' + #{aetherwisp.volvox.presentation.header.menu.settings.language} ]]*/'Timezone / Language',
                                    }]
                                }]
                            }
                        }),
                        fields: [
                            { name: 'title', title: 'Title' },
                        ]
                    }),
                    isc.Canvas.create({
                        autoDraw: false,
                        height: '100%',
                        width: '70%'
                    })
                ]
            })
        ]
    }),
    logoutForm: isc.DynamicForm.create({
        ID: 'volvox-header-form',
        action: /*[[ @{/logout} ]]*/'',
        autoDraw: true,
        height: 1,
        method: 'POST',
        width: 1,
        canSubmit: true,
        hidden: true,
        visible: false,
        fields: [
            { type: 'hidden', name: /*[[ ${_csrf.parameterName} ]]*/'', value: /*[[ ${_csrf.token} ]]*/null }
        ]
    }),
    initWidget: function () {
        this.Super("initWidget", arguments);

        //======================================================================
        // ヘッダ左の余白
        this.addMember(isc.LayoutSpacer.create({
            width: '*',
            height: '100%'
        }));

        //======================================================================
        // ヘッダ右のメニューボタン
        this.addMember(isc.IconMenuButton.create({
            autoDraw: false,
            align: 'right',
            autoFit: true,
            canHover: false,
            height: '100%',
            icon: null,
            minWidth: 100,
            showMenuOnClick: true,
            showFocusedAsOver: false,
            title: /*[[ ${T(com.github.aetherwisp.volvox.application.user.Users).currentUser().getUsername()} ]]*/'',
            valign: 'center',
            wrap: false,
            menu: isc.Menu.create({
                ID: 'menu',
                autoDraw: false,
                showShadow: true,
                shadowDepth: 10,
                data: [{
                    isSeparator: true
                }, {
                    title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings} ]]*/'Settings',
                    submenu: [{
                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.timezone} + ' / ' + #{aetherwisp.volvox.presentation.header.menu.settings.language} ]]*/'Timezone / Language',
                        click: function (_target, _item, _menu, _colNum) {
                            isc.say('FIXME: 設定ウィンドウを表示して言語を選択状態にする');
                        }
                    }, {
                        isSeparator: true
                    }, {
                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.general} ]]*/'General',
                        // click: 'volvox-settings-window.show();'
                        click: function (_target, _item, _menu, _colNum) {
                            let window = isc.ViewLoader.getById('volvox-settings-window');
                            window.show();
                        }
                    }]
                }, {
                    title: /*[[ #{aetherwisp.volvox.presentation.header.menu.logout} ]]*/'Logout',
                    click: function (_target, _item, _menu, _colNum) {
                        let form = isc.ViewLoader.getById('volvox-header-form');
                        form.submit();
                    },
                }]
            })
        }));
    },
});
