var volvox.ajax = function(_args) {
    let jqXHR = $.ajax($.extend({}, $.ajaxSettings, _args));
    let deferred = $.Deferred();

    jqXHR.done(function(data, statusText, jqXHR) {
        console.log('done(=success)時の共通処理 ...');

        // deferred.resovle ではなくて deferred.resolveWith で
        // myAjax(...).done() 内でのthisのコンテキストを
        // 明示的に指定する
        deferred.resolveWith(this, arguments);
    });

    jqXHR.fail(function(jqXHR, statusText, errorThrown) {
        console.log('fail(=error)時の共通処理 ...');

        // deferred.reject ではなくて deferred.rejectWith で
        // myAjax(...).fail() 内でのthisのコンテキストを
        // 明示的に指定する
        deferred.rejectWith(this, arguments);
    });

    jqXHR.always(function() {
        console.log('always(=complete)時の共通処理 ...');
    });

    return $.extend({}, jqXHR, deferred.promise());
};
