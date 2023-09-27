package med.voll.api.domain.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record RegisterPatientData(
        @NotBlank
        String name,
        @NotBlank
        String phoneNumber,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,10}")
        String dni,
        @NotNull
        AddressData address) {
}
