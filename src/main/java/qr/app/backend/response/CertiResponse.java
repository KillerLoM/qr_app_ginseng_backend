package qr.app.backend.response;

import lombok.Getter;
import lombok.Setter;
import qr.app.backend.model.Certificate;
import java.util.List;

@Getter
@Setter
public class CertiResponse {
    private int amount;
    private List<Certificate> certificates;
}
