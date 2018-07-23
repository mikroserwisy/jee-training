package pl.training.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    private Long id;
    @NonNull
    private String number;
    private long balance;

}
