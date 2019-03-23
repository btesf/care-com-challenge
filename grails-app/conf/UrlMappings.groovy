import care.com.challenge.CareController

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller: 'Care', action: 'index')
        "500"(view:'/error')
	}
}
