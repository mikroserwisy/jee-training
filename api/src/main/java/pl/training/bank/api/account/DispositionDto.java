package pl.training.bank.api.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class DispositionDto implements Serializable {

    @NonNull
    private String accountNumber;
    @NonNull
    private long funds;
    @NonNull
    private String operationName;

}
