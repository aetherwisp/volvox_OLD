window.volvox = window.volvox || {};

window.volvox.VolvoxSettingsWindow = isc.defineClass('VolvoxSettingsWindow', 'ModalWindow').addProperties({
    alwaysShowScrollbars: false,
    autoCenter: true,
    autoDraw: false,
    canDragResize: false,
    title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings} ]]*/'Settings',
    maxWidth: '100%',
    width: '75%',
    height: '75%',
    initWidget: function () {
        this.Super("initWidget", arguments);

        this.addMember(isc.HLayout.create({
            autoDraw: false,
            height: '100%',
            width: '100%',
            members: [
                isc.TreeGrid.create({
                    autoDraw: false,
                    height: '100%',
                    maxWidth: '50%',
                    minWidth: 200,
                    width: '30%',
                    showHeader: false,
                    showNodeIcons: false,
                    showResizeBar: true,
                    showOpener: true,
                    data: isc.Tree.create({
                        modelType: 'children',
                        nameProperty: 'title',
                        childrenProperty: 'children',
                        root: /*[[ ${root} ]]*/null,
                        // root: {
                        //     title: null,
                        //     children: [{
                        //         title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.general} ]]*/'General',
                        //         children: [{
                        //             title: /*[[ #{aetherwisp.volvox.presentation.header.menu.settings.timezone} + ' / ' + #{aetherwisp.volvox.presentation.header.menu.settings.language} ]]*/'Timezone / Language',
                        //         }]
                        //     }]
                        // }
                    }),
                    fields: [
                        { name: 'title', title: 'Title' },
                    ],
                    nodeClick: function(_viewer, _node, _recordNum) {
                        isc.say(_node.title + ' clicked.');
                    }
                }),
                //======================================================================
                // 右フォーム
                // TODO: TabSet で切り替える（参考：https://www.smartclient.com/smartclient-release/showcase/?id=closeableTabs）
                isc.Canvas.create({
                    autoDraw: false,
                    height: '100%',
                    // minWidth: '50%',
                    width: '70%'
                })
            ]
        }));
    }

});
