package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement) {
        public AddressData(Address address) {
                this(address.getStreet(),address.getDistrict(),address.getCity(),address.getNumber(),
                        address.getComplement());
        }
}
