// language=JavaScript

window.volvox = window.volvox || {};

window.volvox.VolvoxHeader = isc.defineClass('VolvoxHeader', 'HLayout').addProperties({
    autoDraw: false,
    //border: '1px solid black',
    height: 50,
    layoutMargin: 0,
    defaultLayoutAlign: 'center',
    defaultResizeBars: 'marked',
    members: [
        isc.LayoutSpacer.create({
            width: '*',
            height: '100%'
        }),
        isc.IconMenuButton.create({
//            align: 'center',
            align: 'right',
            autoFit: true,
            canHover: false,
            height: '100%',
            icon: null,
            minWidth: 100,
            showMenuOnClick: true,
            showFocusedAsOver: false,
            title: /*[[ ${T(com.github.aetherwisp.volvox.application.user.Users).currentUser().getUsername()} ]]*/,
            valign: 'center',
            wrap: false,
            menu: isc.Menu.create({
                ID: 'menu',
                autoDraw: false,
                showShadow: true,
                shadowDepth: 10,
                data: [
                    { title: "New", keyTitle: "Ctrl+N", icon: "icons/16/document_plain_new.png" },
                    { title: "Open", keyTitle: "Ctrl+O", icon: "icons/16/folder_out.png" },
                    { isSeparator: true },
                    { title: "Save", keyTitle: "Ctrl+S", icon: "icons/16/disk_blue.png" },
                    { title: "Save As", icon: "icons/16/save_as.png" },
                    { isSeparator: true },
                    {
                        title: "Recent Documents", icon: "icons/16/folder_document.png", submenu: [
                            { title: "data.xml", checked: true },
                            { title: "Component Guide.doc" },
                            { title: "SmartClient.doc", checked: true },
                            { title: "AJAX.doc" }
                        ]
                    },
                    { isSeparator: true },
                    {
                        title: "Export as...", icon: "icons/16/export1.png", submenu: [
                            { title: "XML" },
                            { title: "CSV" },
                            { title: "Plain text" }
                        ]
                    },
                    { isSeparator: true },
                    { title: "Print", enabled: false, keyTitle: "Ctrl+P", icon: "icons/16/printer3.png" }
                ]
            })
        })
    ]

});
