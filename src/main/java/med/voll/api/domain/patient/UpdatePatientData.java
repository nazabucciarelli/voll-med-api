package med.voll.api.domain.patient;


import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdatePatientData(
        @NotNull
        Long id,
        String name,
        String email,
        String phoneNumber,
        String dni,
        AddressData address) { // Solo el id se verifica que no sea nulo, ya que el resto es opcional, como validamos
                                // anteriormente que si no es nulo se cambie el campo.
}
