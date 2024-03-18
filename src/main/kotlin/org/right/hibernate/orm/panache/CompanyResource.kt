package org.right.hibernate.orm.panache

import org.jboss.resteasy.reactive.RestPath;
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

@Path("companies")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

class CompanyResource {

    @GET
    fun get(): Response {
        return Response.ok(Company.listAll()).status(200).build()
    }


    @POST
    @Transactional
    fun create(company: Company): Response {
        if (company.isValid()) {
            company.persist()
            return Response.ok(Company.listAll()).status(201).build()
        }
        throw WebApplicationException("Company values are not valid.", 404)
    }


    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@RestPath id: Long): Response {
        val entity: Company = Company.findById(id)
            ?: throw WebApplicationException("Company with id of $id does not exist.", 404)
        entity.delete() 
        return Response.ok(Company.listAll()).status(200).build()
    }

}