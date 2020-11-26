//var LOADMASK = app.LoadMask.create();

//======================================================================
// Layouts
isc.VLayout.create({
    width: '100%',
    height: '100%',
    layoutMargin: 0,
    defaultLayoutAlign: 'center',
    defaultResizeBars: 'marked',

    showResizeBar: true,

    members: [
        isc.LayoutSpacer.create({
            height: '*',
        }),
        isc.HLayout.create({
            autoDraw: false,

            height: 250,
            layoutMargin: 0,
            defaultLayoutAlign: 'center',
            defaultResizeBars: 'marked',
            members: [
                isc.LayoutSpacer.create({
                    width: '*',
                    height: '100%'
                }),
                isc.VLayout.create({
                    layoutMargin: 0,
                    showResizeBars: false,
                    members: [
                        isc.DynamicForm.create({
                            action: /*[[ @{/login} ]]*/'',
                            autoDraw: false,
                            autoFocus: true,
                            autoFocusOnError: false,
                            canSubmit: true,
                            groupBorderCSS: '1px solid blue',
                            groupTitle: '<span style="font: bold large/100% monospace;">LOGIN</span>',
                            height: 200,
                            isGroup: true,
                            method: 'POST',
                            padding: 10,
                            numCols: 5,
                            showInlineErrors: true,
                            validateOnChange: true,
                            validateOnExit: true,
                            width: 500,
                            fields: [{
                                type: 'hidden',
                                name: document.querySelector('meta[name="_csrfParameterName"]').content,
                                value: document.querySelector('meta[name="_csrfToken"]').content
                            }, {
                                name: 'username',
                                title: 'Username',
                                type: 'text',
                                colSpan: 5,
                                width: '*',
                                height: 32,
                                showTitle: false,
                                showHintInField: true,
                                showErrorIcon: true,
                                hint: 'ユーザ名',
                                endRow: true,
                                changed: function (_form, _item, _value) {
                                    var link = REISSUE;
                                    if (_item.validate()) {
                                        link.enable();
                                        link.setValue(link.enabledValue);
                                    } else {
                                        link.disable();
                                        link.setValue(link.disabledValue);
                                    }
                                },
                                validators: [{
                                    type: 'required',
                                    clientOnly: true,
                                    errorMessage: 'ユーザ名は必須です。'
                                }]
                            }, {
                                type: 'RowSpacerItem',
                                height: 20,
                                colSpan: 5,
                                endRow: true
                            }, {
                                name: 'password',
                                title: 'Password',
                                type: 'password',
                                colSpan: 5,
                                width: '*',
                                height: 32,
                                showTitle: false,
                                showHintInField: true,
                                hint: 'パスワード',
                                endRow: true,
                                validators: [{
                                    type: 'required',
                                    clientOnly: true,
                                    errorMessage: 'パスワードは必須です。'
                                }]
                            }, {
                                type: 'RowSpacerItem',
                                height: 20,
                                colSpan: 5,
                                endRow: true
                            }, {
                                disabled: true,
                                ID: 'REISSUE',
                                type: 'StaticTextItem',
                                name: 'reissueLink',
                                shouldSaveValue: false,
                                disabled: true,
                                value: '<span style="cursor: pointer; color: silver;">Forgot your password?</span>',
                                disabledValue: '<span style="cursor: default; color: silver;">Forgot your password?</span>',
                                enabledValue: '<span style="cursor: pointer; color: blue;">Forgot your password?</span>',
                                showTitle: false,
                                width: '*',
                                colSpan: 2,
                                textAlign: 'left',
                                startRow: true,
                                click: function (_form, _link) {
                                    console.log('未実装です。すみません。');
                                }
                            }, {
                                type: 'button',
                                name: 'loginButton',
                                title: '<span style="font: bold large/120% monospace;">ログイン</span>',
                                width: '*',
                                height: 32,
                                disabled: true,
                                colSpan: 3,
                                startRow: false,
                                endRow: false,
                                click: function (_form) {
                                    if (_form.validate()) {
                                        _form.submit();
                                    }
                                }
                            }],

                            //======================================================================
                            // Methods
                            login: function () {
                                let form = this;
                            },

                            //======================================================================
                            // Events
                            itemChanged: function (_item, _newValue) {
                                var form = this;

                                // パスワード忘れたリンクの制御
                                var link = form.getField('reissueLink');
                                if (form.getField('username').validate()) {
                                    link.enable();
                                    link.setValue(link.enabledValue);
                                } else {
                                    link.disable();
                                    link.setValue(link.disabledValue);
                                }

                                // ログインボタンの制御
                                var loginButton = form.getField('loginButton');
                                if (form.validate()) {
                                    loginButton.enable();
                                } else {
                                    loginButton.disable();
                                }
                            },
                            itemKeyPress: function (_item, _keyName) {
                                if ('Enter' === _keyName) {
                                    var form = this;
                                    form.login();
                                }
                            },
                        }),
                        isc.Label.create({
                            align: 'left',
                            contents: '<span style="color: red;">あいうえお</span>',
                            height: 50,
                            showEdge: false,
                            valign: 'center'
                        })
                    ]
                }),
                isc.LayoutSpacer.create({
                    width: '*',
                    height: '100%'
                }),
            ]
        }),
        isc.LayoutSpacer.create({
            height: '*',
            border: "1px solid blue",
        }),
    ]
});
