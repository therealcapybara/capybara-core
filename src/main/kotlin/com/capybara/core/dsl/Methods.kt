package com.capybara.core.dsl

open class Method(val name: String)

object Get : Method("GET")

object Post : Method("POST")

object Delete : Method("DELETE")

object Put : Method("PUT")