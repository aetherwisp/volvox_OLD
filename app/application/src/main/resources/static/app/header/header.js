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
    members: [
        isc.DynamicForm.create({
            ID: 'volvox-header-form',
            name: 'volvox-header-form',
            action: /*[[ @{/logout} ]]*/'',
            autoDraw: true,
            height: '100%',
            method: 'POST',
            width: 0,
            canSubmit: true,
            hidden: true,
            visible: false,
            fields: [
                { type: 'hidden', name: /*[[ ${_csrf.parameterName} ]]*/'', value: /*[[ ${_csrf.token} ]]*/null }
            ],
            click: function () {
                this.submit();
            }
        }),
        isc.LayoutSpacer.create({
            width: '*',
            height: '100%'
        }),
        isc.IconMenuButton.create({
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
                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.language} ]]*/'Language',
                        click: function (_target, _item, _menu, _colNum) {
                            isc.say('FIXME: 設定ウィンドウを表示して言語を選択状態にする');
                        }
                    }, {
                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.timezone} ]]*/'Timezone',
                        click: function (_target, _item, _menu, _colNum) {
                            isc.say('FIXME: 設定ウィンドウを表示してタイムゾーンを選択状態にする');
                        }
                    }, {
                        isSeparator: true
                    }, {
                        title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.general} ]]*/'General',
                        click: function (_target, _item, _menu, _colNum) {
                            isc.say('FIXME: 設定ウィンドウを表示');
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
        }),

    ]

});
