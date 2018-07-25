package pl.training.bank.rest.controller;

import lombok.Setter;
import pl.training.bank.api.account.DispositionDto;
import pl.training.bank.service.disposition.DispositionExecutor;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static java.util.Collections.singletonList;

@Setter
@Path("dispositions")
public class DispositionController {

    @EJB
    private DispositionExecutor dispositionExecutor;

    @POST
    public Response process(DispositionDto dispositionDto) {
        dispositionExecutor.execute(singletonList(dispositionDto));
        return Response.noContent().build();
    }

}
