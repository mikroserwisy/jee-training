package pl.training.bank.service.rest.controller;

import com.sun.jndi.toolkit.url.Uri;
import pl.training.bank.api.account.AccountDto;
import pl.training.bank.entity.Account;
import pl.training.bank.service.Mapper;
import pl.training.bank.service.account.AccountService;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("accounts")
public class AccountController {

    @EJB
    private AccountService accountService;
    @EJB
    private Mapper mapper;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createAccount() {
        Account account = accountService.createAccount();
        URI uri = currentUriWithId(account.getId());
        return Response.created(uri)
                .entity(mapper.map(account, AccountDto.class))
                .build();
    }

    @Path("{id:\\d+}")
    public AccountDto getById(@PathParam("id") Long id) {
        Account account = accountService.getAccount(id);
        return mapper.map(account, AccountDto.class);
    }

    private URI currentUriWithId(Long id) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        return uriBuilder.path(id.toString()).build();
    }

}
