STK.namespace("v6page", function (a, b) {
    var STK = arguments[0],
        $ = arguments[1];
    STK.register("lib.kit.dom.children", function (a) {
        return function (b) {
            if (!a.core.dom.isNode(b)) throw "Parameter must be an HTMLEelement!";
            var c = [];
            for (var d = 0, e = b.childNodes.length; d < e; d++) b.childNodes[d].nodeType == 1 && c.push(b.childNodes[d]);
            return c
        }
    });
    STK.register("lib.kit.dom.textSelection", function (a) {
        return function (b, c) {
            var d, e;
            d = {};
            e = a.parseParam({}, c);
            var f = function (c) {
                    return a.core.dom.selectText(b, c)
                },
                g = function () {
                    b.__areaQuery = a.jsonToQuery(a.core.dom.textSelectArea(b))
                },
                h = function () {
                    b.__areaQuery = !1
                };
            a.addEvent(b, "beforedeactivate", g);
            a.addEvent(b, "active", h);
            var i = function () {
                    var c = null;
                    try {
                        c = a.core.dom.textSelectArea(b)
                    } catch (d) {
                        c = a.queryToJson(b.__areaQuery)
                    }
                    c.start === 0 && c.len === 0 && b.__areaQuery && (c = a.queryToJson(b.__areaQuery));
                    c.start = parseInt(c.start, 10);
                    c.len = parseInt(c.len, 10);
                    return c
                },
                j = function (a, c) {
                    var d = b.value,
                        e = c.start,
                        f = c.len || 0,
                        g = d.slice(0, e),
                        h = d.slice(e + f, d.length);
                    b.value = g + a + h;
                    d = null;
                    g = null;
                    h = null;
                    var e = null,
                        f = null
                };
            d.setCursor = function (a) {
                f(a)
            };
            d.getCursor = function () {
                return i()
            };
            d.insertCursor = function (a) {
                var b = i();
                j(a, b);
                b.len = a.length;
                f(b)
            };
            d.TempletCursor = function (c) {
                var d, e, g;
                d = i();
                d.len > 0 ? e = b.value.substr(d.start, d.len) : e = "";
                g = a.templet(c, {
                    origin: e
                });
                j(g, d);
                d.start = d.start + c.indexOf("#{origin");
                d.len = g.length - c.replace(/#\{[origin].+?\}/, "").length;
                f(d)
            };
            d.insertText = j;
            d.destroy = function () {
                a.removeEvent(b, "beforedeactivate", g);
                a.removeEvent(b, "active", h);
                b = null
            };
            return d
        }
    });
    STK.register("lib.kit.dom.smartInput", function (a) {
        return function (b, c) {
            var d, e, f, g, h, i, j, k, l, m = "stop",
                n, o, p, q, r;
            d = a.parseParam({
                notice: "",
                currentClass: null,
                noticeClass: null,
                noticeStyle: null,
                maxLength: null,
                needLazyInput: !1,
                LazyInputDelay: 200
            }, c);
            e = a.cascadeNode(b);
            h = a.lib.kit.dom.textSelection(b);
            a.custEvent.define(e, "enter");
            a.custEvent.define(e, "ctrlEnter");
            a.custEvent.define(e, "lazyInput");
            f = function () {
                d.maxLength && a.bLength(b.value) > d.maxLength && (b.value = a.leftB(b.value, d.maxLength))
            };
            o = function () {
                if (b.value === d.notice) {
                    b.value = "";
                    d.noticeClass != null && a.removeClassName(b, d.noticeClass)
                }
                d.currentClass != null && a.addClassName(b.parentNode, d.currentClass)
            };
            p = function () {
                if (b.value === "") {
                    b.value = d.notice;
                    d.noticeClass != null && a.addClassName(b, d.noticeClass)
                }
                d.currentClass != null && a.removeClassName(b.parentNode, d.currentClass)
            };
            g = function () {
                f();
                return b.value === d.notice ? "" : b.value
            };
            q = function (b) {
                b.keyCode === 13 && a.custEvent.fire(e, "enter", g())
            };
            r = function (b) {
                (b.keyCode === 13 || b.keyCode === 10) && b.ctrlKey && a.custEvent.fire(e, "ctrlEnter", g())
            };
            i = function () {
                if (m === "stop") {
                    l = setInterval(k, d.LazyInputDelay);
                    m = "sleep"
                }
            };
            j = function () {
                clearInterval(l);
                m = "stop"
            };
            k = function () {
                if (n === b.value)
                    if (m === "weakup") {
                        a.custEvent.fire(e, "lazyInput", b.value);
                        m = "sleep"
                    } else m === "waiting" && (m = "weakup");
                else m = "waiting";
                n = b.value
            };
            if (d.needLazyInput) {
                a.addEvent(b, "focus", i);
                a.addEvent(b, "blur", j)
            }
            a.addEvent(b, "focus", o);
            a.addEvent(b, "blur", p);
            a.addEvent(b, "keyup", f);
            a.addEvent(b, "keydown", q);
            a.addEvent(b, "keydown", r);
            e.getValue = g;
            e.setValue = function (a) {
                b.value = a;
                f();
                return e
            };
            e.setNotice = function (a) {
                d.notice = a;
                return e
            };
            e.setNoticeClass = function (a) {
                d.noticeClass = a;
                return e
            };
            e.setNoticeStyle = function (a) {
                d.noticeStyle = a;
                return e
            };
            e.setMaxLength = function (a) {
                d.maxLength = a;
                return e
            };
            e.restart = function () {
                p()
            };
            e.startLazyInput = i;
            e.stopLazyInput = j;
            e.setCursor = h.setCursor;
            e.getCursor = h.getCursor;
            e.insertCursor = h.insertCursor;
            e.insertText = h.insertText;
            e.destroy = function () {
                if (d.needLazyInput) {
                    a.removeEvent(b, "focus", o);
                    a.removeEvent(b, "blur", p)
                }
                j();
                a.removeEvent(b, "focus", o);
                a.removeEvent(b, "blur", p);
                a.removeEvent(b, "keyup", f);
                a.removeEvent(b, "keydown", q);
                a.removeEvent(b, "keydown", r);
                a.custEvent.undefine(e, "enter");
                a.custEvent.undefine(e, "ctrlEnter");
                a.custEvent.undefine(e, "lazyInput");
                h.destroy();
                e = null
            };
            return e
        }
    });
    STK.register("lib.kit.extra.merge", function (a) {
        return function (a, b) {
            var c = {};
            for (var d in a) c[d] = a[d];
            for (var d in b) c[d] = b[d];
            return c
        }
    });
    STK.register("pl.content.feednav.source.search", function (a) {
        return function (b) {
            var c = a.builder(b).list,
                d = a.delegatedEvent(b),
                e, f = [],
                g = {};
            a.custEvent.define(g, ["search"]);
            var h = function () {
                    e = a.lib.kit.dom.smartInput(c.keyword[0], {
                        notice: c.keyword[0].getAttribute("notice")
                    });
                    d.add("search_button", "click", j);
                    d.add("search_date", "click", i);
                    k()
                },
                i = function (b) {
                    var c = b.data.type,
                        d = {
                            pos: "bottom-left",
                            offsetX: 1,
                            offsetY: 1
                        };
                    f[c] || ~function (c) {
                        f[c] = a.ui.calendar({
                            callback: function (a) {
                                b.el.value = a;
                                f[c].hide()
                            }
                        })
                    }(c);
                    a.foreach(f, function (a, e) {
                        c == e && a.show(b.el, d)
                    })
                },
                j = function () {
                    var d = a.htmlToJson(b);
                    d.is_search = 1;
                    d.is_searchadv = 1;
                    d.key_word == c.keyword[0].getAttribute("notice") && (d.key_word = "");
                    a.custEvent.fire(g, "search", [d])
                },
                k = function () {
                    var c = a.queryToJson(FM.getURL().query || ""),
                        d = !0;
                    a.foreach(a.sizzle("input[type=checkbox]", b), function (a) {
                        a.checked = c[a.getAttribute("name")] == 1 ? !0 : !1;
                        c[a.getAttribute("name")] == 1 && (d = !1)
                    });
                    d && a.foreach(a.sizzle("input[type=checkbox]", b), function (a) {
                        a.checked = !0
                    });
                    a.foreach(a.sizzle("input[type=text]", b), function (a) {
                        c[a.getAttribute("name")] && (a.value = c[a.getAttribute("name")])
                    });
                    e.setValue(c.is_search && c.key_word ? decodeURIComponent(c.key_word) : "");
                    e.restart()
                },
                l = function () {
                    d && d.destroy && d.destroy();
                    e && e.destroy && e.destroy();
                    a.custEvent.undefine(g, ["search"]);
                    f[1] && f[1].destroy && f[1].destroy();
                    f[2] && f[2].destroy && f[2].destroy()
                };
            g.destroy = l;
            g.reset = k;
            h();
            return g
        }
    });
    STK.register("lib.kit.extra.listener", function (a) {
        var b = {},
            c = {};
        c.define = function (c, d) {
            if (b[c] != null) throw "lib.kit.extra.listener.define: 频道已被占用";
            b[c] = d;
            var e = {};
            e.register = function (d, e) {
                if (b[c] == null) throw "lib.kit.extra.listener.define: 频道未定义";
                a.listener.register(c, d, e)
            };
            e.fire = function (d, e) {
                if (b[c] == null) throw "commonlistener.define: 频道未定义";
                a.listener.fire(c, d, e)
            };
            e.remove = function (b, d) {
                a.listener.remove(c, b, d)
            };
            e.cache = function (b) {
                return a.listener.cache(c, b)
            };
            return e
        };
        return c
    });
    STK.register("conf.channel.feed", function (a) {
        var b = ["forward", "publish", "comment", "delete", "refresh", "reply", "feedTagUpdate", "feedTagMoreUpdate", "qfaceAdd", "qfaceCount", "timeFeedPublish", "timeFeedEdit", "videolayer"];
        return a.lib.kit.extra.listener.define("conf.channel.feed", b)
    });
    STK.register("lib.kit.io.ajax", function (a) {
        var b = function (b, c, d) {
            c = c | 0 || 1;
            d = d || "fail";
            var e = b.args;
            e.__rnd && delete e.__rnd;
            (new Image).src = "//weibolog.sinaapp.com/?t=" + c + "&u=" + encodeURIComponent(b.url) + "&p=" + encodeURIComponent(a.core.json.jsonToQuery(e)) + "&m=" + d;
            (new Image).src = "//s1.sinaedge.com/whb.gif?t=" + c + "&u=" + encodeURIComponent(b.url) + "&p=" + encodeURIComponent(a.core.json.jsonToQuery(e)) + "&m=" + d
        };
        return function (c) {
            var d = {},
                e = [],
                f = null,
                g = !1,
                h = a.parseParam({
                    url: "",
                    method: "get",
                    responseType: "json",
                    timeout: 3e4,
                    onTraning: a.funcEmpty,
                    isEncode: !0
                }, c);
            h.onComplete = function (a) {
                g = !1;
                c.onComplete(a, h.args);
                setTimeout(i, 0)
            };
            h.onFail = function (a) {
                g = !1;
                if (typeof c.onFail == "function") try {
                    c.onFail(a, h.args)
                } catch (d) {
                }
                setTimeout(i, 0);
                try {
                    b(h)
                } catch (d) {
                }
            };
            h.onTimeout = function (a) {
                try {
                    b(h);
                    c.onTimeout(a)
                } catch (d) {
                }
            };
            var i = function () {
                    if (!!e.length) {
                        if (g === !0) return;
                        g = !0;
                        h.args = e.shift();
                        if (h.method.toLowerCase() == "post") {
                            var b = a.core.util.URL(h.url);
                            b.setParam("__rnd", +(new Date));
                            h.url = b.toString()
                        }
                        f = a.ajax(h)
                    }
                },
                j = function (a) {
                    while (e.length) e.shift();
                    g = !1;
                    if (f) try {
                        f.abort()
                    } catch (b) {
                    }
                    f = null
                };
            d.request = function (a) {
                a || (a = {});
                c.noQueue && j();
                if (!c.uniqueRequest || !f) {
                    e.push(a);
                    a._t = 0;
                    i()
                }
            };
            d.abort = j;
            return d
        }
    });
    STK.register("lib.kit.io.jsonp", function (a) {
        return function (b) {
            var c = a.parseParam({
                    url: "",
                    method: "get",
                    responseType: "json",
                    varkey: "_v",
                    timeout: 3e4,
                    onComplete: a.funcEmpty,
                    onTraning: a.funcEmpty,
                    onFail: a.funcEmpty,
                    isEncode: !0
                }, b),
                d = [],
                e = {},
                f = !1,
                g = function () {
                    if (!!d.length) {
                        if (f === !0) return;
                        f = !0;
                        e.args = d.shift();
                        e.onComplete = function (a) {
                            f = !1;
                            c.onComplete(a, e.args);
                            setTimeout(g, 0)
                        };
                        e.onFail = function (a) {
                            f = !1;
                            c.onFail(a);
                            setTimeout(g, 0)
                        };
                        a.jsonp(a.core.json.merge(c, {
                            args: e.args,
                            onComplete: function (a) {
                                e.onComplete(a)
                            },
                            onFail: function (a) {
                                try {
                                    e.onFail(a)
                                } catch (b) {
                                }
                            }
                        }))
                    }
                },
                h = {};
            h.request = function (a) {
                a || (a = {});
                d.push(a);
                a._t = 1;
                g()
            };
            h.abort = function (a) {
                while (d.length) d.shift();
                f = !1;
                e = null
            };
            return h
        }
    });
    STK.register("lib.kit.io.ijax", function (a) {
        return function (b) {
            var c = a.parseParam({
                    url: "",
                    timeout: 3e4,
                    isEncode: !0,
                    abaurl: null,
                    responseName: null,
                    varkey: "callback",
                    abakey: "callback"
                }, b),
                d = [],
                e = null,
                f = !1;
            c.onComplete = function (a, d) {
                f = !1;
                b.onComplete(a, c.form, d);
                c.form = null;
                c.args = null;
                setTimeout(g, 0)
            };
            c.onFail = function (a, d) {
                f = !1;
                b.onFail(a, c.form, d);
                c.form = null;
                c.args = null;
                setTimeout(g, 0)
            };
            var g = function () {
                    var b;
                    if (!!d.length) {
                        if (f === !0) return;
                        f = !0;
                        b = d.shift();
                        c.args = b.args;
                        c.form = b.form;
                        e = a.ijax(c)
                    }
                },
                h = function (a) {
                    while (d.length) d.shift();
                    f = !1;
                    if (e) try {
                        e.abort()
                    } catch (b) {
                    }
                    e = null
                },
                i = {};
            i.request = function (c, e) {
                if (!a.isNode(c)) throw "[lib.kit.io.ijax.request] need a form as first parameter";
                e || (e = {});
                b.noQueue && h();
                d.push({
                    form: c,
                    args: e
                });
                g()
            };
            i.abort = h;
            return i
        }
    });
    STK.register("lib.kit.io.inter", function (a) {
        var b = a.core.json.merge;
        return function () {
            var c = {},
                d = {},
                e = {},
                f = function (a, b) {
                    return function (c, d) {
                        try {
                            b.onComplete(c, d)
                        } catch (f) {
                        }
                        try {
                            c.code === "100000" ? b.onSuccess(c, d) : b.onError(c, d)
                        } catch (f) {
                        }
                        for (var g in e[a]) try {
                            e[a][g](c, d)
                        } catch (f) {
                        }
                    }
                },
                g = function (a, b, c) {
                    return function (d) {
                        try {
                            b.onComplete(d, c)
                        } catch (f) {
                        }
                        try {
                            d.code === "100000" ? b.onSuccess(d, c) : b.onError(d, c)
                        } catch (f) {
                        }
                        for (var g in e[a]) try {
                            e[a][g](d, c)
                        } catch (f) {
                        }
                    }
                };
            c.register = function (a, b) {
                if (typeof d[a] != "undefined") throw a + " registered";
                d[a] = b;
                e[a] = {}
            };
            c.addHook = function (b, c) {
                var d = a.core.util.getUniqueKey();
                e[b][d] = c;
                return d
            };
            c.rmHook = function (a, b) {
                e[a] && e[a][b] && delete e[a][b]
            };
            c.getTrans = function (c, e) {
                var g = b(d[c], e);
                g.onComplete = f(c, e);
                g.url += (g.url.indexOf("?") >= 0 ? "&" : "?") + "ajwvr=6";
                g.withDomain && (g.url += "&domain=" + $CONFIG.domain);
                var h = d[c].requestMode,
                    i = "ajax";
                if (h === "jsonp" || h === "ijax") i = h;
                return a.lib.kit.io[i](g)
            };
            c.request = function (c, e, f) {
                var h = b(d[c], e);
                h.onComplete = g(c, e, f);
                h.url += (h.url.indexOf("?") >= 0 ? "&" : "?") + "ajwvr=6";
                h.withDomain && (h.url += "&domain=" + $CONFIG.domain);
                h = a.core.obj.cut(h, ["noqueue"]);
                h.args = f;
                var i = d[c].requestMode;
                return i === "jsonp" ? a.jsonp(h) : i === "ijax" ? a.ijax(h) : a.ajax(h)
            };
            return c
        }
    });
    STK.register("conf.trans.feed.tag", function (a) {
        var b = a.lib.kit.io.inter(),
            c = b.register;
        c("feedTagList", {
            url: "/aj/mblog/tag/mytaglist",
            method: "post"
        });
        c("feedTagListHtml", {
            url: "/p/aj/v6/mblog/taglist",
            method: "get"
        });
        c("feedTagUpdate", {
            url: "/aj/v6/mblog/tag/updatetags",
            method: "post"
        });
        c("feedTagDel", {
            url: "/aj/mblog/tag/destroy",
            method: "post"
        });
        c("feedTagEdit", {
            url: "/aj/mblog/tag/update",
            method: "post"
        });
        return b
    });
    STK.register("lib.kit.dom.parentElementBy", function (a) {
        return function (a, b, c) {
            if (!a || !b) throw new Error("传入的参数为空");
            var d = 0,
                e;
            a = a.parentNode;
            while (a && a.parentNode) {
                d++;
                e = c(a);
                if (e === !1) return !1;
                if (e === !0) return a;
                if (e === b) return null;
                a = a.parentNode;
                if (d > 3e4) return !1
            }
            return null
        }
    });
    STK.register("lib.kit.extra.language", function (a) {
        window.$LANG || (window.$LANG = {});
        return function (b) {
            var c = [].splice.call(arguments, 1, arguments.length),
                d = [b, $LANG].concat(c),
                e = a.core.util.language.apply(this, d);
            return e
        }
    });
    STK.register("lib.kit.dom.firstChild", function (a) {
        var b = a.core.dom.next;
        return function (a) {
            var c = a.firstChild;
            c && c.nodeType != 1 && (c = b(c));
            return c
        }
    });
    STK.register("lib.dialog.loginLayer", function (a) {
        var b, c = "//tjs.sjs.sinajs.cn/t5/register/js/page/remote/loginLayer.js?version=";
        return function (d) {
            d = a.core.obj.parseParam({
                lang: "zh-cn",
                loginSuccessUrl: encodeURIComponent(window.location.href),
                version: window.$CONFIG && window.$CONFIG.version || "20170103"
            }, d || {});
            "$CONFIG" in window || (window.$CONFIG = {});
            window.$CONFIG.sinaSSOControllerTemporary = window.$CONFIG.sinaSSOControllerTemporary || {};
            if (!window.$CONFIG.sinaSSOControllerTemporary.lock) {
                window.$CONFIG.sinaSSOControllerTemporary.lock = !0;
                if (window.sinaSSOController) {
                    window.sinaSSOController.loginSuccessUrl && window.sinaSSOController.loginSuccessUrl.indexOf("comefrom=loginlayer") > -1 ? window.$CONFIG.sinaSSOControllerTemporary.top = window.sinaSSOController : window.$CONFIG.sinaSSOControllerTemporary.oth = window.sinaSSOController;
                    window.sinaSSOController = window.$CONFIG.sinaSSOControllerTemporary.top || {}
                }
                if (window.WBtopGlobal_loginLayer) window.WBtopGlobal_loginLayer(d);
                else {
                    if (b) return;
                    b = !0;
                    a.core.io.scriptLoader({
                        url: c + d.version,
                        onComplete: function () {
                            b = !1;
                            window.WBtopGlobal_loginLayer(d)
                        },
                        timeout: 1e4,
                        onTimeout: function () {
                            b = !1
                        }
                    })
                }
            }
        }
    });
    STK.register("lib.kit.io.cssLoader", function (a) {
        var b = "",
            c = "//img.t.sinajs.cn/t4/",
            d = "//timg.sjs.sinajs.cn/t4/";
        if (typeof $CONFIG != "undefined") {
            c = $CONFIG.cssPath || c;
            b = $CONFIG.version || ""
        }
        var e = {};
        return function (f, g, h, i, j) {
            i = i || b;
            h = h || function () {
            };
            var k = function (a, b) {
                    var c = e[a] || (e[a] = {
                        loaded: !1,
                        list: []
                    });
                    if (c.loaded) {
                        b(a);
                        return !1
                    }
                    c.list.push(b);
                    return c.list.length > 1 ? !1 : !0
                },
                l = function (a) {
                    var b = e[a].list;
                    for (var c = 0; c < b.length; c++) b[c](a);
                    e[a].loaded = !0;
                    delete e[a].list
                };
            if (!!k(f, h)) {
                var m;
                j ? m = d + f : m = c + f + "?version=" + i;
                var n = a.C("link");
                n.setAttribute("rel", "Stylesheet");
                n.setAttribute("type", "text/css");
                n.setAttribute("charset", "utf-8");
                n.setAttribute("href", m);
                document.getElementsByTagName("head")[0].appendChild(n);
                var o = a.C("div");
                o.id = g;
                a.core.util.hideContainer.appendChild(o);
                var p = 3e3,
                    q = function () {
                        if (parseInt(a.core.dom.getStyle(o, "height")) == 42) {
                            a.core.util.hideContainer.removeChild(o);
                            l(f)
                        } else if (--p > 0) setTimeout(q, 10);
                        else {
                            a.log(f + "timeout!");
                            a.core.util.hideContainer.removeChild(o);
                            delete e[f]
                        }
                    };
                setTimeout(q, 50)
            }
        }
    });
    STK.register("lib.dialog.authentication", function (a) {
        return function (b) {
            var c = a.lib.kit.extra.language,
                d = a.core.util.browser;
            b = a.parseParam({
                src: "//weibo.com/a/verify/realname?stage=home_verification",
                icon: "warn",
                isHold: !0,
                width: "380px",
                height: "240px",
                title: c("#L{帐号验证}")
            }, b || {});
            var e = {},
                f, g, h = !1,
                i = "tblog_checkfailed_reform",
                j = {
                    init: function () {
                        f = a.ui.dialog(b);
                        var c = [];
                        c.push('<iframe id="account_authentication" name="account_authentication" node-type="frame" width="' + b.width + '" height="' + b.height + '" allowtransparency="true" scrolling="no" frameborder="0" src=""></iframe>');
                        var d = a.builder(c.join(""));
                        f.setTitle(b.title);
                        f.setContent(d.box);
                        var e = f.getDomList()
                    },
                    show: function () {
                        try {
                            window.SUDA && SUDA.uaTrack && SUDA.uaTrack(i, "checkfailed_box")
                        } catch (c) {
                        }
                        h || a.lib.kit.io.cssLoader("style/css/module/layer/layer_check_identity.css", "js_style_css_module_layer_check_identity", function () {
                            h = !0
                        });
                        f.show().setMiddle();
                        g = a.E("account_authentication");
                        var d = decodeURIComponent(b.src) + "&rnd=";
                        g.attachEvent ? g.attachEvent("onload", function () {
                            g.height = b.height;
                            f.setMiddle()
                        }) : g.onload = function () {
                            g.height = b.height;
                            f.setMiddle()
                        };
                        g.src = d + a.core.util.getUniqueKey()
                    },
                    destroy: function () {
                    },
                    hook: function (a, b) {
                        try {
                            a == "100000" ? j.verifySucc() : j.verifyFail()
                        } catch (c) {
                        }
                    },
                    verifySucc: function () {
                        window.SUDA && SUDA.uaTrack && SUDA.uaTrack(i, "checkfailed_success");
                        f.hide();
                        var b = {
                                title: c("#L{提示}"),
                                icon: "success",
                                OK: function () {
                                    window.SUDA && SUDA.uaTrack && SUDA.uaTrack(i, "checkfailed_play");
                                    history.go(0)
                                },
                                OKText: c("#L{进入首页}"),
                                msg: c("#L{恭喜，您的身份已验证成功，马上进入微博。}")
                            },
                            d = a.ui.alert(b.msg, b);
                        a.custEvent.add(d, "hide", function () {
                            history.go(0)
                        })
                    },
                    verifyFail: function () {
                        window.SUDA && SUDA.uaTrack && SUDA.uaTrack(i, "checkfailed_twotimes");
                        f.hide();
                        var b = {
                                title: c("#L{提示}"),
                                icon: "warn",
                                OK: function () {
                                    SUDA.uaTrack && SUDA.uaTrack(i, "checkfailed_triple");
                                    j.show()
                                },
                                OKText: c("#L{再次验证}"),
                                msg: c("#L{抱歉，您的身份信息不准确，请再次验证。<br/>}") + '<a class="S_spetxt" suda-data="key=tblog_checkfailed_reform&value=checkfailed_havealook" href="//weibo.com">' + c("#L{您也可以先体验微博，随后再验证身份信息>>}") + "</a>"
                            },
                            d = a.ui.alert(b.msg, b);
                        a.custEvent.add(d, "hide", function () {
                            history.go(0)
                        })
                    }
                };
            j.init();
            e.destroy = j.destory;
            e.show = j.show;
            window.App = window.App || {};
            window.App.checkRealName = j.hook;
            return e
        }
    });
    STK.register("lib.dialog.memberDialog", function (a) {
        var b = '<div node-type="outer" class="layer_point"><dl class="point clearfix"><dt><span class="" node-type="icon"></span></dt><dd node-type="inner"><p class="S_txt1" node-type="textLarge"></p><p class="S_txt1" node-type="textComplex"></p><p class="S_txt2" node-type="textSmall"></p></dd></dl></div><div class="W_layer_btn S_bg1"><a href="javascript:void(0);" class="W_btn_b" node-type="OK"></a><a href="javascript:void(0);" class="W_btn_a" node-type="cancel"></a><a href="http://vip.weibo.com/paycenter?pageid=byebank" class="W_btn_a" node-type="member"><span><em class="W_icon icon_member"></em>#L{立即开通会员}</span></a></div>',
            c = {
                success: "icon_succM",
                error: "icon_errorM",
                warn: "icon_warnM",
                "delete": "icon_delM",
                question: "icon_questionM"
            },
            d = a.lib.kit.extra.language,
            e = function (e, f) {
                var g, h, i, j, k, l;
                g = a.parseParam({
                    title: "&nbsp;",
                    icon: "warn",
                    textLarge: e,
                    textComplex: "",
                    textSmall: "",
                    OK: a.funcEmpty,
                    OKText: d("#L{确定}"),
                    cancel: a.funcEmpty,
                    cancelText: d("#L{确认}")
                }, f);
                g.icon = c[g.icon];
                h = {};
                i = a.ui.dialog();
                i.setContent(d(b));
                l = i.getDomList(!0);
                l.icon.className = g.icon;
                l.textLarge.innerHTML = g.textLarge;
                l.textComplex.innerHTML = g.textComplex;
                l.textSmall.innerHTML = g.textSmall;
                l.OK.innerHTML = "<span>" + g.OKText + "</span>";
                l.cancel.innerHTML = "<span>" + g.cancelText + "</span>";
                i.setTitle(g.title);
                i.getDomList().title.style.borderBottomStyle = "none";
                var m = function () {
                    j = !0;
                    k = a.htmlToJson(l.textComplex);
                    i.hide()
                };
                a.addEvent(l.OK, "click", m);
                a.addEvent(l.cancel, "click", i.hide);
                a.custEvent.add(i, "hide", function () {
                    a.custEvent.remove(i, "hide", arguments.callee);
                    a.removeEvent(l.OK, "click", m);
                    a.removeEvent(l.cancel, "click", i.hide);
                    j ? g.OK(k) : g.cancel(k)
                });
                i.show().setMiddle();
                h.dia = i;
                return h
            };
        return function (b) {
            b = a.parseParam({
                type: "follow",
                errortype: "1"
            }, b);
            var c, f, g = {
                    textLarge: d("#L{您已达到悄悄关注上限！}"),
                    textComplex: d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{悄悄关注上限立即提高}'),
                    textSmall: d('#L{可}<a href="http://vip.weibo.com/paycenter?pageid=byebank" class="S_link2">#L{开通会员}</a>#L{或先将悄悄关注减少至10人以下，再添加}'),
                    OKText: d("#L{管理我的悄悄关注}"),
                    OK: function () {
                        a.preventDefault();
                        window.location.href = "/" + $CONFIG.uid + "/whisper"
                    }
                },
                h = {
                    textLarge: d("#L{您已达到关注上限！}"),
                    textComplex: d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{关注上限立即提高}'),
                    textSmall: d('#L{可}<a href="http://vip.weibo.com/paycenter?pageid=byebank" class="S_link2">#L{开通会员}</a>#L{或先将关注减少至2000人以下，再添加}'),
                    OKText: d("#L{管理我的关注}"),
                    OK: function () {
                        a.preventDefault();
                        window.location.href = "/" + $CONFIG.uid + "/follow"
                    }
                };
            if (b.type == "quiet") {
                switch (parseInt(b.errortype, 10)) {
                    case 2:
                        g.textLarge = d("#L{您当前已达会员等级悄悄关注上限啦！}");
                        g.textSmall = "";
                        g.textComplex = d('<a href="http://vip.weibo.com/privilege" class="S_link2">#L{了解更多会员特权信息»}</a>');
                        break;
                    case 1:
                        g.textLarge = d("#L{您已达到悄悄关注上限！}");
                        g.textSmall = "";
                        g.textComplex = d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{悄悄关注上限立即提高}');
                        break;
                    case 3:
                        g.textLarge = d("#L{您已达到悄悄关注上限！}");
                        g.textComplex = d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{悄悄关注上限立即提高}');
                        g.textSmall = d('#L{可}<a href="http://vip.weibo.com/paycenter">#L{开通会员}</a>#L{或将悄悄关注减少至10人以下，再添加}')
                }
                c = g
            } else {
                switch (parseInt(b.errortype, 10)) {
                    case 2:
                        h.textLarge = d("#L{您当前已达会员等级关注上限啦！}");
                        h.textSmall = "";
                        h.textComplex = d('<a href="http://vip.weibo.com/privilege" class="S_link2">#L{了解更多会员特权信息»}</a>');
                        break;
                    case 1:
                        h.textLarge = d("#L{您已达到关注上限！}");
                        h.textSmall = "";
                        h.textComplex = d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{关注上限立即提高}');
                        break;
                    case 3:
                        h.textLarge = d("#L{您已达到关注上限！}");
                        h.textComplex = d('#L{开通}<a href="http://vip.weibo.com/privilege">#L{微博会员}</a>，#L{关注上限立即提高}');
                        h.textSmall = d('#L{可}<a href="http://vip.weibo.com/paycenter">#L{开通会员}</a>#L{或将关注减少至2000人以下，再添加}')
                }
                c = h
            }
            f = e("", c);
            parseInt(b.errortype, 10) == 2 ? f.dia.getDomList().member.style.display = "none" : f.dia.getDomList().cancel.style.display = "none"
        }
    });
    STK.register("conf.trans.publisher", function (a) {
        var b = a.lib.kit.io.inter(),
            c = b.register;
        c("publish_v6", {
            url: "/aj/mblog/add",
            method: "post"
        });
        c("publish_page", {
            url: "/p/aj/v6/mblog/add",
            method: "post",
            withDomain: !0
        });
        c("publish", {
            url: "/p/aj/v6/mblog/add",
            method: "post",
            withDomain: !0
        });
        c("publishPro", {
            url: "/aj/mblog/add",
            method: "post"
        });
        c("interactive", {
            url: "/aj/mblog/interactive",
            method: "post"
        });
        c("timingPublish", {
            url: "/aj/v6/mblog/addtime",
            method: "post"
        });
        c("getpublish", {
            url: "/p/aj/v6/publish",
            method: "get"
        });
        c("reviewadd", {
            url: "/p/aj/review/add",
            method: "post"
        });
        c("follow", {
            url: "/aj/f/followed",
            method: "post"
        });
        c("proxy", {
            url: "/p/aj/proxy",
            method: "post"
        });
        c("getReEdit", {
            url: "/p/aj/v6/edit",
            method: "get",
            withDomain: !0
        });
        c("publisherReEdit", {
            url: "/p/aj/v6/mblog/edit",
            method: "post",
            withDomain: !0
        });
        c("getHistoricRecod", {
            url: "/p/aj/v6/history",
            method: "get",
            withDomain: !0
        });
        return b
    });
    STK.register("lib.dialog.ioError", function (a) {
        var b = a.lib.kit.extra.language,
            c, d;
        return function (d, e, f) {
            var g = {},
                h, i, j = function () {
                },
                k = {
                    init: function () {
                        k.data()
                    },
                    data: function () {
                        i = a.parseParam({
                            auto: !0,
                            call: j,
                            ok: j,
                            cancel: j,
                            beside: null
                        }, f);
                        h = a.parseParam({
                            location: "",
                            title: "",
                            icon: "",
                            oKText: b("#L{确 定}"),
                            cancelText: b("#L{取 消}"),
                            api: "",
                            reload: "",
                            suda: ""
                        }, e.data);
                        h.msg = e.msg || b("#L{网络繁忙}");
                        e.data && e.data.OKText && (h.okText = e.data.OKText);
                        h.OK = function () {
                            a.preventDefault();
                            var b = a.queryToJson(h.suda || "");
                            b = b.ok || {};
                            window.SUDA && SUDA.uaTrack && b.key && SUDA.uaTrack(b.key, b.value);
                            i.ok();
                            h.location && (window.location.href = h.location)
                        };
                        h.cancel = function () {
                            a.preventDefault();
                            var b = a.queryToJson(h.suda || "");
                            b = b.cancel || {};
                            window.SUDA && SUDA.uaTrack && b.key && SUDA.uaTrack(b.key, b.value);
                            i.cancel()
                        }
                    },
                    run: function () {
                        var a = l[e.code] || l[100001];
                        return a() || i.call(h, e)
                    },
                    destroy: function () {
                        c && c.destroy()
                    }
                },
                l = {
                    100001: function () {
                        if (i.beside) a.ui.tipAlert(h.msg, a.core.json.merge(h, {
                            autoHide: !1,
                            icon: "warnS"
                        })).beside(i.beside);
                        else {
                            var b = a.ui.alert(h.msg.split("\\n"), a.core.json.merge(h, {
                                icon: "warnB"
                            }));
                            a.addClassName(b.getOuter(), "W_translateZ")
                        }
                    },
                    100002: function () {
                        a.lib.dialog.loginLayer({
                            lang: window.$CONFIG && window.$CONFIG.lang || "zh-cn"
                        })
                    },
                    100003: function () {
                        function c() {
                            if (h.location) location.href = h.location;
                            else if (h.api) {
                                var c = h.api.split("?"),
                                    d = a.core.json.merge({
                                        api: c[0]
                                    }, a.queryToJson(c[1] || ""));
                                a.conf.trans.publisher.request("proxy", {
                                    onSuccess: function (c) {
                                        a.ui.notice(b(c.msg) || b("#L{请求成功}")).on("hide", function () {
                                            var a = FM.getState();
                                            $CONFIG.bigpipe ? FM.setState(a.split("#")[0] + "#_0") : location.reload()
                                        })
                                    },
                                    onError: function (b) {
                                        a.lib.dialog.ioError(b.code, b)
                                    },
                                    onFail: function (b) {
                                        a.lib.dialog.ioError(b.code, b)
                                    }
                                }, d)
                            }
                        }

                        i.beside ? a.ui.tipConfirm(h.msg, h).ok(c).beside(i.beside) : a.ui.confirm(h.msg.split("\n"), h).ok(c)
                    },
                    100004: function () {
                        c || (c = a.lib.dialog.authentication());
                        c.show()
                    },
                    100005: function () {
                        h.type = e.data && (e.data.type ? e.data.type : "follow");
                        h.errortype = e.data && (e.data.errortype || "1");
                        return a.lib.dialog.memberDialog(h || {})
                    },
                    100008: function () {
                        a.lib.dialog.loginLayer({
                            lang: window.$CONFIG && window.$CONFIG.lang || "zh-cn"
                        })
                    },
                    100012: function () {
                        e.data && window.open(e.data, "_self")
                    }
                };
            k.init();
            g.getdata = function () {
                return h
            };
            g.getAction = function (a) {
                return a ? l[a] : l
            };
            g.getCode = function () {
                return e.code || ""
            };
            g.run = k.run;
            i.auto && k.run();
            return g
        }
    });
    STK.register("pl.content.feednav.source.tag", function (a) {
        var b = a.conf.trans.feed.tag,
            c = a.lib.kit.extra.language,
            d = {
                ITEM: c('<li tag_name="#{TAG_NAME}" action-type="feed_tag_hover" node-type="feed_tag"><span href="javascript:void(0);" class="W_btn_b W_btn_tag" onclick="return false;"><span class="W_arrow_bor W_arrow_bor_l" url="?is_tag=1&tag_name=#{TAG_NAME}" action-data="tag_name=#{TAG_NAME}" action-type="feed_tag_active" title="#L{查看这个标签下的微博}"><i class="S_line3"></i><em class="S_bg2_br"></em></span><a href="javascript:void(0);" node-type="txt">#{TAG_NAME}(<span node-type="num">#{COUNT}</span>)</a><a href="javascript:void(0);" class="W_ficon ficon_edit S_ficon" action-data="old_tag=#{TAG_NAME}" action-type="feed_tag_edit" title="#L{修改这个标签}">7</a><a href="javascript:void(0);" class="W_ficon ficon_close S_ficon" action-data="del_tag=#{TAG_NAME}" action-type="feed_tag_del" title="#L{删除这个标签}">X</a></span></li>'),
                EDIT: c('<li node-type="editor"><input type="text" node-type="tag_edit_form" type="text" class="W_input" value="#{TAG_NAME}"><a action-type="tag_edit_submit" action-data="old_tag=#{TAG_NAME}" href="javascript:void(0);" onclick="return false" class="W_btn_a">#L{保存}</a><a class="W_btn_b" action-type="tag_edit_cancel" action-data="old_tag=#{TAG_NAME}" href="javascript:void(0);" onclick="return false" class="W_btn_b">#L{取消}</a></li>')
            };
        return function (e) {
            function q() {
                var b = a.queryToJson(FM.getURL().query);
                b.tag_name && a.foreach(a.sizzle("[action-type=feed_tag_hover]"), function (c) {
                    var d = a.sizzle("em", c)[0];
                    if (c.getAttribute("tag_name") == b.tag_name) {
                        a.addClassName(a.lib.kit.dom.firstChild(c), "W_btn_tag_cur");
                        a.addClassName(d, "S_bg1_br");
                        a.removeClassName(d, "S_bg2_br")
                    } else {
                        a.removeClassName(a.lib.kit.dom.firstChild(c), "W_btn_tag_cur");
                        a.addClassName(d, "S_bg2_br");
                        a.removeClassName(d, "S_bg1_br")
                    }
                })
            }

            var f = a.delegatedEvent(e),
                g, h = {},
                i = {};
            a.custEvent.define(i, ["search"]);
            var j = function (b) {
                    var c = function () {
                            var b = Array.prototype.slice.apply(arguments);
                            if (!a.core.arr.isArray(b[0])) throw "The diff function needs an array as first parameter";
                            var c = a.core.arr.unique(b.shift());
                            if (c.length == 0) return [];
                            b = b.length == 1 ? b[0] : a.core.arr.unique(Array.prototype.concat.apply([], b));
                            return a.core.arr.clear(a.core.arr.foreach(c, function (c) {
                                return a.core.arr.inArray(c, b) ? null : c
                            }))
                        },
                        e = function (b, c) {
                            if (!b) return !1;
                            var e = a.sizzle("[tag_name=" + b + "]", h.show)[0],
                                f, g;
                            if (e) {
                                f = a.builder(e).list.count[0];
                                g = c + parseInt(f.innerHTML);
                                g < 0 && (g = 0);
                                f.innerHTML = "" + g
                            } else a.core.dom.insertHTML(h.show, a.core.util.templet(d.ITEM, {
                                TAG_NAME: b,
                                COUNT: 1
                            }))
                        },
                        f = b.res ? b.res.split(" ") : [],
                        g = b.now ? b.now.split(" ") : [],
                        i = c(f, g),
                        j = c(g, f);
                    if (i.length > 0)
                        for (var k in i) e(i[k], -1);
                    if (j.length > 0)
                        for (var k in j) e(j[k], 1)
                },
                k, l, m = {},
                n, o = function (b) {
                    a.foreach(b, function (a, b) {
                        a && a.style && (a.style.display = "")
                    })
                },
                p = function (b) {
                    a.foreach(b, function (a, b) {
                        a && a.style && (a.style.display = "none")
                    })
                };
            getTagNodes = function (b) {
                var c = a.lib.kit.dom.parentElementBy(b, h.show, function (a) {
                        if (a.getAttribute("node-type") == "feed_tag") return !0
                    }),
                    d = a.sizzle("[node-type=count]", c)[0],
                    e = a.sizzle("[node-type=txt]", c)[0],
                    f = a.sizzle("[action-type=feed_tag_del]", c)[0],
                    g = a.sizzle("[action-type=feed_tag_edit]", c)[0];
                return {
                    tag: c,
                    count: d,
                    text: e,
                    del: f,
                    edit: g
                }
            };
            var r = {
                feed_tag_hover: function (b) {
                    var c = b.evt.target && b.evt.target.getAttribute && b.evt.target.getAttribute("action-type");
                    c !== "feed_tag_del" && c !== "feed_tag_edit" && a.custEvent.fire(i, "search", {
                        is_tag: 1,
                        tag_name: b.el.getAttribute("tag_name")
                    })
                },
                feed_tag_page: function (a) {
                    s(a.data)
                },
                feed_tag_del: function (d) {
                    var f = d.el,
                        g = function () {
                            b.request("feedTagDel", {
                                onSuccess: function (b) {
                                    var c = a.lib.kit.dom.parentElementBy(f, h.show, function (a) {
                                        if (a.getAttribute("node-type") == "feed_tag") return !0
                                    });
                                    c.parentNode.removeChild(c);
                                    var d = a.sizzle("[node-type=feed_tag]", h.show);
                                    d.length == 0 && p([e])
                                },
                                onFail: function (b, d) {
                                    a.ui.alert(b.msg || c("#L{删除失败}"))
                                },
                                onError: function (b, d) {
                                    a.ui.alert(b.msg || c("#L{删除失败}"))
                                }
                            }, d.data)
                        };
                    a.ui.confirm(c("#L{你确定要删除这个微博标签吗？}"), {
                        textSmall: c("#L{删除微博标签不会将对应的微博一起删除}"),
                        OK: g
                    })
                },
                feed_tag_edit: function (b) {
                    r.tag_edit_cancel();
                    var c = b.el,
                        e = getTagNodes(c),
                        f = a.builder(a.templet(d.EDIT, {
                            TAG_NAME: b.data.old_tag
                        })).list.editor[0];
                    a.replaceNode(f, e.tag);
                    e.tag.setAttribute("isediting", 1);
                    m = {
                        tag: e,
                        editor: f
                    }
                },
                tag_edit_cancel: function () {
                    if (m.tag) {
                        a.replaceNode(m.tag.tag, m.editor);
                        m.tag.tag.removeAttribute("isediting")
                    }
                    m = {}
                },
                tag_edit_submit: function (d) {
                    var e = d.el,
                        f = m.tag,
                        g = d.data.old_tag,
                        h = a.sizzle("[node-type=tag_edit_form]", m.editor)[0],
                        i = a.trim(h.value);
                    i == g ? r.tag_edit_cancel() : b.request("feedTagEdit", {
                        onSuccess: function (a) {
                            f.text.value = a.data.tag;
                            f.edit.setAttribute("action-data", "old_tag=" + a.data.tag);
                            f.del.setAttribute("action-data", "del_tag=" + a.data.tag);
                            f.tag.setAttribute("tag_name", a.data.tag);
                            f.text.setAttribute = ("action-data", "tag_name=" + a.data.tag);
                            f.text.innerHTML = a.data.tag;
                            r.tag_edit_cancel()
                        },
                        onFail: function (b) {
                            a.ui.alert(b.msg || c("#L{更新失败}"))
                        },
                        onError: function (b) {
                            a.ui.alert(b.msg || c("#L{更新失败}"))
                        }
                    }, {
                        old_tag: g,
                        new_tag: i
                    })
                }
            };
            a.foreach(r, function (a, b) {
                f.add(b, "click", a)
            });
            var s = function (c, d) {
                    i.inited = !0;
                    c = c || {};
                    c = a.lib.kit.extra.merge({
                        is_tag: 1,
                        uid: $CONFIG.oid || $CONFIG.page_id
                    }, c);
                    b.request("feedTagListHtml", {
                        onSuccess: function (b) {
                            if (a.trim(b.data.html).length) {
                                e.innerHTML = b.data.html;
                                e.style.display = "";
                                g = a.builder(e).list;
                                h = {
                                    container: g.tag_container && g.tag_container[0],
                                    pagebar: g.feed_tag_pagebar && g.feed_tag_pagebar[0],
                                    show: g.tag_show && g.tag_show[0]
                                };
                                q();
                                d && d()
                            } else e.style.display = "none"
                        },
                        onFail: function (b, c) {
                            a.lib.dialog.ioError(b.code, b)
                        },
                        onError: function (b, c) {
                            a.lib.dialog.ioError(b.code, b)
                        }
                    }, c)
                },
                t = function () {
                    f && f.destroy && f.destroy();
                    a.custEvent.undefine(i, ["search"]);
                    a.conf.channel.feed.remove("feedTagUpdate", j);
                    FM.unstateChange(q)
                };
            FM.onstateChange(q);
            a.conf.channel.feed.register("feedTagUpdate", j);
            i.destroy = t;
            i.init = s;
            i.inited = !1;
            i.block = h;
            return i
        }
    });
    STK.register("conf.channel.fixed", function (a) {
        var b = ["init", "beforefixed", "fixed", "removefixed", "destroy", "reset"];
        return a.lib.kit.extra.listener.define("conf.channel.fixed", b)
    });
    STK.register("pl.content.feednav.source.init", function (a) {
        function e(a) {
            while (a = a.parentNode)
                if (a && a.style && a.style.position === "fixed") return !0;
            return !1
        }

        function d() {
            var b = [];
            a.foreach(a.sizzle("[module-type=feed]"), function (a, d) {
                b.push(c(a))
            });
            return b.join("|")
        }

        function c(a) {
            return !a || a == document.body ? !1 : a.id ? a.id : c(a.parentNode)
        }

        var b = a.lib.kit.extra.language,
            f = window.$CONFIG || {},
            g = a.pl.content.feednav.source,
            h = f.bigpipe === "true";
        return function (b) {
            function y(a) {
                a["fixed-target"] === j.feed_nav[0] && (j.feed_nav[0].style.cssText += "box-shadow:none;")
            }

            function x(a) {
                a["fixed-target"] === j.feed_nav[0] && (j.feed_nav[0].style.cssText += "box-shadow: 0 0 2px rgba(0, 0, 0, 0.2);")
            }

            function t() {
                function e(b) {
                    switch (b) {
                        case "all":
                            c(j.tab_all[0]);
                            d(j.tab_hot[0]);
                            d(j.tab_other[0]);
                            break;
                        case "hot":
                            d(j.tab_all[0]);
                            c(j.tab_hot[0]);
                            d(j.tab_other[0]);
                            break;
                        default:
                            d(j.tab_all[0]);
                            d(j.tab_hot[0]);
                            c(j.tab_other[0]);
                            if (j.feed_nav_adv) {
                                var e = a.sizzle("[filter-type-inlayer=" + b + "]", j.feed_nav_adv[0])[0];
                                if (e) {
                                    j.menutext[0].innerHTML = a.sizzle("a", e)[0].innerHTML;
                                    var f = e.getAttribute("action-data"),
                                        g = e.getAttribute("suda-data");
                                    j.tab_other[0].setAttribute("action-data", f);
                                    j.tab_other[0].setAttribute("suda-data", g);
                                    j.tab_other[0].setAttribute("type", b)
                                }
                            }
                    }
                }

                function d(b) {
                    var c = a.sizzle("a", b)[0];
                    a.removeClassName(b, "curr");
                    a.removeClassName(b, "S_bg2");
                    a.removeClassName(c, "S_line1")
                }

                function c(b) {
                    var c = a.sizzle("a", b)[0];
                    a.addClassName(b, "curr");
                    a.addClassName(b, "S_bg2");
                    a.addClassName(c, "S_line1")
                }

                var b = a.queryToJson(FM.getURL().query || "");
                switch (s(b)) {
                    case "searchAdv":
                        e("all");
                        r("searchAdv");
                        j.feed_nav[0].style.display = "none";
                        l.reset();
                        break;
                    case "rank":
                        e("rank");
                        r("searchAdv_rank");
                        a.foreach(j.search_rank, function (c) {
                            b.rank == a.queryToJson(c.getAttribute("action-data")).rank ? a.addClassName(c, "W_fb") : a.removeClassName(c, "W_fb")
                        });
                        break;
                    case "tag":
                        e("tag");
                        r("searchAdv_tag");
                        b.tag_name || m.init({}, function () {
                            var b = a.sizzle("[action-type=feed_tag_hover]", p.searchAdv_tag)[0];
                            if (b) {
                                var c = a.queryToJson(FM.getURL().query || "");
                                c.tag_name = b.getAttribute("tag_name");
                                if (f.bigpipe === "true") u(c, !1);
                                else {
                                    var d = a.sizzle("em", b)[0];
                                    if (b.getAttribute("tag_name") == c.tag_name) {
                                        a.addClassName(a.lib.kit.dom.firstChild(b), "W_btn_tag_cur");
                                        a.addClassName(d, "S_bg1_br");
                                        a.removeClassName(d, "S_bg2_br")
                                    }
                                }
                            }
                        });
                        m.inited || m.init({
                            tag_name: b.tag_name
                        });
                        break;
                    case "timefeed":
                        e("timefeed");
                        r("");
                        break;
                    case "ori":
                        e("ori");
                        r("");
                        break;
                    case "pic":
                        e("pic");
                        r("");
                        break;
                    case "video":
                        e("video");
                        r("");
                        break;
                    case "music":
                        e("music");
                        r("");
                        break;
                    case "article":
                        e("article");
                        r("");
                        break;
                    case "timeBase":
                        e("all");
                        r("");
                        break;
                    case "hot":
                        e("hot");
                        r("");
                        break;
                    case "all":
                        e("all");
                        r("rightbar");
                        break;
                    case "vclub":
                        e("vclub");
                        r("")
                }
            }

            function s(a) {
                if (a.is_searchadv == 1) return "searchAdv";
                if (a.stat_date) return "timeBase";
                if (a.rank) return "rank";
                if (a.is_tag == 1) return "tag";
                if (a.timefeed == 1) return "timefeed";
                if (a.is_hot == 1) return "hot";
                if (a.is_ori == 1) return "ori";
                if (a.is_pic == 1) return "pic";
                if (a.is_video == 1) return "video";
                if (a.is_music == 1) return "music";
                if (a.is_article == 1) return "article";
                if (a.is_all == 1) return "all";
                if (a.is_vclub == 1) return "vclub"
            }

            function r(b) {
                j.feed_nav[0].style.display = "";
                a.foreach(p, function (a) {
                    a && a.style && (a.style.display = "none")
                });
                a.foreach(b.split(" "), function (a) {
                    a = p[a];
                    a && a.style && (a.style.display = "")
                })
            }

            function q() {
                e(j.feed_nav[0]) && window.scrollTo(0, b.getBoundingClientRect().top + a.scrollPos().top - 60)
            }

            var c = a.delegatedEvent(b),
                i = a.lib.kit.extra.merge,
                j = a.builder(b).list,
                k, l, m, n = {},
                o = {},
                p = {
                    rightbar: j.input_search && j.input_search[0],
                    searchAdv: j.search && j.search[0],
                    searchAdv_tag: j.feed_tag_list && j.feed_tag_list[0],
                    searchAdv_rank: j.search_ranks && j.search_ranks[0]
                },
                u = function (b, c) {
                    var e = c === !1 ? "" : "pids=" + d() + "&",
                        f = FM.getURL().path,
                        g = a.jsonToQuery(b, !0),
                        i = "";
                    c != !1 && q();
                    if (h) {
                        g = g ? g : "";
                        i = f + "?" + e + g + (c === !1 ? "" : "#_0");
                        c === !1 ? FM.replaceState(i, c) : FM.setState(i, c)
                    } else {
                        i = f + "?" + g;
                        location.href.split("#")[0] != i && (c === !1 ? location.replace(i) : location.href = i)
                    }
                },
                v = {
                    search_type: function (b) {
                        if (!b.evt || !b.evt.target || b.evt.target.getAttribute("node-type") !== "morebtn") {
                            if (b.el.getAttribute("node-type") === "tab_other" && !b.el.getAttribute("type")) {
                                w.show();
                                return
                            }
                            var c = b.data;
                            k.setValue("");
                            k.restart();
                            u(c);
                            b.evt.type && a.preventDefault()
                        }
                    },
                    search_adv: function (b) {
                        if (b.data.type == 1) {
                            r("rightbar");
                            j.feed_nav[0].style.display = "";
                            if (a.queryToJson(FM.getURL().query || "").is_searchadv == 1) {
                                u({});
                                k.setValue("");
                                k.restart()
                            }
                        } else {
                            r("searchAdv");
                            j.feed_nav[0].style.display = "none";
                            l.reset();
                            q()
                        }
                        a.preventDefault()
                    },
                    taglist_close: function () {
                        u({});
                        a.preventDefault()
                    },
                    search_key: function () {
                        var b = !!a.queryToJson(FM.getURL().query).is_search,
                            c = a.trim(k.getValue());
                        if (!b && document.activeElement != j.keyword[0] && c === "") j.keyword[0].focus();
                        else {
                            var d = a.queryToJson(FM.getURL().query || "");
                            if (c != "") {
                                d.is_search = 1;
                                d.key_word = c
                            } else {
                                delete d.is_search;
                                delete d.key_word
                            }
                            u(d);
                            a.preventDefault()
                        }
                    },
                    search_rank: function (b) {
                        u(b.data);
                        a.preventDefault()
                    },
                    search_ranks: function (b) {
                        u(b.data);
                        a.preventDefault()
                    },
                    search_ranks_hide: function () {
                        u({});
                        a.preventDefault()
                    }
                },
                w = function () {
                    var b, c, d, e = {
                            show: function (b) {
                                if (c.style.display === "none") {
                                    c.style.display = "";
                                    c.style.zIndex = 10001;
                                    var e = a.position(d),
                                        f = d.offsetHeight;
                                    if (c) {
                                        c.style.position = "absolute";
                                        a.setXY(c, {
                                            t: e.t + f,
                                            l: e.l
                                        });
                                        a.ui.effect(c, "fadeInDown", "fast")
                                    }
                                }
                                window.SUDA && window.SUDA.uaTrack && window.SUDA.uaTrack("tblog_profile_new", "tab_more")
                            },
                            hide: function (d) {
                                d = a.fixEvent(d);
                                if (!a.contains(b, d.target) && d.target != b) {
                                    if ((a.contains(j.tab_other[0], d.target) || d.target == j.tab_other[0]) && !j.tab_other[0].getAttribute("type")) return;
                                    c && c.style.display != "none" && a.ui.effect(c, "fadeOutUp", "fast", function () {
                                        c.style.display = "none"
                                    })
                                }
                            }
                        },
                        f = function () {
                            a.addEvent(b, "click", e.show);
                            a.addEvent(document.body, "click", e.hide)
                        };
                    return {
                        init: function () {
                            d = j.tab_other[0];
                            b = j.morebtn[0];
                            c = j.feed_nav_adv[0];
                            !b || !c || !d || f()
                        },
                        show: function () {
                            e.show()
                        },
                        destroy: function () {
                            a.removeEvent(b, "click", e.show);
                            a.removeEvent(document.body, "click", e.hide)
                        }
                    }
                }(),
                z = function () {
                    var d = a.queryToJson(FM.getURL().query || "");
                    w.init();
                    a.foreach(v, function (a, b) {
                        c.add(b, "click", a)
                    });
                    k = a.lib.kit.dom.smartInput(j.keyword[0], {
                        notice: j.keyword[0].getAttribute("notice"),
                        value: d.is_search && d.key_word ? d.key_word : ""
                    });
                    a.hotKey.add(j.keyword[0], "enter", v.search_key);
                    var e = a.sizzle("[action-type=search_key]", b)[0];
                    o = setInterval(function () {
                        a.trim(k.getValue()).length ? a.removeClassName(e, "S_ficon_dis") : a.addClassName(e, "S_ficon_dis")
                    }, 500);
                    l = g.search(p.searchAdv);
                    m = g.tag(p.searchAdv_tag);
                    a.custEvent.add(l, "search", function (a, b) {
                        u(b)
                    });
                    a.custEvent.add(m, "search", function (a, b) {
                        u(b)
                    });
                    d.is_tag == 1 && m.init({}, function () {
                        var b = a.sizzle("[action-type=feed_tag_hover]", p.searchAdv_tag)[0];
                        if (b) {
                            var c = a.queryToJson(FM.getURL().query || "");
                            if (!c.tag_name) {
                                c.tag_name = b.getAttribute("tag_name");
                                if (f.bigpipe === "true") u(c, !1);
                                else {
                                    var d = a.sizzle("em", b)[0];
                                    if (b.getAttribute("tag_name") == c.tag_name) {
                                        a.addClassName(a.lib.kit.dom.firstChild(b), "W_btn_tag_cur");
                                        a.addClassName(d, "S_bg1_br");
                                        a.removeClassName(d, "S_bg2_br")
                                    }
                                }
                            }
                        }
                    });
                    a.conf.channel.fixed.register("fixed", x);
                    a.conf.channel.fixed.register("removefixed", y)
                },
                A = function () {
                    clearInterval(o);
                    FM.unstateChange(t);
                    c && c.destroy && c.destroy();
                    k && k.destroy && k.destroy();
                    l && l.destroy && l.destroy();
                    m && m.destroy && m.destroy();
                    a.hotKey.remove(j.keyword[0], "enter", v.search_key);
                    a.conf.channel.fixed.remove("fixed", x);
                    a.conf.channel.fixed.remove("removefixed", y)
                },
                n = {};
            n.destroy = A;
            z();
            t();
            FM.onstateChange(t);
            return n
        }
    });
    FM.register("pl.content.feednav.index", function (b, c) {
        return a.pl.content.feednav.source.init(a.E(b), c)
    })
});