package com.capybara.core.dsl


class App { }

fun app(body: App.() -> Unit) : App {
    val app = App()
    app.body()
    return app
}