package ticketbus

class BootStrap {
    def springSecurityService

    def init = { servletContext ->
        new Pais(nome: 'Brasil').save()

        def userRole = new Role(authority: 'ROLE_USER').save()

        def me = new User(username: 'mm.marciomartins@gmail.com', password: 'super').save()

        UserRole.create(me, userRole)

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
