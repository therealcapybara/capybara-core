package com.capybara.core.dsl



class ResourceTest {

    fun shouldCreateResource() {
        val projectResoursce = resource("project") {

            methods {
                allows(Get)
                allows(Post)
                allows(Delete)
                allows(Put)
            }

            property("title") {
                hasType(Text)
            }

            property("description") {
                hasType(Text)
            }
        }
    }
}